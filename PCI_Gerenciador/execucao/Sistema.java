package execucao;

//import negocios.CapturaTela;
import apresentacao.Splash;
import apresentacao.FrmPrincipal;

public class Sistema {

	/**
	 * Autor: Sullyvan Puppi
	 * Classe respons�vel por inicializar o sistema
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		//CapturaTela tela = new CapturaTela();
		Splash splash = new Splash(3000);
		splash.showSplashAndExit();
		//FrmLogin formLogin = new FrmLogin(tela.getLarguraTela(),tela.getAlturaTela());
		//formLogin.show();		
		FrmPrincipal formPrincipal = new FrmPrincipal("puppi");
		formPrincipal.show();
	}
}
