package structcheck_module;

import java.util.SortedSet;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.api.Violation;

public class CheckTokenInMethod extends CheckToken {

	public CheckTokenInMethod(String methodName, String targetName,
			int targetType, String violationMessage) {
		super(methodName, TokenTypes.METHOD_DEF, targetName, targetType,
				violationMessage);
	}
	
	public CheckTokenInMethod(String methodName, String targetName,
			int targetType) {
		super(methodName, TokenTypes.METHOD_DEF, targetName, targetType,
				"Method " + methodName + " should include " +
						targetName + " " + targetType);
	}

	@Override
	public void process() {
		super.process();
	}

}
