package structcheck_module;

import java.util.SortedSet;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import structcheck_module.Violation;

public class CheckStatementInMethod extends CheckTokenInMethod {

	public CheckStatementInMethod(String methodName, int statementType,
			String violationMessage) {
		super(methodName, null, statementType, violationMessage);
	}

	public CheckStatementInMethod(String methodName, int statementType) {
		super(methodName, null, statementType,
				"Method " + methodName + " should include a " + statementType);
	}

	@Override
	public SortedSet<Violation> process() {
		super.process();
		SortedSet<Violation> violations = violationIfNotFindTarget();
		return violations;
	}

}
