/*
 * FoxyAuthBuilder.kt
 * Copyright (C) 2022 Hyperspace Developers.
 * This file is part of project Foxy.
 *
 * The Foxy project is non-violent software: you can use, redistribute, and/or modify it under the terms of the NPLv7+
 * as found in the LICENSE file in the source code root directory or at <https://git.pixie.town/thufie/npl-builder>.
 *
 * The Foxy project comes with ABSOLUTELY NO WARRANTY, to the extent permitted by applicable law.  See the
 * NPL for details.
 */

package dev.hyperspace.foxy.utils

/** A class that handles building a request to start an OAuth flow. */
class FoxyAuthBuilder(
    /** The URI of the instance that the user wants to authenticate to. */
    var instance: String,

    /** The redirect URI to navigate to when the user authorizes the app's access. */
    var redirectUri: String
) {

    private var application: FoxyApp = FoxyApp("", null)
    private var appScopes: MutableList<String> = mutableListOf()

    /** Sets the name of the application entity. */
    fun appName(name: String) {
        application = FoxyApp(name, null)
    }

    /** Sets the application entity's website. */
    fun appWebsite(uri: String) {
        application = FoxyApp(application.name, uri)
    }

    /** Defines the scopes that the app will need access to. */
    fun scopes(builder: MutableList<String>.() -> Unit) = builder(appScopes)

    /** Returns the FoxyApp object for the application entity. */
    fun getApp(): FoxyApp = application

    /** Returns the list of scopes. */
    fun getScopes(): List<String> = appScopes.toList()

}