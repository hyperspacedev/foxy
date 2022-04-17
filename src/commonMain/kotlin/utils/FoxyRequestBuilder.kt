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

package utils

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

    /** An enumeration class that represents the various kinds of timelines that can be requested. */
    enum class TimelineScope(val path: String) {

        /** The user's home timeline. */
        Home("/api/v1/timelines/home"),

        /** The local timeline of the instance or the public timeline.
         *
         * Note that when this endpoint is chosen, to make a request for a local timeline, the local parameter must be
         * added to the request: `parameter("local", true)`.
         */
        Network("/api/v1/timelines/public"),

        /** The user's direct messages. */
        Messages("/api/v1/conversations")
    }

    enum class InformationScope(val path: String) {
        Instance("/api/v1/instance"),
        User("/api/v1/verify_credentials")
    }

    /** Sets the endpoint to a custom string.
     *
     * This should only be used if the existing endpoint methods do not provide the endpoint requested.
     */
    @Dangerous
    fun customEndpoint(path: String) {
        endpoint = path
    }

    fun info(infoScope: InformationScope) {
        endpoint = infoScope.path
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

    /** Sets the endpoint to fetch a timeline.
     *
     * @param scope The timeline to fetch
     * @see TimelineScope
     * */
    fun timeline(scope: TimelineScope) {
        endpoint = scope.path
    }

    /** Returns the endpoint that was requested. */
    fun getEndpoint(): String = endpoint

    /** Returns a list of the parameters to be passed into the client's request. */
    fun getParams(): List<Parameter> = params.toList()

}
