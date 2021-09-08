package exchanger;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class LackOfFundsAdvice {

    @ResponseBody
    @ExceptionHandler(LackOfFundsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String lackOfFundsHandler(LackOfFundsException ex){
        return ex.getMessage();
    }
}
