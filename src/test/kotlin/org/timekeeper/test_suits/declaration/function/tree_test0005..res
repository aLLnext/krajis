testing system
version: 0.0.2
test_case = function eval() { function inner() { } }
Program
 └─body
    └─FunctionDeclaration
       ├─id: Identifier
       │  └─name: eval
       ├─body: BlockStatement
       │  └─body
       │     └─FunctionDeclaration
       │        ├─id: Identifier
       │        │  └─name: inner
       │        ├─body: BlockStatement
       │        │  └─body: null
       │        └─params: null
       └─params: null
