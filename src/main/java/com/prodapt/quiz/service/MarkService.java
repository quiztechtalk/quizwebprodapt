package main.java.com.prodapt.quiz.service;


import java.io.UnsupportedEncodingException;
import java.util.List;

import main.java.com.prodapt.quiz.beans.Mark;
import main.java.com.prodapt.quiz.common.CustomQuizException;
import main.java.com.prodapt.quiz.controller.MarkController;
import main.java.com.prodapt.quiz.controller.TokenController;

import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestHeader;
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
	public Object getMarks(@RequestHeader(value="token") String token) throws IllegalArgumentException, UnsupportedEncodingException, CustomQuizException, JSONException{
		
		TokenController.verifyToken(token);
		List<Mark> responseBean=new MarkController().getMarksFromFile();
		return responseBean;
		
		
	}
	


}
