/*
 * MastodonError.kt
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

/** A data class representing an error message sent by Mastodon when an error occurred in the request. */
@Serializable
data class MastodonError(

    /** The primary error message. */
    val error: String,

    /** A longer description of the error, which may contain additional information.
     *
     * This is filled in typically with the OAuth API.
     */
    @SerialName("error_description") val errorDescription: String
)
