package test;

//import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

//import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import datamodel.buildingblocks.LineBlock;
import datamodel.rules.ConcreteRuleFactory;
import datamodel.ruleset.RuleSetCreator;
import engine.Engine;
import engine.IPlainTextDocumentEngine;


public class TestDatamodel {
	private RuleSetCreator rsc;
	private static List<List<String>> inputSpec;
	private static List<String> omList; 
	private static List<String> h1List; 
	//private static List<String> h2List; 
	private static List<String> boldList; 
	//private static List<String> italicsList;
	private static List<LineBlock> lb = new ArrayList<>();
	private static IPlainTextDocumentEngine engine;
	
	private static void setUpWrongRulesSyntax() {
		inputSpec = new ArrayList<List<String>>();
		omList = new ArrayList<String>(); inputSpec.add(omList);
		omList.add("OMIT");omList.add("THIS IS WRONG");omList.add("1,2");  // wrong syntax
		h1List = new ArrayList<String>(); inputSpec.add(h1List);
		h1List.add("H1");h1List.add("STARTS_WITH"); h1List.add("POLITICAL ECONOMY");
		boldList = new ArrayList<String>(); inputSpec.add(boldList);
		boldList.add("<B>");boldList.add("ALL_CAPS");
	}
	
	private static void setUpWrongRulesNull() {
		inputSpec = new ArrayList<List<String>>();
		omList = new ArrayList<String>(); inputSpec.add(omList);
		omList.add("OMIT");omList.add("POSITIONS");omList.add("1,2");
		h1List = new ArrayList<String>(); inputSpec.add(h1List);
		h1List.add(null);h1List.add("STARTS_WITH"); h1List.add("POLITICAL ECONOMY"); // null syntax
		boldList = new ArrayList<String>(); inputSpec.add(boldList);
		boldList.add("<B>");boldList.add("ALL_CAPS");
	}
	
	private static void setUpAnnotatedFileRuleIsNotStartsWith() {
		inputSpec = new ArrayList<List<String>>();
		omList = new ArrayList<String>(); inputSpec.add(omList);
		omList.add("OMIT");omList.add("POSITIONS");omList.add("1,2");  // the rule is not STARTS_WITH

	}
	
	@Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

	@BeforeClass
	public static void setUp() {
	}
	
	@Test(timeout = 1000)
	public final void testWrongRulesSyntax() {
		System.out.println("[testWrongRulesSyntax]:");
		setUpWrongRulesSyntax();
		rsc = new RuleSetCreator(lb, inputSpec, "inputSpec");
		exit.expectSystemExitWithStatus(-100);
		rsc.createRuleSet();
	}
	
	@Test(timeout = 1000)
	public final void testWrongRulesNull() {
		System.out.println("[testWrongRulesNull]:");
		setUpWrongRulesNull();
		rsc = new RuleSetCreator(lb, inputSpec, "inputSpec");
		exit.expectSystemExitWithStatus(-1);
		rsc.createRuleSet();
	}
	
	@Test
	public final void testWrongRuleInPositionNull() {
		System.out.println("[testWrongRuleInPositionNull]:");
		ConcreteRuleFactory r = new ConcreteRuleFactory();
		r.createRuleInPosition(lb, null);
	}
	
	@Test
	public final void testWrongRuleStartWithNull() {
		System.out.println("[testWrongRuleStartWithNull]:");
		ConcreteRuleFactory r = new ConcreteRuleFactory();
		r.createRuleStartWith(null);
	}
	
	@Test
	public final void testAnnotatedFileRuleIsNotStartsWith() {
		System.out.println("[testAnnotatedFileRuleIsNotStartsWith]:");
		setUpAnnotatedFileRuleIsNotStartsWith();
		engine = new Engine("somefile","ANNOTATED","alias");
		List<String> prefixes = new ArrayList<>();
		prefixes.add("POLITICAL ECONOMY");
		engine.registerInputRuleSetForAnnotatedFiles(inputSpec, prefixes);
		
	}
}
