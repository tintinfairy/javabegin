import java.io.*;

public class Begin {

    final static String PATH_TO_FILE = "Information.txt";

    public static void main(String[] args) {
        TextProc textProc = new TextProc();
        UrlDownloader information = new UrlDownloader();
        //Course course = new Course(this.title, information.getDescription(doc));


        try (FileWriter fw = new FileWriter(PATH_TO_FILE);) {
            PrintWriter pw = new PrintWriter(fw);

            pw.println("Text: " + textProc.getText());
            pw.println("Text Length: " + textProc.wordsAmount());
            pw.println("Words: ");
            for (String word : textProc.words()) {
                pw.println(word);
            }
            pw.println("Unique Words: " + textProc.wordsUniqueness(textProc.words()));
            information.outputInf();
           // course.executePost("https://webhook.site/1abbd4d4-1290-4bae-a460-611ada0df55d",information.outputInf())


        } catch (IOException e) {
            System.out.println("Cannot open the file " + PATH_TO_FILE);
            System.out.println("Text: " + textProc.getText());
            System.out.println("Text Length: " + textProc.wordsAmount());
            System.out.println("Words: ");
            for (String word : textProc.words()) {
                System.out.println(word);
            }
            System.out.println("Unique Words: " + textProc.wordsUniqueness(textProc.words()));
        }


    }
}
