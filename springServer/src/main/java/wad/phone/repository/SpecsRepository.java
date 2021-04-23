package wad.phone.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import wad.phone.entity.Specs;

@Repository
public interface SpecsRepository extends ReactiveMongoRepository<Specs, String> {
}
