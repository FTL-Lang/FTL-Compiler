package com.thomas.needham.ftl.frontend

import io.kotlintest.specs.FeatureSpec

/**
 * Created by thoma on 24/10/2016.
 */
class TokenRegistryTests : FeatureSpec(){
    val tokens : List<Token> = mutableListOf(
            Token("func", EnumTokenTypes.Types.KEYWORD_TOKEN),
            Token("\u0000", EnumTokenTypes.Types.END_OF_FILE_TOKEN)
    )
    init{
        feature("The Token Registry"){
            scenario("Should contain these tokens"){
                val lexer : Lexer = Lexer("func")
                lexer.tokeniseSourceCode()
                lexer.tokenRegistry.registeredTokens.forEachIndexed { i, token ->
                    (token.value == tokens[i].value).and((token.type == tokens[i].type)).shouldBe(true)
                }
            }
        }
    }
}