/*
 * FoxyPersonalScope.kt
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

/** A sealed class representing different scopes available to current user's account  */
sealed class FoxyPersonalScope {

    /** An enumeration of different actions user can perform on their account.  */
    enum class Actions(val endpointName: String) {
        /** Accept a follow */
        Accept("authorize"),

        /** Reject a follow */
        Reject("reject"),
    }

    /** Current user's bookmarks */
    object Bookmarks : FoxyPersonalScope()

    /** Current user's favorites */
    object Favorites : FoxyPersonalScope()

    /** Current user's mutes */
    object Mutes : FoxyPersonalScope()

    /** Current user's blocks */
    object Blocks : FoxyPersonalScope()

    /** Use this to fetch a list of blocked domains, block a domain, or unblock a domain using GET, POST, or DELETE methods. */
    object DomainBlocks : FoxyPersonalScope()

    /** Fetches a list of all filters or creates a filter using GET or POST methods on null id
     *
     * View a single filter, update it or remove it using POST, PUT or DELETE methods
     * */
    class Filters(val id: String? = null) : FoxyPersonalScope()

    /** Report a user */
    object Report : FoxyPersonalScope()

    /** Fetch all the pending follow requests if id is null otherwise
     * accept or reject a certain follow. */
    class Follow(val id: String? = null, val action: Actions? = null) : FoxyPersonalScope()

    /** Fetch currently featured profiles on user's profile */
    object Endorsements : FoxyPersonalScope()

    /** Fetch or feature a tag if id and suggested is null on GET or POST methods respectively.
     *Otherwise, unfeature a tag on id or show suggested tags
     * */
    class FeaturedTags(val id: String? = null, val suggested: String? = "suggestions") : FoxyPersonalScope()

    /** Preferences user defined in their settings */
    object Preferences : FoxyPersonalScope()

    /** Server generated suggestions on how to follow based on previous interactions.
     * On DELETE, remove an account to follow from suggestion on id
     * */
    class Suggestions(val id: String? = null) : FoxyPersonalScope()
}