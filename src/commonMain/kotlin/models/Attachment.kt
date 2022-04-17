/*
 * Attachment.kt
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
import utils.annotations.MastodonDeprecated

/** A data class that represents an attachment on a status. */
@Serializable
data class Attachment(

    /** The ID of the attachment. */
    val id: String,

    /** The type of attachment, used for rendering purposes. */
    val type: String,

    /** The URL linking to the attachment media. */
    val url: String,

    /** The URL linking to the attachment's preview. */
    @SerialName("preview_url") val previewUrl: String,

    /** The URL linking to the attachment's remote URL, if it wasn't loaded from the server itself. */
    @SerialName("remote_url") val remoteUrl: String? = null,

    /** The shorter text URL to the attachment.
     *
     * Note that this field has been deprecated since Mastodon v3.5.0 and will likely be replaced.
     */
    @MastodonDeprecated("0.6.0", "3.5.0", replaceWith = ReplaceWith("url"))
    @SerialName("text_url") val textUrl: String? = null,

    /** The alternate text for this attachment. */
    val description: String? = null,

    /** The blur hash used for creating blurred images.
     *
     * This is typically used for images with content warnings attached to them.
     */
    val blurhash: String? = null
)
