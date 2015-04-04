package tel_ran.tests.generator;

import tel_ran.tests.generator.numeric.*;
import tel_ran.tests.generator.pictures.*;
import tel_ran.tests.sequences.*;

public class TestingProblemsBox {
	
	public static final int numberOfTestingProblems = 14;
	
	public Testing_Problem[] tpBox;
	
	public TestingProblemsBox() {
		
		tpBox = new Testing_Problem[numberOfTestingProblems];
		
		tpBox[0] = new NumComputations();
		tpBox[1] = new NumEstimations();
		tpBox[2] = new ArithmeticSequence();
		tpBox[3] = new ArithmeticWIncSequence();
		tpBox[4] = new GeometricSequence();
		tpBox[5] = new FibonacciSequence();
		tpBox[6] = new PeriodicalSequense();
		tpBox[7] = new CesarSequence();
		tpBox[8] = new BakerSequence();
		tpBox[9] = new NumTableTest();
		tpBox[10] = new Picture_211E_Test();
		tpBox[11] = new Picture_311F_Test();
		tpBox[12] = new Picture_Roman_Test();
		tpBox[13] = new Picture_AllDifferent_Test();
	}

}
