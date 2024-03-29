package tel_ran.tests.box_generator;

import tel_ran.tests.exceptions.TasksException;
import tel_ran.tests.generator.code_task.html.HtmlCorrectingProblem;
import tel_ran.tests.generator.code_task.java.StringCalculatorCodingTest;
import tel_ran.tests.generator.code_task.javascript.JavaScriptCodingTest;
import tel_ran.tests.interfaces.IConstants;
import tel_ran.tests.interfaces.ITestingProblem;

public class Programming_Task extends TaskBoxGenerator {
		
	public Programming_Task() throws TasksException {		
		super();
		this.numberOfTask = 3;
		tasks = new ITestingProblem[numberOfTask];
		dirName = IConstants.CATEGORY_DIR_PATHS[IConstants.PROGRAMMING_TASKS];
		
		int index = 0;
		
			tasks[index++] = new StringCalculatorCodingTest();	
			tasks[index++] = new HtmlCorrectingProblem();
			tasks[index++] = new JavaScriptCodingTest();
					
		
		if (index == 0) {
			String pr = "No tasks";
			throw new TasksException(pr);
		}
	}

	

}
