package tel_ran.tests.generator;

import tel_ran.tests.sequences.*;
import tel_ran.tests.tools.*;
public class NumRandomSequence extends NumTest{
	
	private int NUMBER_OF_SEQUENCES;
	
	private int lengthOfSequence = 6;
	
	private NumSequencesBox numSeq;
	private NumSequence nS;
	private int[] sequence;
	private int questionPosition;
	String sequenceDescription;
	
	private int range;
	
	public NumRandomSequence() {
		
		super();
		
		numSeq = new NumSequencesBox();
		NUMBER_OF_SEQUENCES = NumSequencesBox.NUMBER_OF_SEQUENCES;
		weight = NUMBER_OF_SEQUENCES;	
		numberOfDescripton = 3;
		sequence = new int[lengthOfSequence];
		category = "Numerical Reasoning";
		name = "NumRandomSequence";
	}
	
	@Override
	public void generate(int difficultyLevel) {
		
		if (difficultyLevel < 1 || difficultyLevel > 5) difficultyLevel = 5;
		
		difLevel = difficultyLevel;

		int correctAnswer;
		
		int seq = RandFunc.IntRandomInRange(0, NUMBER_OF_SEQUENCES-1);
		
		nS = numSeq.seqBox[seq];
		nS.setDifficultyLevel(difficultyLevel);
		sequence = nS.make(lengthOfSequence);
		range = nS.range;		
		
		questionPosition = RandFunc.IntRandomInRange(0, lengthOfSequence-1);
		correctAnswer = sequence[questionPosition];
		
		p.setODSA(makeProblem());
		setIntAnswers(correctAnswer, range);
	}

		private String[] makeProblem() {
		
		String res="";
		
		if  (questionPosition == 0)res = "?";
		else res = Integer.toString(sequence[0]);
		for (int i=1; i < lengthOfSequence; i++) {
			res = res.concat("   ");
			if (i != questionPosition) res = res.concat(Integer.toString(sequence[i]));
			else {res = res.concat("?");}
		}
		
		String[] s = new String[1];
		s[0] = res;
		return s;	
	}
	
	public void setLengthOfSequence(int lengthOfSequence) {
			this.lengthOfSequence = lengthOfSequence;
	}
	public int getLengthOfSequence() {
		return lengthOfSequence;
	}
	
	public int[] getSequence() {
		return sequence;
	}
	
	public String getSequenceDescription() {
		return sequenceDescription;
	}
}
