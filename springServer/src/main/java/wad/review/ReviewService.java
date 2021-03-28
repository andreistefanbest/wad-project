package wad.review;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wad.review.dto.ReviewDTO;
import wad.review.entity.Review;
import wad.review.repository.ReviewRepository;
import wad.user.entity.User;
import wad.user.repositories.user.UserRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ReviewDTO> getReviews(String phoneId) {
        return reviewRepository.findByPhoneId(phoneId)
                .stream()
                .map(this::dtoFromReview)
                .collect(toList());
    }

    public void addReview(Review r) {
        reviewRepository.save(r);
    }

    private ReviewDTO dtoFromReview(Review r) {
        ReviewDTO dto = new ReviewDTO();
        BeanUtils.copyProperties(r, dto);

        userRepository.findById(r.getUserId())
                .map(User::getFullName)
                .ifPresent(dto::setUserName);

        return dto;
    }
}
