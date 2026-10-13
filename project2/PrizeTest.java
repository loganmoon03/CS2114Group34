import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the Prize class.
 *
 * @author Dochan (Logan) Moon
 * @version 12 Oct 2026
 */
public class PrizeTest
    extends TestCase
{

    // ~ Fields ................................................................

    private Prize prize;

    // ~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Sets up each test method.
     */
    public void setUp()
    {
        prize = new Prize("Mug", "coffee mug");
    }

    // ~ Public Methods ........................................................


    // ----------------------------------------------------------
    /**
     * Tests getName()
     */
    public void testGetName()
    {
        assertEquals("Mug", prize.getName());
    }


    // ----------------------------------------------------------
    /**
     * Tests getDescription()
     */
    public void testGetDescription()
    {
        assertEquals("coffee mug", prize.getDescription());
    }


    // ----------------------------------------------------------
    /**
     * Tests the getters when the prize was made with empty text.
     */
    public void testGettersWithEmptyText()
    {
        // * the prize does not check its values, Raffle does
        Prize empty = new Prize("", "");

        assertEquals("", empty.getName());
        assertEquals("", empty.getDescription());
    }


    // ----------------------------------------------------------
    /**
     * Tests equals() with prizes that are the same.
     */
    public void testEqualsSamePrize()
    {
        // * the same object
        assertTrue(prize.equals(prize));
        // * a different object with the same name and description
        assertTrue(prize.equals(new Prize("Mug", "coffee mug")));
    }


    // ----------------------------------------------------------
    /**
     * Tests equals() with prizes that are different.
     */
    public void testEqualsDifferentPrize()
    {
        // * different name
        assertFalse(prize.equals(new Prize("Hat", "coffee mug")));
        // * different description
        assertFalse(prize.equals(new Prize("Mug", "Plain white mug")));
        // * both different
        assertFalse(prize.equals(new Prize("Hat", "big hat")));
        // * upper and lower case are not the same
        assertFalse(prize.equals(new Prize("mug", "COFFEE MUG")));
    }


    // ----------------------------------------------------------
    /**
     * Tests equals() with null and with other classes.
     */
    public void testEqualsBadInput()
    {
        assertFalse(prize.equals(null));
        assertFalse(prize.equals("Mug"));
        assertFalse(prize.equals(new RaffleTicket("Mug", "coffee mug")));
    }


    // ----------------------------------------------------------
    /**
     * Tests toString()
     */
    public void testToString()
    {
        assertEquals("Mug - coffee mug", prize.toString());
    }


    // ----------------------------------------------------------
    /**
     * Tests toString() when the prize was made with empty text.
     */
    public void testToStringWithEmptyText()
    {
        Prize empty = new Prize("", "");

        assertEquals(" - ", empty.toString());
    }
}
