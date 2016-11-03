/*
    The MIT License (MIT)

    FTL-Compiler Copyright (c) 2016 thoma

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
package com.thomas.needham.ftl.frontend

/**
 * Class to represent a location within a source file
 * @author Thomas Needham
 */
class Span {
	/**
	 *
	 */
	val file: SourceFile
	val beginLine: Int
	val beginColumn: Int
	val endLine: Int
	val endColumn: Int

	constructor(sourceFile: SourceFile, beginLine: Int, beginColumn: Int, endLine: Int = -1, endColumn: Int = -1) {
		this.file = sourceFile
		this.beginLine = beginLine
		this.beginColumn = beginColumn
		this.endLine = endLine
		this.endColumn = endColumn
	}

	fun getBeginPosition(): String {
		return "Line: ${beginLine}, Column: ${beginColumn}"
	}

	fun getEndPosition(): String {
		return "Line: ${endLine}, Column: ${endColumn}"
	}

	override fun toString(): String {
		return "${file.file.name}: ${getBeginPosition()} To ${getEndPosition()}"
	}
}
