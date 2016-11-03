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


/**
 * Container class for the TokenTypes Enum
 */
class EnumTokenTypes {
	/**
	 * Enum containing a list of token types
	 */
	enum class Types(val value: Int) {
		KEYWORD_TOKEN(1.shl(0)),
		OPERATOR_TOKEN(1.shl(1)),
		LEFT_BRACE_TOKEN(1.shl(2)),
		RIGHT_BRACE_TOKEN(1.shl(3)),
		LEFT_SQUARE_PAREN_TOKEN(1.shl(4)),
		RIGHT_SQUARE_PAREN_TOKEN(1.shl(5)),
		LEFT_PAREN_TOKEN(1.shl(6)),
		RIGHT_PAREN_TOKEN(1.shl(7)),
		WHITESPACE_TOKEN(1.shl(8)),
		END_OF_FILE_TOKEN(1.shl(9)),
		IDENTIFIER_TOKEN(1.shl(10)),
		NUMERIC_LITERAL(1.shl(11)),
		STRING_LITERAL(1.shl(12)),
		COMMENT_TOKEN(1.shl(13)),
		COMMA_TOKEN(1.shl(14)),
		DOT_TOKEN(1.shl(15)),
		TYPE_TOKEN(1.shl(16)),
		COLON_TOKEN(1.shl(17)),
		SEMICOLON_TOKEN(1.shl(18)),
		ARROW_TOKEN(1.shl(19)),
		UNKNOWN_TOKEN(1.shl(32))
	}
}