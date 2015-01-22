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
	
	@OneToMany(mapedBy=
	Set<Article> articles;
	
	@ManyToOne
	@JoinColumn(name="parent_category")
	private Category parentCategory;
	
	@OneToMany(mapedBy="parentCategory")
	Set<Category> categories;
	
	
}