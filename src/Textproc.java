public class Textproc {

    String name;

    public Textproc(String str) {

        name = str;
    }

    public String Yourname() {
        return name;}
   //длина строки
    public int Simbnumber() {
        return name.length();}

    //слова
    public void Words() {
        String[] wordsar = name.split("\\s");
        for(String subStr:wordsar)
            System.out.println(subStr);}

}

