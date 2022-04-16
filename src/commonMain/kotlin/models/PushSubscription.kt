/*
 * PushSubscription.kt
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

/** A data class containing a subscription to the push streaming server.
 *
 * Clients utilizing this must have the "push" scope enabled in their client registration.
 */
data class PushSubscription(

    /** The subscription's ID. */
    val id: Double,

    /** The URL endpoint where push events will be sent to. */
    val endpoint: String,

    /** The instance's VAPID key. */
    @SerialName("server_key") val serverKey: String,

    /** The push subscription alert object that the endpoint will listen for. */
    val alerts: PushSubscriptionAlert
)
