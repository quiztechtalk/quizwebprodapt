package main.java.com.prodapt.quiz.beans;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author gandhi.d
 *
 */

public class Answer implements Serializable {

	private static final long serialVersionUID = 190934L;
	private Integer mark;
	private String topic;
	private List<Quiz> answers;
	
	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public List<Quiz> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Quiz> answers) {
		this.answers = answers;
	}
	
	

}
