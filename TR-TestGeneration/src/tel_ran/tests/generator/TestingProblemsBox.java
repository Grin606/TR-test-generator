package tel_ran.tests.generator;

public class TestingProblemsBox {
	
	public static final int numberOfTestingProblems = 5;
	
	Testing_Problem[] tpBox;
	
	public TestingProblemsBox() {
		
		tpBox = new Testing_Problem[numberOfTestingProblems];
		
		tpBox[0] = new NumComputations();
		tpBox[1] = new NumEstimations();
		tpBox[2] = new NumRandomSequence();
		tpBox[3] = new CharRandomSequence();
		tpBox[4] = new NumTableTest();
	}

}
