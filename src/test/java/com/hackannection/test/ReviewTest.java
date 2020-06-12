package com.hackannection.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.ahackannection.HackannectionApplication;
import com.ahackannection.entity.Training;
import com.ahackannection.entity.TrainingReview;
import com.ahackannection.exception.ValidationException;
import com.ahackannection.service.ReviewService;
import com.ahackannection.service.TrainingService;
import com.ahackannection.service.VisitorService;

import lombok.NoArgsConstructor;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = HackannectionApplication.class)
@SpringBootTest
@Transactional
@NoArgsConstructor
class ReviewTest {

	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private VisitorService visitorService;
	
	@Autowired
	private TrainingService trainingService;
	
	@Test
	void testSaveReview() {
		TrainingReview newReview = new TrainingReview();
		TrainingReview newReviewRet = new TrainingReview(); 
		newReview.setTraining(trainingService.findTrainingById(45L));
		newReview.setVisitor(visitorService.findVisitorById(52L));
		newReview.setRate((byte) 8);
		newReview.setComment("Useful to fall asleep rather quickly");
		
		try {
			newReviewRet = reviewService.saveReview(newReview);
			assertThat(newReviewRet).isNotNull();	
		} catch (ValidationException e) {			
			e.printStackTrace();
		}
		
	}
	
	@Test
	void testFindReviewById() {		
		TrainingReview review = reviewService.findReviewById(1L);
		assertThat(review).isNotNull();
		assertEquals(review.getVisitor().getLogin(), "lexus");		
	}
	
	@Test
	void testFindReviewsByVisitorId() {
		List<TrainingReview> reviews = reviewService.findReviewsByVisitorId(45L);
		TrainingReview review = reviewService.findReviewById(1L);
		assertThat(reviews).isNotNull().isNotEmpty();	
		assertThat(reviews.contains(review));
	}
	
	@Test
	void testFindReviewsByTrainingId() {
		List<TrainingReview> reviews = reviewService.findReviewsByTrainingId(52L);
		TrainingReview review = reviewService.findReviewById(1L);
		assertThat(reviews).isNotNull().isNotEmpty();	
		assertThat(reviews.contains(review));
	}	
	
	@Test
	void testFindReviewsWithinRateRange() {
		List<TrainingReview> reviews = reviewService.findReviewsWithinRateRange((byte) 5, (byte) 10);
		TrainingReview review = reviewService.findReviewById(1L);
		assertThat(reviews).isNotNull().isNotEmpty();
		assertThat(reviews.contains(review));
	}
	
	@Test
	void testFindAllReviews() {
		List<TrainingReview> reviews = reviewService.findAllReviews();
		assertThat(reviews).isNotNull().isNotEmpty();		
	}
	
	@Test
	void testRemoveReview() {	
		assertThrows(NoSuchElementException.class, 
		() -> {
			reviewService.removeReview(1L);
			TrainingReview review;
			review = reviewService.findReviewById(1L);			
		});
	}

}
