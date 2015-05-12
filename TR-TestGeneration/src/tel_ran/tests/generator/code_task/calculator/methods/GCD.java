package tel_ran.tests.generator.code_task.calculator.methods;

public class GCD extends AbstractMethod implements Actions{

	public GCD () {
		super();
		name = "gcd";
		assertComment = "GCD of 16361*16561 and 16361*16661 is 16361";
		interfaceComment = "Returns string, representing the greatest common divider of the arguments";
		stubComment = interfaceComment;
		numArg = 2;
	}
	
	@Override
	public String toJUnit() {
		
		int c = 16361;
		int a = c*16561;
		int b = c*16661;
		
		
		return 	N + TT+ CC +name +
				NN + TT + CC+ assertComment + 
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.gcd(\"1\",null));" +
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.gcd(null,\"1\"));" +
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.gcd(\"1\",\"" + NOT_DIGIT + "\"));"+
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.gcd(\""+ NOT_DIGIT + "\", \"1\"));"+
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.gcd(\"0\",\"1\"));" +
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.gcd(\"1\",\"0\"));" +
				N  + ASSERT + "("+"\"" + encode (c, base)+ "\"" + ", sc.gcd(\""+encode(a,base)+"\",\""+ encode(b, base)+"\"));";

	}







}