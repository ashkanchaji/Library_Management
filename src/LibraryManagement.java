import java.time.LocalDateTime;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

public class LibraryManagement {

    public static HashMap<String, Library> libraries = new HashMap<>();
    public static HashMap<String, Category> categories = new HashMap<>();
    public static HashMap<String, Student> students = new HashMap<>();
    public static HashMap<String, Staff> staff = new HashMap<>();
    public static HashSet<Borrow> borrows = new HashSet<>();
    public static HashSet<ReserveSeat> reserveSeats = new HashSet<>();

    public static void main(String[] args) {
        Category nullCategory = new Category("null", "null");
        categories.put("null", nullCategory);

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            if (input.equals("finish")) {
                break;
            } else if (input.equals("report-penalties-sum")) {
                reportPenaltiesSum();
                continue;
            }

            processInput(input);
        }
    }

    private static void processInput(String input) {
        String[] commands = input.split("#");

        String[] info = commands[1].split("\\|");

        switch (commands[0]) {
            case "add-library":
                if (addLibrary(info)) {
                    System.out.println("success");
                } else {
                    System.out.println("duplicate-id");
                }
                break;
            case "add-category":
                if (addCategory(info)) {
                    System.out.println("success");
                } else {
                    System.out.println("duplicate-id");
                }
                break;
            case "add-book":
                if (!categories.containsKey(info[6]) || !libraries.containsKey(info[7])) {
                    System.out.println("not-found");
                } else if (addBook(info)) {
                    System.out.println("success");
                } else {
                    System.out.println("duplicate-id");
                }
                break;
            case "edit-book":
                if (editBook(info)) {
                    System.out.println("success");
                } else {
                    System.out.println("not-found");
                }
                break;
            case "remove-book":
                removeBook(info);
                break;
            case "add-thesis":
                if (!categories.containsKey(info[5]) || !libraries.containsKey(info[6])) {
                    System.out.println("not-found");
                } else if (addThesis(info)) {
                    System.out.println("success");
                } else {
                    System.out.println("duplicate-id");
                }
                break;
            case "edit-thesis":
                if (editThesis(info)) {
                    System.out.println("success");
                } else {
                    System.out.println("not-found");
                }
                break;
            case "remove-thesis": // is the order OK?
                removeThesis(info);
                break;
            case "add-student":
                if (addStudent(info)) {
                    System.out.println("success");
                } else {
                    System.out.println("duplicate-id");
                }
                break;
            case "edit-student":
                if (editStudent(info)) {
                    System.out.println("success");
                } else {
                    System.out.println("not-found");
                }
                break;
            case "remove-student":
                removeStudent(info);
                break;
            case "add-staff":
                if (addStaff(info)) {
                    System.out.println("success");
                } else {
                    System.out.println("duplicate-id");
                }
                break;
            case "edit-staff":
                if (editStaff(info)) {
                    System.out.println("success");
                } else {
                    System.out.println("not-found");
                }
                break;
            case "remove-staff":
                removeStaff(info);
                break;
            case "borrow":
                borrow(info);
                break;
            case "return":
                returnSource(info);
                break;
            case "search":
                search(info);
                break;
            case "search-user":
                searchUser(info);
                break;
            case "category-report":
                categoryReport(info);
                break;
            case "library-report":
                libraryReport(info);
                break;
            case "report-passed-deadline":
                reportPassedDeadline(info);
                break;
            case "reserve-seat":
                reserveSeat(info);
                break;
            default:
                break;
        }
    }

    private static boolean addLibrary(String[] info) {
        // 0: id, 1: name, 2: establishYear, 3: tableCount, 4: address

        if (libraries.containsKey(info[0])) {
            return false;
        } else {
            Library library = new Library(info[0], info[1], info[2], info[3], info[4]);
            libraries.put(info[0], library);
            return true;
        }
    }

    private static boolean addCategory(String[] info) {
        // 0: id, 1: name

        if (categories.containsKey(info[0])) {
            return false;
        } else {
            Category category = new Category(info[0], info[1]);
            categories.put(info[0], category);
            return true;
        }
    }

    private static boolean addBook(String[] info) {
        // 0: id, 1: name, 2: author, 3: publisher, 4: printYear, 5: copyCount,
        // 6: category, 7: library

        Library library = libraries.get(info[7]);

        if (library.books.containsKey(info[0])) {
            return false;
        } else {
            Category category = categories.get(info[6]);

            Book book = new Book(info[0], info[1], info[2], info[3], info[4], info[5],
                    category, library);

            HashSet<Book> categoryBooks = categories.get(info[6]).getBooks();
            categoryBooks.add(book);
            categories.get(info[6]).setBooks(categoryBooks);

            libraries.get(info[7]).books.put(info[0], book);
            return true;
        }
    }

    private static boolean editBook(String[] info) {
        // 0: bookID, 1: libraryID, 2: name, 3: author, 4: publisher, 5: printYear,
        // 6: copyCount, 7: category

        Library library = libraries.get(info[1]);

        if ((!info[1].equals("-") && !libraries.containsKey(info[1])) ||
                (!info[0].equals("-") && !library.books.containsKey(info[0])) ||
                (!info[7].equals("-") && !categories.containsKey(info[7]))) {
            return false;
        } else {
            if (!info[2].equals("-")) {
                library.books.get(info[0]).setName(info[2]);
            }
            if (!info[3].equals("-")) {
                library.books.get(info[0]).setAuthor(info[3]);
            }
            if (!info[4].equals("-")) {
                library.books.get(info[0]).setPublisher(info[4]);
            }
            if (!info[5].equals("-")) {
                library.books.get(info[0]).setPrintYear(info[5]);
            }
            if (!info[6].equals("-")) {
                library.books.get(info[0]).setCopyCount(info[6]);
            }
            if (!info[7].equals("-")) {
                Book book = library.books.get(info[0]);
                String oldCategory = library.books.get(info[0]).getCategory().getId();
                Category category = categories.get(info[7]);
                libraries.get(info[1]).books.get(info[0]).setCategory(category);

                HashSet<Book> newCategoryBooks = categories.get(info[7]).getBooks();
                newCategoryBooks.add(book);
                categories.get(info[7]).setBooks(newCategoryBooks);

                HashSet<Book> oldCategoryBooks = categories.get(oldCategory).getBooks();
                oldCategoryBooks.remove(book);
                categories.get(oldCategory).setBooks(oldCategoryBooks);
            }
            return true;
        }
    }

    private static void removeBook(String[] info) {
        // 0: bookID, 1: libraryID

        Library library = libraries.get(info[1]);

        if (!libraries.containsKey(info[1]) || !library.books.containsKey(info[0])) {
            System.out.println("not-found");
        } else if (libraries.get(info[1]).books.get(info[0]).isBorrowed()) {
            System.out.println("not-allowed");
        } else {
            boolean[] couldRemoveFromCategory = {true};
            libraries.forEach((id, library1) -> {
                if (!id.equals(info[1]) && library1.books.containsKey(info[0])) {
                    couldRemoveFromCategory[0] = false;
                }
            });
            if (couldRemoveFromCategory[0]) {
                Book book = library.books.get(info[0]);
                String category = library.books.get(info[0]).getCategory().getId();
                HashSet<Book> categoryBooks = categories.get(category).getBooks();
                categoryBooks.remove(book);
                categories.get(category).setBooks(categoryBooks);
            }

            libraries.get(info[1]).books.remove(info[0]);
            System.out.println("success");
        }
    }

    private static boolean addThesis(String[] info) {
        // 0: thesisID, 1: name, 2: studentName, 3: professorName, 4: defenceYear
        // 5: category, 6: library

        Library library = libraries.get(info[6]);

        if (library.thesis.containsKey(info[0])) {
            return false;
        } else {
            Category category = categories.get(info[5]);

            Thesis thesis = new Thesis(info[0], info[1], info[2], info[3], info[4],
                    category, library);

            HashSet<Thesis> categoryThesis = categories.get(info[5]).getThesis();
            categoryThesis.add(thesis);
            categories.get(info[5]).setThesis(categoryThesis);

            libraries.get(info[6]).thesis.put(info[0], thesis);
            return true;
        }
    }

    private static boolean editThesis(String[] info) {
        // 0: thesisID, 1: libraryID, 2: name, 3: studentName, 4: professorName,
        // 5: defenceYear, 6: category

        Library library = libraries.get(info[1]);

        if ((!info[1].equals("-") && !libraries.containsKey(info[1])) ||
                (!info[0].equals("-") && !library.thesis.containsKey(info[0])) ||
                (!info[6].equals("-") && !categories.containsKey(info[6]))) {
            return false;
        } else {
            if (!info[2].equals("-")) {
                library.thesis.get(info[0]).setName(info[2]);
            }
            if (!info[3].equals("-")) {
                library.thesis.get(info[0]).setStudentName(info[3]);
            }
            if (!info[4].equals("-")) {
                library.thesis.get(info[0]).setProfessorName(info[4]);
            }
            if (!info[5].equals("-")) {
                library.thesis.get(info[0]).setDefenceYear(info[5]);
            }
            if (!info[6].equals("-")) {
                Thesis thesis = library.thesis.get(info[0]);
                String oldCategory = library.thesis.get(info[0]).getCategory().getId();
                Category category = categories.get(info[6]);
                libraries.get(info[1]).thesis.get(info[0]).setCategory(category);

                HashSet<Thesis> newCategoryThesis = categories.get(info[6]).getThesis();
                newCategoryThesis.add(thesis);
                categories.get(info[6]).setThesis(newCategoryThesis);

                HashSet<Thesis> oldCategoryThesis = categories.get(oldCategory).getThesis();
                oldCategoryThesis.remove(thesis);
                categories.get(oldCategory).setThesis(oldCategoryThesis);
            }
            return true;
        }
    }

    private static void removeThesis(String[] info) {
        // 0: thesisId, 1: libraryId

        Library library = libraries.get(info[1]);

        if (!libraries.containsKey(info[1]) || !library.thesis.containsKey(info[0])) {
            System.out.println("not-found");
        } else if (libraries.get(info[1]).thesis.get(info[0]).isBorrowed()) {
            System.out.println("not-allowed");
        } else {
            boolean[] couldRemoveFromCategory = {true};
            libraries.forEach((id, library1) -> {
                if (!id.equals(info[1]) && library1.thesis.containsKey(info[0])) {
                    couldRemoveFromCategory[0] = false;
                }
            });
            if (couldRemoveFromCategory[0]) {
                Thesis thesis = library.thesis.get(info[0]);
                String category = library.thesis.get(info[0]).getCategory().getId();
                HashSet<Thesis> categoryThesis = categories.get(category).getThesis();
                categoryThesis.remove(thesis);
                categories.get(category).setThesis(categoryThesis);
            }

            libraries.get(info[1]).thesis.remove(info[0]);
            System.out.println("success");
        }
    }

    private static boolean addStudent(String[] info) {
        // 0: id, 1: password, 2: firstName, 3: lastName, 4: nationalID,
        // 5: birthYear, 6: address

        if (students.containsKey(info[0])) {
            return false;
        } else {
            Student student = new Student(info);
            students.put(info[0], student);
            return true;
        }
    }

    private static boolean editStudent(String[] info) {
        // 0: id, 1: password, 2: firstName, 3: lastName, 4: nationalID,
        // 5: birthYear, 6: address

        if (!students.containsKey(info[0])) {
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

    private static void removeStudent(String[] info) {
        // 0: id

        if (!students.containsKey(info[0])) {
            System.out.println("not-found");
        } else if (!students.get(info[0]).getBorrowedBooks().isEmpty() ||
                students.get(info[0]).getPenalty() != 0) {
            System.out.println("not-allowed");
        } else {
            students.remove(info[0]);
            System.out.println("success");
        }
    }

    private static boolean addStaff(String[] info) {
        // 0: id, 1: password, 2: firstName, 3: lastName, 4: nationalID,
        // 5: birthYear, 6: address

        if (staff.containsKey(info[0])) {
            return false;
        } else {
            Staff newStaff = new Staff(info);
            staff.put(info[0], newStaff);
            return true;
        }
    }

    private static boolean editStaff(String[] info) {
        // 0: id, 1: password, 2: firstName, 3: lastName, 4: nationalID,
        // 5: birthYear, 6: address

        if (!staff.containsKey(info[0])) {
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

    private static void removeStaff(String[] info) {
        // 0: id

        if (!staff.containsKey(info[0])) {
            System.out.println("not-found");
        } else if (!staff.get(info[0]).getBorrowedBooks().isEmpty() ||
                staff.get(info[0]).getPenalty() != 0) {
            System.out.println("not-allowed");
        } else {
            staff.remove(info[0]);
            System.out.println("success");
        }
    }

    private static void borrow(String[] info) { // sorry for the complex code but couldn't change it mid way
        // 0: personId, 1: password, 2: libraryID, 3: book/thesis ID
        // 4: date, 5: time

        // check if IDs are correct
        if ((!students.containsKey(info[0]) && !staff.containsKey(info[0])) ||
                !libraries.containsKey(info[2])) {
            System.out.println("not-found");
            return;
        }

        Library library = libraries.get(info[2]);
        String writingType;
        if (!library.books.containsKey(info[3]) && !library.thesis.containsKey(info[3])) {
            System.out.println("not-found");
            return;
        } else {
            writingType = library.books.containsKey(info[3]) ? "book" : "thesis";
        }

        // check if password is correct
        String password;
        String personType = students.containsKey(info[0]) ? "student" : "staff";
        if (personType.equals("student")) {
            password = students.get(info[0]).getPassword();
        } else {
            password = staff.get(info[0]).getPassword();
        }
        if (!password.equals(info[1])) {
            System.out.println("invalid-pass");
            return;
        }

        // check if borrowing is allowed
        if (writingType.equals("book")) {
            if (library.books.get(info[3]).getCopyCountNow() == 0) {
                System.out.println("not-allowed");
                return;
            }
        } else {
            if (library.thesis.get(info[3]).isBorrowed()) {
                System.out.println("not-allowed");
                return;
            }
        }

        if (personType.equals("student")) {
            if (students.get(info[0]).getBorrowedBooks().size() == 3) {
                System.out.println("not-allowed");
                return;
            }
        } else {
            if (staff.get(info[0]).getBorrowedBooks().size() == 5) {
                System.out.println("not-allowed");
                return;
            }
        }
        int maxBorrowTime;
        if (personType.equals("student")) {
            if (writingType.equals("book")) {
                maxBorrowTime = 240;
            } else {
                maxBorrowTime = 168;
            }
        } else {
            if (writingType.equals("book")) {
                maxBorrowTime = 336;
            } else {
                maxBorrowTime = 240;
            }
        }
        // borrow the book
        Borrow borrowedBook = new Borrow(info[2], info[3], info[0], info[4], info[5], maxBorrowTime);

        borrows.add(borrowedBook);

        if (personType.equals("student")) {
            ArrayList<Borrow> borrowedBooks = students.get(info[0]).getBorrowedBooks();
            borrowedBooks.add(borrowedBook);
            students.get(info[0]).setBorrowedBooks(borrowedBooks);
        } else {
            ArrayList<Borrow> borrowedBooks = staff.get(info[0]).getBorrowedBooks();
            borrowedBooks.add(borrowedBook);
            staff.get(info[0]).setBorrowedBooks(borrowedBooks);
        }

        if (writingType.equals("book")) {
            int newCopyCount = library.books.get(info[3]).getCopyCountNow() - 1;
            library.books.get(info[3]).setCopyCountNow(newCopyCount);

            if (!library.books.get(info[3]).isBorrowed()) {
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
        if ((student == null && staff1 == null) || (book == null && thesis == null)) {
            System.out.println("not-found");
            return;
        }


        // check if person has borrowed the source
        HashMap<Long, Borrow> borrowedSources = new HashMap<>();
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
                Long hoursDifference = borrowDuration(borrow, info[4], info[5]);
                borrowedSources.put(hoursDifference, borrow);
            }
        }

        if (borrowedSources.isEmpty()){
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
        Borrow borrowToRemove;
        long leastPenaltyTime = Long.MAX_VALUE;
        long oldestBorrowTime = 0L;
        Long toRemoveKey = -1L;

        // doing it right???
        for (Long hoursDifference : borrowedSources.keySet()){
            Borrow borrow = borrowedSources.get(hoursDifference);

            if (hoursDifference > borrow.getMaxBorrowTime()){
                for (Long hourDiff : borrowedSources.keySet()){
                    Borrow borrowedSource = borrowedSources.get(hourDiff);
                    if (hourDiff > borrowedSource.getMaxBorrowTime()){
                        long penaltyTime =  hourDiff - borrowedSource.getMaxBorrowTime();

                        if (penaltyTime < leastPenaltyTime) {
                            leastPenaltyTime = penaltyTime;
                            toRemoveKey = hourDiff;
                        }
                    }
                }
                break;
            } else {
                if (hoursDifference > oldestBorrowTime){
                    oldestBorrowTime = hoursDifference;
                    toRemoveKey = hoursDifference;
                }
            }
        }

        borrowToRemove = borrowedSources.get(toRemoveKey);
        String dateToRemove = borrowToRemove.getDate();


        String personType = student != null ? "student" : "staff";
        String sourceType = book != null ? "book" : "thesis";

        ArrayList<Borrow> personBorrowedSources = student != null ? student.getBorrowedBooks() : staff1.getBorrowedBooks();

        it = personBorrowedSources.iterator();

        while (it.hasNext()){
            Borrow borrow = it.next();
            if (borrow.getWritingID().equals(info[3]) &&
                    borrow.getLibraryID().equals(info[2]) &&
                    borrow.getDate().equals(dateToRemove)){
                it.remove();

                if (book != null){
                    int copyCount = libraries.get(info[2]).books.get(info[3]).getCopyCount();
                    int copyCountNow = libraries.get(info[2]).books.get(info[3]).getCopyCountNow();

                    int newCopyCount = copyCountNow + 1;
                    libraries.get(info[2]).books.get(info[3]).setCopyCountNow(newCopyCount);

                    if (newCopyCount == copyCount){
                        libraries.get(info[2]).books.get(info[3]).setBorrowed(false);
                    }
                } else {
                    libraries.get(info[2]).thesis.get(info[3]).setBorrowed(false);
                }
                break;
            }
        }

        if (personType.equals("student")){
            students.get(info[0]).setBorrowedBooks(personBorrowedSources);
        } else {
            staff.get(info[0]).setBorrowedBooks(personBorrowedSources);
        }

        it = borrows.iterator();

        while (it.hasNext()){
            Borrow borrow = it.next();

            if (borrow.getWritingID().equals(info[3]) &&
                    borrow.getLibraryID().equals(info[2]) &&
                    borrow.getPersonId().equals(info[0]) &&
                    borrow.getDate().equals(dateToRemove)) {
                it.remove();
                break;
            }
        }

        long penalty = calculatePenalty(personType, sourceType, toRemoveKey);

        if (penalty != 0 ){
            System.out.println(penalty);
            long personPenalties;
            if (personType.equals("student")){
                personPenalties = student.getPenalty() + penalty;
                students.get(info[0]).setPenalty(personPenalties);
            } else {
                personPenalties = staff1.getPenalty() + penalty;
                staff.get(info[0]).setPenalty(personPenalties);
            }
            return;
        }

        System.out.println("success");
    }

    private static long borrowDuration(Borrow borrow, String returningDate, String returningTime) {
        String[] borrowDateInfo = borrow.getDate().split("-");
        String[] borrowTimeInfo = borrow.getTime().split(":");

        String[] returnDateInfo = returningDate.split("-");
        String[] returnTimeInfo = returningTime.split(":");

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

        return duration.toHours();
    }

    private static long calculatePenalty(String personType, String sourceType, long hoursDifference) {
        long penaltyPerHour;
        long maxPenaltyTime;

        if (personType.equals("student")) {
            penaltyPerHour = 50;
            maxPenaltyTime = sourceType.equals("book") ? 240 : 168;
        } else {
            penaltyPerHour = 100;
            maxPenaltyTime = sourceType.equals("book") ? 336 : 240;
        }

        if (hoursDifference > maxPenaltyTime) {
            return (hoursDifference - maxPenaltyTime) * penaltyPerHour;
        } else {
            return 0;
        }
    }

    private static void search(String[] info) {
        // 0: keyWord to search

        String keyWord = info[0].toLowerCase();

        ArrayList<String> foundSources = new ArrayList<>();

        libraries.forEach((libraryId, library) -> {
            HashMap<String, Book> libraryBook = library.getBooks();
            HashMap<String, Thesis> libraryThesis = library.getThesis();

            libraryBook.forEach((bookID, book) -> {
                if (book.getName().contains(keyWord) ||
                        book.getAuthor().contains(keyWord) ||
                        book.getPublisher().contains(keyWord)) {
                    foundSources.add(bookID);
                }
            });

            libraryThesis.forEach((thesisID, thesis) -> {
                if (thesis.getName().contains(keyWord) ||
                        thesis.getStudentName().contains(keyWord) ||
                        thesis.getProfessorName().contains(keyWord)) {
                    foundSources.add(thesisID);
                }
            });
        });

        if (foundSources.isEmpty()) {
            System.out.println("not-found");
            return;
        }

        Collections.sort(foundSources);

        for (int i = 0; i < foundSources.size(); i++) {
            if (i == 0) {
                System.out.print(foundSources.get(i));
            } else {
                System.out.print("|" + foundSources.get(i));
            }
        }

        System.out.println();
    }

    private static void searchUser(String[] info) {
        // 0: id, 1: passWord, 2: keyWord to search

        if (!staff.containsKey(info[0]) && !students.containsKey(info[0])) {
            System.out.println("not-found");
            return;
        }

        if (students.containsKey(info[0])) {
            if (!students.get(info[0]).getPassword().equals(info[1])) {
                System.out.println("invalid-pass");
                return;
            }
        } else {
            if (!staff.get(info[0]).getPassword().equals(info[1])) {
                System.out.println("invalid-pass");
                return;
            }
        }

        String keyWord = info[2].toLowerCase();

        ArrayList<String> foundIDs = new ArrayList<>();

        staff.forEach((key, value) -> {
            if (value.getFirstName().contains(keyWord) ||
                    value.getLastName().contains(keyWord)) {
                foundIDs.add(key);
            }
        });

        students.forEach((key, value) -> {
            if (value.getFirstName().contains(keyWord) ||
                    value.getLastName().contains(keyWord)) {
                foundIDs.add(key);
            }
        });

        if (foundIDs.isEmpty()) {
            System.out.println("not-found");
            return;
        }

        Collections.sort(foundIDs);

        for (int i = 0; i < foundIDs.size(); i++) {
            if (i == 0) {
                System.out.print(foundIDs.get(i));
            } else {
                System.out.print("|" + foundIDs.get(i));
            }
        }

        System.out.println();
    }

    private static void categoryReport(String[] info) { // is it done correctly?
        // 0: category id

        if (!categories.containsKey(info[0])) {
            System.out.println("not-found");
            return;
        }

        int bookCount = 0;
        int thesisCount = 0;

        for (String libraryID : libraries.keySet()){
            Library library = libraries.get(libraryID);

            HashMap<String, Book> libraryBooks = library.getBooks();
            HashMap<String, Thesis> libraryThesis = library.getThesis();

            for (String bookID : libraryBooks.keySet()){
                Book book = libraryBooks.get(bookID);

                if (book.getCategory().getId().equals(info[0])){
                    bookCount += book.getCopyCount();
                }
            }

            for (String thesisID : libraryThesis.keySet()){
                Thesis thesis = libraryThesis.get(thesisID);

                if (thesis.getCategory().getId().equals(info[0])){
                    thesisCount++;
                }
            }

        }

        System.out.println(bookCount + " " + thesisCount);
    }

    private static void libraryReport(String[] info) {
        // 0: libraryID

        if (!libraries.containsKey(info[0])) {
            System.out.println("not-found");
            return;
        }

        Library library = libraries.get(info[0]);

        int[] totalBooks = {0};
        int totalThesis = library.thesis.size();
        int[] borrowedBooks = {0};
        int[] borrowedThesis = {0};

        HashMap<String, Book> libraryBooks = library.getBooks();
        HashMap<String, Thesis> libraryThesis = library.getThesis();

        libraryBooks.forEach((id, book) -> {
            // should I count like this??? maybe even ***countNow*** ???
            totalBooks[0] += book.getCopyCount();
            if (book.isBorrowed()) {
                // should I count like this???
                borrowedBooks[0] += book.getCopyCount() - book.getCopyCountNow();
            }
        });

        libraryThesis.forEach((id, thesis) -> {
            if (thesis.isBorrowed()) {
                borrowedThesis[0]++;
            }
        });

        System.out.printf("%d %d %d %d\n", totalBooks[0], totalThesis,
                borrowedBooks[0], borrowedThesis[0]);
    }

    private static void reportPassedDeadline(String[] info) { // is it implemented correctly?
        // 0: libraryID, 1: date, 2: time

        if (!libraries.containsKey(info[0])) {
            System.out.println("not-found");
            return;
        }
        if (borrows.isEmpty()) {
            System.out.println("none");
            return;
        }

        ArrayList<String> passedDeadlineSources = new ArrayList<>();

        for (Borrow borrow : borrows) {
            if (!passedDeadlineSources.contains(borrow.getWritingID()) &&
                    borrow.getLibraryID().equals(info[0]) &&
                    (borrowDuration(borrow, info[1], info[2]) > borrow.getMaxBorrowTime())) {
                passedDeadlineSources.add(borrow.getWritingID());
            }
        }

        if (passedDeadlineSources.isEmpty()) {
            System.out.println("none");
            return;
        }

        Collections.sort(passedDeadlineSources);

        for (int i = 0; i < passedDeadlineSources.size(); i++) {
            if (i == 0) {
                System.out.print(passedDeadlineSources.get(i));
            } else {
                System.out.print("|" + passedDeadlineSources.get(i));
            }
        }

        System.out.println();
    }

    private static void reportPenaltiesSum() {
        // no info input

        long[] totalPenalties = {0};

        students.forEach((key, student) -> {
            totalPenalties[0] += student.getPenalty();
        });
        staff.forEach((key, staff) -> {
            totalPenalties[0] += staff.getPenalty();
        });

        long finalTotalPenalties = totalPenalties[0];

        System.out.println(finalTotalPenalties);
    }

    private static void reserveSeat(String[] info) {
        // 0: ID, 1: password, 2: libraryID, 3: date, 4: timeStart, 5: timeEnd

        if (!students.containsKey(info[0]) && !staff.containsKey(info[0]) ||
                !libraries.containsKey(info[2])) {
            System.out.println("not-found");
            return;
        }

        Student student = students.getOrDefault(info[0], null);
        Staff staff1 = staff.getOrDefault(info[0], null);

        if (student != null) {
            if (!student.getPassword().equals(info[1])) {
                System.out.println("invalid-pass");
                return;
            }
        } else {
            if (!staff1.getPassword().equals(info[1])) {
                System.out.println("invalid-pass");
                return;
            }
        }

        String[] dateInfo = info[3].split("-");
        String[] startTimeInfo = info[4].split(":");
        String[] endTimeInfo = info[5].split(":");

        LocalDateTime startTime = LocalDateTime.of(
                Integer.parseInt(dateInfo[0]),
                Integer.parseInt(dateInfo[1]),
                Integer.parseInt(dateInfo[2]),
                Integer.parseInt(startTimeInfo[0]),
                Integer.parseInt(startTimeInfo[1]));
        LocalDateTime endTime = LocalDateTime.of(
                Integer.parseInt(dateInfo[0]),
                Integer.parseInt(dateInfo[1]),
                Integer.parseInt(dateInfo[2]),
                Integer.parseInt(endTimeInfo[0]),
                Integer.parseInt(endTimeInfo[1]));
        Duration duration = Duration.between(startTime, endTime);

        // how should the minutes be counted?
        if (duration.toMinutes() > 480) {
            System.out.println("not-allowed");
            return;
        }

        if (student != null) {
            if (!student.getReserveSeats().isEmpty()) {
                for (ReserveSeat reserveSeat : student.getReserveSeats()) {
                    if (reserveSeat.getDate().equals(info[3]) &&
                        reserveSeat.getPersonID().equals(info[0])) {
                        System.out.println("not-allowed");
                        return;
                    }
                }
            }
        } else {
            if (!staff1.getReserveSeats().isEmpty()) {
                for (ReserveSeat reserveSeat : staff1.getReserveSeats()) {
                    if (reserveSeat.getDate().equals(info[3]) &&
                        reserveSeat.getPersonID().equals(info[0])) {
                        System.out.println("not-allowed");
                        return;
                    }
                }
            }
        }

        int tableCount = libraries.get(info[2]).getTableCount();
        int reservedSeatsCount = 0;

        for (ReserveSeat reserveSeat : reserveSeats) {
            if (reserveSeat.getLibraryID().equals(info[2])) {
                if (reserveSeat.getDate().equals(info[3])) {
                    String startTime1 = reserveSeat.getStartTime();
                    String endTime1 = reserveSeat.getEndTime();

                    if (checkTimeInterfere(startTime1, endTime1, info[4], info[5])) {
                        reservedSeatsCount++;
                    }
                }
            }
        }

        if (reservedSeatsCount == tableCount){
            System.out.println("not-available");
            return;
        }

        ReserveSeat reserveSeat = new ReserveSeat(info[0], info[2], info[3], info[4], info[5]);

        reserveSeats.add(reserveSeat);
        if (student != null) {
            HashSet<ReserveSeat> studentReservedSeats = student.getReserveSeats();
            studentReservedSeats.add(reserveSeat);
            student.setReserveSeats(studentReservedSeats);
        } else {
            HashSet<ReserveSeat> staffReservedSeats = staff1.getReserveSeats();
            staffReservedSeats.add(reserveSeat);
            staff1.setReserveSeats(staffReservedSeats);
        }

        System.out.println("success");
    }

    private static boolean checkTimeInterfere(String startTime1, String endTime1,
                                              String startTime2, String endTime2) {
        LocalTime startTime = LocalTime.parse(startTime1 + ":00");
        LocalTime endTime = LocalTime.parse(endTime1 + ":00");

        LocalTime targetStartTime = LocalTime.parse(startTime2 + ":00");
        LocalTime targetEndTime = LocalTime.parse(endTime2 + ":00");

        // is the equal correct?
        if (targetStartTime.equals(startTime) || targetEndTime.equals(endTime) ||
                (targetStartTime.isBefore(endTime) && targetStartTime.isAfter(startTime)) ||
                (targetEndTime.isBefore(endTime) && targetEndTime.isAfter(startTime)) ||
                (targetStartTime.isBefore(startTime) && targetEndTime.isAfter(endTime))) {
            return true;
        }

        return false;
    }
}