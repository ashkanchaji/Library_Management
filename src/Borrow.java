/**
 * The Borrow class contain all the necessary information about the borrowed
 * source that will be used in other commands and method. These infos are the
 * borrowed source's library ID, its own ID, the user's ID, the date and time
 * that the source was borrowed and the max amount of hours that the source can
 * be borrowed, all as final fields. With all the necessary getters.
 *
 * @author Ashkan Chaji
 * @version 1.0 (25.Mar.2024)
 */

public class Borrow {
    private final String libraryID;
    private final String writingID;
    private final String personId;
    private final String date;
    private final String time;
    private final int maxBorrowTime;

    /**
     * A constructor that initializes all the fields.
     *
     * @param libraryID source's library ID
     * @param writingID source ID
     * @param personId user ID
     * @param date date of borrowing
     * @param time time of borrowing
     * @param maxBorrowTime max amount of hours that could be borrowed
     */

    public Borrow(String libraryID, String writingID, String personId, String date, String time,
                  int maxBorrowTime) {
        this.libraryID = libraryID;
        this.writingID = writingID;
        this.personId = personId;
        this.date = date;
        this.time = time;
        this.maxBorrowTime = maxBorrowTime;
    }

    public String getLibraryID() {
        return libraryID;
    }

    public String getWritingID() {
        return writingID;
    }

    public String getPersonId() {
        return personId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getMaxBorrowTime() {
        return maxBorrowTime;
    }
}
