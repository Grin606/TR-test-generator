package tel_ran.tests.generator;

import tel_ran.tests.dataset.*;
import tel_ran.tests.generator.pictures.Picture_311F_Test;
import tel_ran.tests.pictures.Picture;
import tel_ran.tests.pictures.Table;

public abstract class Test1 {

	public static void main(String[] args) {
		
	Testing_Problem r = new Picture_311F_Test();
	r.generate(0);
	
	Table t;
	System.out.println("Question");
	for (Picture[][] pp: r.p.thp.thpArray) {
		t = new Table(pp);
		t.display();
	}
	System.out.println("Answers");
	for (Picture[][] pp: r.a.thp.thpArray) {
		t = new Table(pp);
		t.display();
	}
	System.out.println(r.correctAnswerChar);
	
	
	
	}
	
	
	public static void showODSA(DataSet p) {
		
		for (String s: p.os.osArray) {
			System.out.print(s + "   ");
		}
		System.out.println();
  }
	
		
	
}
