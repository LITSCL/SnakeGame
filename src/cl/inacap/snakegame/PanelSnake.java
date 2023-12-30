package cl.inacap.snakegame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import cl.inacap.snakegame.util.StartAudioUtil;

public class PanelSnake extends JPanel{
	private Color colorSnake = Color.green; 
	private Color colorComida = Color.red;
	private int sizePanel, sizeCuadro, cantidadCuadrados; 
	private List<int[]> snake = new ArrayList<>(); //Aqui se almacenarán las coordenadas (Pares ordenados) de cada cuadro que componga el snake.
	private int[] comida = new int[2]; //Aqui se almacenaran las coordenadas de donde aparecerá la comida.
	private String direccion = "derecha";
	private String nuevaDireccion = "derecha";
	private Caminante caminante;
	private Thread hilo;
	private StartAudioUtil sta = new StartAudioUtil();
	
	public PanelSnake(int sizePanel, int cantidadCuadrados) {
		this.sizePanel = sizePanel;
		this.cantidadCuadrados = cantidadCuadrados;
		this.sizeCuadro = (sizePanel / cantidadCuadrados);
		int[] a = {cantidadCuadrados / 2 - 1, cantidadCuadrados / 2 - 1}; //Primer par ordenado del snake (En este array contiene en su primer indice el eje X y en el segundo indice contiene el eje Y del primer cuadro que compone el snake).
		int[] b = {cantidadCuadrados / 2, cantidadCuadrados / 2 - 1}; //Segundo par ordenado del snake.
		snake.add(a);
		snake.add(b);
		generarComida();
		
		caminante = new Caminante(this);
		hilo = new Thread(caminante);
		hilo.start();
	}
	
	@Override
	public void paint(Graphics pintor) { 
		super.paint(pintor);
		
		//Pintando snake.
		pintor.setColor(colorSnake);
		for (int i = 0; i < snake.size(); i++) {
			if(i == snake.size() - 1){
				pintor.setColor(Color.white);
			}
			pintor.fillRect(snake.get(i)[0] * sizeCuadro, snake.get(i)[1] * sizeCuadro, sizeCuadro - 1, sizeCuadro - 1);
		}
		
		//Pintando comida.
		pintor.setColor(colorComida);
		pintor.fillRect(comida[0] * sizeCuadro, comida[1] * sizeCuadro, sizeCuadro - 1, sizeCuadro - 1);
	}
	
	public void avanzar() {
		igualarDirecciones();
		int[] primerCuadradoSnake = snake.get(snake.size() - 1); //El primer cuadrado del snake es el ultimo que se añadió a la lista.
		int agregarX = 0;
		int agregarY = 0;
		
		switch (direccion) {
			case "derecha":
				agregarX = 1;
				break;
			case "izquierda":
				agregarX = -1;
				break;
			case "arriba":
				agregarY = -1;
				break;
			case "abajo":
				agregarY = 1;
				break;
		}
		
		int[] nuevoPrimerCuadradoSnake = {(primerCuadradoSnake[0] + agregarX), (primerCuadradoSnake[1] + agregarY)}; //Aqui se calculan las coordenadas del nuevo primer cuadrado del snake.
		
		if (nuevoPrimerCuadradoSnake[0] == this.cantidadCuadrados) {
			nuevoPrimerCuadradoSnake[0] = 0;
		}
		if (nuevoPrimerCuadradoSnake[0] == -1) {
			nuevoPrimerCuadradoSnake[0] = this.cantidadCuadrados-1;
		}
		if (nuevoPrimerCuadradoSnake[1] == this.cantidadCuadrados) {
			nuevoPrimerCuadradoSnake[1] = 0;
		}
		if (nuevoPrimerCuadradoSnake[1] == -1) {
			nuevoPrimerCuadradoSnake[1] = this.cantidadCuadrados-1;
		}
		
		boolean cuadradoPintado = false;
		for (int i = 0; i < snake.size(); i++) {
			if (nuevoPrimerCuadradoSnake[0] == snake.get(i)[0] && nuevoPrimerCuadradoSnake[1] == snake.get(i)[1]) { //Aqui se esta consultando si la serpiente se chocó.
				cuadradoPintado = true;	
				break;
			}
		}
		
		if (cuadradoPintado == true) {		
			sta.reproducir("SonidoMuerte", "wav");
			JOptionPane.showMessageDialog(null, "Has perdido");
			snake.clear();
			this.direccion = "derecha";
			nuevaDireccion = "derecha";
			int[] a = {cantidadCuadrados / 2 - 1, cantidadCuadrados / 2 - 1}; //Primer par ordenado del snake (En este array contiene en su primer indice el eje X y en el segundo indice contiene el eje Y del primer cuadro que compone el snake).
			int[] b = {cantidadCuadrados / 2, cantidadCuadrados / 2 - 1}; //Segundo par ordenado del snake.
			snake.add(a);
			snake.add(b);
			generarComida();
			caminante.run();
		}
		else {
			if (nuevoPrimerCuadradoSnake[0] == comida[0] && nuevoPrimerCuadradoSnake[1] == comida[1]) { //Aquí se verifica si el snake se comió la comida.
				sta.reproducir("SonidoComida", "wav");
				snake.add(nuevoPrimerCuadradoSnake); //Aquí se agranda el snake.
				generarComida();
			}
			else {
				snake.add(nuevoPrimerCuadradoSnake);
				snake.remove(0); //Aca se borra el ultimo cuadrado del snake.
			}
		}
	}
	
	public void generarComida() {
		boolean existe = false;
		
		int x = (int)(Math.random() * cantidadCuadrados); //El método random genera un valor random entre 0.0 y 1.0.
		int y = (int)(Math.random() * cantidadCuadrados); //El método random genera un valor random entre 0.0 y 1.0.
	

		for (int i = 0; i < snake.size(); i++) {
			if (snake.get(i)[0] == x && snake.get(i)[1] == y) { //Aquí se esta consultando si el par ordenado existe dentro del snake.
				existe = true;
				generarComida();
				break;
			}
		}
		
		if (existe == false) {
			this.comida[0] = x;
			this.comida[1] = y;
		}		
	}
	
	public void cambiarDireccion(String direccion) {
		if ((this.direccion.equals("derecha") || this.direccion.equals("izquierda")) && (direccion.equals("arriba") || direccion.equals("abajo"))) {
			this.nuevaDireccion = direccion;
		}
		
		if ((this.direccion.equals("arriba") || this.direccion.equals("abajo")) && (direccion.equals("derecha") || direccion.equals("izquierda"))) {
			this.nuevaDireccion = direccion;
		}
		
	}
	
	public void igualarDirecciones() {
		this.direccion = this.nuevaDireccion;
	}
}

