package wad.phone.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import wad.phone.entity.Brand;

@Repository
public interface BrandsRepository extends MongoRepository<Brand, Integer> {
}
