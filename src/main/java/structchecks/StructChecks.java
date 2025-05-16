package structchecks;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import structcheck_module.Check;
import structcheck_module.CheckMethodInMethod;
import structcheck_module.CheckStatementInMethod;
import structcheck_module.CheckStatementNotInMethod;

public class StructChecks {
	public static final Check[] checks = {
			// metodo Tienda.anhadeVendedor
			new CheckStatementInMethod("Tienda.anhadeVendedor",
					TokenTypes.LITERAL_THROWS,
					"El metodo debe indicar que podria lanzar la excepcion " +
					"anhadiendo el correspondiente 'throws' en su cabecera."),
			new CheckStatementInMethod("Tienda.anhadeVendedor",
					TokenTypes.LITERAL_IF),
			new CheckStatementNotInMethod("Tienda.anhadeVendedor",
					TokenTypes.LITERAL_ELSE),
			new CheckMethodInMethod("Tienda.anhadeVendedor", "Tienda.buscaVendedor"),
			
			// metodo Tienda.buscaVendedor
			new CheckStatementInMethod("Tienda.buscaVendedor",
					TokenTypes.LITERAL_FOR),
			// TODO new CheckMethodReturnType("Tienda.buscaVendedor", "Vendedor"),
			
			// Declaracion excepciones
			// TODO new CheckClassExtends("Tienda.NombreYaExistente", "RuntimeException")
	};
}
