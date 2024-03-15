public class Book {
    private String id;
    private String name;
    private String author;
    private String publisher;
    private String printYear;
    private Integer copyCount;
    private Category category;
    private Library library;

    public Book(String id, String name, String author, String publisher,
                String printYear, String copyCount, Category category,
                Library library) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.printYear = printYear;
        this.copyCount = Integer.parseInt(copyCount);
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
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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

    public void setCopyCount(Integer copyCount) {
        this.copyCount = copyCount;
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
}
