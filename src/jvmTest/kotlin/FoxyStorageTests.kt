/*
 * FoxyStorageTests.kt
 * Copyright (C) 2022 Hyperspace Developers.
 * This file is part of project Foxy.
 *
 * The Foxy project is non-violent software: you can use, redistribute, and/or modify it under the terms of the NPLv7+
 * as found in the LICENSE file in the source code root directory or at <https://git.pixie.town/thufie/npl-builder>.
 *
 * The Foxy project comes with ABSOLUTELY NO WARRANTY, to the extent permitted by applicable law.  See the
 * NPL for details.
 */

import dev.hyperspace.foxy.security.ValidatedSession
import dev.hyperspace.foxy.utils.tokenStorageGet
import dev.hyperspace.foxy.utils.tokenStorageWrite
import kotlin.test.Test

class FoxyStorageTests {

    private val dummyToken: String = "42cf00-09ba110r-0f2o3o1b9a5r"
    private fun getTimes(): Pair<String, String> = Pair("2010-06-01T22:19:44.475Z", "2010-06-15T22:20:11.411Z")

    @Test
    fun testStorageWrite() {
        val (now, _) = getTimes()
        val validatedSession = ValidatedSession(dummyToken, now)
        validatedSession.setIntegrityStamp(86400)

        tokenStorageWrite(validatedSession.token + ";" + validatedSession.timestamp + ";" + validatedSession.integrity)
    }

    @Test
    fun testStorageRead() {
        println(tokenStorageGet())
    }
}