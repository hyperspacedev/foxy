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

import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import models.Instance
import utils.*
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class FoxyRequestTests {

    @Test
    fun testFetchInstance() {
        runBlocking {
            val instanceResponse = Foxy.request<Instance> {
                method = HttpMethod.Get
                info(FoxyRequestBuilder.InformationScope.Instance)
            }

            assertTrue(instanceResponse !is MastodonResponse.Error)
            val instance = instanceResponse.hoistEntityOrNull()

            assertNotNull(instance)
            assertTrue(instance.uri == "mastodon.social")
        }
    }

    @Test
    fun testUnauthenticatedResponseFails() {
        // FIXME: Requires access token logic to be implemented so that we can make this authenticated request
        val app = FoxyApp("Foxy Unit Testing", "https://hyperspace.marquiskurt.net")
        runBlocking {
            val timelineResponse = Foxy.request<Timeline> {
                method = HttpMethod.Get

                timeline(FoxyRequestBuilder.TimelineScope.Network)
                parameter("local", true)
            }

            assertTrue(timelineResponse is MastodonResponse.Error)
            val mastErr = timelineResponse.error

            assertNotNull(mastErr)
            assertTrue(mastErr.error.isNotBlank())
        }
    }

    @Test
    fun testFetchTimeline() {
        // FIXME: Requires access token logic to be implemented so that we can make this authenticated request
        val app = FoxyApp("Foxy Unit Testing", "https://hyperspace.marquiskurt.net")
        runBlocking {
            Foxy.startOAuthFlow("mastodon.social", app, "urn:ietf:wg:oauth:2.0:oob")
            Foxy.finishOAuthFlow(Foxy.AuthGrantType.ClientCredential)

            val timelineResponse = Foxy.request<Timeline> {
                method = HttpMethod.Get

                timeline(FoxyRequestBuilder.TimelineScope.Network)
                parameter("local", true)
            }

            assertTrue(timelineResponse !is MastodonResponse.Error)
            val timeline = timelineResponse.hoistEntityOrNull()

            assertNotNull(timeline)
            assertTrue(timeline.isNotEmpty())
        }
    }

}