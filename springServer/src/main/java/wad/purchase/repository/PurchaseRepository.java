package wad.purchase.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import reactor.core.publisher.Flux;
import wad.purchase.entity.Purchase;

public interface PurchaseRepository extends MongoRepository<Purchase, String> {

    Flux<Purchase> findByUserId(String userID);

}
