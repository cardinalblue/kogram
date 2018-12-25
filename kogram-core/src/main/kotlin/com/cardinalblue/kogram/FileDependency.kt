package com.cardinalblue.kogram

class FileDependency(val packageName: String){
    val imports: MutableList<ImportPath> = mutableListOf()
}

data class ImportPath(val path: String)