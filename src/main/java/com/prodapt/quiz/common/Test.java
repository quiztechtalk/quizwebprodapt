package main.java.com.prodapt.quiz.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test {
	
	public static void main(String[] args) {

		String a = "SomeOne";

		String b = "SomeTwo";

		String s1[] = a.split("");

		String s2[] = b.split("");
		
		List<String> name1=new CopyOnWriteArrayList<>();
		List<String> name2=new CopyOnWriteArrayList<>();
		List<String> name3=new CopyOnWriteArrayList<>();
		
		name1.addAll(Arrays.asList(s1));
		name2.addAll(Arrays.asList(s2));
		name3.addAll(Arrays.asList("f","l","a","m","e","s"));

		for(String elString:name1){
			if(name2.contains(elString)){
				name2.remove(name2.get(name2.indexOf(elString)));
				name1.remove(name1.get(name1.indexOf(elString)));
				
			}
		}
		
		int flLength=name2.size()+name1.size();
		
		System.out.println("flLength "+flLength);
		
		
		while(true){
			if(name3.size()==1){
				break;
			}
			int temp=0;
			getLength(name3,temp,flLength);
			}
		
		/*for(int i=flLength;i>1;i--){
			int temp=0;
			getLength(name3,temp,i);
			}
*/
		System.out.println(name1);
		System.out.println(name2);
		
		System.out.println("Result is "+getDescription(name3.get(0)));
	}

	
	public static int getLength(List<String> list, int temp, int condition) {
		for (int j = 0; j < list.size(); j++) {
			++temp;
			if (list.size() > 1 && temp == condition) {
				System.out.println("matched position " + temp + " value "
						+ list.get(j) + " i " + condition);
				list.remove(j);
				break;
			}
		}

		if (condition != temp) {
			getLength(list, temp, condition);
		}

		return 0;
	}
	
	public static String getDescription(String value){
		Map<String, String> fMap=new HashMap<String, String>();
		fMap.put("f", "Friend");
		fMap.put("l", "Love");
		fMap.put("a", "Affection");
		fMap.put("m", "Marriage");
		fMap.put("e", "Enemy");
		fMap.put("s", "Sister");
		return fMap.get(value.toLowerCase());
	}
	
}


