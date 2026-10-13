import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 * Runs the raffle console menu and checks the user's input.
 *
 * @author Dochan (Logan) Moon
 * @version 11 Oct 2026
 */
public class Main
{

    // ~ Fields ................................................................

    private Raffle raffle;
    private Scanner input;

    // ~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a Main that reads the user's typing from the given Scanner.
     *
     * @param input where the user's typing comes from
     */
    private Main(Scanner input)
    {
        this.input = input;
        raffle = new Raffle();
    }

    // ~ Public Methods ........................................................


    // ----------------------------------------------------------
    /**
     * Starts the program.
     *
     * @param args not used
     */
    public static void main(String[] args)
    {
        Main main = new Main(new Scanner(System.in));
        main.run();
    }

    // ~ Private Methods .......................................................


    // ----------------------------------------------------------
    /**
     * Shows the menu until the user quits.
     */
    private void run()
    {
        System.out.println("===== Welcome to the Raffle =====");

        boolean running = true;
        while (running)
        {
            printMenu();
            String choice = readLine("Choose an option: ");

            // No more input, so stop.
            if (choice == null)
            {
                return;
            }

            running = handleChoice(choice);
        }
    }


    // ----------------------------------------------------------
    /**
     * Runs the menu option the user picked.
     *
     * @param choice what the user typed
     * @return false when the user wants to quit
     */
    private boolean handleChoice(String choice)
    {
        int number;
        try
        {
            number = Integer.parseInt(choice);
        }
        catch (NumberFormatException e)
        {
            System.out.println("\"" + choice + "\" is not a number. Type a "
                + "number from the menu.");
            return true;
        }

        if (number == 1)
        {
            addTickets();
        }
        else if (number == 2)
        {
            addPrize();
        }
        else if (number == 3)
        {
            drawWinner();
        }
        else if (number == 4)
        {
            showTickets();
        }
        else if (number == 5)
        {
            showPrizes();
        }
        else if (number == 6)
        {
            showResults();
        }
        else if (number == 0)
        {
            System.out.println("Goodbye!");
            return false;
        }
        else
        {
            System.out.println(number + " is not an option. Type a number "
                + "from the menu.");
        }
        return true;
    }


    // ----------------------------------------------------------
    /**
     * Prints the main menu.
     */
    private void printMenu()
    {
        System.out.println();
        System.out.println("===== Raffle Menu =====");
        System.out.println("1. Add tickets");
        System.out.println("2. Add a prize");
        System.out.println("3. Draw a winner");
        System.out.println("4. Show tickets");
        System.out.println("5. Show prizes");
        System.out.println("6. Show results");
        System.out.println("0. Quit");
    }


    // ----------------------------------------------------------
    /**
     * Asks for an id, a name and a quantity, then adds the tickets.
     */
    private void addTickets()
    {
        String id = null;
        String name = null;
        boolean asking = true;

        // Asks again if the id is used by another name.
        while (asking)
        {
            id = readText("Participant ID: ");
            if (id == null)
            {
                return;
            }

            name = readText("Participant name: ");
            if (name == null)
            {
                return;
            }

            if (raffle.hasNameConflict(id, name))
            {
                System.out.println("ID \"" + id + "\" already belongs to a "
                    + "different name. Use a different ID for " + name
                    + ".");
            }
            else
            {
                asking = false;
            }
        }

        int quantity = readQuantity();
        if (quantity == 0)
        {
            return;
        }

        if (raffle.addTickets(id, name, quantity))
        {
            System.out.println("Added " + quantity + " ticket(s) for "
                + name + ". Tickets in the raffle: "
                + raffle.getTicketCount() + ".");
        }
        else
        {
            System.out.println("The tickets could not be added.");
        }
    }


    // ----------------------------------------------------------
    /**
     * Asks for a prize name and description, then adds the prize.
     */
    private void addPrize()
    {
        String name = readText("Prize name: ");
        if (name == null)
        {
            return;
        }

        String description = readText("Prize description: ");
        if (description == null)
        {
            return;
        }

        raffle.addPrize(name, description);
        System.out.println("Added the prize \"" + name + "\". Prizes in the "
            + "raffle: " + raffle.getPrizeCount() + ".");
    }


    // ----------------------------------------------------------
    /**
     * Draws one winner and shows the ticket and the prize.
     */
    private void drawWinner()
    {
        Result result = raffle.draw();

        if (result == null)
        {
            if (raffle.getTicketCount() == 0)
            {
                System.out.println("There are no tickets. Choose 1 to add "
                    + "tickets first.");
            }
            else
            {
                System.out.println("There are no prizes. Choose 2 to add a "
                    + "prize first.");
            }
            return;
        }

        System.out.println("The winner is " + result.getTicket() + "!");
        System.out.println("Prize: " + result.getPrize());
        System.out.println("Tickets left: " + raffle.getTicketCount()
            + ", prizes left: " + raffle.getPrizeCount() + ".");
    }


    // ----------------------------------------------------------
    /**
     * Shows every ticket that is left.
     */
    private void showTickets()
    {
        if (raffle.getTicketCount() == 0)
        {
            System.out.println("There are no tickets. Choose 1 to add "
                + "tickets.");
            return;
        }

        System.out.println("Tickets (" + raffle.getTicketCount() + "):");
        System.out.print(raffle.listTickets());
    }


    // ----------------------------------------------------------
    /**
     * Shows every prize that is left.
     */
    private void showPrizes()
    {
        if (raffle.getPrizeCount() == 0)
        {
            System.out.println("There are no prizes. Choose 2 to add a "
                + "prize.");
            return;
        }

        System.out.println("Prizes (" + raffle.getPrizeCount() + "):");
        System.out.print(raffle.listPrizes());
    }


    // ----------------------------------------------------------
    /**
     * Shows every drawing so far.
     */
    private void showResults()
    {
        String results = raffle.listResults();

        if (results.isEmpty())
        {
            System.out.println("Nothing has been drawn yet. Choose 3 to "
                + "draw a winner.");
            return;
        }

        System.out.println("Results:");
        System.out.print(results);
    }


    // ----------------------------------------------------------
    /**
     * Keeps asking until the user types something that is not blank.
     *
     * @param prompt the question to show
     * @return the text, or null if there is no more input
     */
    private String readText(String prompt)
    {
        while (true)
        {
            String text = readLine(prompt);
            if (text == null)
            {
                return null;
            }

            if (!text.isEmpty())
            {
                return text;
            }

            System.out.println("This cannot be blank. Please try again.");
        }
    }


    // ----------------------------------------------------------
    /**
     * Keeps asking until the user types a quantity that is in range.
     *
     * @return the quantity, or 0 if there is no more input
     */
    private int readQuantity()
    {
        while (true)
        {
            String text = readLine("How many tickets (" + Raffle.MIN_QUANTITY
                + " to " + Raffle.MAX_QUANTITY + "): ");
            if (text == null)
            {
                return 0;
            }

            try
            {
                int quantity = Integer.parseInt(text);
                if (quantity >= Raffle.MIN_QUANTITY
                    && quantity <= Raffle.MAX_QUANTITY)
                {
                    return quantity;
                }

                System.out.println("The quantity must be from "
                    + Raffle.MIN_QUANTITY + " to " + Raffle.MAX_QUANTITY
                    + ". Please try again.");
            }
            catch (NumberFormatException e)
            {
                System.out.println("\"" + text + "\" is not a whole number. "
                    + "Please try again.");
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * Shows a prompt and reads one line.
     *
     * @param prompt the question to show
     * @return the trimmed line, or null if there is no more input
     */
    private String readLine(String prompt)
    {
        System.out.print(prompt);

        if (!input.hasNextLine())
        {
            System.out.println();
            return null;
        }
        return input.nextLine().trim();
    }
}
