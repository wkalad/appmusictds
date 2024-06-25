package auxiliares;

import java.net.MalformedURLException;
import java.net.URL;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import modelo.Cancion;

public class Player {

	private Cancion cancionActual = null;
	private MediaPlayer mediaPlayer;
	public Player() {
		com.sun.javafx.application.PlatformImpl.startup(()->{});
	}
	
	
	public void play(String accion, Cancion cancion) {
		switch (accion) {
		case "play":
			setCancionActual(cancion);
			mediaPlayer.play();
			break;
		case "stop":
			mediaPlayer.stop();
			break;
		case "pause":
			mediaPlayer.pause();
			break;
		}
	}
	
	public void setCancionActual(Cancion cancion) {
		URL uri = null;
		if(cancion != cancionActual) {
			cancionActual = cancion;
			
			try {
				uri = new URL(cancion.getRutaFichero());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
			Media media = new Media(uri.toString());
			mediaPlayer = new MediaPlayer(media);
		}
	}
}
