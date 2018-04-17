package main.java.com.prodapt.quiz.common;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 
 * @author gandhi.d
 *
 */

public class JWTProperties {
	
	private static Properties properties;
	private static Logger logger=Logger.getLogger(JWTProperties.class);
	
	private JWTProperties(){
		
	}
	
	public static Properties getInstance(){
		if(properties ==null ){
			
			try{
				
			properties=new Properties();
			InputStream inputStream=JWTProperties.class.getResourceAsStream("/jwt.properties");
			properties.load(inputStream);
			inputStream.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		return properties;
	}
	
	public static void main(String[] args) {
		System.out.println(JWTProperties.getInstance().getProperty("time"));
	}
	
}
