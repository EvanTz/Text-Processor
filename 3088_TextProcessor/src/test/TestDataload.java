package test;

//import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

//import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dataload.RawFileLineLoader;
import datamodel.buildingblocks.LineBlock;

public class TestDataload {
	private static RawFileLineLoader l;
	private String correctPath = "Resources\\\\SampleDocs\\\\adam_mt.txt";
	private String wrongPath = "Resources\\\\SampleDocs\\\\thisfiledoesnotexist.txt";
	private static List<LineBlock> lb = new ArrayList<>();
	
	@BeforeClass
	public static void setUp() throws Exception{
		l = new RawFileLineLoader();
	}
	
	@Test
	public final void checkCorrectPath() {
		l.load(correctPath, lb);
	}
	
	@Test
	public final void checkWrongPath() {
		System.out.println("Test loading file that does not exist [checkWrongPath]:");
		l.load(wrongPath, lb);
	}
	
}
