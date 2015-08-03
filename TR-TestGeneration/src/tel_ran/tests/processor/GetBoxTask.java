package tel_ran.tests.processor;

import tel_ran.tests.box_generator.TaskBoxGenerator;
import tel_ran.tests.exceptions.TasksException;
import tel_ran.tests.generator.ITestingProblem;
import tel_ran.tests.interfaces.IConstants;
import tel_ran.tests.interfaces.IGetTaskGenerate;

public class GetBoxTask implements IGetTaskGenerate {

	TaskBoxGenerator box;
	
	
	
	public GetBoxTask(String type) throws TasksException, InstantiationException, IllegalAccessException {
		
		int index = getClassIndex(type);		
		Class<?> cl = IConstants.CATEGORY_CLASSES[index];
		box = (TaskBoxGenerator) cl.newInstance();

	}
	
	/**
	 * FOR FUTURE
	 * This constructor should be written for create objects like Programming_Task_Java, Programming_Task_C++ etc
	 * @param type
	 * @param language
	 * @throws TasksException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public GetBoxTask(String type, String language) throws TasksException, InstantiationException, IllegalAccessException {
		
		int index = getClassIndex(type);		
		Class<?> cl = IConstants.CATEGORY_CLASSES[index];
		box = (TaskBoxGenerator) cl.newInstance();

	}
	
	private int getLanguageIndex(String language) {
		int result = -1;
		int size = IConstants.PROGRAM_LANGUAGES.length;
		
		for(int i = 0; i <size; i++) {
			if(IConstants.PROGRAM_LANGUAGES[i].equals(language)) {
				result = i;

			}
		}
		
		return result;
	}
	
	private int getClassIndex(String type) {
		int result = -1;
		boolean flagAction = false;
		int size = IConstants.CATEGORIES.length;
		
		for(int i = 0; i <size; i++) {
			if(IConstants.CATEGORIES[i].equals(type)) {
				result = i;
				flagAction = true;
			}
		}
		
		if(!flagAction) {
			size = IConstants.CATEGORY_DIR_PATHS.length;
			for (int i = 0; i < size; i++) {
				if(IConstants.CATEGORIES[i].equals(type)) {
					result = i;
					flagAction = true;
				}
			}
		}
		
		return result;
	}


	@Override
	public ITestingProblem getTask(int lvl) {		
		return box.generate(lvl);
	}


	@Override
	public String getDirName() {		
		return box.getDirName();
	}


	@Override
	public String getView() {		
		return box.getView();
	}
	
	

}
