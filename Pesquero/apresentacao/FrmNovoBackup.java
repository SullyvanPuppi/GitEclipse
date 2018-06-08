package apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

//@SuppressWarnings("serial")
public class FrmNovoBackup extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4337949359663162419L;

	FrmBackup formBackup;
	
	private String login = "";
	
	private String getLogin(){
		return login;
	}
	
	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	private JDesktopPane desk = null;

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
	public FrmNovoBackup(JDesktopPane desktop, String login) {
		super();
		this.desk = desktop;
		this.login = login;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Cópia de segurança");
		this.setSize(200,160);
		this.setModal(true);

		JLabel lblNovo = new JLabel("Selecione a opção desejada?");
		JButton btnCopia = new JButton("Realizar cópia de segurança");
		JButton btnCarregar = new JButton("Carregar cópia de segurança");
		JButton btnCancelar = new JButton("Cancelar");

		Box linhaUm = Box.createHorizontalBox();
		Box linhaDois = Box.createHorizontalBox();
		Box linhaTres = Box.createHorizontalBox();
		Box linhaQuatro = Box.createHorizontalBox();

		linhaUm.add(lblNovo);
		linhaDois.add(btnCopia);
		linhaTres.add(btnCarregar);
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
				
		btnCopia.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if ((formBackup == null) || (!formBackup.isFocused())) {
					formBackup = new FrmBackup(desk, 0, getLogin());
					formBackup.setLocation(100, 100);
					setVisible(false);
					formBackup.show();
				}
			}});
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}});	
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
	}

}