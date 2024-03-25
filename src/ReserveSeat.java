/**
 * The ReserveSeat class contains all the necessary information about a users
 * reserved seat in a library such as user's ID, library ID, the reserve date,
 * start time of the reserve and the end time of the reserve, all as final fields.
 * With all the necessary getters.
 *
 * @author Ashkan Chaji
 * @version 1.0 (25.Mar.2024)
 */

public class ReserveSeat {
    private final String personID;
    private final String libraryID;
    private final String date;
    private final String startTime;
    private final String endTime;

    /**
     * A constructor that initializes all the fields.
     *
     * @param personID user's ID
     * @param libraryID library ID
     * @param date date of reserve
     * @param startTime starting time of the reserve
     * @param endTime end time of the reserve
     */

    public ReserveSeat(String personID, String libraryID, String date, String startTime, String endTime) {
        this.personID = personID;
        this.libraryID = libraryID;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getPersonID() {
        return personID;
    }

    public String getLibraryID() {
        return libraryID;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
}
