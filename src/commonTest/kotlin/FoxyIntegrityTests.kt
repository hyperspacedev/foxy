/*
 * FoxyIntegrityTests.kt
 * Copyright (C) 2022 Hyperspace Developers.
 * This file is part of project Foxy.
 *
 * The Foxy project is non-violent software: you can use, redistribute, and/or modify it under the terms of the NPLv7+
 * as found in the LICENSE file in the source code root directory or at <https://git.pixie.town/thufie/npl-builder>.
 *
 * The Foxy project comes with ABSOLUTELY NO WARRANTY, to the extent permitted by applicable law.  See the
 * NPL for details.
 */

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/** A class hosting all the test for validating integrity stamps of authenticated sessions. */
class FoxyIntegrityTests {

    private val dummyToken: String = "42cf00-09ba110r-0f2o3o1b9a5r"

    /** Test that the integrity stamp creation and validation works. */
    @Test
    fun testChecksum() {
        val now = "2010-06-01T22:19:44.475Z"
        val validatedSession = ValidatedSession(dummyToken, now)
        validatedSession.setIntegrityStamp(86400)
        assertTrue(
            validatedSession.validateIntegrity(86400, now),
            "Validation integrity failed."
        )
    }

    /** Test that the integrity stamp check fails when fast-forwarding to the future. */
    @Test
    fun testChecksumFailureToValidate() {
        val now = "2010-06-01T22:19:44.475Z"
        val future = "2010-06-15T22:20:11.411Z"

        val validatedSession = ValidatedSession(dummyToken, now)
        validatedSession.setIntegrityStamp(86400)
        val originalIntegrity = validatedSession.integrity

        val futureSession = ValidatedSession(dummyToken, future, integrity = originalIntegrity)
        assertFalse(
            futureSession.validateIntegrity(86400, future),
            "Now and future sessions shouldn't match."
        )

    }

    /** Test that the integrity stamp check succeeds when the current date is in the stamp's lifespan. */
    @Test
    fun testChecksumValidInPeriod() {
        val now = "2010-06-01T22:19:44.475Z"
        val future = "2010-06-01T22:20:11.411Z"

        val validatedSession = ValidatedSession(dummyToken, now)
        validatedSession.setIntegrityStamp(86400 * 14)

        val futureSession = ValidatedSession(dummyToken, now, integrity = validatedSession.integrity)
        assertTrue(
            futureSession.validateIntegrity(86400 * 14, future),
            "Future within token's lifespan."
        )
    }
}