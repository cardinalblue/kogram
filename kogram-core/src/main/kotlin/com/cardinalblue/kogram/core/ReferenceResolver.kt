package com.cardinalblue.kogram.core

class ReferenceResolver {

    fun execute(fileDependency: FileDependency){
        val koClasses = mutableListOf<KoClass>()
        val koTypes = mutableSetOf<KoType>()

        koClasses.addAll(fileDependency.classes)
        fileDependency.classes.forEach { koClass ->
            koClass.properties.forEach { koProperty ->
                koTypes.add(koProperty.type)
            }
            koClass.functions.forEach { koFunction ->
                koTypes.add(koFunction.returnType)
            }
        }

        //find reference
        koClasses.forEach { koClass ->
            koTypes.forEach { koType ->
                if(koClass.name == koType.name){
                    fileDependency.typeReferences[koType] = koClass
                }
            }
        }
    }
}