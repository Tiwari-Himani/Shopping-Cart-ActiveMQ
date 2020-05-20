package com.assignment.shoppingcart.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class CartItemId implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private ShoppingCart shoppingCart;
	@ManyToOne
	private Merchandise product;

	public CartItemId() {
	}

	public CartItemId(ShoppingCart shoppingCart, Merchandise product) {
		this.shoppingCart = shoppingCart;
		this.product = product;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public Merchandise getProduct() {
		return product;
	}

	public void setProduct(Merchandise product) {
		this.product = product;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof CartItemId))
			return false;
		CartItemId that = (CartItemId) o;
		return Objects.equals(getShoppingCart(), that.getShoppingCart())
				&& Objects.equals(getProduct(), that.getProduct());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getShoppingCart(), getProduct());
	}
}
