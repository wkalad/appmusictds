package modelo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class AdaptadorIText implements IAdaptadorPDF{

	
	
	@Override
	public void generarPDF(Usuario usuario, String ruta) {
		FileOutputStream archivo = null;		
		try {
			archivo =  new FileOutputStream(ruta);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Document documento = new Document();
		
		try {
			PdfWriter.getInstance(documento, archivo);
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		documento.open();
		
		try {
			documento.add(new Paragraph("Usuario: " + usuario.getNombre() + "\n"));
		} catch (DocumentException e1) {
			e1.printStackTrace();
		}
		
		for(Playlist p : usuario.getPlaylists()) {
			
			try {
				documento.add(new Paragraph("Playlist: " + p.getNombre()));
				documento.add(new Paragraph("Canciones:"));
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			
			for(Cancion c : p.getCanciones()) {
				try {
					documento.add(new Paragraph(c.getCancionInfo()));
				} catch (DocumentException e) {
					e.printStackTrace();
				}
			}
			
			try {
				documento.add(new Paragraph("\n"));
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		
		documento.close();
	}

}
