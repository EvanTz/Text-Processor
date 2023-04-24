package exporters;

import datamodel.buildingblocks.Document;
import datamodel.buildingblocks.LineBlock;
import datamodel.buildingblocks.StyleEnum;
//import datamodel.buildingblocks.FormatEnum;
import java.io.FileWriter;
import java.io.IOException;

public class MarkdownExporter {
	private Document doc;
	private String outName;
	private FileWriter outWriter;
	private int numPar = 0;

	public MarkdownExporter(Document document, String outputFileName) {
		this.doc = document;
		this.outName = outputFileName;
	}

	public int export() {

		try {
			outWriter = new FileWriter(outName);
			
			for(LineBlock l: doc.getLineblocks()) {
				// System.out.println(l.getParagraphString() +"\n"+ l.getStyle() +"\n"+ l.getFormat());
				switch(l.getStyle()) {
				case OMITTED:
					break;
				case H2:
					//outWriter.write("##"+l.getParagraphString().replaceAll("\r\n", " ").replaceAll("\n", "\r\n")+" \r\n\r\n");  // crlf
					outWriter.write("##"+l.getParagraphString().replaceAll("\r\n", " ")+" \n\n");  // lf
					numPar++;
					break;
				case H1:
					//outWriter.write("#"+l.getParagraphString().replaceAll("\r\n", " ").replaceAll("\n", "\r\n")+" \r\n\r\n");  // crlf
					outWriter.write("#"+l.getParagraphString().replaceAll("\r\n", " ")+" \n\n");  // lf
					numPar++;
					break;
				default: 
					;
				}
				
				if (l.getStyle() == StyleEnum.NORMAL) {
					switch(l.getFormat()) {
					case BOLD:
						//outWriter.write("**"+l.getParagraphString().replaceAll("\r\n", " ").replaceAll("\n", "\r\n")+" **\r\n\r\n");  // crlf
						outWriter.write("**"+l.getParagraphString().replaceAll("\r\n", " ")+" **\n\n");  // lf
						numPar++;
						break;
					case ITALICS:
						//outWriter.write("_"+l.getParagraphString().replaceAll("\r\n", " ").replaceAll("\n", "\r\n")+"_\r\n\r\n");  // crlf
						outWriter.write("_"+l.getParagraphString().replaceAll("\r\n", " ")+"_\n\n");  // lf
						numPar++;
						break;
					default:
						//outWriter.write(l.getParagraphString().replaceAll("\r\n", " ").replaceAll("\n", "\r\n")+" \r\n\r\n");  // crlf
						outWriter.write(l.getParagraphString().replaceAll("\r\n", " ")+" \n\n");  // lf
						numPar++;
					}
				}
			}
			
			outWriter.close();
		}catch (IOException e) {
			System.out.println("Error creating output file.");
			e.printStackTrace();
		}
		
		return numPar;
	}

}
