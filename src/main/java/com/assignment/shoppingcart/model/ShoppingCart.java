package com.assignment.shoppingcart.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class ShoppingCart implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	private Customer customer;
	private OrderStatus orderStatus;
	@OneToMany(mappedBy = "cartItemId.shoppingCart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<CartItem> cartItems = new HashSet<>();

	public enum OrderStatus implements Serializable {
		DRAFT, CANCELLED, PAYMENT_PENDING, PAYMENT_RECEIVED, SHIPPING_SCHEDULED, SHIPPED, DELIVERED, DELIVERY_CONFIRMED;
	}

	public ShoppingCart() {
	}

	public ShoppingCart(Customer customer) {
		this.customer = customer;
		this.orderStatus = OrderStatus.DRAFT;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ShoppingCart))
			return false;
		ShoppingCart that = (ShoppingCart) o;
		return getId() == that.getId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public String toString() {
		return "ShoppingCart [id=" + id + ", customer=" + customer + ", orderStatus=" + orderStatus + ", cartItems="
				+ cartItems + "]";
	}

}
