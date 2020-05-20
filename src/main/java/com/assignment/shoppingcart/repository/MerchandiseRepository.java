package com.assignment.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;

import com.assignment.shoppingcart.model.Merchandise;

public interface MerchandiseRepository extends CrudRepository<Merchandise, Integer> {
}
