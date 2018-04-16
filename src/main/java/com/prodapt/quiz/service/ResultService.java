package main.java.com.prodapt.quiz.service;

import main.java.com.prodapt.quiz.beans.Answer;
import main.java.com.prodapt.quiz.beans.ResponseBean;
import main.java.com.prodapt.quiz.controller.ResultController;

import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController("/result")
public class ResultService {
	
	
	
	
	@RequestMapping(method = RequestMethod.POST,value="/getResult")
	public ResponseBean getMarks(String answerData) throws JSONException{
		
		Answer answer=new TokenService().getPojo(answerData, Answer.class);
		ResponseBean responseBean=new ResultController().getResult(answer);
		return responseBean;
		
	}


}
