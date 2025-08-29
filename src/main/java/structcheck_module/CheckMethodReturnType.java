package structcheck_module;

import java.util.SortedSet;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/*
 * CheckMethodReturnType is used for checking the type of variable a method returns.
 */
public class CheckMethodReturnType extends CheckTokenInMethod{
	
	/**
	 * CheckMethodReturnType constructor with custom violation message.
	 * @param methodName Name of the method we're looking inside of.
	 * @param returnType Type of the variable the method should return.
	 * @param violationMessage Custom message to use in the log for the violation.
	 */
	public CheckMethodReturnType(String methodName, String returnType,
			String violationMessage) {
		super(methodName, returnType, TokenTypes.TYPE, violationMessage);
	}

	/**
	 * CheckMethodReturnType constructor without custom violation message.
	 * @param methodName Name of the method we're looking inside of.
	 * @param returnType Type of the variable the method should return.
	 */
	public CheckMethodReturnType(String methodName, String returnType) {
		super(methodName, returnType, TokenTypes.TYPE,
				"Method " + methodName + " should return type " +
						returnType);
	}
	
	/**
	 * Begins computation for the check.
	 */
	@Override
	public SortedSet<Violation> process() {
		super.process();
		SortedSet<Violation> violations = checkReturnType();
		return violations;
	}

}
