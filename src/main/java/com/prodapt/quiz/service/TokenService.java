package main.java.com.prodapt.quiz.service;


import java.io.IOException;

import main.java.com.prodapt.quiz.beans.Token;
import main.java.com.prodapt.quiz.beans.User;
import main.java.com.prodapt.quiz.common.CustomQuizException;
import main.java.com.prodapt.quiz.controller.TokenController;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 * 
 * @author gandhi.d
 *
 */
@RestController
public class TokenService {
	
	private static Logger log=Logger.getLogger(TokenService.class);
	
	
	
	@RequestMapping(method = RequestMethod.POST,value="/createToken")
	public String createToken(@RequestBody String user) throws JSONException, JsonGenerationException, JsonMappingException, IOException{
		
		User userObject=getPojo(user, User.class);
		ObjectMapper objectMapper=new ObjectMapper();
		return objectMapper.writeValueAsString(new TokenController().createToken(userObject));
		
	}
	
	@RequestMapping(method = RequestMethod.POST,value="/createToken/{user}/{time}")
	public String createTokenBasedOnTime(@RequestParam(value="user", defaultValue="user") String user,@RequestParam(value="time", defaultValue="10") String time,@RequestHeader(value="token") String token) throws CustomQuizException, JSONException, JsonGenerationException, JsonMappingException, NumberFormatException, IOException{
		TokenController.verifyToken(token);
		ObjectMapper objectMapper=new ObjectMapper();
		return objectMapper.writeValueAsString(new TokenController().createTokenBasedOnSessionTime(user,Integer.parseInt(time)));
		
	}
	
	
	@RequestMapping(method = RequestMethod.POST,value="/verifyToken")
	public String verifyToken(@RequestBody String token) throws JsonGenerationException, JsonMappingException, IOException, CustomQuizException{
		Token token2=getPojo(token, Token.class);
		ObjectMapper objectMapper=new ObjectMapper();
		return objectMapper.writeValueAsString(TokenController.verifyToken(token2.getToken()));
		
	}
	
	@RequestMapping(method = RequestMethod.PUT,value="/verifyToken/{user}")
	public String verifyToken(@RequestParam(value="user", defaultValue="user") String user, @RequestBody String userToken,@RequestHeader(value="token") String token) throws CustomQuizException, JSONException, JsonGenerationException, JsonMappingException, IOException{
		TokenController.verifyToken(token);
		Token token2=getPojo(userToken, Token.class);
		ObjectMapper objectMapper=new ObjectMapper();
		return objectMapper.writeValueAsString(new TokenController().verifyUserToken(user,token2.getToken()));
		
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
