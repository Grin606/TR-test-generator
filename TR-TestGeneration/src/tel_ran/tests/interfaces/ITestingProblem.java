package tel_ran.tests.interfaces;

public interface ITestingProblem {
	
	/**
	 * Main function that calls test generation and fill all data of the Testing problem.
	 * @param difficultyLevel = level of difficulty 1-3
	 */
	void generate(int difficultyLevel); 
	
	/**
	 * Return name of the task type like "Calculator" for Java or NumEstimations for Quantative tasks
	 * This name can be seen by users-copmanies
	 * @return String
	 */
	String getCategory2Name();
	
	/**
	 * Return the text of the question. 
	 * This is the main field for programming tasks, that contains a question, a somple of code of interface
	 * @return long String
	 */
	String getDescription();
	
	/**
	 * Return the type of data to create images
	 * It's used for logic tasks.
	 * It's not used in programming tasks. So it can return 0 or -1;
	 * @return
	 */
	int WhatKindOfProblem();
	
	/**
	 * Return 1 letter = correct answer
	 * It's used for questions with type of American Test
	 * It's not used for prograaming task and can be = null
	 * @return
	 */
	String getCorrectAnswerChar();
	
	/**
	 * Returns number of options of answers (correct and incorrect)
	 * It's used for questions with type of American Test
	 * It's not used for prograaming task and can be = null
	 * @return
	 */
	int getNumOfAnswers();
	
	/**
	 * Return class with View (see package tel_ran.tests.test_views
	 * @return ImageView for tasks with image (attention, quantative etc)
	 * or CodeView for programming tasks
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */	
	ITaskView getView() throws ClassNotFoundException, InstantiationException, IllegalAccessException;
	

}
