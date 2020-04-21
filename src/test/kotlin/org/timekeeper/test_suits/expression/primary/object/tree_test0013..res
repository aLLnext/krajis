Program
 └─body
    ├─ExpressionStatement
    │  └─expression: StringLiteral
    │     └─value: "use strict"
    └─ExpressionStatement
       └─expression: AssignmentExpression
          ├─left: Identifier
          │  └─name: x
          └─right: ObjectExpression
             └─properties
                ├─Property
kind: init
                │  ├─key: Identifier
                │  │  └─name: y
                │  └─value: NumericLiteral
                │     └─value: 1
                └─Property
kind: init
                   ├─key: Identifier
                   │  └─name: y
                   └─value: NumericLiteral
                      └─value: 1
