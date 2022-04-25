/*
 * Instance.kt
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

/** A data class representing the server instance the user is signed into. */
@Serializable
data class Instance(
    /** The server's URI. */
    val uri: String,

    /** The name of the instance. */
    val title: String,

    /** A description of the instance. */
    val description: String,

    /** A shorter variant of the description of the instance. */
    @SerialName("short_description") val shortDescription: String,

    /** The email address of the instance owner to contact, if necessary. */
    val email: String,

    /** The version of the Mastodon software that the instance runs on. */
    val version: String,

    /** A list of ISO 639 Part 1-5 language codes that the instance supports. */
    val languages: List<String>,

    /** Whether the instance is accepting registrations.
     *
     * This maps onto the "registrations" property of the Instance JSON object and has been renamed to be clearer in its
     * meaning.
     */
    @SerialName("registrations") val acceptingRegistrations: Boolean,

    /** Whether the administrator is required to approve a registration when a user registers an account. */
    @SerialName("approval_required") val approvalRequired: Boolean,

    /** Whether existing users can send invites to register for the instance. */
    @SerialName("invites_enabled") val invitesEnabled: Boolean,

    /** A list of streaming URLs of interest. */
    val urls: InstanceStreamingHash,

    /** The basic statistics of the instance. */
    val stats: InstanceStats,

    /** The URL linking to the instance's thumbnail image. */
    val thumbnail: String? = null,

    /** The account associated with the instance administrator or point of contact. */
    @SerialName("contact_account") val contactAccount: Account? = null
)
