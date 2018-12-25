package com.cardinalblue.kogram

import org.jetbrains.kotlin.psi.KtImportList
import org.jetbrains.kotlin.psi.KtTreeVisitorVoid

class DependencyVisitor: KtTreeVisitorVoid(){

    val dependencies = mutableListOf<Dependency>()

    override fun visitImportList(importList: KtImportList) {
        importList.imports.forEach {
            dependencies += Dependency(it.importPath.toString())
        }
        super.visitImportList(importList)
    }
}