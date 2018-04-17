package main.java.com.prodapt.quiz.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import main.java.com.prodapt.quiz.beans.ResponseBean;
import main.java.com.prodapt.quiz.beans.Topic;
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
public class TopicController {

	
	
	public List<Topic> getTopicsFromFile() throws JSONException {

		List<Topic> responceBean = new ArrayList<Topic>();
		try {
			 JSONParser parser = new JSONParser();
			InputStream inputStream=TopicController.class.getResourceAsStream("/local.topic.json");
        	Reader reader=new InputStreamReader(inputStream);
            Object obj = parser.parse(reader);
            
            ObjectMapper objectMapper=new ObjectMapper();
            responceBean=objectMapper.readValue(obj.toString(), TypeFactory.collectionType(List.class, Topic.class));
		}

		catch (ParseException exception) {
			// UTF-8 encoding not supported
			//responceBean = ResponseData.internalError();
			exception.printStackTrace();
		}

		catch (FileNotFoundException exception) {
			// Invalid Signing configuration / Couldn't convert Claims.
			//responceBean = ResponseData.internalError();
			exception.printStackTrace();
		}
		
		catch (IOException exception) {
			// Invalid Signing configuration / Couldn't convert Claims.
			//responceBean = ResponseData.internalError();
			exception.printStackTrace();
		}

		return responceBean;

	}
	
	public static void main(String[] args) throws IOException, ParseException {}
	

}
