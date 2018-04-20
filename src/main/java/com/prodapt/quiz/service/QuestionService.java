package main.java.com.prodapt.quiz.service;


import main.java.com.prodapt.quiz.common.CustomQuizException;
import main.java.com.prodapt.quiz.controller.QuestionController;
import main.java.com.prodapt.quiz.controller.TokenController;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.TokenExpiredException;

/**
 * 
 * @author gandhi.d
 *
 */

@RestController
public class QuestionService {
	
	
	@RequestMapping("/getQuestions/{topic}/{mark}")
	public Object getMarks(@RequestParam(value="topic", defaultValue="java") String topic,@RequestParam(value="mark", defaultValue="10") Integer mark,@RequestHeader(value="token") String token) {
		try{
		TokenController.verifyToken(token);
		ObjectMapper objectMapper=new ObjectMapper();
		return objectMapper.writeValueAsString(new QuestionController().getQuizFromFile(topic,mark));
	}
	catch(TokenExpiredException ex){
		return new CustomQuizException(ex);
	}
	catch(Exception e){
		return new CustomQuizException(e);
	}
		
	}


}
