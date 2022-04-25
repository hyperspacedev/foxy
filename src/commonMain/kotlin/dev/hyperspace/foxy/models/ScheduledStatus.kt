/*
 * ScheduledStatus.kt
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


/** A data class that represents a scheduled status that will be posted in the future. */
@Serializable
data class ScheduledStatus(

    /** The scheduled status's ID. */
    var id: String,

    /** An ISO-8061 string that states when the status will be posted. */
    @SerialName("scheduled_at") var scheduledAt: String,

    /** The parameters for the scheduled status upon creation. */
    val parameters: ScheduledStatusParameters,

    /** A list of the media attachments for the status. */
    @SerialName("media_attachments") val mediaAttachments: List<Attachment>
)
