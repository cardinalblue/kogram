package com.cardinalblue.kogram

import java.nio.file.Files
import java.nio.file.Path

fun Path.isFile(): Boolean = Files.isRegularFile(this)
