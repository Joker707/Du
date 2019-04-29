import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class DuTests {
    private String input = "du";
    private ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    private Path file1 = Paths.get("src", "main", "resources", "1.txt");
    private ExpectedSystemExit exit = ExpectedSystemExit.none();


    @Test
    public void differentFlags() {
        String[] args1 = {"-c", "1.txt"};
        Parser.main(args1);
        String excepted = "Суммарный размер равен 100 " ;
        String actual = outputStream.toString();
        assertEquals(excepted, actual);

        Path dir1 = Paths.get("src","test","resources","1.txt");
        String[] args2 = {"-c", "-h", dir1.toString()};
        Parser.main(args2);
        String excepted1 = "Суммарный размер равен 100 KB" ;
        String actual1 = outputStream.toString();
        assertEquals(excepted1, actual1);
    }


}
