package tel_ran.tests.sequences;

import tel_ran.tests.generator.character.CharRandomSequence;
import tel_ran.tests.tools.RandFunc;

public class CesarSequence extends CharRandomSequence{
	
	static int STEP=3;
	static int WORD_LENGHT=6;
	
	public CesarSequence () {
		super();
		category2Name = "Cesar Sequence";
		
	}
	public void generate(int difficultyLevel) {
		
		setDifficultyLevel(difficultyLevel);
		
		int step = RandFunc.IntRandomInRange(1, STEP);
		
		String[] sequence = new String[lengthOfSequence];
			
		sequence[0] = CharRandomSequence.randomWord(WORD_LENGHT);
		for (int i=1; i<lengthOfSequence; i++) {
			sequence[i] = cesarStep(step, sequence[i-1]);
		}
		
		make(sequence);
}
	
	public static String cesarStep(int step, String s) {
		
		int newChar;
		
		StringBuffer res = new StringBuffer();
		for (int j=0; j < s.length(); j++) {
			newChar = (int)(s.charAt(j)+step);
			if (newChar > 90) newChar = 64+(newChar-90);
			if (newChar < 65) newChar = 90-(64 - newChar);
			res = res.append(Character.toString((char)newChar));
		}
		return res.toString();
	}

	public void setDifficultyLevel(int difficultyLevel) {
		
		int[] cesarStep = { 1, 2, 3, 5, 10 };
		int[] cesarWordLenght = { 3, 4, 5, 6, 6 };
		
		STEP = cesarStep[difficultyLevel - 1];
		WORD_LENGHT = cesarWordLenght[difficultyLevel - 1];
	}
}
	
	


