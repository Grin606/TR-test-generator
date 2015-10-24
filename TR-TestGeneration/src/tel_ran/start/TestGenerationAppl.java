package tel_ran.start;

import java.util.*;

import tel_ran.tests.interfaces.IConstants;
import tel_ran.tests.processor.TestProcessor;

public class TestGenerationAppl {
	
	public static final String TYPE = IConstants.CATEGORIES[IConstants.PROGRAMMING_TASKS];
	public static final int NUMB = 10;
	public static final String PTH = "d:\\res\\";
	public static final int DIF_LEVEL = 5;
//	static Scanner reader = new Scanner(System.in);
	
	
	
	public static void main(String[] args) throws Exception {	
       
       String type = TYPE;
       int num = NUMB;       
       
      List<String[]> result; 
     
      TestProcessor proc = new TestProcessor();
       
       if (args.length != 0) {
    	   result = proc.testProcessStart(args[0], num, PTH, DIF_LEVEL);
       } else 
    	   result = proc.processStart(type, num, PTH, DIF_LEVEL); 
	
       
       for (String[] content : result) {
    	   System.out.println("MAIN");
    	   System.out.println("0 = " + content[0]);
    	   System.out.println("1 = " + content[1]);
    	   System.out.println("2 = " + content[2]);
    	   System.out.println("3 = " + content[3]);
    	   System.out.println("4 = " + content[4]);
    	   System.out.println("5 = " + content[5]);
    	   System.out.println("6 = " + content[6]);
    	   System.out.println("7 = " + content[7]);
    	   System.out.println("8 = " + content[8]);
       }
       
	}

}
