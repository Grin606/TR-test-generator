package tel_ran.tests.generator.code_task.calculator.methods;

public class Cube extends AbstractMethod implements Actions{

	public Cube () {
		super();
		name = "cube";
		assertComment = "";
		interfaceComment = "Returns string, representing the cube of argument";
		stubComment = interfaceComment;
		numArg = 1;
	}
	
	@Override
	public String toJUnit() {
		
		int a = 1234;
		
		return 	N + TT+ CC +name +
				NN  + ASSERT + "("+ C_WRONG + ","+ "sc.cube(null));" +
				N  + ASSERT + "("+ C_WRONG + ","+ "sc.cube(\"" + NOT_DIGIT + "\"));"+
				N  + ASSERT + "("+"\"" + encode (a*a*a, base)+ "\"" + ", sc.cube(\""+ encode(a, base)+"\"));";


	}


}
