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

/** A builder class that handles building requests to Mastodon. */
class FoxyRequestBuilder(

    /** The HTTP method to make the request with. */
    var method: HttpMethod = HttpMethod.Get,

    /** The specified endpoint to make a request to. */
    var endpoint: String = "",

    /** A mutable list of the request parameters. */
    private val params: MutableList<Pair<String, Any>> = mutableListOf()
) {

    /** Adds a parameter to the list of request parameters.
     *
     * @param key The parameter's key.
     * @param value The value of the parameter.
     */
    fun parameter(key: String, value: Any) {
        params.add(Pair(key, value))
    }

    /** Returns a list of the parameters to be passed into the client's request. */
    fun getParams(): List<Pair<String, Any>> = params.toList()

}