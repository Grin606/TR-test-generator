package tel_ran.tests.generator.attention;

import java.util.Random;


import tel_ran.tests.generator.Testing_Problem;

public abstract class AttentionTest extends Testing_Problem {
	
	protected Random rand;
	protected int level = 3;

	public AttentionTest() {
		super();
		this.rand = new Random();
		category = "Attention";
		reniewDifficulty();
	}
	
	protected void setDifLevel(int difficultyLevel)  {
		assert difficultyLevel > 0 && difficultyLevel < 6;
		if (difficultyLevel != level) {
			level = difficultyLevel;
			reniewDifficulty();
		}
			
	}
	
	protected void setFinalTask(StringBuffer[] stb) {
		int n = stb.length;
		String[] res = new String[n];
		for(int i = 0; i < n; i++) {
			res[i] = stb[i].toString();
		}
		
		p.setODSA(res);	
		
		
	}

	protected abstract void reniewDifficulty();

}