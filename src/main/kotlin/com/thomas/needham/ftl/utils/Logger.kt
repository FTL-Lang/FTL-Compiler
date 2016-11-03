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
package com.thomas.needham.ftl.utils

import com.thomas.needham.ftl.utils.GlobalFunctions.CONSOLE_COLOUR_RED
import com.thomas.needham.ftl.utils.GlobalFunctions.CONSOLE_COLOUR_RESET
import com.thomas.needham.ftl.utils.GlobalFunctions.CONSOLE_COLOUR_YELLOW
import com.thomas.needham.ftl.errors.EnumCompilerErrors
import com.thomas.needham.ftl.errors.EnumCompilerWarnings
import com.thomas.needham.ftl.frontend.lexer.Span

/**
 * Static functions that allow logging messages to the console in multiple colours
 */
object Logger {
	/**
	 * Prints a message to the console in white
	 * @param message the message to print
	 */
	@JvmStatic fun printMessage(message: String): Unit {
		println(message)
	}

	/**
	 * Prints a warning message to the console in yellow
	 * @param warningType the type of warning message
	 * @param span the location in the file where the warning occurred
	 * @see EnumCompilerWarnings
	 * @see Span
	 */
	@JvmStatic fun printWarning(warningType: EnumCompilerWarnings.Warnings, span: Span): Unit {
		println(CONSOLE_COLOUR_YELLOW + span.toString() + " " + warningType.message + CONSOLE_COLOUR_RESET)
	}

	/**
	 * Prints a warning message to the console in red
	 * @param errorType the type of error message
	 * @param span the location in the file where the error occurred
	 * @see EnumCompilerErrors
	 * @see Span
	 */
	@JvmStatic fun printError(errorType: EnumCompilerErrors.Errors, span: Span): Unit {
		println(CONSOLE_COLOUR_RED + span.toString() + " " + errorType.message + CONSOLE_COLOUR_RESET)
	}
}