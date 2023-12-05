package cl.inacap.snakegame;

import java.awt.Color;
import java.awt.Graphics; //Esta clase permite trabajar con Gráficos 2D.

import javax.swing.JPanel;

public class PanelFondo extends JPanel{
	private Color colorFondo = Color.gray; //Este atributo indica el color que se utilizará para el fondo.
	private int sizePanel, sizeCuadrado, cantidadCuadrados; //El primer atributo indica el tamaño del panel.
	
	public PanelFondo(int sizePanel, int cantidadCuadrados) {
		this.sizePanel = sizePanel;
		this.cantidadCuadrados = cantidadCuadrados;
		this.sizeCuadrado = (sizePanel / cantidadCuadrados); //Este valor no debe ser entregado por parámetro, debe ser calculado en base al tamaño del panel y la cantidad de cuadros deseada.
	}
	
	@Override
	public void paint(Graphics pintor) { //El objeto pintor permite dibujar en pantalla.
		super.paint(pintor);
		pintor.setColor(colorFondo);
		for (int i = 0; i < this.cantidadCuadrados; i++) { //Aca se recorre el eje X.
			for (int j = 0; j < this.cantidadCuadrados; j++) { //Aca se recorre el eje Y.
				pintor.fillRect(i * sizeCuadrado, j * sizeCuadrado, sizeCuadrado - 1, sizeCuadrado - 1); //El método fillRect() permite dibujar un cuadrado (El primer parámetro indica la posisión en coordenada X, el segundo la posisión en coordenada Y, el tercero indica el ancho y el cuarto indica el alto).
			}
		}
	}
}
