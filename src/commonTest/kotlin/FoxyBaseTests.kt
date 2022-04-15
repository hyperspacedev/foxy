/*
 * FoxyBaseTests.kt
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
import kotlin.test.assertEquals

/** A class hosting all the basic tests for the Foxy library. */
class FoxyBaseTests {

    /** A simple test to expect "Hello, world!" to be returned from the greeter. */
    @Test
    fun testHelloWorld() {
        assertEquals("Hello, World!", greet("World"))
    }
}