package com.ahackannection.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ahackannection.entity.TrainingReview;
import com.ahackannection.exception.ValidationException;

import java.sql.Date;

@Service
@Component
public interface ReviewService {
	
	TrainingReview saveReview(TrainingReview Review) throws ValidationException;
    
	void removeReview(Long reviewId);
	
	TrainingReview findReviewById(Long id); 
	
	List<TrainingReview> findReviewsByVisitorId(Long reviewId);
	
	List<TrainingReview> findReviewsByTrainingId(Long reviewId);
	
	List<TrainingReview> findReviewsWithinTimeInterval(Date begin, Date end);
	
	List<TrainingReview> findReviewsWithinRateRange(byte lowerBound, byte upperBound);
	
	List<TrainingReview> findAllReviews();

}
