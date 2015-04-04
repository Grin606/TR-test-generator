package tel_ran.tests.pictures;

import tel_ran.tests.tools.RandFunc;

public class Table33_311F extends Table33{
	
public Table33 t;
	
	public int kind;
	
	public int rc1 = -1;
	public int rc2 = -1;
	public int rc3 = -1;
	public int rs1 = -1;
	public int rs2 = -1;
	public int rs3 = -1;
	public int ri1 = -1;
	public int ri2 = -1;
	public int ri3 = -1;
	
	public Table33_311F() {
	
	t = new Table33();
			
	kind = RandFunc.getRandomOfThree(0, 1, 2);

	rc1 = RandFunc.IntRandomInRange(0, Picture.NUMBER_OF_COLORS-1);
	rs1 = RandFunc.IntRandomInRange(1, Picture.NUMBER_OF_SHAPES-1);
	ri1 = RandFunc.IntRandomInRange(0, Picture.NUMBER_OF_INSIDES-1);
	
	boolean flag = true;
	
	do {
	switch (kind) {
	
	case 0:
		rc2 = RandFunc.IntRandomInRangeExept(0, Picture.NUMBER_OF_COLORS-1, rc1);
		do {
			rc3 = RandFunc.IntRandomInRangeExept(0, Picture.NUMBER_OF_COLORS-1, rc1);
		}while (rc3 == rc2);
		t.setThreeColorsOneShapeOneInside(rc1, rc2, rc3, rs1, ri1);
		flag = (t.countColors() != 3);
		break;
	case 1:
		rs2 = RandFunc.IntRandomInRangeExept(0, Picture.NUMBER_OF_SHAPES-1, rs1);
		do {
			rs3 = RandFunc.IntRandomInRangeExept(0, Picture.NUMBER_OF_SHAPES-1, rs1);
		}while (rs3 == rs2);
		t.setThreeShapesOneInsideOneColor(rs1, rs2, rs3, ri1, rc1);
		flag = (t.countShapes() != 3);
		break;
	case 2:
		ri2 = RandFunc.IntRandomInRangeExept(0, Picture.NUMBER_OF_INSIDES-1, ri1);
		do {
			ri3 = RandFunc.IntRandomInRangeExept(0, Picture.NUMBER_OF_INSIDES-1, ri1);
		}while (ri3 == ri2);
		t.setThreeInsidesOneShapeOneColor(ri1, ri2, ri3, rs1, rc1);
		flag = (t.countInsides() != 3);
		break;
	}
	}while(flag);
	
	}
}
