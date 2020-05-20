package com.assignment.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;

import com.assignment.shoppingcart.model.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Integer> {
}
