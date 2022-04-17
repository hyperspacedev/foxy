/*
 * FoxyTimelineScope.kt
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

/** A sealed class that represents the different timeline scopes that the request builder has access to. */
sealed class FoxyTimelineScope {

    /** The home timeline. Requires authentication. */
    object Home : FoxyTimelineScope()

    /** The public or local timelines.
     *
     * Requires authentication if the instance has disabled public access to this API. */
    object Network : FoxyTimelineScope()

    /** The direct messages timeline. Requires authentication. */
    object Conversations : FoxyTimelineScope()

    /** A list with a specified ID. Requires authentication. */
    class List(val id: String) : FoxyTimelineScope()

    /** A timeline with statuses containing a specific hashtag. */
    class Tagged(val tag: String) : FoxyTimelineScope()

}
