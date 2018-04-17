package main.java.com.prodapt.quiz.common;

import main.java.com.prodapt.quiz.beans.ResponseBean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author gandhi.d
 *
 */

public class ResponseData {

	public static ResponseBean internalError() throws JSONException {
		ResponseBean responseData = new ResponseBean();

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", ResponseCode.INTERNAL_SERVER_ERROR);
		jsonObject.put("statusMessage", ResponseMessage.INTERNAL_SERVER_ERROR);
		//responseData.setStatusCode(ResponseCode.INTERNAL_SERVER_ERROR);
		return responseData;
	}

	public static ResponseBean success(Object data) throws JSONException {
		ResponseBean responseData = new ResponseBean();
		responseData.setJsonObject(data);
		return responseData;
	}

	public static ResponseBean expireToken() throws JSONException {
		ResponseBean responseData = new ResponseBean();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", ResponseCode.INTERNAL_SERVER_ERROR);
		jsonObject.put("statusMessage", ResponseMessage.TOKEN_EXPIRED);
		//responseData.setStatusCode(ResponseCode.INTERNAL_SERVER_ERROR);
		responseData.setJsonObject(jsonObject);
		return responseData;
	}

	public static ResponseBean invalidToken() throws JSONException {
		ResponseBean responseData = new ResponseBean();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", ResponseCode.INTERNAL_SERVER_ERROR);
		jsonObject.put("statusMessage", ResponseMessage.INVALID_TOKEN);
		//responseData.setStatusCode(ResponseCode.INTERNAL_SERVER_ERROR);
		responseData.setJsonObject(jsonObject);
		return responseData;
	}

}
