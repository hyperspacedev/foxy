/*
 * ScheduledStatusParameters.kt
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


/** A data class representing the parameters for creating a status. */
@Serializable
data class ScheduledStatusParameters(

    /** The text content of the scheduled status. */
    val text: String,

    /** The ID of the account the status replies to, if it is a reply. */
    @SerialName("in_reply_to_id") val inReplyToId: String? = null,

    /** A list of the IDs for media attachments. */
    @SerialName("media_ids") val mediaIds: List<String>? = null,

    /** Whether this status has been marked as sensitive and needs a content warning. */
    val sensitive: Boolean? = null,

    /** The spoiler text and/or content warning for this post, if it has been marked as sensitive. */
    @SerialName("spoiler_text") val spoilerText: String? = null,

    /** The visibility of the status. */
    val visibility: String,

    /** An ISO-8601 string that states when this status was scheduled. */
    @SerialName("scheduled_at") val scheduledAt: String? = null,

    /** The ID of the application used to post this status. */
    @SerialName("application_id") val applicationId: String
)
