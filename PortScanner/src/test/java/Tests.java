import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests {
    @Test
    public void assertThreadsNumber(){
        //test 1
        PortScanner portScanner = new PortScanner();
        assertTrue(portScanner.getTHREADS_NUM() <= 16 && portScanner.getTHREADS_NUM() > 0);
    }
}
