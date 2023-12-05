package cl.inacap.snakegame;

import java.awt.Color;
import java.awt.Graphics; //Esta clase permite trabajar con Gr�ficos 2D.

import javax.swing.JPanel;

public class PanelFondo extends JPanel{
	private Color colorFondo = Color.gray; //Este atributo indica el color que se utilizar� para el fondo.
	private int sizePanel, sizeCuadrado, cantidadCuadrados; //El primer atributo indica el tama�o del panel.
	
	public PanelFondo(int sizePanel, int cantidadCuadrados) {
		this.sizePanel = sizePanel;
		this.cantidadCuadrados = cantidadCuadrados;
		this.sizeCuadrado = (sizePanel / cantidadCuadrados); //Este valor no debe ser entregado por par�metro, debe ser calculado en base al tama�o del panel y la cantidad de cuadros deseada.
	}
	
	@Override
	public void paint(Graphics pintor) { //El objeto pintor permite dibujar en pantalla.
		super.paint(pintor);
		pintor.setColor(colorFondo);
		for (int i = 0; i < this.cantidadCuadrados; i++) { //Aca se recorre el eje X.
			for (int j = 0; j < this.cantidadCuadrados; j++) { //Aca se recorre el eje Y.
				pintor.fillRect(i * sizeCuadrado, j * sizeCuadrado, sizeCuadrado - 1, sizeCuadrado - 1); //El m�todo fillRect() permite dibujar un cuadrado (El primer par�metro indica la posisi�n en coordenada X, el segundo la posisi�n en coordenada Y, el tercero indica el ancho y el cuarto indica el alto).
			}
		}
	}
}
