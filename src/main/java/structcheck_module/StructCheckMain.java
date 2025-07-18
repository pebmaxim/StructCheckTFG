package structcheck_module;

import com.puppycrawl.tools.checkstyle.api.*;

import java.lang.reflect.Field;
import java.util.SortedSet;
import java.util.TreeSet;

public class StructCheckMain extends AbstractCheck {
	
	public static Check[] checks;
	
	/*
	 * Loads the .class file with the checks. In case of error, a message is printed and the value of the list is null
	 * and, therefore, a NullPointerException happens at the start of the processing.
	 */
	static {
        Check[] loadedChecks = null;
        try {
            Class<?> configClass = Class.forName("tests.StructChecks");
            Field checksField = configClass.getField("checks");
            loadedChecks = (Check[]) checksField.get(null);
        } catch (Exception e) {
            System.err.println("FAILED TO LOAD CHECKS: " + e);
        }
        checks = loadedChecks;
    }
	
	/*
	 * Iterates through the list of checks, looking for the tokens whose subtrees it must explore.
	 */
	public int[] getDefaultTokens() {
		int[] tokens = new int[checks.length];
		int i = 0;
		for (Check check: checks) {
			tokens[i] = check.baseToken().type();
			i++;
		}
		return tokens;
	}
	
	/*
	 * Called automatically following getDefaultTokens() for every subtree saved in said call
	 */
	@Override
	public void visitToken(DetailAST ast) {
		int astType = ast.getType();
		String astText = ast.findFirstToken(TokenTypes.IDENT).getText(); // Get the object's identification
		for (Check check: checks) {
			if (check.checkCleared()) {
				continue;
			}
			int checkType = check.baseToken().type();
			String[] checkName = check.baseToken().name().split("\\."); // Separate the method from the class it belongs to
			if (astType == checkType) { // If the AST and the check aren't looking in the same place, we should move on.
				SortedSet<Violation> violations = new TreeSet<Violation>();
				// Might not be necessary to use switch
				switch(astType) {
					case(TokenTypes.CLASS_DEF):
						if (astText.equals(checkName[0])) {
							check.setBaseNode(ast);
							violations.addAll(check.process());
							break;
						}
					case(TokenTypes.METHOD_DEF):
						// If the ast is a method, this gives us the identification of the class it belongs to.
						String astFather = ast.getParent().getPreviousSibling().getText();
						if (astFather.equals(checkName[0]) && astText.equals(checkName[1])) {
							check.setBaseNode(ast);
							violations.addAll(check.process());
							break;
						}
				}
				for (Violation violation: violations) {
					log(violation.lineNo(), violation.message());
				}
			}
					
		}
		/*
		 * Loads all the violations to be printed in the terminal.
		 */
		
	}
	
	/*
	 * These methods are required by AbstractCheck. They're not used for the purposes of this module.
	 */
	@Override
	public int[] getRequiredTokens() {
		int[] a = {-1};
		return a;
	}
	
	@Override
	public int[] getAcceptableTokens() {
		int[] a = {-1};
		return a;
	}
}
