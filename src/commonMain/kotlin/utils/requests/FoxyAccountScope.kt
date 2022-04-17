/*
 * FoxyAccountScope.kt
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

/** A sealed class that represents the different account scopes available to the request builder. */
sealed class FoxyAccountScope {

    enum class Statistics(val endpointName: String) {
        Statuses("statuses"),
        Following("following"),
        Followers("followers"),
        FeaturedTags("featured_tags"),
        Lists("lists"),
        IdentityProofs("identity_proofs")
    }

    enum class Actions(val endpointName: String) {
        Follow("follow"),
        Block("block"),
        Mute("mute"),
        Pin("pin")
    }

    /** The current user. */
    object CurrentUser : FoxyAccountScope()

    object Register : FoxyAccountScope()

    object Relationships : FoxyAccountScope()

    object UpdateCurrentUser : FoxyAccountScope()

    object Search : FoxyAccountScope()

    /** An account with a specified ID. */
    class Account(val id: String) : FoxyAccountScope()
    class AccountStatistic(val statistics: Statistics, val id: String) : FoxyAccountScope()
    class AccountAction(val action: Actions, val id: String) : FoxyAccountScope()
    class UndoAccountAction(val action: Actions, val id: String) : FoxyAccountScope()

    class AccountNote(val id: String) : FoxyAccountScope()
}
