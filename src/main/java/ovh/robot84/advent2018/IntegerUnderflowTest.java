package ovh.robot84.advent2018;

public class IntegerUnderflowTest {
public static void main(String[] args) {
    int a = 1, b = 2;
    a = Integer.MIN_VALUE;
    b = Integer.MIN_VALUE - 1;
    System.out.println(a);
    System.out.println(b);
    /*
    -2147483648
    2147483647
     */
}
}
