package wad.review.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import wad.review.entity.Review;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByPhoneId(String phoneId);
}
