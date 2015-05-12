package tel_ran.tests.generator.code_task.calculator.methods;

public class Square extends AbstractMethod implements Actions{

	public Square () {
		super();
		name = "square";
		assertComment = "Squares of Integer.MIN_VALUE and Integer.MAX_VALUE are interesting";
		interfaceComment = "Returns string, representing the square of argument";
		stubComment = interfaceComment;
		numArg = 1;
	}
	
	@Override
	public String toJUnit() {
		
		int a = nInt();
		
		return 	N + TT+ CC +name +
				NN + TT + CC+ assertComment + 
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.square(null));" +
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.square(\"" + NOT_DIGIT + "\"));"+
				N  + ASSERT + "("+"\"" + encode (a*a, base)+ "\"" + ", sc.square(\""+ encode(a, base)+"\"));"+
				N  + ASSERT + "("+"\"" + encode (1, base)+ "\"" + ", sc.square(\""+encode(Integer.MAX_VALUE,base)+"\"));"+
				N  + ASSERT + "("+"\"" + encode (0, base)+ "\"" + ", sc.square(\""+encode(Integer.MIN_VALUE,base)+"\"));";


	}
	
}
