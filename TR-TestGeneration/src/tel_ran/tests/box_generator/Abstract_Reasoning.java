package tel_ran.tests.box_generator;

import tel_ran.tests.exceptions.TasksException;
import tel_ran.tests.generator.ITestingProblem;
import tel_ran.tests.generator.Testing_Problem;
import tel_ran.tests.generator.pictures.*;


public class Abstract_Reasoning extends TaskBoxGenerator {
	
	public static final String category = "Abstract Reasoning";
	
	public Abstract_Reasoning() throws TasksException {		
		super();
		this.numberOfTask = 4;
		tasks = new ITestingProblem[numberOfTask];
		cat = category;

		
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

}
