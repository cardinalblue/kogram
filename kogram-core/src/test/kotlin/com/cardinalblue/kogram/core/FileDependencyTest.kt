package com.cardinalblue.kogram.core

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class FileDependencyTest{

    @Test
    fun `parse import path`() {
        val content = """
            package com.cardinalblue.kogram

            import java.net.URI
            import java.nio.file.Path
            import java.nio.file.Paths

            class Test
		"""
        val ktFile = KtTestCompiler.compileFromContent(content.trimIndent())
        val testVisitor = DependencyVisitor()
        val dependency = testVisitor.resolveDependency(ktFile)

        assertEquals(listOf(ImportPath("java.net.URI"),
                ImportPath("java.nio.file.Path"),
                ImportPath("java.nio.file.Paths")), dependency.imports)
    }

    @Test
    fun `parse package name`() {
        val content = """
            package com.cardinalblue.kogram

            import java.net.URI
            import java.nio.file.Path
            import java.nio.file.Paths

            class Test
		"""
        val ktFile = KtTestCompiler.compileFromContent(content.trimIndent())
        val testVisitor = DependencyVisitor()
        val dependency = testVisitor.resolveDependency(ktFile)

        assertEquals("com.cardinalblue.kogram", dependency.packageName)
    }

    @Test
    fun `parse class name`() {
        val content = """
            package com.cardinalblue.kogram

            import java.net.URI
            import java.nio.file.Path
            import java.nio.file.Paths

            class SampleClass

            class SampleClass2
		"""
        val ktFile = KtTestCompiler.compileFromContent(content.trimIndent())
        val testVisitor = DependencyVisitor()
        val dependency = testVisitor.resolveDependency(ktFile)

        assertEquals(listOf("SampleClass", "SampleClass2"), dependency.classes.map{ it.name })
    }

    //TODO type inference needed to be consider
    @Test
    fun `parse class member`() {
        val content = """
            package com.cardinalblue.kogram

            import java.net.URI
            import java.nio.file.Path
            import java.nio.file.Paths

            class SampleClass {
               var sampleClass2: SampleClass2 = SampleClass2()
            }

		"""
        val ktFile = KtTestCompiler.compileFromContent(content.trimIndent())
        val testVisitor = DependencyVisitor()
        val dependency = testVisitor.resolveDependency(ktFile)
        val property = dependency.classes[0].properties[0]

        assertEquals("sampleClass2", property.name)
        assertEquals("SampleClass2", property.type.name)
    }

    @Test
    fun `parse class function`() {
        val content = """
            package com.cardinalblue.kogram

            import java.net.URI
            import java.nio.file.Path
            import java.nio.file.Paths

            class SampleClass {

               fun sampleFun(): Int {
                   return 1
               }
            }

		"""
        val ktFile = KtTestCompiler.compileFromContent(content.trimIndent())
        val testVisitor = DependencyVisitor()
        val dependency = testVisitor.resolveDependency(ktFile)
        val property = dependency.classes[0].functions[0]

        assertEquals("sampleFun", property.name)
        assertEquals("Int", property.returnType.name)
    }
}

