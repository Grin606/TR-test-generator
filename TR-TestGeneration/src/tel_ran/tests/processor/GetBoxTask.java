package tel_ran.tests.processor;

import tel_ran.tests.box_generator.AbstractBoxGenerator;
import tel_ran.tests.box_generator.AccurateBoxGenerator;
import tel_ran.tests.box_generator.QuantativeBoxGenerator;
import tel_ran.tests.box_generator.TaskBoxGenerator;
import tel_ran.tests.exceptions.TasksException;
import tel_ran.tests.generator.Testing_Problem;

public class GetBoxTask implements TaskGenerate {

	TaskBoxGenerator box;
	
	
	
	public GetBoxTask(int type) throws TasksException {
		switch(type) {
		case 0: box = new AccurateBoxGenerator(); break;
		case 1: box = new QuantativeBoxGenerator(); break;
		case 2: box = new AbstractBoxGenerator(); break;		
		}		
	}


	@Override
	public Testing_Problem getTask(int lvl) {		
		return box.generate(lvl);
	}

}
