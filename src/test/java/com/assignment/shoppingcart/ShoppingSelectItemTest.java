package com.assignment.shoppingcart;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.shoppingcart.config.MessageConstructSelectItem;
import com.assignment.shoppingcart.model.Customer;
import com.assignment.shoppingcart.model.Merchandise;
import com.assignment.shoppingcart.repository.CustomerRepository;
import com.assignment.shoppingcart.repository.MerchandiseRepository;
import com.assignment.shoppingcart.service.SelectShoppingItem;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { SelectShoppingItem.class, MerchandiseRepository.class, CustomerRepository.class,
		JmsMessagingTemplate.class })
public class ShoppingSelectItemTest {
	@MockBean
	CustomerRepository customerRepository;
	@MockBean
	MerchandiseRepository merchandiseRepository;
	@MockBean
	JmsMessagingTemplate jmsMessagingTemplate;
	@SpyBean
	SelectShoppingItem shoppingItemSelectService;

	@Test
	public void testMockBatchItemSelect() {

		when(merchandiseRepository.findAll()).thenReturn(IntStream.range(1, 11).mapToObj(i -> {
			Merchandise m = new Merchandise();
			m.setId(i);
			return m;
		}).collect(Collectors.toList()));

		when(customerRepository.findAllByOrderByIdAsc()).thenReturn(IntStream.range(1, 11).mapToObj(i -> {
			Customer c = new Customer();
			c.setId(i);
			return c;
		}).collect(Collectors.toList()));

		shoppingItemSelectService.mockBatchItemSelect();
		verify(merchandiseRepository, times(1)).findAll();
		verify(customerRepository, times(1)).findAllByOrderByIdAsc();
		verify(shoppingItemSelectService, times(12)).publishSelectedCartItem(any(MessageConstructSelectItem.class));
	}

	@Test
	public void testPublishSelectedCartItem() {
		MessageConstructSelectItem message = new MessageConstructSelectItem(1, 1, 1, 1);
		shoppingItemSelectService.publishSelectedCartItem(message);
		verify(jmsMessagingTemplate, times(1)).convertAndSend(anyString(), any(MessageConstructSelectItem.class));
	}
}