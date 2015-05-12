package tel_ran.tests.generator.code_task.calculator.methods;

public class Divide extends AbstractMethod implements Actions{

	public Divide () {
		super();
		name = "divide";
		assertComment = "Integer.MIN_VALUE is hard case for coding to string";
		interfaceComment = "Returns string, representing the result of dividing of op1 by op2";
		stubComment = interfaceComment;
		numArg = 2;
	}

	@Override
	public String toJUnit() {
		
		int a = nInt();
		int b = nInt();
		
		return 	N + TT+ CC +name +
				NN + TT + CC+ assertComment + 
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.divide(\"1\",null));" +
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.divide(null,\"1\"));" +
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.divide(\"1\",\"" + NOT_DIGIT + "\"));"+
				N  + ASSERT + "("+ C_WRONG + ","+"sc.divide(\""+ NOT_DIGIT + "\", \"1\"));"+
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.divide(\"1\",\"0\"));" +
				N  + ASSERT + "("+"\"" + encode (b, base)+ "\"" + ", sc.divide(\""+encode(a*b,base)+"\",\""+ encode(a, base)+"\"));"+
				N  + ASSERT + "("+"\"" + encode (Integer.MIN_VALUE, base)+ "\"" + ", sc.divide(\""+encode(Integer.MIN_VALUE,base)+"\",\""+ encode(1, base)+"\"));";

	}






}
