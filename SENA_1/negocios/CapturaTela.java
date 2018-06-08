/**
 * 
 */
package negocios;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * @author Sullyva Puppi
 *
 */
public class CapturaTela {

	private int altura;
	private int largura;

	public CapturaTela(){
		super();
		Toolkit tela = Toolkit.getDefaultToolkit();
		Dimension tamanhoTela = tela.getScreenSize();
		this.altura=tamanhoTela.height;
		this.largura=tamanhoTela.width;
	}
	public int getAlturaTela(){
		return altura; 
	}
	public int getLarguraTela(){
		return largura;
	}
}