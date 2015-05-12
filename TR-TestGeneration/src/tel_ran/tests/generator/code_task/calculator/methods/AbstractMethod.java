package tel_ran.tests.generator.code_task.calculator.methods;

import java.util.Random;

public abstract class AbstractMethod implements Actions{
	
	Random gen = new Random();
	
	static final String T = "\t";
	static final String TT = "\t\t";
	static final String N = "\n";
	static final String NN = "\n\n";
	static final String CC = "//";
	
	static final String OVERRIDE = "\n\t@Override";
	static final String PUBLIC = "public ";
	static final String STRING = "String ";
	static final String[] ARGUMENTS = {"(STRING op1)", "(String op1, String op2)"};
	static final String RETURN = "\n\treturn null;";
	static final String TODO = "\n\t\t// TODO ";
	
	static final String ASSERT = "\t\tassertEquals";
	static final String C_WRONG = "SCalculator.WRONG";
	
	static final String DIGITS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static final String NOT_DIGIT = "@";

	public int base;
	public String alphabet;
	
	static final int TEST_RANGE = 30000;
		
	String name;
	String interfaceComment;
	String stubComment;
	String assertComment;
	int numArg;
	
	@Override
	public void setBase(int b) {
		base = b;
		alphabet = DIGITS.substring(0, base);
	}
	
	@Override
	public String getAlphabet() {
		return alphabet;
	}
	
	@Override
	public String toInterface () {
		
		return T+PUBLIC+STRING+name+ARGUMENTS[numArg-1]+";  " + CC + interfaceComment;
	}
	
	@Override
	public String toStub () {
		
		return  OVERRIDE + N +
				T+PUBLIC+STRING+name+ARGUMENTS[numArg-1]+ "{"+
				TODO + stubComment +
				RETURN +
				N + T + "}";
	}
	
	String encode(int n, int base) {
		
		int dig;
		String digits = alphabet.substring(0, base);
		String res = "";
		
		if (n == 0) return digits.substring(0, 1);
		long num = (n > 0) ? (long)n : - (long)n;
		long b = (long)base;
		
		while (num != 0) {
			
			dig = (int)(num%b);
			res = digits.substring(dig, dig+1) + res;
			num = num/b;
		}
		
		return (n > 0) ? res : "-"+res;
	}
	
	int nInt() {return gen.nextInt()%TEST_RANGE;}


}
