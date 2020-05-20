package com.assignment.shoppingcart.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private Sex sex;

	@OneToMany(mappedBy = "customer")
	private List<ShoppingCart> shoppingCartList = new ArrayList<>();

	public enum Sex {
		MALE, FEMALE;
	}

	public Customer() {
	}

	public Customer(String name, Sex sex) {
		this.name = name;
		this.sex = sex;

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

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Customer))
			return false;
		Customer customer = (Customer) o;
		return Objects.equals(getId(), customer.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", sex=" + sex + "]";
	}

}
