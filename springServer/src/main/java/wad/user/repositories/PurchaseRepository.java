package wad.user.repositories;

import org.springframework.data.repository.CrudRepository;
import wad.user.entities.Purchase;

import java.util.List;

public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {

    List<Purchase> findByUserId(Integer userID);

}
