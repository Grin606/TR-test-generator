package tel_ran.tests.generator;

public class DescriptionBox {
	
	public static final int NUMBER_OF_DESCRIPTIONS = 10;
	public String[] description;
	
	public DescriptionBox() {
	
	description = new String[NUMBER_OF_DESCRIPTIONS];
	
	description[0] = "";
	description[1] = "What number is the result of the expression?";
	description[2] = "What number is closest to the result of the expression?";
	description[3] = "What number is to be set instead of question mark?";
	description[4] = "What 'word' is to be set instead of question mark?";
	description[5] = "What table is to be next?";
	description[6] = "Which sequence is incorrect?";
	description[7] = "In which row the sequence is incorrect?";
	description[8] = "In which row the sequence is correct?";
	description[9] = "Count the number of the following figures:";

	}
}
