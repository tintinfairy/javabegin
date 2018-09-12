import java.io.*;
import org.apache.commons.lang.RandomStringUtils;
public class Begin {

    public static String givenUsingApache_whenGeneratingRandomStringBounded_thenCorrect() {

        int length = 50;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);

        return generatedString;
    }

    public static void main(String[] args) throws Exception {


        Textproc nstr = new Textproc(givenUsingApache_whenGeneratingRandomStringBounded_thenCorrect());

        FileWriter fw = new FileWriter("C:\\Users\\Ксения\\IdeaProjects\\Ex1\\src\\main\\java\\Information.txt");
        fw.write(nstr.Yourname()+" "+nstr.Simbnumber()+" ");
        for (String Substr:nstr.Words()) fw.write(Substr+" ");
        fw.close();

    }

}

