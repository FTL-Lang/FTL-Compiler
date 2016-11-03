/*
    The MIT License (MIT)

    FTL-Lang Copyright (c) 2016 thoma

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
*/
package com.thomas.needham.ftl.utils

/**
 * This class represents the command line arguments that are passed to the compiler
 */
class CommandLineArguments {
	/**
	 * The Input File Path
	 */
	val inputFile: String

	/**
	 * The Output File Path
	 */
	val outputFile: String

	/**
	 * Constructor for CommandLineArguments
	 * @param inputFile the input file path
	 * @param outputFile the output file path
	 */
	constructor(inputFile: String, outputFile: String) {
		this.inputFile = inputFile
		this.outputFile = outputFile
	}
}