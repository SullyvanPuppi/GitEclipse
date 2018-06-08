package apresentacao;

//Classes internas
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import negocios.Usuario;

/**
 * @author Sullyvan Puppi
 *
 * Classe responsável pelo formulário para cadastrar e exibir dados
 * de usuários.
 * 
 */
public class FrmCadastroUsuario extends JInternalFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4117891928030975588L;
	/**
	 * 
	 */
	private JLabel lblNomeUsuario = null;
	private JLabel lblUsuario = null;
	private JTextField txtNomeUsuario = null;
	private JTextField txtLogin = null;
	private JLabel lblGrupoUsuarios = null;
	private JComboBox cmbGrupoUsuarios = null;
	private JLabel lblSenhaUsuario = null;
	private JPasswordField txtSenha = null;
	private JLabel lblCorrigeSenha = null;
	private JPasswordField txtCorrigeSenha = null;
	private JButton btnConfirmar = null;
	private JButton btnCancelar = null;
	private JLabel lblDivisao = null;

	//-----Atributo de Opção de visualização
	private int op;

	//-----Retorna opção de visualização
	public int getOp(){
		return this.op;
	}

	//-----Atributo para armazenar o login atual
	private String login;

	//-----Retorna o login atual
	public String getLogin(){
		return this.login;
	}
	//-----Titulo da janela
	private String titulo;

	private String getTitulo(){
		return this.titulo;
	}
	
	private int permissao;
	
	public int getPermissao(){
		return permissao;
	}

	/**
	 * This is the xxx default constructor
	 */
	public FrmCadastroUsuario(JDesktopPane desktop, int permissao, int op, String login) {
		super();
		this.op = op;
		this.login = login;
		this.permissao = permissao;
		initialize(op, login);
		/*----Opções: 
		 * 0 - Para Novo cadastro
		 * 1 - Exibir usuário atual
		 */
		if(op==0){//Cadastro novo
			Novo();
		}		
		if(op==1){//Exibir usuário atual
			btnAlterarSenha.setVisible(true);
			exibirUsuario(login);
		}		
	}

	//Atributo que armazena os items do combobox grupos
	private DefaultComboBoxModel gruposModel;

	private JLabel lblCargo = null;
	private JTextField txtCargo = null;
	private JButton btnAlterarSenha = null;
	private JButton btnNovaSenha = null;
	private JButton btnEditar = null;

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize(int op, String login) {
		if(op==0){
			this.titulo="][ Novo usuário ][";
		}else if(op==1){
			this.titulo="][ Cadastro de usuário ][";
		}else if(op==2){
			this.titulo="][ Alterar senha ][";
		}
		this.setTitle(""+getTitulo()+"");

		lblCargo = new JLabel("Cargo");
		lblCorrigeSenha = new JLabel("Digite a senha novamente");
		lblSenhaUsuario = new JLabel("Senha");
		lblGrupoUsuarios = new JLabel("Grupo de usuários");
		lblUsuario = new JLabel("Login");
		lblNomeUsuario = new JLabel("Nome");
		lblDivisao = new JLabel("______________________________________________________________________");

		Box linhaUm = Box.createHorizontalBox();
		Box linhaDois = Box.createHorizontalBox();
		Box linhaTres = Box.createHorizontalBox();
		Box linhaQuatro = Box.createHorizontalBox();
		Box linhaCinco = Box.createHorizontalBox();
		Box linhaSeis = Box.createHorizontalBox();
		Box linhaSete = Box.createHorizontalBox();
		Box linhaOito = Box.createHorizontalBox();

		linhaUm.add(lblNomeUsuario);
		linhaUm.add(getTxtNomeUsuario());
		linhaDois.add(lblUsuario);
		linhaDois.add(getTxtLogin());
		linhaTres.add(lblCargo);
		linhaTres.add(getTxtCargo());
		linhaQuatro.add(lblGrupoUsuarios);
		linhaQuatro.add(getCmbGrupoUsuarios());
		linhaCinco.add(lblSenhaUsuario);
		linhaCinco.add(getTxtSenha());
		linhaSeis.add(lblCorrigeSenha);
		linhaSeis.add(getTxtCorrigeSenha());
		linhaSete.add(lblDivisao);
		linhaOito.add(getBtnAlterarSenha());
		linhaOito.add(Box.createHorizontalStrut(5));
		linhaOito.add(getBtnNovaSenha());
		linhaOito.add(Box.createHorizontalStrut(5));
		linhaOito.add(getBtnEditar());
		linhaOito.add(Box.createHorizontalStrut(5));
		linhaOito.add(getBtnConfirmar());
		linhaOito.add(Box.createHorizontalStrut(5));
		linhaOito.add(getBtnCancelar());			

		Box linhas = Box.createVerticalBox();
		linhas.add(linhaUm);
		linhas.add(Box.createVerticalStrut(5));		
		linhas.add(linhaDois);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaTres);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaQuatro);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaCinco);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaSeis);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaSete);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaOito);

		JPanel jContentPane = new JPanel();
		jContentPane.add(linhas);
		getContentPane().add(jContentPane);

		pack();

		getTxtSenha().setEnabled(false);
		getTxtCorrigeSenha().setEnabled(false);
	}
	/*-----------Quadro de direitos no formulário de cadastro de usuário-------//
	 * 
	 * 1 - Administrador - Pleno direitos
	 * 
	 * 2 - Secretaria - Usuário comum
	 * 
	 * 3 - Convidado - Usuário comum
	 */	

	//---Método para exibir campos para novo cadastro
	private void Novo(){
		//Possui direito de administrador
		habilitarCampos();
		btnNovaSenha.setVisible(true);
		btnNovaSenha.setEnabled(true);
		btnEditar.setEnabled(false);
		btnConfirmar.setEnabled(true);			
		btnAlterarSenha.setVisible(false);
	}
	//---Método de acesso ao usuário para alterar senha
	private void exibirUsuario(String login){
		desabilitarCampos();
		btnConfirmar.setEnabled(false);
		txtCorrigeSenha.setVisible(true);
		lblCorrigeSenha.setVisible(true);
		consultar(login);
	}
	//---Método desabilitar campos
	private void desabilitarCampos(){
		txtNomeUsuario.setEditable(false);
		txtLogin.setEditable(false);
		txtCargo.setEditable(false);
		txtSenha.setEnabled(false);
		txtCorrigeSenha.setEnabled(false);
		cmbGrupoUsuarios.setEnabled(false);
	}
	//----Método habilitar campos
	private void habilitarCampos(){
		txtNomeUsuario.setEditable(true);
		txtLogin.setEditable(true);
		txtCargo.setEditable(true);
		cmbGrupoUsuarios.setEnabled(true);
	}
	//----Consultar para exibir dados
	private void consultar(String login){
		Usuario consultar = new Usuario();
		consultar.consultar(login);
		txtNomeUsuario.setText(consultar.getUsuario());
		txtLogin.setText(consultar.getLogin());
		txtCargo.setText(consultar.getCargo());
		cmbGrupoUsuarios.setSelectedItem((String)consultar.getGrupo());
		txtSenha.setText(consultar.getSenha());
		txtCorrigeSenha.setText(consultar.getSenha());
	}
	/**
	 * This method initializes txtNomeUsuario	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNomeUsuario() {
		if (txtNomeUsuario == null) {
			txtNomeUsuario = new JTextField("",45);
			txtNomeUsuario.setToolTipText("Nome do usuário");
		}
		return txtNomeUsuario;
	}
	/**
	 * This method initializes txtLogin	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtLogin() {
		if (txtLogin == null) {
			txtLogin = new JTextField("",20);
			txtLogin.setToolTipText("Login para acesso ao sistema");
		}
		return txtLogin;
	}
	/**
	 * This method initializes cmbGrupoUsuarios	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbGrupoUsuarios() {
		gruposModel = new DefaultComboBoxModel();
		gruposModel.addElement(new String("Selecione um grupo de usuários"));
		if(getPermissao()==1){
			gruposModel.addElement(new String("Administrador"));
			gruposModel.addElement(new String("Secretaria"));
			gruposModel.addElement(new String("Convidado"));	
		}else{
			gruposModel.addElement(new String("Secretaria"));
			gruposModel.addElement(new String("Convidado"));
		}		
		cmbGrupoUsuarios = new JComboBox(gruposModel);
		cmbGrupoUsuarios.setToolTipText("Grupo de usuários determinando direitos no sistema");
		return cmbGrupoUsuarios;
	}
	/**
	 * This method initializes txtSenha	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getTxtSenha() {
		if (txtSenha == null) {
			txtSenha = new JPasswordField("",20);
			txtSenha.setToolTipText("Senha para acesso ao sistema");
		}
		return txtSenha;
	}
	/**
	 * This method initializes txtCorrigeSenha	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getTxtCorrigeSenha() {
		if (txtCorrigeSenha == null) {
			txtCorrigeSenha = new JPasswordField("",20);
			txtCorrigeSenha.setToolTipText("Confirmação de senha");
		}
		return txtCorrigeSenha;
	}
	/**
	 * This method initializes btnConfirmar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton("Confirmar");
			btnConfirmar.setToolTipText("Confirmar o cadastro");
			btnConfirmar.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
					if (getOp()==0){
						Usuario validar = new Usuario();
						//Ajusta dados na classe Usuário para validar
						validar.ajustaDados(getTxtNomeUsuario().getText(), getTxtLogin().getText(), getTxtCargo().getText(), (String) cmbGrupoUsuarios.getSelectedItem(), getTxtSenha().getText(), getTxtCorrigeSenha().getText());
						//Se não tiver pendências retornará 0 se tiver retornará o número de pendeências
						if(validar.validar()>=1){
							JOptionPane.showMessageDialog(null,"Encontrada(s) inconsistência(s) "+validar.validar()+" erros.\nO(s) seguintes dados estão incorretos:"+validar.getStatus(),"Dados incorretos",2);
						}else{
							//Não encotro pendências nos dados, verifica se Login ja existe
							if(validar.getExiste()>=1){	
								JOptionPane.showMessageDialog(null, "Login ja existe. Por favor digite outro login.","Login existente",2);
								getTxtLogin().setText("");
								getTxtLogin().grabFocus();
							}//Login não existe então cadastrar e limpar campos
							else{
								validar.cadastrar();
								desabilitarCampos();
								btnConfirmar.setEnabled(false);
								btnEditar.setEnabled(true);
							}
						}
					}else{
						Usuario validar = new Usuario();
						//Ajusta dados na classe Usuaário para validar
						validar.ajustaDados(getTxtNomeUsuario().getText(), getTxtLogin().getText(), getTxtCargo().getText(), (String) cmbGrupoUsuarios.getSelectedItem(), getTxtSenha().getText(), getTxtCorrigeSenha().getText());
						//Se não tiver pendências retornará 0 se tiver retornará o número de pendeências
						if(validar.validar()>=1){
							JOptionPane.showMessageDialog(null,"Foram encontrados "+validar.validar()+" erros.\nO(s) seguintes dados estão incorretos:"+validar.getStatus(),"Dados incorretos",1);
							txtSenha.setText("");
							txtCorrigeSenha.setText("");
							txtSenha.grabFocus();
						}else{
							validar.alterar();
							btnConfirmar.setEnabled(false);
							btnEditar.setEnabled(true);
							desabilitarCampos();
						}
					}					
				}
			});
		}
		return btnConfirmar;
	}
	/**
	 * This method initializes btnCancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setToolTipText("Cancelar o cadastro");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Fecha a janela
					dispose();
				}
			});
		}
		return btnCancelar;
	}
	/**
	 * This method initializes txtCargo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtCargo() {
		if (txtCargo == null) {
			txtCargo = new JTextField("",45);
			txtCargo.setToolTipText("Cargo que exerce na empresa");
		}
		return txtCargo;
	}
	/**
	 * This method initializes btnAlterarSenha	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAlterarSenha() {
		if (btnAlterarSenha == null) {
			btnAlterarSenha = new JButton("Alterar senha");
			btnAlterarSenha.setToolTipText("Alterar senha cadastrada");
			btnAlterarSenha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Usuario consultar = new Usuario();
					String atual = JOptionPane.showInputDialog(null,"Digite sua senha atual","Senha atual", 3);
					consultar.consultarSenha(getLogin(), atual);				
					if(consultar.getConfirma()==1){//Se confirmar alteração de senha desabilitar campos
						btnConfirmar.setEnabled(true);
						txtSenha.setEnabled(true);
						txtCorrigeSenha.setVisible(true);
						lblCorrigeSenha.setVisible(true);
						txtCorrigeSenha.setEnabled(true);
					}											
				}
			});
		}
		return btnAlterarSenha;
	}
	/**
	 * This method initializes btnNovaSenha	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnNovaSenha() {
		if (btnNovaSenha == null) {
			btnNovaSenha = new JButton("Nova senha");
			btnNovaSenha.setToolTipText("Nova senha");
			btnNovaSenha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Confirmar opção de alterar senha
					if (JOptionPane.showConfirmDialog(new JFrame(),
							"Deseja alterar a senha deste usuário?\nObs.: Está ação cadastrará o login como senha.", "Confirmação",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						getTxtSenha().setText(getTxtLogin().getText());
						getTxtCorrigeSenha().setText(getTxtLogin().getText());
						btnConfirmar.setEnabled(true);
					}					
				}
			});
		}
		return btnNovaSenha;
	}
	/**
	 * This method initializes btnEditar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEditar() {
		if (btnEditar == null) {
			btnEditar = new JButton("Editar");
			btnEditar.setToolTipText("Editar o cadastro do usuário");
			btnEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					habilitarCampos();//Habilita campos para edição
					btnConfirmar.setEnabled(true);
					btnEditar.setEnabled(false);
				}
			});
		}
		return btnEditar;
	}
} 