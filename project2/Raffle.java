import java.util.Random;

// -------------------------------------------------------------------------
/**
 * Holds the rules of the raffle. Keeps the tickets, prizes and results in
 * LinkedChains and draws a random winner. It does no input or output.
 *
 * @author Dochan (Logan) Moon
 * @version 11 Oct 2026
 */
public class Raffle
{

    // ~ Fields ................................................................

    /** The smallest number of tickets that can be added at one time. */
    public static final int MIN_QUANTITY = 1;

    /** The largest number of tickets that can be added at one time. */
    public static final int MAX_QUANTITY = 1000;

    private LinkedChain<RaffleTicket> tickets;
    private LinkedChain<Prize> prizes;
    private LinkedChain<Result> results;

    private Random random;

    // ~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new Raffle with no tickets, prizes or results yet.
     */
    public Raffle()
    {
        tickets = new LinkedChain<RaffleTicket>();
        prizes = new LinkedChain<Prize>();
        results = new LinkedChain<Result>();
        random = new Random();
    }

    // ~ Public Methods ........................................................


    // ----------------------------------------------------------
    /**
     * Adds tickets for one participant. Nothing is added if the input is
     * blank, the quantity is out of range, or the id has a different name.
     *
     * @param participantID the id of the participant
     * @param name the name of the participant
     * @param quantity how many tickets to add
     * @return true if the tickets were added
     */
    public boolean addTickets(String participantID, String name, int quantity)
    {
        if (isBlank(participantID) || isBlank(name))
        {
            return false;
        }

        if (quantity < MIN_QUANTITY || quantity > MAX_QUANTITY)
        {
            return false;
        }

        String id = participantID.trim();
        String cleanName = name.trim();

        if (hasNameConflict(id, cleanName))
        {
            return false;
        }

        // One ticket object for each chance to win.
        for (int i = 0; i < quantity; i++)
        {
            tickets.add(new RaffleTicket(id, cleanName));
        }
        return true;
    }


    // ----------------------------------------------------------
    /**
     * Adds a prize. Nothing is added if the name or description is blank.
     *
     * @param name the name of the prize
     * @param description a short description of the prize
     */
    public void addPrize(String name, String description)
    {
        if (isBlank(name) || isBlank(description))
        {
            return;
        }

        prizes.add(new Prize(name.trim(), description.trim()));
    }


    // ----------------------------------------------------------
    /**
     * Draws one random ticket and prize, removes them, and saves the result.
     *
     * @return the result, or null if there are no tickets or prizes
     */
    public Result draw()
    {
        if (tickets.isEmpty() || prizes.isEmpty())
        {
            return null;
        }

        // Every ticket has the same chance.
        int ticketIndex = random.nextInt(tickets.size());
        int prizeIndex = random.nextInt(prizes.size());

        RaffleTicket ticket = tickets.removeAt(ticketIndex);
        Prize prize = prizes.removeAt(prizeIndex);

        Result result = new Result(ticket, prize);
        results.add(result);
        return result;
    }


    // ----------------------------------------------------------
    /**
     * Checks if this id is already used by a different name.
     *
     * @param participantID the id to look for
     * @param name the name that wants to use the id
     * @return true if the id belongs to a different name
     */
    public boolean hasNameConflict(String participantID, String name)
    {
        if (isBlank(participantID) || isBlank(name))
        {
            return false;
        }

        String id = participantID.trim();
        String cleanName = name.trim();

        // Copied to an array because get(i) in a loop is slow on a chain.
        RaffleTicket[] all = tickets.toArray(new RaffleTicket[tickets.size()]);

        for (int i = 0; i < all.length; i++)
        {
            RaffleTicket ticket = all[i];
            if (ticket.getParticipantID().equals(id)
                && !ticket.getName().equals(cleanName))
            {
                return true;
            }
        }
        return false;
    }


    // ----------------------------------------------------------
    /**
     * Gets how many tickets are left in the raffle.
     *
     * @return the number of tickets left
     */
    public int getTicketCount()
    {
        return tickets.size();
    }


    // ----------------------------------------------------------
    /**
     * Gets how many prizes are left in the raffle.
     *
     * @return the number of prizes left
     */
    public int getPrizeCount()
    {
        return prizes.size();
    }


    // ----------------------------------------------------------
    /**
     * Lists every ticket that is left, one per line.
     *
     * @return the tickets as text, or an empty string if there are none
     */
    public String listTickets()
    {
        return makeList(tickets.toArray(new RaffleTicket[tickets.size()]));
    }


    // ----------------------------------------------------------
    /**
     * Lists every prize that is left, one per line.
     *
     * @return the prizes as text, or an empty string if there are none
     */
    public String listPrizes()
    {
        return makeList(prizes.toArray(new Prize[prizes.size()]));
    }


    // ----------------------------------------------------------
    /**
     * Lists every drawing result, one per line, oldest first.
     *
     * @return the results as text, or an empty string if there are none
     */
    public String listResults()
    {
        return makeList(results.toArray(new Result[results.size()]));
    }

    // ~ Private Methods .......................................................


    // ----------------------------------------------------------
    /**
     * Turns the items into numbered lines of text.
     *
     * @param items the items copied out of a chain, first entry first
     * @return the items as text, one per line
     */
    private String makeList(Object[] items)
    {
        String text = "";
        int number = 1;

        // add() puts new entries at the front, so read backwards.
        for (int i = items.length - 1; i >= 0; i--)
        {
            text = text + number + ". " + items[i] + "\n";
            number++;
        }
        return text;
    }


    // ----------------------------------------------------------
    /**
     * Checks if the text is null or only spaces.
     *
     * @param text the text to check
     * @return true if the text is blank
     */
    private boolean isBlank(String text)
    {
        return text == null || text.trim().isEmpty();
    }
}
