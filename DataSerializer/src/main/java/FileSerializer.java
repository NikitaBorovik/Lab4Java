import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileSerializer {
    public void serializeResult(Map<Integer, Boolean> portStatus, String fileName, boolean isShortType) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("resultDir/"+fileName))) {
            for (Map.Entry<Integer, Boolean> entry : portStatus.entrySet()) {
                if(isShortType){
                    writer.write(entry.getKey() + ":" + entry.getValue());
                }
                else {
                    writer.write("Port number "+entry.getKey() + (entry.getValue()?" is open":" is closed"));
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
