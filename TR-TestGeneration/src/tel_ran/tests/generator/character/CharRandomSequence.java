package tel_ran.tests.generator.character;

import tel_ran.tests.tools.*;
public abstract class CharRandomSequence extends CharTest{

	protected int lengthOfSequence = 6;
	
	String[] sequence;
	String sequenceDescription;
	
//	private int range;
	
	public CharRandomSequence() {
		
		super();			
		numberOfDescripton = 4;
		sequence = new String[lengthOfSequence];
		category = "Character Reasoning";
	}
	
	@Override
	public abstract void generate (int difficultyLevel);
//	{
//		
//		if (difficultyLevel < 1 || difficultyLevel > 5) difficultyLevel = 5;
//		
//		difLevel = difficultyLevel;
//
//		int seq = RandFunc.IntRandomInRange(0, NUMBER_OF_SEQUENCES-1);
//		
//		chS = getCharSequence(seq);
//		chS.setDifficultyLevel(difficultyLevel);
//		sequence = chS.make(lengthOfSequence);
//		range = chS.range;	
	public void make (String[] sequence) {
	
		String correctAnswer = sequence[lengthOfSequence-1];
		
		p.setODSA(makeProblem(sequence));
		setCharAnswers(correctAnswer);
	}

		
		private String[] makeProblem(String[] sequence) {
			
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

