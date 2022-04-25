/*
 * FoxyAttachmentScope.kt
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

/** Represents different attachments a user can add to their statuses such as media, polls or schedule a status. */
sealed class FoxyAttachmentScope {

    /** Upload a media to a post if id is null otherwise, get the updated media or update the medial on
     * GET and PUT respectively
     */
    class Media(val id: String?) : FoxyAttachmentScope()

    /** View a poll when vote is null otherwise vote on a poll on POST */
    class Polls(val id: String, val vote: String? = "votes") : FoxyAttachmentScope()

    /** View all scheduled statuses when id is null
     *
     * Otherwise, view a single scheduled status, schedule a status or delete a scheduled a status
     * on GET, PUT or DELETE respectively
     * */
    class Scheduled(val id: String? = null) : FoxyAttachmentScope()
}