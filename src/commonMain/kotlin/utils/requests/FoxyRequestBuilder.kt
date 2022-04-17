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

    /** Sets the endpoint to a custom string.
     *
     * This should only be used if the existing endpoint methods do not provide the endpoint requested.
     */
    @Dangerous
    fun customEndpoint(path: String) {
        endpoint = path
    }

    /** Sets the endpoint to fetch account information.
     *
     * @param scope The user account to fetch in question.
     * @see FoxyAccountScope
     */
    fun getAccount(scope: FoxyAccountScope) {
        endpoint = when (scope) {
            is FoxyAccountScope.CurrentUser -> "/api/v1/verify_credentials"
            is FoxyAccountScope.Account -> "/api/v1/accounts/${scope.id}"
        }
    }

    /** Sets the endpoint to retrieve the instance information. */

    fun getInstance() {
        endpoint = "/api/v1/instance"
    }

    /** Sets the endpoint to fetch a timeline.
     *
     * @param scope The timeline to fetch
     * @see FoxyTimelineScope
     * */
    fun getTimeline(scope: FoxyTimelineScope) {
        endpoint = when (scope) {
            is FoxyTimelineScope.Home -> "/api/v1/timelines/home"
            is FoxyTimelineScope.Network -> "/api/v1/timelines/public"
            is FoxyTimelineScope.Conversations -> "/api/v1/conversations"
            is FoxyTimelineScope.List -> "/api/v1/timelines/list/${scope.id}"
            is FoxyTimelineScope.Tagged -> "/api/v1/timelines/tag/${scope.tag}"
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

    /** Returns the endpoint that was requested. */
    fun getEndpoint(): String = endpoint

    /** Returns a list of the parameters to be passed into the client's request. */
    fun getParams(): List<Parameter> = params.toList()

}
