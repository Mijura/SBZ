package com.sbz.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ArticleCategory {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private int maxDiscount;
	
	@ManyToOne
	private ArticleCategory superCategory;
	
	public ArticleCategory(){}

	public ArticleCategory(Long id, String name, int maxDiscount, ArticleCategory superCategory) {
		super();
		this.id = id;
		this.name = name;
		this.maxDiscount = maxDiscount;
		this.superCategory = superCategory;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxDiscount() {
		return maxDiscount;
	}

	public void setMaxDiscount(int maxDiscount) {
		this.maxDiscount = maxDiscount;
	}

	public ArticleCategory getSuperCategory() {
		return superCategory;
	}

	public void setSuperCategory(ArticleCategory superCategory) {
		this.superCategory = superCategory;
	}
	
	
}
