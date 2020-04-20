testing system
version: 0.0.2
test_case = function hello() { sayHi(); }
Program
 └─body
    └─FunctionDeclaration
       ├─id: Identifier
       │  └─name: hello
       ├─body: BlockStatement
       │  └─body
       │     └─ExpressionStatement
       │        └─expression: CallExpression
       │           ├─callee: Identifier
       │           │  └─name: sayHi
       │           └─arguments: null
       └─params: null
