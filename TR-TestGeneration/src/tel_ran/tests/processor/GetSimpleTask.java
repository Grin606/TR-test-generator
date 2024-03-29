package tel_ran.tests.processor;

import tel_ran.tests.generator.Testing_Problem;
import tel_ran.tests.interfaces.IGetTaskGenerate;
import tel_ran.tests.interfaces.ITaskView;
import tel_ran.tests.interfaces.ITestingProblem;

public class GetSimpleTask implements IGetTaskGenerate {
	
	ITestingProblem tp;

	
	
	public GetSimpleTask(String name)  {
		super();
		
		Class<?> cl;
		try {
			cl = Class.forName("tel_ran.tests.generator." + name);
			this.tp = (Testing_Problem)cl.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			System.out.println("Incorrect class name. /n Name sholud include all symbols after -- tel_ran.tests.generator. --");			
		}
			
		
	}



	@Override
	public ITestingProblem getTask(int lvl) {	
		tp.generate(lvl);
		return tp;
	}



	@Override
	public String getDirName() {				
		return tp.getCategory2Name();
		
	}



	@Override
	public ITaskView getView() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		return tp.getView();
	
	}

}
