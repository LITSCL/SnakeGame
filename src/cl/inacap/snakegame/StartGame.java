package cl.inacap.snakegame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StartGame extends JFrame {
	private JPanel contentPane;
	private PanelFondo fondo = new PanelFondo(800, 30); //Se crea una relación Has-a, porque el JFrame tiene un PanelFondo.
	private PanelSnake snake = new PanelSnake(800, 30);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartGame frame = new StartGame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StartGame() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) { //Este listener se ejecuta cuando se presiona una tecla.
				if (evt.getKeyCode() == KeyEvent.VK_LEFT) { //Aquí se consulta si se seleccionó la flecha izquierda del teclado.
					snake.cambiarDireccion("izquierda");
				}
				else if (evt.getKeyCode() == KeyEvent.VK_RIGHT) {
					snake.cambiarDireccion("derecha");
				}
				else if (evt.getKeyCode() == KeyEvent.VK_UP) {
					snake.cambiarDireccion("arriba");
				}
				else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
					snake.cambiarDireccion("abajo");
				}
			}
		});
		setTitle("SnakeGame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 805, 829);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		this.setLocationRelativeTo(null); //Innecesario.
		
		//Primero se debe renderizar el snake para que se vea delante.
		snake.setBounds(5,5,800,800); //Esta instruccion posisiona el panel (Necesario para que el snake se posisione exactamente encima de una cuadricula).
		getContentPane().add(snake);
		snake.setOpaque(false);
		
		fondo.setBounds(10,10,800,800); //Esta instrucción posisiona el panel.
		getContentPane().add(fondo); //Al Jframe se le añade el fondo (Aquí es donde se llama al método pintor de la clase PanelFondo), dicho método dibuja todos los cuadros del fondo.
		
		this.requestFocus(true);
	}

}
