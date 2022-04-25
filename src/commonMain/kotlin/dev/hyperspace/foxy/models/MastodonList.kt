/*
 * MastodonList.kt
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

/** Represents a list of some users that the authenticated user follows */

@Serializable
data class MastodonList(

    /** The internal database ID of the list */
    val id: String,

    /** The user-defined title of the list */
    val title: String,

    /** The user-defined title of the list */
    @SerialName("replies_policy") val repliesPolicy: String

)
