package tel_ran.tests.numfunctions;

public class FunctionsBox {
	
	public static final int NUMBER_OF_FUNCTIONS = 4;
	
	public NFunct[] fBox;
	
	public FunctionsBox() {
		
		fBox = new NFunct[NUMBER_OF_FUNCTIONS];
		
		fBox[0] = new IntLinear();
		fBox[1] = new IntSquareRoot();
		fBox[2] = new IntSquarePlusStep();
		fBox[3] = new IntSquareMulty();
	}
}
