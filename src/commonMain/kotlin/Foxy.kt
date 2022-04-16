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
import kotlin.time.Duration.Companion.days

data class FoxyApp(val name: String, val website: String?)


class Foxy(var domain: String = "mastodon.social") {

    private val scopes = listOf("read", "write", "follow")
    private var appEntity: Application? = null
    private var session: ValidatedSession? = null

    enum class AuthGrantType(val parameter: String) {
        ClientCredential("client_credentials"),
        AuthorizationCode("authorization_code")
    }

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

    private suspend fun makeRequest(type: HttpMethod, path: String, params: List<Pair<String, Any?>>): HttpResponse =
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

    suspend fun finishOAuthFlow(grant: AuthGrantType, code: String = "") {
        val authorizationCode = code.split("code")
        val redirectUri =
            if (authorizationCode.count() > 1) authorizationCode[0].removeSuffix("?") else "urn:ietf:wg:oauth:2.0:oob"

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

    fun closeClient() {
        client.close()
    }
}