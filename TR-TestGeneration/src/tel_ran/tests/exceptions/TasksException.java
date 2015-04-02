package tel_ran.tests.exceptions;

public class TasksException extends Exception {
	
	String problem;

	public TasksException(String problem) {
		super();
		this.problem = problem;
	}
	
	

}
