import java.util.HashMap;

/**
 * The library class contains the library ID, its name, established year, seat counts,
 * address which are all final, and two HashMaps, one for the books and the other for
 * the thesis that are in the library. And has all the getter and setters that are needed
 * for the fields.
 *
 * @author Ashkan Chaji
 * @version 1.0 (25.Mar.2024)
 */

public class Library {
    private final String id;
    private final String name;
    private final String establishYear;
    private final Integer tableCount;
    private final String address;
    // needed to be public for the ease of access
    public HashMap<String, Book> books;
    public HashMap<String, Thesis> thesis;

    /**
     * A constructor that initializes all the fields
     *
     * @param id library ID
     * @param name library name
     * @param establishYear library establish year
     * @param tableCount the number of seats in the library
     * @param address library address
     */

    public Library(String id, String name, String establishYear,
                   String tableCount, String address) {
        this.id = id;
        this.name = name;
        this.establishYear = establishYear;
        this.tableCount = Integer.parseInt(tableCount);
        this.address = address;
        books = new HashMap<>();
        thesis = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEstablishYear() {
        return establishYear;
    }

    public Integer getTableCount() {
        return tableCount;
    }

    public String getAddress() {
        return address;
    }

    public HashMap<String, Book> getBooks() {
        return books;
    }

    public void setBooks(HashMap<String, Book> books) {
        this.books = books;
    }

    public HashMap<String, Thesis> getThesis() {
        return thesis;
    }

    public void setThesis(HashMap<String, Thesis> thesis) {
        this.thesis = thesis;
    }
}
