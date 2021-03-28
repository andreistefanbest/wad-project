package wad.purchase.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import wad.purchase.entity.Purchase;

import java.util.List;

public interface PurchaseRepository extends MongoRepository<Purchase, String> {

    List<Purchase> findByUserId(String userID);

}
