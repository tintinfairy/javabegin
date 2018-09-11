public class Begin {

    public class Ncl {
        String name;
        public Ncl(String str) {

            name=str;
            System.out.println(str);
        }

        public void Yourname(String name){System.out.println(name);}
    }
        public static void main(String[] args) {

            String[] sarray = new String[4];
            for (int i = 0; i < 4; i++)
                sarray[i] = "Hey, Jude!";
            for (int i = 0; i < 4; i++)
                System.out.println(sarray[i]);
    }
}
