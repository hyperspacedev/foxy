/*
 * Source.kt
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


/** Represents display or publishing preferences of user's own account.
 *  Returned as an additional entity when verifying and updated credentials, as an attribute of Account. */
@Serializable
class Source(

    /** Profile bio */
    val note: String,

    /** Metadata about the account */
    val fields: List<Field>,

    /** The default post privacy to be used for new statuses */
    val privacy: String? = null,

    /** Whether new statuses should be marked sensitive by default */
    val sensitive: Boolean? = null,

    /** The default posting language for new statuses */
    val language: String? = null,

    /** The number of pending follow requests */
    @SerialName("follow_requests_count") val followRequestsCount: String? = null

)