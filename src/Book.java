/**
 * The Book class contains all the information about the book such as its ID, name
 * author, publisher, printYear, number of copies, number of copies that are available,
 * the category and the library that it's and a field to check if at least one of the
 * copies of this book is borrowed. With all the necessary getter and setters.
 *
 * @author Ashkan Chaji
 * @version 1.0 (25.Mar.2024)
 */

public class Book {
    private String id;
    private String name;
    private String author;
    private String publisher;
    private String printYear;
    private Integer copyCount;
    private Integer copyCountNow;
    private Category category;
    private Library library;
    private boolean borrowed;

    /**
     * A constructor that initializes all the fields.
     *
     * @param id Book ID
     * @param name Book name (converted to lower case for search)
     * @param author Book's author (converted to lower case for search)
     * @param publisher Book's publisher (converted to lower case for search)
     * @param printYear Book's print year
     * @param copyCount number of copies (copyCountNow is the same at first)
     * @param category Book's category
     * @param library Book's library
     */

    public Book(String id, String name, String author, String publisher,
                String printYear, String copyCount, Category category,
                Library library) {
        this.id = id;
        this.name = name.toLowerCase();
        this.author = author.toLowerCase();
        this.publisher = publisher.toLowerCase();
        this.printYear = printYear;
        this.copyCount = Integer.parseInt(copyCount);
        copyCountNow = Integer.parseInt(copyCount);
        this.category = category;
        this.library = library;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author.toLowerCase();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher.toLowerCase();
    }

    public String getPrintYear() {
        return printYear;
    }

    public void setPrintYear(String printYear) {
        this.printYear = printYear;
    }

    public Integer getCopyCount() {
        return copyCount;
    }

    /**
     * sets the new number of total copies of the books and adds the differance of
     * the new and the old copy counts to copyCountNow.
     *
     * @param copyCount new number of copy counts
     */

    public void setCopyCount(String copyCount) {
        int diff = Integer.parseInt(copyCount) - this.copyCount;
        copyCountNow += diff;
        if (copyCountNow < 0){copyCountNow = 0;}
        this.copyCount = Integer.parseInt(copyCount);
    }

    public Integer getCopyCountNow() {
        return copyCountNow;
    }

    public void setCopyCountNow(int copyCountNow) {
        this.copyCountNow = copyCountNow;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }
}
