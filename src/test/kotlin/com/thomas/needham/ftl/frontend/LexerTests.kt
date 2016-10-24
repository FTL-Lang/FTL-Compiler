package com.thomas.needham.ftl.frontend

import io.kotlintest.specs.FeatureSpec

/**
 * Created by thoma on 23/10/2016.
 */
class LexerTests : FeatureSpec(){
    val correctSourceCode : String = "func main -> () -> int {" +
            "\n println(\"Hello World!\")" +
            "\n return 0" +
            "\n }"
    val unterminatedString : String = "func main -> () -> int {" +
            "\n println(\"Hello World!)" +
            "\n return 0" +
            "\n }"
    val correctTokens : List<String> = arrayOf<String>(
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
            "\"Hello World!\" : STRING_LITERAL",
            ") : RIGHT_PAREN_TOKEN",
            "return : KEYWORD_TOKEN",
            "0 : INTEGER_LITERAL",
            "} : RIGHT_BRACE_TOKEN",
            "\u0000 : END_OF_FILE_TOKEN"
    ).toList()
    init {
        feature("The Source Tokenizer"){
            scenario("Should Successfully Tokenize This Code ${correctSourceCode}") {
                val lexer : Lexer = Lexer(correctSourceCode)
                lexer.tokeniseSourceCode().shouldBe(true)
            }

            scenario("Should Error On Unterminated String"){
                val lexer : Lexer = Lexer(unterminatedString)
                lexer.tokeniseSourceCode().shouldBe(false)
            }

            scenario("Should Print The Following Tokens ${correctTokens.forEach { println(it) }}"){
                val lexer : Lexer = Lexer(correctSourceCode)
                lexer.tokeniseSourceCode()
                lexer.printTokens().forEachIndexed { index, item -> item.shouldEqual(correctTokens[index]) }
            }
        }
    }
}