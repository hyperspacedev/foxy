/*
 * FoxyAuthTests.kt
 * Copyright (C) 2022 Hyperspace Developers.
 * This file is part of project Foxy.
 *
 * The Foxy project is non-violent software: you can use, redistribute, and/or modify it under the terms of the NPLv7+
 * as found in the LICENSE file in the source code root directory or at <https://git.pixie.town/thufie/npl-builder>.
 *
 * The Foxy project comes with ABSOLUTELY NO WARRANTY, to the extent permitted by applicable law.  See the
 * NPL for details.
 */

import kotlinx.coroutines.runBlocking
import kotlin.test.*

class FoxyAuthTests {

    private lateinit var client: Foxy

    @BeforeTest
    fun setup() {
        client = Foxy()
        client.domain = "mastodon.social"
    }

    @AfterTest
    fun teardown() {
        client.closeClient()
    }

    @Test
    fun testStartOAuthFlowSucceeds() {
        val foxyApp = FoxyApp("TestApplication", null)
        runBlocking {
            val result = client.startOAuthFlow(foxyApp, "urn:ietf:wg:oauth:2.0:oob")
            assertNotNull(result)
            assertTrue(result.isNotBlank())
        }
    }

    @Test
    fun testOAuthFlow() {
        val foxyApp = FoxyApp("TestApplication", null)
        runBlocking {
            val result = client.startOAuthFlow(foxyApp, "urn:ietf:wg:oauth:2.0:oob")
            assertNotNull(result)
            assertTrue(result.isNotBlank())
            client.finishOAuthFlow(Foxy.AuthGrantType.ClientCredential, "")
        }
    }
}