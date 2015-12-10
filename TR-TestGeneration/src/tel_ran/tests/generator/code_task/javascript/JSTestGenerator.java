package tel_ran.tests.generator.code_task.javascript;

public interface JSTestGenerator {
	String generateQuestion(String filePath);

	String generateJUnit(String filePath) throws Exception;

	String getTestName();

}
