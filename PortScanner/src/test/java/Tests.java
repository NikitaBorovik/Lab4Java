import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests {
    @Test
    public void assertThreadsNumber(){
        assertTrue(PortScanner.THREADS_NUM <= 16 && PortScanner.THREADS_NUM > 0);
    }
}
