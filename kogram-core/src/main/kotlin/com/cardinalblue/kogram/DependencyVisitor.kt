package com.cardinalblue.kogram

import org.jetbrains.kotlin.psi.*

class DependencyVisitor: KtTreeVisitorVoid(){

    var dependency: FileDependency? = null
    var visitingClass: KoClass? = null

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

    override fun visitNamedFunction(function: KtNamedFunction) {
        visitingClass?.let {
            val newFunction = KoFunction(function.name?: "UnKnown", function.typeReference?.typeElement?.text?: "UnKnown")
            it.functions += newFunction
        }
        super.visitNamedFunction(function)
    }

    override fun visitClass(klass: KtClass) {
        val koClass: KoClass = convertToKoClass(klass)
        dependency!!.classes += koClass
        visitingClass = koClass
        super.visitClass(klass)
    }

    private fun convertToKoClass(klass: KtClass): KoClass {
        val properties = mutableListOf<KoProperty>()
        val functions = mutableListOf<KoFunction>()
        klass.getProperties().forEach {
            val property = KoProperty(it.name?: "UnKnown", it.typeReference?.typeElement?.text?: "UnKnown")
            properties += property
        }
        return KoClass(klass.name!!, properties, functions)
    }


}