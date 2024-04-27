public class Main {

    /**
     * The main method.
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the Java Complexity Reader!");
        System.out.println("This program will analyze the complexity of Java files in a given directory.");
        System.out.printf("-----------------------------------------------%n");

        App app = new App();
        app.run();
    }
}
