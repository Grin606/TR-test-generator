package tel_ran.tests.generator.code_task.calculator.methods;

public class CoPrime extends AbstractMethod implements Actions{

	public CoPrime () {
		super();
		name = "coprime";
		assertComment = "2*2*2*2 and 3*3*3*3 are coprime, 2*2*2*2*3 and 3*3*3*3*2 are not coprime";
		interfaceComment = "Returns \"yes\" or \"no\"";
		stubComment = interfaceComment;
		numArg = 2;
	}

	@Override
	public String toJUnit() {
		
		int a = 2*2*2*2;
		int b = 3*3*3*3;
		int a1 = a*3;
		int b1 = b*2;
		
		return 	N + TT+ CC +name +
				NN + TT + CC+ assertComment + 
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.coprime(\"1\",null));" +
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.coprime(null,\"1\"));" +
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.coprime(\"1\",\"" + NOT_DIGIT + "\"));"+
				N  + ASSERT + "("+ C_WRONG + ","+"sc.coprime(\""+ NOT_DIGIT + "\", \"1\"));"+
				N  + ASSERT + "("+"\"yes\"" + ", sc.coprime(\""+encode(a,base)+"\",\""+ encode(b, base)+"\"));"+
				N  + ASSERT + "("+"\"no\"" + ", sc.coprime(\""+encode(a1,base)+"\",\""+ encode(b1, base)+"\"));";

	}

}
