package sombra.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;
	
	@Column(name="reg_date")
	private Timestamp registrationDate;

    @OneToMany
    @JoinTable(name="baskets")
    private List<Article> basket;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Article> getBasket() {
        return basket;
    }

    public void setBasket(List<Article> basket) {
        this.basket = basket;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
