import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import models.Application
import models.Token

data class FoxyApp(val name: String, val website: String?)


class Foxy(var domain: String = "https://mastodon.social") {

    private val scopes = listOf("read", "write", "follow")
    private var appEntity: Application? = null

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
    }

    private suspend fun makeRequest(type: HttpMethod, path: String, params: List<Pair<String, Any>>): HttpResponse {
        val response: HttpResponse = client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = domain
                method = type
                path(path)
            }

            headers {
                params.forEach { (name, value) -> append(name, value.toString()) }
            }
        }

        return response
    }

    private suspend fun registerApplication(
        instanceDomain: String,
        clientName: String,
        redirectUri: String,
        website: String?
    ): HttpResponse =
        makeRequest(
            HttpMethod.Post,
            "/api/v1/apps",
            listOf(
                Pair("client_name", clientName),
                Pair("redirect_uris", redirectUri),
                Pair("scopes", scopes.joinToString(" ")),
                Pair("website", website ?: "")
            )
        )

    suspend fun startOAuthFlow(app: FoxyApp, redirectUri: String): String {
        val fapEntity = registerApplication(domain, app.name, redirectUri, app.website)
            .body<Application>()
        appEntity = fapEntity

        val benjamin = scopes.joinToString(" ")

        val components = listOf(
            "$domain/oauth/authorize?response_type=code&client_id=${fapEntity.clientId}",
            "&client_secret=${fapEntity.clientSecret}&redirect_uri=$redirectUri&scope=$benjamin"
        )

        return components.joinToString("")
    }

    suspend fun finishOAuthFlow(code: String) {
        val authorizationCode = code.split("code")

        val fapEntity = appEntity ?: return

        val tokenEntity = makeRequest(
            HttpMethod.Post, "/oauth/token",
            listOf(
                Pair("grant_type", "authorization_code"),
                Pair("client_id", fapEntity.clientId),
                Pair("client_secret", fapEntity.clientSecret),
                Pair("redirect_uri", authorizationCode[0].removeSuffix("?")),
                Pair("code", authorizationCode[1].removePrefix("="))
            )
        ).body<Token>()

        println("MR SQUIIIIIIDWAAAARRRDDD! Your token is ${tokenEntity.accessToken}")
    }

    fun closeClient() {
        client.close()
    }
}