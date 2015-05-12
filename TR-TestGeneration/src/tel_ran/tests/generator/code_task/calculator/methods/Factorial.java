package tel_ran.tests.generator.code_task.calculator.methods;

public class Factorial extends AbstractMethod implements Actions{

	public Factorial () {
		super();
		name = "factorial";
		assertComment = " 10!";
		interfaceComment = "Returns string representing factorial of the argument";
		stubComment = interfaceComment;
		numArg = 1;
	}
	
	@Override
	public String toJUnit() {
		
		int a = 10;
		int af = 1*2*3*4*5*6*7*8*9*10;
		

		return 	N + TT+ CC +name +
				NN + TT + CC+ assertComment + 
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.factorial(null));" +
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.factorial(\"" + NOT_DIGIT + "\"));"+
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.factorial(\""+ encode(-1, base)+"\"));" +
				N  + ASSERT + "("+"\""+encode(af,base)+"\"" + ", sc.factorial(\""+ encode(a, base)+"\"))";


	}

	


}
