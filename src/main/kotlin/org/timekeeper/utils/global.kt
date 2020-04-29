package org.timekeeper.utils

import org.antlr.v4.runtime.RuleContext
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.TerminalNode
import java.io.BufferedWriter
import java.io.File
import java.io.PrintWriter

const val REPORT_SYNTAX_ERRORS = true
const val UPDATE_TREE = false
const val TEST_PATH = "src/test/kotlin/org/timekeeper/test_suits/valid"

const val SYMBOL_CROSS = " ├─"
const val SYMBOL_CORNER = " └─"
const val SYMBOL_VERTICAL = " │ "
const val SYMBOL_SPACE = "   "

fun ParseTree.printNode(indent: String, rules: List<String>) {
    println(getNodeText(rules))

    for (i in 0 until childCount) {
        val child = getChild(i)
        val isLast = i == (childCount - 1)
        child.printChildNode(indent, isLast, rules)
    }
}

@Suppress("NAME_SHADOWING")
fun ParseTree.printChildNode(indent: String, isLast: Boolean, rules: List<String>) {
    var indent = indent
    print(indent)
    if (isLast) {
        print(SYMBOL_CORNER)
        indent += SYMBOL_SPACE
    } else {
        print(SYMBOL_CROSS)
        indent += SYMBOL_VERTICAL
    }
    printNode(indent, rules)
}

fun ParseTree.getNodeText(rules: List<String>): String? {
    when (this) {
        is RuleContext -> {
            val ruleIndex = this.ruleContext.ruleIndex
            return rules[ruleIndex]
        }
        is ErrorNode -> {
            return this.toString()
        }
        is TerminalNode -> {
            val token = this.symbol
            return token.text
        }
    }
    return null
}

fun printlnWithBuffer(string: String?, out: File? = null) {
    if (out != null) {
        string?.let {
            out.appendText(string + "\n")
        }
    } else {
        println(string)
    }
}

fun printWithBuffer(string: String?, out: File? = null) {
    if (out != null) {
        string?.let {
            out.appendText(string)
        }
    } else {
        print(string)
    }
}