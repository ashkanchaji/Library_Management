public class Thesis {
    private String id;
    private String name;
    private String studentName;
    private String professorName;
    private String defenceYear;
    private Category category;
    private Library library;
    private boolean borrowed;

    public Thesis(String id, String name, String studentName, String professorName,
                  String defenceYear, Category category, Library library) {
        this.id = id;
        this.name = name.toLowerCase();
        this.studentName = studentName.toLowerCase();
        this.professorName = professorName.toLowerCase();
        this.defenceYear = defenceYear;
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName.toLowerCase();
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName.toLowerCase();
    }

    public String getDefenceYear() {
        return defenceYear;
    }

    public void setDefenceYear(String defenceYear) {
        this.defenceYear = defenceYear;
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
