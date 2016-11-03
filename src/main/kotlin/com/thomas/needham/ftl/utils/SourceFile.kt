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

import java.io.File

/**
 * This class represents a source code file
 * @author Thomas Needham
 */
class SourceFile {
	/**
	 * Handle to the file
	 */
	val file: File

	/**
	 * The text contained in the file
	 */
	val text: Array<Char>

	/**
	 * The path to the file
	 */
	val path: String

	/**
	 * the size of the file
	 */
	val length: Long

	/**
	 * The number of lines in the file
	 */

	val lineCount: Long

	/**
	 * Constructor for SourceFile
	 * @param file A handle to a file
	 */
	constructor(file: File) {
		this.file = file
		if (file.isFile && file.exists() && file.canRead()) {
			this.text = (file.readText() + "\u0000").toCharArray().toTypedArray()
			this.path = file.absolutePath
			this.length = file.length()
			this.lineCount = this.text.count { x -> x == '\n' }.toLong()
		} else {
			throw IllegalArgumentException("Invalid Source File")
		}
	}
}