package sombra.entity;

import org.hibernate.validator.constraints.Email;
import sombra.validator.Unique;
import sombra.validator.UniqueField;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Map;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @Column
    @NotNull(message = "Поле логін не може бути пустим!")
    @Size(min = 4, message = "Логін повинен складатись не менш ніж з 4 символів!")
    @Pattern(regexp = "^(\\w){3,}$", message = "Логін повинен складатись з латинських літер!")
    @Unique(field = UniqueField.NAME, message = "Користувач з таким іменем вже зареєстрований")
    private String name;

    @Column
    @NotNull
    @Email(message = "Така адреса електронної пошти не існує!")
    @Unique(field = UniqueField.EMAIL, message = "Користувач з такою адресою електронної пошти вже зареєстрований")
    private String email;

    @Column
    @NotNull
    @Pattern(regexp = "^\\+380[0-9]{9}$", message = "Такий телефон не існує")
    @Unique(field = UniqueField.PHONE, message = "Користувач з таким номером телефону вже зареєстрований")
    private String phone;

    @Column
    @NotNull
    @Size(min = 6, message = "Пароль повинен містити не менше 6 символів!")
    private String password;
	
	@Column(name="reg_date")
	private Date registrationDate;


    @ElementCollection
    @CollectionTable(name="baskets", joinColumns = {@JoinColumn(name="user_id")})
    @Column(name = "count")
    @MapKeyJoinColumn(name = "article_id")
    private Map<Article, Integer> basket;

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

    public Map<Article, Integer> getBasket() {
        return basket;
    }

    public void setBasket(Map<Article, Integer> basket) {
        this.basket = basket;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
