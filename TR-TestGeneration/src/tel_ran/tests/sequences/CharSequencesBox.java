package tel_ran.tests.sequences;

public class CharSequencesBox {
	
public static final int NUMBER_OF_SEQUENCES = 2;
	
	public CharSequence[] seqBox;
	
	public CharSequencesBox() {
		seqBox = new CharSequence[NUMBER_OF_SEQUENCES];
		
		seqBox[0] = new CesarSequence();
		seqBox[1] = new BakerSequence();
	}
}
