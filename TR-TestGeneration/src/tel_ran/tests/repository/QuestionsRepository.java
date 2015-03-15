package tel_ran.tests.repository;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class QuestionsRepository {
	
	List<String[]> questionList = new LinkedList<String[]>();

	public QuestionsRepository() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public boolean addQuestion(String[] str) {
		return questionList.add(str);
	}
	
	public List<String[]> getQuestionList() {
		return questionList;
	}
	
	public void displayList() {
		Iterator<String[]> it = questionList.iterator();		
		while (it.hasNext()) {
			System.out.println(Arrays.toString(it.next()));
			
		}
	}
	
	

}
