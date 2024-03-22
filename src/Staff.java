import java.util.ArrayList;
import java.util.HashSet;

public class Staff {
    private final String id;
    private String password;
    private String firstName;
    private String lastName;
    private String nationalID;
    private String birthYear;
    private String address;
    private HashSet<ReserveSeat> reserveSeats;
    private long penalty;
    private ArrayList<Borrow> borrowedBooks;

    public Staff(String[] info) {
        // 0: id, 1: password, 2: firstName, 3: lastName, 4: nationalID,
        // 5: birthYear, 6: address

        this.id = info[0];
        this.password = info[1];
        this.firstName = info[2].toLowerCase();
        this.lastName = info[3].toLowerCase();
        this.nationalID = info[4];
        this.birthYear = info[5];
        this.address = info[6];
        reserveSeats = new HashSet<>();
        penalty = 0;
        borrowedBooks = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (!password.equals("-")){
            this.password = password;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (!firstName.equals("-")){
            this.firstName = firstName.toLowerCase();
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (!lastName.equals("-")){
            this.lastName = lastName.toLowerCase();
        }
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        if (!nationalID.equals("-")){
            this.nationalID = nationalID;
        }
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        if (!birthYear.equals("-")){
            this.birthYear = birthYear;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (!address.equals("-")){
            this.address = address;
        }
    }

    public HashSet<ReserveSeat> getReserveSeats() {
        return reserveSeats;
    }

    public void setReserveSeats(HashSet<ReserveSeat> reserveSeats) {
        this.reserveSeats = reserveSeats;
    }

    public long getPenalty() {
        return penalty;
    }

    public void setPenalty(long penalty) {
        this.penalty = penalty;
    }

    public ArrayList<Borrow> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(ArrayList<Borrow> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
