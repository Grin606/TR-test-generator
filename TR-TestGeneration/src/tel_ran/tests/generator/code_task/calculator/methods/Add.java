package tel_ran.tests.generator.code_task.calculator.methods;

public class Add extends AbstractMethod implements Actions{
	
	public Add() {
		super();
		name = "add";
		assertComment = "Integer.MIN_VALUE is hard case for coding to string";
		interfaceComment = "Returns string, representing the sum of the arguments";
		stubComment = interfaceComment;
		numArg = 2;
	}
	


	@Override
	public String toJUnit() {
		
		int a = nInt();
		int b = nInt();
		
		return 	N + TT+ CC +name +
				NN + TT + CC+ assertComment + 
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.add(\"0\",null));" +
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.add(null,\"0\"));" +
				N  + ASSERT + "("+ C_WRONG + ","+"sc.add(\"0\",\"" + NOT_DIGIT + "\"));"+
				N  + ASSERT + "("+ C_WRONG + ","+"sc.add(\""+ NOT_DIGIT + "\", \"0\"));"+
				N  + ASSERT + "("+"\"" + encode (a+b, base)+ "\"" + ", sc.add(\""+encode(a,base)+"\",\""+ encode(b, base)+"\"));"+
				N  + ASSERT + "("+"\"" + encode (Integer.MIN_VALUE, base)+ "\"" + ", sc.add(\""+encode(Integer.MIN_VALUE,base)+"\",\""+ encode(0, base)+"\"));";

	}

}
