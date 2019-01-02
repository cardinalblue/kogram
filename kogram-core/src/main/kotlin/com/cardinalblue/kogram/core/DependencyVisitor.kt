package com.cardinalblue.kogram.core

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
            val newFunction = KoFunction(function.name ?: "UnKnown",
                    getTypeFromReference(function.typeReference))
            it.functions += newFunction
        }
        super.visitNamedFunction(function)
    }

    private fun getTypeFromReference(typeReference: KtTypeReference?): KoType {
        val name = typeReference?.typeElement?.text?: "UnKnown"
        return KoType(name)
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
            val property = KoProperty(it.name ?: "UnKnown",
                    getTypeFromReference(it.typeReference))
            properties += property
        }
        return KoClass(klass.name!!, properties, functions)
    }


}