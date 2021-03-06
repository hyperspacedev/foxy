/*
 * FeaturedTag.kt
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

@Serializable
/** A data class representing a tag that is featured on a profile. */
data class FeaturedTag(

    /** The featured tag's ID. */
    val id: String,

    /** The tag's name.
     * @see Tag.name
     */
    val name: String,

    /** The URL linking to a public page displaying statuses tagged with this tag.
     * @see Tag.url
     */
    val url: String,

    /** The number of statuses containing the tag written by the author. */
    @SerialName("statuses_count") val statusesCount: Double,

    /** An ISO-8601 string containing the date of the last status written by the author with this tag. */
    @SerialName("last_status_at") val lastStatusAt: String
)
