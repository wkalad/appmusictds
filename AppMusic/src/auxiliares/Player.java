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
	
	
	public void play(Cancion cancion) {
		setCancionActual(cancion);
		mediaPlayer.play();
	}
	
	public void pause() {
		mediaPlayer.pause();
	}
	
	public void stop() {
		mediaPlayer.stop();
	}
	
	public void setCancionActual(Cancion cancion) {
		URL uri = null;
		if(cancion != cancionActual || cancionActual == null) {
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
	
	public boolean isRepro() {
		return cancionActual != null;
	}
}
