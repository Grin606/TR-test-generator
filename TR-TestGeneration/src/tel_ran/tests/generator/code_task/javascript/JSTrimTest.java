package tel_ran.tests.generator.code_task.javascript;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class JSTrimTest implements JSTestGenerator {
	private static final int CHAR_FROM = 48;
	private static final int CHAR_TO = 57;
	private static final int MIN_LENGTH = 20;
	private static final int MAX_LENGTH = 40;
	private static final int MIN_QUANTITY_CH = 3;
	private String testName = "Trim";

	Random gen = new Random();

	Character ch = (char) (CHAR_FROM + gen.nextInt(CHAR_TO - CHAR_FROM + 1));

	@Override
	public String generateQuestion(String filePath) {
		return "Write javascript with function trim that takes a string and returns this string without the symbol '"
				+ ch + "'";

	}

	@Override
	public String generateJUnit(String filePath) throws FileNotFoundException {
		String path = filePath.concat(File.separator).concat("trim").concat(".java");
		PrintWriter out = new PrintWriter(path);
		out.println("package test.javascript;");
		out.println("");
		out.println("import static org.junit.Assert.*;");
		out.println("import org.junit.Test;");
		out.println("");
		out.println("public class JavaScriptTest{");
		out.println("");
		out.println("\t@Test");
		out.println("\tpublic void test() throws Exception{");
		String str = getControlString();
		out.println("String str = \"" + str + "\";");
		out.println("ScriptRunner scr = new ScriptRunner(\"tests.js\",\"javascript\");");
		out.println("String res = (String) scr.execute(\"trim\", str);");
		out.println("str=str.replaceAll(\"" + ch.toString() + "\",\"\");");
		out.println("assertEquals(str, res);");
		out.println("}");
		out.println("}");
		out.close();
		return path;

	}

	private String getControlString() {
		char[] symbols = new char[MIN_LENGTH + gen.nextInt(MAX_LENGTH - MIN_LENGTH + 1)];
		for (int i = 0; i < symbols.length; i++) {
			symbols[i] = (char) (CHAR_FROM + gen.nextInt(CHAR_TO - CHAR_FROM + 1));
		}
		for (int i = 0; i < MIN_QUANTITY_CH; i++) {
			symbols[gen.nextInt(symbols.length)] = ch;

		}
		return new String(symbols);
	}

	@Override
	public String getTestName() {
		return testName;
	}

}
