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

public class CodeView extends AbstractTaskView {
	
	
	
	@Override
	public String[] getTaskViews(ITestingProblem task, int lvl)
			throws Exception {
		
		
		String taskText = task.getQuestionText();			
		String fileName = getHashName(taskText).concat(".zip");
		String newDir = null;		
		
		List<String> files = task.getFiles();
		if(files!=null) {
			String newPath = path.concat(File.separator).concat(fileName);
			newDir = dirName.concat(File.separator).concat(fileName);	
			createArchive(files, newPath);
			deleteFiles(files, task.getPathToFiles());
			
		}
					
		answer[0] = task.getDescription();
		answer[1] = taskText;
		answer[2] = task.getCategory2Name();
		answer[3] = Integer.toString(lvl);			
		answer[4] = task.getCorrectAnswerChar();
		answer[5] = Integer.toString(task.getNumOfAnswers());
		answer[6] = newDir;
		answer[7] = task.getTestLanguage();
		answer[8] = task.getStubText();
		return answer;
				
	}
	
	private void deleteFiles(List<String> files, String path) {
		File f;
		
		for (String str : files) {
			f = new File(str);
			f.delete();
		}		
		f = new File(path);
		f.delete();		
	}

	private String getHashName(String str) {
		return Integer.toHexString(0xFF & str.hashCode());
	}
	
	private void createArchive(List<String> files, String path) throws IOException {
		
		ZipOutputStream zos = new ZipOutputStream (new FileOutputStream(path));
	
		byte[] buf = new byte[1024];		
		ZipEntry se;		
				
		for (String fl : files) {
			FileInputStream in = new FileInputStream(fl);
			se = new ZipEntry(getName(fl));
			zos.putNextEntry(se);
			int len;
			while((len = in.read(buf)) > 0) {
				zos.write(buf, 0, len);
			}
			zos.closeEntry();
			in.close();
		}
		
		zos.close();
		
		
	}
	
	private String getName(String str) {
		String[] res = str.split("[\\\\/]");
		return res[res.length-1];
	}


}
