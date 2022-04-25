/*
 * Tag.kt
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

import kotlinx.serialization.Serializable

/** A data class representing a hashtag used in statuses and announcements. */
@Serializable
data class Tag(

    /** The name of the tag. */
    val name: String,

    /** A URL linking to a public page displaying statuses with this tag. */
    val url: String,

    /** A list of usage statistics for this tag. */
    val history: List<History>? = null
)
