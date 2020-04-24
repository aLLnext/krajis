package org.timekeeper.grammar.exceptions

import org.antlr.v4.runtime.ParserRuleContext


class InvalidOperationException(ctx: ParserRuleContext?, name: String) : Exception(
    getMsg(
        ctx,
        name
    )
) {
    companion object {
        fun getMsg(ctx: ParserRuleContext?, name: String): String {
            return "INVALID OPERATION EXCEPTION:\nline -> ${ctx?.start?.line}| ${ctx?.start?.startIndex}:${ctx?.stop?.stopIndex} $name"
        }
    }
}