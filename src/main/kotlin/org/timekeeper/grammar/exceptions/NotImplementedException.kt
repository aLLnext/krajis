package org.timekeeper.grammar.exceptions

import org.antlr.v4.runtime.ParserRuleContext

class NotImplementedException(ctx: ParserRuleContext?, name: String) : Exception(
    getMsg(
        ctx,
        name
    )
) {
    companion object {
        fun getMsg(ctx: ParserRuleContext?, name: String): String {
            return "Not Implemented Exception:\nline -> ${ctx?.start?.line}| ${ctx?.start?.startIndex}:${ctx?.stop?.stopIndex} $name"
        }
    }
}