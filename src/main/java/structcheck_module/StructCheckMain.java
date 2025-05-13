package structcheck_module;

import com.puppycrawl.tools.checkstyle.api.*;

import structchecks.StructChecks;
import java.util.ArrayList;

public class StructCheckMain extends AbstractCheck {
	
	public int[] getDefaultTokens() {
		int[] tokens = new int[structchecks.StructChecks.checks.length];
		int i = 0;
		for (Check check: StructChecks.checks) {
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
		for (Check check: StructChecks.checks) {
			if (check.checkCleared()) {
				continue;
			}
			int checkType = check.baseToken().type();
			String[] checkName = check.baseToken().name().split("\\.");
			if (astType == checkType && astText.equals(check)) {
				
			} // If the name is Class.Method, separate them
			if (astType == checkType) { // If the ast and the check aren't looking in the same place, we should move on.
				
				// Might not be necessary to use switch
				switch(astType) {
					case(TokenTypes.CLASS_DEF):
						if (astText.equals(checkName[0])) {
							check.setBaseNode(ast);
							check.process();
							break;
						}
					case(TokenTypes.METHOD_DEF):
						// If the ast is a method, this gives us the identification of the class it belongs to.
						String astFather = ast.getParent().getPreviousSibling().getText();
						if (astFather.equals(checkName[0]) && astText.equals(checkName[1])) {
							check.setBaseNode(ast);
							check.process();
							break;
						}
				}
			}
					
		}
	}
	
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
