package tel_ran.tests.box_generator;

import tel_ran.tests.exceptions.TasksException;
import tel_ran.tests.generator.Testing_Problem;
import tel_ran.tests.generator.numeric.NumComputations;
import tel_ran.tests.generator.numeric.NumEstimations;
import tel_ran.tests.generator.numeric.NumTableTest;
import tel_ran.tests.sequences.ArithmeticSequence;
import tel_ran.tests.sequences.ArithmeticWIncSequence;
import tel_ran.tests.sequences.BakerSequence;
import tel_ran.tests.sequences.CesarSequence;
import tel_ran.tests.sequences.FibonacciSequence;
import tel_ran.tests.sequences.GeometricSequence;
import tel_ran.tests.sequences.PeriodicalSequense;


public class QuantativeBoxGenerator extends TaskBoxGenerator {
	
	
		
	public QuantativeBoxGenerator() throws TasksException {
		super();
		this.numberOfTask = 12;
		tasks = new Testing_Problem[numberOfTask];
		
		int index = 0;
		
			tasks[index++] = new NumComputations();		
			tasks[index++] = new NumEstimations();	
			tasks[index++] = new ArithmeticSequence();
			tasks[index++] = new ArithmeticWIncSequence();
			tasks[index++] = new GeometricSequence();	
			tasks[index++] = new FibonacciSequence();
			tasks[index++] = new PeriodicalSequense();
			tasks[index++] = new CesarSequence();
			tasks[index++] = new BakerSequence();	
			tasks[index++] = new NumTableTest();
			tasks[index++] = new NumEstimations();	
			tasks[index++] = new NumEstimations();	
					

		
		if (index == 0) {
			String pr = "No tasks";
			throw new TasksException(pr);
		}			
		
	
		
	}		

}
	
	

