import java.util.HashMap;
import java.util.Map;

public class TextProc {

    private String text;
    private Map<String, Integer> hashmap = new HashMap<String, Integer>();


    public TextProc(String name) {
        this.text = name;
    }

    public String[] words() {
        return text.split("\\s");
    }


    public Map<String, Integer> getHashmap() {
        String[] words = this.words();
        for (String w : words) {
            Integer i = hashmap.get(w);
            if (i == null)
                hashmap.put(w, 1);
            else hashmap.put(w, i + 1);
        }

        return hashmap;
    }
}