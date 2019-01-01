package com.cardinalblue.kogram.generator.puml

import java.lang.StringBuilder

fun StringBuilder.appendTab(tabCount: Int = 1): StringBuilder {
    for(i in 0 until tabCount){
        append("    ")
    }
    return this
}