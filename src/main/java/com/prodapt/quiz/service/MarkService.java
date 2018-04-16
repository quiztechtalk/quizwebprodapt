package main.java.com.prodapt.quiz.service;


import main.java.com.prodapt.quiz.beans.ResponseBean;
import main.java.com.prodapt.quiz.controller.MarkController;

import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author gandhi.d
 *
 */
@RestController("/mark")
public class MarkService {
	
	
	
	
	
	@RequestMapping("/getMarks")
	public ResponseBean getMarks() throws JSONException{
		
		ResponseBean responseBean=new MarkController().getMarksFromFile();
		return responseBean;
		
	}
	


}
