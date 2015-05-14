package tel_ran.tests.generator;

public abstract class AbstractTest implements ITestingProblem {
	
	protected String name;
	protected int numOfAnswers;
	protected String category;
	protected DescriptionBox dbox;
	protected String description;
	protected int numberOfDescripton;
	protected int difLevel = 5;
	protected String typeOfView;
	
	public static final String[] answerCharSymbols = {"A","B","C","D","E","F","G","H"};
	
	
	public AbstractTest() {
		dbox = new DescriptionBox();		
	}
				
	public String getCategory() {
		return category;
	}


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

	public String getName() {
		return name;
	}
	
	@Override
	public int WhatKindOfProblem() {		
		return 0;
	}
	@Override
	public String getCorrectAnswerChar(){
		return null;
	}
	
	@Override
	public String getView() {
		return typeOfView;
	}
	
}
