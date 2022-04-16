/*
 * InstanceStats.kt
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

/** A data class representing statistics for an instance. */
@Serializable
data class InstanceStats(
    /** The number of registered users on the instance.
     *
     * This maps onto the "user_count" property of the Instance JSON object and has been renamed to be clearer in its
     * meaning.
     */
    @SerialName("user_count") val users: Double,

    /** The number of statuses written on the instance.
     *
     * This maps onto the "status_count" property of the Instance JSON object and has been renamed to be clearer in its
     * meaning.
     */
    @SerialName("status_count") val statuses: Double,

    /** The number of domains that federate with this instance.
     *
     * This maps onto the "domain_count" property of the Instance JSON object and has been renamed to be clearer in its
     * meaning.
     */
    @SerialName("domain_count") val federatedDomains: Double
)
