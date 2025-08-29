package structcheck_module;

import java.util.SortedSet;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import structcheck_module.Violation;

/*
 * CheckMethodInMethod is used when you're looking for the use of a method
 * inside another method.
 */
public class CheckMethodInMethod extends CheckTokenInMethod {

	/**
	 * CheckMethodInMethod constructor with custom violation message.
	 * @param methodName Name of the method we're looking inside of.
	 * @param targetMethodName Name of the method we're checking is used.
	 * @param violationMessage Custom message to use in the log for the violation.
	 */
	public CheckMethodInMethod(String methodName, String targetMethodName,
			String violationMessage) {
		super(methodName, targetMethodName, TokenTypes.METHOD_CALL,
				violationMessage);
	}
	
	/**
	 * CheckMethodInMethod constructor without custom violation message.
	 * @param methodName Name of the method we're looking inside of.
	 * @param targetMethodName Name of the method we're checking is used.
	 */
	public CheckMethodInMethod(String methodName, String targetMethodName) {
		super(methodName, targetMethodName, TokenTypes.METHOD_CALL,
				"Method " + methodName + " should call method " +
				targetMethodName);
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
