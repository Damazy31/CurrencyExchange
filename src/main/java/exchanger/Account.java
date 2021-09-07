package exchanger;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Account {
    private @Id @GeneratedValue Long id;
    private String name;
    private String surname;
    private Double pln;
    private Double usd;

    public Account() {
        this.usd = (double)0;
    }

    public Account(String name, String surname, Double pln){
        this.name = name;
        this.surname = surname;
        this.pln = pln;
        this.usd = (double) 0;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() { return surname;}

    public Double getPln() {return pln;}

    public Double getUsd() {return usd;}

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPln(Double pln) {
        this.pln = pln;
    }

    public void setUsd(Double usd) {
        this.usd = usd;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, pln, usd);
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof Account))
            return false;
        Account account = (Account) o;
        return Objects.equals(this.id, account.id) && Objects.equals(this.name, account.name)
                && Objects.equals(this.surname, account.surname) && Objects.equals(this.pln, account.pln)
                && Objects.equals(this.usd, account.usd);
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + this.id + ", name='" + this.name + '\'' + ", surname='" + this.surname + '\''
                + ", PLN=" + this.pln + '\'' + ", USD=" + this.usd + '\'' + '}';
    }

}
