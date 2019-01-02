package com.cardinalblue.kogram.core

class FileDependency(val packageName: String){
    val imports: MutableList<ImportPath> = mutableListOf()
    val classes: MutableList<KoClass> = mutableListOf()

    val typeReferences: MutableMap<KoType, KoClass> = mutableMapOf()
}

data class ImportPath(val path: String)

//TODO functions should not be mutableList
class KoClass(val name: String, val properties: List<KoProperty>, val functions: MutableList<KoFunction>)

class KoProperty(val name: String, val type: KoType)

class KoFunction(val name: String, val returnType: KoType)

//TODO should add package name
data class KoType(val name: String)