public class UseCustomException {

MyException newExc = new MyException("This is a custom exception");


public UseCustomException() throws MyException {
    System.out.println("Hello Back Again with custom exception");
    throw newExc;
}


public static void main(String args[]) {
    try {
        UseCustomException use = new UseCustomException();
    } catch (MyException myEx) {
        System.out.println("This is my custom exception:" + myEx.getMessage());
    }
}
}