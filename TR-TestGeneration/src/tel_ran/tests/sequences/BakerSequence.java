package tel_ran.tests.sequences;

import tel_ran.tests.generator.character.CharRandomSequence;

public class BakerSequence extends CharRandomSequence{
	
	static int WORD_LENGHT=6;
	static int MIX_RANGE = 8;
	
	public BakerSequence () {
		super();
		category2Name = "Cesar Sequence";
		
	}
	public void generate(int difficultyLevel) {
		
		setDifficultyLevel(difficultyLevel);
		
		String[] sequence = new String[lengthOfSequence];
			
		sequence[0] = CharRandomSequence.randomWord(WORD_LENGHT);
		for (int i=1; i<lengthOfSequence; i++) {
			sequence[i] = bakerStep(sequence[i-1]);
		}
		
		make(sequence);
}
	
	public String bakerStep(String s) {
		
		StringBuffer res = new StringBuffer();
		int i=0;
		int j = s.length()-1;
		while (i<j) {
			res = res.append(Character.toString(s.charAt(j)));
			res = res.append(Character.toString(s.charAt(i)));
			i++;
			j--;
			
		}
		if (i == j) res=res.append(Character.toString(s.charAt(i)));
		return res.toString();
	}

	public void setDifficultyLevel(int difficultyLevel) {
		
		int[] bakerWordLenght = {3, 4, 6, 6, 6 };
		
		WORD_LENGHT = bakerWordLenght[difficultyLevel - 1];
	}
}
