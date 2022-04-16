/*
 * Poll.kt
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

@Serializable
data class Poll(

    /** The ID of the poll in the database */
    val id: String,

    /** When the poll ends or null if the poll does not end */
    @SerialName("expires_at") val expiresAt: String?,

    /** Is the poll currently expired? */
    val expired: Boolean,

    /** Doeds the poll allow multiple-choice answers? */
    val multiple: Boolean,

    /** How many unique accounts have voted on a multiple-choice poll */
    @SerialName("votes_count") val votesCount: Double,

    /** When called with a user token, has the authorized user voted? Or null if no current user */
    val voted: Boolean?,

    /** Then called with a user token, which options has the authorized user chosen? Contains an array of index values for options or null if no current user*/
    @SerialName("own_votes") val ownVotes: List<Double>?,

    /** Possible answers for the poll */
    val options: List<PollOptions>,

    /** Custom emoji to be used for rendering poll options */
    val emoji: List<Emoji>
)
