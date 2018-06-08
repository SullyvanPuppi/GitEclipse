package apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import negocios.Empresa;

public class FrmNossaEmpresa extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8197829893606584966L;
	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	JDesktopPane desk = null;

	//Direitos de usuário determinado pelo grupo
	private int permissao;
	private JLabel lblNome = null;
	private JFormattedTextField txtNome = null;
	private JLabel lblCnpj = null;
	private JFormattedTextField txtCnpj = null;
	private JLabel lblIe = null;
	private JFormattedTextField txtIe = null;
	private JLabel lblEndereco = null;
	private JFormattedTextField txtEndereco = null;
	private JLabel lblEnderecoNumero = null;
	private JFormattedTextField txtEnderecoNumero = null;
	private JLabel lblEnderecoComplemento = null;
	private JFormattedTextField txtEnderecoComplemento = null;
	private JFormattedTextField txtEnderecoBairro = null;
	private JLabel lblEnderecoCidade = null;
	private JFormattedTextField txtEnderecoCidade = null;
	private JLabel lblEnderecoEstado = null;
	private JComboBox cmbEnderecoEstado = null;
	private JLabel lblCep = null;
	private JFormattedTextField txtCep = null;
	private JLabel lblTel1 = null;
	private JFormattedTextField txtTel1 = null;
	private JLabel lblTel2 = null;
	private JFormattedTextField txtTel2 = null;
	private JLabel lblFax = null;
	private JFormattedTextField txtFax = null;
	private JLabel lblEmail = null;
	private JFormattedTextField txtEmail = null;
	private JLabel lblSite = null;
	private JFormattedTextField txtSite = null;
	private JLabel lblResponsavel = null;
	private JFormattedTextField txtResponsavel = null;
	private JLabel lblResponsavelDoc = null;
	private JFormattedTextField txtResponsavelDoc = null;
	private JLabel lblDivisao = null;
	private JButton btnConfirmar = null;
	private JButton btnCancelar = null;
	private JButton btnAlterar = null;
	private JButton btnRelatorio = null;

	public int getPermissao(){
		return this.permissao;
	}
	//---Ajusta permissão no sistema
	public void ajustaPermissao(){
		Empresa cadastrar = new Empresa();
		if(cadastrar.verificaExiste()==1){
			if(getPermissao()==1){//Administrdor		
				btnAlterar.setEnabled(true);
				btnConfirmar.setEnabled(false);
			}else if(getPermissao()==2){//Secretaria
				btnAlterar.setEnabled(false);
				btnConfirmar.setEnabled(false);
			}else if(getPermissao()==3){//Convidado
				btnConfirmar.setEnabled(false);
				btnAlterar.setEnabled(false);
			}
		}else{
			if(getPermissao()==1){//Administrdor		
				btnAlterar.setEnabled(false);
				btnConfirmar.setEnabled(true);
			}else if(getPermissao()==2){//Secretaria
				btnConfirmar.setEnabled(false);
				btnAlterar.setEnabled(false);
			}else if(getPermissao()==3){//Convidado
				btnConfirmar.setEnabled(false);
				btnAlterar.setEnabled(false);
			}
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
	public FrmNossaEmpresa(JDesktopPane desktop, int permissao) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		initialize();
		desabilitarCampos();
		ajustaDados();
		ajustaPermissao();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Empresa responsável");
		lblNome = new JLabel("Nome");
		txtNome = new JFormattedTextField(setMascara("*********************************************"));
		lblCnpj = new JLabel("CNPJ");
		txtCnpj = new JFormattedTextField(setMascara("##.###.###/####-##"));		
		lblIe = new JLabel("Inscrição estadual");
		txtIe = new JFormattedTextField(setMascara("###.###.###.###"));
		lblEndereco = new JLabel("Endereço");
		txtEndereco = new JFormattedTextField(setMascara("*********************************************"));
		lblEnderecoNumero = new JLabel("Número");
		txtEnderecoNumero = new JFormattedTextField(setMascara("*****"));
		lblEnderecoComplemento = new JLabel("Complemento");
		txtEnderecoComplemento = new JFormattedTextField(setMascara("********************"));
		JLabel lblEnderecoBairro = new JLabel("Bairro");
		txtEnderecoBairro = new JFormattedTextField(setMascara("*********************************************"));
		lblEnderecoCidade = new JLabel("Cidade");
		txtEnderecoCidade = new JFormattedTextField(setMascara("*********************************************"));
		lblEnderecoEstado = new JLabel("Estado");
		cmbEnderecoEstado = new JComboBox(getEstados());
		lblCep = new JLabel("C.E.P.");
		txtCep = new JFormattedTextField(setMascara("##.###-###"));
		lblTel1 = new JLabel("Telefone");
		txtTel1 = new JFormattedTextField(setMascara("(##)####-####"));
		lblTel2 = new JLabel("Telefone");
		txtTel2 = new JFormattedTextField(setMascara("(##)####-####"));
		lblFax = new JLabel("Fax");
		txtFax = new JFormattedTextField(setMascara("(##)####-####"));
		lblEmail = new JLabel("E-mail");
		txtEmail = new JFormattedTextField(setMascara("*********************************************"));
		lblSite = new JLabel("Site");
		txtSite = new JFormattedTextField(setMascara("*********************************************"));
		lblResponsavel = new JLabel("Responsavel");
		txtResponsavel = new JFormattedTextField(setMascara("*********************************************"));
		lblResponsavelDoc = new JLabel("Documento");
		txtResponsavelDoc = new JFormattedTextField(setMascara("********************"));
		lblDivisao = new JLabel("___________________________________________________________________________________________________");
		btnRelatorio = new JButton("Relatório");
		btnRelatorio.setToolTipText("Gerar relatório");
		btnAlterar = new JButton("Alterar");
		btnAlterar.setToolTipText("Alterar dados cadastrados");
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
		Box linhaDez = Box.createHorizontalBox();
		Box linhaOnze = Box.createHorizontalBox();
		Box linhaDoze = Box.createHorizontalBox();
		Box linhaTreze = Box.createHorizontalBox();

		linhaUm.add(lblNome);
		linhaUm.add(txtNome);
		linhaDois.add(lblCnpj);
		linhaDois.add(txtCnpj);
		linhaDois.add(Box.createHorizontalStrut(5));
		linhaDois.add(lblIe);
		linhaDois.add(txtIe);
		linhaTres.add(lblEndereco);
		linhaTres.add(txtEndereco);
		linhaQuatro.add(lblEnderecoNumero);
		linhaQuatro.add(txtEnderecoNumero);
		linhaQuatro.add(Box.createHorizontalStrut(5));
		linhaQuatro.add(lblEnderecoComplemento);
		linhaQuatro.add(txtEnderecoComplemento);
		linhaQuatro.add(Box.createHorizontalStrut(5));
		linhaQuatro.add(lblCep);
		linhaQuatro.add(txtCep);
		linhaCinco.add(lblEnderecoBairro);
		linhaCinco.add(txtEnderecoBairro);
		linhaCinco.add(Box.createHorizontalStrut(5));
		linhaCinco.add(lblEnderecoCidade);
		linhaCinco.add(txtEnderecoCidade);
		linhaCinco.add(Box.createHorizontalStrut(5));
		linhaCinco.add(lblEnderecoEstado);
		linhaCinco.add(cmbEnderecoEstado);
		linhaSeis.add(Box.createHorizontalStrut(5));
		linhaSete.add(lblTel1);
		linhaSete.add(txtTel1);
		linhaSete.add(Box.createHorizontalStrut(5));
		linhaSete.add(lblTel2);
		linhaSete.add(txtTel2);
		linhaSete.add(Box.createHorizontalStrut(5));
		linhaSete.add(lblFax);
		linhaSete.add(txtFax);
		linhaOito.add(lblEmail);
		linhaOito.add(txtEmail);
		linhaNove.add(lblSite);
		linhaNove.add(txtSite);
		linhaDez.add(lblResponsavel);
		linhaDez.add(txtResponsavel);
		linhaOnze.add(lblResponsavelDoc);
		linhaOnze.add(txtResponsavelDoc);
		linhaDoze.add(lblDivisao);
		linhaTreze.add(btnRelatorio);
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
		linhas.add(linhaCinco);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaSeis);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaSete);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaOito);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaNove);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaDez);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaOnze);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaDoze);
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaTreze);

		jContentPane = new JPanel();
		jContentPane.add(linhas);
		getContentPane().add(jContentPane);
		pack();

		//--Ações
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitarCampos();
				btnAlterar.setEnabled(false);
				btnConfirmar.setEnabled(true);
			}});
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Empresa cadastrar = new Empresa();
				cadastrar.ajustaDados(txtNome.getText(), txtCnpj.getText(), txtIe.getText(), txtEndereco.getText(), txtEnderecoNumero.getText(),txtEnderecoComplemento.getText(), txtEnderecoBairro.getText(), txtEnderecoCidade.getText(), (String) cmbEnderecoEstado.getSelectedItem(), txtCep.getText(), txtTel1.getText(), txtTel2.getText(), txtFax.getText(), txtEmail.getText(), txtSite.getText(), txtResponsavel.getText(), txtResponsavelDoc.getText());
				if(cadastrar.validar()>0){
					JOptionPane.showMessageDialog(null, "Encontrada inconsistências nos dados informados:\n"+cadastrar.getMsgStatus(),"Erro",2);
				}else{
					if(cadastrar.verificaExiste()==0){
						cadastrar.cadastrar();
						desabilitarCampos();
						btnAlterar.setEnabled(true);
						btnConfirmar.setEnabled(false);	
					}else{
						if (JOptionPane.showConfirmDialog(new JFrame(),
								"Deseja alterar as informações da empresa?", "Confirmação",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
							cadastrar.alterar();
							desabilitarCampos();
							btnAlterar.setEnabled(true);
							btnConfirmar.setEnabled(false);	
						}
					}					
				}
			}});
		btnRelatorio.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Empresa gerar = new Empresa();
				//exibe o resultado
				gerar.gerarRelatorio().show();
			}});
	}
	public void habilitarCampos(){
		txtNome.setEditable(true);
		txtCnpj.setEditable(true);
		txtIe.setEditable(true);
		txtEndereco.setEditable(true);
		txtEnderecoNumero.setEditable(true);
		txtEnderecoComplemento.setEditable(true);
		txtEnderecoBairro.setEditable(true);
		txtEnderecoCidade.setEditable(true);
		cmbEnderecoEstado.setEnabled(true);
		txtCep.setEditable(true);
		txtTel1.setEditable(true);
		txtTel2.setEditable(true);
		txtFax.setEditable(true);
		txtEmail.setEditable(true);
		txtSite.setEditable(true);
		txtResponsavel.setEditable(true);
		txtResponsavelDoc.setEditable(true);
		btnConfirmar.setEnabled(true);
	}
	public void desabilitarCampos(){
		txtNome.setEditable(false);
		txtCnpj.setEditable(false);
		txtIe.setEditable(false);
		txtEndereco.setEditable(false);
		txtEnderecoNumero.setEditable(false);
		txtEnderecoComplemento.setEditable(false);
		txtEnderecoBairro.setEditable(false);
		txtEnderecoCidade.setEditable(false);
		cmbEnderecoEstado.setEnabled(false);
		txtCep.setEditable(false);
		txtTel1.setEditable(false);
		txtTel2.setEditable(false);
		txtFax.setEditable(false);
		txtEmail.setEditable(false);
		txtSite.setEditable(false);
		txtResponsavel.setEditable(false);
		txtResponsavelDoc.setEditable(false);
	}
	public void ajustaDados(){
		Empresa cadastrar = new Empresa();
		if(cadastrar.verificaExiste()==1){
			cadastrar.consultar();
			txtNome.setText(cadastrar.getTxtNome());
			txtCnpj.setText(cadastrar.getTxtCnpj());
			txtIe.setText(cadastrar.getTxtIe());
			txtEndereco.setText(cadastrar.getTxtEndereco());
			txtEnderecoNumero.setText(cadastrar.getTxtEnderecoNumero());
			txtEnderecoComplemento.setText(cadastrar.getTxtEnderecoComplemento());
			txtEnderecoBairro.setText(cadastrar.getTxtEnderecoBairro());
			txtEnderecoCidade.setText(cadastrar.getTxtEnderecoCidade());
			cmbEnderecoEstado.setSelectedItem((String) cadastrar.getCmbEnderecoEstado());
			txtCep.setText(cadastrar.getTxtCep());
			txtTel1.setText(cadastrar.getTxtTel1());
			txtTel2.setText(cadastrar.getTxtTel2());
			txtFax.setText(cadastrar.getTxtFax());
			txtEmail.setText(cadastrar.getTxtEmail());
			txtSite.setText(cadastrar.getTxtSite());
			txtResponsavel.setText(cadastrar.getTxtResponsavel());
			txtResponsavelDoc.setText(cadastrar.getTxtResponsavelDoc());	
		}	
	}
	public DefaultComboBoxModel getEstados(){
		DefaultComboBoxModel estados = new DefaultComboBoxModel();
		//Lista de estados brasileiros
		estados.addElement(new String("--"));
		estados.addElement(new String("AC"));
		estados.addElement(new String("AL"));
		estados.addElement(new String("AP"));
		estados.addElement(new String("AM"));
		estados.addElement(new String("BA"));
		estados.addElement(new String("CE"));
		estados.addElement(new String("ES"));
		estados.addElement(new String("GO"));
		estados.addElement(new String("MA"));
		estados.addElement(new String("MT"));
		estados.addElement(new String("MS"));
		estados.addElement(new String("MG"));
		estados.addElement(new String("PA"));
		estados.addElement(new String("PB"));
		estados.addElement(new String("PR"));
		estados.addElement(new String("PE"));
		estados.addElement(new String("PI"));
		estados.addElement(new String("RJ"));
		estados.addElement(new String("RN"));
		estados.addElement(new String("RS"));
		estados.addElement(new String("RO"));
		estados.addElement(new String("RR"));
		estados.addElement(new String("SC"));
		estados.addElement(new String("SP"));
		estados.addElement(new String("SE"));
		estados.addElement(new String("TO"));
		estados.addElement(new String("DF"));
		return estados;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
