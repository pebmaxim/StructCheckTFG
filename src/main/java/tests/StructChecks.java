package tests;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import structcheck_module.*;

public class StructChecks {
	public static final Check[] checks = {
			// metodo Tienda.anhadeVendedor
			new CheckStatementInMethod("Tienda.anhadeVendedor",
					TokenTypes.LITERAL_IF),
			new CheckStatementInMethod("Tienda.anhadeVendedor",
					TokenTypes.LITERAL_THROWS,
					"El metodo debe indicar que podria lanzar la excepcion " +
					"anhadiendo el correspondiente 'throws' en su cabecera."),
			new CheckStatementNotInMethod("Tienda.anhadeVendedor",
					TokenTypes.LITERAL_ELSE),
			new CheckMethodInMethod("Tienda.anhadeVendedor", "Tienda.buscaVendedor"),
			
			// metodo Tienda.buscaVendedor
			new CheckStatementInMethod("Tienda.buscaVendedor",
					TokenTypes.LITERAL_FOR),
			new CheckMethodReturnType("Tienda.buscaVendedor", "Vendedor"),
			
			// Declaracion excepciones
			new CheckClassExtends("Tienda.NombreYaExistente", "RuntimeException")
	};
}
