package exchanger;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;


public class ExchangeRate {
    private String table;
    private String currency;
    private String code;
    private Rates[] rates;

    public ExchangeRate(){}

    public ExchangeRate(@JsonProperty("table") String table,
                        @JsonProperty("currency") String currency,
                        @JsonProperty("code") String code,
                        @JsonProperty("rates") Rates[] rates){
        this.table = table;
        this.currency = currency;
        this.code = code;
        this.rates = rates;
    }

    public Rates[] getRates() {
        return rates;
    }

    public Double getAsk() {
        return rates[0].getAsk();
    }

    public Double getBid() {
        return rates[0].getBid();
    }

    public boolean checkDate(){
        LocalDate currentDate = LocalDate.now();
        LocalDate ratesDate = LocalDate.parse(rates[0].getEffectiveDate());
        return ratesDate.isBefore(currentDate);
    }
}
