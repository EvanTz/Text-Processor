package datamodel.rules;

import datamodel.buildingblocks.LineBlock;

public class RuleStartWith extends AbstractRule {
	private String prefix;
	private char[] pr;
	private char[] ch;

	public RuleStartWith(String prefix) {
		this.prefix = prefix;
	}

	@Override
	public boolean isValid(LineBlock paragraph) {

		pr = prefix.toCharArray();
		ch = paragraph.getParagraphString().toCharArray();
		
		for(int i=0; i <pr.length; i++) {
			if(pr[i] != ch[i]) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return ("Rule starts with prefix: " + prefix);
	}

}
