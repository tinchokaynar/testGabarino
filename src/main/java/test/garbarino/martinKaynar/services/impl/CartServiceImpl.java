package test.garbarino.martinKaynar.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.garbarino.martinKaynar.dtos.CreateCartDto;
import test.garbarino.martinKaynar.dtos.ProductCartDto;
import test.garbarino.martinKaynar.dtos.ResponseCartDto;
import test.garbarino.martinKaynar.exceptions.CantNullException;
import test.garbarino.martinKaynar.exceptions.CustomException;
import test.garbarino.martinKaynar.exceptions.NotFoundException;
import test.garbarino.martinKaynar.models.Cart;
import test.garbarino.martinKaynar.models.ProductCart;
import test.garbarino.martinKaynar.models.ProductStock;
import test.garbarino.martinKaynar.repositories.RepoCart;
import test.garbarino.martinKaynar.repositories.RepoProductCart;
import test.garbarino.martinKaynar.repositories.RepoProductStock;
import test.garbarino.martinKaynar.services.CartService;

@Service
public class CartServiceImpl implements CartService {
	
	private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

	@Autowired
	private RepoCart repoCart;

	@Autowired
	private RepoProductStock repoProductoStock;

	@Autowired
	private RepoProductCart repoProductCart;

	public Cart createCart(CreateCartDto createCartDto) {
		Cart cart = new Cart();
		cart.setEmail(createCartDto.getEmail());
		cart.setFullName(createCartDto.getFullName());
		repoCart.save(cart);
		return cart;
	}

	@Override
	public Cart getCart(Long id) throws NotFoundException, CantNullException {
		if (id != null) {
			return repoCart.findById(id).orElseThrow(() -> new NotFoundException("Cart", id));
		} else {
			throw new CantNullException("Id Cart");
		}
	}

	@Override
	public ProductCart addProduct(Cart cart, ProductStock prod, Long quantity) throws CustomException {
		if (prod.getStock() < quantity) {
			throw new CustomException("There's no stock for product id " + prod.getId());
		} else {
			ProductCart productCart = new ProductCart();
			productCart.setQuantity(quantity);
			productCart.setUnitPrice(prod.getUnitPrice());
			productCart.setCart(cart);
			productCart.setProductStock(prod);
			cart.setTotal(cart.getTotal() + (prod.getUnitPrice() * quantity));
			repoProductCart.save(productCart);
			return productCart;
		}
	}

	@Override
	public void deleteProduct(Long idCart, Long idProduct) throws NotFoundException {
		Cart cart = getCart(idCart);
		ProductCart productCart = cart.getProducts().stream().filter(p -> p.getId().equals(idProduct)).findFirst()
				.orElseThrow(() -> new NotFoundException("ProductCart", idProduct));
		cart.setTotal(cart.getTotal() - (productCart.getUnitPrice()) * productCart.getQuantity());
		cart.getProducts().remove(productCart);
		repoProductCart.delete(productCart);
		repoCart.save(cart);
	}

	@Override
	public List<ProductCartDto> getProducts(Cart cart) {
		return cart.getProducts().stream().map(p -> mapProduct(p)).collect(Collectors.toList());
	}

	private ProductCartDto mapProduct(ProductCart prodCart) {
		ProductCartDto productCartDto = new ProductCartDto();
		productCartDto.setId(prodCart.getProductStock().getId());
		productCartDto.setQuantity(prodCart.getQuantity());
		productCartDto.setDescription(prodCart.getProductStock().getDescription());
		productCartDto.setUnitPrice(prodCart.getUnitPrice());
		return productCartDto;
	}

	@Override
	public void moveToReady(Cart cart) {
		cart.setStatus(Cart.Status.READY);
		repoCart.save(cart);
	}

	@Override
	public ResponseCartDto getCart(Cart cart) {
		ResponseCartDto response = new ResponseCartDto();
		response.setId(cart.getId());
		response.setTotal(cart.getTotal());
		response.setCreationDate(cart.getCreationDate());
		response.setProducts(cart.getProducts().stream().map(p -> p.getId()).collect(Collectors.toList()));
		response.setStatus(cart.getStatus().toString());
		return response;
	}

	@Override
	public void payment(Long idCart) {
		boolean canProcess = false;

		Cart cart = getCart(idCart);

		if (cart.isReady()) {

			synchronized (cart) {
				canProcess = verifyStock(cart);
			}

			if (canProcess) {
				logger.info("The cart Id: " + cart.getId() + " was processed");
				cart.setStatus(Cart.Status.PROCESSED);
			} else {
				logger.error("There is no enough stock for cart Id:" + cart.getId());
				cart.setStatus(Cart.Status.FAILED);
			}

			repoCart.save(cart);
		} else {
			logger.error("The cart Id:" + cart.getId()+ " is not Ready for payment");
		}
	}

	private synchronized boolean verifyStock(Cart cart) {
		if (cart.getProducts().stream().allMatch(p -> p.getQuantity() <= p.getProductStock().getStock())) {
			cart.getProducts().stream().forEach(p -> updateStock(p.getProductStock(), p.getQuantity()));
			return true;
		} else {
			return false;
		}
	}

	private synchronized void updateStock(ProductStock prodStock, Long quantity) {
		prodStock.setStock(prodStock.getStock() - quantity);
		repoProductoStock.save(prodStock);
	}

}
