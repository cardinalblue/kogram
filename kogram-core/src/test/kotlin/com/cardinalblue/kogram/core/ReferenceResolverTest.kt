package com.cardinalblue.kogram.core

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ReferenceResolverTest{

    @Test
    internal fun `reference resolver should find the property reference to SampleClass2`() {
        val classProperty = KoProperty("sampleClass2", KoType("SampleClass2"))
        val sampleClass = KoClass(name = "SampleClass", functions = mutableListOf(), properties = listOf(classProperty))
        val sampleClass2 = KoClass(name = "SampleClass2", functions = mutableListOf(), properties = listOf())
        val dependency = FileDependency("com.cardinalblue.kogram").apply {
            classes.addAll(listOf(sampleClass, sampleClass2))
        }

        val referenceResolver = ReferenceResolver()
        referenceResolver.execute(dependency)

        val typeReferences = dependency.typeReferences
        assertThat(typeReferences).containsEntry(KoType("SampleClass2"), sampleClass2)
    }

    @Test
    internal fun `reference resolver should find the function reference to SampleClass2`() {
        val function = KoFunction("sampleClass2", KoType("SampleClass2"))
        val sampleClass = KoClass(name = "SampleClass", functions = mutableListOf(function), properties = listOf())
        val sampleClass2 = KoClass(name = "SampleClass2", functions = mutableListOf(), properties = listOf())
        val dependency = FileDependency("com.cardinalblue.kogram").apply {
            classes.addAll(listOf(sampleClass, sampleClass2))
        }

        val referenceResolver = ReferenceResolver()
        referenceResolver.execute(dependency)

        val typeReferences = dependency.typeReferences
        assertThat(typeReferences).containsEntry(KoType("SampleClass2"), sampleClass2)
    }
}

