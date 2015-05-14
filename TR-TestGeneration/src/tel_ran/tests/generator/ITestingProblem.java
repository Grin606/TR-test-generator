package tel_ran.tests.generator;

public interface ITestingProblem {
	
		
	void generate(int difficultyLevel);
	String getName();
	String getDescription();
	int WhatKindOfProblem();
	String getCategory();
	String getCorrectAnswerChar();
	int getNumOfAnswers();
	String getView();
	

}
