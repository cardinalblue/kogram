package com.cardinalblue.kogram.core

import java.nio.file.Files
import java.nio.file.Path

fun Path.isFile(): Boolean = Files.isRegularFile(this)
