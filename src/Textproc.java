public class Textproc {

    String name;

    public Textproc(String str) {

        name = str;
    }

    public String Yourname() {
        return name;}
   //число слов
    public int Simbnumber(String[] mass) {
        int c=0;
        for(String sub:mass) c++;
        return c-1;}

    //слова
    public String[] Words() {
      return name.split("\\s");
    }

}
