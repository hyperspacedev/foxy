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

import dev.hyperspace.foxy.Foxy
import dev.hyperspace.foxy.utils.FoxyApp
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class FoxyAuthTests {

    /** Test that the OAuth starting point succeeds in creating an application entity. */
    @Test
    fun testStartOAuthFlowSucceeds() {
        val foxyApp = FoxyApp("TestApplication", null)
        runBlocking {
            val result = Foxy.startOAuthFlow("mastodon.social", foxyApp, "urn:ietf:wg:oauth:2.0:oob")
            assertNotNull(result)
            assertTrue(result.isNotBlank())
        }
    }

    /** Test that the OAuth flow works as intended using client credentials. */
    @Test
    fun testOAuthFlow() {
        runBlocking {
            val result = Foxy.startOAuthFlow {
                instance = "mastodon.social"
                redirectUri = "urn:ietf:wg:oauth:2.0:oob"

                appName("Foxy Unit Testing")
                appWebsite("https://github.com/hyperspacedev/foxy")

                scopes {
                    add("read")
                    add("write")
                    add("follow")
                    add("push")
                }
            }
            assertNotNull(result)
            assertTrue(result.isNotBlank())
            Foxy.finishOAuthFlow(Foxy.AuthGrantType.ClientCredentials)
        }
    }
}