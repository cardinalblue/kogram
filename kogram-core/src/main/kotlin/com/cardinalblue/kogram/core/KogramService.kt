package com.cardinalblue.kogram.core

import java.nio.file.Path

/**
 *  Resolve a directory's dependency by certain rules
 */

class KogramService {

    fun resolveDependency(path: Path): FileDependency {
        val compiler = KtCompiler()
        val file = compiler.compile(path, path)
        val dependencyVisitor = DependencyVisitor()
        return dependencyVisitor.resolveDependency(file)
    }
}