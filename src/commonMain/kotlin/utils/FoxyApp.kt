/*
 * FoxyApp.kt
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

/** A data class that represents an application that will be made on the Mastodon server. */
data class FoxyApp(
    /** The name of the application. */
    val name: String,

    /** The URL linking to the application's website. */
    val website: String?
)

