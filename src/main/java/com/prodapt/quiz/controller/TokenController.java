package main.java.com.prodapt.quiz.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import main.java.com.prodapt.quiz.beans.ResponseBean;
import main.java.com.prodapt.quiz.beans.User;
import main.java.com.prodapt.quiz.common.CustomQuizException;
import main.java.com.prodapt.quiz.common.JWTProperties;
import main.java.com.prodapt.quiz.common.ResponseMessage;

import org.json.JSONException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.TokenExpiredException;

/**
 * 
 * @author gandhi.d
 *
 */

public class TokenController {
	
	public String createToken(User user) throws JSONException{
		
		//ResponseBean responceBean=new ResponseBean();
		
		String token = "";
		try {
			
			Date expireTime = new Date(System.currentTimeMillis() + 40000);
			Algorithm algorithm = Algorithm.HMAC256(JWTProperties.getInstance().getProperty(user.getPassword()));
			token = JWT.create().withExpiresAt(expireTime)
					.withIssuer(JWTProperties.getInstance().getProperty(user.getName()))
					.sign(algorithm);
		} 
		
		catch (UnsupportedEncodingException exception) {
			// UTF-8 encoding not supported
			//responceBean=ResponseData.invalidToken();
			exception.printStackTrace();
		}
		
		catch (JWTCreationException exception) {
			// Invalid Signing configuration / Couldn't convert Claims.
			//responceBean=ResponseData.invalidToken();
			exception.printStackTrace();
		}
		
		
		
		return token;
		
	}
	
	
public String createTokenBasedOnSessionTime(final String user,final Integer sessionTime) throws JSONException{
		
		//ResponseBean responceBean=new ResponseBean();
		
		String token = "";
		try {
			
			Date expireTime = new Date(System.currentTimeMillis() + Integer.parseInt(JWTProperties.getInstance().getProperty("time"))*sessionTime);
			Algorithm algorithm = Algorithm.HMAC256(JWTProperties.getInstance().getProperty("secretKey"));
			
			token = JWT.create().withExpiresAt(expireTime)
					.withIssuer(user)
					.sign(algorithm);
			
			//responceBean=ResponseData.success(token);
		}
		catch (UnsupportedEncodingException exception) {
			// UTF-8 encoding not supported
			//responceBean=ResponseData.invalidToken();
		}
		
		catch (JWTCreationException exception) {
			// Invalid Signing configuration / Couldn't convert Claims.
			//responceBean=ResponseData.invalidToken();
		}
		
		return token;
		
	}
	
	
public static String verifyToken(String token) throws CustomQuizException{
		
		//ResponseBean responceBean=new ResponseBean();
		String status="";
		try {
			Algorithm algorithm = Algorithm.HMAC256(JWTProperties.getInstance().getProperty("secretKey"));
			JWTVerifier verifier = JWT.require(algorithm)
					.withIssuer(JWTProperties.getInstance().getProperty("issuer"))
					.build(); // Reusable verifier instance
			//DecodedJWT jwt = verifier.verify(token);
			
			verifier.verify(token);
			status=ResponseMessage.SUCCESS;
			//responceBean=ResponseData.success(ResponseMessage.SUCCESS);
		} 
		catch (Exception exception) {
		}
		
		
		
		
		return status;
	}

public String verifyUserToken(final String user,final String token) throws JSONException{
	
	ResponseBean responceBean=new ResponseBean();
	String status="";
	try {
		Algorithm algorithm = Algorithm.HMAC256(JWTProperties.getInstance().getProperty("secretKey"));
		JWTVerifier verifier = JWT.require(algorithm)
				.withIssuer(user)
				.build(); // Reusable verifier instance
		//DecodedJWT jwt = verifier.verify(token);
		
		verifier.verify(token);

		//responceBean=ResponseData.success(ResponseMessage.SUCCESS);
		status=ResponseMessage.SUCCESS;
	} 
	catch (TokenExpiredException exception) {
		status=ResponseMessage.TOKEN_EXPIRED;
		//responceBean=ResponseData.expireToken();
	}
	
	catch (UnsupportedEncodingException exception) {
		// UTF-8 encoding not supported
		//responceBean=ResponseData.invalidToken();
		status=ResponseMessage.INVALID_TOKEN;
	}
	
	catch (JWTCreationException exception) {
		// Invalid Signing configuration / Couldn't convert Claims.
		//responceBean=ResponseData.invalidToken();
		status=ResponseMessage.INVALID_TOKEN;
	}
	return status;
}



}
