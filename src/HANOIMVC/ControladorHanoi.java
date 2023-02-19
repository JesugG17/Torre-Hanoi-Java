package HANOIMVC;
import java.awt.event.*;
public class ControladorHanoi implements ActionListener{
	VistaHanoi vista;
	ModeloHanoi modelo;
	public ControladorHanoi(VistaHanoi vista,ModeloHanoi modelo) {
		this.vista = vista;
		this.modelo = modelo;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == vista.btnIniciar) {
			vista.timer.start();
			vista.obtenerMovimientos(modelo.getMovtos());
			vista.btnIniciar.setVisible( false );
			return;
		}


		Movimiento movimientoActual = modelo.getMovtos().get(vista.accion);

		if (vista.subir(movimientoActual)) {
			return;
		}

		if (vista.bajar(movimientoActual)) {
			return;
		}

		vista.mover(movimientoActual);


	}

}
