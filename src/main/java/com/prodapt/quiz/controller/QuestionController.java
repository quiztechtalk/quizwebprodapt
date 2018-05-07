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
import main.java.com.prodapt.quiz.beans.Topic;
import main.java.com.prodapt.quiz.common.QuizProperties;

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
	private Set<Quiz> getTopicStatus(final List<Quiz> liQuizs, final Integer mark) throws JSONException {

		return getRandomQuestions(splitQuiz(liQuizs),mark);

	}
	
/**
 * 	
 * @param allQuestionsList
 * @param mark
 * @return
 * based on total mark question is split and how many counts may come those details set in properties file  
 * @throws JSONException 
 */
private Set<Quiz> getRandomQuestions(final Map<Integer, List<Quiz>> allQuestionsList,final Integer mark) throws JSONException{
		
	Set<Quiz> seQuizs=new HashSet<Quiz>();
	
			Map<Integer,Integer> questionSetMap=new TreeMap<>();
			/*
			 * questionSetMap is used to store question mark as key and number of question count is value everything get from properties file
			 */
			for(String questionSet:QuizProperties.getInstance().get("mark_"+mark).toString().split(",")){
				questionSetMap.put(Integer.parseInt(questionSet.substring(0,questionSet.indexOf("_"))), Integer.parseInt(questionSet.substring(questionSet.indexOf("_")+1)));
				
			}
			
			System.out.println(getQuizBasedOnMark(allQuestionsList,questionSetMap));
			
		seQuizs.addAll(getQuizBasedOnMark(allQuestionsList,questionSetMap));
		return seQuizs;
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
	
	public Set<Quiz> getQuizFromFile(final String topic) throws JSONException {

		Set<Quiz> responceBean = new HashSet<Quiz>();

		try {
			
			 JSONParser parser = new JSONParser();
				InputStream inputStream=TopicController.class.getResourceAsStream("/local.quiz.json");
	        	Reader reader=new InputStreamReader(inputStream);
	            Object obj = parser.parse(reader);
	            
	            InputStream inputStream2=TopicController.class.getResourceAsStream("/local.topic.json");
	        	Reader reader2=new InputStreamReader(inputStream2);
	            Object obj2 = parser.parse(reader2);
	            
	         ObjectMapper objectMapper=new ObjectMapper();
	         List<Quiz> lQuizs=objectMapper.readValue(obj.toString(), TypeFactory.collectionType(List.class, Quiz.class));
	         
	         List<Topic> topic2=objectMapper.readValue(obj2.toString(), TypeFactory.collectionType(List.class, Topic.class));
	         String topicMark="30";
	         for(Topic topicl:topic2){
	        	if(topicl.getName().equalsIgnoreCase(topic)) {
	        		topicMark=topicl.getMark();
	        	}
	         }
	         
	         List<Quiz> filteredQuiz=lQuizs.stream().filter(a->a.getTopic().equalsIgnoreCase(topic)).collect(Collectors.toList());
		
			responceBean = getTopicStatus(filteredQuiz,Integer.parseInt(topicMark));
		}

		catch (ParseException exception) {
			// UTF-8 encoding not supported
			exception.printStackTrace();
		}

		catch (FileNotFoundException exception) {
			// Invalid Signing configuration / Couldn't convert Claims.
			exception.printStackTrace();
		}
		
		catch (IOException exception) {
			// Invalid Signing configuration / Couldn't convert Claims.
			exception.printStackTrace();
		}

		return responceBean;

	}
	
	
	
	public static void main(String[] args) {}

}
