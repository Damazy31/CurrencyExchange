package exchanger;

import java.util.Objects;

public class Currency {
    private String name;
    private Double value;

    public Currency(){}

    public Currency(String name, Double value){
        this.name = name;
        this.value = value;
    }
}
