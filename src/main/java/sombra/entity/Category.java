package sombra.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="categories")
public class Category {
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@OneToMany(mappedBy = "category")
	private Set<Article> articles;
	
	@ManyToOne
	@JoinColumn(name="parent_category")
	private Category parentCategory;
	
	@OneToMany(mappedBy="parentCategory")
	Set<Category> categories;
	
	
}