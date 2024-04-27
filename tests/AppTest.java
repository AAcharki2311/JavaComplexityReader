import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void constructor() {
        App app = new App();
        assertNotNull(app);
    }

    @Test
    void getFiles() {
        App app = new App();
        assertNotNull(app.getFiles("src"));
    }

    @Test
    void getFilesWrong() {
        App app = new App();
        assertThrows(IllegalArgumentException.class, () -> app.getFiles(null));
        assertThrows(IllegalArgumentException.class, () -> app.getFiles("fakeDir"));
    }
}