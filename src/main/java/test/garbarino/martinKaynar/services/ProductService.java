package test.garbarino.martinKaynar.services;

import test.garbarino.martinKaynar.exceptions.CantNullException;
import test.garbarino.martinKaynar.exceptions.NotFoundException;
import test.garbarino.martinKaynar.models.ProductStock;

public interface ProductService {
	
	public ProductStock getProduct(Long id) throws NotFoundException,CantNullException;

}
