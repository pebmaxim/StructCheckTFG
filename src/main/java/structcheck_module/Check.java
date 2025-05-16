package structcheck_module;

import java.util.SortedSet;
import java.util.TreeSet;

import com.puppycrawl.tools.checkstyle.api.*;
import structcheck_module.Violation;

public abstract class Check {
	// Token on which the check is performed: it can be a method, a class, etc.
	private final Token baseToken;
	
	// Node corresponding to the base token. It cannot be assigned in the
	// constructor, since it must first be looked up in the AST. It is assigned
	// using setBaseNode().
	private DetailAST baseNode = null;
	
	// Message to be displayed when a check violation is found.
	private final String violationMessage;
	private boolean checkCleared;
	private SortedSet<Violation> violations;
	
	/**
	 * @param baseName
	 * @param baseType
	 */
	public Check(String baseName, int baseType, String violationMessage) {
		super();
		baseToken = new Token(baseName, baseType);
		this.violationMessage = violationMessage;
		this.checkCleared = false;
		this.violations = new TreeSet<Violation>();
	}
	
	/**
	 * @return the baseToken
	 */
	public Token baseToken() {
		return baseToken;
	}
	
	/**
	 * Confirm that this check has been cleared.
	 */
	public void clearCheck() {
		this.checkCleared = true;
	}
	
	/**
	 * Returns whether the check has been cleared or not.
	 * @return whether the check has been cleared or not.
	 */
	public boolean checkCleared() {
		return this.checkCleared;
	}

	/**
	 * Returns the message describing the violation of the check.
	 * @return the message describing the violation of the check.
	 */
	public String violationMessage() {
		return violationMessage;
	}

	/**
	 * @return the baseNode
	 */
	public DetailAST baseNode() {
		return baseNode;
	}

	/**
	 * @param baseNode the baseNode to set
	 */
	public void setBaseNode(DetailAST baseNode) {
		this.baseNode = baseNode;
	}

	/**
	 * Check to be performed on the base node. If the check fails, one or more
	 * violations are returned.
	 * The base node must have been assigned (with {@link #setBaseNode()})
	 * before calling this method.
	 * @return a set with the check violations found (an empty set is returned
	 * if no violations are found).
	 * XXX ¿mejor retornar null si no hay ninguna violation?
	 */
	public SortedSet<Violation> process() {
		this.checkCleared = true;
		return violations;
	}
	

}
