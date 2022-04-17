/*
 * FoxyRequestBuilder.kt
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

import io.ktor.http.*
import utils.aliases.Parameter
import utils.annotations.Dangerous

/** A builder class that handles building requests to Mastodon. */
class FoxyRequestBuilder(

    /** The HTTP method to make the request with. */
    var method: HttpMethod = HttpMethod.Get,

    /** The specified endpoint to make a request to. */
    private var endpoint: String = "",

    /** A mutable list of the request parameters. */
    private val params: MutableList<Parameter> = mutableListOf()
) {

    /** Returns the endpoint that was requested. */
    fun getEndpoint(): String = endpoint

    /** Returns a list of the parameters to be passed into the client's request. */
    fun getParams(): List<Parameter> = params.toList()

    /** Sets the endpoint to fetch account information.
     *
     * @param scope The user account to fetch in question.
     * @see FoxyAccountScope
     */
    fun account(scope: FoxyAccountScope) {
        endpoint = when (scope) {
            is FoxyAccountScope.CurrentUser -> "/api/v1/verify_credentials"
            is FoxyAccountScope.UpdateCurrentUser -> "/api/v1/update_credentials"
            is FoxyAccountScope.Register -> "/api/v1/accounts"
            is FoxyAccountScope.Relationships -> "/api/v1/accounts/relationships"
            is FoxyAccountScope.Search -> "/api/v1/accounts/search"
            is FoxyAccountScope.Account -> "/api/v1/accounts/${scope.id}"
            is FoxyAccountScope.AccountStatistic -> "/api/v1/accounts/${scope.id}/${scope.statistics.endpointName}"
            is FoxyAccountScope.AccountAction -> "/api/v1/accounts/${scope.id}/${scope.action.endpointName}"
            is FoxyAccountScope.UndoAccountAction -> "/api/v1/accounts/${scope.id}/un${scope.action.endpointName}"
            is FoxyAccountScope.AccountNote -> "/api/v1/accounts/${scope.id}/note"
        }
    }

    /** Sets the endpoint to a custom string.
     *
     * This should only be used if the existing endpoint methods do not provide the endpoint requested.
     */
    @Dangerous
    fun customEndpoint(path: String) {
        endpoint = path
    }

    /** Sets the endpoint to retrieve instance information.
     *
     * @param scope The instance scope endpoint to fetch.
     * @See FoxyInstanceScope
     * */
    fun instance(scope: FoxyInstanceScope) {
        endpoint = "/api/v1/instance" + when (scope) {
            is FoxyInstanceScope.Instance -> ""
            is FoxyInstanceScope.Peers -> "/peers"
            is FoxyInstanceScope.Activity -> "/activity"
        }
    }

    /** Sets the endpoint to a notification-related action.
     *
     * Note that this does required authenticated access at a user level.
     * @param scope The notification scope endpoint to fetch.
     * @see FoxyNotificationScope
     */
    fun notification(scope: FoxyNotificationScope) {
        endpoint = "/api/v1/notifications" + when (scope) {
            is FoxyNotificationScope.All -> ""
            is FoxyNotificationScope.Notification -> "/${scope.id}"
            is FoxyNotificationScope.Clear -> "/clear"
            is FoxyNotificationScope.Dismiss -> "/${scope.id}/dismiss"
        }
    }

    /** Adds a parameter to the list of request parameters.
     *
     * @param key The parameter's key.
     * @param value The value of the parameter.
     */
    fun parameter(key: String, value: Any) {
        params.add(Pair(key, value))
    }

    /** Adds a list of parameters to the request parameters.
     *
     * @param builder A receiver closure that builds a list of parameters.
     */
    fun parameters(builder: MutableList<Parameter>.() -> Unit) {
        val newParameters = mutableListOf<Parameter>()
        builder(newParameters)
        params.addAll(newParameters.toList())
    }

    /** Sets the endpoint to a status-related action.
     *
     * @param scope The status scope endpoint to set.
     * @see FoxyStatusScope
     */
    fun status(scope: FoxyStatusScope) {
        endpoint = "/api/v1/statuses" + when (scope) {
            is FoxyStatusScope.Editor -> ""
            is FoxyStatusScope.Status -> "/${scope.id}"
            is FoxyStatusScope.Context -> "/${scope.statusId}/context"
            is FoxyStatusScope.FavoritedBy -> "/${scope.statusId}/favourited_by"
            is FoxyStatusScope.RebloggedBy -> "/${scope.statusId}/reblogged_by"
            is FoxyStatusScope.Action -> "/${scope.id}/${scope.action.endpointName}"
            is FoxyStatusScope.UndoAction -> "/${scope.id}/un${scope.action.endpointName}"

        }
    }

    /** Sets the endpoint to fetch a timeline.
     *
     * @param scope The timeline to fetch
     * @see FoxyTimelineScope
     * */
    fun timeline(scope: FoxyTimelineScope) {
        endpoint = when (scope) {
            is FoxyTimelineScope.Home -> "/api/v1/timelines/home"
            is FoxyTimelineScope.Network -> "/api/v1/timelines/public"
            is FoxyTimelineScope.Conversations -> "/api/v1/conversations"
            is FoxyTimelineScope.List -> "/api/v1/timelines/list/${scope.id}"
            is FoxyTimelineScope.Tagged -> "/api/v1/timelines/tag/${scope.tag}"
        }
    }

}
