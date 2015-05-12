package tel_ran.tests.generator.code_task.calculator;

import java.io.*;
import java.util.*;

import tel_ran.tests.generator.code_task.CodeTestingProblem;
import tel_ran.tests.generator.code_task.calculator.methods.Actions;
import tel_ran.tests.repository.QuestionsRepository;



public class StringCalculatorCodingTest extends CodeTestingProblem {
	
	private Actions[] methods;
	private int base;
	private TestData td;
	
	private static final String testName = "SCalculator_Test";
	private static final String interfaceName = "SCalculator";
	
	
	
	
//	String descriptionFilePath; --- we don't generate description. we write it into DescriptionBox
//								--- and specify its number
//	String questionFilePath; --- we don't need a file. We use here the variable String questionText form super-class
//	String stubFilePath; ---  we don't need a file. We use here the variable String stubText form super-class
//	String junitFilePath; --- all files has the same start path - temporary folder (its name specified in super-class
//	String interfaceFilePath; 
		
	Random gen = new Random();
	
	
	
	public StringCalculatorCodingTest() {
		super();
		name = "Calculator";  /* -- new line -- */
		this.numberOfDescripton = 10;   /* -- new line -- */
	}


	
		
	public void generateTest(int difLevel) {
		
		getTestData(difLevel);
//		generateDescription(descriptionFilePath);
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
	
	
	private String generateReadme(String filePath) throws FileNotFoundException {
		
		String path = filePath.concat(File.separator).concat(this.readmeFileName);
		
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
		
		res.append("Write the public class StringCalculator using the ").append(Integer.toString(base)).
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
		
		res.append("public class StringCalculator implements SCalculator {\n").
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
				
		out.println("import static org.junit.Assert.*;\n"
				+ "import org.junit.Test;\n\n"
				+ "public class " + testName + " {"
				+ "\n\n\t@Test(timeout = " +Integer.toString(td.TIME_OUT)+")"
				+ "\n\tpublic void test(){\n"
				+ "\n\t\t//base "+ Integer.toString(base) 
				+ "\n\n\t\tSCalculator sc = new StringCalculator();"); 
		
		for (int i=0; i<methods.length; i++) 
			out.println(methods[i].toJUnit());
		
		out.println("\n}");
		out.println("\n}");
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
