package test.garbarino.martinKaynar.dtos;

public class ProductCartDto {

	private Long id;
	
	private String description;
	
	private Double unitPrice;
	
	private Long quantity;

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
}
