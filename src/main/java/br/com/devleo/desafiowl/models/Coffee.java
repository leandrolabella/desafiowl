package br.com.devleo.desafiowl.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "coffees")
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_ID")
    private Item item;

    @Temporal(TemporalType.DATE)
    private Date coffeeDate;

    public Coffee() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItemid(String itemid){
        this.item = new Item(Long.parseLong(itemid), null, null, null);
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Date getCoffeeDate() {
        return coffeeDate;
    }

    public void setCoffeeDate(String coffeeDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.coffeeDate = format.parse(coffeeDate);
        } catch (ParseException ignored) {
        }
    }
}