package test.garbarino.martinKaynar.dtos;

public class ResponseAddProductDto {
	
	private Long idProductCart;
	
	private Long idProductStock;
	
	private Long quantity;

	public Long getIdProductCart() {
		return idProductCart;
	}

	public Long getIdProductStock() {
		return idProductStock;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setIdProductCart(Long idProductCart) {
		this.idProductCart = idProductCart;
	}

	public void setIdProductStock(Long idProductStock) {
		this.idProductStock = idProductStock;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}
