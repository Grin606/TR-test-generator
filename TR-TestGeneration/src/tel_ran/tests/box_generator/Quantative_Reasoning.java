package tel_ran.tests.box_generator;

import tel_ran.tests.exceptions.TasksException;
import tel_ran.tests.generator.numeric.NumComputations;
import tel_ran.tests.generator.numeric.NumEstimations;
import tel_ran.tests.generator.numeric.NumTableTest;
import tel_ran.tests.interfaces.IConstants;
import tel_ran.tests.interfaces.ITestingProblem;
import tel_ran.tests.sequences.ArithmeticSequence;
import tel_ran.tests.sequences.ArithmeticWIncSequence;
import tel_ran.tests.sequences.BakerSequence;
import tel_ran.tests.sequences.CesarSequence;
import tel_ran.tests.sequences.FibonacciSequence;
import tel_ran.tests.sequences.GeometricSequence;
import tel_ran.tests.sequences.PeriodicalSequense;


public class Quantative_Reasoning extends TaskBoxGenerator {
	
		
	public Quantative_Reasoning() throws TasksException {
		super();
		this.numberOfTask = 12;
		tasks = new ITestingProblem[numberOfTask];		
		dirName = IConstants.CATEGORY_DIR_PATHS[IConstants.QUANTATIVE_REASOINING];
		
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
	
	

