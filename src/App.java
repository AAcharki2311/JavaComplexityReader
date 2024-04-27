import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * The Application.
 */
public class App {

    /**
     * Constructor.
     */
    public App() {}

    /**
     * Run the program.
     */
    public void run() {
        System.out.println("\033[1m Which directory would you like to analyze? \033[0m");
        Scanner scanner = new Scanner(System.in);
        String dir = scanner.nextLine();

        System.out.println("\n\033[1mDIRECTORY: " + dir + "\033[0m");

        for(File file : getFiles(dir)) {
            CodeAnalyzer codeAnalyzer = new CodeAnalyzer(file);
            System.out.printf("-----------------------------------------------%n");
            System.out.printf("\033[1m              File: %s%n", file.getName() + "\033[0m");
            codeAnalyzer.run();
        }

    }

    /**
     * Get the files in a directory.
     * @param dir the directory.
     * @return the files in the directory.
     */
    public List<File> getFiles(String dir) {
        if(dir == null) throw new IllegalArgumentException("The directory cannot be null.");
        try{
            return Stream.of(new File(dir).listFiles())
                    .filter(f -> f.getName().endsWith(".java"))
                    .toList();
        } catch (Exception e) {
            throw new IllegalArgumentException("The directory does not exist.");
        }
    }

}
