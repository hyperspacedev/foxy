/*
 * PushSubscriptionAlert.kt
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

import kotlinx.serialization.Serializable

/** A data class containing the kinds of alerts a push subscription will deliver to an endpoint. */
@Serializable
data class PushSubscriptionAlert(

    /** Whether an account has followed the user. */
    val follow: Boolean,

    /** Whether an account has favorited a user's status. */
    val favourite: Boolean,

    /** Whether an account has reblogged a user's status. */
    val reblog: Boolean,

    /** Whether the user has been mentioned in another account's status. */
    val mention: Boolean,

    /** Whether a poll that a user has voted in or created has ended. */
    val poll: Boolean
)
