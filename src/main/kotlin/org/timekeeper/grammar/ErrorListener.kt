package org.timekeeper.grammar

import org.antlr.v4.runtime.BaseErrorListener
import org.antlr.v4.runtime.RecognitionException
import org.antlr.v4.runtime.Recognizer
import org.timekeeper.utils.REPORT_SYNTAX_ERRORS

class ErrorListener private constructor() : BaseErrorListener() {
    companion object {
        val instance by lazy { BaseErrorListener() }
    }

    override fun syntaxError(
        recognizer: Recognizer<*, *>?,
        offendingSymbol: Any?,
        line: Int,
        charPositionInLine: Int,
        msg: String?,
        e: RecognitionException?
    ) {
        if (!REPORT_SYNTAX_ERRORS) {
            return
        }
        System.err.println("line $line: $charPositionInLine $msg.")
    }
}