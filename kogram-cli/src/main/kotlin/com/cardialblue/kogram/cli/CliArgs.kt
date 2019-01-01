package com.cardialblue.kogram.cli

import com.beust.jcommander.Parameter

interface Args {
    var help: Boolean
}

class CliArgs: Args {

    @Parameter(names = ["--input", "-i"],
            required = false,
            description = "Input path to analyze")
    var input: String? = null

    @Parameter(names = ["--help", "-h"],
            help = true, description = "Shows the usage.")
    override var help: Boolean = false
}