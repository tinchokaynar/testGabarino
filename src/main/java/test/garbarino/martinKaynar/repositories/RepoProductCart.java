package test.garbarino.martinKaynar.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import test.garbarino.martinKaynar.models.ProductCart;

@Repository
public interface RepoProductCart extends CrudRepository<ProductCart, Long>{

}
