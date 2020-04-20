testing system
version: 0.0.2
test_case = function test() { "42" + 42; }
Program
 └─body
    └─FunctionDeclaration
       ├─id: Identifier
       │  └─name: test
       ├─body: BlockStatement
       │  └─body
       │     └─ExpressionStatement
       │        └─expression: BinaryExpression
       │           ├─left: StringLiteral
       │           │  └─value: "42"
       │           ├─operator: +
       │           └─right: NumericLiteral
       │              └─value: 42
       └─params: null
