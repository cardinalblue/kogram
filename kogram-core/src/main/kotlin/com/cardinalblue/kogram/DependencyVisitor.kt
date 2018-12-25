package com.cardinalblue.kogram

import org.jetbrains.kotlin.psi.KtImportList
import org.jetbrains.kotlin.psi.KtPackageDirective
import org.jetbrains.kotlin.psi.KtTreeVisitorVoid

class DependencyVisitor: KtTreeVisitorVoid(){

    var dependency: FileDependency? = null

    override fun visitPackageDirective(directive: KtPackageDirective) {
        dependency = FileDependency(directive.fqName.toString())
        super.visitPackageDirective(directive)
    }

    override fun visitImportList(importList: KtImportList) {
        importList.imports.forEach {
            dependency!!.imports += ImportPath(it.importPath.toString())
        }
        super.visitImportList(importList)
    }
}