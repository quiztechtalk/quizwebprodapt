package main.java.com.prodapt.quiz.beans;

import java.io.Serializable;
/**
 * 
 * @author gandhi.d
 *
 */
public class Result implements Serializable{

	private static final long serialVersionUID = 13467L;
	
	private Integer totalMarks;
	private Integer scoredMarks;
	private String resultStatus;
	
	public String getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}
	public Integer getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(Integer totalMarks) {
		this.totalMarks = totalMarks;
	}
	public Integer getScoredMarks() {
		return scoredMarks;
	}
	public void setScoredMarks(Integer scoredMarks) {
		this.scoredMarks = scoredMarks;
	}
	
	
	

}
