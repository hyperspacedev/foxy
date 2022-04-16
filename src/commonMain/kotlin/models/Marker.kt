/*
 * Marker.kt
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

import kotlinx.serialization.Serializable

/** Represents the last read position within a user's timelines */

@Serializable
data class Marker(

    /** Information about the user's position in the home timeline */
    val home: MarkerHash,
    /**  Information about the user's position in their notifications */
    val notifications: MarkerHash

)
