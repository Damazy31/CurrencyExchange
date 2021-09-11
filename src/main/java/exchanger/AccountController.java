package exchanger;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private final AccountRepository repository;
    private ExchangeRate exchangeRate;
    private final HTTPHandler httpHandler;

    AccountController(AccountRepository repository){
        this.repository = repository;
        httpHandler = new HTTPHandler();
        exchangeRate =new ExchangeRate();
    }

    @GetMapping("/accounts/{id}")
    Account one(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    @PostMapping("/accounts")
    Account newAccount(@RequestBody Account newAccount){
        return repository.save(newAccount);
    }

    @PutMapping("/accounts/{id}/buy")
    Account buyUsd(@RequestBody Double addedUsd, @PathVariable Long id) {
        if(exchangeRate.getRates() == null || exchangeRate.checkDate()) {
            getNewExchangeRate();
        }
        return repository.findById(id)
                .map(account -> {
                    account.buy(addedUsd, exchangeRate.getAsk());
                    return repository.save(account);
                })
                .orElseThrow(() -> new AccountNotFoundException(id));
    }
    @PutMapping("/accounts/{id}/sell")
    Account sellUsd(@RequestBody Double addedUsd, @PathVariable Long id) {
        if(exchangeRate.getRates() == null || exchangeRate.checkDate()) {
            getNewExchangeRate();
        }
        return repository.findById(id)
                .map(account -> {
                    account.sell(addedUsd, exchangeRate.getBid());
                    return repository.save(account);

                })
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    @DeleteMapping("/accounts/{id}")
    void deleteAccount(@PathVariable Long id) {
        repository.deleteById(id);
    }

    private void getNewExchangeRate(){
        try {
            exchangeRate = httpHandler.synchronousRequest();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
