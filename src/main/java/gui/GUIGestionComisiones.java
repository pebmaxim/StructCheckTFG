package gui;

import fundamentos_test.*;
import modelo.Tienda;
import modelo.Vendedor;

/**
 * Interfaz Gráfica de Usuario (GUI) de la aplicación para la gestion de las
 * comisiones de los vendedores de una tienda.
 * 
 * @author  Metodos de Programacion (UC)
 * @version abr-22
 */
public class GUIGestionComisiones {
	// opciones del menu
	public static final int NUEVO_VENDEDOR = 0;
	public static final int NUEVA_VENTA = 1;
	public static final int DATOS_VENDEDOR = 2;
	public static final int RANKING_VENDEDOR = 3;
	public static final int ELIMINA_VENDEDOR = 4;
	public static final int FIN_APLICACION = 5;

	/**
	 * Programa principal basado en menu.
	 * @param args argumentos del programa principal (no usados)
	 * @throws AssertionError si se ha producido un error no esperado.
	 */
	public static void main(String[] args) {

		// variables auxiliares
		String nombre;
		Lectura lect;
		boolean correcto;

		// crea la tienda
		Tienda tienda = new Tienda();

		// crea la ventana de menu
		Menu menu = FundamentosFactory.getMenu("Comisiones tienda");
		menu.insertaOpcion("Nuevo Vendedor", NUEVO_VENDEDOR);
		menu.insertaOpcion("Nueva venta", NUEVA_VENTA);
		menu.insertaOpcion("Datos Vendedor", DATOS_VENDEDOR);
		menu.insertaOpcion("Ranking vendedor", RANKING_VENDEDOR);
		menu.insertaOpcion("Elimina vendedor", ELIMINA_VENDEDOR);
		int opcion;

		// lazo de espera de comandos del usuario
		while (true) {
			opcion = menu.leeOpcion();
			if (opcion == FIN_APLICACION) {
				break;
			}

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) {
			case NUEVO_VENDEDOR:
				lect = FundamentosFactory.getLectura("Nuevo Vendedor");
				lect.creaEntrada("Nombre", "Pepe");
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre");

				// anhade el vendedor a la tienda
				// TODO (Paso 1) adapa el codigo para utilizar excepciones como
				// mecanismo de notificacion de errores
				// TODO (Paso 2) Permite un maximo de 3 intentos para introducir
				// un nombre no usado.
				// Ademas del mensaje "Ya existe el vendedor", en el ultimo
				// intento se muestra tambien el mensaje:
				// mensaje("Operacion cancelada",
				//         "Alcanzado el numero maximo de intentos");
				correcto = tienda.anhadeVendedor(new Vendedor(nombre));
				if (!correcto) {
					mensaje("ERROR", "Ya existe el vendedor " + nombre);
				} else {
					mensaje("Vendedor anhadido",
							"Vendedor " + nombre + " anhadido con exito");
				}
				break;

			case NUEVA_VENTA:
				lect = FundamentosFactory.getLectura("Datos Venta");
				lect.creaEntrada("Nombre Vendedor", "Pepe");
				lect.creaEntrada("Importe", "100.0");
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre Vendedor");
				double importe = lect.leeDouble("Importe");

				// anhade la venta al vendedor
				// TODO (Paso 1) adapa el codigo para utilizar excepciones como
				// mecanismo de notificacion de errores
				// TODO (Paso 2) Repite la entrada mientras los datos no son validos:
				// - El nombre no corresponde a ningun vendedor:
				//   mensaje("ERROR", "No existe el vendedor " + nombre);
				// - El texto introducido en el campo importe no es un valor numerico:
				//   No poner mensaje (ya lo pone el paquete Fundamentos.
				// - El valor del campo importe es es menor o igual que 0:
				//   mensaje("ERROR", "El importe debe ser mayor que 0");
				correcto = tienda.anhadeVenta(nombre, importe);
				if (!correcto) {
					mensaje("ERROR", "No existe el vendedor " + nombre);
				} else {
					mensaje("Venta anhadida",
							"Venta anhadida a " + nombre);
				}
				break;

			case DATOS_VENDEDOR:
				lect = FundamentosFactory.getLectura("Datos Vendedor");
				lect.creaEntrada("Nombre Vendedor", "Pepe");
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre Vendedor");

				// busca el vendedor y muestra sus datos
				Vendedor vendedor = tienda.buscaVendedor(nombre);
				if (vendedor == null) {
					mensaje("ERROR", "No existe el vendedor " + nombre);
				} else {
					mensaje("Datos vendedor",
							"Nombre:" + vendedor.nombre() +
							" Comision: " + vendedor.comisionAcumulada());
				}
				break;

			case RANKING_VENDEDOR:
				lect = FundamentosFactory.getLectura("Ranking Vendedor");
				lect.creaEntrada("Nombre Vendedor", "Pepe");
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre Vendedor");

				// muestra la posicion en el ranking del vendedor
				// TODO (Paso 1) adapa el codigo para utilizar excepciones como
				// mecanismo de notificacion de errores
				int posRanking = tienda.posRankingVendedor(nombre);
				if (posRanking == -1) {
					mensaje("ERROR", "No existe el vendedor " + nombre);
				} else {
					mensaje("Ranking vendedor",
							"Nombre:" + nombre +
							" Posicion Ranking: " + posRanking);
				}
				break;

			case ELIMINA_VENDEDOR:
				lect = FundamentosFactory.getLectura("Elimina Vendedor");
				lect.creaEntrada("Nombre Vendedor", "Pepe");
				lect.esperaYCierra();
				nombre = lect.leeString("Nombre Vendedor");

				// elimina el vendedor
				// TODO (Paso 1) adapa el codigo para utilizar excepciones como
				// mecanismo de notificacion de errores
				vendedor = tienda.eliminaVendedor(nombre);
				if (vendedor == null) {
					mensaje("ERROR", "No existe el vendedor " + nombre);
				} else {
					mensaje("Vendedor eliminado",
							"Eliminado " + vendedor.nombre());
				}
				break;

			default:
				throw new AssertionError("Opcion no esperada");
			}
		}
	}

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje.
	 * @param titulo titulo de la ventana
	 * @param txt texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) {
		Mensaje msj = FundamentosFactory.getMensaje(titulo);
		msj.escribe(txt);
	}

}
