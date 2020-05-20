package com.assignment.shoppingcart.service;

import com.assignment.shoppingcart.config.MessageConfig;
import com.assignment.shoppingcart.config.MessageConstructSelectItem;
import com.assignment.shoppingcart.model.*;
import com.assignment.shoppingcart.repository.CartItemRepository;
import com.assignment.shoppingcart.repository.CustomerRepository;
import com.assignment.shoppingcart.repository.MerchandiseRepository;
import com.assignment.shoppingcart.repository.ShoppingCartRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class SelectItemMessageReceive {
	private static Logger log = LoggerFactory.getLogger(SelectItemMessageReceive.class);

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	ShoppingCartRepository shoppingCartRepository;
	@Autowired
	CartItemRepository cartItemRepository;
	@Autowired
	MerchandiseRepository merchandiseRepository;

	@JmsListener(destination = MessageConfig.CART_ITEM_SELECT_QUEUE, containerFactory = "myFactory")
	public void execute(MessageConstructSelectItem message) {
		log.info("--CARTRECIEVED--[Received]: " + message);

		ShoppingCart shoppingCart = shoppingCartRepository.findById(message.getShoppingCartId()).orElse(null);

		if (null == shoppingCart) {
			Customer customer = customerRepository.findById(message.getCustomerId()).orElse(null);
			shoppingCart = new ShoppingCart(customer);
			shoppingCart = shoppingCartRepository.save(shoppingCart);
		}

		Merchandise merchandise = merchandiseRepository.findById(message.getProductId()).orElse(null);

		int itemCount = message.getCount();
		CartItemId cartItemId = new CartItemId(shoppingCart, merchandise);
		CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(null);

		if (null != cartItem) {
			itemCount += cartItem.getQuantity();

			if (itemCount > 0) {
				cartItem.setQuantity(itemCount);
				cartItemRepository.save(cartItem);
			} else {
				cartItemRepository.delete(cartItem);
			}
		} else {
			if (itemCount > 0) {
				cartItemRepository.save(new CartItem(cartItemId, itemCount));
			}
		}

		log.info("--CARTRECIEVED--[Persisted]: " + shoppingCartRepository.findById(shoppingCart.getId()).orElse(null));
	}

}
