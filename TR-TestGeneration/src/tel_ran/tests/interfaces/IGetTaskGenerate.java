package tel_ran.tests.interfaces;

import tel_ran.tests.generator.ITestingProblem;

public interface IGetTaskGenerate {
	
	ITestingProblem getTask(int lvl);
	String getDirName();
	String getView();

}
