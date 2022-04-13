package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Application(
    val name: String,
    val website: String? = null,
    @SerialName("client_id") val clientId: String,
    @SerialName("client_secret") val clientSecret: String
)
