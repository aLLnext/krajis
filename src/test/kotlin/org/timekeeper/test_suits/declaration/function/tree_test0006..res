testing system
version: 0.0.2
test_case = function hello(a) { sayHi(); }
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
       └─params
          └─Identifier
             └─name: a
