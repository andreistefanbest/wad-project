package wad.phone.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import wad.phone.entities.Brands;

@Repository
public interface BrandsRepository extends MongoRepository<Brands, Integer> {
}
