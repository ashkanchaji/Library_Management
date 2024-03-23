import java.util.HashMap;

public class Library {
    private final String id;
    private final String name;
    private final String establishYear;
    private final Integer tableCount;
    private final String address;
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

    public HashMap<String, Book> getBooks() {
        return books;
    }

    public HashMap<String, Thesis> getThesis() {
        return thesis;
    }

    public Integer getTableCount() {
        return tableCount;
    }
}
