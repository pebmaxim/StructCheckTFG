package structcheck_module;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;

//XXX creo que no hace falta esta clase
public class TokenMethod extends Token {

	public TokenMethod(String methodName) {
		super(methodName, TokenTypes.METHOD_DEF);
	}

}
