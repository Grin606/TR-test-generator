package tel_ran.tests.box_generator;

import tel_ran.tests.exceptions.TasksException;
import tel_ran.tests.generator.Testing_Problem;
import tel_ran.tests.generator.attention.*;

public class Attention extends TaskBoxGenerator {

	
	public Attention() throws TasksException {
		super();
		this.numberOfTask = 2;
		tasks = new Testing_Problem[numberOfTask];
		this.category = "Attention Test";
		
		int index = 0;
		
			tasks[index++] = new AttentionNumbersLoop();		
			tasks[index++] = new AttentionNumTest();	
		
					

		
		if (index == 0) {
			String pr = "No tasks";
			throw new TasksException(pr);
		}


	}

}