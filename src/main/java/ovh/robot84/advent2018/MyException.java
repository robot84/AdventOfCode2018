package ovh.robot84.advent2018;

public class MyException extends Exception {

public MyException(String exc) {
    super(exc);
}


public String getMessage() {
    return super.getMessage();
}
}