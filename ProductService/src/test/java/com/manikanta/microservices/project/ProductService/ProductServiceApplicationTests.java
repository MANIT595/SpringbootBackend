package com.manikanta.microservices.project.ProductService;

import com.manikanta.microservices.project.ProductService.Entity.Product;
import com.manikanta.microservices.project.ProductService.Service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private ProductService productService;

	@Test
	public void testGetUser_Success() throws Exception {
		Long userId = 1L;
		Product user = new Product();
		user.setProductId(userId);
		user.setProductDesc("test");
		user.setProductName("test");
		user.setBrand("test");
		user.setQuantity(1L);
		user.setOrderId(1L);

		productService.addProduct(user);

		Product response = productService.getProduct(userId);
//		Assert.assertNotNull(response);
//		Assert.assertEquals(response.getBody().getEmail(), "test@example.com");
//		Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

}
