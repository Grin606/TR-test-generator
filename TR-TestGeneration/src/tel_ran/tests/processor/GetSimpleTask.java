package tel_ran.tests.processor;

import tel_ran.tests.generator.Testing_Problem;

public class GetSimpleTask implements GetTaskGenerate {
	
	Testing_Problem tp;

	
	
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
	public Testing_Problem getTask(int lvl) {	
		tp.generate(lvl);
		return tp;
	}



	@Override
	public String getDirName() {				
		return tp.getName();
		
	}

}
