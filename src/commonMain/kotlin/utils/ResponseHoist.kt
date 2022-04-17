/*
 * ResponseHoist.kt
 * Copyright (C) 2022 Hyperspace Developers.
 * This file is part of project Foxy.
 *
 * The Foxy project is non-violent software: you can use, redistribute, and/or modify it under the terms of the NPLv7+
 * as found in the LICENSE file in the source code root directory or at <https://git.pixie.town/thufie/npl-builder>.
 *
 * The Foxy project comes with ABSOLUTELY NO WARRANTY, to the extent permitted by applicable law.  See the
 * NPL for details.
 */

package utils

/** Hoists the entity value of the response up.
 *
 * @return The entity value of the response if the response is successful, or null if the response is an error.
 */
fun <T> MastodonResponse<T>.hoistEntityOrNull(): T? =
    when (this) {
        is MastodonResponse.Error -> null
        is MastodonResponse.Success<T> -> entity
    }