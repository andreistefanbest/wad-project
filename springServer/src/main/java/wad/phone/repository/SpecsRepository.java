package wad.phone.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import wad.phone.entity.Specs;


public interface SpecsRepository extends MongoRepository<Specs, String> {
}
