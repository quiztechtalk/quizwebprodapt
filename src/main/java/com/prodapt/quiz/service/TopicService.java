package main.java.com.prodapt.quiz.service;

import java.io.UnsupportedEncodingException;

import main.java.com.prodapt.quiz.common.CustomQuizException;
import main.java.com.prodapt.quiz.controller.TokenController;
import main.java.com.prodapt.quiz.controller.TopicController;

import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

/**
 * 
 * @author gandhi.d
 *
 */
@RestController
public class TopicService {
	
	
	
	
	@RequestMapping("/getTopics")
	public Object createToken(@RequestHeader(value="token") String token){
		try{
		TokenController.verifyToken(token);
		return new TopicController().getTopicsFromFile();
		}
		catch(TokenExpiredException ex){
			return new CustomQuizException(ex);
		}
		catch(Exception e){
			return new CustomQuizException(e);
		}
		
		
	}

}
