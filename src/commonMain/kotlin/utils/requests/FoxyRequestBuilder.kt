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

import io.ktor.client.request.forms.*
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
    private val params: MutableList<Parameter> = mutableListOf(),

    /** A form builder to send Form Data */
    private var formDataBuilder: FormBuilder.() -> Unit = {}
) {

    /** Returns the endpoint that was requested. */
    fun getEndpoint(): String = endpoint

    /** Returns the form builder */
    fun getFormBuilder(): FormBuilder.() -> Unit = formDataBuilder

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

    /** Sets the endpoint to work with current user's account
     *
     * @param scope The personal scope
     * @see FoxyPersonalScope
     * */
    fun personal(scope: FoxyPersonalScope) {
        endpoint = when (scope) {
            is FoxyPersonalScope.Bookmarks -> "/api/v1/bookmarks"
            is FoxyPersonalScope.Favorites -> "/api/v1/favourites"
            is FoxyPersonalScope.Mutes -> "/api/v1/mutes"
            is FoxyPersonalScope.Blocks -> "/api/v1/blocks"
            is FoxyPersonalScope.DomainBlocks -> "/api/v1/domain_blocks"
            is FoxyPersonalScope.Filters ->
                if (scope.id != null) "/api/v1/filters/${scope.id}"
                else "/api/v1/filters"
            is FoxyPersonalScope.Report -> "/api/v1/reports"
            is FoxyPersonalScope.Follow ->
                if (scope.id != null) "/api/v1/follow_requests/${scope.id}/${scope.action}"
                else "/api/v1/follow_requests"
            is FoxyPersonalScope.Endorsements -> "api/v1/endorsements"
            is FoxyPersonalScope.FeaturedTags ->
                if (scope.id != null) "/api/v1/featured_tags/${scope.id}"
                else if (scope.suggested != null) "/api/v1/featured_tags/suggestions"
                else "/api/v1/featured_tags"
            is FoxyPersonalScope.Preferences -> "/api/v1/preferences"
            is FoxyPersonalScope.Suggestions ->
                if (scope.id != null) "/api/v1/suggestions/${scope.id}"
                else "/api/v1/suggestions"
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

    /** Set the internal class FormBuilder to a private var so Foxy can use it. */
    fun formData(builder: FormBuilder.() -> Unit) {
        formDataBuilder = builder
    }


    /** Sets the endpoint to retrieve instance information.
     *
     * @param scope The instance scope endpoint to fetch.
     * @See FoxyInstanceScope
     * */
    fun instance(scope: FoxyInstanceScope) {
        endpoint = when (scope) {
            is FoxyInstanceScope.Instance -> "/api/v1/instance"
            is FoxyInstanceScope.Peers -> "/api/v1/instance/peers"
            is FoxyInstanceScope.Activity -> "/api/v1/instance/activity"
            is FoxyInstanceScope.Trends -> "/api/v1/trends"
            is FoxyInstanceScope.Directory -> "/api/v1/directory"
            is FoxyInstanceScope.CustomEmojis -> "/api/v1/custom_emojis"
        }
    }

    /** Sets the endpoint to a notification-related action.
     *
     * Note that this does required authenticated access at a user level.
     * @param scope The notification scope endpoint to fetch.
     * @see FoxyNotificationScope
     */
    fun notification(scope: FoxyNotificationScope) {
        endpoint = when (scope) {
            is FoxyNotificationScope.All -> "/api/v1/notifications"
            is FoxyNotificationScope.Notification -> "/api/v1/notifications/${scope.id}"
            is FoxyNotificationScope.Clear -> "/api/v1/notifications/clear"
            is FoxyNotificationScope.Dismiss -> "/api/v1/notifications/${scope.id}/dismiss"
            is FoxyNotificationScope.Push -> "/api/v1/push/subscription"
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

    /** Sets the endpoints to work with attachments in a status
     * @param scope The attachment scope to work with
     * @see FoxyAttachmentScope
     * */
    fun attachment(scope: FoxyAttachmentScope) {
        endpoint = when (scope) {
            is FoxyAttachmentScope.Media ->
                if (scope.id != null) "/api/v1/media/${scope.id}"
                else "api/v2/media"
            is FoxyAttachmentScope.Polls ->
                if (scope.vote != null) "/api/v1/polls/${scope.vote}/votes"
                else "/api/v1/polls/${scope.id}"
            is FoxyAttachmentScope.Scheduled ->
                if (scope.id != null) "/api/v1/scheduled_statuses/${scope.id}"
                else "/api/v1/scheduled_statuses"
        }
    }

    fun subTimelineScopes(scope: SubTimelineScopes) {
        endpoint = when (scope) {
            is SubTimelineScopes.Conversations ->
                if (scope.id != null) {
                    if (scope.read != null) "/api/v1/conversations/${scope.id}/read"
                    else "/api/v1/conversations/${scope.id}"
                } else "/api/v1/conversations"
            is SubTimelineScopes.Lists ->
                if (scope.id != null) "/api/v1/lists/${scope.id}"
                else "/api/v1/lists"
            is SubTimelineScopes.AccountsInList -> "/api/v1/lists/${scope.id}/accounts"
            is SubTimelineScopes.Markers -> "/api/v1/markers"
        }
    }

    /***
     * Sets the end point to search for content in accounts, statuses, and hashtags
     */
    fun search() {
        endpoint = "/api/v2/search"
    }

}
