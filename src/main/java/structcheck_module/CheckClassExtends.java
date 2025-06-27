package structcheck_module;

import java.util.SortedSet;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/*
 * CheckClassExtends is used for checks that involve class extensions, such as
 * exceptions extending RuntimeException or <Class> extends Comparable
 */
public class CheckClassExtends extends CheckToken{
	
	/**
	 * CheckClassExtends constructor with custom violation message.
	 * @param className Name of the class we're checking for extensions.
	 * @param extension The name of the extension the class should have.
	 * @param violationMessage Custom message to use in the log for the violation.
	 */
	public CheckClassExtends(String className, String extension,
			String violationMessage) {
		super(className, TokenTypes.CLASS_DEF, extension, TokenTypes.EXTENDS_CLAUSE, violationMessage);
	}
	
	/**
	 * CheckClassExtends constructor without custom violation message.
	 * @param className Name of the class we're checking for extensions.
	 * @param extension The name of the extension the class should have.
	 */
	public CheckClassExtends(String className, String extension) {
		super(className, TokenTypes.CLASS_DEF, extension, TokenTypes.EXTENDS_CLAUSE,
				"Class " + className + " should extend " +
						extension);
	}
	
	/**
	 * Begins computation for the check.
	 */
	@Override
	public SortedSet<Violation> process() {
		super.process();
		SortedSet<Violation> violations = checkExtends();
		return violations;
	}

}
