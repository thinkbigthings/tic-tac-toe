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

## Monitoring

### VisualVM Setup and Configuration
- [JVisualVM](https://visualvm.github.io) is the default Java monitoring tool.
- IntelliJ also has a [VisualVM Plugin](https://plugins.jetbrains.com/plugin/?idea&pluginId=7115)
- VisualVM should have the VisualVM-MBeans plugin installed.

### Running VisualVM
- Launch jvisualvm &
- Launch java -jar build/libs/tic-tac-toe-0.3.2.jar
- Look in jvisualvm for the spring boot main class: org.springframework.boot.loader.JarLauncher
- double click the class
- wait a few minutes (takes a while to load)
- look at the profiling tab (sampling seems to not work)



