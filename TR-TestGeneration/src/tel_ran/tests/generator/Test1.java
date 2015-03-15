package tel_ran.tests.generator;

import tel_ran.tests.tools.RandFunc;

public abstract class Test1 {

	public static void main(String[] args) {
		
		TestingProblemsBox tpb = new TestingProblemsBox();
		Testing_Problem r;
		
		int nProblem = tpb.tpBox.length;
		
		for (int i=0; i < nProblem; i++) {
			
			r = tpb.tpBox[i];
			r.generate(RandFunc.IntRandomInRange(1,5));
			System.out.println("Name: " + r.getName() + ";  Category: " + r.getCategory() + ";   Difficulty level: " +r.getDifLevel() 
					           + ";   Description number: " + r.getNumberOfDescripton());
			System.out.println(r.getODSA_p()[0]);
			for (int j=0; j < r.getODSAlength_a(); j++)
				System.out.print(r.getODSA_a()[j]+ "    ");
			System.out.println("\n");
		}
	}	
  }


