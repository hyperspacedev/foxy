/*
 * Activity.kt
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

/** A data class that represents a week's worth of server activity. */
@Serializable
data class Activity(

    /** A UNIX timestamp corresponding to midnight of the first day of the week. */
    val week: String,

    /** The number of statuses that were authored since the week started. */
    val statuses: String,

    /** The number of times users have logged in since the week started. */
    val logins: String,

    /** The number of new users that have joined since the week started. */
    val registrations: String
)
