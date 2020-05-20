package com.assignment.shoppingcart;

import com.assignment.shoppingcart.config.MessageConstructSelectItem;
import com.assignment.shoppingcart.model.*;
import com.assignment.shoppingcart.repository.CartItemRepository;
import com.assignment.shoppingcart.repository.CustomerRepository;
import com.assignment.shoppingcart.repository.MerchandiseRepository;
import com.assignment.shoppingcart.repository.ShoppingCartRepository;
import com.assignment.shoppingcart.service.SelectItemMessageReceive;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { SelectItemMessageReceive.class, CartItemRepository.class, MerchandiseRepository.class,
		CustomerRepository.class, ShoppingCartRepository.class })
public class SelectItemMessageReceiveTest {

	@Autowired
	SelectItemMessageReceive itemSelectMessageReceiveService;
	@MockBean
	CustomerRepository customerRepository;
	@MockBean
	CartItemRepository cartItemRepository;
	@MockBean
	MerchandiseRepository merchandiseRepository;
	@MockBean
	ShoppingCartRepository shoppingCartRepository;

	@Test
	public void testCartAndCartItem() {
		MessageConstructSelectItem message = new MessageConstructSelectItem(1, 1, 1, 1);

		ShoppingCart shoppingCart = new ShoppingCart();
		when(shoppingCartRepository.findById(anyInt())).thenReturn(Optional.of(shoppingCart))
				.thenReturn(Optional.of(shoppingCart));

		Merchandise product = new Merchandise();
		when(merchandiseRepository.findById(anyInt())).thenReturn(Optional.of(product));

		CartItem cartItem = new CartItem(new CartItemId(), 0);
		when(cartItemRepository.findById(any(CartItemId.class))).thenReturn(Optional.of(cartItem));

		when(cartItemRepository.save(any(CartItem.class))).thenReturn(cartItem);

		assertThat(message.getCount()).isGreaterThan(0);
		itemSelectMessageReceiveService.execute(message);

		verify(shoppingCartRepository, times(2)).findById(anyInt());
		verify(merchandiseRepository, times(1)).findById(anyInt());
		verify(cartItemRepository, times(1)).save(any(CartItem.class));
	}

	@Test
	public void testItemcount() {

		Merchandise merchandise = new Merchandise();
		when(merchandiseRepository.findById(anyInt())).thenReturn(Optional.of(merchandise));

		when(cartItemRepository.findById(any(CartItemId.class))).thenReturn(Optional.empty());

		ShoppingCart shoppingCart = new ShoppingCart();
		when(shoppingCartRepository.findById(anyInt())).thenReturn(Optional.of(shoppingCart))
				.thenReturn(Optional.of(shoppingCart));

		MessageConstructSelectItem message = new MessageConstructSelectItem(1, 1, 1, -1);

		assertThat(message.getCount()).isLessThanOrEqualTo(0);
		itemSelectMessageReceiveService.execute(message);

		verify(merchandiseRepository, times(1)).findById(anyInt());
		verify(shoppingCartRepository, times(2)).findById(anyInt());
		verify(cartItemRepository, times(0)).save(any(CartItem.class));
	}
}