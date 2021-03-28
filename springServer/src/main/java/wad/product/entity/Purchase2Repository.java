package wad.product.entity;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Purchase2Repository extends MongoRepository<Purchase2, String> {
    List<Purchase2> findByUserId(String userID);
}
