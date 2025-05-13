package structcheck_module;

import java.util.SortedSet;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.api.Violation;

public class CheckMethodInMethod extends CheckTokenInMethod {

	public CheckMethodInMethod(String methodName, String targetMethodName,
			String violationMessage) {
		super(methodName, targetMethodName, TokenTypes.METHOD_DEF,
				violationMessage);
	}

	public CheckMethodInMethod(String methodName, String targetMethodName) {
		super(methodName, targetMethodName, TokenTypes.METHOD_DEF,
				"Method " + methodName + " should call method " +
				targetMethodName);
	}

	@Override
	public void process() {
		
	}

}
