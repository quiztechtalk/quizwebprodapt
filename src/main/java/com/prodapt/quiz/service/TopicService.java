package main.java.com.prodapt.quiz.service;

import java.util.List;

import main.java.com.prodapt.quiz.beans.ResponseBean;
import main.java.com.prodapt.quiz.beans.Topic;
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
	public List<Topic> createToken() throws JSONException{
		return new TopicController().getTopicsFromFile();
	}
	

}
