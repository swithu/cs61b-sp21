package flik;

/** An Integer tester created by Flik Enterprises.
 * @author Josh Hug
 * */
public class Flik {
    /** @param a Value 1
     *  @param b Value 2
     *  @return Whether a and b are the same */
    public static boolean isSameNumber(int a, int b) {
        return a == b;

        /*
        * You can also write:
        *
        * public static boolean isSameNumber(Integer a, Integer b) {
        *   return a.equals(b);
        * }
        *
        * Since there is an Integer Caching Mechanism issue with
        * Integer object, Integer a == Integer b won't work after
        * both a and b hitting the number 128. a and b become two
        * different objects with different addresses (reference).
        *
        * But this won't affect int a and int b using a == b, cause
        * int is a primitive type, while Integer is an object.
        */
    }
}
