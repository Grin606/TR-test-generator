package tel_ran.tests.images;

import java.awt.Graphics2D;

public interface Fields extends Comparable<Fields> {
	
	boolean reduction(int max) throws Exception;
	int getWidth() throws Exception;
	int getHeight() throws Exception;	
	void draw(Graphics2D gr) throws Exception; 
	void setXY(int x, int y) throws Exception;
	int reform(int max) throws Exception;
	

}
