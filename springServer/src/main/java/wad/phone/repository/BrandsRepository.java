package wad.phone.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import wad.phone.entity.Brand;


public interface BrandsRepository extends MongoRepository<Brand, String> {
}
