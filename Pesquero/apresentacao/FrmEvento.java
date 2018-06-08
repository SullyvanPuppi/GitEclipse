package apresentacao;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import negocios.Notificacoes;

public class FrmEvento extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3143311019872183707L;
	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	JDesktopPane desk = null;
	private JTextField txtRemetente = null;
	private JFormattedTextField txtDtEnvio = null;
	private JFormattedTextField txtLembrar = null;
	private JTextField txtAssunto = null;
	private JTextArea txtAnotacao = null;

	//Direitos de usuário determinado pelo grupo
	private int permissao;

	private JLabel lblDivisao = null;
	private JButton btnConfirmar = null;
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
	public void ajustaDados(String nmAssunto){
		Notificacoes consultar = new Notificacoes();
		consultar.consultarEvento(nmAssunto);
		txtRemetente.setText(consultar.getTxtRemetente());
		txtDtEnvio.setText(consultar.getTxtDtEnvio());
		txtLembrar.setText(consultar.getTxtLembrar());
		txtAssunto.setText(consultar.getTxtAssunto());
		txtAnotacao.setText(consultar.getTxtAnotacao());
	}
	private int op;

	public int getOp(){
		return op;
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
	public void desabilitaCampos(){
		if(getOp()==0){
			txtRemetente.setEditable(false);
			txtDtEnvio.setEditable(false);
		}else{
			txtRemetente.setEditable(false);
			txtDtEnvio.setEditable(false);
			txtAssunto.setEditable(false);
			txtAnotacao.setEditable(false);
			btnConfirmar.setEnabled(false);
		}
	}
	/**
	 * This is the xxx default constructor
	 */
	public FrmEvento(int permissao, int op, String nmAssunto, String login) {
		super();
		this.permissao = permissao;
		this.op = op;
		initialize();
		if(getOp()==0){//Novo cadastro
			Notificacoes novo = new Notificacoes();
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
		this.setTitle("Evento");
		this.setModal(true);

		JLabel lblRemetente = new JLabel("Remetente");
		txtRemetente = new JTextField();
		JLabel lblDtEnvio = new JLabel("Data");
		txtDtEnvio = new JFormattedTextField(setMascara("##/##/####"));
		JLabel lblLembrar = new JLabel("Lembrar em");
		txtLembrar = new JFormattedTextField(setMascara("##/##/####"));
		JLabel lblAssunto = new JLabel("Assunto");
		txtAssunto = new JTextField();
		JLabel lblAnotacao = new JLabel("Recado");
		txtAnotacao = new JTextArea();
		JScrollPane jspTxtAnotacao = new JScrollPane(txtAnotacao);
		jspTxtAnotacao.setPreferredSize(new Dimension(400, 200));

		lblDivisao = new JLabel("_____________________________________________________________________________________");
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setToolTipText("Confirmar dados para cadastro");
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Cancelar cadastro e fechar");

		Box linhaUm = Box.createHorizontalBox();
		Box linhaDois = Box.createHorizontalBox();
		Box linhaQuatro = Box.createHorizontalBox();
		Box linhaCinco = Box.createHorizontalBox();
		Box linhaSeis = Box.createHorizontalBox();

		Box linhaDoze = Box.createHorizontalBox();
		Box linhaTreze = Box.createHorizontalBox();

		linhaUm.add(lblRemetente);
		linhaUm.add(txtRemetente);
		linhaDois.add(lblDtEnvio);
		linhaDois.add(txtDtEnvio);
		linhaDois.add(Box.createHorizontalStrut(5));
		linhaDois.add(lblLembrar);
		linhaDois.add(txtLembrar);		
		linhaQuatro.add(lblAnotacao);
		linhaCinco.add(jspTxtAnotacao);
		linhaSeis.add(lblAssunto);
		linhaSeis.add(txtAssunto);

		linhaDoze.add(lblDivisao);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnConfirmar);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnCancelar);

		Box linhas = Box.createVerticalBox();
		linhas.add(linhaUm);
		linhas.add(Box.createVerticalStrut(5));		
		linhas.add(linhaDois);
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

		//---Ações
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Notificacoes cadastrar = new Notificacoes();
				cadastrar.ajustaDados(txtRemetente.getText(), txtDtEnvio.getText(), txtLembrar.getText(), txtAssunto.getText(), txtAnotacao.getText());
				if(cadastrar.validaDados()>0){
					JOptionPane.showMessageDialog(null, "Encontrada inconsistências nos dados informados:\n"+cadastrar.getMsgStatus(),"Erro",2);
				}else{
					if(cadastrar.verificaExiste()==0){
						cadastrar.cadastrar();
					}else{
						JOptionPane.showMessageDialog(null, "Já existe um evento cadastrado com este assunto","Erro",2);				
					}
				}
			}});		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});		
	}
	public DefaultComboBoxModel getUsuarios(){
		DefaultComboBoxModel usuarios = new DefaultComboBoxModel();
		usuarios.addElement(new String("--------------------------------"));
		return usuarios;
	}
}
