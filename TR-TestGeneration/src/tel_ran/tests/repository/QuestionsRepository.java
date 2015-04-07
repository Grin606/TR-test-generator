package tel_ran.tests.repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
	
	public void displayInFile(String path) throws IOException {
		String fileName = path + "answers.txt";	
		File base = new File(fileName);
		
		if (base.exists()) {
			base.delete();
			base.createNewFile();
		}
		
		FileWriter fw = new FileWriter(base, true);
		BufferedWriter bw = new BufferedWriter(fw);		
		String dev = "----";
		StringBuffer tt;

		for (String[] st : questionList) {
			tt = new StringBuffer("");
			for (String str : st)
				tt.append(str).append(dev);
			bw.write(tt.toString());
			bw.newLine();
		}
		
		bw.close();
		fw.close();		
				
	}
	
	public List<String[]> getList () {
		return questionList;
	}
	

}
