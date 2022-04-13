import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.auth.*
import io.ktor.utils.io.core.*
import io.ktor.client.call.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*

class Foxy {
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

    suspend fun makeRequest(url: String, path: String): HttpResponse {
        val response: HttpResponse = client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = url
                method = HttpMethod.Get
                path(path)
                parameter("limit", 2)
            }
        }

        return response
    }

    suspend fun registerApplication(
        instanceDomain: String,
        clientName: String,
        redirectUri: String,
        website: String?
    ): HttpResponse {
        val response: HttpResponse = client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = instanceDomain
                method = HttpMethod.Post
                path("/api/v1/apps")
            }
            headers {
                append("client_name", clientName)
                append("redirect_uris", redirectUri)
                append("scopes", listOf("read", "write", "follow").joinToString(" "))
                append("website", website ?: "")
            }
        }
        return response
    }

    fun closeClient() {
        client.close()
    }
}