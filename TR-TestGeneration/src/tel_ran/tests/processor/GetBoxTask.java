package tel_ran.tests.processor;

import tel_ran.tests.box_generator.Abstract_Reasoning;
import tel_ran.tests.box_generator.Attention;
import tel_ran.tests.box_generator.Quantative_Reasoning;
import tel_ran.tests.box_generator.TaskBoxGenerator;
import tel_ran.tests.exceptions.TasksException;
import tel_ran.tests.generator.Testing_Problem;

public class GetBoxTask implements GetTaskGenerate {

	TaskBoxGenerator box;
	
	
	
	public GetBoxTask(int type) throws TasksException {
		switch(type) {
		case 0: box = new Attention(); break;
		case 1: box = new Quantative_Reasoning(); break;
		case 2: box = new Abstract_Reasoning(); break;		
		}		
	}


	@Override
	public Testing_Problem getTask(int lvl) {		
		return box.generate(lvl);
	}


	@Override
	public String getDirName() {		
		return box.getClass().toString().substring(34);
	}
	
	

}
