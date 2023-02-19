package HANOIMVC;

public class AplHanoi {
	public static void main(String [] a) {

		/*
			MAXIMUM 5 DISCS
			IF YOU WANT TO ADD MORE ADD MORE COLORS TO
			ARRAY NAMED COLORS IN "VISTAHANOI", IN THAT ARRAY
			THERE ARE ONLY 5 DIFFERENT COLORS
		 */
		System.out.println("________________");
		VistaHanoi vista = new VistaHanoi( 5);
		ModeloHanoi modelo = new ModeloHanoi(5);
		ControladorHanoi controlador = new ControladorHanoi(vista,modelo);
		vista.setControlador(controlador);
		
	}

}

