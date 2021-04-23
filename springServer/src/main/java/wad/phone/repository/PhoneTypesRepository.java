package wad.phone.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import wad.phone.entity.PhoneType;

@Repository
public interface PhoneTypesRepository extends ReactiveMongoRepository<PhoneType, String> {
}
