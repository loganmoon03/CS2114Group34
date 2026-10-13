// -------------------------------------------------------------------------
/**
 * Represents one drawing result, the winning ticket and its prize.
 *
 * @author Dochan (Logan) Moon
 * @version 11 Oct 2026
 */
public class Result
{

    // ~ Fields ................................................................

    private RaffleTicket ticket;
    private Prize prize;

    // ~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates the record of one drawing.
     *
     * @param ticket the ticket that was drawn
     * @param prize the prize that the ticket won
     */
    public Result(RaffleTicket ticket, Prize prize)
    {
        this.ticket = ticket;
        this.prize = prize;
    }

    // ~ Public Methods ........................................................


    // ----------------------------------------------------------
    /**
     * Gets the ticket that was drawn.
     *
     * @return the winning ticket
     */
    public RaffleTicket getTicket()
    {
        return ticket;
    }


    // ----------------------------------------------------------
    /**
     * Gets the prize that was won.
     *
     * @return the prize
     */
    public Prize getPrize()
    {
        return prize;
    }


    // ----------------------------------------------------------
    /**
     * Gets the result as text.
     *
     * @return the winner and the prize
     */
    @Override
    public String toString()
    {
        return ticket + " won " + prize;
    }
}
