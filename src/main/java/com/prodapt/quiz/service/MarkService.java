package main.java.com.prodapt.quiz.service;


import java.util.List;

import main.java.com.prodapt.quiz.beans.Mark;
import main.java.com.prodapt.quiz.common.CustomQuizException;
import main.java.com.prodapt.quiz.controller.MarkController;
import main.java.com.prodapt.quiz.controller.TokenController;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.exceptions.TokenExpiredException;

/**
 * 
 * @author gandhi.d
 *
 */
@RestController
public class MarkService {
	
	
	
	
	
	@RequestMapping("/getMarks")
	public Object getMarks(@RequestHeader(value="token") String token){
		
		try{
		TokenController.verifyToken(token);
		List<Mark> responseBean=new MarkController().getMarksFromFile();
		return responseBean;
		}
		catch(TokenExpiredException ex){
			return new CustomQuizException(ex);
		}
		catch(Exception e){
			return new CustomQuizException(e);
		}
		
	}
	


}
