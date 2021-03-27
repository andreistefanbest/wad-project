package wad.purchase.repository;

import org.springframework.data.repository.CrudRepository;
import wad.purchase.entity.Purchase;

import java.util.List;

public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {

    List<Purchase> findByUserId(Integer userID);

}
