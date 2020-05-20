package com.assignment.shoppingcart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Merchandise implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	private String description;
	private String productCategory;
	private double price;

	public Merchandise() {
	}

	public Merchandise(String description, String productCategory, double price) {
		this.description = description;
		this.productCategory = productCategory;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Merchandise))
			return false;
		Merchandise product = (Merchandise) o;
		return getId() == product.getId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public String toString() {
		return "Product{" + "id=" + id + ", description='" + description + '\'' + ", productCategory='"
				+ productCategory + '\'' + ", price=" + price + '}';
	}
}
