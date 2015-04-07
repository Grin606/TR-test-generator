package tel_ran.tests.processor;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.imageio.ImageIO;

import tel_ran.tests.generator.*;
import tel_ran.tests.images.Image;
import tel_ran.tests.repository.QuestionsRepository;

public class TestProcessor {
	
	//These constants are designed to simplify the choice of the task's type outside 
	public static final int ATTENTION = 0;
	public static final int QUANTATIVE = 1;
	public static final int ABSTRACT = 2;	
	
	
	Image img;
	QuestionsRepository rep;
	
	
	String dev = "----";
	
	

	public TestProcessor(Image img, QuestionsRepository rep) {
		super();
		this.img = img;
		this.rep = rep;
	}
	
	public TestProcessor() {
		this.rep = new QuestionsRepository();  
		this.img  = new Image();	      
	}

	//test method
	public List<String[]> processStart(String testName, int number, String path, int maxLvl) throws Exception {
		GetTaskGenerate tg = new GetSimpleTask(testName);
		return processing(tg, number, path, maxLvl);		
	}
	
	//main process method
	public List<String[]> processStart(int testType, int number, String path, int maxLvl) throws Exception {
		GetTaskGenerate tg = new GetBoxTask(testType); 
		
		return processing(tg, number, path, maxLvl);		
	}

	public List<String[]> processing (GetTaskGenerate taskGen, int number, String path, int maxLvl) throws Exception {
		String dirName = taskGen.getDirName();		
		dirName = path.concat(dirName);	
		
		if(!new File(dirName).exists() && !new File(dirName).mkdir())
				System.out.println("Creating directory " + dirName + " failed");
			else
				path = dirName.concat(File.separator);		
				
		Testing_Problem testTask = null;
						long time1 = System.currentTimeMillis();
			
		BufferedImage res;	
		String imgName;
		String[] dsc;
		int step, lvl = 1;
		if (number % maxLvl == 0)
			step = number/maxLvl;
		else
			step = number/maxLvl +1;		
		int th = step;
		

		
						long time2 = System.currentTimeMillis();
						long time3, time4, time34 = 0L, time5, time54=0L, time6, time56 = 0L, time8, time85 = 0L, time86 = 0L;
		
		for (int i = 0; i < number; i++) {
			
			if (i > th) {
				lvl++;
				th += step;
			}
						time3 = System.currentTimeMillis();
						
			testTask = taskGen.getTask(lvl); 
			
						time4 = System.currentTimeMillis();
					
			res = img.getImage(testTask);			
		
						time5 = System.currentTimeMillis();			
			
			imgName = fileNaming(res);
			
			File newImage = new File(path + imgName);
			ImageIO.write(res, "jpeg", newImage);	
			
						time8 = System.currentTimeMillis();
						
			
			
			dsc = new String[7];
			dsc[0] = testTask.getDescription();
			dsc[1] = imgName;
			dsc[2] = testTask.getName();
			dsc[3] = Integer.toString(lvl);			
			dsc[4] = testTask.getCorrectAnswerChar();			
			dsc[5] = Integer.toString(testTask.getNumberOfDescripton());
			dsc[6] = Integer.toString(testTask.getNumOfAnswers());
						
			rep.addQuestion(dsc);
			
						time6 = System.currentTimeMillis();
						time34 += time4 - time3; 
						time54 += time5 - time4;
						time56 += time6 - time5;
						time86 += time6 - time8;
						time85 += time8 - time5;
						
		}
		
						long time7 = System.currentTimeMillis();
						long time71 = time7 - time1;
						long time21 = time2 - time1;
		
//						System.out.println("Общее время исполнения - \t" + time71);
//						System.out.println(" ");
//						System.out.println("1. Генерация файлов и свич - \t" + time21);
//						System.out.println("2. Генерация задач - \t\t" + time34);
//						System.out.println("3. Обработка картинок - \t" + time54);
//						System.out.println("   3.1. Калькуляция \t\t" + Image.time01);
//						System.out.println("        3.1.1. расчет и ресайз \t" + Image.time03);
//						System.out.println("        3.1.2. сеттинг координат \t" + Image.time04);
//						System.out.println("   3.2. Рисование в буфере \t" + Image.time02);
//						System.out.println("4. Заипсь в файлы - \t\t" + time56);
//						System.out.println("   4.1. Картинки - \t\t" + time85);
//						System.out.println("   4.2. Текст и список - \t" + time86);
//						System.out.println(" ");
//						
		rep.displayList();
//		rep.displayInFile(path);
						
		return rep.getList();
	}
	
	
	
	private String fileNaming (BufferedImage img) throws NoSuchAlgorithmException {
		StringBuffer name = hashImage(img);		
//		Date date = new Date();
//		Long dat = date.getTime();
		name.append(".jpg");
		
		return name.toString();
	}
	
	private StringBuffer hashImage (BufferedImage img) throws NoSuchAlgorithmException  {
		
		int[] data = new int [img.getHeight()*img.getWidth()];		
		img.getData().getPixel(0, 0, data);
		
		byte[] imageBytes = ((DataBufferByte) img.getData().getDataBuffer()).getData();		
		MessageDigest mdInst = MessageDigest.getInstance("MD5");
		mdInst.update(imageBytes);
		byte[] code = mdInst.digest();
		StringBuffer hash = new StringBuffer("");
		
		for (byte b : code)
			hash.append(Integer.toHexString((0xFF & b)));
		
		
		return hash;
	}	


}
