/*
 * FoxyNotificationScope.kt
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

/** A sealed class that represents the different notification scopes available to the request builder. */
sealed class FoxyNotificationScope {

    /** Retrieves all notifications the user has. */
    object All : FoxyNotificationScope()

    /** Clears all notifications the user has. */
    object Clear : FoxyNotificationScope()

    /** Retrieves a notification with a specified ID. */
    class Notification(val id: String) : FoxyNotificationScope()

    /** Dismisses a notification with a specified ID. */
    class Dismiss(val id: String) : FoxyNotificationScope()
}
