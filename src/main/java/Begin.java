import java.io.*;
import org.apache.commons.lang.RandomStringUtils;
import java.util.Random;
public class Begin {

    final static String PATH_TO_FILE =  "tmp/Information.txt";

    public static void main(String[] args) {
        TextProc textProc = new TextProc();
        try (FileWriter fw = new FileWriter(PATH_TO_FILE);) {
            PrintWriter pw = new PrintWriter(fw);

            pw.println("Text: " + textProc.getText());
            pw.println("Text Length: " + textProc.wordsAmount());
            pw.println("Words: ");
            for (String word : textProc.words()) {
                pw.println(word);
            }
        } catch (IOException e) {
            System.out.println("Cannot open the file " + PATH_TO_FILE);

            System.out.println("Text: " + textProc.getText());
            System.out.println("Text Length: " + textProc.wordsAmount());
            System.out.println("Words: ");
            for (String word : textProc.words()) {
                System.out.println(word);
            }
        }
    }
}

