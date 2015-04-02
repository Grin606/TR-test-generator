package tel_ran.tests.numfunctions;

import tel_ran.tests.tools.RandFunc;

public abstract class NFunct {
	
	public int range;
	static int nMin;
	static int nMax;
	
	public abstract int function(int ar);
	public abstract void setDifficultyLevel(int difficultyLevel);
	
	public static int[] makeRandomIntArray(int len) {
		
		int[] res = new int[len];
		
		for (int i=0; i<len; i++) {
			res[i] = RandFunc.IntRandomInRange(nMin, nMax);
			while (isInArray(res,i-1,res[i])) res[i] = RandFunc.IntRandomInRange(nMin, nMax);
		}
		return res;
	}
	
	private static boolean isInArray(int[] ar, int index, int n) {
		
		if (index < 0) return false;
		
		for (int i=0; i<=index; i++) 
			if (ar[i] == n) return true;
			
		return false;
		
	}

}
