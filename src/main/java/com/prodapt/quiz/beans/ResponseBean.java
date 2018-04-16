package main.java.com.prodapt.quiz.beans;

import java.io.Serializable;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 
 * @author gandhi.d
 *
 */

public class ResponseBean implements Serializable {
	
private static final long serialVersionUID = 12661L;
private JSONObject jsonObject;
private JSONArray jsonArray;
private Integer statusCode;


public JSONObject getJsonObject() {
	return jsonObject;
}
public void setJsonObject(JSONObject jsonObject) {
	this.jsonObject = jsonObject;
}
public JSONArray getJsonArray() {
	return jsonArray;
}
public void setJsonArray(JSONArray jsonArray) {
	this.jsonArray = jsonArray;
}
public Integer getStatusCode() {
	return statusCode;
}
public void setStatusCode(Integer statusCode) {
	this.statusCode = statusCode;
}


}
