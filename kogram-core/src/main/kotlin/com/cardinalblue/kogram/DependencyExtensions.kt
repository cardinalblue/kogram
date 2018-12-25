package com.cardinalblue.kogram

import org.jetbrains.kotlin.psi.KtFile

fun DependencyVisitor.resolveDependency(ktFile: KtFile): List<Dependency> {
    this.visitFile(ktFile)
    return this.dependencies
}