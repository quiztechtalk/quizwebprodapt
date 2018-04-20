package main.java.com.prodapt.quiz.common;

import java.io.UnsupportedEncodingException;

import org.json.JSONException;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

/**
 * 
 * @author gandhi.d
 *
 */
public class CustomQuizException extends Exception {

	
	private static final long serialVersionUID = 54461L;

	public CustomQuizException() {

	}

	public CustomQuizException(String message) {
		super(message);
	}
	
	public CustomQuizException(TokenExpiredException exception) {
		super(ResponseMessage.TOKEN_EXPIRED);
	}
	
	public CustomQuizException(JWTCreationException exception) {
		super(ResponseMessage.INVALID_TOKEN);
	}
	
	public CustomQuizException(Exception exception) {
		super(ResponseMessage.INVALID_TOKEN);
	}
	
	public CustomQuizException(JSONException exception) {
		super(ResponseMessage.INVALID_TOKEN);
	}
	
	public CustomQuizException(IllegalArgumentException exception) {
		super(ResponseMessage.INVALID_TOKEN);
	}
	
	public CustomQuizException(UnsupportedEncodingException exception) {
		super(ResponseMessage.INVALID_TOKEN);
	}
	
	public CustomQuizException(RuntimeException exception) {
		super(ResponseMessage.INVALID_TOKEN);
	}
	
	

}
