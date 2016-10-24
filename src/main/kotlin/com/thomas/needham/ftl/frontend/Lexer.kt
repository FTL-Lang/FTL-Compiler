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

import com.thomas.needham.ftl.Utils.StackFrame

/**
 * This class represents the lexer which splits up source code
 * into a list of tokens
 * @author Thomas Needham
 * @see Token
 * @see EnumTokenTypes
 */
class Lexer {
    /**
     * The inputted source code
     */
    val sourceString: String
    /**
     * Token registry to contain the lexed tokens
     */
    var tokenRegistry: TokenRegistry

    /**
     * Primary constructor for The Lexer
     * @param sourceString The Source code to be lexed
     */
    constructor(sourceString: String) {
        this.sourceString = sourceString
        this.tokenRegistry = TokenRegistry()
    }

    /**
     * Secondary constructor for the Lexer to be used when compiling multiple files
     * @param sourceString the Source code currently being lexed
     * @param tokenRegistry A token registry containing the currently lexed tokens
     */
    constructor(sourceString: String, tokenRegistry: TokenRegistry) {
        this.sourceString = sourceString
        this.tokenRegistry = tokenRegistry
    }

    /**
     * Function to split source code into a list of tokens
     * @return Whether the source code was tokenized successfully
     */
    fun tokeniseSourceCode(): Boolean {
        var sourceChars: Array<Char> = (sourceString + '\u0000').toCharArray().toTypedArray() // Append a NULL terminator '\0'
        var index: Int = 0
        var currentToken: StringBuilder = StringBuilder()
        while (index < sourceChars.size) { // while there is source code to read
            if (!isControlCharacter(sourceChars[index])) { // is the current char a control character
                if (sourceChars[index] == '\r' || sourceChars[index] == '\t') // ignore tabs and new lines
                {
                    index++
                    continue
                }
                currentToken.append(sourceChars[index]) // append the current char to the current token
                index++
            } else if (currentToken.toString() == "//") { // Ignore Comments
                while (sourceChars[index] != '\n') {
                    currentToken = StringBuilder()
                    index++
                }
            } else {
                val stackFrame = evaluateString(index, currentToken, sourceChars) // Check for unterminated string literals
                if (stackFrame == null) { // String Was Properly Terminated
                    System.err.println("Unterminated String Literal: ${currentToken.toString()}")
                    return false
                }
                index = stackFrame.valueX!!
                currentToken = stackFrame.valueY!!
                sourceChars = stackFrame.valueZ!! // XXX: Hack to emulate pass by reference

                if (!currentToken.toString().isNullOrEmpty() && !currentToken.toString().isNullOrBlank()) {
                    // If the current token is not null or empty add it to the registry
                    registerTokenToRegistry(currentToken.toString())
                }
                registerTokenToRegistry(sourceChars[index].toString()) // add the whitespace to the registry
                currentToken = StringBuilder() // clear the current token
                index++
            }
        }
        return true
    }

    /**
     * Function to add tokens to the token registry
     * @param token the token to add to the registry
     */
    private fun registerTokenToRegistry(token: String): Unit {
        if (!token.isNullOrEmpty() && !token.isNullOrBlank())
            tokenRegistry.registerToken(token)
    }

    /**
     * Function to terminate multi word string literals i.e literals containing spaces
     * @param index index of the char currently being read
     * @param currentToken the value of the current token
     * @param sourceChars the source code currently being lexed
     * @return A StackFrame containing the index and token value after string termination,
     * or null if the string could not be terminated
     * @see StackFrame
     */
    private fun evaluateString(index: Int, currentToken: StringBuilder, sourceChars: Array<Char>): StackFrame? {
        var pIndex: Int = index // Make a copy of the passed values
        val pCurrentToken: StringBuilder = currentToken
        val pSourceChars: Array<Char> = sourceChars

        if (!pCurrentToken.toString().isNullOrEmpty() && !pCurrentToken.toString().isNullOrBlank()) {
            // if the current token is not empty
            if (pCurrentToken.toString().startsWith("\"")) {
                // if the current token does not end in "
                while (!pCurrentToken.endsWith("\"")) {
                    // account for escape characters
                    if (pIndex > pSourceChars.size - 1)
                        return null
                    if (pSourceChars[pIndex] != '\\' || (pSourceChars[pIndex] == '\\' && pSourceChars[pIndex - 1] == '\\')) {
                        pCurrentToken.append(pSourceChars[pIndex]) // Keep appending chars until we find a " or we reach the end
                    }
                    pIndex++

                    if (pSourceChars[pIndex] == '\\') {
                        pIndex++
                        while (pSourceChars[pIndex - 1] != '\"' || pSourceChars[pIndex - 2] == '\\') {
                            if (pIndex > pSourceChars.size - 1)
                                return null
                            if (sourceChars[pIndex] != '\\' || sourceChars[pIndex] == '\"') {
                                if (pSourceChars[pIndex - 1] == '\\') {
                                    when (sourceChars[pIndex]) {
                                        '0' -> pCurrentToken.append('\u0000') // NULL terminator
                                        'n' -> pCurrentToken.append('\n')
                                        'r' -> pCurrentToken.append('\r')
                                        't' -> pCurrentToken.append('\t')
                                        'b' -> pCurrentToken.append('\b')
                                        's' -> pCurrentToken.append(' ')
                                        'f' -> pCurrentToken.append('\u000C')
                                        '\'' -> pCurrentToken.append('\'')
                                        '\"' -> pCurrentToken.append('\"')
                                        '\\' -> pCurrentToken.append('\\')
                                        else -> pCurrentToken.append(sourceChars[pIndex])
                                    }
                                } else {
                                    pCurrentToken.append(sourceChars[pIndex])
                                }
                                pIndex++
                            }
                            else{
                                pIndex++
                                continue
                            }
                        }
                    }
                }
                pIndex++
            }
        }
        return StackFrame(pIndex, pCurrentToken, pSourceChars)
    }

    /**
     * Returns Whether a character is a control character
     * @param ch the char to test
     * @return Whether ch is a control character
     */
    private fun isControlCharacter(ch: Char): Boolean {
        return !(ch != ' ' && ch != '\n' &&
                ch != '\t' && ch != '(' &&
                ch != ')' && ch != '[' &&
                ch != ']' && ch != '{' &&
                ch != '}' && ch != '\u0000')
    }

    /**
     * Function to print all of the tokens defined in the token registry
     * @return Returns a list of tokens for the purpose of unit testing
     */
    fun printTokens(): List<String> { // Only Returns a list of tokens for the purpose of unit testing
        val list: MutableList<String> = mutableListOf()
        for (token: Token in tokenRegistry.registeredTokens) {
            list.add(token.toString())
            println(token.toString())
        }
        return list
    }
}