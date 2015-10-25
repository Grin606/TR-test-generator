package tel_ran.tests.generator.code_task.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;

import tel_ran.tests.generator.code_task.CodeTestingProblem;

public abstract class JavaTestingProblem extends CodeTestingProblem {
	
	public static final String readmeFileName = "Readme.txt"; 
	protected static final String ext = ".java";
	protected static final String pathGenerate = "Temporary";
	protected static final String packageJavaForGradleStructure = "package main.java;";
	protected static final String packageTestForGradleStructure = "package test.java;";
	protected static final String baseForImportInTestForGradleStructure = "import main.java.";
	
	protected String testName;
	protected  String interfaceName;
	protected  String className;
	
	public JavaTestingProblem() {
		super();
		this.testLanguage = "java";
		this.numberOfDescripton = 10; 
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
	
	protected String generateReadme(String filePath) throws FileNotFoundException {
		
		String path = filePath.concat(File.separator).concat(readmeFileName);
		
		PrintWriter out = new PrintWriter(path);		
		out.println("Interface: " + interfaceName + ext);
		out.println("JUnit: " + testName + ext);
		out.close();
		
		return path;	}
	


}
