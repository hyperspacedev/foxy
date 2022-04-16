/*
 * AnnouncementReaction.kt
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

/** A data class that represents a reaction on an announcement. */
@Serializable
data class AnnouncementReaction(

    /** The name of the emoji used in the reaction. */
    val name: String,

    /** The number of users who have reacted with this reaction. */
    val count: Int,

    /** Whether the current authorized user has reacted to this announcement with this reaction. */
    val me: Boolean,

    /** A URL containing the link to the custom emoji, if a custom emoji is used. */
    val url: String,

    /** A URL containing the link to the non-animated custom emoji, if a custom emoji is used. */
    @SerialName("static_url") val staticUrl: String
)
