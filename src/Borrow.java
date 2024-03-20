public class Borrow {
    private final String libraryID;
    private final String writingID;
    private final String personId;
    private final String date;
    private final String time;
    private final int maxBorrowTime;

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
