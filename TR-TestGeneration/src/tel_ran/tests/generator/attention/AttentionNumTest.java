package tel_ran.tests.generator.attention;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import tel_ran.tests.generator.OneDimStringAnswers;

public class AttentionNumTest extends OneDimStringAnswers {

	public AttentionNumTest() {
		super();
		//weight = NUMBER_OF_TYPES;//???
		numberOfDescripton = 6;
//		category = "Attention";
		category2Name = "NumAttention";
		numOfAnswers = 5;
	}

	@Override
	public void generate(int difficultyLevel) {
		String[][] problem = new String[2][];
		int QU_NUM=7;
		Random rand = new Random();
		StringBuffer str = new StringBuffer();
		int n = 10;
		Set<Integer> set = new HashSet<Integer>();
		
		while (set.size()<QU_NUM){
			set.add(rand.nextInt(n));
		}
		
		for(Integer i:set)
		str.append(i.toString());
		
		int ran1=rand.nextInt(4)+1;
		int ran2=ran1;
		while(ran2==ran1){
			ran2=rand.nextInt(4)+1;
		}
		
		String ch1=str.substring(ran1, ran1+1);
		String ch2=str.substring(ran2, ran2+1);
		
		
		String correct_str =	str.toString();

		str.replace(ran1, ran1+1, ch2);
		str.replace(ran2, ran2+1, ch1);
		String wrong_str =	str.toString();
		String[] questions=new String[5];
		int numOfAnswer = rand.nextInt(5);
		for (int i=0; i<5; i++){
			if(i==numOfAnswer){
				questions[i]=wrong_str;
			}
			else{
				questions[i]=correct_str;
			}
		
		}	
		problem[0]=questions;

		problem[1]=new String[5];
		System.arraycopy(answerCharSymbols, 0, problem[1], 0, 5);

		p.setTDSA(problem);
		
		switch(numOfAnswer){
		case 0: correctAnswerChar="A"; break;
		case 1: correctAnswerChar="B"; break;
		case 2: correctAnswerChar="C"; break;
		case 3: correctAnswerChar="D"; break;
		case 4: correctAnswerChar="E"; break;
		default: assert false;
		}
	
	
	
		
		
		
	}
	
	static void shuffleArray(String[] ar) {
		Random rnd = new Random();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
						String a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	

}

