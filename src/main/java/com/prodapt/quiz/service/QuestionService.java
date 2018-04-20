package main.java.com.prodapt.quiz.service;


import java.io.IOException;

import main.java.com.prodapt.quiz.common.CustomQuizException;
import main.java.com.prodapt.quiz.controller.QuestionController;
import main.java.com.prodapt.quiz.controller.TokenController;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author gandhi.d
 *
 */

@RestController
public class QuestionService {
	
	
	@RequestMapping("/getQuestions/{topic}/{mark}")
	public Object getMarks(@RequestParam(value="topic", defaultValue="java") String topic,@RequestParam(value="mark", defaultValue="10") Integer mark,@RequestHeader(value="token") String token) throws IllegalArgumentException, CustomQuizException, JsonGenerationException, JsonMappingException, IOException, JSONException {
		TokenController.verifyToken(token);
		ObjectMapper objectMapper=new ObjectMapper();
		return objectMapper.writeValueAsString(new QuestionController().getQuizFromFile(topic,mark));
	
		
	}


}
