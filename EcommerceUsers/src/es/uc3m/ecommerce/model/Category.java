package es.uc3m.ecommerce.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the categories database table.
 * 
 */
@Entity
@Table(name="categories")

@NamedQueries({
	@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c"),
	@NamedQuery(name="Category.findById", query="SELECT c FROM Category c WHERE c.categoryId = :categoryId"),
	@NamedQuery(name="Category.findByName", query="SELECT c FROM Category c WHERE c.categoryName = :categoryName")	
//	@NamedQuery(name="Category.findRootCategories",
//		query="SELECT c FROM Category c WHERE c.categoryId in (SELECT DISTINCT c.category FROM Category c) AND c.category IS NOT NULL"),
//	@NamedQuery(name="Category.findChildCategories", 
//			query="SELECT c FROM Category c WHERE c.categoryId = :parentId")
})

public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int categoryId;

	private String categoryName;

	private int isDeleted;
	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="parentId")
	private Category category;

	//bi-directional many-to-one association to Category
	@OneToMany(mappedBy="category")
	private List<Category> categories;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="categoryBean")
	private List<Product> products;

	public Category() {
	}

	public int getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}	
	
	public Category addCategory(Category category) {
		getCategories().add(category);
		category.setCategory(this);

		return category;
	}

	public Category removeCategory(Category category) {
		getCategories().remove(category);
		category.setCategory(null);

		return category;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
}