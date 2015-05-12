package tel_ran.tests.processor;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;



import tel_ran.tests.generator.ITestingProblem;

import tel_ran.tests.generator.code_task.CodeTestingProblem;

public class CodeView extends AbstractTaskView {
	
	
	
	@Override
	public String[] getTaskViews(ITestingProblem task, int lvl)
			throws Exception {
		
		
		String taskText = ((CodeTestingProblem)task).getQuestionText();
		
//		if (unique.add(taskText)) {
			
			String newPath = path.concat(File.separator).concat(getHashName(taskText)).concat(Long.toString(System.currentTimeMillis())).
					concat(".zip");					
			
			List<String> files = ((CodeTestingProblem)task).getCodeFiles();
			createArchive(files, newPath);
			deleteFiles(files, path);
					
			answer[0] = task.getDescription();
			answer[1] = taskText;
			answer[2] = task.getName();
			answer[3] = Integer.toString(lvl);			
			answer[4] = task.getCorrectAnswerChar();
			answer[5] = Integer.toString(task.getNumOfAnswers());
			answer[6] = newPath;
			return answer;
//		} else {
//			return null;
//		}
				
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
