package main.java.com.prodapt.quiz.service;

import java.io.UnsupportedEncodingException;

import main.java.com.prodapt.quiz.common.CustomQuizException;
import main.java.com.prodapt.quiz.controller.TokenController;
import main.java.com.prodapt.quiz.controller.TopicController;

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
public class TopicService {
	
	
	
	
	@RequestMapping("/getTopics")
	public Object createToken(@RequestHeader(value="token") String token) throws IllegalArgumentException, UnsupportedEncodingException, CustomQuizException, JSONException{
		
		TokenController.verifyToken(token);
		return new TopicController().getTopicsFromFile();
		
		
		
	}

}
