import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the Raffle class.
 *
 * @author Dochan (Logan) Moon
 * @version 12 Oct 2026
 */
public class RaffleTest
    extends TestCase
{

    // ~ Fields ................................................................

    private Raffle raffle;

    // ~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Sets up each test method.
     */
    public void setUp()
    {
        raffle = new Raffle();
    }

    // ~ Public Methods ........................................................


    // ----------------------------------------------------------
    /**
     * Tests addTickets() with good input.
     */
    public void testAddTickets()
    {
        assertTrue(raffle.addTickets("ehcks77", "Logan", 3));
        assertEquals(3, raffle.getTicketCount());

        // * the same person can buy more tickets later
        assertTrue(raffle.addTickets("ehcks77", "Logan", 2));
        assertEquals(5, raffle.getTicketCount());

        // * a different person with a different id
        assertTrue(raffle.addTickets("david123", "David", 1));
        assertEquals(6, raffle.getTicketCount());
    }


    // ----------------------------------------------------------
    /**
     * Tests addTickets() when the id already belongs to another name.
     */
    public void testAddTicketsNameConflict()
    {
        assertTrue(raffle.addTickets("ehcks77", "Logan", 3));

        assertFalse(raffle.addTickets("ehcks77", "David", 2));
        // * nothing was added
        assertEquals(3, raffle.getTicketCount());
    }


    // ----------------------------------------------------------
    /**
     * Tests addTickets() with a missing or blank id or name.
     */
    public void testAddTicketsBlank()
    {
        assertFalse(raffle.addTickets(null, "Logan", 1));
        assertFalse(raffle.addTickets("", "Logan", 1));
        assertFalse(raffle.addTickets("   ", "Logan", 1));

        assertFalse(raffle.addTickets("ehcks77", null, 1));
        assertFalse(raffle.addTickets("ehcks77", "", 1));
        assertFalse(raffle.addTickets("ehcks77", "   ", 1));

        assertEquals(0, raffle.getTicketCount());
    }


    // ----------------------------------------------------------
    /**
     * Tests addTickets() with a quantity that is out of range.
     */
    public void testAddTicketsBadQuantity()
    {
        assertFalse(raffle.addTickets("ehcks77", "Logan", 0));
        assertFalse(raffle.addTickets("ehcks77", "Logan", -5));
        // * one more than the limit of 1000
        assertFalse(raffle.addTickets("ehcks77", "Logan", 1001));
        assertEquals(0, raffle.getTicketCount());

        // * the smallest and largest quantity that are allowed
        assertTrue(raffle.addTickets("ehcks77", "Logan", 1));
        assertTrue(raffle.addTickets("ehcks77", "Logan", 1000));
        assertEquals(1001, raffle.getTicketCount());
    }


    // ----------------------------------------------------------
    /**
     * Tests that addTickets() removes spaces around the id and name.
     */
    public void testAddTicketsTrimsSpaces()
    {
        assertTrue(raffle.addTickets("  ehcks77 ", " Logan  ", 1));

        assertEquals("1. Logan (ID: ehcks77)\n", raffle.listTickets());
        // * so the same person without spaces is not a conflict
        assertTrue(raffle.addTickets("ehcks77", "Logan", 1));
    }


    // ----------------------------------------------------------
    /**
     * Tests addPrize() with good input.
     */
    public void testAddPrize()
    {
        raffle.addPrize("Mug", "coffee mug");
        assertEquals(1, raffle.getPrizeCount());

        raffle.addPrize("Hat", "big hat");
        assertEquals(2, raffle.getPrizeCount());

        // * two of the same prize are allowed
        raffle.addPrize("Hat", "big hat");
        assertEquals(3, raffle.getPrizeCount());
    }


    // ----------------------------------------------------------
    /**
     * Tests addPrize() with a missing or blank name or description.
     */
    public void testAddPrizeBlank()
    {
        raffle.addPrize(null, "coffee mug");
        raffle.addPrize("", "coffee mug");
        raffle.addPrize("   ", "coffee mug");

        raffle.addPrize("Mug", null);
        raffle.addPrize("Mug", "");
        raffle.addPrize("Mug", "   ");

        // * nothing was added
        assertEquals(0, raffle.getPrizeCount());
        assertEquals("", raffle.listPrizes());
    }


    // ----------------------------------------------------------
    /**
     * Tests draw() with one ticket and one prize, so the winner is known.
     */
    public void testDraw()
    {
        raffle.addTickets("ehcks77", "Logan", 1);
        raffle.addPrize("Mug", "coffee mug");

        Result result = raffle.draw();

        assertNotNull(result);
        assertEquals(new RaffleTicket("ehcks77", "Logan"), result.getTicket());
        assertEquals(new Prize("Mug", "coffee mug"), result.getPrize());

        // * the drawn ticket and prize were removed
        assertEquals(0, raffle.getTicketCount());
        assertEquals(0, raffle.getPrizeCount());
    }


    // ----------------------------------------------------------
    /**
     * Tests draw() when there are no tickets or no prizes.
     */
    public void testDrawEmpty()
    {
        // * nothing at all
        assertNull(raffle.draw());

        // * tickets but no prizes
        raffle.addTickets("ehcks77", "Logan", 2);
        assertNull(raffle.draw());
        assertEquals(2, raffle.getTicketCount());

        // * prizes but no tickets
        Raffle other = new Raffle();
        other.addPrize("Mug", "coffee mug");
        assertNull(other.draw());
        assertEquals(1, other.getPrizeCount());

        // * a failed draw is not saved as a result
        assertEquals("", raffle.listResults());
        assertEquals("", other.listResults());
    }


    // ----------------------------------------------------------
    /**
     * Tests that draw() removes one ticket and one prize every time.
     */
    public void testDrawUntilPrizesRunOut()
    {
        raffle.addTickets("ehcks77", "Logan", 2);
        raffle.addTickets("david123", "David", 1);
        raffle.addPrize("Mug", "coffee mug");
        raffle.addPrize("Hat", "big hat");

        assertNotNull(raffle.draw());
        assertEquals(2, raffle.getTicketCount());
        assertEquals(1, raffle.getPrizeCount());

        assertNotNull(raffle.draw());
        assertEquals(1, raffle.getTicketCount());
        assertEquals(0, raffle.getPrizeCount());

        // * no prizes left, so the last ticket stays
        assertNull(raffle.draw());
        assertEquals(1, raffle.getTicketCount());
    }


    // ----------------------------------------------------------
    /**
     * Tests hasNameConflict()
     */
    public void testHasNameConflict()
    {
        raffle.addTickets("ehcks77", "Logan", 2);
        raffle.addTickets("david123", "David", 1);

        // * the id belongs to a different name
        assertTrue(raffle.hasNameConflict("ehcks77", "David"));
        assertTrue(raffle.hasNameConflict("david123", "Logan"));
        // * upper and lower case are not the same name
        assertTrue(raffle.hasNameConflict("ehcks77", "logan"));

        // * the same id and name is not a conflict
        assertFalse(raffle.hasNameConflict("ehcks77", "Logan"));
        // * an id nobody uses yet
        assertFalse(raffle.hasNameConflict("imnotlogan", "Logan"));
    }


    // ----------------------------------------------------------
    /**
     * Tests hasNameConflict() with blank input and with no tickets.
     */
    public void testHasNameConflictBadInput()
    {
        // * an empty raffle has no conflicts
        assertFalse(raffle.hasNameConflict("ehcks77", "Logan"));

        raffle.addTickets("ehcks77", "Logan", 1);

        assertFalse(raffle.hasNameConflict(null, "David"));
        assertFalse(raffle.hasNameConflict("  ", "David"));
        assertFalse(raffle.hasNameConflict("ehcks77", null));
        assertFalse(raffle.hasNameConflict("ehcks77", "  "));
    }


    // ----------------------------------------------------------
    /**
     * Tests getTicketCount()
     */
    public void testGetTicketCount()
    {
        raffle.addTickets("ehcks77", "Logan", 4);
        assertEquals(4, raffle.getTicketCount());

        raffle.addPrize("Mug", "coffee mug");
        raffle.draw();
        assertEquals(3, raffle.getTicketCount());
    }


    // ----------------------------------------------------------
    /**
     * Tests getTicketCount() with no tickets and after a bad add.
     */
    public void testGetTicketCountEmpty()
    {
        assertEquals(0, raffle.getTicketCount());

        raffle.addTickets("ehcks77", "Logan", 0);
        assertEquals(0, raffle.getTicketCount());
    }


    // ----------------------------------------------------------
    /**
     * Tests getPrizeCount()
     */
    public void testGetPrizeCount()
    {
        raffle.addPrize("Mug", "coffee mug");
        raffle.addPrize("Hat", "big hat");
        assertEquals(2, raffle.getPrizeCount());

        raffle.addTickets("ehcks77", "Logan", 1);
        raffle.draw();
        assertEquals(1, raffle.getPrizeCount());
    }


    // ----------------------------------------------------------
    /**
     * Tests getPrizeCount() with no prizes and after a bad add.
     */
    public void testGetPrizeCountEmpty()
    {
        assertEquals(0, raffle.getPrizeCount());

        raffle.addPrize("", "");
        assertEquals(0, raffle.getPrizeCount());
    }


    // ----------------------------------------------------------
    /**
     * Tests listTickets()
     */
    public void testListTickets()
    {
        raffle.addTickets("ehcks77", "Logan", 2);
        raffle.addTickets("david123", "David", 1);

        // * the first ticket added is number 1
        assertEquals("1. Logan (ID: ehcks77)\n"
            + "2. Logan (ID: ehcks77)\n"
            + "3. David (ID: david123)\n", raffle.listTickets());
    }


    // ----------------------------------------------------------
    /**
     * Tests listTickets() with no tickets.
     */
    public void testListTicketsEmpty()
    {
        assertEquals("", raffle.listTickets());

        // * a rejected ticket is not listed
        raffle.addTickets("ehcks77", "  ", 1);
        assertEquals("", raffle.listTickets());
    }


    // ----------------------------------------------------------
    /**
     * Tests listPrizes()
     */
    public void testListPrizes()
    {
        raffle.addPrize("Mug", "coffee mug");
        raffle.addPrize("Hat", "big hat");

        // * the first prize added is number 1
        assertEquals("1. Mug - coffee mug\n"
            + "2. Hat - big hat\n", raffle.listPrizes());
    }


    // ----------------------------------------------------------
    /**
     * Tests listPrizes() with no prizes.
     */
    public void testListPrizesEmpty()
    {
        assertEquals("", raffle.listPrizes());

        // * a rejected prize is not listed
        raffle.addPrize("Mug", null);
        assertEquals("", raffle.listPrizes());
    }


    // ----------------------------------------------------------
    /**
     * Tests listResults()
     */
    public void testListResults()
    {
        raffle.addTickets("ehcks77", "Logan", 1);
        raffle.addPrize("Mug", "coffee mug");
        raffle.draw();

        assertEquals("1. Logan (ID: ehcks77) won Mug - coffee mug\n",
            raffle.listResults());

        // * the second drawing goes under the first one
        raffle.addTickets("david123", "David", 1);
        raffle.addPrize("Hat", "big hat");
        raffle.draw();

        assertEquals("1. Logan (ID: ehcks77) won Mug - coffee mug\n"
            + "2. David (ID: david123) won Hat - big hat\n",
            raffle.listResults());
    }


    // ----------------------------------------------------------
    /**
     * Tests listResults() before anything was drawn.
     */
    public void testListResultsEmpty()
    {
        assertEquals("", raffle.listResults());

        // * a draw with no prizes does not make a result
        raffle.addTickets("ehcks77", "Logan", 1);
        raffle.draw();
        assertEquals("", raffle.listResults());
    }
}
