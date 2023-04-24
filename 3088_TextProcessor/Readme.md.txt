Ευάγγελος Τζώρτζης
ΑΜ: 3088

Libraries Used:
	JUnit4 used for testing
	System-rules used in junit tests
	Various javafx libraries used for selecting file path (JFileChooser used in ClientUI)
	Itext5 used in the pdf file creation


Tests:

AllTests runs all the test classes that are mentioned below.

	TestEngine tests complete and correct usage of the program, checking all correct test cases for all classes. Basically the Happy Day Scenario.

	TestDatamodel tests wrong/null inputs in various use/test cases regarding the datamodel package classes as well as the registerInputRuleSetForAnnotatedFiles method in engine.

	TestDataload tests wrong path as input as well as a happy day scenario for loading file.


Notes:
	The ClientUI class implements the interface with the user. There are multiple checks for errors.