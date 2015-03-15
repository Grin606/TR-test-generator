package tel_ran.tests.images;

import java.awt.Font;
import java.util.ArrayList;

public class StringDevider {
	
	String dev = " ";
	Font fontFields;
	
	public StringDevider(Font font) {
		super();		
		fontFields = font;
	}
	
	public String[] dev(String str, int len, int max) {
		String[] words = str.split(" ");	
		ArrayList<StringBuffer> lines = new ArrayList<StringBuffer>();
		StringBuffer sb = new StringBuffer("");
		int temp = 0;
		int wLen;
		int ind = 0;
		
		for (String w : words) {
			wLen = (int)fontFields.getStringBounds(w, Image.frc).getWidth();
			if (wLen > max)
				return null;
			
			if (temp + wLen > max) {
				lines.add(sb);
				sb = new StringBuffer("");
			} 
			sb.append(w).append(" ");
		}
		
		String[] res = new String[ind+1];		
		ind = 0;
		for (StringBuffer r : lines) {
			res[ind++] = r.toString();			
		}

		return res;		
	}

}
