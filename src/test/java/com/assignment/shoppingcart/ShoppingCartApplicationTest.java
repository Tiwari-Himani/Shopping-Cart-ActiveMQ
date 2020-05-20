package com.assignment.shoppingcart;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.assignment.shoppingcart.service.SelectShoppingItem;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartApplicationTest {

	@Autowired
	ShoppingCartApplication shoppingCartServiceApplication;

	@MockBean
	SelectShoppingItem shoppingItemSelectService;

	@Test
	public void testShoppingCartApplication() {
		verify(shoppingItemSelectService, times(1)).mockBatchItemSelect();
	}
}