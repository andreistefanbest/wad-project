package wad.phone.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import wad.phone.entity.Specs;

@Repository
public interface SpecsRepository extends MongoRepository<Specs, Integer> {
}
