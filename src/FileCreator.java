import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator {
    public static void createFile() {
        try {
            File f = new File("src/moviesbygenre.txt");
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            for (String n : MovieCollection.returnGenres()) {
                fw.write(n + "\n");
            }
            fw.close();
        }
        catch (IOException ioe) {
            System.out.println("Writing file failed");
            System.out.println(ioe);
        }
    }
}
