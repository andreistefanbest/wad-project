package wad.product.entities;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Purchase2Repository extends CrudRepository<Purchase2, Integer> {
    List<Purchase2> findByUserId(Integer userID);
}
