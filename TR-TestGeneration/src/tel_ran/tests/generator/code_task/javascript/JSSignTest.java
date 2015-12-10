package tel_ran.tests.generator.code_task.javascript;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;

public class JSSignTest implements JSTestGenerator {
	private String testName = "Signum";

	Random gen = new Random();

	@Override
	public String generateQuestion(String filePath) {
		return "Create javascript with function sign, which return sign of number ";
	}

	@Override
	public String generateJUnit(String filePath) throws Exception {
		Path source = Paths.get("TestGeneration", "src", "tel_ran", "tests", "generator", "code_task", "javascript",
				"JSSignTest.tmp.tmp");
		Path target = Paths.get(filePath, "JavaScriptTest.java");
		Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
		return filePath + File.separator + "JavaScriptTest.java";
	}

	@Override
	public String getTestName() {
		return testName;
	}

}
