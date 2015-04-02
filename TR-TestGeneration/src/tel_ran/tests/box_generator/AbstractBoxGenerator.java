package tel_ran.tests.box_generator;

import tel_ran.tests.exceptions.TasksException;
import tel_ran.tests.generator.Testing_Problem;
import tel_ran.tests.generator.pictures.Picture_211E_Test;
import tel_ran.tests.generator.pictures.Picture_311F_Test;


public class AbstractBoxGenerator extends TaskBoxGenerator {
	
	
	public AbstractBoxGenerator() throws TasksException {		
		super();
		this.numberOfTask = 2;
		tasks = new Testing_Problem[numberOfTask];
		
		int index = 0;
		
			tasks[index++] = new Picture_211E_Test();	
			tasks[index++] = new Picture_311F_Test();	
					

		
		if (index == 0) {
			String pr = "No tasks";
			throw new TasksException(pr);
		}


	}

}
