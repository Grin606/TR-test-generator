package tel_ran.tests.processor;


import java.io.File;
import java.util.List;

import tel_ran.tests.box_generator.Abstract_Reasoning;
import tel_ran.tests.box_generator.Attention;
import tel_ran.tests.box_generator.MetaCategory;
import tel_ran.tests.box_generator.Programming_Task;
import tel_ran.tests.box_generator.Quantative_Reasoning;
import tel_ran.tests.generator.*;
import tel_ran.tests.generator.code_task.CodeTestingProblem;
import tel_ran.tests.repository.QuestionsRepository;

/** Main class and interface for generation of test tasks.**/
public class TestProcessor {
	
	//These constants are designed to simplify the choice of the task's type outside 
	
	/**Name for meta-category of attention tasks. Used in generation of tasks (method Process Start) **/
	public static final String ATTENTION = Attention.category;	
	
	/**Name for meta-category of quantative tasks. Used in generation of tasks (method Process Start) **/
	public static final String QUANTATIVE_REASONING = Quantative_Reasoning.category;	
	
	/**Name for meta-category of abstract (pictures) tasks. Used in generation of tasks (method Process Start) **/
	public static final String ABSTRACT_REASONING = Abstract_Reasoning.category;
	
	/**Name for meta-category of code tasks. Used in generation of tasks (method Process Start) **/
	public static final String PROGRAMMING = Programming_Task.category;
	
	public static final String README_FILE = CodeTestingProblem.readmeFileName;
	
	/** Collection of generation results. **/ 
	QuestionsRepository rep;
	
	
//	String dev = "----";
	
	
	/** default constructor **/
	public TestProcessor() {
		this.rep = new QuestionsRepository();        
	}

	
	/** Test generation method. It runs only one given category (not meta-category).
	 *  It receives the full name of needed class (with package) and returns List<String[]>
	 *  with questions (their description, text, links to files, correct answers etc.).
	 *  testName - the full name of class to test
	 *  number - number of questions to generate 
	 *  path - path for files saving
	 *  maxLvl - maximum difficulty level (1 - 5). It will generate questions from level 1 to this level
	 *  **/
	public List<String[]> testProcessStart(String testName, int number, String path, int maxLvl) throws Exception {
		IGetTaskGenerate tg = new GetSimpleTask(testName);
		return processing(tg, number, path, maxLvl);		
	}
	
	
	/** Main generation method. It runs questions creation for one given meta-category.
	 *  It receives the short name of meta-category and returns List<String[]>
	 *  with questions (their description, text, links to files, correct answers etc.). 
	 *  The correct short names can be received from TestProcessor-constants or from method getMetaCategory.
	 *  
	 *  testName - the short name of meta-category
	 *  number - number of questions to generate 
	 *  path - path for files to save
	 *  maxLvl - maximum difficulty level (1 - 5). It will generate questions from level 1 to this level
	 *  **/
	public List<String[]> processStart(String testType, int number, String path, int maxLvl) throws Exception {
				
		IGetTaskGenerate tg = new GetBoxTask(testType); 
		
		return processing(tg, number, path, maxLvl);		
	}

	// the inner basic method for questions generation
	//
	private List<String[]> processing (IGetTaskGenerate taskGen, int number, String path, int maxLvl) throws Exception {
		
		//variables of the method
		ITestingProblem testTask = null;		
		String[] dsc;
		
		
		// creation of folder
		String dirName = taskGen.getDirName();		
		String newPath = path.concat(dirName);	
		
		if(!new File(newPath).exists() && !new File(newPath).mkdir()) {
				System.out.println("Creating directory " + newPath + " failed");
				dirName = "";
		}
			else {
				path = newPath.concat(File.separator);
				dirName = File.separator.concat(dirName);
			}
					
		// generate class for type of question view. It can be presented as a picture or as a code-text.		
		Class<?> cl = Class.forName(taskGen.getView());
		ITaskView taskView = (ITaskView) cl.newInstance();
		taskView.setPath(newPath, dirName);
		

		// difficulty level
		if (maxLvl > 5)
			maxLvl = 5;
		if (maxLvl < 1)
			maxLvl = 1;

		int step, lvl = 1;
		step = number/maxLvl + 1;
		if (number%maxLvl == 0)
			step--;	
		int th = step;
				
		for (int i = 0; i < number; i++) {
			
			if (i == th) {
				lvl++;
				th += step;
			}
						
			testTask = taskGen.getTask(lvl); 
								
			dsc = taskView.getTaskViews(testTask, lvl);			
			if (dsc == null)
				i--;
			else
				rep.addQuestion(dsc);							
		}
						
		return rep.getList();
	}
	
	/** List of the meta categories. Returns List<String> **/ 
	public static List<String> getMetaCategory() {
		return MetaCategory.getMetaCategory();
	}
	
	



}
