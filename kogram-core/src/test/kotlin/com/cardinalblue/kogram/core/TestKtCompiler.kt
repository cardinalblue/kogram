package com.cardinalblue.kogram.core

import org.jetbrains.kotlin.com.intellij.openapi.util.text.StringUtilRt
import org.jetbrains.kotlin.idea.KotlinLanguage
import org.jetbrains.kotlin.psi.KtFile
import java.nio.file.Path
import java.nio.file.Paths


/**
 *  this class is copied from detekt
 */
object KtTestCompiler : KtCompiler() {

    private val root = Paths.get(resource("/"))

    fun compile(path: Path) = compile(root, path)

    fun compileFromContent(content: String): KtFile
            = psiFileFactory.createFileFromText(KotlinLanguage.INSTANCE, StringUtilRt.convertLineSeparators(content)) as KtFile
}


