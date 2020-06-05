package com.ahackannection.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.TrainingReview;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.repository.ReviewRepository;
import com.ahackannection.service.ReviewService;
import com.google.common.collect.Lists;

import java.sql.Date;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
	
	@Autowired
	private final ReviewRepository reviewRepository;
	
	private void validateReview(TrainingReview review) throws ValidationException {
		if(review == null) {
			throw new ValidationException("Object created is null");
		}		
	} 	
	
	public TrainingReview saveReview(TrainingReview review) throws ValidationException {
		validateReview(review);
		TrainingReview validReview = reviewRepository.save(review);
		return validReview;	
	}
	
	public void removeReview(Long reviewId) {
		reviewRepository.deleteById(reviewId);
	}
	
	public List<TrainingReview> findReviewsByVisitorId(Long visitorId) {		
		List<TrainingReview> reviewList = Lists.newArrayList(reviewRepository.findAll());
		
		return reviewList.stream()
				.filter(x -> x.getVisitor().getId() == visitorId)
				.collect(Collectors.toList());
	}
	
	public List<TrainingReview> findReviewsByTrainingId(Long trainingId) {		
		List<TrainingReview> reviewList = Lists.newArrayList(reviewRepository.findAll());
		
		return reviewList.stream()
				.filter(x -> x.getTraining().getId() == trainingId)
				.collect(Collectors.toList());
	}
	
	public List<TrainingReview> findReviewsWithinRateRange(byte lowerBound, byte upperBound) {		
		List<TrainingReview> reviewList = Lists.newArrayList(reviewRepository.findAll());
		
		return reviewList.stream()
				.filter(x -> x.getRate() >= lowerBound && x.getRate() <= upperBound)
				.collect(Collectors.toList());
	}
	
	public List<TrainingReview> findReviewsWithinTimeInterval(Date begin, Date end) {		
		List<TrainingReview> reviewList = Lists.newArrayList(reviewRepository.findAll());
		
		return reviewList.stream()
				.filter(x -> x.getPostedOn().after(begin) && x.getPostedOn().before(end))
				.collect(Collectors.toList());
	}
	
	public List<TrainingReview> findAllReviews() {
		
		List<TrainingReview> reviewList = Lists.newArrayList(reviewRepository.findAll());
		
		return reviewList.stream()				
				.collect(Collectors.toList());
	}


}
