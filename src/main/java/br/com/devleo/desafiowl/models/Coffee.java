package br.com.devleo.desafiowl.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "coffees")
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String userCpf;
    private String itemId;
    @Temporal(TemporalType.DATE)
    private Date coffeeDate;

    public Coffee() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCpf() {
        return userCpf;
    }

    public void setUserCpf(String userCpf) {
        this.userCpf = userCpf;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Date getCoffeeDate() {
        return coffeeDate;
    }

    public void setCoffeeDate(Date coffeeDate) {
        this.coffeeDate = coffeeDate;
    }
}