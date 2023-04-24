package exporters;

import datamodel.buildingblocks.Document;
import datamodel.buildingblocks.LineBlock;
import datamodel.buildingblocks.StyleEnum;

import com.itextpdf.text.pdf.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.*;


public class PdfExporter {
	private Document doc;
	private String outName;
	private int numPar = 0;
	private Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12);
	private Font h1Font = new Font(Font.FontFamily.TIMES_ROMAN, 18);
	private Font h2Font = new Font(Font.FontFamily.TIMES_ROMAN, 16);
	private Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
	private Font italicsFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.ITALIC);
	

	public PdfExporter(Document document, String outputFileName) {
		this.doc = document;
		this.outName = outputFileName;
	}

	public int export() {
		com.itextpdf.text.Document finalDoc = new com.itextpdf.text.Document();
		try {
			PdfWriter.getInstance(finalDoc, new FileOutputStream(outName));
		} catch (FileNotFoundException e1) {
			System.out.println("PDFWriter Error");
			e1.printStackTrace();
		} catch (DocumentException e1) {
			System.out.println("PDFWriter Error");
			e1.printStackTrace();
		}
		finalDoc.open();
		try {
			for(LineBlock l: doc.getLineblocks()) {
				//System.out.println(l.getParagraphString() +"\n"+ l.getStyle() +"\n"+ l.getFormat());
				Paragraph par = new Paragraph();
				
				switch(l.getStyle()) {
				case OMITTED:
					break;
				case H2:
					par.add(new Paragraph(l.getParagraphString().replaceAll("\r\n", " "), h2Font));
					finalDoc.add( Chunk.NEWLINE);
					numPar++;
					break;
				case H1:
					par.add(new Paragraph(l.getParagraphString().replaceAll("\r\n", " "), h1Font));
					finalDoc.add( Chunk.NEWLINE);
					numPar++;
					break;
				default: 
					;
				}
				
				if (l.getStyle() == StyleEnum.NORMAL) {
					switch(l.getFormat()) {
					case BOLD:
						par.add(new Paragraph(l.getParagraphString().replaceAll("\r\n", " "), boldFont));
						finalDoc.add(Chunk.NEWLINE);
						numPar++;
						break;
					case ITALICS:
						par.add(new Paragraph(l.getParagraphString().replaceAll("\r\n", " "), italicsFont));
						finalDoc.add(Chunk.NEWLINE);
						numPar++;
						break;
					default:
						par.add(new Paragraph(l.getParagraphString().replaceAll("\r\n", " "), normalFont));
						finalDoc.add(Chunk.NEWLINE);
						numPar++;
					}
				}
				finalDoc.add(par);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		finalDoc.close();
		return numPar;
	}

}
