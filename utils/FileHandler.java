import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class FileHandler  {
    public static void writeFile(String fileName, List<String> list) throws Exception {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(new File(fileName));
            bw = new BufferedWriter(fw);
            for (int i = 0; list != null && i < list.size(); i++) {
                bw.write(list.get(i));
                bw.write("\n");
            }
        } catch (Exception e) {
            System.out.println("YAWIN:ERROR:" + e.getMessage());
            throw e;
        } finally {
            try {
                bw.close();
            } catch (Exception e) {
            }
            try {
                fw.close();
            } catch (Exception e) {
            }
        }

    }
}
