Program
 └─body
    ├─VariableDeclaration
    │  ├─kind: var
    │  └─declarations
    │     └─VariableDeclarator
    │        ├─id: Identifier
    │        │  └─name: tips
    │        └─init: ArrayExpression
    │           └─elements
    │              ├─StringLiteral
    │              │  └─value: "Click on any AST node with a '+' to expand it"
    │              └─StringLiteral
    │                 └─value: "Shift click on an AST node expands the whole substree"
    ├─FunctionDeclaration
    │  ├─id: Identifier
    │  │  └─name: printTips
    │  ├─body: BlockStatement
    │  │  └─body
    │  │     └─ExpressionStatement
    │  │        └─expression: CallExpression
    │  │           ├─callee: MemberExpression
    │  │           │  ├─kind: DotMember
    │  │           │  ├─obj: Identifier
    │  │           │  │  └─name: tips
    │  │           │  └─property: Identifier
    │  │           │     └─name: forEach
    │  │           └─arguments
    │  │              └─ArrowFunctionExpression
    │  │                 ├─params
    │  │                 │  ├─Identifier
    │  │                 │  │  └─name: tip
    │  │                 │  └─Identifier
    │  │                 │     └─name: i
    │  │                 └─body: CallExpression
    │  │                    ├─callee: MemberExpression
    │  │                    │  ├─kind: DotMember
    │  │                    │  ├─obj: Identifier
    │  │                    │  │  └─name: console
    │  │                    │  └─property: Identifier
    │  │                    │     └─name: log
    │  │                    └─arguments
    │  │                       └─BinaryExpression
    │  │                          ├─left: TemplateStringLiteral
    │  │                          │  └─value: `Tip ${i}:`
    │  │                          ├─operator: +
    │  │                          └─right: Identifier
    │  │                             └─name: tip
    │  └─params: null
    ├─VariableDeclaration
    │  ├─kind: var
    │  └─declarations
    │     └─VariableDeclarator
    │        ├─id: Identifier
    │        │  └─name: a
    │        └─init: null
    ├─ExpressionStatement
    │  └─expression: AssignmentExpression
    │     ├─left: Identifier
    │     │  └─name: a
    │     └─right: StringLiteral
    │        └─value: 'Hello'
    ├─VariableDeclaration
    │  ├─kind: var
    │  └─declarations
    │     └─VariableDeclarator
    │        ├─id: Identifier
    │        │  └─name: message
    │        └─init: NumericLiteral
    │           └─value: 2
    ├─VariableDeclaration
    │  ├─kind: var
    │  └─declarations
    │     └─VariableDeclarator
    │        ├─id: Identifier
    │        │  └─name: t
    │        └─init: NumericLiteral
    │           └─value: 2
    ├─ExpressionStatement
    │  └─expression: AssignmentExpression
    │     ├─left: Identifier
    │     │  └─name: message
    │     └─right: StringLiteral
    │        └─value: 'Hello'
    ├─VariableDeclaration
    │  ├─kind: var
    │  └─declarations
    │     ├─VariableDeclarator
    │     │  ├─id: Identifier
    │     │  │  └─name: v
    │     │  └─init: NumericLiteral
    │     │     └─value: 2
    │     ├─VariableDeclarator
    │     │  ├─id: Identifier
    │     │  │  └─name: с
    │     │  └─init: null
    │     └─VariableDeclarator
    │        ├─id: Identifier
    │        │  └─name: b
    │        └─init: UnaryExpression
    │           ├─operator: -
    │           └─argument: NumericLiteral
    │              └─value: 2
    ├─VariableDeclaration
    │  ├─kind: var
    │  └─declarations
    │     └─VariableDeclarator
    │        ├─id: Identifier
    │        │  └─name: t22
    │        └─init: BinaryExpression
    │           ├─left: Identifier
    │           │  └─name: a
    │           ├─operator: +
    │           └─right: Identifier
    │              └─name: b
    ├─VariableDeclaration
    │  ├─kind: var
    │  └─declarations
    │     ├─VariableDeclarator
    │     │  ├─id: Identifier
    │     │  │  └─name: v1
    │     │  └─init: null
    │     └─VariableDeclarator
    │        ├─id: Identifier
    │        │  └─name: b1
    │        └─init: UnaryExpression
    │           ├─operator: -
    │           └─argument: NumericLiteral
    │              └─value: 2
    ├─ExpressionStatement
    │  └─expression: ExpressionStatement
    │     └─expression: FunctionDeclaration
    │        ├─id: Identifier
    │        │  └─name: test22
    │        ├─body: BlockStatement
    │        │  └─body: null
    │        └─params
    │           ├─Identifier
    │           │  └─name: t
    │           └─Identifier
    │              └─name: tt
    ├─FunctionDeclaration
    │  ├─id: Identifier
    │  │  └─name: foo
    │  ├─body: BlockStatement
    │  │  └─body
    │  │     ├─FunctionDeclaration
    │  │     │  ├─id: Identifier
    │  │     │  │  └─name: f
    │  │     │  ├─body: BlockStatement
    │  │     │  │  └─body: null
    │  │     │  └─params: null
    │  │     ├─FunctionDeclaration
    │  │     │  ├─id: Identifier
    │  │     │  │  └─name: g
    │  │     │  ├─body: BlockStatement
    │  │     │  │  └─body
    │  │     │  │     └─ReturnStatement
    │  │     │  │        └─argument: null
    │  │     │  └─params: null
    │  │     ├─BlockStatement
    │  │     │  └─body: null
    │  │     └─ReturnStatement
    │  │        └─argument: FunctionExpression
    │  │           ├─params
    │  │           │  └─Identifier
    │  │           │     └─name: x
    │  │           └─body: BlockStatement
    │  │              └─body
    │  │                 └─ReturnStatement
    │  │                    └─argument: BinaryExpression
    │  │                       ├─left: BinaryExpression
    │  │                       │  ├─left: Identifier
    │  │                       │  │  └─name: a
    │  │                       │  ├─operator: *
    │  │                       │  └─right: Identifier
    │  │                       │     └─name: x
    │  │                       ├─operator: +
    │  │                       └─right: Identifier
    │  │                          └─name: b
    │  └─params
    │     ├─Identifier
    │     │  └─name: a
    │     └─Identifier
    │        └─name: b
    ├─VariableDeclaration
    │  ├─kind: var
    │  └─declarations
    │     └─VariableDeclarator
    │        ├─id: Identifier
    │        │  └─name: bar
    │        └─init: CallExpression
    │           ├─callee: CallExpression
    │           │  ├─callee: Identifier
    │           │  │  └─name: foo
    │           │  └─arguments
    │           │     ├─NumericLiteral
    │           │     │  └─value: 2
    │           │     └─NumericLiteral
    │           │        └─value: 3
    │           └─arguments
    │              └─NumericLiteral
    │                 └─value: 5
    ├─VariableDeclaration
    │  ├─kind: var
    │  └─declarations
    │     └─VariableDeclarator
    │        ├─id: Identifier
    │        │  └─name: c
    │        └─init: FunctionExpression
    │           ├─params: null
    │           └─body: BlockStatement
    │              └─body: null
    ├─ExpressionStatement
    │  └─expression: CallExpression
    │     ├─callee: Identifier
    │     │  └─name: c
    │     └─arguments: null
    ├─IfStatement
    │  ├─test: SequenceExpression
    │  │  └─expressions
    │  │     ├─LogicalExpression
    │  │     │  ├─left: BinaryExpression
    │  │     │  │  ├─left: Identifier
    │  │     │  │  │  └─name: bar
    │  │     │  │  ├─operator: <
    │  │     │  │  └─right: NumericLiteral
    │  │     │  │     └─value: 10
    │  │     │  ├─operator: &&
    │  │     │  └─right: BooleanLiteral
    │  │     │     └─value: false
    │  │     ├─BinaryExpression
    │  │     │  ├─left: Identifier
    │  │     │  │  └─name: foo
    │  │     │  ├─operator: ===
    │  │     │  └─right: NullLiteral
    │  │     │     └─value: null
    │  │     └─BooleanLiteral
    │  │        └─value: true
    │  ├─consequent: BlockStatement
    │  │  └─body
    │  │     └─WhileStatement
    │  │        ├─test: SequenceExpression
    │  │        │  └─expressions
    │  │        │     ├─StringLiteral
    │  │        │     │  └─value: "What a nice place!"
    │  │        │     └─LogicalExpression
    │  │        │        ├─left: BinaryExpression
    │  │        │        │  ├─left: Identifier
    │  │        │        │  │  └─name: bar
    │  │        │        │  ├─operator: !==
    │  │        │        │  └─right: NumericLiteral
    │  │        │        │     └─value: 0
    │  │        │        ├─operator: ||
    │  │        │        └─right: BinaryExpression
    │  │        │           ├─left: BinaryExpression
    │  │        │           │  ├─left: NumericLiteral
    │  │        │           │  │  └─value: 2
    │  │        │           │  ├─operator: +
    │  │        │           │  └─right: NumericLiteral
    │  │        │           │     └─value: 2
    │  │        │           ├─operator: ===
    │  │        │           └─right: NumericLiteral
    │  │        │              └─value: 4
    │  │        └─body: BlockStatement
    │  │           └─body
    │  │              └─IfStatement
    │  │                 ├─test: BinaryExpression
    │  │                 │  ├─left: Identifier
    │  │                 │  │  └─name: bar
    │  │                 │  ├─operator: <=
    │  │                 │  └─right: NumericLiteral
    │  │                 │     └─value: 0
    │  │                 ├─consequent: ContinueStatement
    │  │                 └─alternative: BlockStatement
    │  │                    └─body
    │  │                       ├─ExpressionStatement
    │  │                       │  └─expression: AssignmentExpression
    │  │                       │     ├─left: Identifier
    │  │                       │     │  └─name: bar
    │  │                       │     └─right: BinaryExpression
    │  │                       │        ├─left: Identifier
    │  │                       │        │  └─name: bar
    │  │                       │        ├─operator: +
    │  │                       │        └─right: NumericLiteral
    │  │                       │           └─value: 1
    │  │                       └─BreakStatement
    │  └─alternative: null
    ├─VariableDeclaration
    │  ├─kind: var
    │  └─declarations
    │     └─VariableDeclarator
    │        ├─id: Identifier
    │        │  └─name: a
    │        └─init: ArrayExpression
    │           └─elements
    │              ├─null
    │              └─Identifier
    │                 └─name: x
    ├─ExpressionStatement
    │  └─expression: AssignmentExpression
    │     ├─left: Identifier
    │     │  └─name: a
    │     └─right: ArrayExpression
    │        └─elements: null
    ├─ExpressionStatement
    │  └─expression: AssignmentExpression
    │     ├─left: Identifier
    │     │  └─name: a
    │     └─right: ArrayExpression
    │        └─elements
    │           ├─null
    │           ├─null
    │           ├─Identifier
    │           │  └─name: x
    │           ├─null
    │           ├─Identifier
    │           │  └─name: y
    │           └─null
    ├─VariableDeclaration
    │  ├─kind: var
    │  └─declarations
    │     └─VariableDeclarator
    │        ├─id: Identifier
    │        │  └─name: o
    │        └─init: ObjectExpression
    │           └─properties: null
    ├─ExpressionStatement
    │  └─expression: AssignmentExpression
    │     ├─left: Identifier
    │     │  └─name: o
    │     └─right: ObjectExpression
    │        └─properties
    │           ├─Property
    │           │  ├─kind: init
    │           │  ├─key: Identifier
    │           │  │  └─name: x
    │           │  └─value: NumericLiteral
    │           │     └─value: 42
    │           ├─Property
    │           │  ├─kind: init
    │           │  ├─key: Identifier
    │           │  │  └─name: while
    │           │  └─value: StringLiteral
    │           │     └─value: "true"
    │           ├─Property
    │           │  ├─kind: init
    │           │  ├─key: StringLiteral
    │           │  │  └─value: "x ="
    │           │  └─value: NumericLiteral
    │           │     └─value: 4
    │           └─Property
    │              ├─kind: init
    │              ├─key: NumericLiteral
    │              │  └─value: 2
    │              └─value: StringLiteral
    │                 └─value: "is the answer"
    ├─ExpressionStatement
    │  └─expression: AssignmentExpression
    │     ├─left: MemberExpression
    │     │  ├─kind: IndexMember
    │     │  ├─obj: Identifier
    │     │  │  └─name: o
    │     │  └─property: NumericLiteral
    │     │     └─value: 2
    │     └─right: BinaryExpression
    │        ├─left: MemberExpression
    │        │  ├─kind: DotMember
    │        │  ├─obj: Identifier
    │        │  │  └─name: o
    │        │  └─property: Identifier
    │        │     └─name: x
    │        ├─operator: +
    │        └─right: MemberExpression
    │           ├─kind: IndexMember
    │           ├─obj: Identifier
    │           │  └─name: o
    │           └─property: StringLiteral
    │              └─value: 'while'
    ├─ExpressionStatement
    │  └─expression: UnaryExpression
    │     ├─operator: delete
    │     └─argument: MemberExpression
    │        ├─kind: IndexMember
    │        ├─obj: Identifier
    │        │  └─name: x
    │        └─property: StringLiteral
    │           └─value: 'true'
    ├─ExpressionStatement
    │  └─expression: UnaryExpression
    │     ├─operator: delete
    │     └─argument: MemberExpression
    │        ├─kind: DotMember
    │        ├─obj: Identifier
    │        │  └─name: o
    │        └─property: Identifier
    │           └─name: x
    ├─VariableDeclaration
    │  ├─kind: var
    │  └─declarations
    │     ├─VariableDeclarator
    │     │  ├─id: Identifier
    │     │  │  └─name: x
    │     │  └─init: NumericLiteral
    │     │     └─value: 3
    │     ├─VariableDeclarator
    │     │  ├─id: Identifier
    │     │  │  └─name: y
    │     │  └─init: null
    │     └─VariableDeclarator
    │        ├─id: Identifier
    │        │  └─name: z
    │        └─init: BinaryExpression
    │           ├─left: NumericLiteral
    │           │  └─value: 5
    │           ├─operator: +
    │           └─right: StringLiteral
    │              └─value: "In my heart I'm number."
    ├─ExpressionStatement
    │  └─expression: AssignmentExpression
    │     ├─left: Identifier
    │     │  └─name: y
    │     └─right: BinaryExpression
    │        ├─left: BinaryExpression
    │        │  ├─left: BinaryExpression
    │        │  │  ├─left: BinaryExpression
    │        │  │  │  ├─left: BinaryExpression
    │        │  │  │  │  ├─left: Identifier
    │        │  │  │  │  │  └─name: x
    │        │  │  │  │  ├─operator: +
    │        │  │  │  │  └─right: BinaryExpression
    │        │  │  │  │     ├─left: BinaryExpression
    │        │  │  │  │     │  ├─left: BinaryExpression
    │        │  │  │  │     │  │  ├─left: ExpressionStatement
    │        │  │  │  │     │  │  │  └─expression: BinaryExpression
    │        │  │  │  │     │  │  │     ├─left: Identifier
    │        │  │  │  │     │  │  │     │  └─name: z
    │        │  │  │  │     │  │  │     ├─operator: -
    │        │  │  │  │     │  │  │     └─right: Identifier
    │        │  │  │  │     │  │  │        └─name: y
    │        │  │  │  │     │  │  ├─operator: *
    │        │  │  │  │     │  │  └─right: NumericLiteral
    │        │  │  │  │     │  │     └─value: 5
    │        │  │  │  │     │  ├─operator: /
    │        │  │  │  │     │  └─right: NumericLiteral
    │        │  │  │  │     │     └─value: 2
    │        │  │  │  │     ├─operator: %
    │        │  │  │  │     └─right: Identifier
    │        │  │  │  │        └─name: z
    │        │  │  │  ├─operator: >>>
    │        │  │  │  └─right: NumericLiteral
    │        │  │  │     └─value: 2e7
    │        │  │  ├─operator: >>
    │        │  │  └─right: Identifier
    │        │  │     └─name: x
    │        │  ├─operator: <<
    │        │  └─right: Identifier
    │        │     └─name: y
    │        ├─operator: |
    │        └─right: BinaryExpression
    │           ├─left: BinaryExpression
    │           │  ├─left: Identifier
    │           │  │  └─name: x
    │           │  ├─operator: &
    │           │  └─right: Identifier
    │           │     └─name: y
    │           ├─operator: ^
    │           └─right: Identifier
    │              └─name: y
    ├─ExpressionStatement
    │  └─expression: AssignmentExpression
    │     ├─left: Identifier
    │     │  └─name: y
    │     └─right: BinaryExpression
    │        ├─left: BinaryExpression
    │        │  ├─left: BinaryExpression
    │        │  │  ├─left: UnaryExpression
    │        │  │  │  ├─operator: !
    │        │  │  │  └─argument: Identifier
    │        │  │  │     └─name: y
    │        │  │  ├─operator: +
    │        │  │  └─right: UnaryExpression
    │        │  │     ├─operator: ~
    │        │  │     └─argument: Identifier
    │        │  │        └─name: y
    │        │  ├─operator: +
    │        │  └─right: UnaryExpression
    │        │     ├─operator: +
    │        │     └─argument: Identifier
    │        │        └─name: y
    │        ├─operator: +
    │        └─right: UnaryExpression
    │           ├─operator: -
    │           └─argument: Identifier
    │              └─name: y
    ├─IfStatement
    │  ├─test: BinaryExpression
    │  │  ├─left: Identifier
    │  │  │  └─name: y
    │  │  ├─operator: <
    │  │  └─right: Identifier
    │  │     └─name: x
    │  ├─consequent: EmptyStatement
    │  └─alternative: null
    ├─IfStatement
    │  ├─test: BinaryExpression
    │  │  ├─left: Identifier
    │  │  │  └─name: y
    │  │  ├─operator: >
    │  │  └─right: Identifier
    │  │     └─name: x
    │  ├─consequent: EmptyStatement
    │  └─alternative: null
    ├─IfStatement
    │  ├─test: BinaryExpression
    │  │  ├─left: Identifier
    │  │  │  └─name: y
    │  │  ├─operator: <=
    │  │  └─right: Identifier
    │  │     └─name: x
    │  ├─consequent: EmptyStatement
    │  └─alternative: null
    ├─IfStatement
    │  ├─test: BinaryExpression
    │  │  ├─left: Identifier
    │  │  │  └─name: y
    │  │  ├─operator: >=
    │  │  └─right: Identifier
    │  │     └─name: x
    │  ├─consequent: EmptyStatement
    │  └─alternative: null
    ├─IfStatement
    │  ├─test: BinaryExpression
    │  │  ├─left: Identifier
    │  │  │  └─name: y
    │  │  ├─operator: ===
    │  │  └─right: Identifier
    │  │     └─name: x
    │  ├─consequent: EmptyStatement
    │  └─alternative: null
    ├─IfStatement
    │  ├─test: BinaryExpression
    │  │  ├─left: Identifier
    │  │  │  └─name: y
    │  │  ├─operator: !==
    │  │  └─right: Identifier
    │  │     └─name: x
    │  ├─consequent: EmptyStatement
    │  └─alternative: null
    ├─BlockStatement
    │  └─body
    │     └─ExpressionStatement
    │        └─expression: CallExpression
    │           ├─callee: Identifier
    │           │  └─name: foo
    │           └─arguments: null
    ├─ExpressionStatement
    │  └─expression: BinaryExpression
    │     ├─left: BinaryExpression
    │     │  ├─left: Identifier
    │     │  │  └─name: x
    │     │  ├─operator: +
    │     │  └─right: Identifier
    │     │     └─name: y
    │     ├─operator: +
    │     └─right: Identifier
    │        └─name: z
    ├─VariableDeclaration
    │  ├─kind: var
    │  └─declarations
    │     └─VariableDeclarator
    │        ├─id: Identifier
    │        │  └─name: greeting
    │        └─init: ObjectExpression
    │           └─properties
    │              ├─Property
    │              │  ├─kind: init
    │              │  ├─key: Identifier
    │              │  │  └─name: fullname
    │              │  └─value: StringLiteral
    │              │     └─value: "Michael Jackson"
    │              └─Property
    │                 ├─kind: init
    │                 ├─key: Identifier
    │                 │  └─name: greet
    │                 └─value: ArrowFunctionExpression
    │                    ├─params
    │                    │  ├─Identifier
    │                    │  │  └─name: message
    │                    │  └─Identifier
    │                    │     └─name: name
    │                    └─body:     ├─ExpressionStatement
    │  └─expression: BinaryExpression
    │     ├─left: UnaryExpression
    │     │  ├─operator: ~
    │     │  └─argument: Identifier
    │     │     └─name: x
    │     ├─operator: +
    │     └─right: Identifier
    │        └─name: y
    ├─DoWhileStatement
    │  ├─body: BlockStatement
    │  │  └─body: null
    │  └─test: BooleanLiteral
    │     └─value: true
    ├─VariableDeclaration
    │  ├─kind: const
    │  └─declarations
    │     └─VariableDeclarator
    │        ├─id: Identifier
    │        │  └─name: a1
    │        └─init: NumericLiteral
    │           └─value: 1
    └─FunctionDeclaration
       ├─id: Identifier
       │  └─name: hello
       ├─body: BlockStatement
       │  └─body
       │     └─ExpressionStatement
       │        └─expression: CallExpression
       │           ├─callee: Identifier
       │           │  └─name: sayHi
       │           └─arguments
       │              └─BinaryExpression
       │                 ├─left: Identifier
       │                 │  └─name: x
       │                 ├─operator: +
       │                 └─right: Identifier
       │                    └─name: y
       └─params: null
