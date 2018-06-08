package apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import negocios.Visita;

public class FrmRelatorioClientesVisitas extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -931568505768859793L;

	String login = "";
	
	private JPanel jContentPane = null;
	//Cria��o da �rea de trabalho do formul�rio
	JDesktopPane desk = null;

	//Direitos de usu�rio determinado pelo grupo
	private int permissao;

	public int getPermissao(){
		return this.permissao;
	}
	//---Ajusta permiss�o no sistema
	public void ajustaPermissao(){
		if(getPermissao()==1){//Administrdor		

		}else if(getPermissao()==2){//Secretaria

		}else if(getPermissao()==3){//Convidado

		}
	}
	/**
	 * This is the xxx default constructor
	 */
	public FrmRelatorioClientesVisitas(JDesktopPane desktop) {
		super();
		this.desk = desktop;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Relat�rio de empresas clientes visitadas");
		this.setSize(300,160);
		this.setModal(true);

		JLabel lblNovo = new JLabel("Selecione a op��o desejada?");
		JButton btnDetalhado = new JButton("Relat�rio detalhado");
		btnDetalhado.setToolTipText("Gerar relat�rio de forma detalhada");
		JButton btnResumido = new JButton("Relat�rio resumido");
		btnResumido.setToolTipText("Gerar relat�rio de forma resumida");
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Concelar registro e fechar");

		Box linhaUm = Box.createHorizontalBox();
		Box linhaDois = Box.createHorizontalBox();
		Box linhaTres = Box.createHorizontalBox();
		Box linhaQuatro = Box.createHorizontalBox();

		linhaUm.add(lblNovo);
		linhaDois.add(btnDetalhado);
		linhaTres.add(btnResumido);
		linhaQuatro.add(btnCancelar);

		Box linhas = Box.createVerticalBox();
		linhas.add(linhaUm);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaDois);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaTres);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaQuatro);
		

		jContentPane = new JPanel();
		jContentPane.add(linhas);
		setContentPane(jContentPane);
				
		btnDetalhado.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Visita gerar = new Visita();
				setVisible(false);
				gerar.gerarRelatorio("", "").show();
			}});
		btnResumido.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Visita gerar = new Visita();
				setVisible(false);
				gerar.gerarRelatorioResumido().show();
			}});	
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
	}

}