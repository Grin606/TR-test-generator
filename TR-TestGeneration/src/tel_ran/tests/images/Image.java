package tel_ran.tests.images;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import tel_ran.tests.dataset.DataSet;
import tel_ran.tests.dataset.Frames;
import tel_ran.tests.generator.Testing_Problem;
import tel_ran.tests.pictures.Picture;


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
	public static Color backgroundColor = Color.WHITE; 
	/** Color of text on the image. Default value = black **/
	static Color fontColor = Color.BLACK; 	
	/** Color of borders in the table of answers. Default value = black **/
	static Color borderColor = Color.BLACK; 	

//	private Color picturesColor = Color.BLACK; 	//Color of pictures on the image
	
	/** Name of font for the all text on the image. Default value = Arial **/
	private String fontName = "Arial"; 	
	/** Size of text for task's description. Default value = 14 **/
	private int fontTaskSize = 14;
	protected static final int fontTaskSizeMin = 8;
	/** Size of text for answers's labels like 'A', 'B', 'C'... Default value = 16 **/ 
	private int fontLabelsSize = 13;	
	protected static final int fontLabelSizeMin = 10;
	/** Size of text for answers. Default value = 14 **/
	private int fontNotesSize = 14;		
	protected static final int fontNotesSizeMin = 6;
	protected static final int minimumObject = 5;
		
	/** the distance form the image's edge to the objects on it **/
	static int margin = 0;						
	/** the distance from borders to text in tables and from one object to other  **/
	static int padding = 6;	
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
	private int fontResize = 0;
	
		
	/**
	 * Constructor creates a new object with all default value	 
	 * and sets all fonts from default values
	 */
	public Image() {
		super();	
		setFonts();
	}
		
	private void setFonts() {
		fontMainText = new Font(fontName, Font.PLAIN, fontTaskSize - fontResize);
		fontLabels = new Font(fontName, Font.BOLD, fontLabelsSize - fontResize);
		fontAnswers = new Font(fontName, Font.PLAIN, fontNotesSize - fontResize);
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
		
		height = Image.margin*2; 		
				
		
		Resizing rs = new Resizing(fontMainText, fontLabels, fontAnswers);
		// objects in the picture
		
		
							long time9 = System.currentTimeMillis();

		List<ImageObject> allObject = setListOfObject(task, rs);	
						
					
		height += calculate(allObject, height);	
		
		
							long time12 = System.currentTimeMillis();
		
		setXY(allObject);
		
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
							time01 += time12-time9; //calculation
							time02 += time11-time10; //drawing
							time03 += time10-time12; //setting
							
							time04 += time10-time12; //calc answers
		
		return res;
	}	
	
	private ArrayList<ImageObject> setListOfObject(Testing_Problem task, Resizing rs) {
		ArrayList<ImageObject> res = new ArrayList<ImageObject>();
		ImageObject io = null;			
		
		if (task.p != null) {
		
			switch (task.WhatKindOfProblem()) {
				case Testing_Problem.ONE_DIM_STRING_ARRAY:				
					io = new ListObject(task.getODSA_p(), rs, Resizing.MainText, Image.padding);
					io.setBorders(task.whatProblemFrames(), borderColor);	
					break;			
				case Testing_Problem.ONE_DIM_PICTURE_ARRAY:
					io = new ListObject(task.getODPA_p(), rs, Image.padding);
					io.setBorders(task.whatProblemFrames(), borderColor);	
					break;
				case Testing_Problem.TWO_DIM_STRING_ARRAY:					
					io = new TableObject(task.getTDSA_p(), Resizing.MainText, rs, Image.padding);
					io.setBorders(Frames.ALL_BORDERS, borderColor);	
					break;
				case Testing_Problem.TWO_DIM_PICTURE_ARRAY:					
					io = new TableObject(task.getTDPA_p(), rs, Image.padding);	
					io.setBorders(Frames.ALL_BORDERS, borderColor);	
					break;					
				case Testing_Problem.THREE_DIM_PICTURE_ARRAY:
					io = new ListObjectInLine(task.getTHDPA_p(), rs, Image.padding);	
					io.setBorders(Frames.INNER_OBJECT_BORDEDS, borderColor);		
					break;
				default: assert false;					
			}	
			io.setFontColor(fontColor);
								
			res.add(io);
		}
				
		if(task.a != null) {	
			int temp = task.getNumOfAnswers();
			String[] answers = new String[temp];
			System.arraycopy(Testing_Problem.answerCharSymbols, 0, answers, 0, temp);
			
			switch (task.WhatKindOfAnswers()) {			
			
				case Testing_Problem.ONE_DIM_STRING_ARRAY:	
					String[][] ans = new String[1][temp];
					ans[0] = task.getODSA_a();
					io = new TableObject(ans, Resizing.MainText, rs, Image.padding);
					io.setHeader(answers, Resizing.HeaderText);							
					io.setBorders(Frames.GRID, borderColor);	
					break;		
				case Testing_Problem.THREE_DIM_PICTURE_ARRAY:
					io = new TableObject(task.getTHDPA_a(), rs, Image.padding);
					io.setNotes(answers, Resizing.HeaderText);						
					io.setBorders(Frames.INNER_OBJECT_BORDEDS, borderColor);	
					break;	
				default: return res;
				}	
			io.setFontColor(fontColor);				
			res.add(io);	
				
		}
		return res;
	}
	
	private int calculate(List<ImageObject> allObject, int height) throws Exception {
				
		int w;		
		int s = allObject.size();
		
		for (int i = 0; i < s; i ++) {
			ImageObject io = allObject.get(i);
			io.calculation();
			w = io.getWidth();
			
			if (w > Image.minWidth) {	
				
				if (!io.getRotated()) {
					ImageObject newio = io.reform(Image.minWidth-Image.margin*2);
					if (newio != null) {
						io = newio;						
						height += io.getHeight() + Image.padding*4;
						allObject.set(i, io);
					}
				} else {
					io.resize(Image.minWidth-Image.margin*4);	
					height = io.getHeight() + Image.padding*4;
				}
			} 
			else 
				height += io.getHeight() + Image.padding*4;						
		}		
	
			
		return height;
				
	}
		
	private void setXY(List<ImageObject> lst) throws Exception {
		int x = Image.margin;
		int y = Image.margin;
		for (ImageObject io : lst) {
			io.setStartXY(x, y);			
			y += io.getHeight() + Image.padding*4;
			
		}				
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
