public class Begin {

    static class Ncl {
        String name;

        public Ncl(String str) {

            name = str;
        }

        public void Yourname() {
            System.out.println(name);

        }
    }
    public static void main(String[] args) {

        /*String[] sarray = new String[4];
        for (int i = 0; i < 4; i++)
            sarray[i] = "Hey, Jude!";
        for (int i = 0; i < 4; i++)
            System.out.println(sarray[i]);*/
        Ncl nlc = new Ncl("Kate");
        nlc.Yourname();
    }

}
