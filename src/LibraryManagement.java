import java.time.LocalDateTime;
import java.time.Duration;
import java.util.*;

public class LibraryManagement {

    public static HashMap<String, Library> libraries = new HashMap<>();
    public static HashMap<String, Category> categories = new HashMap<>();
    public static HashMap<String, Student> students = new HashMap<>();
    public static HashMap<String, Staff> staff = new HashMap<>();
    public static HashMap<String, Book> books = new HashMap<>();
    public static HashMap<String, Thesis> theses = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            if (input.equals("finish")) {
                break;
            }

            processInput(input);
        }
    }

    private static void processInput(String input) {
        String[] commands = input.split("#");

        String[] info = commands[1].split("\\|");

        switch(commands[0]){
            case "add-library" :
                if (addLibrary(info)){
                    System.out.println("success");
                } else {
                    System.out.println("duplicate-id");
                }
                break;
            case "add-category" :
                if (addCategory(info)){
                    System.out.println("success");
                } else {
                    System.out.println("duplicate-id");
                }
                break;
            case "add-book" :
                // *** IS THIS WAY CORRECT IN TEST CASES ? ***
                if ((!info[6].equals("null") && !categories.containsKey(info[6])) ||
                        !libraries.containsKey(info[7])){
                    System.out.println("not-found");
                } else if (addBook(info)){
                    System.out.println("success");
                } else {
                    System.out.println("duplicate-id");
                }
                break;
            case "edit-book" :
                if (editBook(info)){
                    System.out.println("success");
                } else {
                    System.out.println("not-found");
                }
                break;
            case "remove-book" :
                removeBook(info);
                break;
            case "add-thesis" :
                // *** IS THIS WAY CORRECT IN TEST CASES ? ***
                if ((!info[5].equals("null") && !categories.containsKey(info[5])) ||
                        !libraries.containsKey(info[6])){
                    System.out.println("not-found");
                } else if (addThesis(info)){
                    System.out.println("success");
                } else {
                    System.out.println("duplicate-id");
                }
                break;
            case "edit-thesis" :
                if (editThesis(info)){
                    System.out.println("success");
                } else {
                    System.out.println("not-found");
                }
                break;
            case "remove-thesis" : // is the order OK?
                removeThesis(info);
                break;
            case "add-student" :
                if (addStudent(info)) {
                    System.out.println("success");
                } else {
                    System.out.println("duplicate-id");
                }
                break;
            case "edit-student" :
                if (editStudent(info)){
                    System.out.println("success");
                } else {
                    System.out.println("not-found");
                }
                break;
            case "remove-student" :
                removeStudent(info);
                break;
            case "add-staff" :
                if (addStaff(info)) {
                    System.out.println("success");
                } else {
                    System.out.println("duplicate-id");
                }
                break;
            case "edit-staff" :
                if (editStaff(info)){
                    System.out.println("success");
                } else {
                    System.out.println("not-found");
                }
                break;
            case "remove-staff" :
                removeStaff(info);
                break;
            case "borrow" :
                borrow(info);
                break;
            case "return" :
                returnSource(info);
                break;
            case "search" :
                search(info);
                break;
        }
    }

    private static boolean addLibrary (String[] info){
        // 0: id, 1: name, 2: establishYear, 3: tableCount, 4: address

        if (libraries.containsKey(info[0])){
            return false;
        } else {
            Library library = new Library(info[0], info[1], info[2], info[3], info[4]);
            libraries.put(info[0], library);
            return true;
        }
    }

    private static boolean addCategory (String[] info){
        // 0: id, 1: name

        if (categories.containsKey(info[0])){
            return false;
        } else {
            Category category = new Category(info[0], info[1]);
            categories.put(info[0], category);
            return true;
        }
    }

    private static boolean addBook (String[] info){
        // 0: id, 1: name, 2: author, 3: publisher, 4: printYear, 5: copyCount,
        // 6: category, 7: library

        Library library = libraries.get(info[7]);

        if (library.books.containsKey(info[0])){
            return false;
        } else {
            Category category = categories.getOrDefault(info[6], null);

            Book book = new Book(info[0], info[1], info[2], info[3], info[4], info[5],
                    category, library);

            library.books.put(info[0], book);
            if (!books.containsKey(info[0])){
                books.put(info[0], book);
            }
            return true;
        }
    }

    private static boolean editBook (String[] info){
        // 0: bookID, 1: libraryID, 2: name, 3: author, 4: publisher, 5: printYear,
        // 6: copyCount, 7: category

        Library library = libraries.get(info[1]);

        if ((!info[1].equals("-") && !libraries.containsKey(info[1])) ||
                (!info[0].equals("-") && !library.books.containsKey(info[0])) ||
                (!info[7].equals("-") && !categories.containsKey(info[7]))){
            return false;
        } else {
            if (!info[2].equals("-")){
                library.books.get(info[0]).setName(info[2]);
            }
            if (!info[3].equals("-")){
                library.books.get(info[0]).setAuthor(info[3]);
            }
            if (!info[4].equals("-")){
                library.books.get(info[0]).setPublisher(info[4]);
            }
            if (!info[5].equals("-")){
                library.books.get(info[0]).setPrintYear(info[5]);
            }
            if (!info[6].equals("-")){
                library.books.get(info[0]).setCopyCount(info[6]);
            }
            if (!info[7].equals("-")){
                Category category = categories.getOrDefault(info[7], null);
                library.books.get(info[0]).setCategory(category);
            }
            return true;
        }
    }

    private static void removeBook(String[] info){
        // 0: bookID, 1: libraryID

        Library library = libraries.get(info[1]);

        if (!libraries.containsKey(info[1]) || !library.books.containsKey(info[0])) {
            System.out.println("not-found");
        } else if (libraries.get(info[1]).books.get(info[0]).isBorrowed()){
            System.out.println("not-allowed");
        } else {
            library.books.remove(info[0]);
            System.out.println("success");
        }
    }

    private static boolean addThesis (String[] info){
        // 0: thesisID, 1: name, 2: studentName, 3: professorName, 4: defenceYear
        // 5: category, 6: library

        Library library = libraries.get(info[6]);

        if (library.thesis.containsKey(info[0])){
            return false;
        } else {
            Category category = categories.getOrDefault(info[5], null);

            Thesis thesis = new Thesis(info[0], info[1], info[2], info[3], info[4],
                    category, library);

            library.thesis.put(info[0], thesis);
            if (!theses.containsKey(info[0])){
                theses.put(info[0], thesis);
            }
            return true;
        }
    }

    private static boolean editThesis (String[] info){
        // 0: thesisID, 1: libraryID, 2: name, 3: studentName, 4: professorName,
        // 5: defenceYear, 6: category

        Library library = libraries.get(info[1]);

        if ((!info[1].equals("-") && !libraries.containsKey(info[1])) ||
                (!info[0].equals("-") && !library.thesis.containsKey(info[0])) ||
                (!info[6].equals("-") && !categories.containsKey(info[6]))){
            return false;
        } else {
            if (!info[2].equals("-")){
                library.thesis.get(info[0]).setName(info[2]);
            }
            if (!info[3].equals("-")){
                library.thesis.get(info[0]).setStudentName(info[3]);
            }
            if (!info[4].equals("-")){
                library.thesis.get(info[0]).setProfessorName(info[4]);
            }
            if (!info[5].equals("-")){
                library.thesis.get(info[0]).setDefenceYear(info[5]);
            }
            if (!info[6].equals("-")){
                Category category = categories.getOrDefault(info[6], null);
                library.thesis.get(info[0]).setCategory(category);
            }
            return true;
        }
    }

    private static void removeThesis (String[] info){
        // 0: thesisId, 1: libraryId

        Library library = libraries.get(info[1]);

        if (!libraries.containsKey(info[1]) || !library.thesis.containsKey(info[0])) {
            System.out.println("not-found");
        } else if (libraries.get(info[1]).thesis.get(info[0]).isBorrowed()){
            System.out.println("not-allowed");
        } else {
            library.thesis.remove(info[0]);
            System.out.println("success");
        }
    }

    private static boolean addStudent(String[] info){
        // 0: id, 1: password, 2: firstName, 3: lastName, 4: nationalID,
        // 5: birthYear, 6: address

        if (students.containsKey(info[0])){
            return false;
        } else {
            Student student = new Student(info);
            students.put(info[0], student);
            return true;
        }
    }

    private static boolean editStudent (String[] info){
        // 0: id, 1: password, 2: firstName, 3: lastName, 4: nationalID,
        // 5: birthYear, 6: address

        if (!students.containsKey(info[0])){
            return false;
        } else {
            Student student = students.get(info[0]);

            student.setPassword(info[1]);
            student.setFirstName(info[2]);
            student.setLastName(info[3]);
            student.setNationalId(info[4]);
            student.setBirthYear(info[5]);
            student.setAddress(info[6]);

            students.replace(info[0], student);
            return true;
        }
    }

    private static void removeStudent (String[] info){
        // 0: id

        if (!students.containsKey(info[0])){
            System.out.println("not-found");
        } else if (!students.get(info[0]).getBorrowedBooks().isEmpty() ||
                    students.get(info[0]).getPenalty() != 0){
            System.out.println("not-allowed");
        } else {
            students.remove(info[0]);
            System.out.println("success");
        }
    }

    private static boolean addStaff (String[] info){
        // 0: id, 1: password, 2: firstName, 3: lastName, 4: nationalID,
        // 5: birthYear, 6: address

        if (staff.containsKey(info[0])){
            return false;
        } else {
            Staff newStaff = new Staff(info);
            staff.put(info[0], newStaff);
            return true;
        }
    }

    private static boolean editStaff (String [] info){
        // 0: id, 1: password, 2: firstName, 3: lastName, 4: nationalID,
        // 5: birthYear, 6: address

        if (!staff.containsKey(info[0])){
            return false;
        } else {
            Staff thisStaff = staff.get(info[0]);

            thisStaff.setPassword(info[1]);
            thisStaff.setFirstName(info[2]);
            thisStaff.setLastName(info[3]);
            thisStaff.setNationalID(info[4]);
            thisStaff.setBirthYear(info[5]);
            thisStaff.setAddress(info[6]);

            staff.replace(info[0], thisStaff);
            return true;
        }
    }

    private static void removeStaff (String[] info){
        // 0: id

        if (!staff.containsKey(info[0])){
            System.out.println("not-found");
        } else if (!staff.get(info[0]).getBorrowedBooks().isEmpty() ||
                    staff.get(info[0]).getPenalty() != 0){
            System.out.println("not-allowed");
        }
        else {
            staff.remove(info[0]);
            System.out.println("success");
        }
    }

    private static void borrow (String[] info){ // sorry for the complex code but couldn't change it mid way
        // 0: personId, 1: password, 2: libraryID, 3: book/thesis ID
        // 4: date, 5: time

        // check if IDs are correct
        if ((!students.containsKey(info[0]) && !staff.containsKey(info[0])) ||
            !libraries.containsKey(info[2])){
            System.out.println("not-found");
            return;
        }

        Library library = libraries.get(info[2]);
        String writingType;
        if (!library.books.containsKey(info[3]) && !library.thesis.containsKey(info[3])){
            System.out.println("not-found");
            return;
        } else {
            writingType = library.books.containsKey(info[3]) ? "book" : "thesis";
        }

        // check if password is correct
        String password;
        String personType = students.containsKey(info[0]) ? "student" : "staff";
        if (personType.equals("student")){
            password = students.get(info[0]).getPassword();
        } else {
            password = staff.get(info[0]).getPassword();
        }
        if (!password.equals(info[1])){
            System.out.println("invalid-pass");
            return;
        }

        // check if borrowing is allowed
        if (writingType.equals("book")){
            if (library.books.get(info[3]).getCopyCountNow() == 0){
                System.out.println("not-allowed");
                return;
            }
        } else {
            if (library.thesis.get(info[3]).isBorrowed()){
                System.out.println("not-allowed");
                return;
            }
        }

        if (personType.equals("student")){
            if (students.get(info[0]).getBorrowedBooks().size() == 3){
                System.out.println("not-allowed");
                return;
            }
        } else {
            if (staff.get(info[0]).getBorrowedBooks().size() == 5){
                System.out.println("not-allowed");
                return;
            }
        }

        // borrow the book
        Borrow borrowedBook = new Borrow(info[2], info[3], info[4], info[5]);

        if (personType.equals("student")){
            ArrayList<Borrow> borrowedBooks = students.get(info[0]).getBorrowedBooks();
            borrowedBooks.add(borrowedBook);
            students.get(info[0]).setBorrowedBooks(borrowedBooks);
        } else {
            ArrayList<Borrow> borrowedBooks = staff.get(info[0]).getBorrowedBooks();
            borrowedBooks.add(borrowedBook);
            staff.get(info[0]).setBorrowedBooks(borrowedBooks);
        }

        if (writingType.equals("book")){
            int newCopyCount = library.books.get(info[3]).getCopyCountNow() - 1;
            library.books.get(info[3]).setCopyCountNow(newCopyCount);

            if (!library.books.get(info[3]).isBorrowed()){
                library.books.get(info[3]).setBorrowed(true);
            }
        } else {
            library.thesis.get(info[3]).setBorrowed(true);
        }

        System.out.println("success");
    }

    private static void returnSource (String[] info){
        // 0: personId, 1: password, 2: libraryID, 3: book/thesis ID
        // 4: date, 5: time

        Library library = libraries.getOrDefault(info[2], null);

        // check if library exists
        if (library == null) {
            System.out.println("not-found");
            return;
        }

        Student student = students.getOrDefault(info[0], null);
        Staff staff1 = staff.getOrDefault(info[0], null);
        Book book = library.books.getOrDefault(info[3], null);
        Thesis thesis = library.thesis.getOrDefault(info[3], null);

        // check if other IDs are correct
        if (student == null && staff1 == null || book == null && thesis == null) {
            System.out.println("not-found");
            return;
        }


        // check if person has borrowed the source
        Borrow borrowedSource = null;
        Iterator<Borrow> it;

        if (student != null){
            it = student.getBorrowedBooks().iterator();
        } else {
            it = staff1.getBorrowedBooks().iterator();
        }

        while (it.hasNext()){
            Borrow borrow = it.next();
            if (borrow.getWritingID().equals(info[3]) &&
                    borrow.getLibraryID().equals(info[2])){
                borrowedSource = borrow;
            }
        }

        if (borrowedSource == null){
            System.out.println("not-found");
            return;
        }

        // check if person's password is correct
        if (student != null){
            if (!student.getPassword().equals(info[1])){
                System.out.println("invalid-pass");
                return;
            }
        } else {
            if (!staff1.getPassword().equals(info[1])){
                System.out.println("invalid-pass");
                return;
            }
        }

        // check if person should be penalized
        String[] borrowDateInfo = borrowedSource.getDate().split("-");
        String[] borrowTimeInfo = borrowedSource.getTime().split(":");

        String[] returnDateInfo = info[4].split("-");
        String[] returnTimeInfo = info[5].split(":");

        LocalDateTime borrowDate = LocalDateTime.of(Integer.parseInt(borrowDateInfo[0]),
                                            Integer.parseInt(borrowDateInfo[1]),
                                            Integer.parseInt(borrowDateInfo[2]),
                                            Integer.parseInt(borrowTimeInfo[0]),
                                            Integer.parseInt(borrowTimeInfo[1]));
        LocalDateTime returnDate = LocalDateTime.of(Integer.parseInt(returnDateInfo[0]),
                                            Integer.parseInt(returnDateInfo[1]),
                                            Integer.parseInt(returnDateInfo[2]),
                                            Integer.parseInt(returnTimeInfo[0]),
                                            Integer.parseInt(returnTimeInfo[1]));
        Duration duration = Duration.between(borrowDate, returnDate);

        long hoursDifference = duration.toHours();

        String personType = student != null ? "student" : "staff";
        String sourceType = book != null ? "book" : "thesis";


        ArrayList<Borrow> borrowedSources = student != null ? student.getBorrowedBooks() : staff1.getBorrowedBooks();

        it = borrowedSources.iterator();

        while (it.hasNext()){
            Borrow borrow = it.next();
            if (borrow.getWritingID().equals(info[3]) &&
                    borrow.getLibraryID().equals(info[2])){
                it.remove();

                if (book != null){
                    int copyCount = libraries.get(info[2]).books.get(info[3]).getCopyCount();
                    int copyCountNow = libraries.get(info[2]).books.get(info[3]).getCopyCountNow();

                    int newCopyCount = copyCountNow + 1;
                    libraries.get(info[2]).books.get(info[3]).setCopyCountNow(newCopyCount);

                    if (copyCountNow == copyCount){
                        libraries.get(info[2]).books.get(info[3]).setBorrowed(false);
                    }
                } else {
                    libraries.get(info[2]).thesis.get(info[3]).setBorrowed(false);
                }
            }
        }

        long penalty = calculatePenalty(personType, sourceType, hoursDifference);

        if (penalty != 0 ){
            System.out.println(penalty);
            long personPenalties;
            if (personType.equals("student")){
                personPenalties = student.getPenalty() + penalty;
                student.setPenalty(personPenalties);
            } else {
                personPenalties = staff1.getPenalty() + penalty;
                staff1.setPenalty(personPenalties);
            }
            return;
        }

        System.out.println("success");
    }

    private static long calculatePenalty (String personType, String sourceType, long hoursDifference){
        long penaltyPerHour;
        long maxPenaltyTime;

        if (personType.equals("student")){
            penaltyPerHour = 50;
            maxPenaltyTime = sourceType.equals("book") ? 240 : 168;
        } else {
            penaltyPerHour = 100;
            maxPenaltyTime = sourceType.equals("book") ? 336 : 240;
        }

        if (hoursDifference > maxPenaltyTime){
            return (hoursDifference - maxPenaltyTime) * penaltyPerHour;
        } else {
            return 0;
        }
    }

    private static void search (String[] info){
        // 0: keyWord to search

        String keyWord = info[0];

        ArrayList<String> foundSources = new ArrayList<>();

        books.forEach((key, value) -> {
            if (value.getName().equals(keyWord) ||
                value.getAuthor().equals(keyWord) ||
                value.getPublisher().equals(keyWord)){
                foundSources.add(key);
            }
        });

        theses.forEach((key, value) -> {
            if (value.getName().equals(keyWord) ||
                value.getStudentName().equals(keyWord) ||
                value.getProfessorName().equals(keyWord)){
                foundSources.add(key);
            }
        });

        if (foundSources.isEmpty()){
            System.out.println("not-found");
            return;
        }

        Collections.sort(foundSources);

        for (int i = 0; i < foundSources.size(); i++) {
            if (i == 0){
                System.out.print(foundSources.get(i));
            } else {
                System.out.print("|" + foundSources.get(i));
            }
        }

        System.out.println();
    }
}