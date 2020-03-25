# krajis
Kotlin js Interpreter

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

## Deployment

**Step 1**\
Generate ANTLR Recognizer.  
`antlr4 -o ...krajis\gen\org\timekeeper\parser -package org.timekeeper.parser -Dlanguage=Java -listener -visitor -lib ...krajis\src\main\kotlin\org\timekeeper\parser .../krajis/src/main/kotlin/org/timekeeper/parser\JavaScriptLexer.g4` 

**Step 2**\
Run main() in Main.kt. Write code in console and enter Ctrl + D to execute.


## Build with 
* Maven - Dependancy Management


## Authors
* **Anton Mikhailenko** - *initial work* - [sapiest](https://github.com/sapiest)
