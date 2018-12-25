package com.cardinalblue.kogram

import org.jetbrains.kotlin.psi.KtFile

fun DependencyVisitor.resolveDependency(ktFile: KtFile): FileDependency {
    this.visitFile(ktFile)
    return this.dependency!!
}