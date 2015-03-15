package tel_ran.tests.tools;


public class ArrayFun {
	
	public static String[] shuffle(String[] ar) {
		
		final int SHUFFLE_COEFF = 2;
		
		int i1, i2;
		String tt;
		
		int arL = ar.length;
		String[] res = new String[arL];
		System.arraycopy(ar, 0, res, 0, arL);
		
		int arL1 = arL-1;
		int arL2 = arL*SHUFFLE_COEFF;
		for (int i=0; i<arL2; i++) {
			
			i1 = RandFunc.IntRandomInRange(0, arL1);
			i2 = RandFunc.IntRandomInRangeExept(0, arL1, i1);
			
			tt = res[i1];
			res[i1] = res[i2];
			res[i2] = tt;	
		}
		 return res;
	}
	
	public static void displayArray (int[] ar) {
		
		int j10=0;
		
		int nStr = ar.length/10+1;
		for (int j=0; j < nStr-1; j++) {
			j10 = j*10;
			for (int i=0; i < 10; i++) {
				System.out.printf("%10d", ar[j10+i]);
			}
			System.out.println();
		}
		for (int i=(nStr-1)*10; i < ar.length; i++) {
			System.out.printf("%10d", ar[i]);
		}
		System.out.println();
	}


}