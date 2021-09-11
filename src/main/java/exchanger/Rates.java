package exchanger;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rates {
    private String no;
    private String effectiveDate;
    private Double bid;
    private Double ask;

    public Rates(@JsonProperty("no") String no,
                 @JsonProperty("effectiveDate") String effectiveDate,
                 @JsonProperty("bid") Double bid,
                 @JsonProperty("ask") Double ask){
        this.no = no;
        this.effectiveDate = effectiveDate;
        this.bid = bid;
        this.ask = ask;
    }

    public Double getAsk() {
        return ask;
    }

    public Double getBid() {
        return bid;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }
}
