package wad.review;

import wad.review.dto.ReviewDTO;
import wad.review.entity.Review;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> getReviews(Integer phoneId) throws Exception;
    void addReview(Review r) throws Exception;

}
