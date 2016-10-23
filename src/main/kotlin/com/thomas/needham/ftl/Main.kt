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

@file:JvmName("Main")

package com.thomas.needham.ftl

import com.thomas.needham.ftl.Utils.ReadAllText
import com.thomas.needham.ftl.frontend.Lexer
import java.io.File

data class Arguments(val inputFile: String, val outputFile: String)

fun main(args: Array<String>) : Unit {
    println("FTL Language Compiler Helping You Code Faster Than Light!")
    if(args.size >= 2) { // TODO Parse Arguments
        for (i : Int in 0..args.size - 1) {
            println("args[${i}] = ${args[i]}")
        }
        val arguments : Arguments = Arguments(args.first(), args.last())
        val lexer : Lexer = Lexer(ReadAllText(File(arguments.inputFile)))
        if(!lexer.tokeniseSourceCode()){
            System.err.println("Error Lexing File: ${arguments.inputFile}")
            return
        }
        lexer.printTokens()
        println("File Lexed Successfully ${arguments.inputFile}")
    }
    else{
        println("Invalid Arguments")
        return
    }
}