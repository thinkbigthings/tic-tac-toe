# tic-tac-toe
Classic Tic Tac Toe game

## Development

Build with gradlew clean build

Build with test coverage: gradlew clean test jacocoTestReport

Run with gradlew bootRun -q

Run with java -jar build/libs/tic-tac-toe-x.y.z.jar



## Run the game

The properties can be set in application.properties, or overridden from the command line like so: 

java -jar tic-tac-toe-x.y.z.jar --boardSize=3 --player1.identity=human --player2.identity=computer --player1.symbol=X --player2.symbol=O

