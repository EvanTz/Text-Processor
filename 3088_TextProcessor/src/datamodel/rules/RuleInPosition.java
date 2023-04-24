package datamodel.rules;

import java.util.List;
import java.util.ArrayList;

import datamodel.buildingblocks.LineBlock;

public class RuleInPosition extends AbstractRule {
	private List<LineBlock> pLineblocks;
	private List<Integer> pPositions;
	private List<String> sPos;

	public RuleInPosition(List<LineBlock> pLineblocks, List<Integer> pPositions) {
		this.pLineblocks = pLineblocks;
		this.pPositions = pPositions;
		this.sPos = new ArrayList<>();
	}

	@Override
	public boolean isValid(LineBlock paragraph) {
		for(LineBlock l: pLineblocks) {
			if(paragraph == l && pPositions.contains(pLineblocks.indexOf(l))) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		for(int i:pPositions) {
			sPos.add(String.valueOf(i));
		}
		String out = String.format("Rule in position(s): %s", String.join(",",sPos));
		return out;
	}

}
