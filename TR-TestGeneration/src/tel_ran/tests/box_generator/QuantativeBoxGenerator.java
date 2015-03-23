package tel_ran.tests.box_generator;

import tel_ran.tests.exceptions.TasksException;
import tel_ran.tests.generator.Testing_Problem;
import tel_ran.tests.tasks.quantative.QuantativeComputation;
import tel_ran.tests.tasks.quantative.QuantativeEstimation;


public class QuantativeBoxGenerator extends TaskBoxGenerator {
	
	static final int numberOfMathTask = 5;
	
		
	public QuantativeBoxGenerator(int level) throws TasksException {
		super();
		tasks = new Testing_Problem[numberOfMathTask];
		
		int index = 0;
		
//		if(QuantativeComputation.DIF_LVL == level) 
			tasks[index++] = new QuantativeComputation();
		
//		if(QuantativeEstimation.DIF_LVL == level)
			tasks[index++] = new QuantativeEstimation();	
//		if(true)
//			tasks[index++] = new NumTableTest();
//		if(true)
//			tasks[index++] = new CesarSequence();
//		if(true)
//			tasks[index++] = new BakerSequence();
//
//		
//		tasks[5] = new ArithmeticSequence();
//		tasks[6] = new ArithmeticWIncSequence();
//		tasks[7] = new GeometricSequence();
//		tasks[8] = new FibonacciSequence();
//		tasks[9] = new PeriodicalSequense();// 			
		
		System.arraycopy(tasks, 0, tasks, 0, index+1);
		
		if (index == 0) {
			String pr = "No tasks for difficulty level " + level;
			throw new TasksException(pr);
		}			
		
		this.len = index;
		
	}
	
	

}
	
	

