import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the RaffleTicket class.
 *
 * @author Dochan (Logan) Moon
 * @version 12 Oct 2026
 */
public class RaffleTicketTest
    extends TestCase
{

    // ~ Fields ................................................................

    private RaffleTicket ticket;

    // ~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Sets up each test method.
     */
    public void setUp()
    {
        ticket = new RaffleTicket("ehcks77", "Logan");
    }

    // ~ Public Methods ........................................................


    // ----------------------------------------------------------
    /**
     * Tests getParticipantID()
     */
    public void testGetParticipantID()
    {
        assertEquals("ehcks77", ticket.getParticipantID());
    }


    // ----------------------------------------------------------
    /**
     * Tests getName()
     */
    public void testGetName()
    {
        assertEquals("Logan", ticket.getName());
    }


    // ----------------------------------------------------------
    /**
     * Tests the getters when the ticket was made with empty text.
     */
    public void testGettersWithEmptyText()
    {
        // * the ticket does not check its values, Raffle does
        RaffleTicket empty = new RaffleTicket("", "");

        assertEquals("", empty.getParticipantID());
        assertEquals("", empty.getName());
    }


    // ----------------------------------------------------------
    /**
     * Tests equals() with tickets that are the same.
     */
    public void testEqualsSameTicket()
    {
        // * the same object
        assertTrue(ticket.equals(ticket));
        // * a different object with the same id and name
        assertTrue(ticket.equals(new RaffleTicket("ehcks77", "Logan")));
    }


    // ----------------------------------------------------------
    /**
     * Tests equals() with tickets that are different.
     */
    public void testEqualsDifferentTicket()
    {
        // * different id
        assertFalse(ticket.equals(new RaffleTicket("david123", "Logan")));
        // * different name
        assertFalse(ticket.equals(new RaffleTicket("ehcks77", "David")));
        // * both different
        assertFalse(ticket.equals(new RaffleTicket("david123", "David")));
        // * upper and lower case are not the same
        assertFalse(ticket.equals(new RaffleTicket("EHCKS77", "logan")));
    }


    // ----------------------------------------------------------
    /**
     * Tests equals() with null and with other classes.
     */
    public void testEqualsBadInput()
    {
        assertFalse(ticket.equals(null));
        assertFalse(ticket.equals("ehcks77"));
        assertFalse(ticket.equals(new Prize("ehcks77", "Logan")));
    }


    // ----------------------------------------------------------
    /**
     * Tests toString()
     */
    public void testToString()
    {
        assertEquals("Logan (ID: ehcks77)", ticket.toString());
    }


    // ----------------------------------------------------------
    /**
     * Tests toString() when the ticket was made with empty text.
     */
    public void testToStringWithEmptyText()
    {
        RaffleTicket empty = new RaffleTicket("", "");

        assertEquals(" (ID: )", empty.toString());
    }
}
