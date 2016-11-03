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

import com.thomas.needham.ftl.frontend.lexer.EnumTokenTypes

/**
 * Class to represent a single Token outputted by the Lexer
 */
class Token {
	/**
	 * The String value of this Token
	 */
	val value: String
	/**
	 * The Type of this Token
	 * @see EnumTokenTypes
	 */
	val type: EnumTokenTypes.Types

	/**
	 * Constructor for Token
	 * @param value the string value of this token
	 * @param type the type of this token
	 * @see EnumTokenTypes
	 */
	constructor(value: String, type: EnumTokenTypes.Types) {
		this.value = value
		this.type = type
	}

	/**
	 * Function to get a string that represents this token
	 * @return A string which represents this token
	 */
	override fun toString(): String {
		return value + " : " + type.name
	}
}