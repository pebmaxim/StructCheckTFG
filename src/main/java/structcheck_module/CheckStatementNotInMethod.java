package structcheck_module;

import java.util.SortedSet;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import structcheck_module.Violation;

public class CheckStatementNotInMethod extends CheckTokenInMethod {

	public CheckStatementNotInMethod(String methodName, int statementType,
			String violationMessage) {
		super(methodName, null, statementType, violationMessage);
	}

	public CheckStatementNotInMethod(String methodName, int statementType) {
		super(methodName, null, statementType,
				"Method " + methodName + " should NOT include any " +
						statementType);
		// TODO: tranformar statementType a um string que describa el tipo de
		// token
	}

	@Override
	public SortedSet<Violation> process() {
		super.process();
		SortedSet<Violation> violations = violationIfFindTarget();
		return violations;
	}

}
