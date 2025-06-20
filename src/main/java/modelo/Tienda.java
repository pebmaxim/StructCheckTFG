package modelo;

import java.util.ArrayList;

/**
 * Tienda con sus vendedores.
 * 
 * @author  Metodos de Programacion (UC)
 * @version abr-22
 */
public class Tienda {
	
	// vendedores de la tienda
	private ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();
	
	// TODO (Paso 1) declara las excepciones NombreYaExistente y NombreVendedorIncorrecto
	
	// TODO (Paso 1) modifica los metodos anhadeVendedor(), anhadeVenta(),
	// posRankingVendedor() y eliminaVendedor() para que utilicen las excepciones
	// como mecanismo de notificacion de errores.
	
	/*
	@SuppressWarnings("serialized")
	private class NombreYaExistente extends RuntimeException{}
	*/
	
	/**
	 * Anhade un nuevo vendedor a la tienda.
	 * @param nuevoVendedor vendedor a anhadir
	 * @return true si el vendedor se ha anhadido
	 *         false si ya habia un vendedor con el mismo nombre
	 */
	public boolean anhadeVendedor(Vendedor nuevoVendedor) 	{
		if (buscaVendedor(nuevoVendedor.nombre()) != null) {
			return false;
		}
		vendedores.add(nuevoVendedor);
		return true;
		
	}
	
	/**
	 * Anhade una venta a un vendedor.
	 * @param nombre nombre del vendedor
	 * @param importe importe de la venta
	 * @return true si se anhade la venta
	 *         false si no se encuentra el vendedor
	 */
	public boolean anhadeVenta(String nombre, double importe) {
		Vendedor vendedor = buscaVendedor(nombre);
		if (vendedor == null) {
			return false;
		}
		vendedor.anhadeVenta(importe);
		return true;
	}
	
	/**
	 * Retorna la posicion en el ranking del vendedor con el ID indicado.
	 * @param nombre nombre del vendedor cuya posicion en el ranking se quiere conocer
	 * @return la posicion en el ranking del vendedor indicado o -1 si no existe
	 * ningun vendedor con ese nombre
	 */
	public int posRankingVendedor(String nombre) {
		Vendedor vendedor = buscaVendedor(nombre);
		if (vendedor == null) {
			return -1;
		}
		
		// cuenta cuantos vendedores tienen una comision mayor que el vendedor
		// en cuestion.
		int numVendedoresConMasComision = 0;
		for (Vendedor v: vendedores) {
			if (v.comisionAcumulada() > vendedor.comisionAcumulada()) {
				numVendedoresConMasComision++;
			}
		}
		
		// la posicion del vendedor en el ranking es el numero de vendedores con
		// mas comision que el mas uno (p.e. si hay 0 vendedores con mas comision
		// que el, eso significa que el vendedor ocupa la posicion 1 del ranking)
		return numVendedoresConMasComision + 1;
	}
	
	/**
	 * Retorna el vendedor con el nombre indicado.
	 * @param nombre nombre del vendedor
	 * @return vendedor con ese nombre o null en caso de no existir ninguno
	 */
	public Vendedor buscaVendedor(String nombre) {
		for (Vendedor v: vendedores) {
			if (v.nombre().equals(nombre)) {
				return v;
			}
		}
		return null;
	}
	
	/**
	 * Elimina el vendedor con el nombre indicado.
	 * @param nombre nombre del vendedor a eliminar.
	 * @return el vendedor eliminado o null si no existe ningun vendedor
	 * con el nombre indicado.
	 */
	public Vendedor eliminaVendedor(String nombre) {
		Vendedor vendedor = buscaVendedor(nombre);
		if (vendedor == null) {
			return null;
		}
		vendedores.remove(vendedor);
		return vendedor;
	}
	
}
