/*
 * Filter.kt
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

/** A data class describing a user-defined filter. */
@Serializable
data class Filter(

    /** The filter ID. */
    val id: String,

    /** The phrase to filter out from timelines. */
    val phrase: String,

    /** A list of strings containing where to filter out the phrase in question.
     *
     * Acceptable strings are: "home", "notifications", "public", "thread".
     */
    val context: List<String>,

    /** An ISO-8601 string containing the date that filter expires, if it is set to expire. */
    @SerialName("expires_at") val expiresAt: String?,

    /** Whether matching entities should be dropped entirely from the server in home and notifications. */
    val irreversible: Boolean,

    /** Whether the filter should consider word boundaries. */
    @SerialName("whole_word") val wholeWord: Boolean
)
