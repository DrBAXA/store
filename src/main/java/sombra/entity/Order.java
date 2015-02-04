package sombra.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

enum OrderState{
    READY, DONE, CANCELED;
}

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
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Enumerated(EnumType.ORDINAL)
    private OrderState state;

    @Column
    private int price;

    public Order() {
    }

    public Order(String email, String phone, Map<Article, Integer> orderMap) {
        this.userEmail = email;
        this.userPhone = phone;
        this.orderMap = orderMap;
        this.state = OrderState.READY;
        this.date = new Date();
        int price = 0;
        for(Article article : orderMap.keySet()){
            price += article.getPrice()*orderMap.get(article);
        }
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
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
