import java.util.HashMap;
import java.util.Map;

public class Course {
    private String title;
    private String description;
    private String[] words;
    private Map<String, Integer> uniwords = new HashMap<String, Integer>();
    TextProc textProc = new TextProc();

    public Course(String title, String description) {
        this.title = title;
        this.description = description;
        this.words = this.description.split("\\s");
        this.uniwords = textProc.getHashmap();
    }

}

