package test.garbarino.martinKaynar.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import test.garbarino.martinKaynar.models.Cart;

@Repository
public interface RepoCart extends CrudRepository<Cart, Long>{

}
