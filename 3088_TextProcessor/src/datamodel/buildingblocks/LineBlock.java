package datamodel.buildingblocks;

import java.util.Arrays;
import java.util.List;

public class LineBlock {
	private String lineblock;
	private List<String> lines;
	private StyleEnum style;
	private FormatEnum format;
	private int numWords = 0;

	public LineBlock(String lb) {
		this.lineblock = lb;
		setStyle(StyleEnum.NORMAL);
		setFormat(FormatEnum.REGULAR);
		this.lines = Arrays.asList(lineblock.split("\r\n"));
	}
	
	public String getParagraphString() {
		return this.lineblock;
	}
	
	public String getStatsAsString() {
		if (lines == null) getLines();
		if (numWords == 0) getNumWords();
		String tS = String.format("Lines: %d		Words: %d",lines.size(), numWords);
		return tS;
	}

	public int getNumWords() {
		if (lines == null) getLines();
		if(numWords == 0) {
			for(int i=0; i<lines.size();i++) {
				numWords += (lines.get(i).split("\\s+").length);
			}
		}
		return numWords;
	}

	public void setStyle(StyleEnum determineHeadingStatus) {
		style = determineHeadingStatus;
		
	}
	
	public StyleEnum getStyle() {
		return style;
	}
	
	public void setFormat(FormatEnum determineFormatStatus) {
		format = determineFormatStatus;
		
	}
	
	public FormatEnum getFormat() {
		return format;
	}

	public List<String> getLines() {
		return lines;
	}
	
	public void setLines(int index, String value) {
		this.lines.set(index, value);
		updateParagraph();
	}

	// Update the paragraph after removing the prefix for annotated files only.
	private void updateParagraph() {
		this.lineblock = String.join(",", lines);
	}
}
