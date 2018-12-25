package com.cardinalblue.kogram.core

import com.cardinalblue.kogram.DependencyVisitor
import com.cardinalblue.kogram.ImportPath
import com.cardinalblue.kogram.resolveDependency
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

}
