package main.java.com.prodapt.quiz.beans;

import java.io.Serializable;

/**
 * 
 * @author gandhi.d
 *
 */

public class Mark implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer mark;
	private Integer time;
	
	private String _id;
	
	
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	
	
	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
	}
	public Integer getTime() {
		return time;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	

}
