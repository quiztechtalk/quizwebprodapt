package main.java.com.prodapt.quiz.common;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class QuizProperties {
	
	private static Properties properties;
	private static Logger logger=Logger.getLogger(QuizProperties.class);
	
	private QuizProperties(){
		
	}
	
	public static Properties getInstance(){
		if(properties ==null ){
			
			try{
				
			properties=new Properties();
			InputStream inputStream=QuizProperties.class.getResourceAsStream("/com/properties/quiz.properties");
			properties.load(inputStream);
			inputStream.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		return properties;
	}
	
	
	
}
