package com.ahackannection.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;;

@Controller
@RequestMapping({"/"})
public class IndexController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "signup";
	}
	
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String reg_success() {
		return "reg_success";
	}
	
	@RequestMapping(value = "/addcontest", method = RequestMethod.GET)
	public String add_contest() {
		return "addcontest";
	}
	
	@RequestMapping(value = "/addsubmission", method = RequestMethod.GET)
	public String add_submission() {
		return "addsubmission";
	}
	
	@RequestMapping(value = "/submission_summary", method = RequestMethod.GET)
	public String summarize_submission() {
		return "submission_summary";
	}
	
	@RequestMapping(value = "/addtraining", method = RequestMethod.GET)
	public String add_training() {
		return "addtraining";
	}
	
	@RequestMapping(value = "/addorder", method = RequestMethod.GET)
	public String enter_order() {
		return "addorder";
	}
	
	@RequestMapping(value = "/addtransact", method = RequestMethod.GET)
	public String create_transact() {
		return "addtransact";
	}
	
	@RequestMapping(value = "/transact_summary", method = RequestMethod.GET)
	public String summarize_transact() {
		return "transact_summary";
	}

}
