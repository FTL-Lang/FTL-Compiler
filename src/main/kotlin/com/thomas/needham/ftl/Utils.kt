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
package com.thomas.needham.ftl

import java.io.*

/**
 * Static object containing useful utility functions which are accessible from the global scope
 */
object Utils {

    @JvmStatic inline fun <reified INNER> array2d(sizeOuter: Int, sizeInner: Int, noinline innerInit: (Int) -> INNER) : Array<Array<INNER>> = Array(sizeOuter) { Array<INNER>(sizeInner, innerInit) }
    @JvmStatic fun array2dOfInt(sizeOuter: Int, sizeInner: Int) : Array<IntArray> = Array(sizeOuter) { IntArray(sizeInner) }
    @JvmStatic fun array2dOfShort(sizeOuter: Int, sizeInner: Int) : Array<ShortArray> = Array(sizeOuter) { ShortArray(sizeInner) }
    @JvmStatic fun array2dOfLong(sizeOuter: Int, sizeInner: Int) : Array<LongArray> = Array(sizeOuter) { LongArray(sizeInner) }
    @JvmStatic fun array2dOfByte(sizeOuter: Int, sizeInner: Int) : Array<ByteArray> = Array(sizeOuter) { ByteArray(sizeInner) }
    @JvmStatic fun array2dOfChar(sizeOuter: Int, sizeInner: Int) : Array<CharArray> = Array(sizeOuter) { CharArray(sizeInner) }
    @JvmStatic fun array2dOfFloat(sizeOuter: Int, sizeInner: Int) : Array<FloatArray> = Array(sizeOuter) { FloatArray(sizeInner) }
    @JvmStatic fun array2dOfDouble(sizeOuter: Int, sizeInner: Int) : Array<DoubleArray> = Array(sizeOuter) { DoubleArray(sizeInner) }
    @JvmStatic fun array2dOfBoolean(sizeOuter: Int, sizeInner: Int) : Array<BooleanArray> = Array(sizeOuter) { BooleanArray(sizeInner) }

    typealias StackFrame = Tuple3<Int, StringBuilder, Array<Char>>
    typealias EvaluateStringParams = Tuple3<Int, StringBuilder, Array<Char>>

    /**
     * Returns a binary files contents
     * @param path the path of the file to return
     * @return The Contents of the file
     */
    @Throws(IOException::class)
    @JvmStatic fun fileToString(path: String) : String {
        val builder = StringBuilder()
        val reader = BufferedReader(FileReader(path))

        var line: String?

        line = reader.readLine()
        while (line != null) {
            builder.append(line).append('\n')
            line = reader.readLine()
        }

        reader.close()

        return builder.toString()
    }
    /**
     * Returns a text files contents
     * @param path the path of the file to return
     * @return The Contents of the file
     * @throws IOException
     * @throws FileNotFoundException
     */
    @Throws(IOException::class, FileNotFoundException::class)
    fun ReadAllText(file: File) : String{
        if(file.isDirectory || !file.canRead()){
            throw FileNotFoundException("Invalid File: ${file.path}")
        }
        val fr : FileReader = FileReader(file)
        val br : BufferedReader = BufferedReader(fr)
        val contents = br.readText()
        if(contents.isNullOrBlank() || contents.isNullOrEmpty())
            println("File is Empty: ${file.path}")
        br.close()
        fr.close()
        return contents
    }

    /**
     * Extension method for Double that safely tries to parse a double
     * @param value the value to parse
     * @return true if the parse succeeded false if the parse failed
     */
    fun Double.Companion.TryParse(value: String) : Boolean { // Companion is required to make TryParse Static
        try {
            value.toDouble()
            return true
        } catch (nfe: NumberFormatException) {
            return false
        }
    }
}