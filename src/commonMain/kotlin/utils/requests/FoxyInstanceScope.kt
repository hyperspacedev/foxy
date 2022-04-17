/*
 * FoxyInstanceScope.kt
 * Copyright (C) 2022 Hyperspace Developers.
 * This file is part of project Foxy.
 *
 * The Foxy project is non-violent software: you can use, redistribute, and/or modify it under the terms of the NPLv7+
 * as found in the LICENSE file in the source code root directory or at <https://git.pixie.town/thufie/npl-builder>.
 *
 * The Foxy project comes with ABSOLUTELY NO WARRANTY, to the extent permitted by applicable law.  See the
 * NPL for details.
 */

package utils.requests

/** A sealed class that represents the various endpoints for instances that the request builder has access to. */
sealed class FoxyInstanceScope {

    /** Retrieves general information about the instance. */
    object Instance : FoxyInstanceScope()

    /** Retrieves a list of peer instances that federate with this instance. */
    object Peers : FoxyInstanceScope()

    /** Retrieves an activity stream from the instance. */
    object Activity : FoxyInstanceScope()
}
