Program
 └─body
    └─ExpressionStatement
       └─expression: BinaryExpression
          ├─left: BinaryExpression
          │  ├─left: Identifier
          │  │  └─name: a
          │  ├─operator: +
          │  └─right: ExpressionStatement
          │     └─expression: BinaryExpression
          │        ├─left: Identifier
          │        │  └─name: b
          │        ├─operator: <
          │        └─right: ExpressionStatement
          │           └─expression: BinaryExpression
          │              ├─left: Identifier
          │              │  └─name: c
          │              ├─operator: *
          │              └─right: Identifier
          │                 └─name: d
          ├─operator: +
          └─right: Identifier
             └─name: e
