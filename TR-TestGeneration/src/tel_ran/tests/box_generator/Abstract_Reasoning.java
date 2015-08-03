package tel_ran.tests.box_generator;

import java.util.List;

import tel_ran.tests.exceptions.TasksException;
import tel_ran.tests.generator.ITestingProblem;
import tel_ran.tests.generator.pictures.*;
import tel_ran.tests.interfaces.IConstants;


public class Abstract_Reasoning extends TaskBoxGenerator {
	
	public Abstract_Reasoning() throws TasksException {		
		super();
		this.numberOfTask = 4;
		tasks = new ITestingProblem[numberOfTask];
		dirName = IConstants.CATEGORY_DIR_PATHS[IConstants.ABSTRACT_REASONING];

		
		int index = 0;
		
			tasks[index++] = new Picture_211E_Test();	
			tasks[index++] = new Picture_311F_Test();	
			tasks[index++] = new Picture_AllDifferent_Test();	
			tasks[index++] = new Picture_Roman_Test();	
					

		
		if (index == 0) {
			String pr = "No tasks";
			throw new TasksException(pr);
		}


	}

	@Override
	public int getCategoryIndex() {		
		return IConstants.ABSTRACT_REASONING;
	}



}
