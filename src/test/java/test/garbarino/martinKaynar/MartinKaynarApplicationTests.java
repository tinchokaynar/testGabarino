package test.garbarino.martinKaynar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import test.garbarino.martinKaynar.models.Cart;
import test.garbarino.martinKaynar.models.ProductStock;
import test.garbarino.martinKaynar.repositories.RepoCart;
import test.garbarino.martinKaynar.repositories.RepoProductStock;
import test.garbarino.martinKaynar.services.CartService;

@SpringBootTest
class MartinKaynarApplicationTests {

	@Autowired 
	private RepoCart repoCart;
	
	@Autowired 
	private RepoProductStock repoProductStock;
	
	@Autowired
	private CartService cartService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void testPaymentGood() {
		Optional<Cart> cart = repoCart.findById(3L);
		Optional<ProductStock> pava = repoProductStock.findById(3L);
		Optional<ProductStock> notebook = repoProductStock.findById(4L);
		
		assertEquals(5, pava.get().getStock());
		assertEquals(15, notebook.get().getStock());
		assertEquals(Cart.Status.READY, cart.get().getStatus());
		
		cartService.payment(cart.get().getId());
		Optional<Cart> cartUpdate = repoCart.findById(3L);
		Optional<ProductStock> pavaUpdate = repoProductStock.findById(3L);
		Optional<ProductStock> notebookUpdate = repoProductStock.findById(4L);
		
		assertEquals(3, pavaUpdate.get().getStock());
		assertEquals(14, notebookUpdate.get().getStock());
		assertEquals(Cart.Status.PROCESSED, cartUpdate.get().getStatus());
	}
	
	@Test
	void testPaymentFail() {
		Optional<Cart> cart = repoCart.findById(2L);
		Optional<ProductStock> tele = repoProductStock.findById(2L);
		
		assertEquals(10, tele.get().getStock());
		assertEquals(Cart.Status.READY, cart.get().getStatus());
		
		cartService.payment(cart.get().getId());
		Optional<ProductStock> teleUpdate = repoProductStock.findById(2L);
		Optional<Cart> cartUpdate = repoCart.findById(2L);
		
		assertEquals(10, teleUpdate.get().getStock());
		assertEquals(Cart.Status.FAILED, cartUpdate.get().getStatus());
	}

}
