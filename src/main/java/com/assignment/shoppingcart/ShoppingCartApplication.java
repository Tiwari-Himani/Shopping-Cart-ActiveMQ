package com.assignment.shoppingcart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.assignment.shoppingcart.service.SelectShoppingItem;

@SpringBootApplication
public class ShoppingCartApplication implements CommandLineRunner {
	private static Logger log = LoggerFactory.getLogger(ShoppingCartApplication.class);

	@Autowired
	SelectShoppingItem selectShoppingItem;

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("**************** HAPPY SHOPPING WITH US *****************");

		selectShoppingItem.mockBatchItemSelect();

		log.info("----------------------------------------------------------");
	}
}
