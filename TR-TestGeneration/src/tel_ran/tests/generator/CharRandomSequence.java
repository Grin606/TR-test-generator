package tel_ran.tests.generator;

import tel_ran.tests.sequences.*;
import tel_ran.tests.sequences.CharSequence;
import tel_ran.tests.tools.*;

public class CharRandomSequence extends CharTest{
	
	private int NUMBER_OF_SEQUENCES;

	private int lengthOfSequence = 6;
	
	private CharSequencesBox chSeq;
	private CharSequence chS;
	String[] sequence;
	String sequenceDescription;
	
	private int range;
	
	public CharRandomSequence() {
		
		super();
		
		chSeq = new CharSequencesBox();
		NUMBER_OF_SEQUENCES = CharSequencesBox.NUMBER_OF_SEQUENCES;			
		weight = NUMBER_OF_SEQUENCES;
		numberOfDescripton = 4;
		sequence = new String[lengthOfSequence];
		category = "Character Reasoning";
		name = "CharRandom Sequence";
	}
	
	@Override
	public void generate(int difficultyLevel) {
		
		if (difficultyLevel < 1 || difficultyLevel > 5) difficultyLevel = 5;
		
		difLevel = difficultyLevel;

		int seq = RandFunc.IntRandomInRange(0, NUMBER_OF_SEQUENCES-1);
		
		chS = chSeq.seqBox[seq];
		chS.setDifficultyLevel(difficultyLevel);
		sequence = chS.make(lengthOfSequence);
		range = chS.range;	
		
		correctAnswer = sequence[lengthOfSequence-1];
		
		p.setODSA(makeProblem());
		setCharAnswers(correctAnswer, range);
	}

		
		private String[] makeProblem() {
			
			StringBuffer res = new StringBuffer();
			
			int l1 = lengthOfSequence-1;
			res = res.append(sequence[0]);
			for (int i=1; i < l1; i++) {
				res = res.append("   ");
				res = res.append(sequence[i]);
			}
			res = res.append("   ?");
			
			String[] s = new String[1];
			s[0] = res.toString();
			return s;			
	}
	
		public static String randomWord (int lenWord) {
		
		char c;
		
		StringBuffer init = new StringBuffer();
		for (int i = 0; i < lenWord; i++) {	
			c = (char)(RandFunc.IntRandomInRange(65, 90));
			init = init.append(Character.toString(c));
		}
		
		return (init.toString());
		
		}

		public void setLengthOfSequence(int lengthOfSequence) {
			this.lengthOfSequence = lengthOfSequence;
		}

		public int getLengthOfSequence() {
			return lengthOfSequence;
		}
	
}