package tel_ran.tests.generator.code_task.java;

import java.io.*;
import java.util.*;

import tel_ran.tests.generator.code_task.calculator.methods.Actions;



public class StringCalculatorCodingTest extends JavaTestingProblem {
	
	private Actions[] methods;
	private int base;
	private TestData td;
		
					
	Random gen = new Random();
		
	public StringCalculatorCodingTest() {
		super();
		category2Name = "Calculator";  /* -- new line -- */
		testName = "SCalculator_Test";
		interfaceName = "SCalculator";
		className = "StringCalculator";
	}
	
	@Override
	public void generate(int difLevel) {
		codeFiles = new LinkedList<String>();		
		createDir(0); /* ---  create temporary folder for files  --- */
		
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
	
	
	// ------------- generate STRING instead of FILE ---------- !!!! 
	
	
	protected void generateQuestion() {
		
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
	
	
	protected void generateStub() {
		
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
	

	protected String generateJUnit(String filePath) throws FileNotFoundException {
		
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
	
	protected String generateInterface(String filePath) throws FileNotFoundException {
		
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
		
	
}
