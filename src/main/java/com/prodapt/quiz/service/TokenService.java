package main.java.com.prodapt.quiz.service;


import java.io.IOException;

import javax.websocket.server.PathParam;

import main.java.com.prodapt.quiz.beans.ResponseBean;
import main.java.com.prodapt.quiz.beans.Token;
import main.java.com.prodapt.quiz.controller.TokenController;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**
 * 
 * @author gandhi.d
 *
 */
@RestController("/token")
public class TokenService {
	
	private static Logger log=Logger.getLogger(TokenService.class);
	
	
	
	@RequestMapping(method = RequestMethod.POST,value="/createToken")
	public ResponseBean createToken() throws JSONException{
		ResponseBean responseBean=new TokenController().createToken();
		return responseBean;
	}
	
	@RequestMapping(method = RequestMethod.POST,value="/createToken/{user}/{time}")
	public ResponseBean createTokenBasedOnTime(@PathParam("user") String user,@PathParam("time") String time) throws NumberFormatException, JSONException{
		ResponseBean responseBean=new TokenController().createTokenBasedOnSessionTime(user,Integer.parseInt(time));
		return responseBean;
	}
	
	
	@RequestMapping(method = RequestMethod.POST,value="/verifyToken")
	public ResponseBean verifyToken(String token) throws JSONException{
		Token token2=getPojo(token, Token.class);
		ResponseBean responseBean=new TokenController().verifyToken(token2.getToken());
		return responseBean;
	}
	
	@RequestMapping(method = RequestMethod.POST,value="/verifyToken/{user}")
	public ResponseBean verifyToken(@PathParam("user") String user, String token) throws JSONException{
		Token token2=getPojo(token, Token.class);
		ResponseBean responseBean=new TokenController().verifyUserToken(user,token2.getToken());
		return responseBean;
	}
	
	
	
	
	public <E, T> E getPojo(String jsonData, Class<E> clazz) {
		E obj = null;
		try {
			obj = clazz.newInstance();
			ObjectMapper mapper = new ObjectMapper();
			mapper.readValue(jsonData, obj.getClass());
			
			Class cls = obj.getClass();
			obj = (E) mapper.readValue(jsonData, obj.getClass());;
		} catch (IllegalAccessException | InstantiationException | NullPointerException | IOException ex) {
			log.info("Exception while parsing Input Json : " + ex);
		}
		return obj;
	}

	
	
	


}
