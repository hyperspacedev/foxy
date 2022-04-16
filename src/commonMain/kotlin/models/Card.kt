/*
 * Card.kt
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

/** A data class that represents a rich preview created from OpenGraph tags. */
@Serializable
data class Card(
    /** The URL to the content of the preview. */
    val url: String,
    /** The preview's title. */
    val title: String,

    /** The preview's description. */
    val description: String,

    /** The type of media the preview links to. */
    val type: String,

    /** The name of the author that created the content. */
    @SerialName("author_name") val authorName: String?,

    /** The URL linking to the author that created the content. */
    @SerialName("author_url") val authorUrl: String?,

    /** The name of the provider of the content. */
    @SerialName("provider_name") val providerName: String?,

    /** The URL linking to the provider that provides the content. */
    @SerialName("provider_url") val providerUrl: String?,

    /** Embeddable HTML code to generate the preview. */
    val html: String?,

    /** The card's width expressed as a Double. */
    val width: Double?,

    /** The card's height expressed as a Double. */
    val height: Double?,

    /** The URL linking to the preview image of the card. */
    val image: String?,

    /** The URL linking to the embedded content for the preview.
     *
     * This is typically used in place of the Card.html property for photo embeds.
     * @see Card.html
     */
    @SerialName("embed_url") val embedUrl: String?,

    /** The blur hash used for creating blurred images.
     *
     * This is typically used for images with content warnings attached to them.
     */
    val blurhash: String?
)
