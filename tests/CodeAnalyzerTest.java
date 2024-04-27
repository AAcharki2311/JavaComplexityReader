import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CodeAnalyzerTest {

    @Test
    void run() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        File file = new File("tests/resources/FileOneTest.java");
        CodeAnalyzer ca = new CodeAnalyzer(file);
        ca.run();

        assertTrue(outContent.toString().contains("method name"));
        assertTrue(outContent.toString().contains("complexity"));
        assertTrue(outContent.toString().contains("three"));
        assertTrue(outContent.toString().contains("Four"));
        assertTrue(outContent.toString().contains("Five"));
        assertTrue(outContent.toString().contains("(%) Methods not following CamelCase: 40.0%"));


    }

    @Test
    void readFile() {
        File file = new File("tests/resources/testFile");
        String text = "JetBrains is the best company in the world\n";
        CodeAnalyzer ca = new CodeAnalyzer(null);
        assertEquals(text, ca.readFile(file));

    }

    @Test
    void readFileDoesNotExist()  {
        File file = new File("fakeFile.java");
        CodeAnalyzer ca = new CodeAnalyzer(null);
        assertThrows((IllegalArgumentException.class), () -> ca.readFile(file));
    }

    @Test
    void printResults() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        CodeAnalyzer ca = new CodeAnalyzer(null);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        double percentage = 23.0;

        ca.printResults(map, percentage);
        assertTrue(outContent.toString().contains("method name"));
        assertTrue(outContent.toString().contains("complexity"));
        assertTrue(outContent.toString().contains("One"));
        assertTrue(outContent.toString().contains("Two"));
        assertTrue(outContent.toString().contains("(%) Methods not following CamelCase: 23.0%"));

    }

    @Test
    void checkCamelCase() {
        CodeAnalyzer ca = new CodeAnalyzer(null);
        File file = new File("tests/resources/FileOneTest.java");
        var res = ca.checkCamelCase(ca.readFile(file));
        assertEquals(40.0, res);
    }

    @Test
    void extractMethodName() {
        CodeAnalyzer ca = new CodeAnalyzer(null);

        String methodName1 = "static testMethod(String line) {";
        String methodName2 = "public testMethod(String line) {";
        String methodName3 = "public void testMethod(String line) throws FileNotFoundException{";
        String methodName4 = "void testMethod(String line) {";
        String methodName5 = "testMethod(String line) {";
        String methodName7 = "";
        String methodName8 = " ";

        assertEquals("testMethod", ca.extractMethodName(methodName1));
        assertEquals("testMethod", ca.extractMethodName(methodName2));
        assertEquals("testMethod", ca.extractMethodName(methodName3));
        assertEquals("testMethod", ca.extractMethodName(methodName4));
        assertEquals("testMethod", ca.extractMethodName(methodName5));
        assertThrows(NullPointerException.class, () -> ca.extractMethodName(null));
        assertThrows(IllegalArgumentException.class, () -> ca.extractMethodName(methodName7));
        assertThrows(IllegalArgumentException.class, () -> ca.extractMethodName(methodName8));


    }

    @Test
    void isCamelCase() {
        CodeAnalyzer ca = new CodeAnalyzer(null);

        String methodNameBlank = " ";
        String methodNameEmpty = "";
        String methodNameFirstUpper = "NotCamelCase";
        String methodNameLastUpper = "notCamelCasE";
        String methodNameCorrect = "isCamelCase";

        assertThrows(IllegalArgumentException.class, () -> ca.isCamelCase(null));
        assertFalse(ca.isCamelCase(methodNameBlank));
        assertFalse(ca.isCamelCase(methodNameEmpty));
        assertFalse(ca.isCamelCase(methodNameFirstUpper));
        assertFalse(ca.isCamelCase(methodNameLastUpper));
        assertTrue(ca.isCamelCase(methodNameCorrect));

    }

    @Test
    void checkComplexity() {
        CodeAnalyzer ca = new CodeAnalyzer(null);
        File file = new File("tests/resources/FileOneTest.java");
        var res = ca.checkComplexity(ca.readFile(file));
        System.out.println(res);
        assertNotNull(res);
        assertEquals(4, res.get("Five"));
        assertEquals(0, res.get("one"));
        assertEquals(1, res.get("two"));
        assertEquals(3, res.get("three"));
        assertEquals(3, res.get("Four"));


    }
}