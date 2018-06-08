package apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import negocios.Seguranca;

public class FrmBackup extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4884522073027754687L;

	private String login = "";
	
	JTextField txtNmFuncionario = null;
	JTextField txtDtBackup = null;
	JTextField txtCaminho = null;
	JProgressBar progresso = null;
	
	private String getLogin(){
		return login;
	}

	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	JDesktopPane desk = null;

	private JLabel lblDivisao = null;
	
	/**
	 * This is the xxx default constructor
	 */
	public FrmBackup(JDesktopPane desktop, int op, String login) {
		super();
		this.desk = desktop;
		this.login = login;
		initialize();
		if(op>0){//restaurar cópia de segurança
			
		}else{//realizar cópia de segurança
			txtNmFuncionario.setText(login);
		}
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Cópia de segurança da base de dados");
		this.setModal(true);
		
		JLabel lblNmFuncionario = new JLabel("Funcionário");
		txtNmFuncionario = new JTextField(getLogin());
		JLabel lblData = new JLabel("Data da cópia de segurança");
		txtDtBackup = new JTextField(10);
		JLabel lblSalvar = new JLabel("Salvar em:");
		txtCaminho = new JTextField(45);
		JButton btnCaminho = new JButton("Caminho");
		JButton btnSalvar = new JButton("Salvar");
		JButton btnCancelar = new JButton("Cancelar");
		progresso = new JProgressBar();
					
		lblDivisao = new JLabel("___________________________________________________________________________________________________________________________");
		
		Box linhaUm = Box.createHorizontalBox();
		Box linhaTres = Box.createHorizontalBox();
		Box linhaQuatro = Box.createHorizontalBox();
		
		Box linhaDoze = Box.createHorizontalBox();
		Box linhaTreze = Box.createHorizontalBox();

		linhaUm.add(lblNmFuncionario);
		linhaUm.add(txtNmFuncionario);
		linhaTres.add(lblData);
		linhaTres.add(txtDtBackup);
		linhaTres.add(Box.createHorizontalStrut(5));
		linhaTres.add(lblSalvar);
		linhaTres.add(txtCaminho);
		linhaTres.add(Box.createHorizontalStrut(5));
		linhaTres.add(btnCaminho);	
		linhaQuatro.add(progresso);
				
		linhaDoze.add(lblDivisao);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnSalvar);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnCancelar);

		Box linhas = Box.createVerticalBox();
		linhas.add(linhaUm);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaTres);
		linhas.add(Box.createVerticalStrut(15));
		linhas.add(linhaQuatro);
		linhas.add(Box.createVerticalStrut(15));
						
		linhas.add(linhaDoze);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaTreze);

		jContentPane = new JPanel();
		jContentPane.add(linhas);
		getContentPane().add(jContentPane);
		pack();

		btnCaminho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Seguranca selecionar = new Seguranca();
				//caminho = selecionar.selecionaCaminho();	
				selecionar.geraBackupClientes();
			}});
	}
	/*private String caminho = "";
	private String getCaminho(){
		return caminho;
	}*/
}
