# CS2114Group34 Group 34 – Raffle

A console raffle program. Add tickets for participants, add prizes, and draw random winners from a menu.

## Requirements

- Java Development Kit (JDK) 8 or newer
- To check that it's installed, run `java -version` and `javac -version`

## Files

`Main.java` - Console menu and input checking (program entry point) 
`Raffle.java` - Raffle rules and random drawing 
`RaffleTicket.java` - One ticket for one participant 
`Prize.java` - One prize 
`Result.java` - A drawn ticket and the prize it won 
`LinkedChain.java` - Generic linked storage used by `Raffle` 

## Compiling

Open a terminal in the folder that contains the `.java` files and run:

```
javac Main.java
```

This also compiles `Raffle`, `LinkedChain` and the other classes that `Main` uses. To compile everything in the folder instead, use `javac *.java` (This will also compile testing classes, reference the testing section below).

## Running

From the same folder:

```
java Main
```

## Using the program

Type the number of a menu option and press Enter.

1 - Add tickets (participant ID, name, quantity 1–1000)
2 - Add a prize (name and description)
3 - Draw a winner (removes one ticket and one prize)
4 - Show remaining tickets
5 - Show remaining prizes
6 - Show results so far
0 - Quit

Notes:
- A participant ID can only belong to one name. Reusing an ID with a different name is rejected.
- Each ticket is one chance to win, so more tickets means better odds.
- You need at least one ticket and one prize before you can draw.

## Testing

The tests extend `student.TestCase`, so the course test library and JUnit must be on the classpath. Replace the jar paths below with the ones on your machine:

```
javac -cp ".:path/to/student.jar:path/to/junit.jar" *.java
java  -cp ".:path/to/student.jar:path/to/junit.jar" org.junit.runner.JUnitCore LinkedChainTest
```

On Windows, use `;` instead of `:` to separate classpath entries.

If you use an IDE (Eclipse, IntelliJ, VS Code), add the same jars to the project's build path and run test files as a JUnit test.

## Authors

Group 34