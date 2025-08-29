package structcheck_module;

import java.util.SortedSet;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import structcheck_module.Violation;

/*
 * CheckStatementInMethod is used when looking for, to name an example, looping structures
 * inside of a method.
 */
public class CheckStatementInMethod extends CheckTokenInMethod {

	/**
	 * CheckStatementInMethod constructor with custom violation message.
	 * @param methodName Name of the method we're looking inside of.
	 * @param statementType Type of statement the method is supposed to have.
	 * @param violationMessage Custom message to use in the log for the violation.
	 */
	public CheckStatementInMethod(String methodName, int statementType,
			String violationMessage) {
		super(methodName, null, statementType, violationMessage);
	}

	/**
	 * CheckStatementInMethod constructor without custom violation message.
	 * @param methodName Name of the method we're looking inside of.
	 * @param statementType Type of statement the method is supposed to have.
	 */
	public CheckStatementInMethod(String methodName, int statementType) {
		super(methodName, null, statementType,
				"Method " + methodName + " should include a " + statementType);
	}

	/**
	 * Begins computation for the check.
	 */
	@Override
	public SortedSet<Violation> process() {
		super.process();
		SortedSet<Violation> violations = violationIfNotFindTarget();
		return violations;
	}

}
