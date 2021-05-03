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

import com.thomas.needham.ftl.frontend.lexer.EnumTokenTypes
import com.thomas.needham.ftl.frontend.lexer.Lexer
import com.thomas.needham.ftl.frontend.lexer.Token
import com.thomas.needham.ftl.frontend.lexer.TokenRegistry
import com.thomas.needham.ftl.utils.SourceFile
import io.kotest.core.spec.style.FeatureSpec
import io.kotest.matchers.shouldBe
import java.io.File

class TokenRegistryTests : FeatureSpec() {
	val tokens: List<Token> = mutableListOf(
		Token("func", EnumTokenTypes.Types.KEYWORD_TOKEN),
		Token("\u0000", EnumTokenTypes.Types.END_OF_FILE_TOKEN)
	)

	init {
		feature("The Token Registry") {
			scenario("Should contain these tokens") {
				val lexer: Lexer = Lexer(SourceFile(File("testdata/tokenregistry/correcttokens.warp")))
				lexer.tokeniseSourceCode()
				lexer.tokenRegistry.registeredTokens.forEachIndexed { i, token ->
					(token.value == tokens[i].value).and((token.type == tokens[i].type)).shouldBe(true)
				}
			}
			scenario("Should Register a Token") {
				val registry: TokenRegistry = TokenRegistry()
				val tok: Token = Token("func", EnumTokenTypes.Types.KEYWORD_TOKEN)
				registry.registerToken(tok.value)
				(registry.registeredTokens.firstOrNull()?.value == tok.value)
					.and(registry.registeredTokens.firstOrNull()?.type == tok.type).shouldBe(true)
			}
		}
	}
}