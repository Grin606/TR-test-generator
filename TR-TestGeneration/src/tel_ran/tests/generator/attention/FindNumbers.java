package tel_ran.tests.generator.attention;

import java.util.TreeSet;

import tel_ran.tests.dataset.Frames;

public class FindNumbers extends AttentionTest {
		
	int numRows; // 3 - 5
	int rowLength; // 20 - 30 
	int numNumbers; // 1 - 2	
	String taskDesc;
	

		
	public FindNumbers() {
		super();
		numberOfDescripton = 9;		
		name = "FindNumbers";
		numOfAnswers = 5;	
		
		
	}

	@Override
	public void generate(int difficultyLevel) {
		
		setDifLevel(difficultyLevel);
		
		int[] ranNum = new int[numNumbers];
		int[] check = new int[10];
		
		if (difficultyLevel > 2) {
			check[1] = 1;
			if (difficultyLevel > 4)
				check[4]=check[7]=1;
		}
		
		if (difficultyLevel < 3) {
			check[6] = 1;
		}
		
		//generate random numbers
		
		taskDesc = new String();
		for (int i = 0; i < numNumbers; i ++) {
			int temp = rand.nextInt(10);
			if (check[temp] == 0) {
				ranNum[i] = temp;
				check[temp] = 1;
				taskDesc = taskDesc.concat(Integer.toString(temp));
				
				if (i+1 < numNumbers)
					taskDesc = taskDesc.concat(" and ");
			} else
				i--;				
		}
		
		
		//generate lines of numbers
		StringBuffer[] problem = new StringBuffer[numRows];
		
		for (int j = 0; j < numRows; j ++) {
			problem[j] = new StringBuffer("");
			int i = 0;
			while (i < rowLength) {			
				int temp = rand.nextInt(10);
				if (check[temp] != 1) {
					problem[j].append(temp);
					i++;
				} 
			}
		}
		
		//adding given numbers	
		int ck = 0;
		int[] numNum = new int[numNumbers];
		
		for (int j = 0; j < numRows; j ++) {
			int[] alls = new int[rowLength];
			for (int i = 0; i < numNumbers; i++) {
				int numNumInRow = rand.nextInt(rowLength/3);				
				for (int k = 0; k < numNumInRow; k ++) {
					int place = rand.nextInt(rowLength);
					if (alls[place] != 1) {
						problem[j].replace(place, place+1, Integer.toString(ranNum[i]));
						alls[place] = 1;
						numNum[i] ++;
					} else
						k--;					
				}
				
			}	
			
		}
		
		for (int i = 0; i < numNumbers; i ++)
			ck = ck*31 + numNum[i];
	
		setFinalTask(problem);
		
		//generate correct answer
		
		String[] ans = new String[numOfAnswers];	
		TreeSet<Integer> ckAns = new TreeSet<Integer>();
		
		int num = rand.nextInt(numOfAnswers);		
		ans[num] = setAnswer(numNum);
		ckAns.add(ck);
		this.correctAnswerChar = answerCharSymbols[num];	
		int num2 = rand.nextInt(numOfAnswers-1);
		if (num2 >= num)
			num2++;
		
		ans[num2] = setAnswer(generateWrong(numNum, 1, ckAns));
		//generate incorrect answers
		
		
		
		for (int i = 0; i < numOfAnswers; i ++) {
			if (i != num && i != num2) {
				ans[i] = setAnswer(generateWrong(numNum, rand.nextInt(2) + 2, ckAns));
			}	
		
		}
		
		
		
		a.setODSA(ans);
		a.exv.fr.setFrExternal(Frames.INSIDE);
			

	}
	
	private int[] generateWrong(int[] values, int step, TreeSet<Integer> ck) {
		int[] res = new int[values.length];		
		int checkAns = 0;
		if (step!=1)
			step = step + step * (values[0] / 10);
		
		
			for (int i = 0; i < values.length; i++) {
				int temp;
				
				temp = rand.nextInt(step*2) + step-1;					
				int sign = rand.nextInt(2)*(-2) +1;				
				res[i] = values[i] + sign *temp;
				if (res[i] <= 0 ) {
					i--;
				} else {
					checkAns = checkAns*31 + res[i];
				}
			
			}
		
							
			if(!ck.add(checkAns)) {
				res = generateWrong(values, step, ck);
			
			}
			
			
			
				
		return res;
	}
	
	private String setAnswer(int[] values) {
		StringBuffer stb = new StringBuffer("");
		for (int i = 0; i < values.length; i ++) {
			stb.append(values[i]);
			if (i != values.length-1)
				stb.append(" and ");
		}
		
		return stb.toString();
		
		
	}
	
	public String getDescription() {
		String res = dbox.description[numberOfDescripton] + " " + taskDesc;
		return res;
	}

	@Override
	protected void reniewDifficulty() {
		numRows = 4 + level / 4;
		rowLength = 15 + level*5;
		numNumbers = 1 + level/3;	
		
	}

}
