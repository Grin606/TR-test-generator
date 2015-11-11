package tel_ran.tests.generator.code_task.html;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import tel_ran.tests.generator.code_task.CodeTestingProblem;
import tel_ran.tests.generator.code_task.html.validator.HtmlValidator;

public class HtmlCorrectingProblem extends CodeTestingProblem {
	private static final int NUMBER_OF_DESCRIPTION = 11;
	private static final String CATEGORY_NAME = "HTML syntax";
	private static final String TASK_PREFIX = "task";
	private static final String HTML = "HTML";
	private static final String TEMP_PATH = "Temporary";
	public static final String CHECK_FILE_NAME = "check.txt";
//	public String matching;
	
	public HtmlCorrectingProblem() {
		super();
		category2Name = CATEGORY_NAME;
		testLanguage = HTML;
		numberOfDescripton = NUMBER_OF_DESCRIPTION;
	}

	@Override
	public void generate(int difficultyLevel) {
		Document taskTemplate = HtmlUtils.prepareHtmlDoc();
		Document doc = taskTemplate.clone();
		for(int i=0; i<difficultyLevel;i++){
			taskTemplate.body().append(TaskData.INSERT_TEXT_MARK);
		}
		String taskData = taskTemplate.html();
		int taskId = 0;
		for(String taskType: HtmlUtils.getTaskList(difficultyLevel)) {
			Element taskElement = doc.body().appendElement(TaskData.TAG_DIV)
				.attr(TaskData.KW_ID, TASK_PREFIX + String.valueOf(taskId++))
				.attr(TaskData.KW_NAME, taskType);
			try {
				IHtmlTask iTask = (IHtmlTask) Class.forName(taskType).newInstance();
				iTask.generateTask(taskElement, difficultyLevel);
				taskData = taskData.replaceFirst(TaskData.INSERT_TEXT_MARK, iTask.getData());
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		questionText = taskData;
		try {
			createDir();
			codeFiles = new LinkedList<String>();
			String fileName = filePath.concat(File.separator).concat(CHECK_FILE_NAME);
			saveToFile(doc.html(),fileName);
			codeFiles.add(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean check(String solution, String matching) throws Exception {
		if(!HtmlValidator.validateHtml(solution))
			return false;

		Document solDoc = Parser.parse(solution, "");
		Document matchDoc = Parser.parse(matching, "");
		if(!HtmlUtils.matchingElements(solDoc.head(), matchDoc.head()))
			return false;
		int taskId = 0;
		Element singleTask = matchDoc.getElementById(TASK_PREFIX + String.valueOf(taskId++));
		if(singleTask == null)
			return HtmlUtils.matchingElements(solDoc.body(), matchDoc.body());
		while(singleTask != null) {
			String taskType = singleTask.attr(TaskData.KW_NAME);
			IHtmlTask iTask = (IHtmlTask) Class.forName(taskType).newInstance();
			if(!iTask.check(solDoc.body(), singleTask))
				return false;
			singleTask = matchDoc.getElementById(TASK_PREFIX + String.valueOf(taskId++));
		}
		return true;
	}
	
	private void createDir() {
		long name = System.currentTimeMillis();
		filePath = TEMP_PATH.concat(File.separator).concat(Long.toString(name));
		File dir = new File(filePath);
		if(dir.exists())
			createDir();
		else {
			dir.mkdirs();			
		}
	}
	
	private void saveToFile(String strData, String fileName) throws IOException {
		PrintWriter pw = new PrintWriter(fileName);
		pw.print(strData);
		pw.flush();
		pw.close();
	}
	
}
