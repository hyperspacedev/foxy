/*
 * MurmurHashing.kt
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

import com.goncalossilva.murmurhash.MurmurHash3

/** Returns a hash of the string using the MurmurHash3 function. */
fun String.hashedToMurmur(): String = MurmurHash3()
    .hash32x86(this.encodeToByteArray())
    .toString()