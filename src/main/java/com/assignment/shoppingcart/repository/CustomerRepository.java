package com.assignment.shoppingcart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.assignment.shoppingcart.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
  Iterable<Customer> findAllByOrderByIdAsc();
}
