package main.java.com.prodapt.quiz.beans;

import java.io.Serializable;
/**
 * 
 * @author gandhi.d
 *
 */

public class Token implements Serializable {
	
	private static final long serialVersionUID = 18974L;
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

}
