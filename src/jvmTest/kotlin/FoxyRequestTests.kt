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

import dev.hyperspace.foxy.Foxy
import dev.hyperspace.foxy.models.Account
import dev.hyperspace.foxy.models.Activity
import dev.hyperspace.foxy.models.Instance
import dev.hyperspace.foxy.models.Status
import dev.hyperspace.foxy.utils.aliases.Timeline
import dev.hyperspace.foxy.utils.requests.FoxyAccountScope
import dev.hyperspace.foxy.utils.requests.FoxyInstanceScope
import dev.hyperspace.foxy.utils.requests.FoxyTimelineScope
import dev.hyperspace.foxy.utils.responses.MastodonResponse
import dev.hyperspace.foxy.utils.responses.hoistEntityOrNull
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/** A class containing tests for handling requests. */
class FoxyRequestTests {


    /** Test that the instance information can be fetched correctly. */
    @Test
    fun testFetchInstance() {
        runBlocking {
            val instanceResponse = Foxy.request<Instance> {
                method = HttpMethod.Get
                instance(FoxyInstanceScope.Instance)
            }

            assertTrue(instanceResponse !is MastodonResponse.Error, "Response should have succeeded.")
            val instance = instanceResponse.hoistEntityOrNull()

            assertNotNull(instance, "Entity response should've been hoisted correctly.")
            assertTrue(instance.uri == "mastodon.social", "Instance URIs don't match.")
        }
    }

    @Test
    fun testFetchActivity() {
        runBlocking {
            val activityResponse = Foxy.request<List<Activity>> {
                method = HttpMethod.Get
                instance(FoxyInstanceScope.Activity)
            }

            assertTrue(activityResponse !is MastodonResponse.Error, "Response should have succeeded.")
            val activities = activityResponse.hoistEntityOrNull()

            assertNotNull(activities, "Entity response should've been hoisted correctly.")
            assertTrue(activities.isNotEmpty(), "Activity list should not be empty..")
        }
    }

    /** Test that an account can be fetched correctly. */
    @Test
    fun testFetchAccount() {
        runBlocking {
            val accountResponse = Foxy.request<Account> {
                method = HttpMethod.Get
                account(FoxyAccountScope.Account("1"))
            }

            assertTrue(accountResponse !is MastodonResponse.Error, "Response should have succeeded.")
            val account = accountResponse.hoistEntityOrNull()

            assertNotNull(account, "Entity response should've been hoisted correctly.")
            assertTrue(account.id == "1", "Account IDs don't match.")
        }
    }

    /** Test that an account statistic can be fetched correctly. */
    @Test
    fun testFetchAccountStats() {
        runBlocking {
            val accountResponse = Foxy.request<List<Status>> {
                method = HttpMethod.Get
                account(FoxyAccountScope.AccountStatistic(FoxyAccountScope.Statistics.Statuses, "1"))
            }

            assertTrue(accountResponse !is MastodonResponse.Error, "Response should have succeeded.")
            val statuses = accountResponse.hoistEntityOrNull()

            assertNotNull(statuses, "Entity response should've been hoisted correctly.")
            assertTrue(statuses.isNotEmpty(), "List of statuses should not be empty.")
        }
    }

    /** Test that the request builder correctly returns an error when unauthenticated. */
    @Test
    fun testUnauthenticatedResponseFails() {
        runBlocking {
            val timelineResponse = Foxy.request<Timeline> {
                method = HttpMethod.Get

                timeline(FoxyTimelineScope.Home)
                parameter("local", true)
            }

            assertTrue(timelineResponse is MastodonResponse.Error, "Response should have failed.")
            val mastErr = timelineResponse.error

            assertNotNull(mastErr, "Error should not be null.")
            assertTrue(mastErr.error.isNotBlank(), "Error mapping should have succeeded.")
        }
    }
}