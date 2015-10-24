package tel_ran.tests.generator.code_task.calculator;

import java.io.*;
import java.util.*;

import tel_ran.tests.generator.code_task.CodeTestingProblem;
import tel_ran.tests.generator.code_task.calculator.methods.Actions;
import tel_ran.tests.interfaces.ITaskView;


public class StringCalculatorCodingTest extends CodeTestingProblem {
	
	private Actions[] methods;
	private int base;
	private TestData td;
	
	private static final String testName = "SCalculator_Test";
	private static final String interfaceName = "SCalculator";
	private static final String className = "StringCalculator";
		
	public static final String readmeFileName = "Readme.txt"; 
	protected static final String ext = ".java";
	protected static final String pathGenerate = "Temporary";
	protected static final String packageJavaForGradleStructure = "package main.java;";
	protected static final String packageTestForGradleStructure = "package test.java;";
	protected static final String baseForImportInTestForGradleStructure = "import main.java.";
			
	Random gen = new Random();
		
	public StringCalculatorCodingTest() {
		super();
		category2Name = "Calculator";  /* -- new line -- */
		this.numberOfDescripton = 10;   /* -- new line -- */
		this.testLanguage = "java";
	}
		
	public void generateTest(int difLevel) {
		createDir(); /* ---  create temporary folder for files  --- */
		
		getTestData(difLevel);

		generateQuestion();  /* changed */
		generateStub();      /* changed */
		
		
		try {
			this.codeFiles.add(generateJUnit(filePath));  /* return result as a list of string = paths to all of the files */
			this.codeFiles.add(generateInterface(filePath));  /* return result as a list of string = paths to all of the files */
			this.codeFiles.add(generateReadme(filePath)); /* --- add Readme file --- */
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
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
	
	
	private String generateReadme(String filePath) throws FileNotFoundException {
		
		String path = filePath.concat(File.separator).concat(readmeFileName);
		
		PrintWriter out = new PrintWriter(path);		
		out.println("Interface: " + interfaceName + ext);
		out.println("JUnit: " + testName + ext);
		out.close();
		
		return path;
	}




	public void getTestData(int difLevel) {	
		
		td = new TestData();
		ArrayList<String> methodsList = getMethodsList();
		
		ArrayList<Integer> testData = getTestProgram(difLevel);

		base = testData.get(0);
		
		ArrayList<String> methodsNames = new ArrayList<String>();
		
		for (int i=1; i<testData.size(); i++) 
			methodsNames.add(methodsList.get(testData.get(i))); 
		
		methods = new Actions[methodsNames.size()];
		String clName = null;
		for (int i=0; i<methodsNames.size(); i++) {
			try {
				clName = "tel_ran.tests.generator.code_task.calculator.methods."+methodsNames.get(i);
				methods[i] = (Actions) Class.forName(clName).newInstance();
				methods[i].setBase(base);
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				System.out.println("Class " + clName + " not found");
				return;
			}
		}
	}
	
	
	// ------ Minus generate Description! All our description are in DescriptionBox. ---- !!!
	
//	private void generateDescription(String filePath) throws FileNotFoundException {
//		
//		PrintStream out = new PrintStream (filePath);
//		out.println("Implement the interface");
//		out.close();
//	}
	
	
	// ------------- generate STRING instead of FILE ---------- !!!! 
	
	private void generateQuestion() {
		
		StringBuffer res = new StringBuffer("");
		
		res.append("Write the public class ").append(className).append(" using the ").append(Integer.toString(base)).
				append("-based positional numerical system with the digits from DIGITS,").append(
						"\nworking with integers and implementing\n");
		outInterface(res);
		
		res.append("\nwhere op1 and op2 are the integers written in ").append(Integer.toString(base)).
				append("-based positional numerical system \nand all methods, returning numbers, \n").
				append("return the result written by the same manner.").append(
				"\n\nIf something is wrong, the result must be WRONG"); 
		
		this.questionText = res.toString();
	}
	
	
	// ------------- generate STRING instead of FILE ---------- !!!! 
	
	private void generateStub() {
		
		StringBuffer res = new StringBuffer("");
		
		
		res.append(packageJavaForGradleStructure).append("\n\n").
			append("public class ").append(className).append(" implements SCalculator {\n").
			append("\n\t//base ").append(base).
			append("\n\t//digits ").append(methods[0].getAlphabet()).append("\n\n");
		
		for (int i=0; i<methods.length; i++) 
			res.append(methods[i].toStub()).append("\n");
		
		res.append("\n}\n");
		
		this.stubText = res.toString();
		
	}
	
	
	// ------------- generate File using PrintWriter instead of PrintStream ---------- !!!! 
	// ------------- + correct File-Name (it should be = name of class) --------- !!!!
	// ------------- return String = path with the name of the file -------------- !!!!
	// ------------- + static variable for naming -------------------------------- !!!!
	
	private String generateJUnit(String filePath) throws FileNotFoundException {
		
		String path = filePath.concat(File.separator).concat(testName).concat(ext);
		
		PrintWriter out = new PrintWriter(path);
				
		out.println(packageTestForGradleStructure);
		out.println("");
		out.println("import static org.junit.Assert.*;");
		out.println("import org.junit.Test;");
		out.println(baseForImportInTestForGradleStructure + className + ";");
		out.println(baseForImportInTestForGradleStructure + interfaceName + ";");
		out.println("");
		out.println("public class " + testName + "{");
		out.println("");
		out.println("\t@Test(timeout = " +Integer.toString(td.TIME_OUT)+")");
		out.println("");
		out.println("\tpublic void test(){");
		out.println("");
		out.println("\t\t//base "+ Integer.toString(base));
		out.println("");
		out.println("\t\tSCalculator sc = new " + className + "();");
		out.println("");
				
		for (int i=0; i<methods.length; i++) 
			out.println(methods[i].toJUnit());
		
		out.println("");
		out.println("}");
		out.println("}");
		out.close();
		
		return path;
		
	}
	
	
	// ------------- generate File using PrintWriter instead of PrintStream ---------- !!!! 
	// ------------- + correct File-Name (it should be = name of class) --------- !!!!
	// ------------- return String = path with the name of the file -------------- !!!!
	// ------------- + static variable for naming -------------------------------- !!!!
	
	private String generateInterface(String filePath) throws FileNotFoundException {
		
		String path = filePath.concat(File.separator).concat(interfaceName).concat(ext);
		
		PrintWriter out = new PrintWriter (path);
		
		StringBuffer res = new StringBuffer("");
		outInterface(res);
		out.println(res.toString());
	
		out.flush();
		out.close();
		
		return path;
		
	}
	
	
	// -------------- Accept StringBuffer. So we can use it for generate String or File --- !!!
	
	private void outInterface (StringBuffer out) {
		
		out.append(packageJavaForGradleStructure).append("\n");
		out.append("\npublic interface " + interfaceName + " {");
		out.append("\n\n\tpublic static final String WRONG = \"Error\";");
		out.append("\n\tpublic static final String DIGITS = \"").append(methods[0].getAlphabet()).append("\";\n\n");
		
		for (int i=0; i<methods.length; i++) out.append(methods[i].toInterface()).append("\n");
		out.append("\n}");		
	}
	
		
	private ArrayList<String> getMethodsList(){		
		ArrayList<String> res = new ArrayList<String>(); 
		
		for (String s : td.methodsList) 
			res.add(s);
		return res;
	}
	
	
	
	private ArrayList<Integer> getTestProgram (int difLevel){
		
		int base;
		ArrayList<Integer> res =  new ArrayList<Integer>();
		
		String s = td.testProgram[difLevel-1];
		String[] data = s.split(",");
		
		if (data[0].equals(td.RANDOM_BASE)) {
			base = td.MIN_BASE + gen.nextInt(td.MAX_BASE-td.MIN_BASE+1);
		}
		else {
			base = Integer.parseInt(data[0]);
		}
		res.add(base);
		
		if (data[1].equals(td.RANDOM_METHOD)) {
			res.add(gen.nextInt(4));
		}
		else {
			for (int i=1; i<data.length; i++) {
				res.add(Integer.parseInt(data[i]));
			}
		}
		
		return res;
	}


//	public void setDescriptionFilePath(String descriptionFilePath) {
//		this.descriptionFilePath = descriptionFilePath;
//	}
//
//	public void setQuestionFilePath(String questionFilePath) {
//		this.questionFilePath = questionFilePath;
//	}
//
//	public void setStubFilePath(String stubFilePath) {
//		this.stubFilePath = stubFilePath;
//	}
//
//	public void setJUnitFilePath(String junitFilePath) {
//		this.junitFilePath = junitFilePath;
//	}
//
//	public void setInterfaceFilePath(String interfaceFilePath) {
//		this.interfaceFilePath = interfaceFilePath;
//	}




	
	
}
