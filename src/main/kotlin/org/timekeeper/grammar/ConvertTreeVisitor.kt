package org.timekeeper.grammar

import JavaScriptParserBaseVisitor
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.TerminalNode
import org.timekeeper.grammar.OperatorsUtils.*
import org.timekeeper.grammar.OperatorsUtils.Companion.OPERATORS_SIGN
import org.timekeeper.grammar.exceptions.InvalidOperationException
import org.timekeeper.grammar.exceptions.NotImplementedException


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

    override fun visitPowerExpression(ctx: JavaScriptParser.PowerExpressionContext?): BaseNode? {
        return visitBinaryExpression(ctx)
    }

    override fun visitParenthesizedExpression(ctx: JavaScriptParser.ParenthesizedExpressionContext?): BaseNode? {
        return ExpressionStatement(
            ctx,
            expression = ctx?.expressionSequence()?.accept(this)
        )
    }

    override fun visitArrayLiteral(ctx: JavaScriptParser.ArrayLiteralContext?): BaseNode? {
        val elements = ArrayList<BaseNode?>()

        val children = ctx?.elementList()?.children
        children?.let {
            var lastIsNull = true
            for (i in 0 until children.size) {
                val res = children[i].accept(this)
                if (lastIsNull) {
                    elements.add(res)
                }
                lastIsNull = res == null
            }
        }
        return ArrayExpression(
            ctx,
            elements = elements
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
            property = ctx?.identifierName()?.accept(this),
            kind = "DotMember"
        )
    }

    override fun visitMemberIndexExpression(ctx: JavaScriptParser.MemberIndexExpressionContext?): BaseNode? {
        return MemberExpression(
            ctx,
            obj = ctx?.singleExpression()?.accept(this),
            property = ctx?.expressionSequence()?.accept(this),
            kind = "IndexMember"
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
            params = ctx?.arrowFunctionParameters()?.formalParameterList()?.formalParameterArg()?.map { it.accept(this) },
            body = ctx?.arrowFunctionBody()?.accept(this)
        )
    }

    override fun visitArrowFunctionBody(ctx: JavaScriptParser.ArrowFunctionBodyContext?): BaseNode? {
        return ctx?.singleExpression()?.accept(this)
    }

    override fun visitImportStatement(ctx: JavaScriptParser.ImportStatementContext?): BaseNode? {
        throw NotImplementedException(ctx, "ImportStatement")
    }

    override fun visitImportFromBlock(ctx: JavaScriptParser.ImportFromBlockContext?): BaseNode? {
        throw NotImplementedException(ctx, "ImportFromBlock")
    }

    override fun visitModuleItems(ctx: JavaScriptParser.ModuleItemsContext?): BaseNode? {
        throw NotImplementedException(ctx, "ModuleItems")
    }

    override fun visitImportDefault(ctx: JavaScriptParser.ImportDefaultContext?): BaseNode? {
        throw NotImplementedException(ctx, "ImportDefault")
    }

    override fun visitImportNamespace(ctx: JavaScriptParser.ImportNamespaceContext?): BaseNode? {
        throw NotImplementedException(ctx, "ImportNamespace")
    }

    override fun visitImportFrom(ctx: JavaScriptParser.ImportFromContext?): BaseNode? {
        throw NotImplementedException(ctx, "ImportFrom")
    }

    override fun visitAliasName(ctx: JavaScriptParser.AliasNameContext?): BaseNode? {
        throw NotImplementedException(ctx, "AliasName")
    }

    override fun visitExportDeclaration(ctx: JavaScriptParser.ExportDeclarationContext?): BaseNode? {
        throw NotImplementedException(ctx, "ExportDeclaration")
    }

    override fun visitExportDefaultDeclaration(ctx: JavaScriptParser.ExportDefaultDeclarationContext?): BaseNode? {
        throw NotImplementedException(
            ctx,
            "ExportDefaultDeclaration"
        )
    }

    override fun visitExportFromBlock(ctx: JavaScriptParser.ExportFromBlockContext?): BaseNode? {
        throw NotImplementedException(ctx, "ExportFromBlock")
    }

    override fun visitDeclaration(ctx: JavaScriptParser.DeclarationContext?): BaseNode? {
        throw NotImplementedException(ctx, "Declaration")
    }

    override fun visitForStatement(ctx: JavaScriptParser.ForStatementContext?): BaseNode? {
        throw NotImplementedException(ctx, "ForStatement")
    }

    override fun visitForInStatement(ctx: JavaScriptParser.ForInStatementContext?): BaseNode? {
        throw NotImplementedException(ctx, "ForInStatement")
    }

    override fun visitForOfStatement(ctx: JavaScriptParser.ForOfStatementContext?): BaseNode? {
        throw NotImplementedException(ctx, "ForOfStatement")
    }

    override fun visitVarModifier(ctx: JavaScriptParser.VarModifierContext?): BaseNode? {
        throw InvalidOperationException(ctx, "ForOfStatement")
    }

    override fun visitYieldStatement(ctx: JavaScriptParser.YieldStatementContext?): BaseNode? {
        throw NotImplementedException(ctx, "YieldStatement")
    }

    override fun visitWithStatement(ctx: JavaScriptParser.WithStatementContext?): BaseNode? {
        throw NotImplementedException(ctx, "WithStatement")
    }

    override fun visitSwitchStatement(ctx: JavaScriptParser.SwitchStatementContext?): BaseNode? {
        throw NotImplementedException(ctx, "SwitchStatement")
    }

    override fun visitCaseBlock(ctx: JavaScriptParser.CaseBlockContext?): BaseNode? {
        throw NotImplementedException(ctx, "CaseBlock")
    }

    override fun visitCaseClauses(ctx: JavaScriptParser.CaseClausesContext?): BaseNode? {
        throw NotImplementedException(ctx, "CaseClauses")
    }

    override fun visitCaseClause(ctx: JavaScriptParser.CaseClauseContext?): BaseNode? {
        throw NotImplementedException(ctx, "CaseClause")
    }

    override fun visitDefaultClause(ctx: JavaScriptParser.DefaultClauseContext?): BaseNode? {
        throw NotImplementedException(ctx, "DefaultClause")
    }

    override fun visitLabelledStatement(ctx: JavaScriptParser.LabelledStatementContext?): BaseNode? {
        throw NotImplementedException(ctx, "LabelledStatement")
    }

    override fun visitThrowStatement(ctx: JavaScriptParser.ThrowStatementContext?): BaseNode? {
        throw NotImplementedException(ctx, "ThrowStatement")
    }

    override fun visitTryStatement(ctx: JavaScriptParser.TryStatementContext?): BaseNode? {
        throw NotImplementedException(ctx, "TryStatement")
    }

    override fun visitCatchProduction(ctx: JavaScriptParser.CatchProductionContext?): BaseNode? {
        throw NotImplementedException(ctx, "CatchProduction")
    }

    override fun visitFinallyProduction(ctx: JavaScriptParser.FinallyProductionContext?): BaseNode? {
        throw NotImplementedException(ctx, "FinallyProduction")
    }

    override fun visitDebuggerStatement(ctx: JavaScriptParser.DebuggerStatementContext?): BaseNode? {
        throw NotImplementedException(ctx, "DebuggerStatement")
    }

    override fun visitClassDeclaration(ctx: JavaScriptParser.ClassDeclarationContext?): BaseNode? {
        throw NotImplementedException(ctx, "ClassDeclaration")
    }

    override fun visitClassTail(ctx: JavaScriptParser.ClassTailContext?): BaseNode? {
        throw NotImplementedException(ctx, "ClassTail")
    }

    override fun visitClassElement(ctx: JavaScriptParser.ClassElementContext?): BaseNode? {
        throw NotImplementedException(ctx, "ClassElement")
    }

    override fun visitMethodDefinition(ctx: JavaScriptParser.MethodDefinitionContext?): BaseNode? {
        throw NotImplementedException(ctx, "MethodDefinition")
    }

    override fun visitFormalParameterList(ctx: JavaScriptParser.FormalParameterListContext?): BaseNode? {
        throw InvalidOperationException(ctx, "FormalParameterList")
    }

    override fun visitLastFormalParameterArg(ctx: JavaScriptParser.LastFormalParameterArgContext?): BaseNode? {
        throw NotImplementedException(
            ctx,
            "LastFormalParameterArg"
        )
    }

    override fun visitElementList(ctx: JavaScriptParser.ElementListContext?): BaseNode? {
        throw NotImplementedException(ctx, "ElementList")
    }

    override fun visitObjectLiteral(ctx: JavaScriptParser.ObjectLiteralContext?): BaseNode? {
        throw InvalidOperationException(ctx, "ObjectLiteral")
    }

    override fun visitComputedPropertyExpressionAssignment(ctx: JavaScriptParser.ComputedPropertyExpressionAssignmentContext?): BaseNode? {
        throw NotImplementedException(
            ctx,
            "ComputedPropertyExpressionAssignment"
        )
    }

    override fun visitFunctionProperty(ctx: JavaScriptParser.FunctionPropertyContext?): BaseNode? {
        throw InvalidOperationException(ctx, "FunctionProperty")
    }

    override fun visitPropertyGetter(ctx: JavaScriptParser.PropertyGetterContext?): BaseNode? {
        throw NotImplementedException(ctx, "PropertyGetter")
    }

    override fun visitPropertySetter(ctx: JavaScriptParser.PropertySetterContext?): BaseNode? {
        throw NotImplementedException(ctx, "PropertySetter")
    }

    override fun visitPropertyShorthand(ctx: JavaScriptParser.PropertyShorthandContext?): BaseNode? {
        throw NotImplementedException(ctx, "PropertyShorthand")
    }

    override fun visitArguments(ctx: JavaScriptParser.ArgumentsContext?): BaseNode? {
        throw NotImplementedException(ctx, "Arguments")
    }

    override fun visitTemplateStringExpression(ctx: JavaScriptParser.TemplateStringExpressionContext?): BaseNode? {
        throw NotImplementedException(
            ctx,
            "TemplateStringExpression"
        )
    }

    override fun visitTernaryExpression(ctx: JavaScriptParser.TernaryExpressionContext?): BaseNode? {
        throw NotImplementedException(ctx, "TernaryExpression")
    }

    override fun visitPreIncrementExpression(ctx: JavaScriptParser.PreIncrementExpressionContext?): BaseNode? {
        throw NotImplementedException(
            ctx,
            "PreIncrementExpression"
        )
    }

    override fun visitMetaExpression(ctx: JavaScriptParser.MetaExpressionContext?): BaseNode? {
        throw NotImplementedException(ctx, "MetaExpression")
    }

    override fun visitInExpression(ctx: JavaScriptParser.InExpressionContext?): BaseNode? {
        throw NotImplementedException(ctx, "InExpression")
    }

    override fun visitPreDecreaseExpression(ctx: JavaScriptParser.PreDecreaseExpressionContext?): BaseNode? {
        throw NotImplementedException(ctx, "PreDecreaseExpression")
    }

    override fun visitAwaitExpression(ctx: JavaScriptParser.AwaitExpressionContext?): BaseNode? {
        throw NotImplementedException(ctx, "AwaitExpression")
    }

    override fun visitThisExpression(ctx: JavaScriptParser.ThisExpressionContext?): BaseNode? {
        throw NotImplementedException(ctx, "ThisExpression")
    }

    override fun visitPostDecreaseExpression(ctx: JavaScriptParser.PostDecreaseExpressionContext?): BaseNode? {
        throw NotImplementedException(
            ctx,
            "PostDecreaseExpression"
        )
    }

    override fun visitTypeofExpression(ctx: JavaScriptParser.TypeofExpressionContext?): BaseNode? {
        throw NotImplementedException(ctx, "TypeofExpression")
    }

    override fun visitInstanceofExpression(ctx: JavaScriptParser.InstanceofExpressionContext?): BaseNode? {
        throw NotImplementedException(ctx, "InstanceofExpression")
    }

    override fun visitImportExpression(ctx: JavaScriptParser.ImportExpressionContext?): BaseNode? {
        throw NotImplementedException(ctx, "ImportExpression")
    }

    override fun visitSuperExpression(ctx: JavaScriptParser.SuperExpressionContext?): BaseNode? {
        throw NotImplementedException(ctx, "SuperExpression")
    }

    override fun visitPostIncrementExpression(ctx: JavaScriptParser.PostIncrementExpressionContext?): BaseNode? {
        throw NotImplementedException(
            ctx,
            "PostIncrementExpression"
        )
    }

    override fun visitYieldExpression(ctx: JavaScriptParser.YieldExpressionContext?): BaseNode? {
        throw NotImplementedException(ctx, "YieldExpression")
    }

    override fun visitNewExpression(ctx: JavaScriptParser.NewExpressionContext?): BaseNode? {
        throw NotImplementedException(ctx, "NewExpression")
    }

    override fun visitClassExpression(ctx: JavaScriptParser.ClassExpressionContext?): BaseNode? {
        throw NotImplementedException(ctx, "ClassExpression")
    }

    override fun visitVoidExpression(ctx: JavaScriptParser.VoidExpressionContext?): BaseNode? {
        throw NotImplementedException(ctx, "VoidExpression")
    }

    override fun visitCoalesceExpression(ctx: JavaScriptParser.CoalesceExpressionContext?): BaseNode? {
        throw NotImplementedException(ctx, "CoalesceExpression")
    }

    override fun visitBigintLiteral(ctx: JavaScriptParser.BigintLiteralContext?): BaseNode? {
        throw NotImplementedException(ctx, "BigintLiteral")
    }

    override fun visitReservedWord(ctx: JavaScriptParser.ReservedWordContext?): BaseNode? {
        throw NotImplementedException(ctx, "ReservedWord")
    }

    override fun visitKeyword(ctx: JavaScriptParser.KeywordContext?): BaseNode? {
        throw NotImplementedException(ctx, "Keyword")
    }

    override fun visitGetter(ctx: JavaScriptParser.GetterContext?): BaseNode? {
        throw NotImplementedException(ctx, "Getter")
    }

    override fun visitSetter(ctx: JavaScriptParser.SetterContext?): BaseNode? {
        throw NotImplementedException(ctx, "Setter")
    }

    override fun visitEos(ctx: JavaScriptParser.EosContext?): BaseNode? {
        throw InvalidOperationException(ctx, "Eos")
    }
}