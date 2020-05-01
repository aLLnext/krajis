package org.timekeeper.grammar

import org.antlr.v4.runtime.ParserRuleContext
import org.timekeeper.grammar.OperatorsUtils.OPERATORS
import org.timekeeper.utils.*
import java.io.File

open class BaseNode(
    val type: String?,
    ctx: ParserRuleContext?,
    var children: ArrayList<BaseNode>? = null
) {
    val start = ctx?.start?.startIndex
    var end = ctx?.stop?.stopIndex


    open fun printNode(indent: String, out: File? = null) {}

    //        println(type)
//        children?.forEachIndexed { index, it ->
//            val isLast = index == (children!!.size - 1)
//            it.printChildNode(indent, isLast)
//        }
    fun printParentNode(indent: String, out: File? = null) {
        printlnWithBuffer(type, out)
        printNode(indent, out)
    }

    @Suppress("NAME_SHADOWING")
    fun printChildNode(indent: String, isLast: Boolean, out: File? = null): String {
        var indent = indent
        printWithBuffer(indent, out)
        if (isLast) {
            printWithBuffer(
                SYMBOL_CORNER,
                out
            )
            indent += SYMBOL_SPACE
        } else {
            printWithBuffer(
                SYMBOL_CROSS,
                out
            )
            indent += SYMBOL_VERTICAL
        }
        return indent
    }
}

class ProgramNode(ctx: ParserRuleContext?, val body: List<BaseNode?>?) : BaseNode("Program", ctx) {

    @Suppress("NAME_SHADOWING")
    override fun printNode(indent: String, out: File?) {
        val indent = super.printChildNode(indent, true, out)
        printlnWithBuffer("body", out)
        body?.forEachIndexed { index, baseNode ->
            baseNode?.printParentNode(super.printChildNode(indent, index == body.size - 1, out), out)
        }
    }
}

class Identifier(ctx: ParserRuleContext?, val name: String? = ctx?.text) : BaseNode("Identifier", ctx) {
    override fun printNode(indent: String, out: File?) {
        super.printChildNode(indent, true, out)
        printlnWithBuffer("name: $name", out)
    }
}

open class Literal(name: String, ctx: ParserRuleContext?) :
    BaseNode(name, ctx) {
    val value = ctx?.text

    override fun printNode(indent: String, out: File?) {
        super.printChildNode(indent, true, out)
        printlnWithBuffer("value: $value", out)
    }
}

class StringLiteral(ctx: ParserRuleContext?) : Literal("StringLiteral", ctx)

class BooleanLiteral(ctx: ParserRuleContext?) : Literal("BooleanLiteral", ctx)

class NumericLiteral(ctx: ParserRuleContext?) : Literal("NumericLiteral", ctx)

class NullLiteral(ctx: ParserRuleContext?) : Literal("NullLiteral", ctx)

class TemplateStringLiteral(ctx: ParserRuleContext?) : Literal("TemplateStringLiteral", ctx)

class VariableDeclaration(ctx: ParserRuleContext?, val declatarions: List<BaseNode?>?, val kind: String?) :
    BaseNode("VariableDeclaration", ctx) {

    override fun printNode(indent: String, out: File?) {
        super.printChildNode(indent, false, out)
        printlnWithBuffer("kind: $kind", out)
        val ind = super.printChildNode(indent, true, out)
        printlnWithBuffer("declarations", out)
        declatarions?.forEachIndexed { index, baseNode ->
            baseNode?.printParentNode(super.printChildNode(ind, index == declatarions.size - 1, out), out)
        }
    }
}

class VariableDeclarator(ctx: ParserRuleContext?, val id: BaseNode?, val init: BaseNode?) :
    BaseNode("VariableDeclarator", ctx) {

    override fun printNode(indent: String, out: File?) {
        var ind = super.printChildNode(indent, false, out)
        printWithBuffer("id: ", out)
        id?.printParentNode(ind, out)
        ind = super.printChildNode(indent, true, out)
        printWithBuffer("init: ", out)
        init?.printParentNode(ind, out)
        if (init == null) {
            printlnWithBuffer("null", out)
        }
    }
}

class FunctionDeclaration(
    ctx: ParserRuleContext?,
    val id: BaseNode?,
    val params: List<BaseNode?>?,
    val body: BaseNode?
) : BaseNode("FunctionDeclaration", ctx) {

    override fun printNode(indent: String, out: File?) {
        var ind = super.printChildNode(indent, false, out)
        printWithBuffer("id: ", out)
        id?.printParentNode(ind, out)
        ind = super.printChildNode(indent, false, out)
        printWithBuffer("body: ", out)
        body?.printParentNode(ind, out)
        ind = super.printChildNode(indent, true, out)
        if (params == null || params.isEmpty()) {
            printlnWithBuffer("params: null", out)
        } else {
            printlnWithBuffer("params", out)
            params.forEachIndexed { index, baseNode ->
                baseNode?.printParentNode(super.printChildNode(ind, index == params.size - 1, out), out)
            }
        }
    }
}

class ExpressionStatement(ctx: ParserRuleContext?, val expression: BaseNode?) :
    BaseNode("ExpressionStatement", ctx) {

    override fun printNode(indent: String, out: File?) {
        val ind = super.printChildNode(indent, true, out)
        printWithBuffer("expression: ", out)
        expression?.printParentNode(ind, out)
    }
}

class AssignmentExpression(ctx: ParserRuleContext?, val left: BaseNode?, val right: BaseNode?) :
    BaseNode("AssignmentExpression", ctx) {

    override fun printNode(indent: String, out: File?) {
        var ind = super.printChildNode(indent, false, out)
        printWithBuffer("left: ", out)
        left?.printParentNode(ind, out)
        ind = super.printChildNode(indent, true, out)
        printWithBuffer("right: ", out)
        right?.printParentNode(ind, out)
    }
}

class EmptyStatement(ctx: ParserRuleContext?) : BaseNode("EmptyStatement", ctx)

class IfStatement(ctx: ParserRuleContext?, val test: BaseNode?, val consequent: BaseNode?, val alternative: BaseNode?) :
    BaseNode("IfStatement", ctx) {

    override fun printNode(indent: String, out: File?) {
        var ind = super.printChildNode(indent, false, out)
        printWithBuffer("test: ", out)
        test?.printParentNode(ind, out)
        ind = super.printChildNode(indent, false, out)
        printWithBuffer("consequent: ", out)
        consequent?.printParentNode(ind, out)
        ind = super.printChildNode(indent, true, out)
        printWithBuffer("alternative: ", out)
        alternative?.printParentNode(ind, out)
        if (alternative == null) {
            printlnWithBuffer("null", out)
        }
    }
}

class SequenceExpression(ctx: ParserRuleContext?, val expressions: List<BaseNode?>?) :
    BaseNode("SequenceExpression", ctx) {

    override fun printNode(indent: String, out: File?) {
        val ind = super.printChildNode(indent, true, out)
        if (expressions == null || expressions.isEmpty()) {
            printlnWithBuffer("expressions: null", out)
        } else {
            printlnWithBuffer("expressions", out)
            expressions.forEachIndexed { index, baseNode ->
                baseNode?.printParentNode(super.printChildNode(ind, index == expressions.size - 1, out), out)
            }
        }
    }
}

class ArrowFunctionExpression(ctx: ParserRuleContext?, val params: List<BaseNode?>?, val body: BaseNode?) :
    BaseNode("ArrowFunctionExpression", ctx) {

    override fun printNode(indent: String, out: File?) {
        var ind = super.printChildNode(indent, false, out)
        if (params == null || params.isEmpty()) {
            printlnWithBuffer("params: null", out)
        } else {
            printlnWithBuffer("params", out)
            params.forEachIndexed { index, baseNode ->
                baseNode?.printParentNode(super.printChildNode(ind, index == params.size - 1, out), out)
            }
        }
        ind = super.printChildNode(indent, true, out)
        printWithBuffer("body: ", out)
        body?.printParentNode(ind, out)
    }
}

class BlockStatement(ctx: ParserRuleContext?, val body: List<BaseNode?>?) : BaseNode("BlockStatement", ctx) {

    override fun printNode(indent: String, out: File?) {
        val ind = super.printChildNode(indent, true, out)
        if (body == null || body.isEmpty()) {
            printlnWithBuffer("body: null", out)
        } else {
            printlnWithBuffer("body", out)
            body.forEachIndexed { index, baseNode ->
                baseNode?.printParentNode(super.printChildNode(ind, index == body.size - 1, out), out)
            }
        }
    }
}

class ArrayExpression(ctx: ParserRuleContext?, val elements: List<BaseNode?>?) : BaseNode("ArrayExpression", ctx) {

    override fun printNode(indent: String, out: File?) {
        val ind = super.printChildNode(indent, true, out)
        if (elements == null || elements.isEmpty()) {
            printlnWithBuffer("elements: null", out)
        } else {
            printlnWithBuffer("elements", out)
            elements.forEachIndexed { index, baseNode ->
                if (baseNode == null) {
                    super.printChildNode(ind, index == elements.size - 1, out)
                    printlnWithBuffer("null", out)
                } else {
                    baseNode.printParentNode(super.printChildNode(ind, index == elements.size - 1, out), out)
                }
            }
        }
    }
}

class ObjectExpression(ctx: ParserRuleContext?, val properties: List<BaseNode?>?) : BaseNode("ObjectExpression", ctx) {

    override fun printNode(indent: String, out: File?) {
        val ind = super.printChildNode(indent, true, out)
        if (properties == null || properties.isEmpty()) {
            printlnWithBuffer("properties: null", out)
        } else {
            printlnWithBuffer("properties", out)
            properties.forEachIndexed { index, baseNode ->
                baseNode?.printParentNode(super.printChildNode(ind, index == properties.size - 1, out), out)
            }
        }
    }
}

class FunctionExpression(ctx: ParserRuleContext?, val params: List<BaseNode?>?, val body: BaseNode?) :
    BaseNode("FunctionExpression", ctx) {

    override fun printNode(indent: String, out: File?) {
        var ind = super.printChildNode(indent, false, out)
        if (params == null || params.isEmpty()) {
            printlnWithBuffer("params: null", out)
        } else {
            printlnWithBuffer("params", out)
            params.forEachIndexed { index, baseNode ->
                baseNode?.printParentNode(super.printChildNode(ind, index == params.size - 1, out), out)
            }
        }
        ind = super.printChildNode(indent, true, out)
        printWithBuffer("body: ", out)
        body?.printParentNode(ind, out)
    }
}

class CallExpression(ctx: ParserRuleContext?, val callee: BaseNode?, val arguments: List<BaseNode?>?) :
    BaseNode("CallExpression", ctx) {

    override fun printNode(indent: String, out: File?) {
        var ind = super.printChildNode(indent, false, out)
        printWithBuffer("callee: ", out)
        callee?.printParentNode(ind, out)
        ind = super.printChildNode(indent, true, out)
        if (arguments == null || arguments.isEmpty()) {
            printlnWithBuffer("arguments: null", out)
        } else {
            printlnWithBuffer("arguments", out)
            arguments.forEachIndexed { index, baseNode ->
                baseNode?.printParentNode(super.printChildNode(ind, index == arguments.size - 1, out), out)
            }
        }
    }
}

class MemberExpression(ctx: ParserRuleContext?, val obj: BaseNode?, val property: BaseNode?, val kind: String?) :
    BaseNode("MemberExpression", ctx) {

    override fun printNode(indent: String, out: File?) {
        var ind = super.printChildNode(indent, false, out)
        printlnWithBuffer("kind: $kind", out)
        ind = super.printChildNode(indent, false, out)
        printWithBuffer("obj: ", out)
        obj?.printParentNode(ind, out)
        ind = super.printChildNode(indent, true, out)
        printWithBuffer("property: ", out)
        property?.printParentNode(ind, out)
    }
}

class BreakStatement(ctx: ParserRuleContext?) : BaseNode("BreakStatement", ctx)

class ReturnStatement(ctx: ParserRuleContext?, val argument: BaseNode?) : BaseNode("ReturnStatement", ctx) {
    override fun printNode(indent: String, out: File?) {
        val ind = super.printChildNode(indent, true, out)
        printWithBuffer("argument: ", out)
        argument?.printParentNode(ind, out)
        if (argument == null) {
            printlnWithBuffer("null", out)
        }
    }
}

class WhileStatement(ctx: ParserRuleContext?, val test: BaseNode?, val body: BaseNode?) :
    BaseNode("WhileStatement", ctx) {

    override fun printNode(indent: String, out: File?) {
        var ind = super.printChildNode(indent, false, out)
        printWithBuffer("test: ", out)
        test?.printParentNode(ind, out)
        ind = super.printChildNode(indent, true, out)
        printWithBuffer("body: ", out)
        body?.printParentNode(ind, out)
    }
}

class ContinueStatement(ctx: ParserRuleContext?) : BaseNode("ContinueStatement", ctx)

class UnaryExpression(ctx: ParserRuleContext?, val operator: OPERATORS?, val argument: BaseNode?) :
    BaseNode("UnaryExpression", ctx) {

    override fun printNode(indent: String, out: File?) {
        super.printChildNode(indent, false, out)
        printlnWithBuffer("operator: ${operator?.value}", out)
        val ind = super.printChildNode(indent, true, out)
        printWithBuffer("argument: ", out)
        argument?.printParentNode(ind, out)
    }
}

class BinaryExpression(ctx: ParserRuleContext?, val left: BaseNode?, val operator: OPERATORS?, val right: BaseNode?) :
    BaseNode("BinaryExpression", ctx) {

    override fun printNode(indent: String, out: File?) {
        var ind = super.printChildNode(indent, false, out)
        printWithBuffer("left: ", out)
        left?.printParentNode(ind, out)
        super.printChildNode(indent, false, out)
        printlnWithBuffer("operator: ${operator?.value}", out)
        ind = super.printChildNode(indent, true, out)
        printWithBuffer("right: ", out)
        right?.printParentNode(ind, out)
    }
}

class LogicalExpression(ctx: ParserRuleContext?, val left: BaseNode?, val operator: OPERATORS?, val right: BaseNode?) :
    BaseNode("LogicalExpression", ctx) {

    override fun printNode(indent: String, out: File?) {
        var ind = super.printChildNode(indent, false, out)
        printWithBuffer("left: ", out)
        left?.printParentNode(ind, out)
        super.printChildNode(indent, false, out)
        printlnWithBuffer("operator: ${operator?.value}", out)
        ind = super.printChildNode(indent, true, out)
        printWithBuffer("right: ", out)
        right?.printParentNode(ind, out)
    }
}

class Property(ctx: ParserRuleContext?, val key: BaseNode?, val value: BaseNode?) : BaseNode("Property", ctx) {
    val kind = "init"

    override fun printNode(indent: String, out: File?) {
        var ind = super.printChildNode(indent, false, out)
        printlnWithBuffer("kind: $kind", out)
        ind = super.printChildNode(indent, false, out)
        printWithBuffer("key: ", out)
        key?.printParentNode(ind, out)
        ind = super.printChildNode(indent, true, out)
        printWithBuffer("value: ", out)
        value?.printParentNode(ind, out)
    }
}

class DoWhileStatement(ctx: ParserRuleContext?, val body: BaseNode?, val test: BaseNode?) :
    BaseNode("DoWhileStatement", ctx) {

    override fun printNode(indent: String, out: File?) {
        var ind = super.printChildNode(indent, false, out)
        printWithBuffer("body: ", out)
        body?.printParentNode(ind, out)
        ind = super.printChildNode(indent, true, out)
        printWithBuffer("test: ", out)
        test?.printParentNode(ind, out)
    }
}

class OperatorsUtils() {

    companion object {
        val OPERATORS_SIGN = HashMap<String, OPERATORS>(
            mapOf(
                Pair("-", OPERATORS.Minus),
                Pair("+", OPERATORS.Plus),
                Pair("*", OPERATORS.Multiply),
                Pair("/", OPERATORS.Divide),
                Pair("&&", OPERATORS.LogicalAnd),
                Pair("||", OPERATORS.LogicalOr),
                Pair("===", OPERATORS.Equals),
                Pair("==", OPERATORS.Equals),
                Pair("!=", OPERATORS.NotEquals),
                Pair("!==", OPERATORS.NotEquals),
                Pair(">", OPERATORS.More),
                Pair(">=", OPERATORS.MoreOrEqual),
                Pair("<", OPERATORS.Less),
                Pair("<=", OPERATORS.LessOrEqual),
                Pair("!", OPERATORS.LogicalNot),
                Pair("~", OPERATORS.BitwiseNot),
                Pair("<<", OPERATORS.LeftShift),
                Pair(">>>", OPERATORS.RightShift),
                Pair(">>", OPERATORS.ArithmeticRightShift),
                Pair("%", OPERATORS.Remainder),
                Pair("|", OPERATORS.BitwiseOr),
                Pair("&", OPERATORS.BitwiseAnd),
                Pair("^", OPERATORS.BitwiseXor)
            )
        )
    }

    enum class OPERATORS(val value: String) {
        Minus("-"),
        Plus("+"),
        BitwiseNot("~"),
        Delete("delete"),
        LogicalNot("!"),

        Multiply("*"),
        Divide("/"),
        Equals("==="),
        NotEquals("!=="),
        Less("<"),
        LessOrEqual("<="),
        More(">"),
        MoreOrEqual(">="),
        LeftShift("<<"),
        RightShift(">>>"),
        ArithmeticRightShift(">>"),
        Remainder("%"),
        BitwiseOr("|"),
        BitwiseAnd("&"),
        BitwiseXor("^"),
        LogicalAnd("&&"),
        LogicalOr("||")
    }
}
