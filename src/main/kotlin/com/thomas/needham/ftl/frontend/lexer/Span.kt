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
package com.thomas.needham.ftl.frontend.lexer

import com.thomas.needham.ftl.utils.SourceFile

/**
 * Class to represent a location within a source file
 * @author Thomas Needham
 */
class Span {
	/**
	 * The SourceFile that this span is contained in
	 * @see SourceFile
	 */
	val file: SourceFile

	/**
	 * The Line that this span starts on
	 */
	val beginLine: Int

	/**
	 * The Column that this span starts on
	 */
	val beginColumn: Int

	/**
	 * The Line that this span ends on
	 */
	val endLine: Int

	/**
	 * The Column that this span ends on
	 */

	val endColumn: Int

	/**
	 * Constructor for Span
	 * @param sourceFile The SourceFile that this span is contained in
	 * @param beginLine The Line that this span starts on
	 * @param beginColumn The Column that this span starts on
	 * @param endLine The Line that this span ends on Leave blank if unknown
	 * @param endColumn The Column that this span ends on Leave blank if unknown
	 * @see SourceFile
	 */
	constructor(sourceFile: SourceFile, beginLine: Int, beginColumn: Int, endLine: Int = -1, endColumn: Int = -1) {
		this.file = sourceFile
		this.beginLine = beginLine
		this.beginColumn = beginColumn
		this.endLine = endLine
		this.endColumn = endColumn
	}

	/**
	 * Function to get the beginning location of this span
	 * @return A string which represents the beginning of this span
	 */
	fun getBeginPosition(): String {
		return "Line: ${beginLine}, Column: ${beginColumn}"
	}
	/**
	 * Function to get the ending location of this span
	 * @return A string which represents the end of this span
	 */
	fun getEndPosition(): String {
		return "Line: ${endLine}, Column: ${endColumn}"
	}

	/**
	 * Function to get a string which represents this span
	 * @return A string that represents this span
	 */
	override fun toString(): String {
		return "${file.file.name}: ${getBeginPosition()} To ${getEndPosition()}"
	}
}
