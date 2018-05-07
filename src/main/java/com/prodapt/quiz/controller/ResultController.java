package main.java.com.prodapt.quiz.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import main.java.com.prodapt.quiz.beans.Answer;
import main.java.com.prodapt.quiz.beans.Quiz;
import main.java.com.prodapt.quiz.beans.ResponceSingleQuestion;
import main.java.com.prodapt.quiz.beans.ResponseBean;
import main.java.com.prodapt.quiz.beans.Result;
import main.java.com.prodapt.quiz.common.ResponseData;
import main.java.com.prodapt.quiz.service.TokenService;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.json.JSONException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.auth0.jwt.exceptions.JWTCreationException;

/**
 * 
 * @author gandhi.d
 *
 */
public class ResultController {
	

	public ResponseBean getResult(final Answer answer) throws JSONException {
		ResponseBean responceBean = new ResponseBean();

		try {
			JSONParser parser = new JSONParser();
			InputStream inputStream=TopicController.class.getResourceAsStream("/local.quiz.json");
        	Reader reader=new InputStreamReader(inputStream);
            Object obj = parser.parse(reader);
            
         ObjectMapper objectMapper=new ObjectMapper();
         List<Quiz> lQuizs=objectMapper.readValue(obj.toString(), TypeFactory.collectionType(List.class, Quiz.class));
         
			
			List<Quiz> filteredQuiz=lQuizs.stream().filter(a->a.getTopic().equals(answer.getTopic())).collect(Collectors.toList());
	
			responceBean = getTopicStatus(filteredQuiz,answer);
		}

		
		catch (JWTCreationException exception) {
			// Invalid Signing configuration / Couldn't convert Claims.
			responceBean = ResponseData.invalidToken();
			exception.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			responceBean = ResponseData.invalidToken();
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			responceBean = ResponseData.invalidToken();
			e.printStackTrace();
		}

		return responceBean;

	}
	
	public ResponseBean getResultForSingleQuestion(final Answer answer) throws JSONException {
		ResponseBean responceBean = new ResponseBean();

		try {
			JSONParser parser = new JSONParser();
			InputStream inputStream=TopicController.class.getResourceAsStream("/local.quiz.json");
        	Reader reader=new InputStreamReader(inputStream);
            Object obj = parser.parse(reader);
            
         ObjectMapper objectMapper=new ObjectMapper();
         List<Quiz> lQuizs=objectMapper.readValue(obj.toString(), TypeFactory.collectionType(List.class, Quiz.class));
         
			
			List<Quiz> filteredQuiz=lQuizs.stream().filter(a->a.getTopic().equals(answer.getTopic())).collect(Collectors.toList());
	
			responceBean = getSingleQuestionStatus(filteredQuiz,answer);
		}

		
		catch (JWTCreationException exception) {
			// Invalid Signing configuration / Couldn't convert Claims.
			responceBean = ResponseData.invalidToken();
			exception.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			responceBean = ResponseData.invalidToken();
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			responceBean = ResponseData.invalidToken();
			e.printStackTrace();
		}

		return responceBean;

	}
	
	/**
	 * 
	 * @param liQuizs
	 * @param answer
	 * @return
	 * @throws JSONException 
	 */
	private ResponseBean getTopicStatus(final List<Quiz> liQuizs, final Answer answer) throws JSONException {

		ResponseBean responceBean = new ResponseBean();
		if (liQuizs == null || liQuizs.isEmpty()){
			responceBean = ResponseData.success("Topic is not available");
		}
		else if (liQuizs.stream().mapToInt(i -> i.getMark()).sum() < answer.getMark()) {
			responceBean = ResponseData
					.success("certain mark list is not available");
		} else {
			responceBean=getRandomQuestions(splitQuizBasedOnQuestionId(liQuizs),answer);
		}

		return responceBean;

	}
	
	private ResponseBean getSingleQuestionStatus(final List<Quiz> liQuizs, final Answer answer) throws JSONException {

		ResponseBean responceBean = new ResponseBean();
		if (liQuizs == null || liQuizs.isEmpty()){
			responceBean = ResponseData.success("Topic is not available");
		}
		else if (liQuizs.stream().mapToInt(i -> i.getMark()).sum() < answer.getMark()) {
			responceBean = ResponseData
					.success("certain mark list is not available");
		} else {
			responceBean=getResultSingleQuestions(splitQuizBasedOnQuestionId(liQuizs),answer);
		}

		return responceBean;

	}
	
/**
 * 	
 * @param allQuestionsList
 * @param answer
 * @return
 * @throws JSONException 
 */
private ResponseBean getRandomQuestions(final Map<Integer, Quiz> allQuestionsList,final Answer answer) throws JSONException{
		
	ResponseBean responseBean=new ResponseBean();
	Result result=new Result();
	List<Result> listOfResult=new  ArrayList<>();
	int scored=0;
	for(Quiz quiz:answer.getAnswers()){
		if(allQuestionsList.get(quiz.getQuestionId())!=null && quiz.getAnswer().equalsIgnoreCase(allQuestionsList.get(quiz.getQuestionId()).getAnswer())){
			scored=scored+allQuestionsList.get(quiz.getQuestionId()).getMark();
		}
		else
		{
			System.out.println(quiz.getAnswer()+" Wrong answer "+allQuestionsList.get(quiz.getQuestionId()).getAnswer());
		}
	}
	result.setScoredMarks(scored);
	result.setTotalMarks(answer.getMark());
	
	float scoredInFloat =scored;
	float totalMarksInFloat=answer.getMark();
	if(scored!=0 && ((scoredInFloat/totalMarksInFloat)*100)>=50){
		result.setResultStatus("Pased");
	}
	else {
		result.setResultStatus("Faild");
	}
	listOfResult.add(result);
	responseBean=ResponseData.success(listOfResult);
	return responseBean;
		
	}


private ResponseBean getResultSingleQuestions(final Map<Integer, Quiz> allQuestionsList,final Answer answer) throws JSONException{
	ResponseBean responseBean=new ResponseBean();
	ResponceSingleQuestion responceSingleQuestion=new ResponceSingleQuestion();
	for(Quiz quiz:answer.getAnswers()){
		if(allQuestionsList.get(quiz.getQuestionId())!=null){
			responceSingleQuestion.setCorrectAnswer(allQuestionsList.get(quiz.getQuestionId()).getAnswer());
			responceSingleQuestion.setSelectedAnswer(quiz.getAnswer());
		}
		else
		{
			System.out.println(quiz.getAnswer()+" Wrong answer "+allQuestionsList.get(quiz.getQuestionId()).getAnswer());
		}
	}
	responseBean=ResponseData.success(responceSingleQuestion);
	return responseBean;
		
	}
	
	/**
	 * 
	 * @param liQuizs
	 * @return 
	 * this is method is used to split the collection stored in map based on QuestionId
	 */
	private Map<Integer, Quiz> splitQuizBasedOnQuestionId(final List<Quiz> liQuizs){
		Map<Integer, Quiz> splitQuizMap=new TreeMap<>();
		for(Quiz quiz:liQuizs){
				splitQuizMap.put(quiz.getQuestionId(), quiz);
		}
		return splitQuizMap;
	}
	
	
	
	public ResponseBean getResultFromFile(final Answer answer) throws JSONException {
		ResponseBean responceBean = new ResponseBean();
		TokenService tokenService = new TokenService();

		try {
			 JSONParser parser = new JSONParser();
				InputStream inputStream=TopicController.class.getResourceAsStream("/local.quiz.json");
	        	Reader reader=new InputStreamReader(inputStream);
	            Object obj = parser.parse(reader);
	            
	         ObjectMapper objectMapper=new ObjectMapper();
	         List<Quiz> lQuizs=objectMapper.readValue(obj.toString(), TypeFactory.collectionType(List.class, Quiz.class));
	         
	         List<Quiz> filteredQuiz=lQuizs.stream().filter(a->a.getTopic().equalsIgnoreCase(answer.getTopic())).collect(Collectors.toList());
		
			responceBean = getTopicStatus(filteredQuiz,answer);
		}

		catch (ParseException exception) {
			// UTF-8 encoding not supported
			responceBean = ResponseData.internalError();
			exception.printStackTrace();
		}

		catch (FileNotFoundException exception) {
			// Invalid Signing configuration / Couldn't convert Claims.
			responceBean = ResponseData.internalError();
			exception.printStackTrace();
		}
		
		catch (IOException exception) {
			// Invalid Signing configuration / Couldn't convert Claims.
			responceBean = ResponseData.internalError();
			exception.printStackTrace();
		}
		return responceBean;

	}
	
	
		
	
	
}
