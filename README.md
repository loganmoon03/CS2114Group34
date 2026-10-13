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

## Reflection

What changed from your specification? Classes, method signatures, where validation lives, test cases — what did the spec say, what did you build instead, and why?

- We kept the planned classes, but LinkedChain.add() returns a boolean instead of void, and invalid indices in get() and removeAt() throw IndexOutOfBoundsException instead of returning null. Main handles input errors and re-prompts users, while Raffle also validates inputs so direct method calls cannot bypass those checks. Our tests cover quantity boundaries, blank inputs, duplicates, and empty drawings.

What changed in the LinkedChain? Anything you added to or modified in the provided class and why your application needed it, or that you used it unchanged.

- We use removeAt() to remove randomly selected entries and toArray() to retrieve entries for listing and participant ID checks. LinkedChain handles storage only; raffle rules stay in Raffle. Our current README credits us with adding removeAt(), but we still need to compare it with the original provided file to confirm that attribution.

What happened to your unknowns? Of the things you listed in your scope as not knowing how to do, which did you figure out, and which are still open?

- We figured out random selection using Random.nextInt(size) and removeAt(). We also defined ticket equality using participant ID and name, and prize equality using name and description. We added equality and duplicate-removal tests, but controlled tests for selecting specific positions during a drawing are still needed.

What doesn't work yet? MVP features, bad-input cases, or tests that are missing or failing in this prototype.

- ID conflict checking only searches remaining tickets, so an ID can be reused with another name after all its tickets are drawn. Listing uses repeated string concatenation instead of the planned StringBuilder, so its performance needs improvement. We still need to add function and tests for removing selected winner with the remaining tickets.

## Next steps

What is left to finish the MVP? The remaining features and fixes, in the order you will do them.

- First, we will fix ID conflict checking so an ID cannot be reused under a different name after its tickets are drawn. Next, we will replace repeated string concatenation with StringBuilder in makeList(). Finally, we will complete console testing, run the full test suite, and update the README with the results and remaining limitations.

Which stretch goals will you attempt? The ones from your scope you are committing to, and the ones you are dropping.

- We propose implementing the optional one-win-per-participant mode, which removes all remaining tickets belonging to a winner. We propose dropping save/load from the final version so we can focus on completing and testing the MVP and the one-win mode.

What new tests will you need? The methods and bad-input cases the added work must cover.

- We will test ID conflicts after a participant’s last ticket is drawn, invalid indices in get(), and draws that remove only one duplicate ticket and prize. Console tests will cover blank input, invalid menu choices, nonnumeric quantities, out-of-range quantities, and end-of-input handling. We will also add controlled random-selection tests and verify that one-win mode removes the winner’s tickets while preserving other participants’ tickets.

Who does what? Which team member owns each remaining item.

- Our proposed assignments are: Dochan (Logan) Moon will fix ID conflict checking, update makeList(), and add controlled random-selection support and tests. David Sage will add console, index-boundary, duplicate-removal, and one-win-mode tests. Aishwarya Shah will implement one-win mode, run the full test suite, and update the README with the results. We will review together after we finish our individual tasks.

## Attribution

| Section          | Team member(s)                  | Contribution (how)                                                                                                                                                                                                                   |
| ---------------- | ------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| LinkedChain      | Dochan (Logan) Moon             | Added removeAt that removes and returns the entry at the index                                                                                                                                                                       |
| RaffleTicket     | Dochan (Logan) Moon             | Created a class that stores the information for raffle tickets with the participants ID and name                                                                                                                                     |
| Prize            | Dochan (Logan) Moon             | Created a class that gets the information for raffle prizes with the name and its description                                                                                                                                        |
| Raffle           | Dochan (Logan) Moon             | Created a class that has three linkedChain fields for tickets, prizes, and results. It receives tickets and prizes and draws winner and prize randomly and rejects if empty. I asked Claude what fields I should have for the class. |
| Result           | Dochan (Logan) Moon             | Created a class that has three linkedChain fields for tickets, prizes, and results that have been drawn.                                                                                                                             |
| Main             | Dochan (Logan) Moon             | Created a main class that handles the input from the user with the help from Claude on which inputs are necessary for the program.                                                                                                   |
| RaffleTicketTest | Dochan (Logan) Moon             | Asked Claude to review my tests and provide what I've missed to test all cases.                                                                                                                                                      |
| PrizeTest        | Dochan (Logan) Moon             | Asked Claude to review my tests and provide what I've missed to test all cases.                                                                                                                                                      |
| ResultTest       | Dochan (Logan) Moon             | Asked Claude to review my tests and provide what I've missed to test all cases.                                                                                                                                                      |
| RaffleTest       | Dochan (Logan) Moon             | Asked Claude to review my tests and provide what I've missed to test all cases.                                                                                                                                                      |
| LinkedChainTest  | David Sage                      | Created a testing class for the linkedchain class analyzing each method and writing corresponding test cases                                                                                                                         |
| readme           | David Sage, Dochan (Logan) Moon | Created the readme document for all sections besides reflection and next steps                                                                                                                                                       |

## Authors

Group 34
