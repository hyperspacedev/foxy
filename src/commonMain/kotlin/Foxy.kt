/*
 * Foxy.kt
 * Copyright (C) 2022 Hyperspace Developers.
 * This file is part of project Foxy.
 *
 * The Foxy project is non-violent software: you can use, redistribute, and/or modify it under the terms of the NPLv7+
 * as found in the LICENSE file in the source code root directory or at <https://git.pixie.town/thufie/npl-builder>.
 *
 * The Foxy project comes with ABSOLUTELY NO WARRANTY, to the extent permitted by applicable law.  See the
 * NPL for details.
 */

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.datetime.Instant
import kotlinx.serialization.json.Json
import models.Application
import models.Token
import security.ValidatedSession
import utils.FoxyApp
import utils.FoxyRequestBuilder
import utils.responses.MastodonResponse
import kotlin.native.concurrent.ThreadLocal
import kotlin.time.Duration.Companion.days

/** The primary HTTP client that will make requests to the Mastodon server. */
@ThreadLocal
object Foxy {

    /** The domain to make requests to. */
    private var domain: String = "mastodon.social"

    /** The list of scopes that the client will have control over. */
    private val scopes: MutableList<String> = mutableListOf("read", "write", "follow")

    /** The application entity created during authorization. */
    private var appEntity: Application? = null

    /** The client's current session with the access token. */
    private var session: ValidatedSession? = null

    /** An enumeration class describing the different grant methods. */
    enum class AuthGrantType(val parameter: String) {

        /** The client wants to obtain app-level access. */
        ClientCredential("client_credentials"),

        /** The client want to obtain user-level access. */
        AuthorizationCode("authorization_code")
    }

    /** The primary HTTP client agent. */
    private val client = HttpClient(CIO) {
        install(Auth) {
            bearer {
                loadTokens {
                    // TODO: Once we have a Bearer Token, define logic to load it from storage/cache here
                    BearerTokens("Access token here??", "Something goes here too")
                }
                refreshTokens {
                    // TODO: Specify logic to refresh the token here
                    BearerTokens("Access token here??", "Something goes here too")
                }
            }
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    /** Make an HTTP request to the Mastodon server.
     * @param builder A receiver closure describing the request to make to the server.
     * @return A MastodonResponse that contains the data requested, or the error reported by the server.
     * @see MastodonResponse
     */
    suspend inline fun <reified T> request(builder: FoxyRequestBuilder.() -> Unit): MastodonResponse<T> {
        val request = FoxyRequestBuilder()
        builder(request)
        val response = makeRequest(request.method, request.getEndpoint(), request.getParams())
        if (response.status != HttpStatusCode.OK)
            return MastodonResponse.Error(error = response.body())
        return MastodonResponse.Success(response.body() as T)
    }

    /** Sets the instance domain of the client to make requests to.
     *
     * This is used if Foxy.startOAuthFlow has not been called.
     * @param domain The instance's URI, without the HTTP protocol marker.
     * @see Foxy.startOAuthFlow
     */
    fun setInstance(domain: String) {
        this.domain = domain
    }

    /** Starts the OAuth authorization process by making a request to the server and fetching the authorization URL.
     * @param domain The instance's URI, without the HTTP protocol marker.
     * @param app The application that will be created on the server.
     * @param redirectUri The URI that the user will be redirected to when authorization is accepted or rejected.
     * @return The URL that the user will visit to authenticate and authorize the app. This can be ignored if the app is
     * obtaining app-level access rather than user-level access.
     */
    suspend fun startOAuthFlow(domain: String, app: FoxyApp, redirectUri: String): String {
        val fapEntity = registerApplication(domain, app.name, redirectUri, app.website)
            .body<Application>()

        appEntity = fapEntity

        val benjamin = scopes.joinToString("%20")

        val components = listOf(
            "https://$domain/oauth/authorize",
            "?response_type=code",
            "&client_id=${fapEntity.clientId}",
            "&client_secret=${fapEntity.clientSecret}",
            "&redirect_uri=$redirectUri",
            "&scope=$benjamin"
        )

        return components.joinToString("")
    }

    /** Finishes authorization process by retrieving the access code to create the token and storing it securely.
     * @param grant The level of access the app will have.
     * @param code The URL containing the access code needed to obtain an access token. This is unused when only
     * app-level access is requested.
     */
    suspend fun finishOAuthFlow(grant: AuthGrantType, code: String = "") {
        val authorizationCode = code.split("code")
        val redirectUri =
            if (authorizationCode.count() > 1) authorizationCode[0].removeSuffix("?")
            else "urn:ietf:wg:oauth:2.0:oob"

        val fapEntity = appEntity ?: return

        val tokenEntity = makeRequest(
            HttpMethod.Post, "/oauth/token",
            buildList {
                addAll(
                    listOf(
                        Pair("grant_type", grant.parameter),
                        Pair("client_id", fapEntity.clientId),
                        Pair("client_secret", fapEntity.clientSecret),
                        Pair("redirect_uri", redirectUri)
                    )
                )

                if (grant == AuthGrantType.AuthorizationCode)
                    add(Pair("code", authorizationCode[1].removePrefix("=")))

            }
        ).body<Token>()

        session =
            ValidatedSession(
                tokenEntity.accessToken,
                Instant.fromEpochSeconds(tokenEntity.createdAt.toLong())
                    .toString()
            )
        //FIXME: A user should be able to change the lifespan of their token but the default should be 2 weeks.
        session?.setIntegrityStamp(
            14.days
                .inWholeSeconds
        )
    }

    fun close() {
        client.close()
    }

    /** Makes a general HTTP request using the domain and access token. */
    @PublishedApi
    internal suspend fun makeRequest(type: HttpMethod, path: String, params: List<Pair<String, Any?>>): HttpResponse =
        client.request {
            url {
                protocol = URLProtocol.HTTPS
                host = domain
                method = type
                path(path)
            }
            params.forEach { (name, value) ->
                parameter(name, value)
            }
        }

    /** Registers the application for authorization and use on the server. */
    private suspend fun registerApplication(
        instanceDomain: String,
        clientName: String,
        redirectUri: String,
        website: String?
    ): HttpResponse {
        domain = instanceDomain
        return makeRequest(
            HttpMethod.Post,
            "/api/v1/apps",
            listOf(
                Pair("client_name", clientName),
                Pair("redirect_uris", redirectUri),
                Pair("scopes", scopes.joinToString(" ")),
                Pair("website", website ?: "")
            )
        )
    }
}