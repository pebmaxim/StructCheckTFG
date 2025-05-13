package structcheck_module;

public class Token {
	private final String name;
	private final int type;	
	
	/**
	 * @param name
	 * @param type
	 */
	public Token(String name, int type) {
		super();
		this.name = name;
		this.type = type;
	}
	
	public Token(int type) {
		// TODO check tokenType does not require name: LITERAL_FOR, LITERAL_IF, ...
		this(null, type);
	}

	/**
	 * @return the tokenName
	 */
	public String name() {
		return name;
	}

	/**
	 * @return the tokenType
	 */
	public int type() {
		return type;
	}
	
    
	
}
