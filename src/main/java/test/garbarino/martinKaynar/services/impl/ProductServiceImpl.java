package test.garbarino.martinKaynar.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.garbarino.martinKaynar.exceptions.CantNullException;
import test.garbarino.martinKaynar.exceptions.NotFoundException;
import test.garbarino.martinKaynar.models.ProductStock;
import test.garbarino.martinKaynar.repositories.RepoProductStock;
import test.garbarino.martinKaynar.services.ProductService;

@Service
public class ProductServiceImpl  implements ProductService {

	@Autowired
	private RepoProductStock repoProductStock;

	@Override
	public ProductStock getProduct(Long id) throws NotFoundException,CantNullException {
		if(id!= null) {
			return repoProductStock.findById(id).orElseThrow(() -> new NotFoundException("Product",id));
		} else {
			throw new CantNullException("Id Product");
		}
	}


}
