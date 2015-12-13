package tel_ran.tests.utils.files;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public interface FileService {
	
	

	void setPath(String path);
	String getPath();
	void setFolderName(String dirName);
	String createArchive(List<String> files, String archiveName) throws IOException;
	void deleteFiles(List<String> files, String parentFolder);
	String saveImage(String imgName, BufferedImage res) throws IOException;
	
}
