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

package dev.hyperspace.foxy.utils.requests

/** A sealed class that represents the different account scopes available to the request builder. */
sealed class FoxyAccountScope {

    /** An enumeration of the different statistics that an account may contain. */
    enum class Statistics(val endpointName: String) {

        /** The statuses an account has authored. */
        Statuses("statuses"),

        /** The accounts they are following. */
        Following("following"),

        /** The accounts that follow this account. */
        Followers("followers"),

        /** The tags that this account features. */
        FeaturedTags("featured_tags"),

        /** The lists the account has created. */
        Lists("lists"),

        /** The identity proofs that the account contains. */
        IdentityProofs("identity_proofs")
    }

    /** An enumerations of the actions a user can take on an account. */
    enum class Actions(val endpointName: String) {

        /** Follow the account. */
        Follow("follow"),

        /** Block the account. */
        Block("block"),

        /** Mute the account. */
        Mute("mute"),

        /** Pin the account to their profile. */
        Pin("pin")
    }

    /** The current user. */
    object CurrentUser : FoxyAccountScope()

    /** Register a new account. */
    object Register : FoxyAccountScope()

    /** The relationships between a set of accounts. */
    object Relationships : FoxyAccountScope()

    /** Update information about the current user. */
    object UpdateCurrentUser : FoxyAccountScope()

    /** Search for a specific account. */
    object Search : FoxyAccountScope()

    /** An account with a specified ID. */
    class Account(val id: String) : FoxyAccountScope()

    /** Retrieve a statistic for a specified account. */
    class AccountStatistic(val statistics: Statistics, val id: String) : FoxyAccountScope()

    /** Perform an action on a specified account. */
    class AccountAction(val action: Actions, val id: String) : FoxyAccountScope()

    /** Undo an action performed on a specified account. */
    class UndoAccountAction(val action: Actions, val id: String) : FoxyAccountScope()

    /** Add or update a note on a specified account. */
    class AccountNote(val id: String) : FoxyAccountScope()
}
