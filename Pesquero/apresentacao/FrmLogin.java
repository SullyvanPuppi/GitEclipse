package apresentacao;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import javax.swing.SwingConstants;

import negocios.ValidaLogin;

public class FrmLogin extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JButton btnEntrar = null;

	private JLabel lblImgLogo = null;

	private JLabel lblAcessar = null;

	private JLabel lblLogin = null;

	private JTextField txtLogin = null;

	private JLabel lblSenha = null;

	private JPasswordField txtSenha = null;

	/**
	 * This is the default constructor
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
			UIManager.setLookAndFeel(new WindowsLookAndFeel());
			SwingUtilities.updateComponentTreeUI(rootPane);			
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(280, 450);
		this.setLocation((largura/2)-(280/2), (altura/2)-(450/2));
		this.setFont(new Font("Dialog", Font.BOLD, 14));
		this.setTitle("Login");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblSenha = new JLabel();
			lblSenha.setBounds(new Rectangle(6, 323, 43, 20));
			lblSenha.setForeground(Color.white);
			lblSenha.setFont(new Font("Dialog", Font.PLAIN, 14));
			lblSenha.setText("Senha");
			lblLogin = new JLabel();
			lblLogin.setBounds(new Rectangle(6, 273, 150, 20));
			lblLogin.setForeground(Color.white);
			lblLogin.setFont(new Font("Dialog", Font.PLAIN, 14));
			lblLogin.setText("Login de acesso");
			lblAcessar = new JLabel();
			lblAcessar.setBounds(new Rectangle(6, 251, 150, 16));
			lblAcessar.setForeground(Color.white);
			lblAcessar.setFont(new Font("Dialog", Font.BOLD, 14));
			lblAcessar.setText("Acessar o sistema:");
			lblImgLogo = new JLabel();
			lblImgLogo.setBounds(new Rectangle(3, 2, 265, 243));
			lblImgLogo.setHorizontalAlignment(SwingConstants.CENTER);
			lblImgLogo.setIcon(new ImageIcon(getClass().getResource("/apresentacao/logo.gif")));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.setBackground(new Color(43, 43, 128));
			jContentPane.add(getBtnEntrar(), null);
			jContentPane.add(lblImgLogo, null);
			jContentPane.add(lblAcessar, null);
			jContentPane.add(lblLogin, null);
			jContentPane.add(getTxtLogin(), null);
			jContentPane.add(lblSenha, null);
			jContentPane.add(getTxtSenha(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes btnEntrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	/**
	 * This method initializes btnEntrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEntrar() {
		if (btnEntrar == null) {
			btnEntrar = new JButton();
			btnEntrar.setBounds(new Rectangle(6, 381, 260, 30));
			btnEntrar.setText("Entrar");
			btnEntrar.setFont(new Font("Dialog", Font.BOLD, 14));
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
							FrmPrincipal formPrincipal = new FrmPrincipal(objLogin.getPermissao(), txtLogin.getText());
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

	/**
	 * This method initializes txtLogin	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtLogin() {
		if (txtLogin == null) {
			txtLogin = new JTextField();
			txtLogin.setBounds(new Rectangle(6, 293, 152, 22));
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
			txtSenha.setBounds(new Rectangle(6, 343, 152, 22));
		}
		return txtSenha;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
