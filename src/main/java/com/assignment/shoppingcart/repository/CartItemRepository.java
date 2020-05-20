package com.assignment.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;

import com.assignment.shoppingcart.model.CartItem;
import com.assignment.shoppingcart.model.CartItemId;

public interface CartItemRepository extends CrudRepository<CartItem, CartItemId> {
}
