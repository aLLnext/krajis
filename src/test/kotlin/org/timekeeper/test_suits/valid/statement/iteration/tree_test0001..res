Program
 └─body
    └─WhileStatement
       ├─test: BinaryExpression
       │  ├─left: Identifier
       │  │  └─name: x
       │  ├─operator: <
       │  └─right: NumericLiteral
       │     └─value: 10
       └─body: BlockStatement
          └─body
             ├─ExpressionStatement
             │  └─expression: AssignmentExpression
             │     ├─left: Identifier
             │     │  └─name: x
             │     └─right: BinaryExpression
             │        ├─left: Identifier
             │        │  └─name: x
             │        ├─operator: +
             │        └─right: NumericLiteral
             │           └─value: 1
             └─ExpressionStatement
                └─expression: AssignmentExpression
                   ├─left: Identifier
                   │  └─name: y
                   └─right: BinaryExpression
                      ├─left: Identifier
                      │  └─name: y
                      ├─operator: -
                      └─right: NumericLiteral
                         └─value: 1
