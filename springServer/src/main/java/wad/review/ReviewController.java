package wad.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wad.review.dto.ReviewDTO;
import wad.review.entity.Review;

import java.util.List;

@RestController
@RequestMapping("/review/")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("review")
    public List<ReviewDTO> getReviews(@RequestParam String phoneId) throws Exception {
        return reviewService.getReviews(phoneId);
    }

    @PostMapping("review")
    public void addReview(@RequestBody Review review) throws Exception {
        reviewService.addReview(review);
    }

}
