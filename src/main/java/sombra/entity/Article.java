package sombra.entity;

import javax.persistence.*;

@Entity
@Table(name="articles")
public class Article {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int price;

    @Column
    private int count;

    @Column
    private String photo;
	
	@ManyToOne
	@JoinColumn(name="category")
	private Category category;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
