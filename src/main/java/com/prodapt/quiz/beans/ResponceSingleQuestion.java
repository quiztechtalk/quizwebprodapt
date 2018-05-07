package main.java.com.prodapt.quiz.beans;

import java.io.Serializable;

/**
 * 
 * @author gandhi.d
 *
 */

public class ResponceSingleQuestion implements Serializable {
	private static final long serialVersionUID = 134637L;
	
	private String selectedAnswer;
	private String correctAnswer;
	public String getSelectedAnswer() {
		return selectedAnswer;
	}
	public void setSelectedAnswer(String selectedAnswer) {
		this.selectedAnswer = selectedAnswer;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	

	
}
