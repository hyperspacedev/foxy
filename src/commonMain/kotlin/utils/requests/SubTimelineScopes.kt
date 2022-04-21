/*
 * SubTimelineScopes.kt
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

/** Represents sub-timeline scopes such as conversations, lists and markers */
sealed class SubTimelineScopes {

    /** Shows conversations when id and read are null.
     * Remove a conversation when id is provided and read is null on DELETE
     * Mark a conversation with id on POST
     * */
    class Conversations(val id: String? = null, val read: String? = "read") : SubTimelineScopes()

    /** Show all the user's list or create a list when id is null on GET and POST respectively.
     * Show a single list, update a list or delete a list on GET, PUT and DELETE respectively.
     * */
    class Lists(val id: String? = null) : SubTimelineScopes()

    /** Shows accounts in a list, add accounts to a list or remove accounts in a list on
     * GET, POST and DELETE respectively.
     * */
    class AccountsInList(val id: String) : SubTimelineScopes()

    /** Get or save a user's position in a timeline on GET and POST respectively.  */
    object Markers : SubTimelineScopes()
}