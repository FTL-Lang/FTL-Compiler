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

import com.sun.org.apache.xpath.internal.operations.Bool
import com.thomas.needham.neurophidea.Tuple3
import kotlin.reflect.KFunction
import kotlin.reflect.jvm.reflect

class Lexer {
    val sourceString : String
    var tokenRegistry : TokenRegistry

    constructor(sourceString: String){
        this.sourceString = sourceString
        this.tokenRegistry = TokenRegistry()
    }

    constructor(sourceString: String, tokenRegistry: TokenRegistry){
        this.sourceString = sourceString
        this.tokenRegistry = tokenRegistry
    }

    fun tokeniseSourceCode() : Boolean{
        var sourceChars : Array<Char> = (sourceString + '\u0000').toCharArray().toTypedArray()
        var index : Int = 0
        var currentToken : StringBuilder = StringBuilder()
        while (index < sourceChars.size){
            if(!isControlCharacter(sourceChars[index])){
                if (sourceChars[index] == '\r' || sourceChars[index] == '\t')
                {
                    index++
                    continue
                }
                currentToken.append(sourceChars[index])
                index++
            }
            else{
                val stackFrame = TerminateString(index, currentToken, sourceChars)
                if(stackFrame == null) { // String Was Successfully Terminated
                    System.err.println("Unterminated String Literal: ${currentToken.toString()}")
                    return false
                }
                index = stackFrame.valueX!!
                currentToken = stackFrame.valueY!!
                sourceChars = stackFrame.valueZ!!
                if(!currentToken.toString().isNullOrEmpty() && !currentToken.toString().isNullOrBlank()){
                    registerTokenToRegistry(currentToken.toString())
                }
                registerTokenToRegistry(sourceChars[index].toString())
                currentToken = StringBuilder()
                index++
            }
        }
        return true
    }

    private fun registerTokenToRegistry(token: String) : Unit{
        if(!token.isNullOrEmpty() && !token.isNullOrBlank())
            tokenRegistry.registerToken(token)
    }

    private fun TerminateString(index: Int, currentToken: StringBuilder, sourceChars: Array<Char>): Tuple3<Int,StringBuilder, Array<Char>>? {
        var pIndex : Int = index
        val pCurrentToken : StringBuilder = currentToken
        val pSourceChars : Array<Char> = sourceChars

        if(!pCurrentToken.toString().isNullOrEmpty() && !pCurrentToken.toString().isNullOrBlank()){
            if(pCurrentToken.toString().startsWith("\"") && !pCurrentToken.toString().endsWith("\"")){
                while(!pCurrentToken.endsWith("\"")){
                    if(pIndex > pSourceChars.size - 1)
                        return null
                    pCurrentToken.append(pSourceChars[pIndex])
                    pIndex++
                }
            }
        }
        return Tuple3(pIndex, pCurrentToken, pSourceChars)
    }

    private fun isControlCharacter(ch: Char) : Boolean {
        return !(ch != ' ' && ch != '\n' &&
                ch != '\t' && ch != '(' &&
                ch != ')' && ch != '[' &&
                ch != ']' && ch != '{' &&
                ch != '}' && ch != '\u0000')
    }

    fun printTokens() : Unit{
        for(token : Token in tokenRegistry.registeredTokens){
            println(token.toString())
        }
    }
}