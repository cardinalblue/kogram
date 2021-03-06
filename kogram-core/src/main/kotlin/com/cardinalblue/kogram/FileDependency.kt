package com.cardinalblue.kogram

class FileDependency(val packageName: String){
    val imports: MutableList<ImportPath> = mutableListOf()
    val classes: MutableList<KoClass> = mutableListOf()
}

data class ImportPath(val path: String)

class KoClass(val name: String, val properties: List<KoProperty>, val functions: MutableList<KoFunction>)

class KoProperty(val name: String, val type: String)

class KoFunction(val name: String, val returnType: String)