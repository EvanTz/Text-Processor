package datamodel.rules;

import datamodel.buildingblocks.LineBlock;

public class RuleAllCaps extends AbstractRule {
	private char[] ch;

	@Override
	public boolean isValid(LineBlock paragraph) {

		ch = paragraph.getParagraphString().toCharArray();
		
		for(int i=0; i < ch.length; i++) {
			if(Character.isLowerCase(ch[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return ("Rule All Caps");
	}

}
