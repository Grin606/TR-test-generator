package tel_ran.tests.generator.attention;


import java.util.Random;


import tel_ran.tests.exceptions.TasksException;
import tel_ran.tests.generator.Testing_Problem;

public class AttentionNumbersLoop extends AttentionTest {
	
	int numRows;
	int loopLength;
	int numLength;

	

	public AttentionNumbersLoop() {
		super();				
		name = "LongAttentionLines";
		numOfAnswers = 4;			
	}

	@Override
	public void generate(int difficultyLevel)  {
		
		setDifLevel(difficultyLevel);
	
				
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
		int lengthRow = loopLength*numLength;
				
		if (rand.nextInt(2)  == 0)
			oneFalse(problem, lengthRow, num);
		else
			someFalse (problem, lengthRow, num);
		
		setFinalTask(problem);		
		
		
		correctAnswerChar = answerCharSymbols[num];		
		
	}
	
	@Override
	protected void reniewDifficulty() {		
		numOfAnswers = numRows = 4 + level / 4;		
		loopLength = 4 + level / 2;
		numLength = 4 + level / 2;					
	}

	private void someFalse(StringBuffer[] problem, int lengthRow, int num) {
		
		for (int i = 0; i < problem.length; i ++)
			if (i != num)
				changeRow(problem[i], lengthRow);
		numberOfDescripton = 8;
		
	}

	private void oneFalse (StringBuffer[] problem, int lengthRow, int num) {
		changeRow(problem[num], lengthRow);
		numberOfDescripton = 7;
	}
	
	private void changeRow(StringBuffer str, int lengthRow) {
		
		
		int place = rand.nextInt(lengthRow - 5) + 2;		
		char c = str.charAt(place);
		char c2 = str.charAt(place+1);
		str.insert(place, c2);
		str.insert(place+1, c);
		
	}


}
