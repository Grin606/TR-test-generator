package tel_ran.tests.box_generator;

import tel_ran.tests.exceptions.TasksException;
import tel_ran.tests.generator.ITestingProblem;
import tel_ran.tests.generator.Testing_Problem;
import tel_ran.tests.generator.attention.*;

public class Attention extends TaskBoxGenerator {

	public static final String category = "Attention Test";	
	
	public Attention() throws TasksException {
		super();
		this.numberOfTask = 2;
		tasks = new ITestingProblem[numberOfTask];
		cat = category;
		
		int index = 0;
		
			tasks[index++] = new AttentionNumbersLoop();		
			tasks[index++] = new AttentionNumTest();	
		
					

		
		if (index == 0) {
			String pr = "No tasks";
			throw new TasksException(pr);
		}


	}

}