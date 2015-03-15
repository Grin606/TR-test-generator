package tel_ran.tests.numfunctions;

public class IntBynaryLog extends NFunct {

	@Override
	public int function(int x) {
		
		if (x < 1) return 0; 
		
		int counter = 0;
		while (x >= 1) {
			x /= 2;
			counter++;
		}
		return counter;
	}

	@Override
	public void setDifficultyLevel(int difficultyLevel) {
		range = 5;
		nMin = 1;
		nMax = 50;
	}

}