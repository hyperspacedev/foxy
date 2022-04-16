/*
 * Mention.kt
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

/** Represents a mention of a user within the content of a status */
@Serializable
data class Mention(

    /** The account id of the mentioned user */
    val id: String,

    /** The username of the mentioned user  */
    val username: String,

    /** The webfinger acct: URI of the mentioned user. Equivalent to username for local users, or username@domain for remote users */
    val acct: String,

    /** The location of the mentioned user's profile */
    val url: String,

    )
