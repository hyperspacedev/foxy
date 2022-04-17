/*
 * ValidatedSession.kt
 * Copyright (C) 2022 Hyperspace Developers.
 * This file is part of project Foxy.
 *
 * The Foxy project is non-violent software: you can use, redistribute, and/or modify it under the terms of the NPLv7+
 * as found in the LICENSE file in the source code root directory or at <https://git.pixie.town/thufie/npl-builder>.
 *
 * The Foxy project comes with ABSOLUTELY NO WARRANTY, to the extent permitted by applicable law.  See the
 * NPL for details.
 */

package security

import kotlinx.datetime.toInstant
import kotlin.experimental.xor

/** A class that represents a validated session with an integrity timestamp. */
class ValidatedSession(val token: String, val timestamp: String, var integrity: String = "") {

    /** Set the integrity stamp of this session, if no integrity stamp has been generated.
     *
     * @param lifespan The lifespan of the integrity stamp in seconds.
     */
    fun setIntegrityStamp(lifespan: Long) {
        if (integrity.isNotEmpty())
            return
        val integrityStamp = makeIntegrityStamp(lifespan)
        integrity = integrityStamp.hashedToMurmur()
    }

    /** Returns an integrity stamp to be used with hashing.
     *
     * @param lifespan The lifespan of the integrity stamp in seconds.
     */
    private fun makeIntegrityStamp(lifespan: Long): String {

        // XOR all the bytes in the token as a component.
        val orderedToken = token
            .toMutableList()
            .map { it.code.toByte() }
            .reduce { first, second -> first.xor(second) }

        // Get the timestamp's epochSeconds as an integer.
        val dateTimestamped = timestamp.toInstant()
        val orderedDate = dateTimestamped.epochSeconds.toInt()

        // Get the sum of all of these elements.
        val sum = lifespan + orderedDate + orderedToken.toInt()

        // Create the checksum string and the resulting hash.
        return "$orderedToken:$orderedDate:$lifespan:$sum"
    }

    /** Validates the integrity timestamp by checking the hashed outputs and the checksums, as well as whether the
     * current date specified is within the lifespan of the token.
     *
     * @param expectedLifespan The expected lifespan of the integrity stamp in seconds.
     * @param currentDate The ISO-8601 date string representing the current date.
     * @return Whether the validation succeeded. Returns false if the hashes don't match, the checksum fails, or if the
     * current date is well outside the lifespan of the integrity stamp.
     */
    fun validateIntegrity(expectedLifespan: Long, currentDate: String): Boolean {
        // Create a replica timestamp with the current parameters.
        val newStamp = makeIntegrityStamp(expectedLifespan)

        // Test that hashes of the replica and the original align.
        if (integrity != newStamp.hashedToMurmur())
            return false

        // Test that the checksum is correct.
        val components = newStamp
            .split(":")
            .map { it.toInt() }
        if (components.last() != components.dropLast(1).sum())
            return false

        // Test that the current date is within the lifespan of the token.
        val duration = currentDate.toInstant() - timestamp.toInstant()
        if (duration.inWholeSeconds >= expectedLifespan)
            return false

        return true
    }
}