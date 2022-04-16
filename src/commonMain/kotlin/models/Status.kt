/*
 * Status.kt
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

/** A data class representing a status posted by an account */
@Serializable
data class Status(

    /** ID of the status */
    val id: String,

    /** URI of the status used for federation */
    val uri: String,

    /**  The date when this status was created in ISO 8601 Datetime */
    @SerialName("created_at") val createdAt: String,

    /** The account that authored this status */
    val account: Account,

    /** HTML-encoded status content */
    val content: String,

    /** Visibility of this status */
    val visibility: String,

    /** Marks the status as sensitive content */
    val sensitive: Boolean,

    /** Subject or summary line, below which status content is collapsed until expanded */
    @SerialName("spoiler_text") val spoilerText: String,

    /** Media that is attached to this status */
    @SerialName("media_attachments") val mediaAttachment: List<Attachment>,

    /** The application used to post this status  */
    val application: Application,

    /** Mentions of users within the status content */
    //val mentions: List<Mention>,

    /** Hashtags used within the status content */
    val tags: List<Tag>,

    /** Custom emoji to be used when rendering status content. */
    val emoji: List<Emoji>,

    /** How many boosts this status has received */
    @SerialName("reblogs_count") val reblogsCounts: Double,

    /** How many favourites this status has received */
    @SerialName("favourites_count") val favouritesCount: Double,

    /** How many replies this status has received */
    @SerialName("replies_count") val repliesCount: Double,

    /** A link to the status's HTML representation */
    val url: String?,

    /** ID of the status being replied */
    @SerialName("in_reply_to_id") val inReplyToId: String?,

    /** ID of the account being replied to */
    @SerialName("in_reply_to_account_id") val inReplyToAccountId: String?,

    /** The status being reblogged */
    val reblog: Status?,

    /** The poll attached to the status */
    // val poll: Poll?

    /** Preview card for links included within status content */
    val card: Card?,

    /** Primary language of this status. ISO 639 Part 1 two-letter language code */
    val language: String?,

    /** Plain-text source of a status. Returned instead of content when status is deleted, so the user may redraft from the source text without the client having to reverse-engineer the original text from the HTML content. */
    val text: String?,

    /** Did a user favourite this status? */
    val fovourited: Boolean,

    /** Did a user boost this status? */
    val reblogged: Boolean,

    /** Did a user mute this status? */
    val muted: Boolean,

    /** Has a user bookmarked this status? */
    val bookmarked: Boolean,

    /** Did a user pin this status? Only appears if the status is pinnable */
    val pinned: Boolean?
)
