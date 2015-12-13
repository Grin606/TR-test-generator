package tel_ran.tests.utils.files;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

public class FileSaver implements FileService {
	
	
	private String folderName;
	private String pathToFolder;

	public volatile static String basePath = "";

	@Override
	public void setPath(String path) {
		if(!path.equals(basePath)){
		if(!path.endsWith("/") && !path.endsWith("\\") && !path.endsWith(File.separator))
			path = path.concat(File.separator);
		basePath = path;		
		}
	}

	@Override
	public String getPath() {	
		return basePath;
	}

	@Override
	public void setFolderName(String dirName) {
		String newPath = basePath.concat(dirName);	
		
		if(!new File(newPath).exists() && !new File(newPath).mkdirs()) {
				System.out.println("Creating directory " + newPath + " failed");
				dirName = "";
				newPath = basePath;
		}
			else {				
				dirName = File.separator.concat(dirName);
			}
		this.folderName = dirName;
		this.pathToFolder = newPath;
	}

	@Override
	public String createArchive(List<String> files, String archiveName) throws IOException {
		
		String fileName = archiveName.concat(".zip");
		String newDir = null;	
				
		if(files!=null) {
			String newPath = this.pathToFolder.concat(File.separator).concat(fileName);
			newDir = this.folderName.concat(File.separator).concat(fileName);	
			doCreateArchive(files, newPath);				
		}	
		return newDir;
	}
	
	private void doCreateArchive(List<String> files, String path) throws IOException {
		
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

	@Override
	public void deleteFiles(List<String> files, String parentFolder) {
		
		File f;
		
		for (String str : files) {
			f = new File(str);
			f.delete();
		}		
		
		f = new File(parentFolder);
		f.delete();		
		
	}

	@Override
	public String saveImage(String imgName, BufferedImage res) throws IOException {
		String shortName = this.folderName.concat(File.separator).concat(imgName);
		String newPath = this.pathToFolder.concat(File.separator).concat(imgName);	
				
		File newImage = new File(newPath);
		ImageIO.write(res, "jpeg", newImage);
		return shortName;
	}
	
}
