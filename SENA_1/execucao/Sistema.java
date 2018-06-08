package execucao;
/**
 * @author Sullyvan Puppi
 *
 * Classe responsável por executar o sistema.
 * 
 */
/*
import apresentacao.FrmLogin;
import negocios.CapturaTela;
import apresentacao.Splash;
*/
import negocios.CapturaTela;
import apresentacao.FrmLogin;
import apresentacao.FrmPrincipal;
import apresentacao.Splash;

public class Sistema {

	/**
	 * @Esta classe executará o sistema
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		CapturaTela tela = new CapturaTela();
		Splash splash = new Splash(3000);
		splash.showSplashAndExit();
		//FrmLogin frmLogin = new FrmLogin(tela.getLarguraTela(),tela.getAlturaTela());
		//frmLogin.show();
		FrmPrincipal frmPrincipal = new FrmPrincipal(2,"rito");
		frmPrincipal.show();
	}
}
