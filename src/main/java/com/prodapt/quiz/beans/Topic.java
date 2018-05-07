package main.java.com.prodapt.quiz.beans;

import java.io.Serializable;
/**
 * 
 * @author gandhi.d
 *
 */
public class Topic implements Serializable {
	

	private static final long serialVersionUID = 1980954L;
	private String _id;
	private String name;
	private String mark;
	private String time;
	
	
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
