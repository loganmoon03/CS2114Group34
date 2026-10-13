import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the Result class.
 *
 * @author Dochan (Logan) Moon
 * @version 12 Oct 2026
 */
public class ResultTest
    extends TestCase
{

    // ~ Fields ................................................................

    private RaffleTicket ticket;
    private Prize prize;
    private Result result;

    // ~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Sets up each test method.
     */
    public void setUp()
    {
        ticket = new RaffleTicket("ehcks77", "Logan");
        prize = new Prize("Mug", "coffee mug");
        result = new Result(ticket, prize);
    }

    // ~ Public Methods ........................................................


    // ----------------------------------------------------------
    /**
     * Tests getTicket()
     */
    public void testGetTicket()
    {
        // * the very same ticket object comes back
        assertSame(ticket, result.getTicket());
        assertEquals("Logan", result.getTicket().getName());
    }


    // ----------------------------------------------------------
    /**
     * Tests getPrize()
     */
    public void testGetPrize()
    {
        // * the very same prize object comes back
        assertSame(prize, result.getPrize());
        assertEquals("Mug", result.getPrize().getName());
    }


    // ----------------------------------------------------------
    /**
     * Tests the getters when the result was made with nothing.
     */
    public void testWhenNull()
    {
        // * the result does not check its values, Raffle does
        Result empty = new Result(null, null);

        assertNull(empty.getTicket());
        assertNull(empty.getPrize());
    }


    // ----------------------------------------------------------
    /**
     * Tests toString()
     */
    public void testToString()
    {
        assertEquals("Logan (ID: ehcks77) won Mug - coffee mug",
            result.toString());
    }


    // ----------------------------------------------------------
    /**
     * Tests toString() when the result was made with nothing.
     */
    public void testToStringWithNull()
    {
        Result empty = new Result(null, null);

        // * it must not crash
        assertEquals("null won null", empty.toString());
    }
}
