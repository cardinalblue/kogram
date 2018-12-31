package com.cardinalblue.kogram.core

import java.net.URI

internal object Resources

fun resource(name: String): URI {
    val explicitName = if (name.startsWith("/")) name else "/$name"
    val resource = Resources::class.java.getResource(explicitName)
    requireNotNull(resource) { "Make sure the resource '$name' exists!" }
    return resource.toURI()
}