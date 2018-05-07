package main.java.com.prodapt.quiz.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import main.java.com.prodapt.quiz.beans.Answer;
import main.java.com.prodapt.quiz.common.CustomQuizException;
import main.java.com.prodapt.quiz.controller.ResultController;
import main.java.com.prodapt.quiz.controller.TokenController;
import main.java.com.prodapt.quiz.controller.TopicController;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResultService {
	
	
	
	@RequestMapping(method = RequestMethod.POST,value="/endQuizForUser")

	public Object getMarks(@RequestBody String answerData,@RequestHeader(value="token") String token) throws IllegalArgumentException, CustomQuizException, JsonGenerationException, JsonMappingException, IOException, JSONException{
		
		TokenController.verifyToken(token);
		Answer answer=new TokenService().getPojo(answerData, Answer.class);
		ObjectMapper objectMapper=new ObjectMapper();
		return objectMapper.writeValueAsString(new ResultController().getResultFromFile(answer));
		
		
	}
	
	@RequestMapping(method = RequestMethod.POST,value="/submitUserResponce")

	public Object getRestForSingleQuestion(@RequestBody String answerData,@RequestHeader(value="token") String token) throws IllegalArgumentException, CustomQuizException, JsonGenerationException, JsonMappingException, IOException, JSONException{
		
		TokenController.verifyToken(token);
		Answer answer=new TokenService().getPojo(answerData, Answer.class);
		ObjectMapper objectMapper=new ObjectMapper();
		return objectMapper.writeValueAsString(new ResultController().getResultForSingleQuestion(answer));
		
		
	}
	
	public static void main(String[] args) {
		
		try {
		JSONParser parser = new JSONParser();
		InputStream inputStream=TopicController.class.getResourceAsStream("/local.answer.json");
    	Reader reader=new InputStreamReader(inputStream);
        Object obj = null;
	
			obj = parser.parse(reader);
		
        
     ObjectMapper objectMapper=new ObjectMapper();
     Answer answer=objectMapper.readValue(obj.toString(), Answer.class);
     
		
		/*Answer answer=new TokenService().getPojo(answerData, Answer.class);
		ObjectMapper objectMapper=new ObjectMapper();*/
		
		
		System.out.println(new ResultController().getResultFromFile(answer));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
