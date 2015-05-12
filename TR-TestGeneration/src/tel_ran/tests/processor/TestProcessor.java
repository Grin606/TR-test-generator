package tel_ran.tests.processor;


import java.io.File;
import java.util.List;
import tel_ran.tests.generator.*;
import tel_ran.tests.repository.QuestionsRepository;

public class TestProcessor {
	
	//These constants are designed to simplify the choice of the task's type outside 
	public static final int ATTENTION = 0;
	public static final int QUANTATIVE = 1;
	public static final int ABSTRACT = 2;	
	public static final int PROGRAMMING = 3;
	
	
//	Image img;
	QuestionsRepository rep;
	
	
	String dev = "----";
	
	

	public TestProcessor(/* Image img, */QuestionsRepository rep) {
		super();
//		this.img = img;
		this.rep = rep;
	}
	
	public TestProcessor() {
		this.rep = new QuestionsRepository();  
//		this.img  = new Image();	      
	}

	//test method
	public List<String[]> processStart(String testName, int number, String path, int maxLvl) throws Exception {
		IGetTaskGenerate tg = new GetSimpleTask(testName);
		return processing(tg, number, path, maxLvl);		
	}
	
	//main process method
	public List<String[]> processStart(int testType, int number, String path, int maxLvl) throws Exception {
		IGetTaskGenerate tg = new GetBoxTask(testType); 
		
		return processing(tg, number, path, maxLvl);		
	}

	public List<String[]> processing (IGetTaskGenerate taskGen, int number, String path, int maxLvl) throws Exception {
		String dirName = taskGen.getDirName();		
		String newPath = path.concat(dirName);	
		
		if(!new File(newPath).exists() && !new File(newPath).mkdir()) {
				System.out.println("Creating directory " + newPath + " failed");
				dirName = File.separator;
		}
			else {
				path = newPath.concat(File.separator);
				dirName = File.separator.concat(dirName).concat(File.separator);
			}
				
		ITestingProblem testTask = null;
			
		
//		ITaskView taskView = new ImageView(newPath, dirName);
		
		// generate class from Testring_Problem		
		
		Class<?> cl = Class.forName(taskGen.getView());
		ITaskView taskView = (ITaskView) cl.newInstance();
		taskView.setPath(newPath, dirName);
		
								
//		BufferedImage res;	
//		HashSet<String> unique = new HashSet<String>(); 
//		String imgName;
		String[] dsc;
		int step, lvl = 1;
		step = number/maxLvl;
		if (number % maxLvl != 0)
			step++;	
		int th = step;
				
		for (int i = 0; i < number; i++) {
			
			if (i > th) {
				lvl++;
				th += step;
			}
						
			testTask = taskGen.getTask(lvl); 
								
			dsc = taskView.getTaskViews(testTask, lvl);
			
			if (dsc == null)
				i--;
			else
				rep.addQuestion(dsc);
			
//						res = img.getImage(testTask);			
		
//						time5 = System.currentTimeMillis();			
			
//			imgName = fileNaming(res);
			
//						time6 = System.currentTimeMillis();	
			
//			if (unique.add(imgName)) {
			
//				File newImage = new File(path + imgName);
//				ImageIO.write(res, "jpeg", newImage);	
			
//						time8 = System.currentTimeMillis();				
			
			
//				dsc = new String[6];
//				dsc[0] = testTask.getDescription();
//				dsc[1] = dirName.concat(imgName);
//				dsc[2] = testTask.getName();
//				dsc[3] = Integer.toString(lvl);			
//				dsc[4] = testTask.getCorrectAnswerChar();			
//				dsc[5] = Integer.toString(testTask.getNumberOfDescripton());
//				dsc[5] = Integer.toString(testTask.getNumOfAnswers());
						
				
						
		}
		

//						
//		rep.displayList();
//		rep.displayInFile(path);
						
		return rep.getList();
	}
	
	
	
//	private String fileNaming (BufferedImage img) throws NoSuchAlgorithmException {
//		StringBuffer name = hashImage(img);
//		name.append(".jpg");
//		
//		return name.toString();
//	}
//	
//	private StringBuffer hashImage (BufferedImage img) throws NoSuchAlgorithmException  {
//		
//		int[] data = new int [img.getHeight()*img.getWidth()];		
//		img.getData().getPixel(0, 0, data);
//		
//		byte[] imageBytes = ((DataBufferByte) img.getData().getDataBuffer()).getData();		
//		MessageDigest mdInst = MessageDigest.getInstance("MD5");
//		mdInst.update(imageBytes);
//		byte[] code = mdInst.digest();
//		StringBuffer hash = new StringBuffer("");
//		
//		for (byte b : code)
//			hash.append(Integer.toHexString((0xFF & b)));
//		
//		
//		return hash;
//	}	


}
