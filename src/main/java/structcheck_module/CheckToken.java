package structcheck_module;

import java.util.Set;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import structcheck_module.Violation;

public abstract class CheckToken extends Check {
	// Token to be found/not found in the base token of this check.
	private final Token targetToken;
	
	/**
	 * @param baseName
	 * @param baseType
	 * @param targetName
	 * @param targetType
	 */
	public CheckToken(String baseName, int baseType,
			String targetName, int targetType, String violationMessage) {
		super(baseName, baseType, violationMessage);
		this.targetToken = new Token(targetName, targetType);
	}

	/**
	 * @return the targetToken
	 */
	public Token targetToken() {
		return targetToken;
	}

	/**
	 * Find the occurences of the target token of this check in its base node.
	 * @param baseNode node where look for the occurrences. 
	 * @return the nodes that match the target token of this check.
	 */
	private Set<DetailAST> findTargetToken(Set<DetailAST> detectedInstances, DetailAST ast) {
		DetailAST child = ast.getFirstChild();
		while (child != null) { // If child is null, that means we have no more siblings to check.
			if (child.getType() == targetToken.type()) {
				detectedInstances.add(child);
			}
			if (child.hasChildren()) { // If child has no children, there's no point in exploring the next depth level.
				detectedInstances = findTargetToken(detectedInstances, child);
			}
			child = child.getNextSibling();
		}
		return detectedInstances;
	}
	
	/**
	 * Creates a violation if the target token is supposed to be present.
	 * @return A SortedSet with a violation if the token was not found or an empty SortedSet otherwise.
	 */
	protected SortedSet<Violation> violationIfNotFindTarget() {
		Set<DetailAST> nodes = findTargetToken(new HashSet<DetailAST>(), baseNode());
		
		SortedSet<Violation> violations = new TreeSet<Violation>();
		if (nodes.isEmpty()) {
			violations.add(new Violation(this.baseNode().getLineNo(), violationMessage()));		
		}
		
		return violations;
	}
	
	/**
	 * Creates violations for every target token that is found where it shouldn't be.
	 * @return A SortedSet with violations for every token spotted or an empty SortedSet otherwise.
	 */
	protected SortedSet<Violation> violationIfFindTarget() {
		Set<DetailAST> nodes = findTargetToken(new HashSet<DetailAST>(), baseNode());

		SortedSet<Violation> violations = new TreeSet<Violation>();
		
		for (DetailAST n: nodes) {
			violations.add(new Violation(n.getLineNo(), violationMessage()));
		}

		return violations;
	}

}
