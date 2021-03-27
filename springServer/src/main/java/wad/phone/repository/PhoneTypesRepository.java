package wad.phone.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import wad.phone.entity.PhoneType;

@Repository
public interface PhoneTypesRepository extends MongoRepository<PhoneType, Integer> {
}
