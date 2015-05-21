package tel_ran.tests.box_generator;

import tel_ran.tests.exceptions.TasksException;
import tel_ran.tests.generator.ITestingProblem;
import tel_ran.tests.generator.code_task.calculator.StringCalculatorCodingTest;

public class Programming_Task extends TaskBoxGenerator {
	
	public static final String category = "Programming_Task";
	
	public Programming_Task() throws TasksException {		
		super();
		this.numberOfTask = 1;
		tasks = new ITestingProblem[numberOfTask];
		cat = category;
		
		int index = 0;
		
			tasks[index++] = new StringCalculatorCodingTest();	
					
		
		if (index == 0) {
			String pr = "No tasks";
			throw new TasksException(pr);
		}
	}


}
