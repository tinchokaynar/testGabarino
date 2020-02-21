package test.garbarino.martinKaynar.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import test.garbarino.martinKaynar.dtos.AddProductDto;
import test.garbarino.martinKaynar.dtos.CreateCartDto;
import test.garbarino.martinKaynar.dtos.ResponseAddProductDto;
import test.garbarino.martinKaynar.dtos.ResponseCartDto;
import test.garbarino.martinKaynar.dtos.ResponseGetProductsDto;
import test.garbarino.martinKaynar.exceptions.CantNullException;
import test.garbarino.martinKaynar.exceptions.CustomException;
import test.garbarino.martinKaynar.exceptions.NotFoundException;
import test.garbarino.martinKaynar.models.Cart;
import test.garbarino.martinKaynar.models.ProductCart;
import test.garbarino.martinKaynar.models.ProductStock;
import test.garbarino.martinKaynar.services.CartService;
import test.garbarino.martinKaynar.services.ProductService;

@RestController
public class CartController {

	@Autowired
	private CartService cartService;

	@Autowired
	private ProductService ProductService;

	@PostMapping("/cart")
	@ResponseStatus(HttpStatus.CREATED)
	public Cart createCart(@Valid @RequestBody CreateCartDto createCartDto) {
		Cart cart = cartService.createCart(createCartDto);
		return cart;
	}

	@PostMapping("cart/{id}/products")
	public ResponseAddProductDto addProduct(@PathVariable Long id, @RequestBody AddProductDto addProductDto) {
		try {
			Cart cart = cartService.getCart(id);
			ProductStock productStock = ProductService.getProduct(addProductDto.getId());			
			ProductCart productCart = cartService.addProduct(cart, productStock, addProductDto.getQuantity());
			
			ResponseAddProductDto response = new ResponseAddProductDto();
			response.setIdProductStock(productStock.getId());
			response.setIdProductCart(productCart.getId());
			response.setQuantity(productCart.getQuantity());
			
			return response;
		} catch (CantNullException | CustomException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}

	}
	
	//Asumi que el id que se recibe no es el del producto en si, si no el Id del product en el carrito, por lo tanto busco el que coincide.
	@DeleteMapping("/carts/{cartId}/products/{productId}")
	public void deleteProduct(@PathVariable Long cartId, @PathVariable Long productId) {
		try {
			
			cartService.deleteProduct(cartId, productId);
		} catch (CantNullException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@GetMapping("/carts/{id}/products")
	public ResponseGetProductsDto getProducts(@PathVariable Long id) {
		ResponseGetProductsDto response = new ResponseGetProductsDto();
		try {
			Cart cart = cartService.getCart(id);
			response.setProductList( cartService.getProducts(cart));
			return response;
		} catch (CantNullException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
	
	@GetMapping("carts/{id}")
	public ResponseCartDto getCart(@PathVariable Long id) {
		try {
			Cart cart = cartService.getCart(id);
			return cartService.getCart(cart);
		} catch (CantNullException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@PostMapping("/carts/{id}/checkout")
	public void moveToReady(@PathVariable Long id) {
		try {
			Cart cart = cartService.getCart(id);
			cartService.moveToReady(cart);
		} catch (CantNullException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		} catch (NotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
