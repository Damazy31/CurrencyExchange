package exchanger;

public class LackOfFundsException extends RuntimeException{
    LackOfFundsException(){
        super("Lack of funds on account");
    }
}
