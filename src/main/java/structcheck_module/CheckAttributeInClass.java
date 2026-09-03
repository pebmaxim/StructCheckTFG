package structcheck_module;

import java.util.SortedSet;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class CheckAttributeInClass extends CheckToken {
	
	public CheckAttributeInClass(String className, String attributeTypeName, String violationMessage) {
		super(className, TokenTypes.CLASS_DEF, attributeTypeName, TokenTypes.VARIABLE_DEF, violationMessage);
	}
	
	public CheckAttributeInClass(String className, String attributeTypeName) {
		super(className, TokenTypes.CLASS_DEF, attributeTypeName, TokenTypes.VARIABLE_DEF,
				"Class " + className + " should have a " + attributeTypeName + " attribute.");	
	}
	
	/**
	 * Begins computation for the check.
	 */
	@Override
	public SortedSet<Violation> process() {
		super.process();
		return violationIfNotFindTarget();
	}

}
