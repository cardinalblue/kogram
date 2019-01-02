package com.cardialblue.kogram.cli

import com.cardinalblue.kogram.core.KogramService
import com.cardinalblue.kogram.generator.puml.PumlGenerator
import java.io.File
import java.nio.file.Paths

fun main(args: Array<String>) {
    val cliArgs = parseArgs(args)

    val filePath = cliArgs.input?: "./"
    val kogramService = KogramService()

    println("resolving dependency...")
    val fileDependency = kogramService.resolveDependency(Paths.get(filePath))
    val umlGenerator = PumlGenerator(fileDependency)

    println("generating uml...")
    val umlString = umlGenerator.generate()
    val pumlFile = File("./output.puml")
    pumlFile.writeText(umlString)

    println("Finish!!")
}

private fun parseArgs(args: Array<String>): CliArgs{
    return parseArguments(args)
}
