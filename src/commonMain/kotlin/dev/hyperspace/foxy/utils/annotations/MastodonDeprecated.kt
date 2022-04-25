/*
 * MastodonDeprecated.kt
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

import kotlin.annotation.AnnotationTarget.*

/** An annotation class that indicates a particular method, endpoint, or property has been deprecated in Mastodon. */
@Target(CLASS, FUNCTION, PROPERTY, ANNOTATION_CLASS, CONSTRUCTOR, PROPERTY_SETTER, PROPERTY_GETTER, TYPEALIAS)
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
@RequiresOptIn(
    "This declaration calls code deprecated in Mastodon and should be marked with 'MastodonDeprecated' or"
            + "'@Option(MastodonDeprecated::class).'",
    level = RequiresOptIn.Level.ERROR
)
annotation class MastodonDeprecated(

    /** The version that this feature was introduced into Mastodon. */
    val introduced: String,

    /** The version that this feature was deprecated. */
    val deprecated: String,

    /** The expression or call to replace the feature with. */
    val replaceWith: ReplaceWith = ReplaceWith(""),

    /** The severity of the deprecation. */
    val level: DeprecationLevel = DeprecationLevel.WARNING
)
