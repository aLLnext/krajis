package org.timekeeper

import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import org.timekeeper.utils.ConvertTreeVisitor
import org.timekeeper.utils.TEST_PATH
import java.io.File
import kotlin.test.assertEquals

class TestingSystem {

    private val convertVisitor = ConvertTreeVisitor()

    @TestFactory
    fun parserTests(): Collection<DynamicTest> {

        val tests = ArrayList<DynamicTest>()
        val correctAnswers = ArrayList<String>()
        val testAnswers = ArrayList<String>()

        File(TEST_PATH).walkTopDown().forEachIndexed { index, it ->
            if (it.isFile.and(".js".toRegex().containsMatchIn(it.name))) {
                val correctFile = File("${it.parent}\\tree_${it.name.subSequence(0, it.name.length - 2)}.res")
                val tempFile = File("tempfile.res")
                tempFile.delete()
                val parser = Initialization().createLexerAndParser(it.readText())
                val result = parser.program().accept(convertVisitor)
                result?.printParentNode("", tempFile)
                correctAnswers.add(correctFile.readText())
                testAnswers.add(correctFile.readText())
                val data = tempFile.readText()
                tests.add(
                    DynamicTest.dynamicTest(
                        "TEST ${it.parentFile.name}/${it.name.subSequence(0, it.name.length - 3)}"
                    ) {
                        assertEquals(correctFile.readText(), data)
                        println(it.readText())
                        println()
                        println(data)
                    }
                )
                tempFile.delete()
            }

        }

        return tests
    }
}
