package tel_ran.tests.processor;

import tel_ran.tests.generator.Testing_Problem;

public interface GetTaskGenerate {
	
	Testing_Problem getTask(int lvl);
	String getDirName();

}
