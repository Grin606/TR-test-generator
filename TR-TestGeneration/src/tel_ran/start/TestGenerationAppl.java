package tel_ran.start;
import java.util.*;

import tel_ran.tests.processor.TestProcessor;

public class TestGenerationAppl {
	
	public static final String TYPE = TestProcessor.PROGRAMMING;
	public static final int NUMB = 1;
	public static final String PTH = "d:/res/";
	public static final int DIF_LEVEL = 5;
	static Scanner reader = new Scanner(System.in);
	
	
	
	public static void main(String[] args) throws Exception {	
       
       String type = TYPE;
       int num = NUMB;       
         
     
      TestProcessor proc = new TestProcessor();
       
       if (args.length != 0) {
    	   proc.testProcessStart(args[0], num, PTH, DIF_LEVEL);
       } else 
    	   proc.processStart(type, num, PTH, DIF_LEVEL); 
				
	}

}
