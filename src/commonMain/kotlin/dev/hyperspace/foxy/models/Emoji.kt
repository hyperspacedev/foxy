/*
 * Emoji.kt
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

/** A data class that represents a custom emoji. */
@Serializable
data class Emoji(

    /** The name of the custom emoji. */
    val shortcode: String,

    /** A URL containing the link to the custom emoji's image. */
    val url: String,

    /** A URL containing the link to the non-animated custom emoji's image. */
    @SerialName("static_url") val staticUrl: String,

    /** Whether the emoji is visible in the emoji picker. */
    @SerialName("visible_in_picker") val visibleInPicker: Boolean,

    /** The category this emoji is associated with, if one has been defined. */
    val category: String? = null
)
