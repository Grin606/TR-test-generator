package tel_ran.tests.generator.code_task.calculator.methods;

public class Subtract extends AbstractMethod implements Actions{

	public Subtract() {
		super();
		name = "subtract";
		assertComment = "Integer.MIN_VALUE is hard case for coding to string";
		interfaceComment = "Returns string, representing the difference of the arguments";
		stubComment = interfaceComment;
		numArg = 2;
	}

	@Override
	public String toJUnit() {
		
		int a = nInt();
		int b = nInt();
		
		return 	N + TT+ CC +name +
				NN + TT + CC+ assertComment + 
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.subtract(\"0\",null));" +
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.subtract(null,\"0\"));" +
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.subtract(\"0\",\"" + NOT_DIGIT + "\"));"+
				N  + ASSERT + "("+ C_WRONG + ","+"sc.subtract(\""+ NOT_DIGIT + "\", \"0\"));"+
				N  + ASSERT + "("+"\"" + encode (a-b, base)+ "\"" + ", sc.subtract(\""+encode(a,base)+"\",\""+ encode(b, base)+"\"));"+
				N  + ASSERT + "("+"\"" + encode (0, base)+ "\"" + ", sc.subtract(\""+encode(Integer.MIN_VALUE,base)+"\",\""+ encode(Integer.MIN_VALUE, base)+"\"));";

	}



}
