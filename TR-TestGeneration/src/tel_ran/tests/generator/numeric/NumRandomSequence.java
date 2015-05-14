package tel_ran.tests.generator.numeric;

import tel_ran.tests.tools.*;
public abstract class NumRandomSequence extends NumTest{
	
//	private int NUMBER_OF_SEQUENCES = 6;
	
	protected int lengthOfSequence = 6;
	
	protected int[] sequence;
	private int questionPosition;
	
	public NumRandomSequence() {
		
		super();
		
//		weight = NUMBER_OF_SEQUENCES;	
		numberOfDescripton = 3;
		sequence = new int[lengthOfSequence];
		category = "Numerical Reasoning";
	}
	@Override
	public abstract void generate(int difficultyLevel);
	
		
		public void make(int[] sequence, int range) {
		
		questionPosition = RandFunc.IntRandomInRange(0, lengthOfSequence-1);
		int corrAnswer = sequence[questionPosition];
		
		p.setODSA(makeProblem(sequence));
		setIntAnswers(corrAnswer, range);
	}

		private String[] makeProblem(int[] sequence) {
		
		StringBuffer res= new StringBuffer("");
		
		if  (questionPosition == 0)res.append("?");
		else res.append(Integer.toString(sequence[0]));
		for (int i=1; i < lengthOfSequence; i++) {
			res = res.append("   ");
			if (i != questionPosition) res = res.append(Integer.toString(sequence[i]));
			else {res = res.append("?");}
		}
		
		String[] s = new String[1];
		s[0] = res.toString();
		return s;	
	}
			
	public void setLengthOfSequence(int lengthOfSequence) {
			this.lengthOfSequence = lengthOfSequence;
	}
	public int getLengthOfSequence() {
		return lengthOfSequence;
	}	
}
