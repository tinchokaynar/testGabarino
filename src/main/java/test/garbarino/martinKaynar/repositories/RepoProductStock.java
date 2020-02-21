package test.garbarino.martinKaynar.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import test.garbarino.martinKaynar.models.ProductStock;

@Repository
public interface RepoProductStock extends CrudRepository<ProductStock, Long>{

}
