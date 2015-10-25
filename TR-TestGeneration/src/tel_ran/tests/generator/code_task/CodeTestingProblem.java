package tel_ran.tests.generator.code_task;

import java.util.List;

import tel_ran.tests.generator.AbstractTest;
import tel_ran.tests.interfaces.ITaskView;

public abstract class CodeTestingProblem extends AbstractTest {
	
	public String questionText; 
	public String stubText;
	public String testLanguage;
	protected List<String> codeFiles;
	protected String filePath;
			
	
	
	public CodeTestingProblem() {
		super();
		numOfAnswers = 0;		
	}
		
	@Override
	public String getPathToFiles() {
		return filePath;
	}
	
	public List<String> getFiles() {
		return codeFiles;
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
	

	
	@Override
	public ITaskView getView() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<?> cl = Class.forName(ITaskView.CODE);
		ITaskView taskView = (ITaskView) cl.newInstance();
		return taskView;
	}

}
