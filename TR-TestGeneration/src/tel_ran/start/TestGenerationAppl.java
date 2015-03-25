package tel_ran.start;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import tel_ran.tests.images.Image;
import tel_ran.tests.processor.TestProcessor;
import tel_ran.tests.repository.QuestionsRepository;


public class TestGenerationAppl {
	
	public static final int TYPE = TestProcessor.ATTENTION2;
	public static final int NUMB = 20;
	public static final String PTH = "d:/res/";
	public static final int DIF_LEVEL = 1;
	static Scanner reader = new Scanner(System.in);
	
	
	
	public static void main(String[] args) throws Exception {
		
	
       
       int type = TYPE;
       int num = NUMB;       
       
//       System.out.println("Input Type of tasks: ");
//       System.out.println("0 - computation");
//       System.out.println("1 - estimation");
//       System.out.println("2 - sequence");
//       System.out.println("3 - sequence og chars");     
//
//
//       type = reader.nextInt();
//       System.out.println(type);
//		
//       System.out.println("Input number of tasks:");
//       
//       num = reader.nextInt();
//       System.out.println(num);
       
       QuestionsRepository rep = new QuestionsRepository();  
       Image image = new Image();
       TestProcessor proc = new TestProcessor(image, rep);
		
       proc.processStart(type, num, PTH, DIF_LEVEL);
				
	}

}
