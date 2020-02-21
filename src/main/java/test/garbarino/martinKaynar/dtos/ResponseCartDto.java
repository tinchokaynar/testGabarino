package test.garbarino.martinKaynar.dtos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ResponseCartDto {
	private Long id;
	private double total;
	private LocalDateTime creationDate;
	private List<Long> products = new ArrayList<Long>();
	private String status;
	
	public Long getId() {
		return id;
	}
	public double getTotal() {
		return total;
	}
	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	public List<Long> getProducts() {
		return products;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	public void setProducts(List<Long> products) {
		this.products = products;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
