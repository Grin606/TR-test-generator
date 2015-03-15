package tel_ran.tests.sequences;

import tel_ran.tests.generator.CharRandomSequence;

public class BakerSequence extends CharSequence{
	
	static int WORD_LENGHT=6;
	static int MIX_RANGE = 8;
	
	public static String desc;
	
	public String[] make (int l) {
		
	String[] res = new String[l];

	res[0] = CharRandomSequence.randomWord(WORD_LENGHT);
	for (int i=1; i < l; i++) {
		res[i] = bakerStep(res[i-1]);
	}
	
	desc = "Baker sequence";
	range = MIX_RANGE;

	return res;
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

