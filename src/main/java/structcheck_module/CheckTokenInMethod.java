package structcheck_module;

import java.util.SortedSet;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import structcheck_module.Violation;

/*
 * CheckTokenInMethod is used when looking for specific items inside of
 * a method. Generally used as a parent class for other check types.
 */
public class CheckTokenInMethod extends CheckToken {

	/**
	 * CheckTokenInMethod constructor with custom violation message.
	 * @param methodName Name of the method we're looking inside of.
	 * @param targetName Name of the target token (its text).
	 * @param targetType Type of the target token.
	 * @param violationMessage Custom message to use in the log for the violation.
	 */
	public CheckTokenInMethod(String methodName, String targetName,
			int targetType, String violationMessage) {
		super(methodName, TokenTypes.METHOD_DEF, targetName, targetType,
				violationMessage);
	}
	
	/**
	 * CheckTokenInMethod constructor without custom violation message.
	 * @param methodName Name of the method we're looking inside of.
	 * @param targetName Name of the target token (its text).
	 * @param targetType Type of the target token.
	 */
	public CheckTokenInMethod(String methodName, String targetName,
			int targetType) {
		super(methodName, TokenTypes.METHOD_DEF, targetName, targetType,
				"Method " + methodName + " should include " +
						targetName + " " + targetType);
	}

	/**
	 * Begins computation for the check.
	 */
	@Override
	public SortedSet<Violation> process() {
		return super.process();
	}

}
