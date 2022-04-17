/*
 * Application.kt
 * Copyright (C) 2022 Hyperspace Developers.
 * This file is part of project Foxy.
 *
 * The Foxy project is non-violent software: you can use, redistribute, and/or modify it under the terms of the NPLv7+
 * as found in the LICENSE file in the source code root directory or at <https://git.pixie.town/thufie/npl-builder>.
 *
 * The Foxy project comes with ABSOLUTELY NO WARRANTY, to the extent permitted by applicable law.  See the
 * NPL for details.
 */

package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** A data class that represents an application.
 *
 * This data class also includes properties used to handle authorizations as a client.
 */
@Serializable
data class Application(

    /** The client's ID. */
    val id: String? = null,

    /** The name of the application. */
    val name: String,

    /** The website associated with this application. */
    val website: String? = null,

    /** The URI that opens once the user authorizes the application to access their account.
     *
     * This field is only used in client authorization and is not returned in most cases for security reasons.
     */
    @SerialName("redirect_uri") val redirectUri: String? = null,

    /** The client ID key used to obtain OAuth tokens.
     *
     * This field is only used in client authorization and is not returned in most cases for security reasons.
     */
    @SerialName("client_id") val clientId: String? = null,

    /** The client secret key used to obtain OAuth tokens.
     *
     * This field is only used in client authorization and is not returned in most cases for security reasons.
     */
    @SerialName("client_secret") val clientSecret: String? = null,

    /** The VAPID key used for accessing the Push Streaming API. */
    @SerialName("vapid_key") val vapidKey: String? = null
)
