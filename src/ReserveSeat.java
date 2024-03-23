public class ReserveSeat {
    private final String personID;
    private final String libraryID;
    private final String date;
    private final String startTime;
    private final String endTime;

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
