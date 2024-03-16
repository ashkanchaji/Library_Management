public class Borrow {
    private final String libraryID;
    private final String bookID;
    private final String date;
    private final String time;

    public Borrow(String libraryID, String bookID, String date, String time) {
        this.libraryID = libraryID;
        this.bookID = bookID;
        this.date = date;
        this.time = time;
    }
}
