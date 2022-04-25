/*
 * Conversation.kt
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

/** A data class that represents a direct message conversation. */
@Serializable
data class Conversation(

    /** The conversation ID. */
    val id: String,

    /** The list of accounts involved in the conversation. */
    val accounts: List<Account>,

    /** Whether the conversation is currently unread. */
    val unread: Boolean,

    /** The last status in the conversation thread. */
    @SerialName("last_status") val lastStatus: Status? = null
)
