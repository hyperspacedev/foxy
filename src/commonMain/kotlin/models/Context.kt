/*
 * Context.kt
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


/** A data class that represents contextual information pertaining to a specific status. */
@Serializable
data class Context(

    /** The list of statuses that preceded the current status. */
    val ancestors: List<Status>,

    /** The list of statuses that follow the current status. */
    val descendants: List<Status>
)
