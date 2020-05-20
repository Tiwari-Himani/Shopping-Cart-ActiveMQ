package com.assignment.shoppingcart.service;

import com.assignment.shoppingcart.config.MessageConfig;
import com.assignment.shoppingcart.config.MessageConstructSelectItem;
import com.assignment.shoppingcart.model.Customer;
import com.assignment.shoppingcart.model.Merchandise;
import com.assignment.shoppingcart.repository.CustomerRepository;
import com.assignment.shoppingcart.repository.MerchandiseRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SelectShoppingItem {
	private static Logger log = LoggerFactory.getLogger(SelectShoppingItem.class);

	@Autowired
	MerchandiseRepository merchandiseRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	JmsMessagingTemplate jmsMessagingTemplate;

	public void mockBatchItemSelect() {
		Iterable<Merchandise> merchandise = merchandiseRepository.findAll();
		List<Merchandise> merchandiseList = new ArrayList<>();
		merchandise.forEach(product -> merchandiseList.add(product));

		Iterable<Customer> customers = customerRepository.findAllByOrderByIdAsc();
		List<Customer> customerList = new ArrayList<>();
		customers.forEach(customer -> {
			customerList.add(customer);
		});

		publishSelectedCartItem(
				new MessageConstructSelectItem(1, customerList.get(0).getId(), merchandiseList.get(0).getId(), 2));
		publishSelectedCartItem(
				new MessageConstructSelectItem(1, customerList.get(0).getId(), merchandiseList.get(0).getId(), -1));
		publishSelectedCartItem(
				new MessageConstructSelectItem(1, customerList.get(0).getId(), merchandiseList.get(1).getId(), 1));
		publishSelectedCartItem(
				new MessageConstructSelectItem(2, customerList.get(1).getId(), merchandiseList.get(2).getId(), 3));
		publishSelectedCartItem(
				new MessageConstructSelectItem(1, customerList.get(0).getId(), merchandiseList.get(2).getId(), 1));
		publishSelectedCartItem(
				new MessageConstructSelectItem(3, customerList.get(2).getId(), merchandiseList.get(0).getId(), 1));
		publishSelectedCartItem(
				new MessageConstructSelectItem(3, customerList.get(2).getId(), merchandiseList.get(1).getId(), 5));
		publishSelectedCartItem(
				new MessageConstructSelectItem(3, customerList.get(2).getId(), merchandiseList.get(2).getId(), -1));
		publishSelectedCartItem(
				new MessageConstructSelectItem(3, customerList.get(2).getId(), merchandiseList.get(3).getId(), 2));
		publishSelectedCartItem(
				new MessageConstructSelectItem(3, customerList.get(2).getId(), merchandiseList.get(3).getId(), -3));
		publishSelectedCartItem(
				new MessageConstructSelectItem(4, customerList.get(3).getId(), merchandiseList.get(2).getId(), 1));
		publishSelectedCartItem(
				new MessageConstructSelectItem(4, customerList.get(3).getId(), merchandiseList.get(0).getId(), 1));
	}

	public void publishSelectedCartItem(MessageConstructSelectItem message) {
		 log.info("--CARTSENT--[Sent]: " + message);
		jmsMessagingTemplate.convertAndSend(MessageConfig.CART_ITEM_SELECT_QUEUE, message);
	}
}
