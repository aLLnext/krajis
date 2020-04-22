package org.timekeeper.grammar

import JavaScriptParserBaseVisitor
import org.antlr.v4.runtime.ParserRuleContext
import org.timekeeper.grammar.OperatorsUtils.*
import org.timekeeper.grammar.OperatorsUtils.Companion.OPERATORS_SIGN


class ConvertTreeVisitor() : JavaScriptParserBaseVisitor<BaseNode?>() {
    private fun getChildren(ctx: ParserRuleContext?): List<BaseNode?>? {
        ctx?.let {
            return ctx.children?.map { it.accept(this) }
        }
        return null
    }

    override fun visitProgram(ctx: JavaScriptParser.ProgramContext?): BaseNode? {
        return ProgramNode(ctx, getChildren(ctx?.sourceElements()))
    }

    override fun visitSourceElement(ctx: JavaScriptParser.SourceElementContext?): BaseNode? {
        return ctx?.statement()?.accept(this)
    }

    override fun visitStatement(ctx: JavaScriptParser.StatementContext?): BaseNode? {
        return ctx?.getChild(0)?.accept(this)
    }

    override fun visitVariableStatement(ctx: JavaScriptParser.VariableStatementContext?): BaseNode? {
        return ctx?.variableDeclarationList()?.accept(this)
    }

    override fun visitVariableDeclarationList(ctx: JavaScriptParser.VariableDeclarationListContext?): BaseNode? {
        return VariableDeclaration(
            ctx,
            declatarions = (ctx?.variableDeclaration()?.map { it.accept(this) }),
            kind = ctx?.varModifier()?.text
        )
    }

    override fun visitVariableDeclaration(ctx: JavaScriptParser.VariableDeclarationContext?): BaseNode? {
        return VariableDeclarator(
            ctx,
            id = ctx?.assignable()?.accept(this),
            init = ctx?.singleExpression()?.accept(this)
        )
    }

    override fun visitExpressionStatement(ctx: JavaScriptParser.ExpressionStatementContext?): BaseNode? {
        return ExpressionStatement(
            ctx,
            expression = ctx?.expressionSequence()?.accept(this)
        )
    }

    override fun visitIfStatement(ctx: JavaScriptParser.IfStatementContext?): BaseNode? {
        return IfStatement(
            ctx,
            test = ctx?.expressionSequence()?.accept(this),
            consequent = ctx?.statement(0)?.accept(this),
            alternative = ctx?.statement(1)?.accept(this)
        )
    }

    override fun visitWhileStatement(ctx: JavaScriptParser.WhileStatementContext?): BaseNode? {
        return WhileStatement(
            ctx,
            test = ctx?.expressionSequence()?.accept(this),
            body = ctx?.statement()?.accept(this)
        )
    }

    override fun visitExpressionSequence(ctx: JavaScriptParser.ExpressionSequenceContext?): BaseNode? {
        if (ctx?.singleExpression()?.size == 1) {
            return ctx.singleExpression(0)?.accept(this)
        }
        return SequenceExpression(
            ctx,
            expressions = ctx?.singleExpression()?.map { it.accept(this) })
    }

    override fun visitLogicalAndExpression(ctx: JavaScriptParser.LogicalAndExpressionContext?): BaseNode? {
        return LogicalExpression(
            ctx,
            left = ctx?.singleExpression(0)?.accept(this),
            operator = OPERATORS.LogicalAnd,
            right = ctx?.singleExpression(1)?.accept(this)
        )
    }

    override fun visitLogicalOrExpression(ctx: JavaScriptParser.LogicalOrExpressionContext?): BaseNode? {
        return LogicalExpression(
            ctx,
            left = ctx?.singleExpression(0)?.accept(this),
            operator = OPERATORS.LogicalOr,
            right = ctx?.singleExpression(1)?.accept(this)
        )
    }

    override fun visitEqualityExpression(ctx: JavaScriptParser.EqualityExpressionContext?): BaseNode? {
        return BinaryExpression(
            ctx,
            left = ctx?.singleExpression(0)?.accept(this),
            operator = OPERATORS_SIGN[ctx?.getChild(1)?.text],
            right = ctx?.singleExpression(1)?.accept(this)
        )
    }

    override fun visitRelationalExpression(ctx: JavaScriptParser.RelationalExpressionContext?): BaseNode? {
        return BinaryExpression(
            ctx,
            left = ctx?.singleExpression(0)?.accept(this),
            operator = OPERATORS_SIGN[ctx?.getChild(1)?.text],
            right = ctx?.singleExpression(1)?.accept(this)
        )
    }

    override fun visitIdentifierExpression(ctx: JavaScriptParser.IdentifierExpressionContext?): BaseNode? {
        return Identifier(ctx)
    }

    override fun visitIdentifierName(ctx: JavaScriptParser.IdentifierNameContext?): BaseNode? {
        return Identifier(ctx)
    }

    override fun visitAssignmentExpression(ctx: JavaScriptParser.AssignmentExpressionContext?): BaseNode? {
        return AssignmentExpression(
            ctx,
            left = ctx?.singleExpression(0)?.accept(this),
            right = ctx?.singleExpression(1)?.accept(this)
        )
    }

    override fun visitLiteralExpression(ctx: JavaScriptParser.LiteralExpressionContext?): BaseNode? {
        return ctx?.literal()?.accept(this)
    }

    override fun visitAssignable(ctx: JavaScriptParser.AssignableContext?): BaseNode? {
        return Identifier(ctx)
    }

    override fun visitLiteral(ctx: JavaScriptParser.LiteralContext?): BaseNode? {
        if (ctx?.BooleanLiteral() != null) {
            return BooleanLiteral(ctx)
        }
        if (ctx?.NullLiteral() != null) {
            return NullLiteral(ctx)
        }
        if (ctx?.StringLiteral() != null) {
            return StringLiteral(ctx)
        }
        if (ctx?.TemplateStringLiteral() != null) {
            return TemplateStringLiteral(ctx)
        }
        return NumericLiteral(ctx)
    }

    override fun visitContinueStatement(ctx: JavaScriptParser.ContinueStatementContext?): BaseNode? {
        return ContinueStatement(ctx)
    }

    override fun visitBreakStatement(ctx: JavaScriptParser.BreakStatementContext?): BaseNode? {
        return BreakStatement(ctx)
    }

    private fun visitBinaryExpression(ctx: ParserRuleContext?): BaseNode? {
        return BinaryExpression(
            ctx,
            left = ctx?.children?.get(0)?.accept(this),
            operator = OPERATORS_SIGN[ctx?.getChild(1)?.text],
            right = ctx?.children?.get(2)?.accept(this)
        )
    }

    override fun visitAdditiveExpression(ctx: JavaScriptParser.AdditiveExpressionContext?): BaseNode? {
        return visitBinaryExpression(ctx)
    }

    override fun visitMultiplicativeExpression(ctx: JavaScriptParser.MultiplicativeExpressionContext?): BaseNode? {
        return visitBinaryExpression(ctx)
    }

    override fun visitBitOrExpression(ctx: JavaScriptParser.BitOrExpressionContext?): BaseNode? {
        return visitBinaryExpression(ctx)
    }

    override fun visitBitXOrExpression(ctx: JavaScriptParser.BitXOrExpressionContext?): BaseNode? {
        return visitBinaryExpression(ctx)
    }

    override fun visitNotExpression(ctx: JavaScriptParser.NotExpressionContext?): BaseNode? {
        return UnaryExpression(
            ctx,
            operator = OPERATORS.LogicalNot,
            argument = ctx?.singleExpression()?.accept(this)
        )
    }

    override fun visitBitNotExpression(ctx: JavaScriptParser.BitNotExpressionContext?): BaseNode? {
        return UnaryExpression(
            ctx,
            operator = OPERATORS.BitwiseNot,
            argument = ctx?.singleExpression()?.accept(this)
        )
    }

    override fun visitBitShiftExpression(ctx: JavaScriptParser.BitShiftExpressionContext?): BaseNode? {
        return visitBinaryExpression(ctx)
    }

    override fun visitBitAndExpression(ctx: JavaScriptParser.BitAndExpressionContext?): BaseNode? {
        return visitBinaryExpression(ctx)
    }

    override fun visitParenthesizedExpression(ctx: JavaScriptParser.ParenthesizedExpressionContext?): BaseNode? {
        return ExpressionStatement(
            ctx,
            expression = ctx?.expressionSequence()?.accept(this)
        )
    }

    override fun visitArrayLiteral(ctx: JavaScriptParser.ArrayLiteralContext?): BaseNode? {
        return ArrayExpression(
            ctx,
            elements = getChildren(ctx?.elementList())
        )
    }

    override fun visitObjectLiteralExpression(ctx: JavaScriptParser.ObjectLiteralExpressionContext?): BaseNode? {
        return ObjectExpression(
            ctx,
            properties = ctx?.objectLiteral()?.propertyAssignment()?.map { it?.accept(this) })
    }

    override fun visitPropertyExpressionAssignment(ctx: JavaScriptParser.PropertyExpressionAssignmentContext?): BaseNode? {
        return Property(
            ctx,
            key = ctx?.propertyName()?.accept(this),
            value = ctx?.singleExpression()?.accept(this)
        )
    }

    override fun visitEmptyStatement(ctx: JavaScriptParser.EmptyStatementContext?): BaseNode? {
        return EmptyStatement(ctx)
    }

    override fun visitFunctionDeclaration(ctx: JavaScriptParser.FunctionDeclarationContext?): BaseNode? {
        return FunctionDeclaration(
            ctx,
            id = Identifier(ctx, ctx?.Identifier()?.text),
            params = ctx?.formalParameterList()?.formalParameterArg()?.map { it.accept(this) },
            body = ctx?.functionBody()?.accept(this)
        )
    }

    override fun visitReturnStatement(ctx: JavaScriptParser.ReturnStatementContext?): BaseNode? {
        return ReturnStatement(
            ctx,
            argument = ctx?.expressionSequence()?.accept(this)
        )
    }

    override fun visitFunctionBody(ctx: JavaScriptParser.FunctionBodyContext?): BaseNode? {
        return BlockStatement(ctx, body = getChildren(ctx?.sourceElements()))
    }

    override fun visitFormalParameterArg(ctx: JavaScriptParser.FormalParameterArgContext?): BaseNode? {
        return ctx?.assignable()?.accept(this)
    }

    override fun visitBlock(ctx: JavaScriptParser.BlockContext?): BaseNode? {
        return BlockStatement(ctx, body = getChildren(ctx?.statementList()))
    }

    override fun visitFunctionExpression(ctx: JavaScriptParser.FunctionExpressionContext?): BaseNode? {
        return ctx?.anoymousFunction()?.accept(this)
    }

    override fun visitAnoymousFunctionDecl(ctx: JavaScriptParser.AnoymousFunctionDeclContext?): BaseNode? {
        return FunctionExpression(
            ctx,
            params = getChildren(ctx?.formalParameterList()),
            body = ctx?.functionBody()?.accept(this)
        )
    }

    override fun visitDeleteExpression(ctx: JavaScriptParser.DeleteExpressionContext?): BaseNode? {
        return UnaryExpression(
            ctx,
            OPERATORS.Delete,
            argument = ctx?.singleExpression()?.accept(this)
        )
    }

    override fun visitMemberDotExpression(ctx: JavaScriptParser.MemberDotExpressionContext?): BaseNode? {
        return MemberExpression(
            ctx,
            obj = ctx?.singleExpression()?.accept(this),
            property = ctx?.identifierName()?.accept(this)
        )
    }

    override fun visitMemberIndexExpression(ctx: JavaScriptParser.MemberIndexExpressionContext?): BaseNode? {
        return MemberExpression(
            ctx,
            obj = ctx?.singleExpression()?.accept(this),
            property = ctx?.expressionSequence()?.accept(this)
        )
    }


    override fun visitArgumentsExpression(ctx: JavaScriptParser.ArgumentsExpressionContext?): BaseNode? {
        return CallExpression(
            ctx,
            callee = ctx?.singleExpression()?.accept(this),
            arguments = ctx?.arguments()?.argument()?.map { it.accept(this) }
        )
    }

    override fun visitUnaryMinusExpression(ctx: JavaScriptParser.UnaryMinusExpressionContext?): BaseNode? {
        return UnaryExpression(
            ctx,
            operator = OPERATORS.Minus,
            argument = ctx?.singleExpression()?.accept(this)
        )
    }

    override fun visitUnaryPlusExpression(ctx: JavaScriptParser.UnaryPlusExpressionContext?): BaseNode? {
        return UnaryExpression(
            ctx,
            operator = OPERATORS.Plus,
            argument = ctx?.singleExpression()?.accept(this)
        )
    }

    override fun visitDoStatement(ctx: JavaScriptParser.DoStatementContext?): BaseNode? {
        return DoWhileStatement(
            ctx,
            body = ctx?.statement()?.accept(this),
            test = ctx?.expressionSequence()?.accept(this)
        )
    }

    override fun visitPropertyName(ctx: JavaScriptParser.PropertyNameContext?): BaseNode? {
        if (ctx?.StringLiteral() != null) {
            return StringLiteral(ctx)
        }
        if (ctx?.numericLiteral() != null) {
            return NumericLiteral(ctx)
        }
        if (ctx?.identifierName() != null) {
            return ctx.identifierName()?.accept(this)
        }
        return ctx?.singleExpression()?.accept(this)
    }

    override fun visitArrowFunction(ctx: JavaScriptParser.ArrowFunctionContext?): BaseNode? {
        return ArrowFunctionExpression(
            ctx,
            params = getChildren(ctx?.arrowFunctionParameters()),
            body = ctx?.arrowFunctionBody()?.accept(this)
        )
    }
}