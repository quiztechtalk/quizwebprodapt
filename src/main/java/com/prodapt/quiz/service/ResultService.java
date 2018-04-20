package main.java.com.prodapt.quiz.service;

import main.java.com.prodapt.quiz.beans.Answer;
import main.java.com.prodapt.quiz.common.CustomQuizException;
import main.java.com.prodapt.quiz.controller.ResultController;
import main.java.com.prodapt.quiz.controller.TokenController;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.TokenExpiredException;

@RestController
public class ResultService {
	
	
	
	
	@RequestMapping(method = RequestMethod.POST,value="/getResult")

	public Object getMarks(@RequestBody String answerData,@RequestHeader(value="token") String token){
		try{
		TokenController.verifyToken(token);
		Answer answer=new TokenService().getPojo(answerData, Answer.class);
		ObjectMapper objectMapper=new ObjectMapper();
		return objectMapper.writeValueAsString(new ResultController().getResultFromFile(answer));
		}
		catch(TokenExpiredException ex){
			return new CustomQuizException(ex);
		}
		catch(Exception e){
			return new CustomQuizException(e);
		}
		
	}


}
