import java.io.*;
import org.apache.commons.lang.RandomStringUtils;
import java.util.Random;
public class Begin {

    public static String givenUsingApache_whenGeneratingRandomStringBounded_thenCorrect() {

        Random rnd = new Random(System.currentTimeMillis());
        int min=1;
        int length = min + rnd.nextInt(10);
        int n = min + rnd.nextInt(10);
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString="" ;
        String generatedString1 = RandomStringUtils.random(length, useLetters, useNumbers);

        for(int i=0; i<n; i++) {
            length = min + rnd.nextInt(10);
            generatedString = generatedString + " " + generatedString1;
            generatedString1 = RandomStringUtils.random(length, useLetters, useNumbers);

        }
        return generatedString;
    }

    public static void main(String[] args) throws Exception {


        Textproc nstr = new Textproc(givenUsingApache_whenGeneratingRandomStringBounded_thenCorrect());

        FileWriter fw = new FileWriter("C:\\Users\\Ксения\\IdeaProjects\\Ex1\\src\\main\\java\\Information.txt");
        fw.write(nstr.Yourname()+" "+nstr.Simbnumber(nstr.Words()));
        for (String Substr:nstr.Words()) fw.write(" "+Substr);
        fw.close();

    }

}

