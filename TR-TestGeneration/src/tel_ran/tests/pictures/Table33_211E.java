package tel_ran.tests.pictures;

import tel_ran.tests.tools.RandFunc;

public class Table33_211E extends Table33{
	
	public Table33 t;
	
	public int kind;
	
	public int rc1 = -1;
	public int rc2 = -1;
	public int rs1 = -1;
	public int rs2 = -1;
	public int ri1 = -1;
	public int ri2 = -1;
	
	public Table33_211E() {
		
		t = new Table33();
		
		boolean flag = false;
		boolean flage;
		boolean flagf;	
				
		kind = RandFunc.getRandomOfThree(0, 1, 2);
		
		rc1 = RandFunc.IntRandomInRange(0,Picture.NUMBER_OF_COLORS-1);
		rs1 = RandFunc.IntRandomInRange(1, Picture.NUMBER_OF_SHAPES-1);
		ri1 = RandFunc.IntRandomInRange(0, Picture.NUMBER_OF_INSIDES-1);
		
		 do {   
			switch (kind) {
		    case 0:
		    	rc2 = RandFunc.IntRandomInRangeExept(0, Picture.NUMBER_OF_COLORS-1, rc1);
		    	t.setTwoColorsOneShapeOneInsidePlusEmpty(rc1, rc2, rs1, ri1);
		    	flag = (t.countColors() != 2);
		    	break;
		    case 1:
		    	ri2 = RandFunc.IntRandomInRangeExept(0, Picture.NUMBER_OF_INSIDES-1, ri1);
		    	t.setTwoInsidesOneShapeOneColorPlusEmpty(ri1, ri2, rs1, rc1);
		    	flag = (t.countInsides() != 2);
		    	break;
		    case 2:
		    	rs2 = RandFunc.IntRandomInRangeExept(1, Picture.NUMBER_OF_SHAPES-1, rs1);
		    	t.setTwoShapesOneInsideOneColorPlusEmpty(rs1, rs2, ri1, rc1);
		    	flag = (t.countShapes() != 2);
		    	break;
		    default: flag = false;
		    }
			flage = t.isTableEmpty();
			flagf = t.isTableFull();
		 } while(flag || flage || flagf);
	}
}
