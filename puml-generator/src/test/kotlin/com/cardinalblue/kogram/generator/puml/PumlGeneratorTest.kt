package com.cardinalblue.kogram.generator.puml

import com.cardinalblue.kogram.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PumlGeneratorTest{

    @Test
    internal fun `generate plantUML with packageName and class`() {
        val expectResult = """
            @startuml

            package "com.cardinalblue.kogram" {
                class Sample
            }

            @enduml

        """.trimIndent()

        val fileDependency = FileDependency(packageName = "com.cardinalblue.kogram").also {
            it.classes.add(KoClass("Sample", listOf(), mutableListOf()))
        }
        val generator = PumlGenerator(fileDependency)
        val actualResult = generator.generate()

        assertEquals(expectResult, actualResult)
    }

    @Test
    internal fun `generate plantUML with class's function and it's properties`() {
        val expectResult = """
            @startuml

            package "com.cardinalblue.kogram" {
                class Sample {
                    + func1(): Int
                    + property1: String
                    + property2: Long
                }
            }

            @enduml

        """.trimIndent()


        val fileDependency = FileDependency(packageName = "com.cardinalblue.kogram").also {
            val property1 = KoProperty(name = "property1", type = KoType("String"))
            val property2 = KoProperty(name = "property2", type = KoType("Long"))
            val func1 = KoFunction(name = "func1", returnType = KoType("Int"))
            it.classes.add(KoClass(name = "Sample",
                    properties = listOf(property1, property2),
                    functions = mutableListOf(func1)))
        }
        val generator = PumlGenerator(fileDependency)
        val actualResult = generator.generate()

        assertEquals(expectResult, actualResult)
    }
}