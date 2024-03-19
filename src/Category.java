import java.util.HashSet;

public class Category {
    private final String id;
    private final String name;
    private HashSet<String> books;
    private HashSet<String> thesis;

    public Category(String id, String name){
        this.id = id;
        this.name = name;
        books = new HashSet<>();
        thesis = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HashSet<String> getBooks() {
        return books;
    }

    public void setBooks(HashSet<String> books) {
        this.books = books;
    }

    public HashSet<String> getThesis() {
        return thesis;
    }

    public void setThesis(HashSet<String> thesis) {
        this.thesis = thesis;
    }
}
