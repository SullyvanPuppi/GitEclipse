package execucao;

import negocios.CapturaTela;
import apresentacao.FrmPrincipal;
import apresentacao.Splash;
//import apresentacao.FrmPrincipal;

public class Sistema {

	/**
	 * Autor: Sullyvan Puppi
	 * Classe responsável por inicializar o sistema
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		CapturaTela tela = new CapturaTela();
		Splash splash = new Splash(3000);
		splash.showSplashAndExit();
		//FrmLogin formLogin = new FrmLogin(tela.getLarguraTela(),tela.getAlturaTela());
		//formLogin.show();		
		FrmPrincipal formPrincipal = new FrmPrincipal(1,"puppi");
		formPrincipal.show();
	}
}
