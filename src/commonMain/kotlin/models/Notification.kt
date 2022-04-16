/*
 * Notification.kt
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

/** Represents a notification of an event relevant to the user. */

@Serializable
data class Notification(

    /** The id of the notification in the database */
    val id: String,

    /** The type of event that resulted in the notification */
    val type: String,

    /** The timestamp of the notification */
    @SerialName("created_at") val createdAt: String,

    /** The account that performed the action that generated the notification */
    val account: Account,

    /** Status that was the object of the notification, e.g. in mentions, reblogs, favourites, or polls */
    val status: Status?
)
