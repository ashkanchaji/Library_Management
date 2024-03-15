import java.util.HashMap;
import java.util.Scanner;

public class LibraryManagement {

    public static HashMap<String, Library> libraries = new HashMap<>();
    public static HashMap<String, Category> categories = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true){
            String input = scanner.nextLine();

            if (input.equals("finish")){
                break;
            }

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
                    /* if ( book was borrowed )
                        System.out.println("not-allowed");
                        break;
                     */
                    if (removeBook(info)){
                        System.out.println("success");
                    } else {
                        System.out.println("not-found");
                    }
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
            }
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
            return true;
        }
    }

    private static boolean editBook (String[] info){
        // 0: bookID, 1: libraryID, 2: name, 3: author, 4: publisher, 5: printYear,
        // 6: copyCount, 7: category

        Library library = libraries.get(info[1]);

        if (!libraries.containsKey(info[1]) || !library.books.containsKey(info[0])){
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
                Category category = categories.get(info[7]);
                library.books.get(info[0]).setCategory(category);
            }
            return true;
        }
    }

    private static boolean removeBook(String[] info){
        // 0: bookID, 1: libraryID

        Library library = libraries.get(info[1]);

        if (!libraries.containsKey(info[1]) || !library.books.containsKey(info[0])) {
            return false;
        } else {
            library.books.remove(info[0]);
            return true;
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
            return true;
        }
    }
}