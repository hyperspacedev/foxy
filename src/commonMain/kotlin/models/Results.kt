/*
 * Results.kt
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

@Serializable
data class Results(

    /** Accounts which match the given query */
    val account: List<Account>,

    /** Statuses which match the given query */
    val status: List<Status>,

    /** Hashtags which match the given query
     *
     * Returns a list of tags which was added in v2 of the API
     * If you are using v1, please use a different field
     * */
    val hashtags: List<Tag>
)
