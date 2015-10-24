package tel_ran.tests.generator.code_task;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import tel_ran.tests.generator.AbstractTest;
import tel_ran.tests.interfaces.ITaskView;

public abstract class CodeTestingProblem extends AbstractTest {
	
	public String questionText; 
	public String stubText;
	public String testLanguage;
	protected String filePath;
	protected List<String> codeFiles;
			
	public CodeTestingProblem() {
		super();
		numOfAnswers = 0;		
	}

	public String getQuestionText() {
		return questionText;
	}
		

	public String getTestLanguage() {
		return testLanguage;
	}

	public String getStubText() {
		return stubText;
	}
	
	public String getFilePath() {
		return filePath;
	}

	public List<String> getCodeFiles() {
		return codeFiles;
	}
	

	
	public void generate(int difLevel)  {				
		
		codeFiles = new LinkedList<String>();
		generateTest(difLevel);
		
	}
	
	@Override
	public ITaskView getView() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> cl = Class.forName(ITaskView.CODE);
		ITaskView taskView = (ITaskView) cl.newInstance();
		return taskView;
	}

	
	abstract protected void generateTest(int difLevel); 
}
