/*
 * tokenStorage.kt
 * Copyright (C) 2022 Hyperspace Developers.
 * This file is part of project Foxy.
 *
 * The Foxy project is non-violent software: you can use, redistribute, and/or modify it under the terms of the NPLv7+
 * as found in the LICENSE file in the source code root directory or at <https://git.pixie.town/thufie/npl-builder>.
 *
 * The Foxy project comes with ABSOLUTELY NO WARRANTY, to the extent permitted by applicable law.  See the
 * NPL for details.
 */

package dev.hyperspace.foxy.utils

import java.io.File
import java.nio.file.Files
import java.nio.file.Path


actual fun tokenStorageWrite(data: String) {

    val file = File(getAbsolutePath())

    file.createNewFile()

    file.bufferedWriter().use { out ->
        out.write(
            "KEEP THIS TOKEN A SECRET! DO NO SHARE IT!\n" +
                    "---------------------------------\n" +
                    data
        )
    }
}

actual fun tokenStorageGet(): String {

    val fileContent = Files.readAllLines(Path.of(getAbsolutePath()))

    return fileContent[2]
}

fun getAbsolutePath(): String {

    val userDirectory: String = System.getProperty("user.home")

    return userDirectory + File.separator + ".foxytoken"

}