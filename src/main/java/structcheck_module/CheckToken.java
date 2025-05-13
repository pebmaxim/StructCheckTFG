package structcheck_module;

import java.util.SortedSet;
import java.util.TreeSet;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.Violation;

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
	private SortedSet<DetailAST> findTargetToken() {
		// TODO
		return null;
	}

	protected SortedSet<Violation> violationIfNotFindTarget() {
		SortedSet<DetailAST> nodes = findTargetToken();
		
		SortedSet<Violation> violations = new TreeSet<Violation>();
		if (nodes.isEmpty()) {
			// Violation found: statement not found in method
			
			// TODO: crea una "Violation" con la linea en la que está el método
			// y el violationMessage y la añade a violations			
		}
		
		return violations;
	}

	protected SortedSet<Violation> violationIfFindTarget() {
		SortedSet<DetailAST> nodes = findTargetToken();

		SortedSet<Violation> violations = new TreeSet<Violation>();
		
		for (DetailAST n: nodes) {
			// TODO: crea una "Violation" con la linea a la que corresponde
			// el nodo n y el violationMessage y la añade a violations		
		}

		return violations;
	}

}
