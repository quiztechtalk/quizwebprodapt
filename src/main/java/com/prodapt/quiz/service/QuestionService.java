package main.java.com.prodapt.quiz.service;


import javax.websocket.server.PathParam;

import main.java.com.prodapt.quiz.beans.ResponseBean;
import main.java.com.prodapt.quiz.controller.QuestionController;

import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author gandhi.d
 *
 */

@RestController("/question")
public class QuestionService {
	
	
	@RequestMapping("/getQuestions/{topic}/{mark}")
	public ResponseBean getMarks(@PathParam("topic") String topic,@PathParam("mark") Integer mark) throws JSONException{
		
		ResponseBean responseBean=new QuestionController().getQuizFromFile(topic,mark);
		return responseBean;
		
	}


}
