package org.timekeeper

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.timekeeper.grammar.*
import java.lang.StringBuilder


fun main(args: Array<String>) {
    val text = StringBuilder()

    while (true) {
        val line = readLine()
        if (line == null || line == "\u0004") {
            break
        }
        text.append(line)
    }
    val lexer = JavaScriptLexer(CharStreams.fromString(text.toString()))
    val tokens = CommonTokenStream(lexer)
    tokens.fill()
    tokens.tokens.forEach {
        if (it.text == " ") return@forEach
        println(it.text)
    }
    val parser = JavaScriptParser(tokens).apply { buildParseTree = true }
    val listener = ErrorListener.instance
    parser.removeErrorListeners()
    parser.addErrorListener(listener)

    val tree = parser.program();
    //val visitor = JavaScriptParserBaseVisitor<Unit>()
    println(tree.toStringTree(parser))

    return
}

