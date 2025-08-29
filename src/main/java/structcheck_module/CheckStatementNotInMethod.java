package structcheck_module;

import java.util.SortedSet;

/*
 * CheckStatementNotInMethod is used when checking that a given structure is
 * NOT present in a method.
 */
public class CheckStatementNotInMethod extends CheckTokenInMethod {

	/**
	 * CheckStatementNotInMethod constructor with custom violation message.
	 * @param methodName Name of the method we're looking inside of.
	 * @param statementType Type of statement the method is NOT supposed to have.
	 * @param violationMessage Custom message to use in the log for the violation.
	 */
	public CheckStatementNotInMethod(String methodName, int statementType,
			String violationMessage) {
		super(methodName, null, statementType, violationMessage);
	}

	/**
	 * CheckStatementNotInMethod constructor without custom violation message.
	 * @param methodName Name of the method we're looking inside of.
	 * @param statementType Type of statement the method is NOT supposed to have.
	 */
	public CheckStatementNotInMethod(String methodName, int statementType) {
		super(methodName, null, statementType,
				"Method " + methodName + " should NOT include any " +
						statementType);
	}

	/**
	 * Begins computation for the check.
	 */
	@Override
	public SortedSet<Violation> process() {
		super.process();
		SortedSet<Violation> violations = violationIfFindTarget();
		return violations;
	}

}
