package tel_ran.tests.test_views;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;






import tel_ran.tests.generator.code_task.CodeTestingProblem;
import tel_ran.tests.interfaces.ITestingProblem;
import tel_ran.tests.utils.files.FileService;

public class CodeView extends AbstractTaskView {
	
	
	
	@Override
	public String[] getTaskViews(ITestingProblem task, int lvl)
			throws Exception {
				
		String taskText = task.getQuestionText();
		String archiveName = getHashName(taskText).concat("-").concat(Long.toString(System.currentTimeMillis()));
		List<String> files = task.getFiles();
		
		try {
			String pathToArchive = this.fileService.createArchive(files, archiveName);
							
			answer[0] = task.getDescription();
			answer[1] = taskText;
			answer[2] = task.getCategory2Name();
			answer[3] = Integer.toString(lvl);			
			answer[4] = task.getCorrectAnswerChar();
			answer[5] = Integer.toString(task.getNumOfAnswers());
			answer[6] = pathToArchive;
			answer[7] = task.getTestLanguage();
			answer[8] = task.getStubText();
		} finally {
			this.fileService.deleteFiles(files, task.getPathToFiles());
		}
		return answer;
				
	}
	
	
	private String getHashName(String str) {
		return Integer.toHexString(0xFF & str.hashCode());
	}
	
	
	


	


}
