package main.java.com.prodapt.quiz.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import main.java.com.prodapt.quiz.beans.Quiz;
import main.java.com.prodapt.quiz.beans.ResponseBean;
import main.java.com.prodapt.quiz.common.QuizProperties;
import main.java.com.prodapt.quiz.common.ResponseData;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.json.JSONException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 
 * @author gandhi.d
 *
 */
public class QuestionController {
	
	

	
	
	/**
	 * 
	 * @param liQuizs
	 * @param mark
	 * @return 
	 * topic is suppose not available or given mark is less than the total of collection it will return not available
	 * @throws JSONException 
	 */
	private ResponseBean getTopicStatus(final List<Quiz> liQuizs, final Integer mark) throws JSONException {

		ResponseBean responceBean = new ResponseBean();
		if (liQuizs == null || liQuizs.isEmpty()){
			responceBean = ResponseData.success("Topic is not available");
		}
		else if (liQuizs.stream().mapToInt(i -> i.getMark()).sum() < mark) {
			responceBean = ResponseData
					.success("Question is not available for this mark please select the minimum");
		} else {
			responceBean=getRandomQuestions(splitQuiz(liQuizs),mark);
		}

		return responceBean;

	}
	
/**
 * 	
 * @param allQuestionsList
 * @param mark
 * @return
 * based on total mark question is split and how many counts may come those details set in properties file  
 * @throws JSONException 
 */
private ResponseBean getRandomQuestions(final Map<Integer, List<Quiz>> allQuestionsList,final Integer mark) throws JSONException{
		
		if(QuizProperties.getInstance().get("mark_"+mark)==null || QuizProperties.getInstance().get("mark_"+mark).toString().trim().isEmpty()){
			return ResponseData
					.success("Question set is available for this mark");
		}
		else
		{
			Map<Integer,Integer> questionSetMap=new TreeMap<>();
			/*
			 * questionSetMap is used to store question mark as key and number of question count is value everything get from properties file
			 */
			for(String questionSet:QuizProperties.getInstance().get("mark_"+mark).toString().split(",")){
				questionSetMap.put(Integer.parseInt(questionSet.substring(0,questionSet.indexOf("_"))), Integer.parseInt(questionSet.substring(questionSet.indexOf("_")+1)));
				
				if(allQuestionsList.get(Integer.parseInt(questionSet.substring(0,questionSet.indexOf("_")))).size()<Integer.parseInt(questionSet.substring(questionSet.indexOf("_")+1))){
					return ResponseData
							.success("Question set is available for this mark");
				}
			}
			return ResponseData.success(getQuizBasedOnMark(allQuestionsList,questionSetMap));
		}
	}
	
	/**
	 * 
	 * @param liQuizs
	 * @return 
	 * this is method is used to split the collection stored in map based on marks set
	 */
	private Map<Integer, List<Quiz>> splitQuiz(final List<Quiz> liQuizs){
		Map<Integer, List<Quiz>> splitQuizMap=new TreeMap<>();
		for(Quiz quiz:liQuizs){
			if(splitQuizMap.containsKey(quiz.getMark())){
				splitQuizMap.get(quiz.getMark()).add(quiz);
			}
			else{
				List<Quiz> list=new ArrayList<Quiz>();
				list.add(quiz);
				splitQuizMap.put(quiz.getMark(), list);
			}
		}
		return splitQuizMap;
	}
	
	
	/**
	 * 
	 * @param allQuestionsList
	 * @param questionSetMap
	 * @return
	 * here suppose number of question count is equal to available questions that time its fully taken
	 */
	
	private List<Quiz> getQuizBasedOnMark(final Map<Integer, List<Quiz>> allQuestionsList,final Map<Integer,Integer> questionSetMap){
		List<Quiz> listofQuiz=new ArrayList<>();
		for(Map.Entry<Integer, Integer> questionPick:questionSetMap.entrySet()){
			if(allQuestionsList.get(questionPick.getKey()).size()==questionPick.getValue()){
				listofQuiz.addAll(allQuestionsList.get(questionPick.getKey()));
			}
			else{
				listofQuiz.addAll(getRandomQuizs(allQuestionsList,questionPick.getKey(),questionPick.getValue()));
			}
		}
		return listofQuiz;
	}
	
	/**
	 * 
	 * @param allQuestionsList
	 * @param questionMark
	 * @param questionMarkCount
	 * @return 
	 * randomly taken question 
	 */
	
	private Set<Quiz> getRandomQuizs(final Map<Integer, List<Quiz>> allQuestionsList,final Integer questionMark,final Integer questionMarkCount){
		Set<Quiz> listOfRandomQuiz=new HashSet<>();
		boolean isRandomQuestionNotSet=true;
			
			while(isRandomQuestionNotSet){
				if(listOfRandomQuiz.size()==questionMarkCount){
					isRandomQuestionNotSet=false;
				}
				else{
					listOfRandomQuiz.add(allQuestionsList.get(questionMark)
							.get(ThreadLocalRandom.current().nextInt(0, allQuestionsList.get(questionMark).size())));
				}
			}
			
		return listOfRandomQuiz;
	}
	
	public ResponseBean getQuizFromFile(final String topic, final Integer mark) throws JSONException {

		ResponseBean responceBean = new ResponseBean();

		try {
			
			 JSONParser parser = new JSONParser();
				InputStream inputStream=TopicController.class.getResourceAsStream("/com/properties/local.quiz.json");
	        	Reader reader=new InputStreamReader(inputStream);
	            Object obj = parser.parse(reader);
	            
	         ObjectMapper objectMapper=new ObjectMapper();
	         List<Quiz> lQuizs=objectMapper.readValue(obj.toString(), TypeFactory.collectionType(List.class, Quiz.class));
	         
	         List<Quiz> filteredQuiz=lQuizs.stream().filter(a->a.getTopic().equals(topic)).collect(Collectors.toList());
		
			responceBean = getTopicStatus(filteredQuiz,mark);
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
	
	
	
	public static void main(String[] args) {}

}
