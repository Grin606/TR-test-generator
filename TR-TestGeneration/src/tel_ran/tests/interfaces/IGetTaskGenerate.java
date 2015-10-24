package tel_ran.tests.interfaces;


public interface IGetTaskGenerate {
	
	ITestingProblem getTask(int lvl);
	String getDirName();
	ITaskView getView() throws ClassNotFoundException, InstantiationException, IllegalAccessException;

}
