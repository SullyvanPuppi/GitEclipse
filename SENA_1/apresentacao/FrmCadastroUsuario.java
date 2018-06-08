package apresentacao;

//Classes internas
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.SwingConstants;

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
	private static final long serialVersionUID = -8171015259832148475L;
	private JPanel jContentPane = null;
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
	
	/**
	 * This is the xxx default constructor
	 */
	public FrmCadastroUsuario(JDesktopPane desktop, int permissao, int op, String login, String loginAtual) {
		super();
		initialize(op, login, loginAtual);
		this.op = op;
		/*----Opções: 
		 * 0 - Para Novo cadastro
		 * 1 - Para exibir usuário selecionado
		 * 2 - Exibir usuário atual
		 */
		if(op==0){//Cadastro novo
			Novo();
		}
		if(permissao==1){
			btnNovaSenha.setVisible(true);
			btnEditar.setVisible(true);
		}else{
			btnNovaSenha.setVisible(false);
			btnEditar.setVisible(false);
		}
		if(op==1){//Exibir usuário selecionado
			if(login.equals(loginAtual)){
				btnAlterarSenha.setVisible(true);
			}else{
				btnAlterarSenha.setVisible(false);
			}exibirUsuario(login);
		}
		if(op==2){//Exibir usuário atual
			if(login.equals(loginAtual)){
				btnAlterarSenha.setVisible(true);
			}else{
				btnAlterarSenha.setVisible(false);
			}exibirUsuario(login);
		}
		this.login = login;	
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
	private void initialize(int op, String login, String loginAtual) {
		this.setSize(389, 285);
		this.setLocation(new Point(210, 10));
		this.setContentPane(getJContentPane());
		if(op==0){
			this.titulo="][ Novo usuário ][";
		}else if(op==1){
			this.titulo="][ Cadastro de usuário ][";
		}else if(op==2){
			this.titulo="][ Alterar senha ][";
		}
		if(login.equals(loginAtual)){
			this.titulo="][ Alterar senha ][";
		}
		this.setTitle(""+getTitulo()+"");
		getTxtSenha().setEnabled(false);
		getTxtCorrigeSenha().setEnabled(false);
	}
	/*-----------Quadro de direitos no formulário de cadastro de usuário-------//
	 * 
	 * 1 - Administrador - Pleno direitos
	 * 
	 * 2 - Coordenador de cursos - Usuário comum
	 * 
	 * 3 - Secretaria - Usuário comum
	 * 
	 * 4 - Convidado - Usuário comum
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
		txtNomeUsuario.setText(consultar.getTxtNomeUsuario());
		txtLogin.setText(consultar.getTxtLogin());
		txtCargo.setText(consultar.getTxtCargo());
		cmbGrupoUsuarios.setSelectedItem((String)consultar.getGrupoUsuario());
		txtSenha.setText(consultar.getTxtSenha());
		txtCorrigeSenha.setText(consultar.getTxtSenha());
	}
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblCargo = new JLabel();
			lblCargo.setBounds(new Rectangle(19, 67, 38, 20));
			lblCargo.setText("Cargo");
			lblDivisao = new JLabel();
			lblDivisao.setBounds(new Rectangle(-50, 213, 510, 16));
			lblDivisao.setHorizontalAlignment(SwingConstants.CENTER);
			lblDivisao.setForeground(new Color(190, 190, 190));
			lblDivisao.setText("_____________________________________________________________________");
			lblCorrigeSenha = new JLabel();
			lblCorrigeSenha.setBounds(new Rectangle(19, 177, 151, 20));
			lblCorrigeSenha.setText("Digite a senha novamente:");
			lblSenhaUsuario = new JLabel();
			lblSenhaUsuario.setBounds(new Rectangle(19, 127, 40, 20));
			lblSenhaUsuario.setText("Senha:");
			lblGrupoUsuarios = new JLabel();
			lblGrupoUsuarios.setBounds(new Rectangle(19, 97, 111, 22));
			lblGrupoUsuarios.setText("Grupo de usuários");
			lblUsuario = new JLabel();
			lblUsuario.setBounds(new Rectangle(21, 37, 36, 22));
			lblUsuario.setText("Login");
			lblNomeUsuario = new JLabel();
			lblNomeUsuario.setBounds(new Rectangle(19, 7, 39, 22));
			lblNomeUsuario.setText("Nome");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(lblNomeUsuario, null);
			jContentPane.add(lblUsuario, null);
			jContentPane.add(getTxtNomeUsuario(), null);
			jContentPane.add(getTxtLogin(), null);
			jContentPane.add(lblGrupoUsuarios, null);
			jContentPane.add(getCmbGrupoUsuarios(), null);
			jContentPane.add(lblSenhaUsuario, null);
			jContentPane.add(getTxtSenha(), null);
			jContentPane.add(lblCorrigeSenha, null);
			jContentPane.add(getTxtCorrigeSenha(), null);
			jContentPane.add(getBtnConfirmar(), null);
			jContentPane.add(getBtnCancelar(), null);
			jContentPane.add(lblDivisao, null);
			jContentPane.add(lblCargo, null);
			jContentPane.add(getTxtCargo(), null);
			jContentPane.add(getBtnAlterarSenha(), null);
			jContentPane.add(getBtnNovaSenha(), null);
			jContentPane.add(getBtnEditar(), null);
		}
		return jContentPane;
	}
	/**
	 * This method initializes txtNomeUsuario	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNomeUsuario() {
		if (txtNomeUsuario == null) {
			txtNomeUsuario = new JTextField();
			txtNomeUsuario.setBounds(new Rectangle(50, 7, 320, 22));
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
			txtLogin = new JTextField();
			txtLogin.setBounds(new Rectangle(50, 37, 152, 22));
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
		gruposModel.addElement(new String("Administrador"));
		gruposModel.addElement(new String("Coordenador de cursos"));
		gruposModel.addElement(new String("Secretaria"));
		gruposModel.addElement(new String("Convidado"));
		cmbGrupoUsuarios = new JComboBox(gruposModel);
		cmbGrupoUsuarios.setBounds(new Rectangle(110, 97, 258, 22));
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
			txtSenha = new JPasswordField();
			txtSenha.setBounds(new Rectangle(19, 147, 152, 22));
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
			txtCorrigeSenha = new JPasswordField();
			txtCorrigeSenha.setBounds(new Rectangle(19, 197, 152, 22));
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
			btnConfirmar = new JButton();
			btnConfirmar.setBounds(new Rectangle(177, 233, 91, 22));
			btnConfirmar.setText("Confirmar");
			btnConfirmar.setToolTipText("Confirmar o cadastro");
			btnConfirmar.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
					if (getOp()==0){
						Usuario validar = new Usuario();
						//Ajusta dados na classe Usuário para validar
						validar.ajustaDados(getTxtLogin().getText(), getTxtSenha().getText(), getTxtCorrigeSenha().getText(), getTxtNomeUsuario().getText(), getTxtCargo().getText(), (String) cmbGrupoUsuarios.getSelectedItem());
						//Se não tiver pendências retornará 0 se tiver retornará o número de pendeências
						if(validar.validar()>=1){
							JOptionPane.showMessageDialog(null,"Encontrada(s) inconsistência(s) "+validar.validar()+" erros.\nO(s) seguintes dados estão incorretos:"+validar.getStatus(),"Dados incorretos",1);
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
						validar.ajustaDados(getTxtLogin().getText(), getTxtSenha().getText(), getTxtCorrigeSenha().getText(), getTxtNomeUsuario().getText(), getTxtCargo().getText(), (String) cmbGrupoUsuarios.getSelectedItem());
						//Se não tiver pendências retornará 0 se tiver retornará o número de pendeências
						if(validar.validar()>=1){
							JOptionPane.showMessageDialog(null,"Foram encontrados "+validar.validar()+" erros.\nO(s) seguintes dados estão incorretos:"+validar.getStatus(),"Dados incorretos",1);
							txtSenha.setText("");
							txtCorrigeSenha.setText("");
							txtSenha.grabFocus();
						}else{
							validar.alterar(getLogin());
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
			btnCancelar = new JButton();
			btnCancelar.setBounds(new Rectangle(277, 233, 91, 22));
			btnCancelar.setText("Cancelar");
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
			txtCargo = new JTextField();
			txtCargo.setBounds(new Rectangle(50, 67, 320, 22));
			txtCargo.setToolTipText("Cargo na escola do usuário");
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
			btnAlterarSenha = new JButton();
			btnAlterarSenha.setBounds(new Rectangle(258, 197, 111, 22));
			btnAlterarSenha.setText("Alterar senha");
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
			btnNovaSenha = new JButton();
			btnNovaSenha.setBounds(new Rectangle(258, 147, 111, 22));
			btnNovaSenha.setText("Nova senha");
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
			btnEditar = new JButton();
			btnEditar.setBounds(new Rectangle(77, 233, 91, 22));
			btnEditar.setText("Editar");
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

}  //  @jve:decl-index=0:visual-constraint="10,10"