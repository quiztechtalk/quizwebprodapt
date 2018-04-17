package main.java.com.prodapt.quiz.service;


import java.util.List;

import main.java.com.prodapt.quiz.beans.Mark;
import main.java.com.prodapt.quiz.controller.MarkController;

import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author gandhi.d
 *
 */
@RestController
public class MarkService {
	
	
	
	
	
	@RequestMapping("/getMarks")
	public List<Mark> getMarks() throws JSONException{
		
		List<Mark> responseBean=new MarkController().getMarksFromFile();
		return responseBean;
		
	}
	


}
