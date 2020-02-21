package test.garbarino.martinKaynar.dtos;

import java.util.ArrayList;
import java.util.List;

public class ResponseGetProductsDto {

	List<ProductCartDto> productList = new ArrayList<ProductCartDto>();

	public List<ProductCartDto> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductCartDto> productList) {
		this.productList = productList;
	}

	
}
