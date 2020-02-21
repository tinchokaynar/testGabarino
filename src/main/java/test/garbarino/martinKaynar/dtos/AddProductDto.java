package test.garbarino.martinKaynar.dtos;

import javax.validation.constraints.NotBlank;

public class AddProductDto {
	
	@NotBlank(message = "Product Id is mandatory")
	private Long id;
	
	@NotBlank(message = "Quantity is mandatory")
	private Long quantity;

	public Long getId() {
		return id;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
}
