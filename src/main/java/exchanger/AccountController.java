package exchanger;

import java.util.List;

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
    private final ExchangeRate exchangeRate;

    AccountController(AccountRepository repository){
        this.repository = repository;
        exchangeRate = new ExchangeRate();
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

        return repository.findById(id)
                .map(account -> {
                    try{
                        account.buy(addedUsd, exchangeRate.getBuy());
                        return repository.save(account);
                    }
                    catch (LackOfFundsException e){
                        throw e;
                    }
                })
                .orElseThrow(() -> new AccountNotFoundException(id));
    }
    @PutMapping("/accounts/{id}/sell")
    Account sellUsd(@RequestBody Double addedUsd, @PathVariable Long id) {

        return repository.findById(id)
                .map(account -> {
                    try{
                        account.sell(addedUsd, exchangeRate.getSell());
                        return repository.save(account);
                    }
                    catch (LackOfFundsException e){
                        throw e;
                    }

                })
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    @DeleteMapping("/accounts/{id}")
    void deleteAccount(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
