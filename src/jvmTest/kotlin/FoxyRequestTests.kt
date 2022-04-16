/*
 * FoxyRequestTests.kt
 * Copyright (C) 2022 Hyperspace Developers.
 * This file is part of project Foxy.
 *
 * The Foxy project is non-violent software: you can use, redistribute, and/or modify it under the terms of the NPLv7+
 * as found in the LICENSE file in the source code root directory or at <https://git.pixie.town/thufie/npl-builder>.
 *
 * The Foxy project comes with ABSOLUTELY NO WARRANTY, to the extent permitted by applicable law.  See the
 * NPL for details.
 */

import io.ktor.client.call.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import models.Instance
import models.Status
import utils.FoxyApp
import utils.FoxyRequestBuilder
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class FoxyRequestTests {

    @Test
    fun testFetchInstance() {
        runBlocking {
            val instance = Foxy.request {
                method = HttpMethod.Get
                info(FoxyRequestBuilder.InformationScope.Instance)
            }.body<Instance>()

            assertNotNull(instance)
        }
    }

    @Test
    fun testFetchTimeline() {
        // FIXME: Requires access token logic to be implemented so that we can make this authenticated request
        var app = FoxyApp("Foxy Unit Testing", "https://hyperspace.marquiskurt.net")
        runBlocking {
            Foxy.startOAuthFlow("mastodon.social", app, "urn:ietf:wg:oauth:2.0:oob")
            Foxy.finishOAuthFlow(Foxy.AuthGrantType.ClientCredential)

            val timeline = Foxy.request {
                method = HttpMethod.Get
                timeline(FoxyRequestBuilder.TimelineScope.Network)
            }.body<List<Status>>()

            assertNotNull(timeline)
            assertTrue(timeline.isNotEmpty())
        }
    }

}