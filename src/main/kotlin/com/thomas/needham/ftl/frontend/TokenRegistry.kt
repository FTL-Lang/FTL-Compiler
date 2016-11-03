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

import com.thomas.needham.ftl.Utils.TryParse
import com.thomas.needham.ftl.Utils.TryDecode

/**
 * Class to manage all of the tokens that are predefined within the compiler
 * such as Keywords, Types, Operators, etc. And also all of the tokens produced by the Lexer
 * when lexing a source file
 * @author Tom Needham (06needhamt)
 * @see Lexer
 */
class TokenRegistry {
	/**
	 * Companion object of Token Registry to contain all static functions and variables
	 */
	companion object StaticData {
		/**
		 * Initialisation lambda for Array<Token>
		 */
		@JvmStatic val tokenArrayInit: (Int) -> Token = {
			Token("NULL", EnumTokenTypes.Types.UNKNOWN_TOKEN)
		}
	}

	/**
	 * Mutable list to contain all tokens outputted by the Lexer
	 * @see Lexer
	 */
	val registeredTokens: MutableList<Token>

	/**
	 * Immutable list to contain all all of the tokens that are predefined within the compiler
	 * such as Keywords, Types, Operators, etc.
	 */
	val predefinedTokens: Array<Token> = arrayOf<Token>(
		Token("class", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("interface", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("enum", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("singleton", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("static", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("final", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("abstract", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("override", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("while", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("for", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("if", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("else", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("switch", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("case", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("default", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("synchronised", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("continue", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("break", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("true", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("false", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("null", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("func", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("do", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("var", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("let", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("return", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("this", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("call", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("goto", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("as", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("int", EnumTokenTypes.Types.TYPE_TOKEN),
		Token("short", EnumTokenTypes.Types.TYPE_TOKEN),
		Token("long", EnumTokenTypes.Types.TYPE_TOKEN),
		Token("float", EnumTokenTypes.Types.TYPE_TOKEN),
		Token("double", EnumTokenTypes.Types.TYPE_TOKEN),
		Token("bool", EnumTokenTypes.Types.TYPE_TOKEN),
		Token("byte", EnumTokenTypes.Types.TYPE_TOKEN),
		Token("char", EnumTokenTypes.Types.TYPE_TOKEN),
		Token("object", EnumTokenTypes.Types.TYPE_TOKEN),
		Token("string", EnumTokenTypes.Types.TYPE_TOKEN),
		Token("array", EnumTokenTypes.Types.TYPE_TOKEN),
		Token("=", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token("+", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token("-", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token("*", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token("/", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token("%", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token("+=", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token("-=", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token("*=", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token("/=", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token("%=", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token("==", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token("!=", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token("&", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token("|", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token("~", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token("^", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token("<<", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token(">>", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token(">>>", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token("&=", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token("|=", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token("~=", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token("^=", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token("<<=", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token(">>=", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token(">>>=", EnumTokenTypes.Types.OPERATOR_TOKEN),
		Token("(", EnumTokenTypes.Types.LEFT_PAREN_TOKEN),
		Token(")", EnumTokenTypes.Types.RIGHT_PAREN_TOKEN),
		Token("[", EnumTokenTypes.Types.LEFT_SQUARE_PAREN_TOKEN),
		Token("]", EnumTokenTypes.Types.RIGHT_SQUARE_PAREN_TOKEN),
		Token("{", EnumTokenTypes.Types.LEFT_BRACE_TOKEN),
		Token("}", EnumTokenTypes.Types.RIGHT_BRACE_TOKEN),
		Token("\n", EnumTokenTypes.Types.WHITESPACE_TOKEN),
		Token("\r", EnumTokenTypes.Types.WHITESPACE_TOKEN),
		Token("\t", EnumTokenTypes.Types.WHITESPACE_TOKEN),
		Token(" ", EnumTokenTypes.Types.WHITESPACE_TOKEN),
		Token(",", EnumTokenTypes.Types.COMMA_TOKEN),
		Token(".", EnumTokenTypes.Types.DOT_TOKEN),
		Token(":", EnumTokenTypes.Types.COLON_TOKEN),
		Token(";", EnumTokenTypes.Types.SEMICOLON_TOKEN),
		Token("->", EnumTokenTypes.Types.ARROW_TOKEN),
		Token("", EnumTokenTypes.Types.END_OF_FILE_TOKEN),
		Token("\u0000", EnumTokenTypes.Types.END_OF_FILE_TOKEN)
	)

	/**
	 * Constructor For the TokenRegistry
	 */
	constructor() {
		registeredTokens = mutableListOf<Token>()
	}

	/**
	 * Function to add a Token into the TokenRegistry
	 * @param value The string value of the token to be added
	 * @return Whether the Token was successfully added to the TokenRegistry
	 * @see Token
	 */
	fun registerToken(value: String): Boolean {
		var temp: Token? = predefinedTokens.firstOrNull { x -> // Is The Token Already present in the registry
			x.value == value
		}
		if (temp != null) { // if the token is already present
			registeredTokens.add(temp) // just add it to the registry no need to resolve it's type
		} else { // if the token is not already present
			if (Double.TryParse(value)) // is it a valid numeric literal
				temp = Token(value, EnumTokenTypes.Types.NUMERIC_LITERAL)
			else if(Long.TryDecode(value)) // is it a valid non base 10 numeric literal
				temp = Token(value, EnumTokenTypes.Types.NUMERIC_LITERAL)
			else if (value.startsWith("\"") && value.endsWith("\"")) // is it a valid string literal
				temp = Token(value, EnumTokenTypes.Types.STRING_LITERAL)
			else if (!value.isNullOrEmpty() && !value.isNullOrBlank()) // is it a valid identifier
				temp = Token(value, EnumTokenTypes.Types.IDENTIFIER_TOKEN)
			else {
				System.err.println("ERROR: Found Unknown Token: ${value}")
				// We have found an unknown token N.B This should never happen
				return false
			}
			registeredTokens.add(temp) // the token has been resolved add it to the registry
		}
		return true
	}
}