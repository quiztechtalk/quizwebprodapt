package main.java.com.prodapt.quiz.service;


import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import main.java.com.prodapt.quiz.beans.Quiz;
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
	
	
	@RequestMapping("/startQuizForUser/{topic}")
	public Object getMarks(@RequestParam(value="topic", defaultValue="java") String topic,@RequestHeader(value="token") String token) throws IllegalArgumentException, CustomQuizException, JsonGenerationException, JsonMappingException, IOException, JSONException {
		TokenController.verifyToken(token);
		ObjectMapper objectMapper=new ObjectMapper();
		setQuiz=new QuestionController().getQuizFromFile(topic);
		Quiz quiz=setQuiz.iterator().next();
		quizId=Integer.toString(ThreadLocalRandom.current().nextInt(100,1000));
		quiz.setQuizId(quizId);
		setQuiz.remove(quiz);
		return objectMapper.writeValueAsString(quiz);
	}
	
	
	@RequestMapping("/getNextQuestion/{quizId}")
	public Object getNextQuestion(@RequestParam(value="quizId") String userQuizId,@RequestHeader(value="token") String token) throws IllegalArgumentException, CustomQuizException, JsonGenerationException, JsonMappingException, IOException, JSONException {
		TokenController.verifyToken(token);
		ObjectMapper objectMapper=new ObjectMapper();
		if(quizId.equals(userQuizId)){
		Quiz quiz=setQuiz.iterator().next();
		quiz.setQuizId(quizId);
		setQuiz.remove(quiz);
		return objectMapper.writeValueAsString(quiz);
		}
		else if(setQuiz.size()==0){
			return objectMapper.writeValueAsString("Quiz is completed for this user");
		}
		else{
			return objectMapper.writeValueAsString("Quiz is not started for this user");
		}
	}


	
	
	
	
	public static void main(String[] args) {
		try {/*
			
			setQuiz=new QuestionController().getQuizFromFile("java");
			
			try(FileWriter fileWriter=new FileWriter("/text.txt")){
				fileWriter.write(setQuiz.toString());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(setQuiz.iterator().next());
		*/
			
			System.out.println(ThreadLocalRandom.current().nextInt(100,1000));
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static Set<Quiz> setQuiz;
	public static String quizId;
	

}
