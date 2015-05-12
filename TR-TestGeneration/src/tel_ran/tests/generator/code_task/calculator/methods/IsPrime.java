package tel_ran.tests.generator.code_task.calculator.methods;

public class IsPrime extends AbstractMethod implements Actions{

	public IsPrime () {
		super();
		name = "isPrime";
		assertComment = "  1073676287 is prime, 26227*27361 is not prime";
		interfaceComment = "Returns \"yes\" or \"no\"";
		stubComment = interfaceComment;
		numArg = 1;
	}
		
	@Override
	public String toJUnit() {
		
		int a = 1073676287;
		int b = 26227*27361;

		return 	N + TT+ CC +name +
				NN + TT + CC+ assertComment + 
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.isPrime(null));" +
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.isPrime(\"" + NOT_DIGIT + "\"));"+
				N  + ASSERT + "("+"\"yes\"" + ", sc.isPrime(\""+ encode(a, base)+"\"));"+
				N  + ASSERT + "("+"\"no\"" + ", sc.isPrime(\""+ encode(b, base)+"\"));";

	}

	
}