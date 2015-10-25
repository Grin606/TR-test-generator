package tel_ran.tests.generator;

import tel_ran.tests.interfaces.DescriptionBox;
import tel_ran.tests.interfaces.ITestingProblem;

public abstract class AbstractTest implements ITestingProblem {
	
	protected String category2Name;
	protected int numOfAnswers;
	protected String category;
	protected DescriptionBox dbox;
	protected String description;
	protected int numberOfDescripton;
	protected int difLevel;	
	
	public static final String[] answerCharSymbols = {"A","B","C","D","E","F","G","H"};
	
	
	public AbstractTest() {
		dbox = new DescriptionBox();		
	}
				
//	public String getCategory() {
//		return category;
//	}


	public void setCategory(String category) {
		this.category = category;
	}
	

	public int getDifLevel() {
		return difLevel;
	}
	
	public int getNumOfAnswers() {
		return numOfAnswers;
	}
	
	public boolean isDescNotEmpty() {
		if (getDescription().equals("")) return false;
		return true;
	}
	

	public String getDescription() {
		return dbox.description[numberOfDescripton];
	}


	public int getNumberOfDescripton() {
		return numberOfDescripton;
	}

	public String getCategory2Name() {
		return category2Name;
	}
	
	@Override
	public int WhatKindOfProblem() {		
		return 0;
	}
	@Override
	public String getCorrectAnswerChar(){
		return null;
	}
		
	
}
