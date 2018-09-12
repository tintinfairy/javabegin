/**
 * Created by Ксения on 12.09.2018.
 */
public class Textproc {

    String name;

    public Textproc(String str) {

        name = str;
    }

    public void Yourname() {
        System.out.println(name);}
   //длина строки
    public void Simbnumber() {
        System.out.println(name.length());}

    //слова
    public void Words() {
        String[] wordsar = name.split("\\s");
        for(String subStr:wordsar)
        System.out.println(subStr);}

}
