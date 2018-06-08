package apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.text.MaskFormatter;

import negocios.Contato;

public class FrmCadastroContato extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5919547576757929731L;
	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	JDesktopPane desk = null;

	//Direitos de usuário determinado pelo grupo
	private int permissao;

	private String nmEmpresa = "";
	private String cnpj = "";
	private String categoria = "";
	private String nmContato = "";
	private String idVisita = "";

	private JLabel lblContato = null;
	private JFormattedTextField txtContato = null;
	private JLabel lblNmContato = null;
	private JFormattedTextField txtNmContato = null;
	private JLabel lblFuncao = null;
	private JFormattedTextField txtFuncao = null;
	private JLabel lblTelefone = null;
	private JFormattedTextField txtTelefone = null;
	private JLabel lblTelefone2 = null;
	private JFormattedTextField txtTelefone2 = null;
	private JLabel lblEmail = null;
	private JFormattedTextField txtEmail = null;

	private JRadioButton rdbSexoMasculino = null;
	private JRadioButton rdbSexoFeminino = null;
	private ButtonGroup radioSexo = new ButtonGroup();
	private String sexo = "";

	private JLabel lblDivisao = null;
	private JButton btnConfirmar = null;
	private JButton btnCancelar = null;
	private JButton btnAlterar = null;

	public int getPermissao(){
		return this.permissao;
	}
	public String getNmContato(){
		return nmContato;
	}
	public String getNmEmpresa(){
		return nmEmpresa;
	}
	public String getCnpj(){
		return cnpj;
	}
	public String getSexo(){
		return sexo;
	}
	public String getCategoria(){
		return categoria;
	}
	public String getIdVisita(){
		return idVisita;
	}
	private int op;

	public int getOp(){
		return op;
	}
	//---Ajusta permissão no sistema
	public void ajustaPermissao(){
		if(getPermissao()==1){//Administrdor		

		}else if(getPermissao()==2){//Secretaria

		}else if(getPermissao()==3){//Convidado

		}
	}
	public void ajustaDados(){
		Contato consultar = new Contato();
		consultar.consultarContato(getNmEmpresa(), getNmContato());
		txtContato.setText(consultar.getNmEmpresa());
		txtNmContato.setText(consultar.getTxtNmContato());
		txtFuncao.setText(consultar.getTxtFuncao());
		txtTelefone.setText(consultar.getTxtTelefone());
		txtTelefone2.setText(consultar.getTxtTelefone2());
		txtEmail.setText(consultar.getTxtEmail());
		if(consultar.getSexo().equals("Masculino")){
			rdbSexoMasculino.setSelected(true);
		}else if(consultar.getSexo().equals("Feminino")){
			rdbSexoFeminino.setSelected(true);
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

	/**
	 * This is the xxx default constructor
	 */
	public FrmCadastroContato(JDesktopPane desktop, int permissao, int op, String nmContato, String nmEmpresa, String cnpj, String categoria, String idVisita) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		this.nmEmpresa = nmEmpresa;
		this.cnpj = cnpj;
		this.categoria = categoria;
		this.nmContato = nmContato;
		this.idVisita = idVisita;
		this.op = op;
		initialize();
		if(getOp()==0){//Novo cadastro
			if(getCategoria().equals("Outros")){
				txtContato.setEnabled(true);	
			}else{
				txtContato.setText(nmEmpresa);
				txtContato.setEnabled(false);
			}
		}else{//Exibir dados
			ajustaDados();
			txtNmContato.setEnabled(false);
			txtContato.setEnabled(false);
		}		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		String titulo = "";
		if(getOp()==0){
			titulo = "Novo Contato";
		}else{
			titulo = "Contato";
		}
		this.setTitle(titulo);
		this.setModal(true);

		lblContato = new JLabel("Contato da empresa");
		txtContato = new JFormattedTextField(setMascara("*********************************************"));
		lblNmContato = new JLabel("Nome");
		txtNmContato = new JFormattedTextField(setMascara("*********************************************"));
		JLabel lblSexo = new JLabel("Sexo");
		rdbSexoMasculino = new JRadioButton("Masculino");
		rdbSexoFeminino = new JRadioButton("Feminino");
		radioSexo.add(rdbSexoMasculino);
		radioSexo.add(rdbSexoFeminino);
		lblFuncao = new JLabel("Função");
		txtFuncao = new JFormattedTextField(setMascara("*********************************************"));
		lblTelefone = new JLabel("Telefone");
		txtTelefone = new JFormattedTextField(setMascara("(##)####-####"));
		lblTelefone2 = new JLabel("Telefone contato");
		txtTelefone2 = new JFormattedTextField(setMascara("(##)####-####"));
		lblEmail = new JLabel("E-mail");
		txtEmail = new JFormattedTextField(setMascara("*********************************************"));

		lblDivisao = new JLabel("________________________________________________________________________________");
		btnAlterar = new JButton("Alterar");
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setToolTipText("Confirmar dados para cadastro");
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Cancelar cadastro e fechar");

		Box linhaUm = Box.createHorizontalBox();
		Box linhaDois = Box.createHorizontalBox();
		Box linhaTres = Box.createHorizontalBox();
		Box linhaQuatro = Box.createHorizontalBox();
		Box linhaCinco = Box.createHorizontalBox();
		Box linhaSeis = Box.createHorizontalBox();
		Box linhaSete = Box.createHorizontalBox();
		Box linhaOito = Box.createHorizontalBox();
		Box linhaNove = Box.createHorizontalBox();
		Box linhaDoze = Box.createHorizontalBox();
		Box linhaTreze = Box.createHorizontalBox();

		linhaUm.add(lblContato);
		linhaUm.add(txtContato);
		linhaDois.add(lblNmContato);
		linhaDois.add(txtNmContato);
		linhaTres.add(lblFuncao);
		linhaTres.add(txtFuncao);
		linhaTres.add(Box.createHorizontalStrut(5));
		linhaTres.add(Box.createHorizontalStrut(5));
		linhaQuatro.add(lblTelefone);
		linhaQuatro.add(txtTelefone);
		linhaQuatro.add(Box.createHorizontalStrut(5));
		linhaQuatro.add(lblTelefone2);
		linhaQuatro.add(txtTelefone2);
		linhaNove.add(lblEmail);
		linhaNove.add(txtEmail);

		linhaSeis.add(lblSexo);
		linhaSeis.add(rdbSexoMasculino);
		linhaSeis.add(rdbSexoFeminino);

		linhaDoze.add(lblDivisao);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnAlterar);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnConfirmar);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnCancelar);

		Box linhas = Box.createVerticalBox();
		linhas.add(linhaUm);
		linhas.add(Box.createVerticalStrut(5));		
		linhas.add(linhaDois);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaTres);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaQuatro);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaNove);	
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaCinco);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaSeis);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaSete);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaOito);
		linhas.add(Box.createVerticalStrut(5));

		linhas.add(linhaDoze);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaTreze);

		jContentPane = new JPanel();
		jContentPane.add(linhas);
		getContentPane().add(jContentPane);
		pack();

		//--Ações 
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Contato cadastrar = new Contato();
				cadastrar.ajustaDados(txtContato.getText().trim(), txtNmContato.getText(), txtFuncao.getText(), txtTelefone.getText(), txtTelefone2.getText(), txtEmail.getText(), getSexo(), getCnpj());
				if(cadastrar.validaDados()>0){
					JOptionPane.showMessageDialog(null, "Encontrada inconsistências nos dados informados:\n"+cadastrar.getMsgStatus(),"Erro",2);
				}else{
					if(cadastrar.verificaExiste()==0){
						cadastrar.cadastrar(getCategoria(), getCnpj(), getIdVisita());
					}else{
						if (JOptionPane.showConfirmDialog(new JFrame(),
								"Deseja alterar as informações do contato selecionado?", "Confirmação",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
							cadastrar.alterar(getCategoria(), txtContato.getText().trim(), getIdVisita());
						}
					}
					btnAlterar.setEnabled(true);	
				}
			}});
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});

		rdbSexoMasculino.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange()==ItemEvent.SELECTED){
					sexo ="Masculino";
				}
			}
		});
		rdbSexoFeminino.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange()==ItemEvent.SELECTED){
					sexo ="Feminino";
				}
			}
		});	
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
