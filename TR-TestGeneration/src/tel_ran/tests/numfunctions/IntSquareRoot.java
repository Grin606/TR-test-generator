package tel_ran.tests.numfunctions;

public class IntSquareRoot extends NFunct {

	@Override
	public int function(int x) {
		
		return (int)Math.sqrt(x);
	}

	@Override
	public void setDifficultyLevel(int difficultyLevel) {
		
		nMin = 10;
		nMax = 100;
		range = 3;
	}


}
