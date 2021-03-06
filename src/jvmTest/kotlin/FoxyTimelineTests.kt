/*
 * FoxyTimelineTests.kt
 * Copyright (C) 2022 Hyperspace Developers.
 * This file is part of project Foxy.
 *
 * The Foxy project is non-violent software: you can use, redistribute, and/or modify it under the terms of the NPLv7+
 * as found in the LICENSE file in the source code root directory or at <https://git.pixie.town/thufie/npl-builder>.
 *
 * The Foxy project comes with ABSOLUTELY NO WARRANTY, to the extent permitted by applicable law.  See the
 * NPL for details.
 */

import dev.hyperspace.foxy.Foxy
import dev.hyperspace.foxy.utils.aliases.Timeline
import dev.hyperspace.foxy.utils.requests.FoxyTimelineScope
import dev.hyperspace.foxy.utils.responses.MastodonResponse
import dev.hyperspace.foxy.utils.responses.hoistEntityOrNull
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/** A class that contains tests for requesting timelines. */
class FoxyTimelineTests {

    /** Assert that the timeline isn't empty or nulled out. */
    private fun assertTimeline(timeline: Timeline?) {
        assertNotNull(timeline, "Timeline should not be nullable.")
        assertTrue(timeline.isNotEmpty(), "Timeline should not be empty.")
    }

    /** Test that the client can successfully fetch the public timeline. */
    @Test
    fun testFetchTimeline() {
        runBlocking {
            Foxy.startOAuthFlow {
                instance = "fosstodon.org"
                appName("Foxy Unit Testing")
                appWebsite("https://hyperspace.marquiskurt.net")

                scopes {
                    add("read")
                }
            }
            Foxy.finishOAuthFlow(Foxy.AuthGrantType.ClientCredentials)

            val timelineResponse = Foxy.request<Timeline> {
                method = HttpMethod.Get

                timeline(FoxyTimelineScope.Network)
                parameter("local", true)
            }

            if (timelineResponse is MastodonResponse.Error)
                println(timelineResponse.error.error)

            assertTrue(timelineResponse !is MastodonResponse.Error, "Response should have succeeded.")
            val timeline = timelineResponse.hoistEntityOrNull()
            assertTimeline(timeline)
        }
    }

    /** Test that the client can fetch tagged posts. */
    @Test
    fun testFetchTaggedPosts() {
        runBlocking {
            Foxy.startOAuthFlow {
                instance = "mastodon.social"

                appName("Foxy Unit Testing")

                scopes {
                    add("read")
                }
            }
            Foxy.finishOAuthFlow(Foxy.AuthGrantType.ClientCredentials)

            val timelineResponse = Foxy.request<Timeline> {
                method = HttpMethod.Get
                timeline(FoxyTimelineScope.Tagged("cat"))
            }

            assertTrue(timelineResponse !is MastodonResponse.Error, "Response should have succeeded.")
            val timeline = timelineResponse.hoistEntityOrNull()
            assertTimeline(timeline)
        }
    }
}