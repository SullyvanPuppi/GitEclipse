package negocios;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @author Sullyvan Puppi
 *
 * Classe para administrador selecionar logo da instituição de ensino
 * 
 */
public class IeSelecionaLogo {

	private String caminhoLogo;
	private static String nomeLogo;
	private String nomeDestino;


	public String getNomeDestino() {
		return nomeDestino;
	}
	public void setNomeDestino(String nomeDestino) {
		this.nomeDestino = nomeDestino;
	}
	public static String getNomeLogo() {
		return nomeLogo;
	}
	public static void setNomeLogo(String nomeLogo) {
		IeSelecionaLogo.nomeLogo = nomeLogo;
	}
	public String getCaminhoLogo() {
		return caminhoLogo;
	}
	public void setCaminhoLogo(String caminhoLogo) {
		this.caminhoLogo = caminhoLogo;
	}

	public IeSelecionaLogo(String nome){

		try {JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(new FileFilter() {
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(".gif")
				|| f.isDirectory();
			}
			public String getDescription() {
				return "GIF Images";
			}
		});
		chooser.setFileFilter(new FileFilter() {
			public boolean accept(File f) {
				return f.getName().toLowerCase().endsWith(".jpg")
				|| f.isDirectory();
			}
			public String getDescription() {
				return "JPG Images";
			}
		});
		int r = chooser.showOpenDialog(new JFrame());
		if (r == JFileChooser.APPROVE_OPTION){
			String name = chooser.getSelectedFile().getAbsolutePath();
			setNomeDestino("logotipo/"+nome+getExtension(chooser.getSelectedFile().getName()));
			setNomeLogo(chooser.getSelectedFile().getName());
			setCaminhoLogo ("/logotipo/"+nome+getExtension(chooser.getSelectedFile().getName()));
			copy(name, getNomeDestino());
			//copyServer(name, getNomeDestino());
			MiniaturaIniciar();}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	private static String getExtension(String nome){
		return nome.substring(nome.lastIndexOf("."));
	}

	public static void copy(String fromFileName, String toFileName)
	throws IOException {
		File fromFile = new File(fromFileName);
		File toFile = new File(toFileName);

		FileInputStream from = null;
		FileOutputStream to = null;
		try {
			from = new FileInputStream(fromFile);
			to = new FileOutputStream(toFile);
			byte[] buffer = new byte[4096];
			int bytesRead;

			while ((bytesRead = from.read(buffer)) != -1)
				to.write(buffer, 0, bytesRead); // write
		} finally {
			if (from != null)
				try {
					from.close();
				} catch (IOException e) {
					;
				}
				if (to != null)
					try {
						to.close();
					} catch (IOException e) {
						;
					}
		}
	}
	public void MiniaturaIniciar() {
		int width = 198; // Lagura da miniatura
		int height = 163; // Altuta da miniatura
		int quality = 80; // Qualidade da imagem [0~100]

		@SuppressWarnings("unused")
		File file = new File(getNomeDestino());

		Image image = new ImageIcon(getNomeDestino()).getImage();
		redimensionar(image, width, height, quality, getNomeLogo());
	}

	//Método para redimensionar imagens (criar thubmnails)
	private void redimensionar(Image image, int width, int height, int quality,
			String nomeImagem) {

		// Calculos necessários para manter as propoçoes da imagem, conhecido
		// como "aspect ratio"
		double thumbRatio = (double) width / (double) height;
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);

		double imageRatio = (double) imageWidth / (double) imageHeight;

		if (thumbRatio < imageRatio) {
			height = (int) (width / imageRatio);
		} else {
			width = (int) (height * imageRatio);
		}
		// Fim do cálculo

		BufferedImage thumbImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		Graphics2D graphics2D = thumbImage.createGraphics();

		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		graphics2D.drawImage(image, 0, 0, width, height, null);

		BufferedOutputStream out;

		try {
			out = new BufferedOutputStream(new FileOutputStream(getNomeDestino()));
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
			quality = Math.max(0, Math.min(quality, 100));
			param.setQuality((float) quality / 100.0f, false);
			encoder.setJPEGEncodeParam(param);
			encoder.encode(thumbImage);
			out.close();

		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException " + e.getMessage());
		} catch (ImageFormatException e) {
			System.out.println("ImageFormatException " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException " + e.getMessage());
		}
	}

}