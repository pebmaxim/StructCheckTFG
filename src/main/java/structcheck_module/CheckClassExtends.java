package structcheck_module;

import java.util.SortedSet;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class CheckClassExtends extends CheckToken{
	
	public CheckClassExtends(String className, String extension,
			String violationMessage) {
		super(className, TokenTypes.CLASS_DEF, extension, TokenTypes.EXTENDS_CLAUSE, violationMessage);
	}

	public CheckClassExtends(String className, String extension) {
		super(className, TokenTypes.CLASS_DEF, extension, TokenTypes.EXTENDS_CLAUSE,
				"Class " + className + " should extend " +
						extension);
	}
	
	@Override
	public SortedSet<Violation> process() {
		super.process();
		SortedSet<Violation> violations = checkExtends();
		return violations;
	}

}
