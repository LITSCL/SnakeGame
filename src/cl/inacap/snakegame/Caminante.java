package cl.inacap.snakegame;

public class Caminante implements Runnable {
	private PanelSnake panel;
	boolean estado = true;
	
	public Caminante(PanelSnake panel) {
		this.panel=panel;
	}
	
	@Override
	public void run() {
		estado = true;
		while (estado) {
			panel.avanzar();
			panel.repaint(); //Esta instrucci�n repinta el snake.
			try {
				Thread.sleep(50); //Esta instrucci�n realiza una espera en ms (No se ejecuta ninguna instrucci�n de abajo en una cierta cantidad de tiempo).
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stop(){
		this.estado = false;
	}
	
}
