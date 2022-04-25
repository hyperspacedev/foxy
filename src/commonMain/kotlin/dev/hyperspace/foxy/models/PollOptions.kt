/*
 * PollOptions.kt
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

/** A data class representing possible poll options */
@Serializable
data class PollOptions(

    /** The text value of the poll option  */
    val title: String,

    /** The number of received votes for this option. Number, or null if results are not published yet */
    @SerialName("votes_count") val votesCount: Double? = null
)
