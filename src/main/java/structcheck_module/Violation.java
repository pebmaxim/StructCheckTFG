package structcheck_module;

/**
 * Class for logging check violations
 */
public class Violation implements Comparable<Violation> {
	
	private int lineNo;
	private String message;
	
	/**
	 * Constructor
	 * @param lineNo Line number where the violation should be logged
	 * @param message Message that will accompany the violation
	 */
	public Violation(int lineNo, String message) {
		this.lineNo = lineNo;
		this.message = message;
	}
	
	/**
	 * Return the violation's line number
	 * @return the violation's line number
	 */
	public int lineNo() {
		return this.lineNo;
	}
	
	/**
	 * Return the violation's log message
	 * @return the violation's log message
	 */
	public String message() {
		return this.message;
	}
	
	public int compareTo(Violation o) {
		return lineNo - o.lineNo();
	}

}
