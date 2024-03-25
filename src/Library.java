import java.util.HashMap;

public class Library {
    private final String id;
    private final String name;
    private final String establishYear;
    private final Integer tableCount;
    private final String address;
    // needed to be public for the ease of access
    public HashMap<String, Book> books;
    public HashMap<String, Thesis> thesis;

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
