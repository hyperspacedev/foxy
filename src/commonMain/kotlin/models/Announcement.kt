/*
 * Announcement.kt
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

/** A data class representing a system-wide announcement. */
@Serializable
data class Announcement(

    /** The ID that identifies this announcement. */
    val id: String,

    /** The sanitized HTML contents of the announcement. */
    val content: String,

    /** An ISO-8601 string that describes when the announcement starts to broadcast. */
    @SerialName("starts_at") val startsAt: String? = null,

    /** An ISO-8601 string that describes when the announcement will stop broadcasting. */
    @SerialName("ends_at") val endsAt: String? = null,

    /** Whether the announcement will broadcast all day instead of a specific time. */
    @SerialName("all_day") val allDay: Boolean,

    /** An ISO-8601 string that describes when the announcement was published. */
    @SerialName("published_at") val publishedAt: String,

    /** An ISO-8601 string that describes when the announcement was updated. */
    @SerialName("updated_at") val updatedAt: String,

    /** Whether the current authorized user has read the announcement. */
    val read: Boolean,

    /** The list of accounts mentioned in the announcement. */
    val mentions: List<Account>,

    /** The list of statuses associated with this announcement. */
    val statuses: List<Status>,

    /** A list of the tags associated with this announcement. */
    val tags: List<Tag>,

    /** The list of custom emojis associated with this announcement. */
    val emojis: List<Emoji>,

    /** The list of reactions associated with this announcement. */
    val reactions: List<AnnouncementReaction>
)
