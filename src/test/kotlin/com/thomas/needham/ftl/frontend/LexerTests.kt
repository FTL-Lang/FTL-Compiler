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

import com.thomas.needham.ftl.frontend.lexer.Lexer
import com.thomas.needham.ftl.utils.SourceFile
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import io.kotest.matchers.shouldBe
import java.io.File

class LexerTests : FeatureSpec() {
	val correctSourceCode: String = "func main -> () -> int {" +
		"\n println(\"Hello World \\\"Escape Test!\\\" 'After Escaped Quotes'\") // This is a comment\n" +
		"return 0\n }"
	val unterminatedString: String = "func main -> () -> int {" +
		"\n println(\"Hello World \\\"Escape Test!\\\" 'After Escaped Quotes') // This is a comment\n" +
		"return 0\n }"
	val correctTokens: List<String> = arrayOf<String>(
		"func : KEYWORD_TOKEN",
		"main : IDENTIFIER_TOKEN",
		"-> : ARROW_TOKEN",
		"( : LEFT_PAREN_TOKEN",
		") : RIGHT_PAREN_TOKEN",
		"-> : ARROW_TOKEN",
		"int : TYPE_TOKEN",
		"{ : LEFT_BRACE_TOKEN",
		"println : IDENTIFIER_TOKEN",
		"( : LEFT_PAREN_TOKEN",
		"\"Hello World \"Escape Test!\" 'After Escaped Quotes'\" : STRING_LITERAL",
		"return : KEYWORD_TOKEN",
		"0 : NUMERIC_LITERAL",
		"} : RIGHT_BRACE_TOKEN",
		"\u0000 : END_OF_FILE_TOKEN"
	).toList()

	init {
		feature("The Source Tokenizer") {
			scenario("Should Successfully Tokenize This Code ${correctSourceCode}") {
				val lexer: Lexer = Lexer(SourceFile(File("testdata/lexer/correct.warp")))
				lexer.tokeniseSourceCode().shouldBe(true)
			}

			scenario("Should Error On Unterminated String") {
				val lexer: Lexer = Lexer(SourceFile(File("testdata/lexer/unterminated.warp")))
				lexer.tokeniseSourceCode().shouldBe(false)
			}

			scenario("Should Print The Following Tokens ${correctTokens.forEach { println(it) }}") {
				val lexer: Lexer = Lexer(SourceFile(File("testdata/lexer/correct.warp")))
				lexer.tokeniseSourceCode()
				lexer.printTokens().forEachIndexed { index, item -> item.shouldBeEqualComparingTo(correctTokens[index]) }
			}
		}
	}
}