/*
 * MarkerHash.kt
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

/** Represents nested attributes in Marker data class */

@Serializable
data class MarkerHash(

    /** The ID of the most recently viewed entity */
    @SerialName("last_read_id") val lastReadId: String,

    /** The timestamp of when the marker was set */
    @SerialName("updated_at") val updatedAt: String,

    /** Used for locking to prevent write conflicts */
    val version: Double

)
