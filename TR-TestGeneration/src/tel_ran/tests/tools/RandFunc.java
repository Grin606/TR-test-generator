package tel_ran.tests.tools;


public class RandFunc {
	
	public static int IntRandomInRange(int nMin, int nMax) {
		return nMin + (int)(Math.random()*(nMax-nMin+1));
	}
	public static int IntRandomInRangeExept(int nMin, int nMax, int exept) {
		int res = nMin + (int)(Math.random()*(nMax-nMin+1));
			while (res == exept) {
				res = nMin + (int)(Math.random()*(nMax-nMin)+0.5);
			}
		return res;
	}
	public static int SignRandom() {
		if (Math.random() >= 0.5) return 1;
		else return -1;
	}
	public static boolean RandomYesOrNo() {
		if (Math.random() >= 0.5) return true;
		else return false;
	}
	
	public static int getRandomOfTwo(int first, int second) {
		 double r = Math.random();
		 if (r < 0.5) return first;
		 else return second;
	}
	
	public static int getRandomOfThree(int first, int second, int third) {
		 double r = Math.random();
		 if (r < 0.3333333) return first;
		 if (r < 0.6666666) return second;
		 return third;
	}
	
	public static double DoubleRandomInRange (double a, double b) {
		return a + Math.random()*(b-a);
	}

}
