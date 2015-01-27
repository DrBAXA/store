package sombra.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column
    @NotNull(message = "Поле логін не може бути пустим!")
    @Size(min = 4, message = "Логін повинен складатись не менш ніж з 4 символів!")
    @Pattern(regexp = "^(\\w){3,}$",
    message = "Логін повинен складатись з латинських літер!")
    private String name;

    @Column
    @NotNull
    @Pattern(regexp = "^[A-Za-z]([A-Za-z0-9])+([\\.\\-\\_]?[A-Za-z0-9]+)*@([a-z0-9-])+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$",
    message = "Така адреса електронної пошти не існує!")
    private String email;

    @Column
    @NotNull
    @Size(min = 6, message = "Пароль повинен містити не менше 6 символів!")
    private String password;
	
	@Column(name="reg_date")
	private Date registrationDate;

    @OneToMany
    @JoinTable(name="baskets",
               joinColumns = {@JoinColumn(name = "user_id")},
               inverseJoinColumns = {@JoinColumn(name = "article_id")})
    private List<Article> basket;

    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;

    public User() {
    }

    public User(String name, String email, String password, Date registrationDate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
