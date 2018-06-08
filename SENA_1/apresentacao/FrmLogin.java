package apresentacao;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.PlasticXPLookAndFeel;
import javax.swing.UnsupportedLookAndFeelException;
import com.jgoodies.looks.plastic.theme.*;
import javax.swing.SwingUtilities;

//Importando classe responsável pela parte lógica do login
import negocios.ValidaLogin;

/**
 * @author Sullyvan Puppi
 *
 * Formulário de login do sistema
 * 
 * Usuário entrará com login e senha.
 * 
 */
public class FrmLogin extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel lbl1 = null;
	private JLabel lblNome = null;
	private JLabel lblImgLogo = null;
	private JTextField txtLogin = null;
	private JLabel lblSenha = null;
	private JPasswordField txtSenha = null;
	private JButton btnEntrar = null;
	
	
	/**
	 * This is the xxx default constructor
	 */
	public FrmLogin(int largura, int altura) {
		super();
		initialize(largura, altura);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize(int largura, int altura) {
		try {
			UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
			PlasticLookAndFeel.setPlasticTheme(new ExperienceBlue());
			UIManager.setLookAndFeel(new PlasticXPLookAndFeel());
			SwingUtilities.updateComponentTreeUI(rootPane);
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(421, 195);
		this.setLocation((largura/2)-(421/2), (altura/2)-(195/2));
		this.setFont(new Font("Dialog", Font.BOLD, 14));
		this.setTitle("][ Login ][");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblImgLogo = new JLabel();
			lblImgLogo.setBounds(new Rectangle(5, 5, 242, 156));
			lblImgLogo.setIcon(new ImageIcon(getClass().getResource("/apresentacao/logo.gif")));
			lblSenha = new JLabel();
			lblSenha.setBounds(new Rectangle(256, 68, 49, 22));
			lblSenha.setFont(new Font("Dialog", Font.PLAIN, 14));
			lblSenha.setText("Senha:");
			lblNome = new JLabel();
			lblNome.setBounds(new Rectangle(256, 28, 58, 22));
			lblNome.setFont(new Font("Dialog", Font.PLAIN, 14));
			lblNome.setText("Usuário:");
			lbl1 = new JLabel();
			lbl1.setBounds(new Rectangle(255, 13, 134, 16));
			lbl1.setFont(new Font("Dialog", Font.BOLD, 14));
			lbl1.setText("Acessar o sistema:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(lblImgLogo);					
			jContentPane.add(lbl1, null);
			jContentPane.add(lblNome, null);
			jContentPane.add(getTxtLogin(), null);
			jContentPane.add(lblSenha, null);
			jContentPane.add(getTxtSenha(), null);
			jContentPane.add(getBtnEntrar(), null);

		}
		return jContentPane;
	}
	/**
	 * This method initializes txtLogin	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtLogin() {
		if (txtLogin == null) {
			txtLogin = new JTextField();
			txtLogin.setBounds(new Rectangle(256, 48, 152, 22));
			txtLogin.setFont(new Font("Dialog", Font.PLAIN, 12));
			txtLogin.setToolTipText("Login de acesso");
		}
		return txtLogin;
	}

	/**
	 * This method initializes txtSenha	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getTxtSenha() {
		if (txtSenha == null) {
			txtSenha = new JPasswordField();
			txtSenha.setBounds(new Rectangle(256, 88, 152, 22));
			txtSenha.setFont(new Font("Dialog", Font.PLAIN, 12));
			txtSenha.setToolTipText("Senha de acesso");
		}
		return txtSenha;
	}

	/**
	 * This method initializes btnEntrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEntrar() {
		if (btnEntrar == null) {
			btnEntrar = new JButton();
			btnEntrar.setBounds(new Rectangle(256, 118, 152, 30));
			btnEntrar.setFont(new Font("Dialog", Font.BOLD, 14));
			btnEntrar.setText("Entrar");
			btnEntrar.setToolTipText("Acessar o sistema");
			btnEntrar.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {
					if (txtLogin.getText().equals("") || txtSenha.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Preencha os campos usuário e senha.","Erro",1);
					}else{
						ValidaLogin objLogin = new ValidaLogin(txtLogin.getText(), txtSenha.getText());
						objLogin.Validar();//Validação dos dados enviados				
						if (objLogin.getPermissao()>0){//Se maior que 0 validado este representa direitos de usuário
							FrmPrincipal formPrincipal = new FrmPrincipal(objLogin.getPermissao(), objLogin.getLogin());
							formPrincipal.show();
							dispose();
						}else{
							getTxtLogin().setText("");
							getTxtSenha().setText("");
						}
					}
				}
			});
		}
		return btnEntrar;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"