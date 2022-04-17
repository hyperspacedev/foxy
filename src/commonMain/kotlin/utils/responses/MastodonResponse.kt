/*
 * MastodonResponse.kt
 * Copyright (C) 2022 Hyperspace Developers.
 * This file is part of project Foxy.
 *
 * The Foxy project is non-violent software: you can use, redistribute, and/or modify it under the terms of the NPLv7+
 * as found in the LICENSE file in the source code root directory or at <https://git.pixie.town/thufie/npl-builder>.
 *
 * The Foxy project comes with ABSOLUTELY NO WARRANTY, to the extent permitted by applicable law.  See the
 * NPL for details.
 */

package utils.responses

import models.MastodonError

/** A sealed class that represents the states of a response from the Mastodon server. */
sealed class MastodonResponse<out T> {

    /** A data class that indicates the request succeeded. */
    data class Success<out T>(

        /** The entity requested from the server in the type specified. */
        val entity: T
    ) : MastodonResponse<T>()

    /** A data class indicates a request failed. */
    data class Error(
        /** The error reported from the Mastodon server. */
        val error: MastodonError
    ) : MastodonResponse<Nothing>()
}