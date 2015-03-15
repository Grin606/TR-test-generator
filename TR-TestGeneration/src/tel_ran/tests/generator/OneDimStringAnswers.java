package tel_ran.tests.generator;

public abstract class OneDimStringAnswers extends Testing_Problem{
	
	String correctAnswer;
	
	public OneDimStringAnswers() {
		super();
		correctAnswer = "";
	}

	@Override
	public abstract void generate(int difficultyLevel);
	
	public void setAnswers(String[] answers) {
		a.setODSA(answers);
	}	
}
