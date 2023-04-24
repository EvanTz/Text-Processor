package client;

//import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import engine.Engine;
import engine.IPlainTextDocumentEngine;

public class ClientUI {

	public static List<String> prefixes = new ArrayList<String>(); 
	
	/**
	 * File Input Window
	 * @return the path to the selected file
	 */
	public static String getFileInput() {
		JFileChooser chooseFile = new JFileChooser("Resources\\SampleDocs");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Plain Text or Html File", "txt", "html");
		chooseFile.setFileFilter(filter);
		int returnVal = chooseFile.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			//Input File Path
			System.out.println("You chose to open this file: " + chooseFile.getSelectedFile().getAbsolutePath());
			String inputFile = chooseFile.getSelectedFile().getAbsolutePath();
			
			if(!chooseFile.getSelectedFile().exists() || chooseFile.getSelectedFile().isDirectory()) {
				System.out.println("FILE DOES NOT EXIST");
				return "";
			}
			
			if(inputFile.endsWith(".html") || inputFile.endsWith(".txt")) {
				
				return inputFile;
			}
			
			System.out.println("WRONG FILE TYPE SELECTED");
			return "";
		}
		
		System.out.println("NO FILE SELECTED");
		return "";
	}
	
	
	public static int printMenu() {
		int answer = 0;
		Scanner input = new Scanner(System.in);
		while(answer > 9 || answer <= 0) {
			System.out.println("Choose(1-6)\n 1. Import File\n 2. Add Rule\n 3. Add prefix without rule to remove (only for annotated files)\n "
					+ "4. Confirm Rule Set\n "
					+ "5. Load File And Characterize Blocks\n 6. Show File Statistics Report\n "
					+ "7. Export to Markdown\n" + " 8. Export to PDF\n" + " 9. Exit");
			
			try {
				answer = input.nextInt();
				input.nextLine();
				if(answer > 9 || answer <= 0) {
					System.out.println("Wrong answer! Try again...");
				}
			}
			catch(Exception e){
				System.out.println("Wrong answer type! Try again...");
				input = new Scanner(System.in);
			}
		}
		return answer;
	}
	
	
	public static int printTypeMenu() {
		int answer = 0;
		Scanner input = new Scanner(System.in);
		while(answer > 2 || answer <= 0) {
			System.out.println("Choose(1-2)\n 1. File is not annotated.\n 2. File is annotated.\n");
			
			try {
				answer = input.nextInt();
				input.nextLine();
				if(answer > 2 || answer <= 0) {
					System.out.println("Wrong answer! Try again...");
				}
			}
			catch(Exception e){
				System.out.println("Wrong answer type! Try again...");
				input = new Scanner(System.in);
			}
		}
		
		return answer;
	} 


	public static String getAlias() {
		String aliasInput = " ";
		Scanner input = new Scanner(System.in);
		while(aliasInput.contains(" ")) {
			System.out.println("Please input an alias for the imported file.\n");
			aliasInput = input.nextLine();
			if (aliasInput.contains(" ")) System.out.println("Name should not contain spaces.");
		}
		System.out.printf("You chose %s as the alias.\n",aliasInput);
		return aliasInput;
	}
	
	
	public static List<String> addRuleMenu(String fType){
		String selection = "";
		List<String> rule = new ArrayList<String>();
		rule.add(getCategoryOfRule());
		
		if(fType == "RAW") {
			selection = getPositionStartsWithAllCaps();
		}
		else {
			selection = "STARTS_WITH";
			System.out.println("Putting Starts With automatically since annotated file was selected.");
		}
		rule.add(selection);
		
		if(selection != "ALL_CAPS") {
			rule.add(getSelectPrefixOrPositions(selection, fType));
		}
		else rule.add(null);
		
		return rule;
	}
	
	
	public static String getCategoryOfRule() {
		int answer = 0;
		Scanner input = new Scanner(System.in);
		while(answer > 5 || answer <= 0) {
			System.out.println("Choose(1-5) which rule you wish to add:\n Style Rules:\n  1. OMIT\n  2. H1\n  3. H2\n "
					+ "Format Rules:\n  4. Bold\n  5. Italics\n");
			
			try {
				answer = input.nextInt();
				input.nextLine();
				if(answer > 5 || answer <= 0) {
					System.out.println("Wrong answer! Try again...");
				}
			}
			catch(Exception e){
				System.out.println("Wrong answer type! Try again...");
				input = new Scanner(System.in);
			}
		}
		switch(answer) {
		case 1:
			return "OMIT";
		case 2:
			return "H1";
		case 3:
			return "H2";
		case 4:
			return "<B>";
		case 5:
			return "<I>";
		}
		return null;
	}
	
	
	public static String getPositionStartsWithAllCaps() {
		int answer = 0;
		Scanner input = new Scanner(System.in);
		while(answer > 3 || answer <= 0) {
			System.out.println("Choose(1-3) position(s) OR starts_with OR all_caps:\n 1. Position(s)\n 2. Starts_With\n 3. All_Caps\n");
			
			try {
				answer = input.nextInt();
				input.nextLine();
				if(answer > 3 || answer <= 0) {
					System.out.println("Wrong answer! Try again...");
				}
			}
			catch(Exception e){
				System.out.println("Wrong answer type! Try again...");
				input = new Scanner(System.in);
			}
		}
		switch(answer) {
		case 1:
			return "POSITIONS";
		case 2:
			return "STARTS_WITH";
		case 3:
			return "ALL_CAPS";
		}
		return null;
	}

	
	public static String getSelectPrefixOrPositions(String posStWth, String fType) {
		if (posStWth == "POSITIONS") {
			//select position(s)
			Scanner input = new Scanner(System.in);
			String pos = " ";
			while(!pos.matches("(0|[1-9][0-9]*)(,(0|[1-9][0-9]*))*")) {
				System.out.println("Please input the position(s) (>=0) to apply the rule separated with commas.\n");
				pos = input.nextLine();
				if (!pos.matches("(0|[1-9][0-9]*)(,(0|[1-9][0-9]*))*")) System.out.println("Improper input. Please follow instructions.");
			}
			return pos;
		}
		else {
			// just select prefix(?es)
			Scanner input = new Scanner(System.in);
			String prefix = "";
			while(prefix == "") {
				System.out.println("Please input the prefix to apply the rule.\n");
				prefix = input.nextLine();
				if (prefix == "") System.out.println("No prefix selected. Please follow instructions.");
			}
			//return prefix;
			
			if(fType == "RAW") {
				return prefix;
			}
			else {
				// select prefix AND put it in the List<String> prefixes
				prefixes.add(prefix);
				return prefix;
			}
		}
	}
	
	
	public static void getPrefixOnly() {
		Scanner input = new Scanner(System.in);
		String prefix = "";
		System.out.println("Please input a prefix without rule to remove it from output.\n");
		prefix = input.nextLine();
		if (prefix == "") System.out.println("No prefix input.");
		else prefixes.add(prefix);
	}
	
	
	public static void main(String[] args) {
		
		String fileType = "RAW";
		String inFilePath = "";
		String alias = "";
		int selection = 0;
		List<List<String>> rulesList = new ArrayList<>();
		String[] splitInFileName;
		String outputFileName;
		
		IPlainTextDocumentEngine mainEngine = null;
		
		
		while(true) {
			selection = printMenu();
			
			if (selection == 1) {
				if(inFilePath == "") {
					inFilePath = getFileInput();
					if(inFilePath != "" && printTypeMenu() == 2) {
						fileType = "ANNOTATED";
					}
					if(inFilePath != "") {
						alias = getAlias();
						
						mainEngine = new Engine(inFilePath, fileType, alias);
					}
				}else System.out.println("File already selected. Please choose another option.");
				
			}
			
			else if(selection == 2) {
				if(inFilePath != "") {
					
					rulesList.addAll(Arrays.asList(addRuleMenu(fileType)));
					
					System.out.println("Rule Set: " + rulesList.toString()+"\n"+"Prefixes(only for annotated files): "+prefixes.toString());
				}
				else System.out.println("No file imported. Please import a file first.");
			}
			
			else if(selection == 3) {
				if(inFilePath != "") {
					if(fileType == "ANNOTATED") {
						getPrefixOnly();
					}
					else System.out.println("Prefix list is only for annotated files.");
				}
				else System.out.println("No file imported. Please import a file first.");
			}
			
			else if(selection == 4) {
				if(inFilePath != "") {
					if(fileType == "RAW") {
						
						mainEngine.registerInputRuleSetForPlainFiles(rulesList);
						
					}
					else {
						
						mainEngine.registerInputRuleSetForAnnotatedFiles(rulesList, prefixes);
						
					}
					System.out.println("Rule Set: " + rulesList.toString()+"\n"+"Prefixes(only for annotated files): "+prefixes.toString());
					System.out.println("Rule Set Confirmed!");
				}
				else System.out.println("No file imported. Please import a file first.");
			}
			
			else if(selection == 5) {
				if (inFilePath != "") {
					if(rulesList.isEmpty()) System.out.println("No rules set!");
					
					mainEngine.loadFileAndCharacterizeBlocks();
					
					System.out.println("File loaded and blocks characterized!");
				} else
					System.out.println("No file imported. Please import a file first.");
			}
			
			else if(selection == 6) {
				if(inFilePath != "") {
					System.out.println("File statistics report:\n");
					
					List<String> s = mainEngine.reportWithStats();
					
					for (String i : s) System.out.print(i);
					System.out.print("\n");
					
				}
				else System.out.println("No file imported. Please import a file first.");
			}
			
			else if(selection == 7) {
				if(inFilePath != "") {
					splitInFileName = inFilePath.split("\\\\");
					outputFileName = ("Resources//Outputs//" + splitInFileName[splitInFileName.length-1]);
					
					mainEngine.exportMarkDown(outputFileName);
				}
				else System.out.println("No file imported. Please import a file first.");
			}
			
			else if(selection == 8) {
				if(inFilePath != "") {
					splitInFileName = inFilePath.split("\\\\");
					outputFileName = ("Resources//Outputs//" + splitInFileName[splitInFileName.length-1]);
					
					mainEngine.exportPdf(outputFileName);
				}
				else System.out.println("No file imported. Please import a file first.");
			}
			
			else if(selection == 9) {
				System.out.println("Exiting...Goodbye!");
				System.exit(0);
			}
		}
		
	}

}
