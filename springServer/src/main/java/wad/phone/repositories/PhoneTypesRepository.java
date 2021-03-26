package wad.phone.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import wad.phone.entities.PhoneTypes;

@Repository
public interface PhoneTypesRepository extends MongoRepository<PhoneTypes, Integer> {
}
