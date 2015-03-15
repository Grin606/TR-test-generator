package tel_ran.tests.sequences;

public class NumSequencesBox {
	
	public static final int NUMBER_OF_SEQUENCES = 5;
	
	public NumSequence[] seqBox;
	
	public NumSequencesBox() {
		seqBox = new NumSequence[NUMBER_OF_SEQUENCES];
		
		seqBox[0] = new ArithmeticSequence();
		seqBox[1] = new ArithmeticWIncSequence();
		seqBox[2] = new GeometricSequence();
		seqBox[3] = new FibonacciSequence();
		seqBox[4] = new PeriodicalSequense();
	}
}
