testing system
version: 0.0.2
test_case = var hello = function hi() { sayHi() };
Program
 └─body
    └─VariableDeclaration
       ├─kind: var
       └─declarations
          └─VariableDeclarator
             ├─id: Identifier
             │  └─name: hello
             └─init: FunctionDeclaration
                ├─id: Identifier
                │  └─name: hi
                ├─body: BlockStatement
                │  └─body
                │     └─ExpressionStatement
                │        └─expression: CallExpression
                │           ├─callee: Identifier
                │           │  └─name: sayHi
                │           └─arguments: null
                └─params: null
