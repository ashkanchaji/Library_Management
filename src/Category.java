import java.util.HashSet;

/**
 * The category class contains the category ID and its name as final fields and
 * has two HashMaps that contains the books and the thesis that are in this category.
 * And it has all the necessary getter and setters for the fields.
 *
 * @author Ashkan Chaji
 * @version 1.0 (25.Mar.2024)
 */

public class Category {
    private final String id;
    private final String name;
    private HashSet<Book> books;
    private HashSet<Thesis> thesis;

    /**
     * A constructor that initializes all the fields.
     *
     * @param id Category ID
     * @param name Category name
     */

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
