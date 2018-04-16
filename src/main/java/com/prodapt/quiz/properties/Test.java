package main.java.com.prodapt.quiz.properties;

import main.java.com.prodapt.quiz.common.JWTProperties;

public class Test {
	public static void main(String[] args) {
		System.out.println(new Test().getClass().getCanonicalName());
		System.out.println(JWTProperties.getInstance().getProperty("time"));
	}

}
