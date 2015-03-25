package tel_ran.tests.attention;


import java.util.Random;


import tel_ran.tests.generator.Testing_Problem;

public class AttentionNumbersLoop extends Testing_Problem {

	public AttentionNumbersLoop() {
		super();
		//weight = NUMBER_OF_TYPES;//???
		numberOfDescripton = 6;
		category = "Attention";
		name = "NumberLoops";
	}

	@Override
	public void generate(int difficultyLevel) {
				
		int numRows = 4;
		int loopLength = 5;
		int numLength = 5;
				
		StringBuffer[] problem = new StringBuffer[numRows];
		Random rand = new Random();
		StringBuffer str;
		int[] check = new int[10];
//		int n = 10;
		
//		Set<Integer> set = new HashSet<Integer>();
		
		for (int j = 0; j < numRows; j++) {
			
			str = new StringBuffer("");			
			for (int i = 0; i < loopLength; i++) {
				int num = rand.nextInt(10);
				assert num >=0 && num < 10;
				
				if (check[num] < 2) {
					check[num]++;
					str.append(num);	
				} else {
					i--;
				}				
				
			}
			problem[j] = new StringBuffer("");		
			problem[j].append(answerCharSymbols[j]).append(") ");
			for (int i = 0; i < numLength; i++) {
				problem[j].append(str);
			}
		}
		
		int num = rand.nextInt(numRows);
		int place = rand.nextInt(loopLength*numLength - 5) + 2;		
		char c = problem[num].charAt(place);
		char c2 = problem[num].charAt(place+1);
		problem[num].insert(place, c2);
		problem[num].insert(place+1, c);
		
		String[] res = new String[numRows];
		for(int i = 0; i < numRows; i++) {
			res[i] = problem[i].toString();
		}
		
		p.setODSA(res);
		
		correctAnswerChar = answerCharSymbols[num];		
		
	}


}
