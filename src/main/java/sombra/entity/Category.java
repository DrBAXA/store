package sombra.entity;

import javax.persistence.*;

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
	
	@ManyToOne
	@JoinColumn(name="parent_category")
	private Category parentCategory;

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

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}
}