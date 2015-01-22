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
	
	@ManyToOne
	@JoinColumn(name="category")
	private Category category;

}
