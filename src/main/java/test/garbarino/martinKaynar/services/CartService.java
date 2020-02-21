package test.garbarino.martinKaynar.services;

import java.util.List;

import test.garbarino.martinKaynar.dtos.CreateCartDto;
import test.garbarino.martinKaynar.dtos.ProductCartDto;
import test.garbarino.martinKaynar.dtos.ResponseCartDto;
import test.garbarino.martinKaynar.exceptions.CantNullException;
import test.garbarino.martinKaynar.exceptions.CustomException;
import test.garbarino.martinKaynar.exceptions.NotFoundException;
import test.garbarino.martinKaynar.models.Cart;
import test.garbarino.martinKaynar.models.ProductCart;
import test.garbarino.martinKaynar.models.ProductStock;

public interface CartService {
	
	public Cart createCart(CreateCartDto createCartDto);
	
	public ProductCart addProduct(Cart cart, ProductStock prod, Long quantity) throws CustomException;
	
	public Cart getCart(Long id) throws NotFoundException,CantNullException;
	
	public void deleteProduct(Long idCart, Long idProduct) throws NotFoundException,CantNullException;
	
	public List<ProductCartDto> getProducts(Cart cart);
	
	public void moveToReady(Cart cart);

	public ResponseCartDto getCart(Cart cart);
	
	public void payment(Long idCart);

}
