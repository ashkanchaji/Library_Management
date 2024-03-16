import java.util.HashSet;

public class Category {
    private final String id;
    private final String name;

    private HashSet<Book> booksID; // needs to be matched with book, thesis class further

    public Category(String id, String name){
        this.id = id;
        this.name = name;
    }
}
