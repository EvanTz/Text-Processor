package dataload;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import datamodel.buildingblocks.LineBlock;
//import datamodel.buildingblocks.Document;

public class RawFileLineLoader {
	
	public void load(String filePath, List<LineBlock> lineblocks) {
		String inputFile = "";
		List<String> fileList = new ArrayList<String>();
		
		Path fPath = Path.of(filePath);
		try {
			inputFile = Files.readString(fPath);
		} catch (IOException e) {
			System.out.println("Error while loading file");
			//e.printStackTrace();
		}
		
		fileList = Arrays.asList(inputFile.split("\r\n(\r\n)+"));
		
		for (String i:fileList) {
			lineblocks.add(new LineBlock(i));
		}
	}
}
