/*
 * FoxyStatusScope.kt
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

/** A sealed class that represents the different status scopes available to the request builder.*/
sealed class FoxyStatusScope {

    /** An enumeration of the different actions a user can perform on a status passively. */
    enum class Actions(val endpointName: String) {

        /** Favorites the status. */
        Favorite("favourite"),

        /** Reblogs the status. */
        Reblog("reblog"),

        /** Pins the status to their profile. */
        Pin("pin"),

        /** Saves the status to their bookmarks. */
        Bookmark("bookmark"),

        /** Mutes the conversation. */
        Mute("mute")
    }

    /** Performs actions relating to creating and editing statuses.
     *
     * Use this to create, modify, and/or delete statuses using the POST, PUT, and DELETE methods, respectively.
     */
    object Editor : FoxyStatusScope()

    /** Retrieves a status with a specific ID. */
    class Status(val id: String) : FoxyStatusScope()

    /** Retrieves the context for a status with a specific ID. */
    class Context(val statusId: String) : FoxyStatusScope()

    /** Retrieves the accounts that have favorited a status with a specific ID. */
    class FavoritedBy(val statusId: String) : FoxyStatusScope()

    /** Retrieves the accounts that have reblogged a status with a specific ID. */
    class RebloggedBy(val statusId: String) : FoxyStatusScope()

    /** Perform an action on an existing status passively. */
    class Action(val action: Actions, val id: String) : FoxyStatusScope()

    /** Undo an action performed on an existing status passively. */
    class UndoAction(val action: Actions, val id: String) : FoxyStatusScope()
}
