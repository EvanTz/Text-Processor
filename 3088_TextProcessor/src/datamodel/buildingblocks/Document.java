package datamodel.buildingblocks;

import java.util.ArrayList;
import java.util.List;

public class Document {
	private List<LineBlock> lineblocks;
	private DocumentRawType docType;
	
	public Document(String pFilePath, DocumentRawType docType) {
		this.lineblocks = new ArrayList<>();
		this.docType = docType;
	}

	public enum DocumentRawType {
		RAW, ANNOTATED
	}

	public List<LineBlock> getLineblocks() {
		return this.lineblocks;
	}
	
	public void setLineblocks(List<LineBlock> lineblocksin) {
		this.lineblocks = lineblocksin;
	}

	public Object getInputFileType() {
		return docType;
	}

}
