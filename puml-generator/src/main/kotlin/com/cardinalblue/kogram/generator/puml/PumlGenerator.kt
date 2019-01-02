package com.cardinalblue.kogram.generator.puml

import com.cardinalblue.kogram.core.FileDependency
import com.cardinalblue.kogram.core.KoClass
import com.cardinalblue.kogram.core.KoFunction
import com.cardinalblue.kogram.core.KoProperty
import java.lang.StringBuilder

private const val TAG_START = "@startuml"
private const val TAG_END = "@enduml"
private const val NAME_PACKAGE = "package"
private const val NAME_CLASS = "class"

class PumlGenerator(private val fileDependency: FileDependency) {

    private val dependency = mutableSetOf<Pair<String, String>>()

    fun generate(): String{
        val stringBuilder = StringBuilder(TAG_START)
        with(stringBuilder){
            appendln()
            appendln()
            appendln("$NAME_PACKAGE \"${fileDependency.packageName}\" {")
            fileDependency.classes.forEach { koClass ->
                appendKoClass(koClass)
            }
            appendln("}")
            appendln()
            appendDependency()
            appendln(TAG_END)
        }

        return stringBuilder.toString()
    }

    private fun StringBuilder.appendKoClass(koClass: KoClass) {
        appendTab()
        if(koClass.functions.size + koClass.properties.size == 0){
            appendln("$NAME_CLASS ${koClass.name}")
        }else{
            appendln("$NAME_CLASS ${koClass.name} {")
            koClass.functions.forEach { appendKoFunction(it, koClass.name) }
            koClass.properties.forEach { appendKoProperty(it, koClass.name) }
            appendTab()
            appendln("}")
        }
    }

    private fun StringBuilder.appendKoFunction(koFunction: KoFunction, className: String) {
        appendTab(2)
        appendln("+ ${koFunction.name}(): ${koFunction.returnType.name}")
        if(fileDependency.typeReferences.containsKey(koFunction.returnType)){
            dependency.add(Pair(className, koFunction.returnType.name))
        }
    }

    private fun StringBuilder.appendKoProperty(koProperty: KoProperty, className: String){
        appendTab(2)
        appendln("+ ${koProperty.name}: ${koProperty.type.name}")
        if(fileDependency.typeReferences.containsKey(koProperty.type)){
            dependency.add(Pair(className, koProperty.type.name))
        }
    }

    private fun StringBuilder.appendDependency(){
        if(dependency.isNotEmpty()){
            dependency.forEach {
                appendln("${it.first} --> ${it.second}")
            }
            appendln()
        }
    }

}