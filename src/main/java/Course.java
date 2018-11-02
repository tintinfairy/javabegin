<<<<<<< HEAD
import java.util.HashMap;
import java.util.Map;

public class Course {
    private String title;
    private String description;
    private Map<String, Integer> uniwords;


    public Course(String title, String description) {
        TextProc textProc = new TextProc(description);
        this.title = title;
        this.description = description;
        this.uniwords = textProc.getHashmap();
    }

}
=======
import java.util.HashMap;
import java.util.Map;

public class Course {
    private String title;
    private String description;
    private Map<String, Integer> uniwords;


    public Course(String title, String description) {
        TextProc textProc = new TextProc(description);
        this.title = title;
        this.description = description;
        this.uniwords = textProc.getHashmap();
    }

}

>>>>>>> 8dfea58d22d3c60948c37dfa2cd28eb4b994c362
