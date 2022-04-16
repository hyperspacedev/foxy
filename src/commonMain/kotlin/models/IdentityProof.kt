/*
 * IdentityProof.kt
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

/** Represents a proof from an external identity provider */

@Serializable
data class IdentityProof(

    /** The name of the identity provider */
    val provider: String,

    /** The account owner's username on the identity provider's service */
    @SerialName("provider_username") val providerUsername: String,

    /** The account owner's profile URL on the identity provider */
    @SerialName("profile_url") val profileUrl: String,

    /** A link to a statement of identity proof, hosted by the identity provider */
    @SerialName("proof_url") val proofUrl: String,

    /** When the identity proof was last updated */
    @SerialName("updated_at") val updatedAt: String

)
