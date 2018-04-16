package main.java.com.prodapt.quiz.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

import main.java.com.prodapt.quiz.beans.Quiz;
import main.java.com.prodapt.quiz.controller.TopicController;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JsonSimpleReadExample {

    public static void main(String[] args) {


        try {
        
        	 JSONParser parser = new JSONParser();
				InputStream inputStream=TopicController.class.getResourceAsStream("/com/properties/local.quiz.json");
	        	Reader reader=new InputStreamReader(inputStream);
	            Object obj = parser.parse(reader);
	            
	         ObjectMapper objectMapper=new ObjectMapper();
	         List<Quiz> lQuizs=objectMapper.readValue(obj.toString(), TypeFactory.collectionType(List.class, Quiz.class));
	         System.out.println(lQuizs.size());
	         
	         List<Quiz> filteredQuiz=lQuizs.stream().filter(a->a.getTopic().equals("java")).collect(Collectors.toList());
	         
	         
            

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}