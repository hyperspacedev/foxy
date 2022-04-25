/*
 * Preferences.kt
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

/** Represents a user's preferences */

@Serializable
data class Preferences(

    /** Default visibility for new posts. Equivalent to Source#privacy
     *
     * @see Source.privacy
     */
    @SerialName("posting:default:visibility") val defaultVisibility: String,

    /** Default sensitivity flag for new posts. Equivalent to Source#sensitive
     *
     * @see Source.sensitive
     */
    @SerialName("posting:default:sensitive") val defaultSensitivity: Boolean,

    /** Default language for new posts. Equivalent to Source#language
     *
     * @see Source.language
     */
    @SerialName("posting:default:language") val defaultLanguage: String? = null,

    /** Whether media attachments should be automatically displayed or blurred/hidden */
    @SerialName("reading:expand:media") val expandMedia: String,

    /** Whether CWs should be expanded by default */
    @SerialName("reading:expand:spoilers") val expandSpoiler: String

)
