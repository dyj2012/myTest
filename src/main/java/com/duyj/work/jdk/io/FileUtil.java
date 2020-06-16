package com.duyj.work.jdk.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public final class FileUtil {

	public static void removeDir(String path) {
		File file = new File(path);
		if(file.isDirectory()){
			File[] files = file.listFiles();
			for(File f : files){
				removeDir(f.getAbsolutePath());
			}
		}
		else{
			file.delete();
		}
	}

	public static boolean copyFile(String path1, String path2) {
		File file1 = new File(path1);
		File file2 = new File(path2);
		try {
			Files.copy(file1.toPath(), file2.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static boolean cutFile(String path1, String path2) {
		copyFile(path1, path2);
		removeDir(path1);
		return true;
	}

	public static boolean copyDir(String path1, String path2) {
		return true;
	}
	
	public static boolean cutDir(String path1, String path2) {
		return true;
	}

}
