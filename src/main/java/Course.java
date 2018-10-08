import java.util.HashMap;
import java.util.Map;

public class Course {
    private String title;
    private String description;
    private Map<String, Integer> uniwords;


    public Course(String title, String description) {
        GeekBrainsCourseCollector information = new GeekBrainsCourseCollector();
        TextProc textProc = new TextProc(information.getJson());
        this.title = title;
        this.description = description;
        this.uniwords = textProc.getHashmap();
    }

}

