Program
 └─body
    └─WhileStatement
       ├─test: BooleanLiteral
       │  └─value: true
       └─body: BlockStatement
          └─body
             └─IfStatement
                ├─test: Identifier
                │  └─name: x
                ├─consequent: BreakStatement
                └─alternative: ExpressionStatement
                   └─expression: Identifier
                      └─name: y
