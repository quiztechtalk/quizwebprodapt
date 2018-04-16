package main.java.com.prodapt.quiz.common;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class MongoProperties {
	
	private static Properties properties;
	private static Logger logger=Logger.getLogger(MongoProperties.class);
	
	private MongoProperties(){
		
	}
	
	public static Properties getInstance(){
		if(properties ==null ){
			
			try{
				
			properties=new Properties();
			InputStream inputStream=MongoProperties.class.getResourceAsStream("/com/properties/mongo.properties");
			properties.load(inputStream);
			inputStream.close();
			}
			catch(Exception e){
				logger.error(e,e);
			}
			
		}
		
		return properties;
	}
	
}
