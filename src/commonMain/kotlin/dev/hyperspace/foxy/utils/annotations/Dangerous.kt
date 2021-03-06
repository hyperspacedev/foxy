/*
 * Dangerous.kt
 * Copyright (C) 2022 Hyperspace Developers.
 * This file is part of project Foxy.
 *
 * The Foxy project is non-violent software: you can use, redistribute, and/or modify it under the terms of the NPLv7+
 * as found in the LICENSE file in the source code root directory or at <https://git.pixie.town/thufie/npl-builder>.
 *
 * The Foxy project comes with ABSOLUTELY NO WARRANTY, to the extent permitted by applicable law.  See the
 * NPL for details.
 */

package dev.hyperspace.foxy.utils.annotations

/** An annotation that indicates that the developer must explicitly acknowledge the annotated member in question carries
 * unintended side effects.
 */
@RequiresOptIn(
    "This declaration may have unintended side effects and should be marked with 'Dangerous' or"
            + "'@Option(Dangerous::class).'",
    level = RequiresOptIn.Level.ERROR
)
annotation class Dangerous()
