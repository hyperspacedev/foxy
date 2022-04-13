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

import io.ktor.client.statement.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.*
import kotlinx.coroutines.runBlocking
import kotlin.test.assertNotNull

class FoxyAuthTests {
    @Test
    fun testRegistration(){
        val foxy = Foxy()
        runBlocking{
            val result = foxy.makeRequest(url = "mastodon.social", path = "api/v1/timelines/public")
            assertNotNull(result.bodyAsText())
            assertEquals(200, result.status.value)
        }
        foxy.closeClient()
    }
}