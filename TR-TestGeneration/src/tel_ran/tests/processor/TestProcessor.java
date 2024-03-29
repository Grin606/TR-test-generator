package tel_ran.tests.processor;


import java.util.ArrayList;
import java.util.List;

import tel_ran.tests.interfaces.IConstants;
import tel_ran.tests.interfaces.IGetTaskGenerate;
import tel_ran.tests.interfaces.ITaskView;
import tel_ran.tests.interfaces.ITestingProblem;
import tel_ran.tests.repository.QuestionsRepository;
import tel_ran.tests.utils.files.FileSaver;
import tel_ran.tests.utils.files.FileService;

/** Main class and interface for generation of test tasks.**/
public class TestProcessor {
	
	
	public static String MC_PROGRAMMING = IConstants.CATEGORIES[IConstants.PROGRAMMING_TASKS];
	public static String MC_ABSTRACT = IConstants.CATEGORIES[IConstants.ABSTRACT_REASONING];
	public static String MC_ATTENTION = IConstants.CATEGORIES[IConstants.ATTENTION];
	public static String MC_QUANTATIVE = IConstants.CATEGORIES[IConstants.QUANTATIVE_REASOINING];
	
	/** Collection of generation results. **/ 
//	QuestionsRepository rep;
	
	
//	String dev = "----";
	
	
	/** default constructor **/
	public TestProcessor() {
//		this.rep = new QuestionsRepository();        
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

	
	/** 
	 *  Test generation method for Programming Tasks.
	 *  It receives 2 category: MetaCategry (must be Programming Task) and Category = ProgrammingLanguage
	 *  
	 *  It runs questions creation for one given meta-category.
	 *  It receives the short name of meta-category and returns List<String[]>
	 *  with questions (their description, text, links to files, correct answers etc.). 
	 *  The correct short names can be received from TestProcessor-constants or from method getMetaCategory.
	 *  
	 *  testName - the short name of meta-category
	 *  number - number of questions to generate 
	 *  path - path for files to save
	 *  maxLvl - maximum difficulty level (1 - 5). It will generate questions from level 1 to this level
	 *  **/
	public List<String[]> processStart(String testType, String progLanguage, int number, String path, int maxLvl) throws Exception {
		
		if(!testType.equals(IConstants.CATEGORIES[IConstants.PROGRAMMING_TASKS]))
			return null;
		
		IGetTaskGenerate tg = new GetBoxTask(testType, progLanguage); 
		
		return processing(tg, number, path, maxLvl);		
	}
	
	// the inner basic method for questions generation
	//
	private List<String[]> processing (IGetTaskGenerate taskGen, int number, String path, int maxLvl) throws Exception {
						
		// creation of folder
		FileService fileService = createFolders(taskGen.getDirName(), path);
							
		// generate class for type of question view. It can be presented as a picture or as a code-text.		
		QuestionsRepository rep = generateQuestionsByLevel(taskGen, number, maxLvl, fileService); 
		
		// difficulty level
		
						
		return rep.getList();
	}
	
	private QuestionsRepository generateQuestionsByLevel(IGetTaskGenerate taskGen, int number, int maxLvl,
			FileService fileService) {
		//variables of the method
		
		QuestionsRepository rep = new QuestionsRepository();   
		
		int checkedMaxLevel = checkMaxLevel(maxLvl);
		int step = calculateStep(checkedMaxLevel, number);
		int th = step;
		int lvl = 1;
		int errorCounter = 0;
		
		for (int i = 0; i < number; i++) {
			
			
			if (i == th) {
				lvl++;
				th += step;
			}
				
			String[] dsc = null;
			try {
				dsc = getNewTask(lvl, taskGen, fileService);
			} catch (Exception e) {				
				e.printStackTrace();
				errorCounter++;
			} 
									
			if (dsc == null) {
				i--;
				if(errorCounter>10) break;
			} else {
				String[] result = new String[dsc.length];
				System.arraycopy(dsc, 0, result, 0, dsc.length);				
				rep.addQuestion(result);
			}
		}
		return rep;
	}


	private String[] getNewTask(int lvl, IGetTaskGenerate taskGen, FileService fileService) throws Exception {
		ITestingProblem testTask = taskGen.getTask(lvl); 		
		ITaskView taskView = testTask.getView();
		taskView.setFileService(fileService);								
		return taskView.getTaskViews(testTask, lvl);			
	}


	private int calculateStep(int checkedMaxLevel, int number) {
		int step = number/checkedMaxLevel + 1;
		if (number%checkedMaxLevel == 0)
			step--;	
		
		return step;
	}


	private int checkMaxLevel(int maxLvl) {
		if (maxLvl > 5)
			maxLvl = 5;
		if (maxLvl < 1)
			maxLvl = 1;

		return maxLvl;
	}


	private FileService createFolders(String dirName, String path) {	
		FileService fileService = new FileSaver();
		fileService.setPath(path);
		fileService.setFolderName(dirName);
		
		return fileService;
	}


	/** List of the meta categories. Returns List<String> **/ 
	public static List<String> getMetaCategory() {
		List<String> result = new ArrayList<String>();
		for (String str : IConstants.CATEGORIES)
			result.add(str);
		return result;
	}
	
	
	/** List of the languages for Programming Task. Returns List<String> **/ 
	public static List<String> getProgrammingLanguagesList() {
		List<String> result = new ArrayList<String>();
		for (String str : IConstants.PROGRAM_LANGUAGES)
			result.add(str);
		return result;
	}
	
	/**
	 * List of the inner categories (1 level = program.lang) by MetaCategories
	 * @param metaCategory
	 * @return
	 */
	public static List<String> getCategoriesList(String metaCategory) {
		
		if(metaCategory.equals(TestProcessor.MC_PROGRAMMING) || metaCategory.equals(IConstants.CATEGORY_DIR_PATHS[IConstants.PROGRAMMING_TASKS])) {
			return getProgrammingLanguagesList();			
		} else {
			return null;
		}

	}
	
	
	/**
	 * Return the unique name of metaCategory for old and new versions of Test-Generator Project
	 * This name won't be changed and translated. It is used for directory creation when they saved 
	 * pictures or other files for test questions.
	 * It can be used also for beans.  
	 * @param metaCategory = title-name (public) of metaCategory.
	 * @return
	 */
	public static String getMetaCategoryKeyByPublicName(String metaCategory) {
		int index = GetBoxTask.getClassIndex(metaCategory);
		return IConstants.CATEGORY_DIR_PATHS[index];		
		
	}
		
	



}
