package tel_ran.tests.generator;

import tel_ran.tests.dataset.DataSet;
import tel_ran.tests.pictures.Table;


public abstract class Test1 {

	public static void main(String[] args) {
		
	Testing_Problem r;
	TestingProblemsBox tpb = new TestingProblemsBox();
	
	r = tpb.tpBox[5];
	
	int dL = 1;
	r.generate(dL);
	
	System.out.println("Question");
	System.out.println("Category: "+ r.category);
	showDataSet(r.p);
	System.out.println("Answers");
	showDataSet(r.a);
	System.out.println(r.correctAnswerChar);
	}
	
	public static void showDataSet(DataSet ds) {
		
		Table t;
		int deep = ds.thp.deep;
		
		for (int i=0; i<deep; i++) {
			t = new Table(ds.thp.thpArray[i]);
			t.display();
		}
  }
}
