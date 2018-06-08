package apresentacao;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import negocios.IeSelecionaLogo;
import negocios.Sistema;

/**
 * @author Sullyvan Puppi
 *
 * Formulário de configuração do sistema
 * 
 * Administrador configurará o sistema.
 * 
 */
public class FrmConfSistema extends JInternalFrame {

	private static final long serialVersionUID = -3698779544635222157L;
	private JPanel jContentPane = null;
	private JTabbedPane AbaPrincipal = null;
	private JPanel abaDadosIe = null;
	private JPanel abaDadosAdm = null;
	
	//----Controles da aba DadosIe
	private JLabel lblNomeIE = null;
	private JTextField txtNomeIE = null;
	private JLabel lblEndereco = null;
	private JTextField txtEndereco = null;
	private JLabel lblNumero = null;
	private JTextField txtNumero = null;
	private JLabel lblComplemento = null;
	private JTextField txtComplemento = null;
	private JLabel lblBairro = null;
	private JLabel lblCidade = null;
	private JLabel lblEstado = null;
	private JTextField txtBairro = null;
	private JTextField txtCidade = null;
	private JComboBox cmbEstado = null;
	private JLabel lblCep = null;
	private JLabel lblTelefone2 = null;
	private JFormattedTextField txtCep = null;
	private JFormattedTextField txtTelefone = null;
	private JLabel lblEmail = null;
	private JTextField txtEmail = null;
	private JLabel lblResponsavel = null;
	private JLabel lblCnpj = null;
	private JLabel lblInscricaoEstadual = null;
	private JTextField txtResponsavel = null;
	private JFormattedTextField txtCnpj = null;
	private JFormattedTextField txtIncricaoEstadual = null;
	private JLabel lblFax = null;
	private JFormattedTextField txtFax = null;

	//Armazenará a lista de estados brasileiros que aparece no comboBox
	private DefaultComboBoxModel estados;

	//---Controles da abaDadosAdm
	private JLabel lblSecretario = null;
	private JLabel lblNomeSecretario = null;
	private JTextField txtNomeSecretario = null;
	private JLabel lblDiretor = null;
	private JTextField txtDiretor = null;
	private JLabel lblNomeDiretor = null;
	private JLabel lblRGSecretario = null;
	private JLabel lblRGDiretor = null;
	private JTextField txtRGSecretario = null;
	private JTextField txtRGDiretor = null;
	private JLabel lblLogoInstituicao = null;
	private JButton btnCarregarLogo = null;
	private JButton btnLimparLogo = null;
	private JLabel lblLogo = null;
	private JLabel lblInstrucao1 = null;
	private JLabel lblInstrucao2 = null;
	private JLabel lblInstrucao3 = null;
	private JLabel lblInstrucao4 = null;
	private JLabel lblInstrucao5 = null;
	
	//---Controles da aba avançado
	private JButton btnEditar = null;
	private JLabel lblDivisao = null;
	private JPanel abaAvancado = null;
	private JLabel lblConexao = null;
	private JTextField txtUrlBanco = null;
	private JLabel lblUrl = null;
	private JLabel lblUsuario = null;
	private JTextField txtUsuarioBd = null;
	private JLabel lblSenha = null;
	private JPasswordField txtSenhaBd = null;
	private JLabel lblDivisao2 = null;
	private JLabel lblConexaoFtp = null;
	private JLabel lblUrlFtp = null;
	private JTextField txtUrlFtp = null;
	private JTextField txtUsuarioFtp = null;
	private JLabel lblUsuarioFtp = null;
	private JLabel lblSenhaFtp = null;
	private JPasswordField txtSenhaFtp = null;
	private JButton btnEditarAvancado = null;
	private JButton btnConfirmarAvancado = null;

	//Controles da abaPrincipal
	private JButton btnCancelar = null;
	private JButton btnConfirmar = null;
	private JButton btnGerarRel = null;

	//Atributo que armazena o critério de direitos no sistema
	private int permissao;
	
	//Atributo que armazenará o caminho do logotipo da instituição de ensino
	private String caminhoLogo = null;
	/**
	 * This is the xxx default constructor
	 */
	public FrmConfSistema(JDesktopPane desktop, int permissao) {
		super();
		this.permissao = permissao;
		initialize();
		validaPermissao(permissao);
	}	
	//---Retorna permissão de direitos do grupo de usuários
	public int getPermissao(){
		return permissao;	
	}
	public void setPermissao(int x){
		this.permissao = x;	
	}
	//----Ajusta permissão para poder editar configurações do sistema
	public void validaPermissao(int x){
		if(x==1){//Somente se pertencer ao grupo de administradores
			getBtnEditar().setEnabled(true);
			getBtnEditarAvancado().setEnabled(true);
			getBtnConfirmar().setEnabled(true);
			getBtnConfirmarAvancado().setEnabled(true);
			getBtnConfirmar().setEnabled(true);
		}else if(x==2){
			getBtnEditar().setEnabled(false);
			getBtnEditarAvancado().setEnabled(false);
			getBtnConfirmar().setEnabled(false);
			getBtnConfirmarAvancado().setEnabled(false);
			getBtnLimparLogo().setEnabled(false);
			getBtnConfirmar().setEnabled(false);
		}else if(x==3){
			getBtnEditar().setEnabled(false);
			getBtnEditarAvancado().setEnabled(false);
			getBtnConfirmar().setEnabled(false);
			getBtnConfirmarAvancado().setEnabled(false);
			getBtnLimparLogo().setEnabled(false);
			getBtnConfirmar().setEnabled(false);
		}else if(x==4){
			getBtnEditar().setEnabled(false);
			getBtnEditarAvancado().setEnabled(false);
			getBtnConfirmar().setEnabled(false);
			getBtnConfirmarAvancado().setEnabled(false);
			getBtnLimparLogo().setEnabled(false);
			getBtnConfirmar().setEnabled(false);
		}
	}
	//---Ajusta caminho do logo da IE
	public void setCaminhoLogo(String caminho){
		this.caminhoLogo = caminho;
	}
	//---Retorna caminho do logo da IE
	public String getCaminhoLogo(){
		return caminhoLogo;
	}
	//---Ajusta logo da IE
	public void ajustaLogo(int op){
		if(op==0){//-- 0 - Para limpar
			setCaminhoLogo("");
			lblLogo.setIcon(new ImageIcon(""));
			abaDadosAdm.add(lblLogo, null);
			lblLogo.setBounds(new Rectangle(42, 140, 198, 163));						
		}else if(op==1){//-- 1 - Para carregar
			IeSelecionaLogo selecionaLogo = new IeSelecionaLogo(getTxtNomeIE().getText());
			setCaminhoLogo(selecionaLogo.getCaminhoLogo());
			lblLogo = new JLabel();
			lblLogo.setIcon(new ImageIcon(getClass().getResource(selecionaLogo.getCaminhoLogo())));
			abaDadosAdm.add(lblLogo, null);
			lblLogo.setBounds(new Rectangle(42, 140, 198, 163));
		}else if(op==2){
			lblLogo = new JLabel();
			lblLogo.setIcon(new ImageIcon(getClass().getResource(getCaminhoLogo())));
			abaDadosAdm.add(lblLogo, null);
			lblLogo.setBounds(new Rectangle(42, 140, 198, 163));
		}	
	}
	//---Classe responsável por formatar márcaras de entrada de campos específicos
	private MaskFormatter setMascara(String mascara){  
		MaskFormatter mask = null;  
		try {  
		mask = new MaskFormatter(mascara);  
		mask.setPlaceholderCharacter('_');  
		}  
		catch (java.text.ParseException exc) { 
			
		}  
		return mask;  
	}
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(693, 421);
		this.setContentPane(getJContentPane());
		this.setTitle("][ Cadastro da instituição de ensino responsável ][");
		validaPermissao(getPermissao());
		desabilitarCampos();
		ajustaDados();
	}

	//Verificar se ja existe instituição de ensino cadastrada
	public void ajustaDados(){
		Sistema ajustar = new Sistema();
		if (ajustar.getExiste()==1){
			ajustar.consultar();
			//--Ajusta os campos
			getTxtNomeIE().setText(ajustar.getNmInstituicao());
			getTxtEndereco().setText(ajustar.getEndereco());
			getTxtNumero().setText(ajustar.getNumero());
			getTxtComplemento().setText(ajustar.getComplemento());
			getTxtBairro().setText(ajustar.getBairro());
			getTxtCidade().setText(ajustar.getCidade());
			getTxtCep().setText(ajustar.getCep());
			getTxtTelefone().setText(ajustar.getTelefone());
			getTxtEmail().setText(ajustar.getEmail()); 
			getTxtFax().setText(ajustar.getFax());
			getTxtResponsavel().setText(ajustar.getNmResponsavel());
			getTxtCnpj().setText(ajustar.getCnpj());
			getTxtIncricaoEstadual().setText(ajustar.getIe());
			getTxtNomeSecretario().setText(ajustar.getNmSecretario());
			getTxtRGSecretario().setText(ajustar.getRgSecretario());
			getTxtDiretor().setText(ajustar.getNmDiretor());
			getTxtRGDiretor().setText(ajustar.getRgDiretor());
			setCaminhoLogo(ajustar.getCaminhoLogo()); 
			getCmbEstado().setSelectedItem(ajustar.getEstado());
			//--Ajusta logo
			ajustaLogo(2);
			//--Desabilita campos para edição
			desabilitarCampos();
			if (getCaminhoLogo().equals("")){
				getBtnCarregarLogo().setEnabled(true);
			}else{
				getBtnCarregarLogo().setEnabled(false);
			}
		}
	}
	
	public void habilitarCampos(){
		getCmbEstado().setEnabled(true);
		getTxtNomeIE().setEditable(true);
		getTxtEndereco().setEditable(true);
		getTxtNumero().setEditable(true);
		getTxtComplemento().setEditable(true);
		getTxtBairro().setEditable(true);
		getTxtCidade().setEditable(true);
		getTxtCep().setEditable(true);
		getTxtTelefone().setEditable(true);
		getTxtEmail().setEditable(true);
		getTxtFax().setEditable(true);
		getTxtResponsavel().setEditable(true);
		getTxtCnpj().setEditable(true);
		getTxtIncricaoEstadual().setEditable(true);
		getTxtNomeSecretario().setEditable(true);
		getTxtRGSecretario().setEditable(true);
		getTxtDiretor().setEditable(true);
		getTxtRGDiretor().setEditable(true);
		getBtnLimparLogo().setEnabled(true);
		getBtnConfirmar().setEnabled(true);		
	}
	public void desabilitarCampos(){
		getCmbEstado().setEnabled(false);
		getTxtNomeIE().setEditable(false);
		getTxtEndereco().setEditable(false);
		getTxtNumero().setEditable(false);
		getTxtComplemento().setEditable(false);
		getTxtBairro().setEditable(false);
		getTxtCidade().setEditable(false);
		getTxtCep().setEditable(false);
		getTxtTelefone().setEditable(false);
		getTxtEmail().setEditable(false);
		getTxtFax().setEditable(false);
		getTxtResponsavel().setEditable(false);
		getTxtCnpj().setEditable(false);
		getTxtIncricaoEstadual().setEditable(false);
		getTxtNomeSecretario().setEditable(false);
		getTxtRGSecretario().setEditable(false);
		getTxtDiretor().setEditable(false);
		getTxtRGDiretor().setEditable(false);
		getBtnCarregarLogo().setEnabled(false);
		getTxtUrlBanco().setEnabled(false);
		getTxtUsuarioBd().setEnabled(false);
		getTxtSenhaBd().setEnabled(false);
		getTxtUrlFtp().setEnabled(false);
		getTxtUsuarioFtp().setEnabled(false);
		getTxtSenhaFtp().setEnabled(false);
		getBtnLimparLogo().setEnabled(false);
	}
	public void habilitarAvancado(){
		getTxtUrlBanco().setEnabled(true);
		getTxtUsuarioBd().setEnabled(true);
		getTxtSenhaBd().setEnabled(true);
		getTxtUrlFtp().setEnabled(true);
		getTxtUsuarioFtp().setEnabled(true);
		getTxtSenhaFtp().setEnabled(true);
	}
	
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			lblDivisao = new JLabel();
			lblDivisao.setBounds(new Rectangle(-98, 349, 830, 16));
			lblDivisao.setHorizontalAlignment(SwingConstants.CENTER);
			lblDivisao.setText("____________________________________________________________________________________________________________________________________________");
			lblDivisao.setForeground(new Color(190, 190, 190));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getAbaPrincipal(), null);
			jContentPane.add(getBtnCancelar(), null);
			jContentPane.add(getBtnConfirmar(), null);
			jContentPane.add(getBtnGerarRel(), null);
			jContentPane.add(getBtnEditar(), null);
			jContentPane.add(lblDivisao, null);
		}
		return jContentPane;
	}
	/**
	 * This method initializes AbaPrincipal	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getAbaPrincipal() {
		if (AbaPrincipal == null) {
			AbaPrincipal = new JTabbedPane();
			AbaPrincipal.setBounds(new Rectangle(3, 3, 685, 356));
			AbaPrincipal.addTab("Dados da instituição de ensino", null, getAbaDadosIe(), null);
			AbaPrincipal.addTab("Dados administrativos", null, getAbaDadosAdm(), null);
			AbaPrincipal.addTab("Avançado", null, getAbaAvancado(), null);			
		}
		return AbaPrincipal;
	}
	/**
	 * This method initializes abaDadosIe	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaDadosIe() {
		if (abaDadosIe == null) {
			lblFax = new JLabel();
			lblFax.setBounds(new Rectangle(326, 133, 23, 22));
			lblFax.setText("Fax");
			lblInscricaoEstadual = new JLabel();
			lblInscricaoEstadual.setBounds(new Rectangle(465, 194, 107, 24));
			lblInscricaoEstadual.setText("Inscrição Estadual");
			lblCnpj = new JLabel();
			lblCnpj.setBounds(new Rectangle(298, 194, 46, 22));
			lblCnpj.setText("C.N.P.J.");
			lblResponsavel = new JLabel();
			lblResponsavel.setBounds(new Rectangle(12, 194, 120, 22));
			lblResponsavel.setText("Responsável pela I.E.");
			lblEmail = new JLabel();
			lblEmail.setBounds(new Rectangle(54, 164, 33, 22));
			lblEmail.setText("Email");
			lblTelefone2 = new JLabel();
			lblTelefone2.setBounds(new Rectangle(148, 134, 51, 22));
			lblTelefone2.setText("Telefone");
			lblCep = new JLabel();
			lblCep.setBounds(new Rectangle(56, 134, 25, 22));
			lblCep.setText("CEP");
			lblEstado = new JLabel();
			lblEstado.setBounds(new Rectangle(591, 104, 41, 22));
			lblEstado.setText("Estado");
			lblCidade = new JLabel();
			lblCidade.setBounds(new Rectangle(231, 104, 41, 22));
			lblCidade.setText("Cidade");
			lblBairro = new JLabel();
			lblBairro.setBounds(new Rectangle(47, 104, 37, 22));
			lblBairro.setText("Bairro");
			lblComplemento = new JLabel();
			lblComplemento.setBounds(new Rectangle(137, 74, 82, 22));
			lblComplemento.setText("Complemento");
			lblNumero = new JLabel();
			lblNumero.setBounds(new Rectangle(37, 74, 47, 22));
			lblNumero.setText("Número");
			lblEndereco = new JLabel();
			lblEndereco.setBounds(new Rectangle(29, 44, 58, 22));
			lblEndereco.setText("Endereço");
			lblNomeIE = new JLabel();
			lblNomeIE.setBounds(new Rectangle(12, 14, 73, 22));
			lblNomeIE.setText("Nome da I.E.");
			abaDadosIe = new JPanel();
			abaDadosIe.setLayout(null);
			abaDadosIe.add(getTxtNomeIE(), null);
			abaDadosIe.add(getTxtEndereco(), null);
			abaDadosIe.add(getTxtNumero(), null);
			abaDadosIe.add(getTxtComplemento(), null);
			abaDadosIe.add(getTxtBairro(), null);
			abaDadosIe.add(getTxtCidade(), null);
			abaDadosIe.add(getCmbEstado(), null);
			abaDadosIe.add(getTxtCep(), null);
			abaDadosIe.add(getTxtTelefone(), null);
			abaDadosIe.add(getTxtEmail(), null);
			abaDadosIe.add(getTxtResponsavel(), null);
			abaDadosIe.add(getTxtCnpj(), null);
			abaDadosIe.add(getTxtIncricaoEstadual(), null);
			abaDadosIe.add(getTxtFax(), null);
			abaDadosIe.add(lblNomeIE, null);
			abaDadosIe.add(lblEndereco, null);
			abaDadosIe.add(lblNumero, null);
			abaDadosIe.add(lblComplemento, null);
			abaDadosIe.add(lblBairro, null);
			abaDadosIe.add(lblCidade, null);
			abaDadosIe.add(lblEstado, null);
			abaDadosIe.add(lblCep, null);
			abaDadosIe.add(lblTelefone2, null);
			abaDadosIe.add(lblEmail, null);
			abaDadosIe.add(lblResponsavel, null);
			abaDadosIe.add(lblCnpj, null);
			abaDadosIe.add(lblInscricaoEstadual, null);
			abaDadosIe.add(lblFax, null);
		}
		return abaDadosIe;
	}
	/**
	 * This method initializes abaDadosAdm
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaDadosAdm() {
		if (abaDadosAdm == null) {
			lblInstrucao5 = new JLabel();
			lblInstrucao5.setBounds(new Rectangle(390, 284, 242, 16));
			lblInstrucao5.setText("selecione a imagem e clique em abrir.");
			lblInstrucao4 = new JLabel();
			lblInstrucao4.setBounds(new Rectangle(390, 265, 271, 16));
			lblInstrucao4.setText("Instituição de Ensino, clique no botão carregar");
			lblInstrucao3 = new JLabel();
			lblInstrucao3.setBounds(new Rectangle(389, 246, 216, 16));
			lblInstrucao3.setText("Para carregar imagem do logotipo da");
			lblInstrucao2 = new JLabel();
			lblInstrucao2.setBounds(new Rectangle(389, 180, 165, 16));
			lblInstrucao2.setText("* Preenchimento obrigatório");
			lblInstrucao1 = new JLabel();
			lblInstrucao1.setBounds(new Rectangle(389, 138, 55, 22));
			lblInstrucao1.setText("Atenção:");
			lblLogoInstituicao = new JLabel();
			lblLogoInstituicao.setBounds(new Rectangle(12, 114, 141, 22));
			lblLogoInstituicao.setText("Logotipo da instituição:*");
			lblRGDiretor = new JLabel();
			lblRGDiretor.setBounds(new Rectangle(365, 84, 16, 22));
			lblRGDiretor.setText("RG");
			lblRGSecretario = new JLabel();
			lblRGSecretario.setBounds(new Rectangle(365, 34, 18, 22));
			lblRGSecretario.setText("RG");
			lblNomeDiretor = new JLabel();
			lblNomeDiretor.setBounds(new Rectangle(12, 84, 34, 22));
			lblNomeDiretor.setText("Nome");
			lblDiretor = new JLabel();
			lblDiretor.setBounds(new Rectangle(12, 64, 69, 22));
			lblDiretor.setText("Diretor (a):*");
			lblNomeSecretario = new JLabel();
			lblNomeSecretario.setBounds(new Rectangle(12, 34, 34, 22));
			lblNomeSecretario.setText("Nome");
			lblSecretario = new JLabel();
			lblSecretario.setBounds(new Rectangle(12, 14, 92, 22));
			lblSecretario.setText("Secretário (a):*");
			abaDadosAdm = new JPanel();
			abaDadosAdm.setLayout(null);
			abaDadosAdm.add(getTxtNomeSecretario(), null);
			abaDadosAdm.add(getTxtDiretor(), null);
			abaDadosAdm.add(getTxtRGSecretario(), null);
			abaDadosAdm.add(getTxtRGDiretor(), null);
			abaDadosAdm.add(getBtnCarregarLogo(), null);
			abaDadosAdm.add(getBtnLimparLogo(), null);
			abaDadosAdm.add(lblSecretario, null);
			abaDadosAdm.add(lblNomeSecretario, null);
			abaDadosAdm.add(lblDiretor, null);
			abaDadosAdm.add(lblNomeDiretor, null);
			abaDadosAdm.add(lblRGSecretario, null);
			abaDadosAdm.add(lblRGDiretor, null);
			abaDadosAdm.add(lblLogoInstituicao, null);
			abaDadosAdm.add(lblInstrucao1, null);
			abaDadosAdm.add(lblInstrucao2, null);
			abaDadosAdm.add(lblInstrucao3, null);
			abaDadosAdm.add(lblInstrucao4, null);
		}
		return abaDadosAdm;
	}
	
	/**
	 * This method initializes txtNomeIE	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNomeIE() {
		if (txtNomeIE == null) {
			txtNomeIE = new JTextField();
			txtNomeIE.setBounds(new Rectangle(75, 14, 594, 22));
			txtNomeIE.setBackground(Color.white);
			txtNomeIE.setToolTipText("Nome da instituição de ensino");
		}
		return txtNomeIE;
	}

	/**
	 * This method initializes txtEndereco	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtEndereco() {
		if (txtEndereco == null) {
			txtEndereco = new JTextField();
			txtEndereco.setBounds(new Rectangle(75, 44, 594, 22));
			txtEndereco.setBackground(Color.white);
			txtEndereco.setToolTipText("Endereço da instituição de ensino");
		}
		return txtEndereco;
	}

	/**
	 * This method initializes txtNumero	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNumero() {
		if (txtNumero == null) {
			txtNumero = new JTextField();
			txtNumero.setBounds(new Rectangle(75, 74, 56, 22));
			txtNumero.setBackground(Color.white);
			txtNumero.setToolTipText("Número no endereço da instituição de ensino");
		}
		return txtNumero;
	}

	/**
	 * This method initializes txtComplemento	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtComplemento() {
		if (txtComplemento == null) {
			txtComplemento = new JTextField();
			txtComplemento.setBounds(new Rectangle(203, 74, 152, 22));
			txtComplemento.setBackground(Color.white);
			txtComplemento.setToolTipText("Complemento no endereço da instituição de ensino");
		}
		return txtComplemento;
	}

	/**
	 * This method initializes txtBairro	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtBairro() {
		if (txtBairro == null) {
			txtBairro = new JTextField();
			txtBairro.setBounds(new Rectangle(75, 104, 152, 22));
			txtBairro.setBackground(Color.white);
			txtBairro.setToolTipText("Bairro da instituição de ensino");
		}
		return txtBairro;
	}

	/**
	 * This method initializes txtCidade	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtCidade() {
		if (txtCidade == null) {
			txtCidade = new JTextField();
			txtCidade.setBounds(new Rectangle(265, 104, 320, 22));
			txtCidade.setBackground(Color.white);
			txtCidade.setToolTipText("Cidade da instituição de ensino");
		}
		return txtCidade;
	}

	/**
	 * This method initializes cmbEstado	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbEstado() {
		if (cmbEstado == null) {
			estados = new DefaultComboBoxModel();
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
			cmbEstado = new JComboBox(estados);
			cmbEstado.setBounds(new Rectangle(626, 105, 41, 20));
			cmbEstado.setToolTipText("Estado da instituição de ensino");
		}
		return cmbEstado;
	}

	/**
	 * This method initializes txtCep	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtCep() {
		if (txtCep == null) {
			txtCep = new JFormattedTextField(setMascara("##.###-###"));
			txtCep.setBounds(new Rectangle(75, 134, 67, 22));
			txtCep.setBackground(Color.white);
			txtCep.setToolTipText("C.E.P. da instituição de ensino");
		}
		return txtCep;
	}

	/**
	 * This method initializes txtTelefone	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtTelefone() {
		if (txtTelefone == null) {
			txtTelefone = new JFormattedTextField(setMascara("(__)____-____"));
			txtTelefone.setBounds(new Rectangle(190, 134, 80, 22));
			txtTelefone.setBackground(Color.white);
			txtTelefone.setToolTipText("Telefone da instituição de ensino");
		}
		return txtTelefone;
	}

	/**
	 * This method initializes txtEmail	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
			txtEmail.setBounds(new Rectangle(78, 164, 320, 22));
			txtEmail.setBackground(Color.white);
			txtEmail.setToolTipText("E-mail da instituição de ensino");
		}
		return txtEmail;
	}

	/**
	 * This method initializes txtResponsavel	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtResponsavel() {
		if (txtResponsavel == null) {
			txtResponsavel = new JTextField();
			txtResponsavel.setBounds(new Rectangle(116, 194, 176, 22));
			txtResponsavel.setBackground(Color.white);
			txtResponsavel.setToolTipText("Responsável pela instituição de ensino");
		}
		return txtResponsavel;
	}

	/**
	 * This method initializes txtCnpj	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtCnpj() {
		if (txtCnpj == null) {
			txtCnpj = new JFormattedTextField(setMascara("##.###.###/####-##"));
			txtCnpj.setBounds(new Rectangle(338, 194, 117, 22));
			txtCnpj.setBackground(Color.white);
			txtCnpj.setToolTipText("CNPJ da instituição de ensino");
		}
		return txtCnpj;
	}

	/**
	 * This method initializes txtIncricaoEstadual	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtIncricaoEstadual() {
		if (txtIncricaoEstadual == null) {
			txtIncricaoEstadual = new JFormattedTextField(setMascara("###.###.###.###"));
			txtIncricaoEstadual.setBounds(new Rectangle(553, 194, 112, 22));
			txtIncricaoEstadual.setBackground(Color.white);
			txtIncricaoEstadual.setToolTipText("Inscrição estadual da instituição de ensino");
		}
		return txtIncricaoEstadual;
	}

	/**
	 * This method initializes btnConfirmar	
	 * @param <validaSistema1>
	 * @param <validaSistema1>
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton();
			btnConfirmar.setBounds(new Rectangle(481, 369, 91, 22));
			btnConfirmar.setText("Continuar");
			btnConfirmar.setToolTipText("Confirmar cadastro da instituição de ensino");
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Sistema validar = new Sistema();
					if (validar.getExiste()==1){
						validar.ajustaDados(getTxtNomeIE().getText(), getTxtEndereco().getText(), getTxtNumero().getText(), getTxtComplemento().getText(), getTxtBairro().getText(), getTxtCidade().getText(), (String) cmbEstado.getSelectedItem(), getTxtCep().getText(), getTxtTelefone().getText(), getTxtEmail().getText(), getTxtFax().getText(), getTxtResponsavel().getText(), getTxtCnpj().getText(), getTxtIncricaoEstadual().getText(), getTxtNomeSecretario().getText(), getTxtRGSecretario().getText(), getTxtDiretor().getText(), getTxtRGDiretor().getText(), getCaminhoLogo());
						if (validar.validar()>=1){
							JOptionPane.showMessageDialog(null,"Foram encontrados "+validar.validar()+" erros.\nO(s) seguintes dados estão incorretos:"+validar.getStatus(),"Dados incorretos",2);
						}else{
							validar.alterar();
							ajustaDados();
						}
					}else{
						validar.ajustaDados(getTxtNomeIE().getText(), getTxtEndereco().getText(), getTxtNumero().getText(), getTxtComplemento().getText(), getTxtBairro().getText(), getTxtCidade().getText(), (String) cmbEstado.getSelectedItem(), getTxtCep().getText(), getTxtTelefone().getText(), getTxtEmail().getText(), getTxtFax().getText(), getTxtResponsavel().getText(), getTxtCnpj().getText(), getTxtIncricaoEstadual().getText(), getTxtNomeSecretario().getText(), getTxtRGSecretario().getText(), getTxtDiretor().getText(), getTxtRGDiretor().getText(), getCaminhoLogo());
						if (validar.validar()>=1){
							JOptionPane.showMessageDialog(null,"Foram encontrados "+validar.validar()+" erros.\nO(s) seguintes dados estão incorretos:"+validar.getStatus(),"Dados incorretos",2);
						}else{
							validar.cadastrar();
							ajustaDados();
						}
					}					
				}});
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
			btnCancelar.setBounds(new Rectangle(581, 369, 91, 22));
			btnCancelar.setText("Cancelar");
			btnCancelar.setToolTipText("Cancelar cadastro e sair");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancelar;
	}

	/**
	 * This method initializes btnGerarRel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnGerarRel() {
		if (btnGerarRel == null) {
			btnGerarRel = new JButton();
			btnGerarRel.setBounds(new Rectangle(281, 369, 91, 22));
			btnGerarRel.setText("Relatório");
			btnGerarRel.setToolTipText("Gerar relatório dos dados da instituição de ensino");
		}
		return btnGerarRel;
	}

	/**
	 * This method initializes txtFax	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtFax() {
		if (txtFax == null) {
			txtFax = new JFormattedTextField();
			txtFax.setBounds(new Rectangle(344, 133, 70, 22));
			txtFax.setBackground(Color.white);
			txtFax.setToolTipText("Fax da instituição de ensino");
		}
		return txtFax;
	}
	/**
	 * This method initializes txtNomeSecretario	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNomeSecretario() {
		if (txtNomeSecretario == null) {
			txtNomeSecretario = new JTextField();
			txtNomeSecretario.setBounds(new Rectangle(39, 34, 320, 22));
			txtNomeSecretario.setBackground(Color.white);
			txtNomeSecretario.setToolTipText("Primeiro secretário da instituição de ensino");
		}
		return txtNomeSecretario;
	}

	/**
	 * This method initializes txtDiretor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtDiretor() {
		if (txtDiretor == null) {
			txtDiretor = new JTextField();
			txtDiretor.setBounds(new Rectangle(39, 84, 320, 22));
			txtDiretor.setBackground(Color.white);
			txtDiretor.setToolTipText("Diretor da instituição de ensino");
		}
		return txtDiretor;
	}

	/**
	 * This method initializes txtRGSecretario	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtRGSecretario() {
		if (txtRGSecretario == null) {
			txtRGSecretario = new JTextField();
			txtRGSecretario.setBounds(new Rectangle(380, 34, 95, 22));
			txtRGSecretario.setBackground(Color.white);
			txtRGSecretario.setToolTipText("R.G. do primeiro secretário da instituição de ensino");
		}
		return txtRGSecretario;
	}

	/**
	 * This method initializes txtRGDiretor	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtRGDiretor() {
		if (txtRGDiretor == null) {
			txtRGDiretor = new JTextField();
			txtRGDiretor.setBounds(new Rectangle(380, 84, 95, 22));
			txtRGDiretor.setBackground(Color.white);
			txtRGDiretor.setToolTipText("R.G. do diretor da instituição de ensino");
		}
		return txtRGDiretor;
	}

	/**
	 * This method initializes btnCarregarLogo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCarregarLogo() {
		if (btnCarregarLogo == null) {
			btnCarregarLogo = new JButton();
			btnCarregarLogo.setBounds(new Rectangle(268, 249, 91, 22));
			btnCarregarLogo.setText("Carregar");
			btnCarregarLogo.setToolTipText("Carregar logo da instituição de ensino");
			btnCarregarLogo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ajustaLogo(1);					
					btnLimparLogo.setEnabled(true);
					getBtnCarregarLogo().setEnabled(false);
				}
			});
		}
		return btnCarregarLogo;
	}
		
	/**
	 * This method initializes btnLimparLogo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnLimparLogo() {
		if (btnLimparLogo == null) {
			btnLimparLogo = new JButton();
			btnLimparLogo.setBounds(new Rectangle(268, 279, 91, 22));
			btnLimparLogo.setText("Limpar");
			btnLimparLogo.setToolTipText("Limpar logo da instituição de ensino");
			btnLimparLogo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ajustaLogo(0);
					getBtnCarregarLogo().setEnabled(true);
					btnLimparLogo.setEnabled(false);
				}
			});
		}
		return btnLimparLogo;
	}
	/**
	 * This method initializes btnEditar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEditar() {
		if (btnEditar == null) {
			btnEditar = new JButton();
			btnEditar.setBounds(new Rectangle(381, 369, 91, 22));
			btnEditar.setText("Editar");
			btnEditar.setToolTipText("Editar cadastro da instituição de ensino");
			btnEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					habilitarCampos();
				}
			});
		}
		return btnEditar;
	}
	/**
	 * This method initializes abaAvancado	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaAvancado() {
		if (abaAvancado == null) {
			lblSenhaFtp = new JLabel();
			lblSenhaFtp.setBounds(new Rectangle(21, 248, 38, 20));
			lblSenhaFtp.setText("Senha");
			lblUsuarioFtp = new JLabel();
			lblUsuarioFtp.setBounds(new Rectangle(17, 218, 54, 20));
			lblUsuarioFtp.setText("Usuário");
			lblUrlFtp = new JLabel();
			lblUrlFtp.setBounds(new Rectangle(14, 168, 172, 20));
			lblUrlFtp.setText("URL de conexão ao servidor FTP");
			lblConexaoFtp = new JLabel();
			lblConexaoFtp.setBounds(new Rectangle(14, 145, 118, 20));
			lblConexaoFtp.setText("Configuração de FTP");
			lblDivisao2 = new JLabel();
			lblDivisao2.setBounds(new Rectangle(-57, 125, 830, 16));
			lblDivisao2.setText("____________________________________________________________________________________________________________________________________________");
			lblDivisao2.setForeground(new Color(190, 190, 190));
			lblSenha = new JLabel();
			lblSenha.setBounds(new Rectangle(21, 113, 38, 20));
			lblSenha.setText("Senha");
			lblUsuario = new JLabel();
			lblUsuario.setBounds(new Rectangle(17, 83, 46, 20));
			lblUsuario.setText("Usuário");
			lblUrl = new JLabel();
			lblUrl.setBounds(new Rectangle(12, 33, 204, 20));
			lblUrl.setText("URL de conexão ao banco de dados");
			lblConexao = new JLabel();
			lblConexao.setBounds(new Rectangle(12, 14, 187, 16));
			lblConexao.setText("Configuração do banco de dados");
			abaAvancado = new JPanel();
			abaAvancado.setLayout(null);
			abaAvancado.add(lblConexao, null);
			abaAvancado.add(getTxtUrlBanco(), null);
			abaAvancado.add(lblUrl, null);
			abaAvancado.add(lblUsuario, null);
			abaAvancado.add(getTxtUsuarioBd(), null);
			abaAvancado.add(lblSenha, null);
			abaAvancado.add(getTxtSenhaBd(), null);
			abaAvancado.add(lblDivisao2, null);
			abaAvancado.add(lblConexaoFtp, null);
			abaAvancado.add(lblUrlFtp, null);
			abaAvancado.add(getTxtUrlFtp(), null);
			abaAvancado.add(getTxtUsuarioFtp(), null);
			abaAvancado.add(lblUsuarioFtp, null);
			abaAvancado.add(lblSenhaFtp, null);
			abaAvancado.add(getTxtSenhaFtp(), null);
			abaAvancado.add(getBtnEditarAvancado(), null);
			abaAvancado.add(getBtnConfirmarAvancado(), null);
		}
		return abaAvancado;
	}
	/**
	 * This method initializes txtUrlBanco	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtUrlBanco() {
		if (txtUrlBanco == null) {
			txtUrlBanco = new JTextField();
			txtUrlBanco.setBounds(new Rectangle(12, 53, 657, 22));
			txtUrlBanco.setBackground(Color.white);
			txtUrlBanco.setToolTipText("URL para conexão com o banco de dados");
		}
		return txtUrlBanco;
	}
	/**
	 * This method initializes txtUsuarioBd	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtUsuarioBd() {
		if (txtUsuarioBd == null) {
			txtUsuarioBd = new JTextField();
			txtUsuarioBd.setBounds(new Rectangle(54, 83, 320, 22));
			txtUsuarioBd.setBackground(Color.white);
			txtUsuarioBd.setToolTipText("Usuário para conexão com o banco de dados");
		}
		return txtUsuarioBd;
	}
	/**
	 * This method initializes txtSenhaBd	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getTxtSenhaBd() {
		if (txtSenhaBd == null) {
			txtSenhaBd = new JPasswordField();
			txtSenhaBd.setBounds(new Rectangle(54, 113, 320, 22));
			txtSenhaBd.setBackground(Color.white);
			txtSenhaBd.setToolTipText("Senha para conexão com o banco de dados");
		}
		return txtSenhaBd;
	}
	/**
	 * This method initializes txtUrlFtp	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtUrlFtp() {
		if (txtUrlFtp == null) {
			txtUrlFtp = new JTextField();
			txtUrlFtp.setBounds(new Rectangle(14, 188, 657, 22));
			txtUrlFtp.setBackground(Color.white);
			txtUrlFtp.setToolTipText("URL para conexão ao servidor FTP");
		}
		return txtUrlFtp;
	}
	/**
	 * This method initializes txtUsuarioFtp	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtUsuarioFtp() {
		if (txtUsuarioFtp == null) {
			txtUsuarioFtp = new JTextField();
			txtUsuarioFtp.setBounds(new Rectangle(54, 218, 320, 22));
			txtUsuarioFtp.setBackground(Color.white);
			txtUsuarioFtp.setToolTipText("Usuário para conexão ao servidor FTP");
		}
		return txtUsuarioFtp;
	}
	/**
	 * This method initializes txtSenhaFtp	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	private JPasswordField getTxtSenhaFtp() {
		if (txtSenhaFtp == null) {
			txtSenhaFtp = new JPasswordField();
			txtSenhaFtp.setBounds(new Rectangle(54, 248, 320, 20));
			txtSenhaFtp.setBackground(Color.white);
			txtSenhaFtp.setToolTipText("Senha para conexão ao servidor FTP");
		}
		return txtSenhaFtp;
	}
	/**
	 * This method initializes btnEditarAvancado	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEditarAvancado() {
		if (btnEditarAvancado == null) {
			btnEditarAvancado = new JButton();
			btnEditarAvancado.setBounds(new Rectangle(481, 299, 91, 22));
			btnEditarAvancado.setText("Editar");
			btnEditarAvancado.setToolTipText("Editar configurações avançadas");
			btnEditarAvancado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					habilitarAvancado();
				}
			});
		}
		return btnEditarAvancado;
	}
	/**
	 * This method initializes btnConfirmarAvancado	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnConfirmarAvancado() {
		if (btnConfirmarAvancado == null) {
			btnConfirmarAvancado = new JButton();
			btnConfirmarAvancado.setBounds(new Rectangle(581, 299, 91, 22));
			btnConfirmarAvancado.setText("Confirmar");
			btnConfirmarAvancado.setToolTipText("Confirmar configuração avançada");
			btnConfirmarAvancado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
		}
		return btnConfirmarAvancado;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"