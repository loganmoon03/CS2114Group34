// -------------------------------------------------------------------------
/**
 * Represents one prize of the raffle.
 *
 * @author Dochan (Logan) Moon
 * @version 11 Oct 2026
 */
public class Prize
{

    // ~ Fields ................................................................

    private String name;
    private String description;

    // ~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a prize.
     *
     * @param name the name of the prize
     * @param description a short description of the prize
     */
    public Prize(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    // ~ Public Methods ........................................................


    // ----------------------------------------------------------
    /**
     * Gets the prize's name.
     *
     * @return the name of the prize
     */
    public String getName()
    {
        return name;
    }


    // ----------------------------------------------------------
    /**
     * Gets the prize's description.
     *
     * @return the description of the prize
     */
    public String getDescription()
    {
        return description;
    }


    // ----------------------------------------------------------
    /**
     * Checks if two prizes have the same name and description.
     *
     * @param other the object to compare against
     * @return true if both prizes are the same
     */
    @Override
    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        }
        if (other == null || !(other instanceof Prize))
        {
            return false;
        }

        Prize that = (Prize)other;

        return name.equals(that.name)
            && description.equals(that.description);
    }


    // ----------------------------------------------------------
    /**
     * Gets the prize as text.
     *
     * @return the name and description of the prize
     */
    @Override
    public String toString()
    {
        return name + " - " + description;
    }
}
