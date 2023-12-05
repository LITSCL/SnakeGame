package cl.inacap.snakegame.util;

import java.applet.AudioClip;

public class StartAudioUtil {
	
	//Es recomendable utilizar sonidos con formato .wav.
	public void reproducir(String nombre,String formato){
		try {
			AudioClip sonido;
			sonido = java.applet.Applet.newAudioClip(getClass().getResource("/cl/inacap/snakegame/sound/" + nombre + "." + formato)); //El sonido tiene que estar obligatoriamente en un paquete (No se puede usar un Folder).
			sonido.play();
		} catch (Exception e) {
			System.out.println("Ruta Erronea");
		}
	}
}
