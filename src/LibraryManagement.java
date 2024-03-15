import java.util.HashMap;
import java.util.Scanner;

public class LibraryManagement {

    static HashMap<String, Library> libraries = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true){
            String input = scanner.nextLine();

            String[] commands = input.split("#");

            switch(commands[0]){
                case "add-library" :
                    if (addLibrary(commands[1])){
                        System.out.println("success");
                    } else {
                        System.out.println("duplicate-id");
                    }
                    break;
            }

            if (input.equals("finish")){
                break;
            }
        }
    }

    private static boolean addLibrary (String information){
        // 0: id, 1: name, 2: establishYear, 3: tableCount, 4: address
        String[] info = information.split("|");

        Library library = new Library(info[0], info[1], info[2], info[3], info[4]);

        if (libraries.containsKey(info[0])){
            return false;
        } else {
            libraries.put(info[0], library);
            return true;
        }
    }
}