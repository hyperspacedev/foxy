/*
 * FoxyStatusTests.kt
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
import models.Account
import models.Status
import utils.requests.FoxyStatusScope
import utils.responses.MastodonResponse
import utils.responses.hoistEntityOrNull
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class FoxyStatusTests {

    val statusId = "1"

    private fun assertStatus(status: Status?) {
        assertNotNull(status, "Status should not be null.")
        assertEquals(status.id, statusId, "Status IDs should match.")
    }

    /** Test that an arbitrary status can be fetched correctly. */
    @Test
    fun testRetrieveStatus() {
        runBlocking {
            val statusResponse = Foxy.request<Status> {
                method = HttpMethod.Get
                status(FoxyStatusScope.Status(statusId))
            }

            assertTrue(statusResponse !is MastodonResponse.Error, "Response should have succeeded.")
            val status = statusResponse.hoistEntityOrNull()
            assertStatus(status)

        }
    }

    /** Test that statistics for an arbitrary status can be fetched correctly. */
    @Test
    fun testRetrieveStatusStats() {
        runBlocking {
            val statusResponse = Foxy.request<Status> {
                method = HttpMethod.Get
                status(FoxyStatusScope.Status(statusId))
            }

            assertTrue(statusResponse !is MastodonResponse.Error, "Response should have succeeded.")
            val status = statusResponse.hoistEntityOrNull()
            assertStatus(status)

            val favoritedByResponse = Foxy.request<List<Account>> {
                method = HttpMethod.Get
                status(FoxyStatusScope.FavoritedBy(statusId))
            }
            assertTrue(favoritedByResponse !is MastodonResponse.Error, "Response should have succeeded.")
            val favorites = favoritedByResponse.hoistEntityOrNull()

            assertNotNull(favorites, "List should not be null.")
            assertTrue(favorites.isNotEmpty(), "List should not be empty.")
        }
    }
}