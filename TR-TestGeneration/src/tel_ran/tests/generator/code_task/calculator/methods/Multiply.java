package tel_ran.tests.generator.code_task.calculator.methods;

public class Multiply extends AbstractMethod implements Actions{

	public Multiply () {
		super();
		name = "multiply";
		assertComment = "Integer.MIN_VALUE is hard case for coding to string";
		interfaceComment = "Returns string, representing the product of the arguments";
		stubComment = interfaceComment;
		numArg = 2;
	}
	
	@Override
	public String toJUnit() {
		
		int a = nInt();
		int b = nInt();
		
		return 	N + TT+ CC +name +
				NN + TT + CC+ assertComment + 
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.multiply(\"1\",null));" +
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.multiply(null,\"1\"));" +
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.multiply(\"1\",\"" + NOT_DIGIT + "\"));"+
				N  + ASSERT + "("+ C_WRONG + ","+"sc.multiply(\""+ NOT_DIGIT + "\", \"1\"));"+
				N  + ASSERT + "("+"\"" + encode (a*b, base)+ "\"" + ", sc.multiply(\""+encode(a,base)+"\",\""+ encode(b, base)+"\"));"+
				N  + ASSERT + "("+"\"" + encode (Integer.MIN_VALUE, base)+ "\"" + ", sc.multiply(\""+encode(Integer.MIN_VALUE,base)+"\",\""+ encode(1, base)+"\"));";

	}





}
