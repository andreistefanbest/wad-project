package wad.phone.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import wad.phone.entities.Phones;

@Repository
public interface PhonesRepository extends MongoRepository<Phones, Integer> {
}
