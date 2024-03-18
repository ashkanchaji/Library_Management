public class Borrow {
    private final String libraryID;
    private final String writingID;
    private final String date;
    private final String time;

    public Borrow(String libraryID, String writingID, String date, String time) {
        this.libraryID = libraryID;
        this.writingID = writingID;
        this.date = date;
        this.time = time;
    }

    public String getLibraryID() {
        return libraryID;
    }

    public String getWritingID() {
        return writingID;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
