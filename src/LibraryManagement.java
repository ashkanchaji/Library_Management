import java.util.HashMap;
import java.util.Scanner;

public class LibraryManagement {

    public static HashMap<String, Library> libraries = new HashMap<>();
    public static HashMap<String, Category> categories = new HashMap<>();
    public static HashMap<String, Book> books = new HashMap<>();

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
                    if (addBook(info)){
                        System.out.println("success");
                    } else {
                        System.out.println("duplicate-id");
                    }

                    // *** IS THIS WAY CORRECT IN TEST CASES ? ***
                    if (!categories.containsKey(info[6]) || !libraries.containsKey(info[7])){
                        System.out.println("not-found");
                    }
                    break;
            }
        }
    }

    private static boolean addLibrary (String[] info){
        // 0: id, 1: name, 2: establishYear, 3: tableCount, 4: address
        Library library = new Library(info[0], info[1], info[2], info[3], info[4]);

        if (libraries.containsKey(info[0])){
            return false;
        } else {
            libraries.put(info[0], library);
            return true;
        }
    }

    private static boolean addCategory (String[] info){
        // 0: id, 1: name
        Category category = new Category(info[0], info[1]);

        if (categories.containsKey(info[0])){
            return false;
        } else {
            categories.put(info[0], category);
            return true;
        }
    }

    private static boolean addBook (String[] info){
        // 0: id, 1: name, 2: author, 3: publisher, 4: printYear, 5: copyCount,
        // 6: category, 7: library

        Category category = categories.getOrDefault(info[6], null);
        Library library = libraries.getOrDefault(info[7], null);

        Book book = new Book(info[0], info[1], info[2], info[3], info[4], info[5],
                category, library);

        if (books.containsKey(info[0])){
            return false;
        } else {
            books.put(info[0], book);
            return true;
        }
    }
}