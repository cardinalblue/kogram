package com.cardinalblue.kogram.core

import com.cardinalblue.kogram.Dependency
import com.cardinalblue.kogram.DependencyVisitor
import com.cardinalblue.kogram.resolveDependency
import org.jetbrains.kotlin.psi.KtFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class SampleTest{

    @Test
    fun name() {
        val content = """
            import java.net.URI
            import java.nio.file.Path
            import java.nio.file.Paths

			val a = if (5 > 4 && 4 < 6 || (3 < 5 || 2 < 5)) { 42 } else { 24 }

			fun complexConditions() {
				while (5 > 4 && 4 < 6 || (3 < 5 || 2 < 5)) {}
				do { } while (5 > 4 && 4 < 6 || (3 < 5 || 2 < 5))
			}
		"""
        val ktFile = KtTestCompiler.compileFromContent(content.trimIndent())
        val testVisitor = DependencyVisitor()
        val imports = testVisitor.resolveDependency(ktFile)

        assertEquals(listOf(Dependency("java.net.URI"),
                Dependency("java.nio.file.Path"),
                Dependency("java.nio.file.Paths")), imports)
    }

}
