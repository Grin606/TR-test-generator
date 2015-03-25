package tel_ran.tests.generator;

public class TestingProblemsBox {
	
	public static final int numberOfTestingProblems = 6;
	
	public Testing_Problem[] tpBox;
	
	public TestingProblemsBox() {
		
		tpBox = new Testing_Problem[numberOfTestingProblems];
		
		tpBox[0] = new NumComputations();
		tpBox[1] = new NumEstimations();
		tpBox[2] = new NumRandomSequence();
		tpBox[3] = new CharRandomSequence();
		tpBox[4] = new NumTableTest();
		tpBox[5] = new Picture_211E_Test();
	}

}

