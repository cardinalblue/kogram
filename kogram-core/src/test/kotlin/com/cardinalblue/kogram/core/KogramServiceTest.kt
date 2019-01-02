package com.cardinalblue.kogram.core

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class KogramServiceTest{

    @Test
    internal fun `KogramService resolve dependency should get correct dependency`() {
        val path = Paths.get(resource("Default.kt"))
        val service = KogramService()
        val dependency = service.resolveDependency(path)

        assertEquals(listOf("Default"), dependency.classes.map { it.name })
    }
}