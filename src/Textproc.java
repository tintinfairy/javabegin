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
    public String[] Words() {
      return name.split("\\s");
    }

}
