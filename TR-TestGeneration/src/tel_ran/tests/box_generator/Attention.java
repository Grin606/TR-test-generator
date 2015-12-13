package tel_ran.tests.box_generator;

import tel_ran.tests.exceptions.TasksException;
import tel_ran.tests.generator.attention.*;
import tel_ran.tests.interfaces.IConstants;
import tel_ran.tests.interfaces.ITestingProblem;

public class Attention extends TaskBoxGenerator {

	
	public Attention() throws TasksException {
		super();
		this.numberOfTask = 2;
		tasks = new ITestingProblem[numberOfTask];
		dirName = IConstants.CATEGORY_DIR_PATHS[IConstants.ATTENTION];
		
		int index = 0;
		
			tasks[index++] = new AttentionNumbersLoop();		
			tasks[index++] = new AttentionNumTest();	
		
					

		
		if (index == 0) {
			String pr = "No tasks";
			throw new TasksException(pr);
		}

	}

	

}