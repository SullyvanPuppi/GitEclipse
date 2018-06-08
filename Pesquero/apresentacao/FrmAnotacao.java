package apresentacao;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import negocios.Anotacao;
import negocios.Venda;

public class FrmAnotacao extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1540072636544166126L;
	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	JDesktopPane desk = null;

	//Direitos de usuário determinado pelo grupo
	private int permissao;

	private JTextField txtRemetente = null;
	private JComboBox cmbDestinatario = null;
	private JFormattedTextField txtDtEnvio = null;
	private JTextField txtAssunto = null;
	private JTextArea txtAnotacao = null;
	private JScrollPane jspTxtAnotacao = null;
	private JLabel lblDivisao = null;
	private JButton btnConfirmar = null;
	private JButton btnEnviar = null;
	private JButton btnCancelar = null;

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
	//---Classe responsável por formatar márcaras de entrada de campos específicos
	private MaskFormatter setMascara(String mascara){  
		MaskFormatter mask = null;  
		try {  
			mask = new MaskFormatter(mascara);  
			mask.setPlaceholderCharacter(' ');  
		}  
		catch (java.text.ParseException exc) { 

		}  
		return mask;  
	}
	public void ajustaDados(String nmAssunto){
		Anotacao consultar = new Anotacao();
		consultar.consultarMsg(getLogin(), nmAssunto);
		txtRemetente.setText(consultar.getTxtRemetente());
		cmbDestinatario.setSelectedItem((String) consultar.getCmbDestinatario());
		txtDtEnvio.setText(consultar.getTxtDtEnvio());
		txtAssunto.setText(consultar.getTxtAssunto());
		txtAnotacao.setText(consultar.getTxtAnotacao());
	}
	public void desabilitaCampos(){
		if(getOp()==0){
			txtRemetente.setEditable(false);
			txtDtEnvio.setEditable(false);
		}else{
			txtRemetente.setEditable(false);
			cmbDestinatario.setEditable(false);
			txtDtEnvio.setEditable(false);
			txtAssunto.setEditable(false);
			txtAnotacao.setEditable(false);
		}
	}
	private int op;

	public int getOp(){
		return op;
	}

	private String loginAtual = "";
	public String getLogin(){
		return loginAtual;
	}
	/**
	 * This is the xxx default constructor
	 */
	public FrmAnotacao(JDesktopPane desktop, int permissao, int op, String login, String nmAssunto) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		this.op = op;
		this.loginAtual = login;
		initialize();
		if(getOp()==0){//Novo cadastro
			Anotacao novo = new Anotacao();
			txtDtEnvio.setText(novo.getHoje());
			txtRemetente.setText(novo.nmAtual(login));;
		}else{//Exibir dados
			ajustaDados(nmAssunto);
		}
		desabilitaCampos();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Recado");
		this.setResizable(false);
		this.setModal(true);

		JLabel lblRemetente = new JLabel("Remetente");
		txtRemetente = new JTextField();
		JLabel lblDestinatario = new JLabel("Destinatário");
		cmbDestinatario = new JComboBox(getUsuarios());
		JLabel lblDtEnvio = new JLabel("Data");
		txtDtEnvio = new JFormattedTextField(setMascara("##/##/####"));
		JLabel lblAssunto = new JLabel("Assunto");
		txtAssunto = new JTextField(45);
		JLabel lblAnotacao = new JLabel("Recado");
		txtAnotacao = new JTextArea();
		jspTxtAnotacao = new JScrollPane(txtAnotacao);
		jspTxtAnotacao.setPreferredSize(new Dimension(400, 200));
		
		txtRemetente.setBackground(Color.white);
		txtDtEnvio.setBackground(Color.white);
		txtAssunto.setBackground(Color.white);
		txtAnotacao.setBackground(Color.white);

		lblDivisao = new JLabel("________________________________________________________________________________________________");
		btnConfirmar = new JButton("Confirmar leitura");
		btnConfirmar.setToolTipText("Confirmar leitura do recado");
		btnEnviar = new JButton("Enviar");
		btnEnviar.setToolTipText("Enviar recado");
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Cancelar registro e fechar");

		Box linhaUm = Box.createHorizontalBox();
		Box linhaDois = Box.createHorizontalBox();
		Box linhaTres = Box.createHorizontalBox();
		Box linhaQuatro = Box.createHorizontalBox();
		Box linhaCinco = Box.createHorizontalBox();
		Box linhaSeis = Box.createHorizontalBox();

		Box linhaDoze = Box.createHorizontalBox();
		Box linhaTreze = Box.createHorizontalBox();

		linhaUm.add(lblRemetente);
		linhaUm.add(txtRemetente);
		linhaDois.add(lblDestinatario);
		linhaDois.add(cmbDestinatario);
		linhaTres.add(lblDtEnvio);
		linhaTres.add(txtDtEnvio);
		linhaTres.add(Box.createHorizontalStrut(15));
		linhaTres.add(lblAssunto);
		linhaTres.add(txtAssunto);
		linhaQuatro.add(lblAnotacao);
		linhaCinco.add(jspTxtAnotacao);
		linhaTres.add(lblAssunto);
		linhaTres.add(txtAssunto);

		linhaDoze.add(lblDivisao);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnConfirmar);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnEnviar);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnCancelar);

		Box linhas = Box.createVerticalBox();
		linhas.add(linhaUm);
		linhas.add(Box.createVerticalStrut(5));		
		linhas.add(linhaDois);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaTres);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaSeis);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaQuatro);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaCinco);
		linhas.add(Box.createVerticalStrut(5));

		linhas.add(linhaDoze);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaTreze);

		jContentPane = new JPanel();
		jContentPane.add(linhas);
		getContentPane().add(jContentPane);
		pack();

		//----Ações
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Anotacao cadastrar = new Anotacao();
				if (JOptionPane.showConfirmDialog(new JFrame(),
						"Deseja confirmar a leitura desta mensagem?", "Confirmação",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
					cadastrar.marcarLido(getLogin(), txtAssunto.getText());
				}	
			}});
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Anotacao cadastrar = new Anotacao();
				cadastrar.ajustaDados(txtRemetente.getText(), (String) cmbDestinatario.getSelectedItem(), txtDtEnvio.getText(), txtAssunto.getText(), txtAnotacao.getText(), getLogin());
				if(cadastrar.validaDados()>0){
					JOptionPane.showMessageDialog(null, "Encontrada inconsistências nos dados informados:\n"+cadastrar.getMsgStatus(),"Erro",2);
				}else{
					cadastrar.cadastrar();
				}
			}});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
	}
	@SuppressWarnings("unchecked")
	public Vector getUsuarios(){
		Anotacao consultar = new Anotacao();
		Vector usuarios = new Vector();
		usuarios.addElement(new String("--------------------------------"));
		usuarios.addAll(consultar.consultarUsuarios());
		return usuarios;
	}
	@SuppressWarnings("unchecked")
	public Vector getClientes(){
		Venda consultar = new Venda();
		Vector clientes = new Vector();
		clientes.addElement("--------------------------------");
		clientes.addAll(consultar.consultarClientes());
		return clientes;
	}
}
