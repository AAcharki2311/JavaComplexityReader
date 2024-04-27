import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * A class to analyze the complexity of a Java file.
 */
public class CodeAnalyzer {

    private final File file;
    private final List<String> conditionalStatements = List.of("if", "else", "for", "while", "switch", "case");
    private final List<String> operators = List.of("&", "|", "=", "!", "+", "-", "/", "*", "%", ">", "<");

    /**
     * Constructor.
     * @param file the file to analyze.
     */
    public CodeAnalyzer(File file) {
        this.file = file;
    }

    /**
     * run method.
     */
    public void run()  {
        var stringFile = readFile(file);
        var percentage = checkCamelCase(stringFile);
        var hashmap = checkComplexity(stringFile);
        printResults(hashmap, percentage);
    }

    /**
     * Read the content of a file.
     * @param fileToRead the file to read and convert to a string.
     * @return the content of the file.
     */
    public String readFile(File fileToRead) {
        try{
            Scanner scanner = new Scanner(fileToRead);
            StringBuilder content = new StringBuilder();
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
            return content.toString();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("The file does not exist.");
        }
    }

    /**
     * Print the results of the analysis as a table.
     * @param complexityMap the hashmap of method names and their complexity.
     * @param percentage the percentage of non camel case methods.
     */
    public void printResults(HashMap<String, Integer> complexityMap, double percentage) {
        System.out.printf("-----------------------------------------------%n");
        System.out.printf("%-30s | %-10s %n", "method name:", "complexity:");

        System.out.printf("-----------------------------------------------%n");
        complexityMap.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(3)
                .forEach(entry -> System.out.printf("%-30s | %-10s %n", entry.getKey(), entry.getValue()));

        System.out.printf("-----------------------------------------------%n");
        System.out.println("(%) Methods not following CamelCase: " + percentage + "%");
    }

    /**
     * Check the percentage of non camel case methods.
     * @param file the file content.
     * @return the percentage of non camel case methods.
     */
    public double checkCamelCase(String file) {
        String[] lines = file.split("\n");
        int totalMethods = 0;
        int nonCamelCaseMethods = 0;

        for (String line : lines) {
            // if a line contains void, and it is not a conditional statement, then we can assume it is a method
            // furthermore, if a line contains { and ( and it is not a class or a catch block,
            // and it does not contain any conditional statements or operators, then we can assume it is a method
            if ((line.contains("void") && conditionalStatements.stream().noneMatch(line::contains)) || (line.contains("{") && line.contains("(") && !line.contains("class") && !line.contains("catch")
                    && conditionalStatements.stream().noneMatch(line::contains) && operators.stream().noneMatch(line::contains))){
                totalMethods++;
                String methodName = extractMethodName(line);
                if (!isCamelCase(methodName)) {
                    nonCamelCaseMethods++;
                }
            }
        }
        var res = (double) nonCamelCaseMethods / totalMethods * 100;
        // format the result to one decimal place
        DecimalFormat df = new DecimalFormat("#.#");
        res = Double.parseDouble(df.format(res));
        return res;
    }

    /**
     * Extract the method name from a line.
     * @param line the line.
     * @return the method name.
     */
    public String extractMethodName(String line) {
        String[] list = line.split(" ");
        String name = Arrays.stream(list).filter(e -> e.contains("(")).findAny().orElse(null);
        if(name == null || name.isBlank()) throw new IllegalArgumentException("The method name cannot be null/empty.");
        return name.split("\\(")[0].trim();
    }

    /**
     * Check if a method name is camel case.
     * @param methodName the method name.
     * @return true if the method name is camel case, false otherwise.
     */
    public boolean isCamelCase(String methodName) {
        if (methodName == null) throw new IllegalArgumentException("The method name cannot be null.");
        if (methodName.isBlank()) return false;
        if (!Character.isLowerCase(methodName.charAt(methodName.length() - 1))) return false;
        return methodName.matches("^[a-z][a-zA-Z0-9]*$");
    }

    /**
     * Check the complexity of the methods.
     * @param file the file content.
     * @return a hashmap of method names and their complexity.
     */
    public HashMap<String, Integer> checkComplexity(String file) {
        HashMap<String, Integer> temp = new HashMap<>();
        String[] lines = file.split("\n");

        for (int i = 0; i < lines.length ; i++) {
            String line = lines[i];
            // if a line contains void, and it is not a conditional statement, then we can assume it is a method
            // furthermore, if a line contains { and ( and it is not a class or a catch block,
            // and it does not contain any conditional statements or operators, then we can assume it is a method
            if ((line.contains("void") && conditionalStatements.stream().noneMatch(line::contains)) || (line.contains("{") && line.contains("(") && !line.contains("class") && !line.contains("catch")
                    && conditionalStatements.stream().noneMatch(line::contains) && operators.stream().noneMatch(line::contains))){
                String methodName = extractMethodName(line);
                int complexity = countConditionalStatements(lines, i);
                temp.put(methodName, complexity);
            }
        }
        return temp;
    }

    /**
     * Count the number of conditional statements in a method.
     * @param lines the lines of the file.
     * @param startIndex the start index of the method.
     * @return the number of conditional statements.
     */
    public int countConditionalStatements(String[] lines, int startIndex) {
        int complexity = 0;
        int openBrackets = 0;

        for (int i = startIndex + 1; i < lines.length; i++) {
            String line = lines[i];
            // if we reach the end of the method, then break
            // we check on the open brackets to make sure we are not breaking in a nested method
            if (line.contains("}") && openBrackets == 0) break;
            // if the line contains any conditional statements, then increment the complexity
            if (conditionalStatements.stream().anyMatch(line::contains)) complexity++;
            // if there are new open brackets than this could be a new conditional statement or a new method.
            if (line.contains("{")) openBrackets++;
            // if there are closed brackets, then decrement the open brackets
            if (line.contains("}")) openBrackets--;
        }
        return complexity;
    }

}
