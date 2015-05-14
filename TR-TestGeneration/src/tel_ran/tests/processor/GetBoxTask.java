package tel_ran.tests.processor;

import tel_ran.tests.box_generator.MetaCategory;
import tel_ran.tests.box_generator.TaskBoxGenerator;
import tel_ran.tests.exceptions.TasksException;
import tel_ran.tests.generator.ITestingProblem;

public class GetBoxTask implements IGetTaskGenerate {

	TaskBoxGenerator box;
	
	
	
	public GetBoxTask(String type) throws TasksException, InstantiationException, IllegalAccessException {
		
		Class<?> cl = MetaCategory.getClass(type);
		box = (TaskBoxGenerator) cl.newInstance();

	}


	@Override
	public ITestingProblem getTask(int lvl) {		
		return box.generate(lvl);
	}


	@Override
	public String getDirName() {		
		return box.getCategory();
	}


	@Override
	public String getView() {		
		return box.getView();
	}
	
	

}
