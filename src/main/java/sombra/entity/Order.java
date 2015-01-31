package sombra.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue
    int id;

    @Column
    private String userEmail;

    @Column
    private String userPhone;

    @ElementCollection
    @CollectionTable(name="order_articles", joinColumns = {@JoinColumn(name="order_id")})
    @Column(name = "count")
    @MapKeyJoinColumn(name = "article_id")
    private Map<Article, Integer> orderMap;

    @Column
    private Date date;

    public Order(String email, String phone, Map<Article, Integer> orderMap) {
        this.userEmail = email;
        this.userPhone = phone;
        this.orderMap = orderMap;
        this.date = new Date();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Map<Article, Integer> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(Map<Article, Integer> orderMap) {
        this.orderMap = orderMap;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
