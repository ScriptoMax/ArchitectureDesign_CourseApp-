package com.ahackannection.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahackannection.entity.Contest;
import com.ahackannection.entity.TrainingReview;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.ReviewService;

import java.sql.Date;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/training_review")
@AllArgsConstructor
public class ReviewController {
	
	private final ReviewService reviewService;
	
	@PostMapping("/saveReview")
	@ResponseBody
	public TrainingReview saveReview(@RequestBody TrainingReview review) throws ValidationException {
		return reviewService.saveReview(review);
	}
	
	@GetMapping("/findReviewById")
	public TrainingReview findReviewById(@RequestParam Long id) {
		return reviewService.findReviewById(id);
	}
	
	@GetMapping("/findByVisitorId")
	public List<TrainingReview> findReviewsByVisitorId(@RequestParam Long visitorId) {
		return reviewService.findReviewsByVisitorId(visitorId);
	}
	
	@GetMapping("/findByTrainingId")
	public List<TrainingReview> findReviewsByTrainingId(@RequestParam Long trainingId) {
		return reviewService.findReviewsByTrainingId(trainingId);
	}
	
	@GetMapping("/findWithinTimeInterval")
	public List<TrainingReview> findReviewsWithinTimeInterval(@RequestParam Date begin, @RequestParam Date end) {
		return reviewService.findReviewsWithinTimeInterval(begin, end);
	}
	
	@GetMapping("/findWithinRateRange")
	public List<TrainingReview> findReviewsWithinRateRange(@RequestParam byte lowerBound, @RequestParam byte upperBound) {
		return reviewService.findReviewsWithinRateRange(lowerBound, upperBound);
	}	
	
	@GetMapping("/findAll")
	public List<TrainingReview> findAllReviews() {
		return reviewService.findAllReviews();
	}
	
	@DeleteMapping("/removeReview/{reviewId}")
	public ResponseEntity<Void> removeReview(@PathVariable Long reviewId) {
		reviewService.removeReview(reviewId);
		return ResponseEntity.ok().build();
	}

}
