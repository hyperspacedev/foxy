/*
 * Account.kt
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

/** A data class representing an account */
@Serializable
data class Account(

    /** The account id */
    val id: String,

    /** The username of the account, not including domain */
    val username: String,

    /** The Webfinger account URI. Equal to username for local users, or username@domain for remote users */
    val acct: String,

    /** The location of the user's profile page */
    val url: String,

    /** The profile's display name */
    @SerialName("display_name") val displayName: String,

    /** The profile's bio / description */
    val note: String,

    /** An image icon that is shown next to statuses and in the profile */
    val avatar: String,

    /** A static version of the avatar. Equal to avatar if its value is a static image; different if avatar is an animated GIF */
    @SerialName("avatar_static") val avatarStatic: String,

    /** An image banner that is shown above the profile and in profile cards*/
    val header: String,

    /**  A static version of the header. Equal to header if its value is a static image; different if header is an animated GIF */
    @SerialName("header_static") val headerStatic: String,

    /** Whether the account manually approves follow requests */
    val locked: Boolean,

    /** Custom emoji entities to be used when rendering the profile. If none, an empty array will be returned */
    val emojis: List<Emoji>,

    /** Whether the account has opted into discovery features such as the profile directory */
    val discoverable: Boolean,

    /** When the account was created. ISO 8601 Datetime */
    @SerialName("created_at") val createdAt: String,

    /** When the most recent status was posted. ISO 8601 Datetime */
    @SerialName("last_status_at") val lastStatusAt: String,

    /** How many statuses are attached to this account */
    @SerialName("statuses_count") val statusesCount: Double,

    /** The reported followers of this profile */
    @SerialName("followers_count") val followersCount: Double,

    /** The reported follows of this profile */
    @SerialName("following_count") val followingCount: Double,

    /** Indicates that the profile is currently inactive and that its user has moved to a new account */
    val moved: Account? = null,

    /** Additional metadata attached to a profile as name-value pairs */
    val fields: List<Field>? = null,

    /** A presentational flag. Indicates that the account may perform automated actions, may not be monitored, or identifies as a robot */
    val bot: Boolean? = null,

    /** An extra entity to be used with API methods to verify credentials and update credentials */
    val source: Source? = null,

    /** An extra entity returned when an account is suspended */
    val suspended: Boolean? = null,

    /** When a timed mute will expire, if applicable */
    @SerialName("mute_expires_at") val muteExpiresAt: String? = null
)

