package tel_ran.tests.images;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import tel_ran.tests.generator.Testing_Problem;


/**
 * Class Image contains all parameters of image, that can be defined before the start of the pictures generations
 * Method getImage of this class creates one picture for the given task 
 *
 */
public class Image {
	
						public static long time01 = 0L;
						public static long time02 = 0L;
						public static long time03 = 0L;
						public static long time04 = 0L;
				
	
	/** Color of the image background. default value = white **/
	static Color backgroundColor = Color.WHITE; 
	/** Color of text on the image. Default value = black **/
	static Color fontColor = Color.BLACK; 	
	/** Color of borders in the table of answers. Default value = black **/
	static Color borderColor = Color.BLACK; 	

//	private Color picturesColor = Color.BLACK; 	//Color of pictures on the image
	
	/** Name of font for the all text on the image. Default value = Arial **/
	private String fontName = "Arial"; 	
	/** Size of text for task's description. Default value = 14 **/
	private int fontTaskSize = 14;	
	/** Size of text for answers's labels like 'A', 'B', 'C'... Default value = 16 **/ 
	private int fontLabelsSize = 13;	
	/** Size of text for answers. Default value = 14 **/
	private int fontAnswerSize = 14;			
		
	/** the distance form the image's edge to the objects on it **/
	static int margin = 3;						
	/** the distance from borders to text in tables and from one object to other  **/
	static int padding = 4;	
	/** minimum height of the picture **/
	/** minimum width of image **/
	static int minWidth = 480;	
	/** minimum height of table columns **/
	static int minColWidth = 60;
	/** minimum width of tables row **/
	static int minRowHeight = 20;				
	
	/** 
	 * font for main text on the picture:
	 * - for description of the task
	 * - for the task 
	 */
	static Font fontMainText;
	
	/** 
	 * font for answers-labels, like 'A', 'B', 'C', etc.
	 */
	static Font fontLabels;
	
	/**
	 * font for texts in answers
	 */
	static Font fontAnswers;
	
	static final FontRenderContext frc = new FontRenderContext(null, true, true);
	
	private int height;	
		
	/**
	 * Constructor creates a new object with all default value	 
	 * and sets all fonts from default values
	 */
	public Image() {
		super();
		setFonts();
	}
	
	
	private void setFonts() {
		fontMainText = new Font(fontName, Font.PLAIN, fontTaskSize);
		fontLabels = new Font(fontName, Font.BOLD, fontLabelsSize);
		fontAnswers = new Font(fontName, Font.PLAIN, fontAnswerSize);
	}
	
	
	
	/** 
	* Calculating and drawing a picture for the given task.
	* Method checks which elements are there in the task, calculates the picture's size
	* and draws all the objects on it 
	* @param task - the given task 
	 * @throws Exception 
	 * @throws GraphicException 
	**/
	
	public BufferedImage getImage (Testing_Problem task) throws Exception {		
		
		height = Image.margin; 							
		
		//temporary variables
		int temp;		
		String[][] answers;
		int insWidth = minWidth - Image.margin*2;	
		
		// objects in the picture
		List<ImageObject> allObject = new ArrayList<ImageObject>();			
		ImageObject desc = null; // description of the task. It may be empty
		ImageObject table = null; 	// set of answers, if the answers should have the form of table
		
		
							long time9 = System.currentTimeMillis();

		
		/*
		 * calculation of the Answers - for answers that are defined as an one-dimensional array of strings
		 * and should be represented as a table		 * 
		 */							
		
		switch (task.WhatKindOfProblem()) {
	
		case Testing_Problem.ONE_DIM_STRING_ARRAY:						
			String[] tasks = task.getODSA_p();	
			for (String tsk : tasks) {				
				desc = setDescr(desc, tsk, insWidth);							
				allObject.add(desc);
			} 
			break;
		case Testing_Problem.TWO_DIM_STRING_ARRAY:			
			table = setTable(null, task.getTDSA_p(), null, fontMainText, task.whatProblemFrames());						
			allObject.add(table);
			break;
		default:
			assert false;
		}		
				
		height += Image.padding*4;
		
							long time12 = System.currentTimeMillis();
		
	
							
		if (task.WhatKindOfAnswers() == Testing_Problem.ONE_DIM_STRING_ARRAY) {
						
			temp = task.getNumOfAnswers();
			String[] taskLabels = Arrays.copyOf(Testing_Problem.answerCharSymbols, temp);			
			answers = new String[1][temp]; 
			answers[0] = task.getODSA_a();
//			answers[0][0] = "0";
//			answers[0][1] = "2";
//			answers[0][2] = "3";
//			answers[0][3] = "4";
//			answers[0][4] = "5";
			
			table = setTable(taskLabels, answers, fontLabels, fontAnswers, 2);	
			allObject.add(table);
		}			
		
		height += Image.margin;
		
							long time10 = System.currentTimeMillis();
					
		BufferedImage res = new BufferedImage(minWidth, height, BufferedImage.TYPE_BYTE_INDEXED);
		
		Graphics2D pict = res.createGraphics();
		
		pict.setColor(Image.backgroundColor);
		pict.fillRect(0, 0, minWidth, height);
		
		
		Iterator<ImageObject> it = allObject.iterator();			
		while (it.hasNext()) {
			it.next().draw(pict);			
		}
							long time11 = System.currentTimeMillis();
							time01 += time10-time9; //calculation
							time02 += time11-time10; //drawing
							time03 += time12-time9; //calculation task
							time04 += time10-time12; //calc answers
		
		return res;
	}	
	
	private ImageObject setTable(String[] header, String[][]fields, Font fntH, Font fntF, int fr) throws Exception {
		ImageObject table = new TablesString(header, fields, fntH, fntF, fr);
		table.setBorderColor(borderColor);
		table.calculation();	
		table.setStartX((Image.minWidth - Image.margin*2 - table.getWidth())/2);
		table.setStartY(height);
		heightUp(table);		
		
		return table;		
	}

	private ImageObject setDescr(ImageObject description, String task, int size) throws Exception {
		description = new TaskDescription(task, fontMainText, size, Image.frc);
		description.setMargin(padding);
		description.setFontColor(fontColor);
		description.calculation();	
		description.setStartX((Image.minWidth - Image.margin*2 - description.getWidth())/2);
		description.setStartY(height);
		heightUp(description);
		return description;
	}
	
	private void heightUp(ImageObject obj) {
		height += obj.getHeight() + Image.padding;		
	}
	
	public Color getFontColor() {
		return fontColor;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public int getPadding() {
		return padding;
	}

	public int getMinColWidth() {
		return minColWidth;
	}

	public int getMinRowHeight() {
		return minRowHeight;
	}

	public FontRenderContext getFrc() {
		return frc;
	}
		
}
