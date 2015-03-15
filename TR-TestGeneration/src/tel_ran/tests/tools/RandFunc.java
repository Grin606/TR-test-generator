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
	public static double DoubleRandomInRange (double a, double b) {
		return a + Math.random()*(b-a);
	}

}
