package tel_ran.tests.processor;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.imageio.ImageIO;

import tel_ran.tests.generator.ITestingProblem;
import tel_ran.tests.generator.Testing_Problem;
import tel_ran.tests.images.Image;

public class ImageView extends AbstractTaskView {
		

	BufferedImage res;	
	String imgName;
	Image img;
	
		
	public ImageView() {
		super();	
		img = new Image();	 
	}


	public ImageView(String path, String dirName) {
		super(path, dirName);
		img = new Image();	 	
	}


	@Override
	public String[] getTaskViews(ITestingProblem task, int lvl) throws Exception {
			
		res = img.getImage((Testing_Problem)task);
		imgName = fileNaming(res);
		
		if (unique.add(imgName)) {
			
			File newImage = new File(path + imgName);
			ImageIO.write(res, "jpeg", newImage);			
		
			answer[0] = task.getDescription();
			answer[1] = dirName.concat(imgName);
			answer[2] = task.getName();
			answer[3] = Integer.toString(lvl);			
			answer[4] = task.getCorrectAnswerChar();
			answer[5] = Integer.toString(task.getNumOfAnswers());
			return answer;
		} else {
			return null;
		}
		
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
