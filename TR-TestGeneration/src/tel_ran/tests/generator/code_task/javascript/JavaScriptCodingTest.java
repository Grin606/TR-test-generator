package tel_ran.tests.generator.code_task.javascript;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

import tel_ran.tests.generator.code_task.CodeTestingProblem;

public class JavaScriptCodingTest extends CodeTestingProblem {
	Random gen = new Random();

	public JavaScriptCodingTest() {
		super();
		this.filePath = JSBase.filepath;
		this.testLanguage = JSBase.testLanguage;
	}

	@Override
	public void generate(int difficultyLevel) {
		int i;
		switch (difficultyLevel) {
		case 1:
			i = gen.nextInt(JSBase.difficultyLevel1.length);
			creatTest(JSBase.difficultyLevel1[i]);
			break;
		case 2:
			i = gen.nextInt(JSBase.difficultyLevel2.length);
			creatTest(JSBase.difficultyLevel2[i]);
			break;
		case 3:
			i = gen.nextInt(JSBase.difficultyLevel3.length);
			creatTest(JSBase.difficultyLevel3[i]);
			break;

		default:
			break;
		}

	}

	private void creatTest(String dLevel) {
		String clName;
		JSTestGenerator jsGenerator;
		clName = "tel_ran.tests.generator.code_task.javascript." + dLevel;
		codeFiles = new LinkedList<String>();
		try {
			jsGenerator = (JSTestGenerator) Class.forName(clName).newInstance();
			this.questionText = jsGenerator.generateQuestion(filePath);
			this.codeFiles.add(jsGenerator.generateJUnit(filePath));
			this.codeFiles.add(generateScriptRunner(filePath));
			this.category2Name = jsGenerator.getTestName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String generateScriptRunner(String filePath) throws IOException {
		Path source = Paths.get("TestGeneration", "src", "tel_ran", "tests", "generator", "code_task", "javascript",
				"ScriptRunner.tmp");
		Path target = Paths.get(filePath, "ScriptRunner.java");
		Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
		return filePath + File.separator + "ScriptRunner.java";

	}

}
