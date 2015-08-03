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
	
	public static final String readmeFileName = "Readme.txt"; 
	protected static final String ext = ".java";
	protected static final String pathGenerate = "Temporary";

		
	public CodeTestingProblem() {
		super();
		numOfAnswers = 0;
		typeOfView = ITaskView.CODE;
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
	
	protected void createDir() {
		long name = System.currentTimeMillis();
		filePath = pathGenerate.concat(File.separator).concat(Long.toString(name));
		File dir = new File(filePath);
		if(dir.exists())
			createDir();
		else {
			dir.mkdirs();			
		}
	}
	
	public void generate(int difLevel)  {
				
		createDir(); /* ---  create temporary folder for files  --- */
		codeFiles = new LinkedList<String>();
		generateTest(difLevel);
		
	}
	
	abstract protected void generateTest(int difLevel); 
}
