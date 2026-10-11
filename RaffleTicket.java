// -------------------------------------------------------------------------
/**
 * Represents one raffle ticket of a participant.
 *
 * @author Dochan (Logan) Moon
 * @version 11 Oct 2026
 */
public class RaffleTicket
{

    // ~ Fields ................................................................

    private String participantID;
    private String name;

    // ~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a ticket for the participant.
     *
     * @param participantID the id of the participant
     * @param name the name of the participant
     */
    public RaffleTicket(String participantID, String name)
    {
        this.participantID = participantID;
        this.name = name;
    }

    // ~ Public Methods ........................................................


    // ----------------------------------------------------------
    /**
     * Gets the participant's id.
     *
     * @return the id of the participant
     */
    public String getParticipantID()
    {
        return participantID;
    }


    // ----------------------------------------------------------
    /**
     * Gets the participant's name.
     *
     * @return the name of the participant
     */
    public String getName()
    {
        return name;
    }


    // ----------------------------------------------------------
    /**
     * Checks if two tickets have the same id and name.
     *
     * @param other the object to compare against
     * @return true if both tickets belong to the same participant
     */
    @Override
    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        }
        if (other == null || !(other instanceof RaffleTicket))
        {
            return false;
        }

        RaffleTicket that = (RaffleTicket)other;

        return participantID.equals(that.participantID)
            && name.equals(that.name);
    }


    // ----------------------------------------------------------
    /**
     * Gets the ticket as text.
     *
     * @return the name and id of the participant
     */
    @Override
    public String toString()
    {
        return name + " (ID: " + participantID + ")";
    }
}
