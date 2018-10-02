public class Course {
    private String title;
    private String description;
    private String[] words;
    private int uniwords;
    TextProc textProc = new TextProc();

    public Course(String title, String description) {
        this.title = title;
        this.description = description;
        this.words = this.description.split("\\s");
        this.uniwords = textProc.wordsUniqueness(this.words);
    }

}

