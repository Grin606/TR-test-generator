package tel_ran.tests.processor;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
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
		String newPath = path.concat(dirName);	
		
		if(!new File(newPath).exists() && !new File(newPath).mkdir()) {
				System.out.println("Creating directory " + newPath + " failed");
				dirName = File.separator;
		}
			else {
				path = newPath.concat(File.separator);
				dirName = File.separator.concat(dirName).concat(File.separator);
			}
		
		Testing_Problem testTask = null;
		
						long time1 = System.currentTimeMillis();
			
		BufferedImage res;	
		HashSet<String> unique = new HashSet<String>(); 
		String imgName;
		String[] dsc;
		int step, lvl = 1;
		step = number/maxLvl;
		if (number % maxLvl != 0)
			step++;	
		int th = step;
		
						long time2 = System.currentTimeMillis();
						long time3, time4, time34 = 0L, time5, time54=0L, time6, time56 = 0L, time8, time95 = 0L, 
								time86 = 0L, time9, time98 = 0L;
		
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
			
						time6 = System.currentTimeMillis();	
			
			if (unique.add(imgName)) {
			
				File newImage = new File(path + imgName);
				ImageIO.write(res, "jpeg", newImage);	
			
						time8 = System.currentTimeMillis();				
			
			
				dsc = new String[7];
				dsc[0] = testTask.getDescription();
				dsc[1] = dirName.concat(imgName);
				dsc[2] = testTask.getName();
				dsc[3] = Integer.toString(lvl);			
				dsc[4] = testTask.getCorrectAnswerChar();			
				dsc[5] = Integer.toString(testTask.getNumberOfDescripton());
				dsc[6] = Integer.toString(testTask.getNumOfAnswers());
						
				rep.addQuestion(dsc);
				
						time9 = System.currentTimeMillis(); 
			
						
						time86 += time8 - time6;
						time95 += time9 - time5;
						time98 += time9 - time8;
			}
			else {
				i--;
			}
							
				time34 += time4 - time3; 
				time54 += time5 - time4;
				time56 += time6 - time5;
			
						
		}
		
						long time7 = System.currentTimeMillis();
						long time71 = time7 - time1;
						long time21 = time2 - time1;
		
						System.out.println("Общее время исполнения - \t" + time71);
						System.out.println(" ");
						System.out.println("1. Генерация файлов и свич - \t" + time21);
						System.out.println("2. Генерация задач - \t\t" + time34);
						System.out.println("3. Обработка картинок - \t" + time54);
						System.out.println("   3.1. Калькуляция \t\t" + Image.time_all_calc);
						System.out.println("        3.1.1. создание списка \t" + Image.time_creating_set);
						System.out.println("        3.1.2. только калькуляция \t" + Image.time_only_calc);
						System.out.println("        3.1.3. ресайзинг \t\t" + Image.time_resizing);
						System.out.println("        3.1.4. установка координа \t" + Image.time_settings);
						System.out.println("   3.2. Рисование в буфере \t" + Image.time_drawing);
						System.out.println("4. Запись в файлы - \t\t" + time95);
						System.out.println("   4.1. Создание имени картинки - \t" + time56);
						System.out.println("   4.2. Запись картинки - \t" + time86);
						System.out.println("   4.2. Текст и список - \t" + time98);
						System.out.println(" ");
						
		rep.displayList();
		rep.displayInFile(path);
						
		return rep.getList();
	}
	
	
	
	private String fileNaming (BufferedImage img) throws NoSuchAlgorithmException {
		StringBuffer name = hashImage(img);
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
