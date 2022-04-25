/*
 * Relationship.kt
 * Copyright (C) 2022 Hyperspace Developers.
 * This file is part of project Foxy.
 *
 * The Foxy project is non-violent software: you can use, redistribute, and/or modify it under the terms of the NPLv7+
 * as found in the LICENSE file in the source code root directory or at <https://git.pixie.town/thufie/npl-builder>.
 *
 * The Foxy project comes with ABSOLUTELY NO WARRANTY, to the extent permitted by applicable law.  See the
 * NPL for details.
 */

package dev.hyperspace.foxy.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** A data class that represents a relationship between two accounts.
 *
 * To simplify the explanations of the relationship in the documentation, 'A' will represent the current user, and 'B'
 * will represent the other account in the relationship.
 */
@Serializable
data class Relationship(

    /** The ID of B. */
    val id: String,

    /** Whether A is following B. */
    val following: Boolean,

    /** Whether A sees reblogged statuses from B. */
    @SerialName("showing_reblogs") val showingReblogs: Boolean,

    /** Whether A is notified of statuses B posts. */
    val notifying: Boolean,

    /** Whether B follows A. */
    @SerialName("followed_by") val followedBy: Boolean,

    /** Whether A is blocking B. */
    val blocking: Boolean,

    /** Whether B is blocking A. */
    @SerialName("blocked_by") val blockedBy: Boolean,

    /** Whether A is muting B. */
    val muting: Boolean,

    /** Whether A is muting notifications from B. */
    @SerialName("muting_notifications") val mutingNotifications: Boolean,

    /** Whether B is requesting to follow A. */
    val requested: Boolean,

    /** Whether A is blocking all accounts coming from B's instance. */
    @SerialName("domain_blocking") val domainBlocking: Boolean,

    /** Whether B is featured on A's profile. */
    val endorsed: Boolean,

    /** A custom note that A has written about B for personal reasons. */
    val note: String
)
