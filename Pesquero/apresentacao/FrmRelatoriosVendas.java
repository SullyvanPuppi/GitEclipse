package apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import negocios.CapturaTela;
import negocios.Venda;

public class FrmRelatoriosVendas extends JDialog {

	FrmExtratoVendas formExtratoVendas;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2367227251896700531L;

	String login = "";
	
	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	JDesktopPane desk = null;

	//Direitos de usuário determinado pelo grupo
	private int permissao;

	public int getPermissao(){
		return this.permissao;
	}
	public String getLogin(){
		return login;
	}
	//---Ajusta permissão no sistema
	public void ajustaPermissao(){
		if(getPermissao()==1){//Administrdor		

		}else if(getPermissao()==2){//Secretaria

		}else if(getPermissao()==3){//Convidado

		}
	}
	/**
	 * This is the xxx default constructor
	 */
	public FrmRelatoriosVendas(JDesktopPane desktop, String login, int permissao) {
		super();
		this.desk = desktop;
		this.login = login;
		this.permissao = permissao;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Relatório de vendas realizadas");
		this.setSize(300,190);
		this.setModal(true);

		JLabel lblNovo = new JLabel("Selecione a opção desejada?");
		JButton btnDetalhado = new JButton("Relatório detalhado");
		JButton btnResumido = new JButton("Relatório resumido");
		JButton btnExtrato = new JButton("Extrato");
		JButton btnCancelar = new JButton("Cancelar");

		Box linhaUm = Box.createHorizontalBox();
		Box linhaDois = Box.createHorizontalBox();
		Box linhaTres = Box.createHorizontalBox();
		Box linhaSeis = Box.createHorizontalBox();
		Box linhaQuatro = Box.createHorizontalBox();

		linhaUm.add(lblNovo);
		linhaDois.add(btnDetalhado);
		linhaTres.add(btnResumido);
		linhaSeis.add(btnExtrato);
		linhaQuatro.add(btnCancelar);

		Box linhas = Box.createVerticalBox();
		linhas.add(linhaUm);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaDois);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaTres);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaSeis);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaQuatro);
		

		jContentPane = new JPanel();
		jContentPane.add(linhas);
		setContentPane(jContentPane);
		
		btnDetalhado.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Venda gerar = new Venda();
				setVisible(false);
				gerar.gerarRelatorio(getPermissao(), "", "","","/  /","/  /","").show();
			}});
		btnExtrato.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				formExtratoVendas = new FrmExtratoVendas(desk, getLogin(), getPermissao());
				CapturaTela tela = new CapturaTela();
				formExtratoVendas.setLocation((tela.getLarguraTela()/2)-(300/2), (tela.getAlturaTela()/2)-(160/2));
				formExtratoVendas.show();
			}});
		btnResumido.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Venda gerar = new Venda();
				setVisible(false);
				gerar.gerarRelatorioResumido().show();
			}});	
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
	}

}