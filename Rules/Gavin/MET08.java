/* 
 * Example for MET08
 * Author: Gavin Scheidler
*/
public class MET08 {
    private String s;

    public MET08(String s) {
        if (s == null) {
            throw new NullPointerException();
        }
        this.s = s;
    }

    public boolean equals(MET08 o) {
        return s.equalsIgnoreCase(s);
    }

    public static void main(String[] args) {
        MET08 c = new MET08("Java");
        String s = "java";
        System.out.println(c.equals(s));
        System.out.println(s.equals(c));
    }

}
