package wad.phone.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import wad.phone.entities.Specs;

@Repository
public interface SpecsRepository extends MongoRepository<Specs, Integer> {
}
