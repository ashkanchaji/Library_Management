import java.util.HashSet;

public class Category {
    private final String id;
    private final String name;
    private HashSet<Book> books;
    private HashSet<Thesis> thesis;

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

    public HashSet<Book> getBooks() {
        return books;
    }

    public void setBooks(HashSet<Book> books) {
        this.books = books;
    }

    public HashSet<Thesis> getThesis() {
        return thesis;
    }

    public void setThesis(HashSet<Thesis> thesis) {
        this.thesis = thesis;
    }
}
