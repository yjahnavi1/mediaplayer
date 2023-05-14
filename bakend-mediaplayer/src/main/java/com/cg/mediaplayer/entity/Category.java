package com.cg.mediaplayer.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotNull(message = "Category name cannot be null!")
	@NotBlank(message = "Category name connot be blank!")
	private String categoryName;
	
	@OneToMany(targetEntity=Videos.class,cascade=CascadeType.ALL)
	@JoinColumn(name="cv_fk",referencedColumnName="id")
	private List<Videos> videos;
	
	public Category(){
		super();
	}
	public Category(int id, String categoryName) {
		super();
		this.id = id;
		this.categoryName = categoryName;
	}
	
	
	public Category(int id,
			@NotNull(message = "Category name cannot be null!") @NotBlank(message = "Category name connot be blank!") String categoryName,
			List<Videos> videos) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.videos = videos;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryName=" + categoryName + ", videos=" + videos + "]";
	}
	
}
