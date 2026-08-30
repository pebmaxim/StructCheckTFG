package structcheck_module;

import java.util.SortedSet;

/*
 * CheckStatementInMethod is used when looking for, to name an example, looping structures
 * inside of a method.
 */
public class CheckStatementInMethod extends CheckTokenInMethod {
	
	private int statementCount;

	/**
	 * CheckStatementInMethod constructor with custom violation message.
	 * @param methodName Name of the method we're looking inside of.
	 * @param statementType Type of statement the method is supposed to have.
	 * @param violationMessage Custom message to use in the log for the violation.
	 */
	public CheckStatementInMethod(String methodName, int statementType, int statementCount,
			String violationMessage) {
		super(methodName, null, statementType, violationMessage);
		this.statementCount = statementCount;
	}

	/**
	 * CheckStatementInMethod constructor without custom violation message.
	 * @param methodName Name of the method we're looking inside of.
	 * @param statementType Type of statement the method is supposed to have.
	 */
	public CheckStatementInMethod(String methodName, int statementType, int statementCount) {
		super(methodName, null, statementType,
				"Method " + methodName + " should include a " + statementType);
		this.statementCount = statementCount;
	}
	
	/**
	 * CheckStatementInMethod constructor with custom violation message.
	 * @param methodName Name of the method we're looking inside of.
	 * @param statementName Text that can be used to identify a specific statement.
	 * @param statementType Type of statement the method is supposed to have.
	 * @param violationMessage Custom message to use in the log for the violation.
	 */
	public CheckStatementInMethod(String methodName, String statementName, int statementType,
			int statementCount, String violationMessage) {
		super(methodName, statementName, statementType, violationMessage);
		this.statementCount = statementCount;
	}
	
	/**
	 * CheckStatementInMethod constructor without custom violation message.
	 * @param methodName Name of the method we're looking inside of.
	 * @param statementName Text that can be used to identify a specific statement.
	 * @param statementType Type of statement the method is supposed to have.
	 */
	public CheckStatementInMethod(String methodName, String statementName, int statementType,
			int statementCount) {
		super(methodName, statementName, statementType,
				"Method " + methodName + " should include a " + statementType);
		this.statementCount = statementCount;
	}

	/**
	 * Begins computation for the check.
	 */
	@Override
	public SortedSet<Violation> process() {
		super.process();
		SortedSet<Violation> violations = violationIfFindTarget();
		int statementsFound = violations.size();
		violations.clear();
		if (statementsFound != statementCount) {
			violations.add(new Violation(this.baseNode().getLineNo(), violationMessage()));
		}
		return violations;
	}

}
