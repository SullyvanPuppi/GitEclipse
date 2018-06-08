package apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import negocios.Funcionario;

public class FrmRelatorioFuncionarios extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -974379423795839264L;

	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	JDesktopPane desk = null;

	//Direitos de usuário determinado pelo grupo
	private int permissao;

	public int getPermissao(){
		return this.permissao;
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
	public FrmRelatorioFuncionarios(JDesktopPane desktop) {
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
		this.setTitle("Relatório de funcionários");
		this.setSize(300,160);
		this.setModal(true);

		JLabel lblNovo = new JLabel("Selecione a opção desejada?");
		JButton btnDetalhado = new JButton("Relatório detalhado");
		btnDetalhado.setToolTipText("Gerar relatório de forma detalhada");
		JButton btnResumido = new JButton("Relatório resumido");
		btnResumido.setToolTipText("Gerar relatório de forma resumida");
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
				Funcionario gerar = new Funcionario();
				setVisible(false);
				gerar.gerarRelatorio("", "").show();
			}});
		btnResumido.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Funcionario gerar = new Funcionario();
				setVisible(false);
				gerar.gerarRelatorioResumido().show();
			}});	
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
	}

}