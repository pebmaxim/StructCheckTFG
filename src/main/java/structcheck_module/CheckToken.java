package structcheck_module;

import java.util.Set;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

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
		Set<DetailAST> nodes = findTargetToken(new HashSet<>(), baseNode());
		
		if (targetToken.type() == TokenTypes.METHOD_CALL) { // Identify whether the method calls found are the ones we're checking for
			nodes = methodCallFilter(nodes);
		}
		
		if (targetToken.name() != null && targetToken.type() != TokenTypes.METHOD_CALL) {
			boolean identCorrecto = false;
			for (DetailAST n: nodes) {
				if (identFinder(n).equalsIgnoreCase(targetToken.name())) {
					identCorrecto = true;
					break;
				}
			}
			if (!identCorrecto) {
				nodes.clear();
			}
		}
		
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
		Set<DetailAST> nodes = findTargetToken(new HashSet<>(), baseNode());
		
		if (targetToken.type() == TokenTypes.METHOD_CALL) { // Identify whether the method calls found are the ones we're checking for
			nodes = methodCallFilter(nodes);
		}

		SortedSet<Violation> violations = new TreeSet<>();
		
		for (DetailAST n: nodes) {
			if (targetToken.name() == null || (targetToken.name() != null &&
					identFinder(n).equalsIgnoreCase(targetToken.name())) ||
					targetToken.type() == TokenTypes.METHOD_CALL)
				violations.add(new Violation(n.getLineNo(), violationMessage()));
		}

		return violations;
	}
	
	/**
	 * Creates a violation if the return type of a method does not match the mandated one.
	 * @return a violation informing the user of the problem and the place to look in.
	 */
	protected SortedSet<Violation> checkReturnType() {
		SortedSet<Violation> violations = new TreeSet<>();
		if (baseNode().getType() != TokenTypes.METHOD_DEF) {
			System.out.println("ERROR: You used checkReturnType in a CLASS_DEF node. Check your code.");
			return null;
		}
		DetailAST type = baseNode().findFirstToken(TokenTypes.TYPE);
		if (!type.getFirstChild().getText().equalsIgnoreCase(targetToken.name())) {
			violations.add(new Violation(baseNode().getLineNo(), violationMessage()));
		}
		return violations;
	}
	
	/**
	 * Creates a violation if a class does not extend the mandated interface/abstract
	 * @return a violation informing the user of the problem and the place to look in
	 */
	protected SortedSet<Violation> checkExtends() {
		Set<DetailAST> nodes = findTargetToken(new HashSet<>(), baseNode());
		String[] split = baseToken().name().split("\\.");
		String name = split[split.length - 1];
		
		SortedSet<Violation> violations = new TreeSet<>();
		violations.add(new Violation(baseNode().getLineNo(), violationMessage()));
		
		for (DetailAST n: nodes) {
			if (n.getPreviousSibling().getText().equals(name)) {
				violations.clear();
			}
		}
		
		return violations;
	}
	
	/**
	 * Filters method calls based on the method name
	 * @param nodes Set of method calls spotted
	 * @return Set of method calls with a matching name
	 */
	private Set<DetailAST> methodCallFilter(Set<DetailAST> nodes) {
		// Separating method name from its class
		String[] split = targetToken().name().split("\\.");
		String name = split[split.length - 1]; 
		
		HashSet<DetailAST> filteredNodes = new HashSet<>();
		
		for (DetailAST n: nodes) {
			// The first child is either the method name or the dot in X.method
			DetailAST methodName = n.getFirstChild(); 
			
			// If the first child is the dot, then its children are an expression and then the method name.
			if (methodName.getType() == TokenTypes.DOT) {
				methodName = methodName.getFirstChild().getNextSibling();
			}
			
			// Checking if the method name for this call is the one we are interested in
			if (methodName.getText().equals(name)) {
				filteredNodes.add(n);
			}
		}
		return filteredNodes;
	}
	
	/**
	 * Method for finding the identification of an item
	 * @param node The item whose identification is currently being searched for
	 * @return The text identifying the item or null if there isn't any
	 */
	private String identFinder(DetailAST node) {
		DetailAST child = node.getFirstChild();
		String ident = "Unidentified";
		while (child != null && ident.equals("Unidentified")) { // If child is null, that means we have no more siblings to check.
			if (child.getType() == TokenTypes.IDENT) {
				ident = child.getText();
				break;
			}
			if (child.hasChildren()) { // If child has no children, there's no point in exploring the next depth level.
				ident = identFinder(child);
			}
			child = child.getNextSibling();
		}
		return ident;
	}

}
