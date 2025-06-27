package structcheck_module;

import java.util.SortedSet;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class CheckMethodReturnType extends CheckTokenInMethod{
	
	public CheckMethodReturnType(String methodName, String returnType,
			String violationMessage) {
		super(methodName, returnType, TokenTypes.TYPE, violationMessage);
	}

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
