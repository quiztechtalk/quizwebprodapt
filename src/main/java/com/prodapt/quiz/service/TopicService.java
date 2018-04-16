package main.java.com.prodapt.quiz.service;

import main.java.com.prodapt.quiz.beans.ResponseBean;
import main.java.com.prodapt.quiz.controller.TopicController;

import org.json.JSONException;
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
	public ResponseBean createToken() throws JSONException{
		ResponseBean responseBean=new TopicController().getTopicsFromFile();
		return responseBean;
	}
	

}
