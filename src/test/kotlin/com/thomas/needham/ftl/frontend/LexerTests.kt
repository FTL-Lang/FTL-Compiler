package com.thomas.needham.ftl.frontend

import io.kotlintest.specs.FeatureSpec

/**
 * Created by thoma on 23/10/2016.
 */
class LexerTests : FeatureSpec(){
    val correctSourceCode : String = "func main -> () -> int {" +
            "\n return 0" +
            "\n }"

    init {
        feature("The Source Tokenizer"){
            scenario("Should Successfully Tokenize This Code ${correctSourceCode}")
            {
                val lexer : Lexer = Lexer(correctSourceCode)
                lexer.tokeniseSourceCode().shouldBe(true)
            }
        }
    }
}