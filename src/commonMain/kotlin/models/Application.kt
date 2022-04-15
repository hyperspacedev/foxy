package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Application(
    val id: String?,
    val name: String,
    val website: String? = null,
    @SerialName("redirect_uri") val redirectUri: String?,
    @SerialName("client_id") val clientId: String?,
    @SerialName("client_secret") val clientSecret: String?,
    @SerialName("vapid_key") val vapidKey: String?
)
