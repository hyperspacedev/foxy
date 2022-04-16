/*
 * Field.kt
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

/** A data class representing a field on a profile. */
@Serializable
data class Field(

    /** The name or key of the field. */
    val name: String,

    /** The field's value. */
    val value: String,

    /** An ISO-8601 string containing the date that the field was verified.
     *
     * This is typically used to display when an account has verified that they own the domain/website in the value
     * field.
     */
    @SerialName("verified_at") val verifiedAt: String?
)
