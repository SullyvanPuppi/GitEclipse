package apresentacao;

//--Imports de classes internas do java
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/* 
 * Importando a classe responsável pela parte
 * lógica do formulário de cadastro de professor
 * */
import negocios.Curso;
import negocios.Professor;
//---Classe para selecionar e copiar foto do professor
import negocios.ProfessorSelecionaFoto;

/**
 * @author Sullyvan Puppi
 *
 * Formulário de cadastro de professor.
 */
public class FrmCadastroProfessor extends JInternalFrame {

	/**--------------Interface com o usuário-------------------------------------------------**/

	private static final long serialVersionUID = 3754833489803989258L;

	private JPanel jContentPane = null;
	private JButton btnGerarRel = null;
	private JButton btnConfirmar = null;
	private JButton btnCancelar = null;
	private JTabbedPane abaPrincipal = null;
	private JPanel abaDados = null;
	private JPanel abaEspecializacao = null;
	private JPanel abaSenha = null;
	private JLabel lblCd = null;
	private JTextField txtCd = null;
	private JLabel lblNome = null;
	private JTextField txtNome = null;
	private JLabel lblSexo = null;
	private JComboBox cmbsexo = null;
	private JLabel lblNacionalidade = null;
	private JTextField txtNacionalidade = null;
	private JLabel lblNatural = null;
	private JTextField txtNatural = null;
	private JLabel lblNascimento = null;
	private JFormattedTextField txtNascimento = null;
	private JLabel lblPai = null;
	private JTextField txtPai = null;
	private JLabel lblMae = null;
	private JTextField txtMae = null;
	private JLabel lblComponente = null;
	private JComboBox cmbComponente = null;
	private JLabel lblDtAd = null;
	private JFormattedTextField txtDtAd = null;
	private JButton btnAdicionar = null;
	private JButton btnRemoverComponente = null;
	private JLabel lblSenha = null;
	private JButton btnGerarSenha = null;
	private JLabel lblEndereco = null;
	private JLabel lblNum = null;
	private JLabel lblBairro = null;
	private JLabel lblCidade = null;
	private JComboBox cmbEstado = null;
	private JLabel lblEstado = null;
	private JLabel lblCep = null;
	private JLabel lblTel = null;
	private JLabel lblEmail = null;
	private JTextField txtEndereco = null;
	private JTextField txtNumero = null;
	private JTextField txtBairro = null;
	private JTextField txtCidade = null;
	private JFormattedTextField txtCep = null;
	private JFormattedTextField txtTel = null;
	private JTextField txtEmail = null;
	private JLabel lblEspComp = null;
	private JLabel lblCpf = null;
	private JLabel lblRg = null;
	private JFormattedTextField txtCpf = null;
	private JTextField txtRg = null;
	private JLabel lblImgFoto = null;
	private JButton btnCarregar = null;
	private JLabel lblFoto = null;
	private JButton btnEditar = null;
	private JButton btnLimpar = null;
	private JLabel lblComplemento = null;
	private JTextField txtComplemento = null;
	private JLabel lblDsComponente = null;
	private JTextArea txtDsComponente = null;
	private JButton btnEditarComponenteIe = null;
	private JLabel lbllogin = null;
	private JLabel lblSenha2 = null;
	private JTextField txtLogin = null;
	private JPasswordField txtSenha = null;	
	//Armazenará a lista de estados brasileiros que aparece no comboBox
	private DefaultComboBoxModel estados;
	//Sexos
	private DefaultComboBoxModel sexos;
	//--Tabela Componentes
	private JScrollPane jspTbComponentes = null;
	private JTable jtbComponentes = null;

	//Formulário de cadastro de componentes curriculares
	FrmCadastroComponente formComponente;	

	//Armazena matricula atual
	private int matricula;
	//Retorna matricula atual
	public int getMatricula(){
		return this.matricula;
	}
	//--Gerar matricula
	public void gerarMatricula(){
		Professor novo = new Professor();
		this.matricula = novo.getMatriculaNova();
		getTxtCd().setText(""+matricula+"");
	}	 	
	//Armazena inteiro que representa permissão no sistema
	private int permissao;
	//Retorna inteiro que representa permissão no sistema
	public int getPermissao(){
		return permissao;	
	}
	//--Armazena opção 0 para novo cadastro e 1 para exibir dados
	private int op = 0;
	//--Retorna opção
	public int getOp(){
		return this.op;
	}		
	//---Ajusta opção
	public void ajustaOp(){
		if(getOp()==0){//Novo cadastro
			getBtnEditar().setEnabled(false);
			getBtnGerarRel().setEnabled(false);
			getBtnLimpar().setEnabled(false);
			getBtnNovoComponente().setEnabled(true);
			txtDtEntrada.setText(consultar.getHoje());
		}else{//Exibir dados
			if(getPermissao()==1){//Administrador				
				getBtnEditar().setEnabled(true);//somente administrador e coordenador de cursos poderão cadastrar e editar professores
				getBtnConfirmar().setEnabled(false);
				getBtnGerarSenha().setEnabled(true);
				getBtnNovoComponente().setEnabled(true);
			}else if(getPermissao()==2){//Coordenador de cursos
				getBtnEditar().setEnabled(true);//somente administrador e coordenador de cursos poderão cadastrar e editar professores
				getBtnConfirmar().setEnabled(false);
				getBtnGerarSenha().setEnabled(true);
				getBtnNovoComponente().setEnabled(true);
			}else if(getPermissao()==3){//Secretaria
				getBtnEditar().setEnabled(false);
				getBtnConfirmar().setEnabled(false);
				getBtnGerarSenha().setEnabled(false);
				getBtnNovoComponente().setEnabled(false);
			}else if(getPermissao()==4){//Convidado
				getBtnEditar().setEnabled(false);
				getBtnConfirmar().setEnabled(false);
				getBtnGerarSenha().setEnabled(false);
				getBtnNovoComponente().setEnabled(false);
			}
		}
	}
	//Caminho da foto
	private String caminhoFoto = "";
	/**
	 * @return the caminhoFoto
	 */
	public String getCaminhoFoto() {
		return caminhoFoto;
	}
	/**
	 * @param caminhoFoto the caminhoFoto to set
	 */
	public void setCaminhoFoto(String caminhoFoto) {
		this.caminhoFoto = caminhoFoto;
	}
	//---Ajusta Foto do professor
	public void ajustaFoto(int opc){
		if(opc==0){//-- 0 - Para limpar
			setCaminhoFoto("");
			abaDados.remove(lblImgFoto);
			lblImgFoto = new JLabel();
			lblImgFoto.setIcon(new ImageIcon(""));
			abaDados.add(lblImgFoto, null);
			lblImgFoto.setBounds(new Rectangle(610, 14, 122, 163));			
		}else if(opc==1){//-- 1 - Para carregar
			try {
				ProfessorSelecionaFoto selecionaFoto = new ProfessorSelecionaFoto(""+getTxtCd().getText()+"");
				setCaminhoFoto(selecionaFoto.getCaminhoFoto());
				lblImgFoto = new JLabel();
				lblImgFoto.setIcon(new ImageIcon(getClass().getResource(selecionaFoto.getCaminhoFoto())));
				abaDados.add(lblImgFoto, null);
				lblImgFoto.setBounds(new Rectangle(610, 14, 122, 163));				
			} catch (Exception e) {

			}	
		}else if(opc==2){//Ajusta foto para exibição
			lblImgFoto = new JLabel();
			lblImgFoto.setIcon(new ImageIcon(getClass().getResource(getCaminhoFoto())));
			abaDados.add(lblImgFoto, null);
			lblImgFoto.setBounds(new Rectangle(610, 14, 122, 163));
		}else if(opc==3){//Confirmar foto para salvar

		}

	}
	//--Tabela curso componentes
	Professor consultar = new Professor();
	//--Atributo para armazenar item selecionado na tabela
	private String ComponenteSelecionado = "";	
	//--Ajustr seleção
	private void setComponenteSelecionado(String selecao){
		this.ComponenteSelecionado = selecao; 
	}
	//--Retornar seleção
	public String getComponenteSelecionado(){
		return this.ComponenteSelecionado;
	}
	//----Atributo para armazenar os itens da tabela componentes(linhasXcolunas)
	DefaultTableModel tabelaComponentes;

	private JLabel lblEstadoN = null;

	private JComboBox cmbEstadoN = null;

	private JButton btnNovoComponente = null;

	private JLabel lblDivisao3 = null;

	private JLabel lblDesde = null;

	private JFormattedTextField txtDtEntrada = null;

	//----Retorna a tabela (linhasXcolunas)
	public DefaultTableModel getTabelaComponentes(){
		return this.tabelaComponentes;
	}
	int editarAtivo = 0;
	public int getEditarAtivo(){
		return editarAtivo;
	}
	public void setEditarAtivo(int x){
		this.editarAtivo = x;
	}
	public void construirTabelaComponentes(){
		jtbComponentes = new JTable(getTabelaComponentes());
		jspTbComponentes = new JScrollPane( jtbComponentes );
		jspTbComponentes.setBounds(12, 124, 365, 230);
		jtbComponentes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setComponenteSelecionado("");
				setComponenteSelecionado((String)(jtbComponentes.getValueAt(jtbComponentes.getSelectedRow(), 0)));
				getTxtDsComponente().setText("");
				consultar.consultarDsComponente(getComponenteSelecionado());
				getTxtDsComponente().setText(consultar.getDsComponente());
				if(getComponenteSelecionado().equals("")){

				}else{
					if(getPermissao()==1 || getPermissao()==2){
						if(getEditarAtivo()==1){
							btnRemoverComponente.setEnabled(true);	
						}else{
							btnRemoverComponente.setEnabled(false);
						}							
					}
				}
			}
		});
		abaEspecializacao.add(jspTbComponentes, null);
	}
	public void consultarComponente(String matricula){
		consultar = new Professor();
		consultar.consultarComponentes(matricula);
		this.tabelaComponentes = consultar.getTabelaComponentes();
		if(tabelaComponentes.getRowCount()<=0){
			Object linhaD[] = new Object[1];
			linhaD[0] = "";
			tabelaComponentes.addRow(linhaD);
			construirTabelaComponentes();			
		}else{
			construirTabelaComponentes();
		}
	}
	//--Ajusta campos para exibição
	public void ajustaProfessor(String matricula){
		consultar.consultar(matricula);
		getTxtCd().setText(matricula);
		getTxtNome().setText(consultar.getNmProfessor());
		getTxtCpf().setText(consultar.getCdCpf());
		getTxtRg().setText(consultar.getCdRg());
		getTxtOrgao().setText(consultar.getRgOrgao());
		cmbsexo.setSelectedItem((String) consultar.getSexo());
		getTxtNascimento().setText(consultar.getDtNascimento());
		getTxtNatural().setText(consultar.getNaturalidade());
		cmbEstadoN.setSelectedItem(consultar.getNaturalidadeEstado());
		getTxtNacionalidade().setText(consultar.getNacionalidade());
		getTxtEndereco().setText(consultar.getEndereco());
		getTxtNumero().setText(consultar.getNumero());
		getTxtComplemento().setText(consultar.getComplemento());
		getTxtBairro().setText(consultar.getBairro());
		getTxtCidade().setText(consultar.getCidade());
		cmbEstado.setSelectedItem((String) consultar.getEstado());
		getTxtCep().setText(consultar.getCep());
		getTxtTel().setText(consultar.getTelefone());
		getTxtEmail().setText(consultar.getEmail());
		getTxtPai().setText(consultar.getNmPai());
		getTxtMae().setText(consultar.getNmMae());
		setCaminhoFoto(consultar.getDsCaminhoFoto());
		txtDtEntrada.setText(consultar.getDtAdmissaoIe());
		ajustaFoto(2);
		getTxtLogin().setText(consultar.getNmLogin());
		getTxtSenha().setText(consultar.getCdSenha());
		desabilitarCampos();
	}
	//----Desabilitar campos
	public void desabilitarCampos(){
		getTxtNome().setEditable(false);
		getTxtCpf().setEditable(false);
		getTxtRg().setEditable(false);
		getTxtOrgao().setEditable(false);
		cmbsexo.setEnabled(false);
		getTxtNascimento().setEditable(false);
		getTxtNatural().setEditable(false);
		cmbEstadoN.setEnabled(false);
		getTxtNacionalidade().setEditable(false);
		getTxtEndereco().setEditable(false); 
		getTxtNumero().setEditable(false);
		getTxtComplemento().setEditable(false);
		getTxtBairro().setEditable(false);
		getTxtCidade().setEditable(false);
		cmbEstado.setEnabled(false);
		getTxtCep().setEditable(false);
		getTxtTel().setEditable(false);
		getTxtEmail().setEditable(false);
		getTxtPai().setEditable(false);
		getTxtMae().setEditable(false);
		getTxtSenha().setEditable(false);
		getTxtLogin().setEditable(false);
		getTxtDtAd().setEditable(false);
		getBtnLimpar().setEnabled(false);
		getBtnCarregar().setEnabled(false);
		getBtnEditarComponenteIe().setEnabled(false);
	}
	//----Habilitar campos
	public void habilitarCampos(){
		setEditarAtivo(1);
		getTxtNome().setEditable(true);
		getTxtCpf().setEditable(true);
		getTxtRg().setEditable(true);
		getTxtOrgao().setEditable(true);
		cmbsexo.setEnabled(true);
		getTxtNascimento().setEditable(true);
		getTxtNatural().setEditable(true);
		cmbEstadoN.setEnabled(true);
		getTxtNacionalidade().setEditable(true);
		getTxtEndereco().setEditable(true); 
		getTxtNumero().setEditable(true);
		getTxtComplemento().setEditable(true);
		getTxtBairro().setEditable(true);
		getTxtCidade().setEditable(true);
		cmbEstado.setEnabled(true);
		getTxtCep().setEditable(true);
		getTxtTel().setEditable(true);
		getTxtEmail().setEditable(true);
		getTxtPai().setEditable(true);
		getTxtMae().setEditable(true);
		getTxtDtAd().setEditable(true);
		cmbComponente.setEnabled(true);
		if(getCaminhoFoto().equals("")){
			getBtnLimpar().setEnabled(false);
			getBtnCarregar().setEnabled(true);
		}else{
			getBtnLimpar().setEnabled(true);
			getBtnCarregar().setEnabled(false);
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

	//Área de trabalho do formulário de cadastro de professor
	JDesktopPane desk = new JDesktopPane();

	private JLabel lblOrgao = null;

	private JTextField txtOrgao = null;

	/**
	 * This is the xxx default constructor
	 */
	public FrmCadastroProfessor(JDesktopPane desktop, int permissao, int op, String matricula, String nmProfessor) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		initialize(op, matricula, nmProfessor);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize(int op, String matricula, String nmProfessor) {
		this.setSize(744, 493);
		this.setContentPane(getJContentPane());
		String titulo = "";
		this.op = op;
		if(op==0){//Novo cadastro
			titulo = "][ Novo cadastro ][";
			gerarMatricula();
		}else if(op==1){//Exibir dados
			titulo="][ Cadastro do professor ][ Professor: "+nmProfessor;			
			ajustaProfessor(matricula);
			abaPrincipal.addTab("Especialização", null, getAbaEspecializacao(), null);
			abaPrincipal.addTab("Senha", null, getAbaSenha(), null);
			cmbComponente.setEnabled(false);
		}
		this.setTitle(titulo);
		ajustaOp();
		getTxtCd().setEnabled(false);
	}


	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			JLabel lblDivisao = new JLabel();
			lblDivisao.setBounds(new Rectangle(-44, 415, 830, 16));
			lblDivisao.setHorizontalAlignment(SwingConstants.CENTER);
			lblDivisao.setText("____________________________________________________________________________________________________________________________________________");
			lblDivisao.setForeground(new Color(190, 190, 190));
			jContentPane.add(lblDivisao, null);
			jContentPane.add(getAbaPrincipal(), null);
			jContentPane.add(getBtnGerarRel(), null);
			jContentPane.add(getBtnConfirmar(), null);
			jContentPane.add(getBtnCancelar(), null);
			jContentPane.add(getBtnEditar(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes btnGerarRel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnGerarRel() {
		if (btnGerarRel == null) {
			btnGerarRel = new JButton();
			btnGerarRel.setBounds(new Rectangle(330, 435, 91, 21));
			btnGerarRel.setText("Relatório");
			btnGerarRel.setToolTipText("Gerar relatório do professor");
			btnGerarRel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}});
		}
		return btnGerarRel;
	}

	/**
	 * This method initializes btnConfirmar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton();
			btnConfirmar.setBounds(new Rectangle(530, 435, 91, 21));
			btnConfirmar.setText("Confirmar");
			btnConfirmar.setToolTipText("Confirmar cadastro do professor");
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Professor cadastrar = new Professor();
					cadastrar.ajustaProfessor(getTxtCd().getText(),getTxtNome().getText(), getTxtCpf().getText(), 
							getTxtRg().getText(), getTxtOrgao().getText(), (String) cmbsexo.getSelectedItem(), getTxtNascimento().getText(), 
							getTxtNatural().getText(), (String) cmbEstadoN.getSelectedItem(), getTxtNacionalidade().getText(), getTxtEndereco().getText(), 
							getTxtNumero().getText(), getTxtComplemento().getText(), getTxtBairro().getText(), getTxtCidade().getText(), 
							(String) cmbEstado.getSelectedItem(), getTxtCep().getText(), getTxtTel().getText(), getTxtEmail().getText(), 
							getTxtPai().getText(), getTxtMae().getText(), getCaminhoFoto(), getTxtDtEntrada().getText());
					if(cadastrar.validarProfessor()>=1){//Se retornar 1 ou mais encontrada inconsistências
						JOptionPane.showMessageDialog(null,"Encontrada(s) inconsistência(s) "+cadastrar.validarProfessor()+" erro(s).\nO(s) seguintes dados estão incorretos:"+cadastrar.getStatus(),"Dados incorretos",2);
					}else{//Se não cadastrar
						if(cadastrar.getExiste(getTxtCd().getText())==1){//verificar se ja existe professor
							if (JOptionPane.showConfirmDialog(new JFrame(),
									"Salvar alterações?", "Confirmação",
									JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
								if(getCaminhoFoto().equals("") || getCaminhoFoto().equals(null)){
									if (JOptionPane.showConfirmDialog(new JFrame(),
											"Confirmar cadastro do professor sem foto?\n\nObs.: Professor exibirá imagem padrão no SENA IV.", "Confirmação",
											JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
										cadastrar.alterarProfessor();
									}
								}else{
									cadastrar.alterarProfessor();
								}							
							}
						}else{//cadastrar
							if(getCaminhoFoto().equals("") || getCaminhoFoto().equals(null)){
								if (JOptionPane.showConfirmDialog(new JFrame(),
										"Confirmar cadastro do professor sem foto?\n\nObs.: Professor exibirá imagem padrão no SENA IV.", "Confirmação",
										JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
									cadastrar.cadastrarProfessor();	
									abaPrincipal.addTab("Especialização", null, getAbaEspecializacao(), null);
									abaPrincipal.addTab("Senha", null, getAbaSenha(), null);
									getTxtLogin().setText(getTxtCd().getText());
									getTxtSenha().setText(getTxtCd().getText());
								}
							}else{
								cadastrar.cadastrarProfessor();	
								abaPrincipal.addTab("Especialização", null, getAbaEspecializacao(), null);
								abaPrincipal.addTab("Senha", null, getAbaSenha(), null);
								getTxtLogin().setText(getTxtCd().getText());
								getTxtSenha().setText(getTxtCd().getText());
							}
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
			btnCancelar.setBounds(new Rectangle(630, 435, 91, 21));
			btnCancelar.setText("Cancelar");
			btnCancelar.setToolTipText("Cancelar cadastro e sair");
			btnCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();						
				}});
		}
		return btnCancelar;
	}

	/**
	 * This method initializes abaPrincipal	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getAbaPrincipal() {
		if (abaPrincipal == null) {
			abaPrincipal = new JTabbedPane();
			abaPrincipal.setBounds(new Rectangle(2, 2, 737, 422));
			abaPrincipal.addTab("Dados pessoais", null, getAbaDados(), null);
		}
		return abaPrincipal;
	}

	/**
	 * This method initializes abaDados	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaDados() {
		if (abaDados == null) {
			lblOrgao = new JLabel();
			lblOrgao.setBounds(new Rectangle(318, 44, 59, 20));
			lblOrgao.setText("Expedidor");
			lblDesde = new JLabel();
			lblDesde.setBounds(new Rectangle(565, 370, 92, 20));
			lblDesde.setText("Data de entrada");
			lblEstadoN = new JLabel();
			lblEstadoN.setBounds(new Rectangle(232, 104, 41, 20));
			lblEstadoN.setText("Estado");
			lblComplemento = new JLabel();
			lblComplemento.setBounds(new Rectangle(128, 164, 81, 20));
			lblComplemento.setText("Complemento");
			lblFoto = new JLabel();
			lblFoto.setBounds(new Rectangle(570, 14, 25, 20));
			lblFoto.setText("Foto");
			lblRg = new JLabel();
			lblRg.setBounds(new Rectangle(168, 44, 25, 20));
			lblRg.setText("R.G.");
			lblCpf = new JLabel();
			lblCpf.setBounds(new Rectangle(12, 44, 32, 20));
			lblCpf.setText("C.P.F");
			lblEmail = new JLabel();
			lblEmail.setBounds(new Rectangle(242, 224, 37, 20));
			lblEmail.setText("E-mail");
			lblTel = new JLabel();
			lblTel.setBounds(new Rectangle(105, 224, 51, 20));
			lblTel.setText("Telefone");
			lblCep = new JLabel();
			lblCep.setBounds(new Rectangle(12, 224, 23, 20));
			lblCep.setText("CEP");
			lblEstado = new JLabel();
			lblEstado.setBounds(new Rectangle(519, 194, 41, 20));
			lblEstado.setText("Estado");
			lblCidade = new JLabel();
			lblCidade.setBounds(new Rectangle(264, 194, 40, 20));
			lblCidade.setText("Cidade");
			lblBairro = new JLabel();
			lblBairro.setBounds(new Rectangle(12, 194, 38, 20));
			lblBairro.setText("Bairro");
			lblNum = new JLabel();
			lblNum.setBounds(new Rectangle(12, 164, 48, 20));
			lblNum.setText("Número");
			lblEndereco = new JLabel();
			lblEndereco.setBounds(new Rectangle(12, 134, 57, 20));
			lblEndereco.setText("Endereço");
			lblMae = new JLabel();
			lblMae.setBounds(new Rectangle(354, 254, 28, 20));
			lblMae.setText("Mãe");
			lblPai = new JLabel();
			lblPai.setBounds(new Rectangle(12, 254, 20, 20));
			lblPai.setText("Pai");
			lblNascimento = new JLabel();
			lblNascimento.setBounds(new Rectangle(12, 74, 114, 20));
			lblNascimento.setText("Data de nascimento");
			lblNatural = new JLabel();
			lblNatural.setBounds(new Rectangle(12, 104, 74, 20));
			lblNatural.setText("Naturalidade");
			lblNacionalidade = new JLabel();
			lblNacionalidade.setBounds(new Rectangle(312, 104, 84, 20));
			lblNacionalidade.setText("Nacionalidade");
			lblSexo = new JLabel();
			lblSexo.setBounds(new Rectangle(468, 44, 31, 20));
			lblSexo.setText("Sexo");
			lblNome = new JLabel();
			lblNome.setBounds(new Rectangle(127, 14, 38, 20));
			lblNome.setText("Nome");
			lblCd = new JLabel();
			lblCd.setBounds(new Rectangle(12, 14, 58, 20));
			lblCd.setText("Matrícula");			
			abaDados = new JPanel();
			abaDados.setLayout(null);
			abaDados.add(lblCd, null);
			abaDados.add(getTxtCd(), null);
			abaDados.add(lblNome, null);
			abaDados.add(getTxtNome(), null);
			abaDados.add(lblSexo, null);
			abaDados.add(getCmbsexo(), null);
			abaDados.add(lblNacionalidade, null);
			abaDados.add(getTxtNacionalidade(), null);
			abaDados.add(lblNatural, null);
			abaDados.add(lblNascimento, null);
			abaDados.add(getTxtNascimento(), null);
			abaDados.add(getTxtNatural(), null);
			abaDados.add(getTxtNascimento(), null);
			abaDados.add(lblPai, null);
			abaDados.add(getTxtPai(), null);
			abaDados.add(lblMae, null);
			abaDados.add(getTxtMae(), null);
			abaDados.add(lblEndereco, null);
			abaDados.add(lblNum, null);
			abaDados.add(lblBairro, null);
			abaDados.add(lblCidade, null);
			abaDados.add(getCmbEstado(), null);
			abaDados.add(lblEstado, null);
			abaDados.add(lblCep, null);
			abaDados.add(lblTel, null);
			abaDados.add(lblEmail, null);
			abaDados.add(getTxtEndereco(), null);
			abaDados.add(getTxtNumero(), null);
			abaDados.add(getTxtBairro(), null);
			abaDados.add(getTxtCidade(), null);
			abaDados.add(getTxtCep(), null);
			abaDados.add(getTxtTel(), null);
			abaDados.add(getTxtEmail(), null);
			abaDados.add(lblCpf, null);
			abaDados.add(lblRg, null);
			abaDados.add(getTxtCpf(), null);
			abaDados.add(getTxtRg(), null);
			abaDados.add(getBtnCarregar(), null);
			abaDados.add(lblFoto, null);
			abaDados.add(getBtnLimpar(), null);
			abaDados.add(lblComplemento, null);
			abaDados.add(getTxtComplemento(), null);
			abaDados.add(lblEstadoN, null);
			abaDados.add(getCmbEstadoN(), null);
			abaDados.add(lblDesde, null);
			abaDados.add(getTxtDtEntrada(), null);
			abaDados.add(lblOrgao, null);
			abaDados.add(getTxtOrgao(), null);
		}
		return abaDados;
	}

	/**
	 * This method initializes abaEspecializacao	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaEspecializacao() {
		if (abaEspecializacao == null) {
			lblDivisao3 = new JLabel();
			lblDivisao3.setBounds(new Rectangle(-44, 91, 830, 16));
			lblDivisao3.setHorizontalAlignment(SwingConstants.CENTER);
			lblDivisao3.setText("____________________________________________________________________________________________________________________________________________");
			lblDivisao3.setForeground(new Color(190, 190, 190));
			lblDsComponente = new JLabel();
			lblDsComponente.setBounds(new Rectangle(412, 104, 210, 20));
			lblDsComponente.setText("Descrição do componente curricular");
			lblEspComp = new JLabel();
			lblEspComp.setBounds(new Rectangle(12, 104, 266, 20));
			lblEspComp.setText("Componente(s) curricular(es) especializado(s)");
			lblDtAd = new JLabel();
			lblDtAd.setBounds(new Rectangle(12, 44, 104, 20));
			lblDtAd.setText("Data de admissão");
			lblComponente = new JLabel();
			lblComponente.setBounds(new Rectangle(12, 14, 184, 20));
			lblComponente.setText("Nome do componente curricular");
			abaEspecializacao = new JPanel();
			abaEspecializacao.setLayout(null);
			abaEspecializacao.add(lblComponente, null);
			abaEspecializacao.add(lblDtAd, null);
			abaEspecializacao.add(getTxtDtAd(), null);
			abaEspecializacao.add(getBtnAdicionar(), null);
			abaEspecializacao.add(getBtnRemoverComponente(), null);
			abaEspecializacao.add(lblEspComp, null);
			abaEspecializacao.add(lblDsComponente, null);
			abaEspecializacao.add(getTxtDsComponente(), null);
			abaEspecializacao.add(getBtnNovoComponente(), null);
			abaEspecializacao.add(lblDivisao3, null);
			abaEspecializacao.add(getBtnEditarComponenteIe(), null);
			getCmbComponente();
			if(getOp()==1){
				getBtnAdicionar().setEnabled(false);
				getBtnRemoverComponente().setEnabled(false);
			}else{
				getBtnAdicionar().setEnabled(true);
				getBtnRemoverComponente().setEnabled(false);
			}
			consultarComponente(""+getTxtCd().getText()+"");
		}
		return abaEspecializacao;
	}

	/**
	 * This method initializes abaSenha	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAbaSenha() {
		if (abaSenha == null) {
			lblSenha2 = new JLabel();
			lblSenha2.setBounds(new Rectangle(12, 74, 38, 20));
			lblSenha2.setText("Senha");
			lbllogin = new JLabel();
			lbllogin.setBounds(new Rectangle(12, 44, 34, 20));
			lbllogin.setText("Login");
			lblSenha = new JLabel();
			lblSenha.setBounds(new Rectangle(12, 14, 329, 20));
			lblSenha.setText("Gerar senha provisória para acesso ao SENA II e SENA IV");
			abaSenha = new JPanel();
			abaSenha.setLayout(null);
			abaSenha.add(lblSenha, null);
			abaSenha.add(getBtnGerarSenha(), null);
			abaSenha.add(lbllogin, null);
			abaSenha.add(lblSenha2, null);
			abaSenha.add(getTxtLogin(), null);
			abaSenha.add(getTxtSenha(), null);
		}
		return abaSenha;
	}

	/**
	 * This method initializes txtCd	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtCd() {
		if (txtCd == null) {
			txtCd = new JTextField();
			txtCd.setBounds(new Rectangle(55, 14, 61, 22));
			txtCd.setBackground(Color.white);
			txtCd.setToolTipText("Matrícula do professor");
		}
		return txtCd;
	}

	/**
	 * This method initializes txtNome	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNome() {
		if (txtNome == null) {
			txtNome = new JTextField();
			txtNome.setBounds(new Rectangle(155, 14, 320, 22));
			txtNome.setBackground(Color.white);
			txtNome.setToolTipText("Nome completo do professor");
		}
		return txtNome;
	}
	/**
	 * This method initializes cmbsexo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbsexo() {
		if (cmbsexo == null) {
			sexos = new DefaultComboBoxModel();
			sexos.addElement("----------");
			sexos.addElement(new String("Feminino"));
			sexos.addElement(new String("Masculino"));
			cmbsexo = new JComboBox(sexos);
			cmbsexo.setBounds(new Rectangle(493, 44, 101, 22));
			cmbsexo.setToolTipText("Sexo do professor");
		}
		return cmbsexo;
	}

	/**
	 * This method initializes txtNacionalidade	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtNacionalidade() {
		if (txtNacionalidade == null) {
			txtNacionalidade = new JTextField();
			txtNacionalidade.setBounds(new Rectangle(379, 104, 217, 22));
			txtNacionalidade.setBackground(Color.white);
			txtNacionalidade.setToolTipText("Nacionalidade do professor");
		}
		return txtNacionalidade;
	}

	/**
	 * This method initializes txtNatural	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JTextField getTxtNatural() {
		if (txtNatural == null) {
			txtNatural = new JTextField();
			txtNatural.setBounds(new Rectangle(74, 104, 152, 22));
			txtNatural.setBackground(Color.white);
			txtNatural.setToolTipText("Naturalidade do professor");
		}
		return txtNatural;
	}

	/**
	 * This method initializes txtPai	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtPai() {
		if (txtPai == null) {
			txtPai = new JTextField();
			txtPai.setBounds(new Rectangle(26, 254, 320, 22));
			txtPai.setBackground(Color.white);
			txtPai.setToolTipText("Nome do pai do professor");
		}
		return txtPai;
	}

	/**
	 * This method initializes txtMae	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtMae() {
		if (txtMae == null) {
			txtMae = new JTextField();
			txtMae.setBounds(new Rectangle(374, 254, 320, 22));
			txtMae.setBackground(Color.white);
			txtMae.setToolTipText("Nome da mãe do professor");
		}
		return txtMae;
	}
	/**
	 * This method initializes cmbComponente	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	@SuppressWarnings("unchecked")
	private void getCmbComponente() {
		Vector cpt = new Vector();
		cpt.addElement("----------");
		Curso consultar = new Curso();
		cpt.addAll(consultar.consultarComponentes());
		cmbComponente = new JComboBox(cpt);
		cmbComponente.setBounds(new Rectangle(164, 14, 420, 22));
		cmbComponente.setToolTipText("Componente curricular que o professor ministra");
		cmbComponente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTxtDsComponente().setText("");
				Professor consultar = new Professor();
				consultar.consultarDsComponente((String) cmbComponente.getSelectedItem());
				getTxtDsComponente().setText(consultar.getDsComponente());
				if((boolean) cmbComponente.getSelectedItem().equals("----------")){
					btnEditarComponenteIe.setEnabled(false);
				}else{
					btnEditarComponenteIe.setEnabled(true);
				}
			}
		});
		abaEspecializacao.add(cmbComponente, null);
	}
	/**
	 * This method initializes btnEditarComponenteIe	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEditarComponenteIe() {
		if (btnEditarComponenteIe == null) {
			btnEditarComponenteIe = new JButton();
			btnEditarComponenteIe.setBounds(new Rectangle(530, 71, 91, 22));
			btnEditarComponenteIe.setText("Editar");
			btnEditarComponenteIe.setToolTipText("Editar descrição do componente curricular no cadastro geral");
			btnEditarComponenteIe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnNovoComponente.setEnabled(true);
					if ((formComponente == null) || (formComponente.isClosed())) {
						formComponente = new FrmCadastroComponente(desk, getPermissao(), 1, (String) cmbComponente.getSelectedItem());
						desk.add(formComponente, new Integer(2));
						formComponente.show();
					}
				}});
		}
		return btnEditarComponenteIe;
	}
	/**
	 * This method initializes txtNascimento	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtNascimento() {
		if (txtNascimento == null) {
			txtNascimento = new JFormattedTextField(setMascara("##/##/####"));
			txtNascimento.setBounds(new Rectangle(107, 74, 86, 22));
			txtNascimento.setBackground(Color.white);
			txtNascimento.setToolTipText("Data de nascimento do professor");
		}
		return txtNascimento;
	}

	/**
	 * This method initializes btnAdicionar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAdicionar() {
		if (btnAdicionar == null) {
			btnAdicionar = new JButton();
			btnAdicionar.setBounds(new Rectangle(12, 74, 91, 22));
			btnAdicionar.setText("Adicionar");
			btnAdicionar.setToolTipText("Adicionar componente curricular ao professor");
			btnAdicionar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Professor cadastrar = new Professor();
					cadastrar.ajustaComponente(getTxtDtEntrada().getText(),getTxtCd().getText(), getTxtNome().getText(), (String) cmbComponente.getSelectedItem(), getTxtDtAd().getText());
					if(cadastrar.validarComponente()>=1){//Se maior que 1 encontro erro
						JOptionPane.showMessageDialog(null,"Encontrada(s) inconsistência(s) "+cadastrar.validarComponente()+" erro(s).\nO(s) seguintes dados estão incorretos:"+cadastrar.getStatus(),"Dados incorretos",2);
					}else{//Se não continuar cadastro
						cadastrar.cadastrarComponente();
					}
					cmbComponente.setSelectedItem("----------");
					txtDtAd.setText("");
					abaEspecializacao.remove(jspTbComponentes);
					consultarComponente(getTxtCd().getText());
				}
			});
		}
		return btnAdicionar;
	}

	/**
	 * This method initializes btnRemoverComponente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnRemoverComponente() {
		if (btnRemoverComponente == null) {
			btnRemoverComponente = new JButton();
			btnRemoverComponente.setBounds(new Rectangle(12, 370, 91, 22));
			btnRemoverComponente.setText("Remover");
			btnRemoverComponente.setToolTipText("Remover componente curricular");
			btnRemoverComponente.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(getComponenteSelecionado().equals("")){
						JOptionPane.showMessageDialog(null, "Selecione um componente curricular para ser exluído seus dados","Seleção",1);
					}else{
						if (JOptionPane.showConfirmDialog(new JFrame(),
								"Deseja realmente excluir este componente curricular?\n\n"+getComponenteSelecionado()+"\n\n", "Confirmação",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
							Professor remover = new Professor();
							remover.removerComponente(getTxtCd().getText(), getTxtNome().getText(), getComponenteSelecionado());
							abaEspecializacao.remove(jspTbComponentes);
							consultarComponente(getTxtCd().getText());
							cmbComponente.setSelectedItem("----------");
							txtDtAd.setText("");
						}
					}
				}
			});
		}
		return btnRemoverComponente;
	}

	/**
	 * This method initializes btnGerarSenha	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnGerarSenha() {
		if (btnGerarSenha == null) {
			btnGerarSenha = new JButton();
			btnGerarSenha.setBounds(new Rectangle(12, 104, 111, 22));
			btnGerarSenha.setText("Gerar");
			btnGerarSenha.setToolTipText("Nova senha");
			btnGerarSenha.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Confirmar opção de alterar senha
					if (JOptionPane.showConfirmDialog(new JFrame(),
							"Deseja alterar a senha deste professor?\nObs.: Está ação cadastrará seu código de matrícula como login e como senha.", "Confirmação",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						getTxtSenha().setText(getTxtCd().getText());
						getTxtLogin().setText(getTxtCd().getText());
						Professor senha = new Professor();
						senha.novaSenha(getTxtCd().getText(), getTxtNome().getText());						
					}					
				}
			});
		}
		return btnGerarSenha;
	}

	/**
	 * This method initializes cmbEstado	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbEstado() {
		if (cmbEstado == null) {
			estados = new DefaultComboBoxModel();
			estados.addElement("--");
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
			cmbEstado.setBounds(new Rectangle(553, 194, 41, 20));
			cmbEstado.setToolTipText("Estado de residência do professor");
		}
		return cmbEstado;
	}

	/**
	 * This method initializes txtEndereco	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtEndereco() {
		if (txtEndereco == null) {
			txtEndereco = new JTextField();
			txtEndereco.setBounds(new Rectangle(57, 134, 539, 22));
			txtEndereco.setBackground(Color.white);
			txtEndereco.setToolTipText("Endereço de residência do professor");
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
			txtNumero.setBounds(new Rectangle(49, 164, 67, 22));
			txtNumero.setBackground(Color.white);
			txtNumero.setToolTipText("Número no endereço de residênscia do professor");
		}
		return txtNumero;
	}

	/**
	 * This method initializes txtBairro	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtBairro() {
		if (txtBairro == null) {
			txtBairro = new JTextField();
			txtBairro.setBounds(new Rectangle(40, 194, 215, 22));
			txtBairro.setBackground(Color.white);
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
			txtCidade.setBounds(new Rectangle(297, 194, 215, 22));
			txtCidade.setBackground(Color.white);
			txtCidade.setToolTipText("Cidade de residência do professor");
		}
		return txtCidade;
	}

	/**
	 * This method initializes txtCep	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JFormattedTextField getTxtCep() {
		if (txtCep == null) {
			txtCep = new JFormattedTextField(setMascara("##.###-###"));
			txtCep.setBounds(new Rectangle(31, 223, 70, 22));			
			txtCep.setBackground(Color.white);
			txtCep.setToolTipText("C.E.P. da residência do professor");
		}
		return txtCep;
	}


	/**
	 * This method initializes txtTel	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtTel() {
		if (txtTel == null) {
			txtTel = new JFormattedTextField(setMascara("(##)####-####"));
			txtTel.setBounds(new Rectangle(147, 224, 89, 22));
			txtTel.setBackground(Color.white);
			txtTel.setToolTipText("Telefone de contato do professor");
		}
		return txtTel;
	}

	/**
	 * This method initializes txtEmail	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
			txtEmail.setBounds(new Rectangle(271, 224, 325, 22));
			txtEmail.setBackground(Color.white);
			txtEmail.setToolTipText("Email de contato do professor");
		}
		return txtEmail;
	}
	/**
	 * This method initializes txtDtAd	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtDtAd() {
		if (txtDtAd == null) {
			txtDtAd = new JFormattedTextField(setMascara("##/##/####"));
			txtDtAd.setBounds(new Rectangle(103, 44, 86, 22));
			txtDtAd.setBackground(Color.white);
			txtDtAd.setToolTipText("Data de admissão no componente curricular");
		}
		return txtDtAd;
	}

	/**
	 * This method initializes txtCpf	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtCpf() {
		if (txtCpf == null) {
			txtCpf = new JFormattedTextField(setMascara("###.###.###-##"));
			txtCpf.setBounds(new Rectangle(39, 43, 119, 22));
			txtCpf.setBackground(Color.white);
			txtCpf.setToolTipText("C.P.F. do professor");
		}
		return txtCpf;
	}

	/**
	 * This method initializes txtRg	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtRg() {
		if (txtRg == null) {
			txtRg = new JTextField();
			txtRg.setBounds(new Rectangle(187, 44, 125, 22));
			txtRg.setBackground(Color.white);
			txtRg.setToolTipText("R.G. do professor");
		}
		return txtRg;
	}

	/**
	 * This method initializes btnCarregar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCarregar() {
		if (btnCarregar == null) {
			btnCarregar = new JButton();
			btnCarregar.setBounds(new Rectangle(620, 194, 111, 22));
			btnCarregar.setText("Carregar");
			btnCarregar.setToolTipText("Carregar foto do professor");
			btnCarregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ajustaFoto(1);
					getBtnCarregar().setEnabled(false);
					getBtnLimpar().setEnabled(true);
				}
			});
		}
		return btnCarregar;
	}

	/**
	 * This method initializes btnEditar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEditar() {
		if (btnEditar == null) {
			btnEditar = new JButton();
			btnEditar.setBounds(new Rectangle(430, 435, 91, 21));
			btnEditar.setText("Editar");
			btnEditar.setToolTipText("Editar cadastro do professor");
			btnEditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					habilitarCampos();					
					getBtnAdicionar().setEnabled(true);
					getBtnConfirmar().setEnabled(true);
				}});
		}
		return btnEditar;
	}
	/**
	 * This method initializes btnLimpar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnLimpar() {
		if (btnLimpar == null) {
			btnLimpar = new JButton();
			btnLimpar.setBounds(new Rectangle(620, 219, 111, 22));
			btnLimpar.setText("Limpar");
			btnLimpar.setToolTipText("Limpar foto do professor");
			btnLimpar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ajustaFoto(0);
					getBtnCarregar().setEnabled(true);
					getBtnLimpar().setEnabled(false);
				}});
		}
		return btnLimpar;
	}

	/**
	 * This method initializes txtComplemento	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtComplemento() {
		if (txtComplemento == null) {
			txtComplemento = new JTextField();
			txtComplemento.setBounds(new Rectangle(193, 164, 148, 22));
			txtComplemento.setBackground(Color.white);
			txtComplemento.setToolTipText("Complemento no endereço de residência do professor");
		}
		return txtComplemento;
	}

	/**
	 * This method initializes txtDsComponente	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTxtDsComponente() {
		if (txtDsComponente == null) {
			txtDsComponente = new JTextArea();
			txtDsComponente.setBounds(new Rectangle(412, 124, 309, 127));
			txtDsComponente.setBackground(Color.white);
			txtDsComponente.setToolTipText("Descrição do componente curricular selecionado");
			txtDsComponente.setEditable(false);
		}
		return txtDsComponente;
	}

	/**
	 * This method initializes txtLogin	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtLogin() {
		if (txtLogin == null) {
			txtLogin = new JTextField();
			txtLogin.setBounds(new Rectangle(41, 44, 152, 22));
			txtLogin.setBackground(Color.white);
			txtLogin.setToolTipText("Login de acesso ao SENA II");
			txtLogin.setEnabled(false);
		}
		return txtLogin;
	}

	/**
	 * This method initializes txtSenha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtSenha() {
		if (txtSenha == null) {
			txtSenha = new JPasswordField();
			txtSenha.setBounds(new Rectangle(41, 74, 152, 22));
			txtSenha.setBackground(Color.white);
			txtSenha.setToolTipText("Senha de acesso ao SENA II");
			txtSenha.setEnabled(false);
		}
		return txtSenha;
	}
	/**
	 * This method initializes cmbEstadoN	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCmbEstadoN() {
		if (cmbEstadoN == null) {
			estados = new DefaultComboBoxModel();
			estados.addElement("--");
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
			cmbEstadoN = new JComboBox(estados);
			cmbEstadoN.setBounds(new Rectangle(266, 104, 41, 20));
			cmbEstadoN.setToolTipText("Estado de naturalidade do professor");
		}
		return cmbEstadoN;
	}
	/**
	 * This method initializes btnNovoComponente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnNovoComponente() {
		if (btnNovoComponente == null) {
			btnNovoComponente = new JButton();
			btnNovoComponente.setBounds(new Rectangle(630, 71, 91, 22));
			btnNovoComponente.setText("Novo");
			btnNovoComponente.setToolTipText("Novo componente curricular");
			btnNovoComponente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					desabilitarCampos();
					txtDtAd.setEditable(false);
					cmbComponente.setEnabled(false);
					btnEditar.setEnabled(true);
					btnEditarComponenteIe.setEnabled(false);
					btnAdicionar.setEnabled(false);
					btnConfirmar.setEnabled(false);
					setEditarAtivo(0);
					cmbComponente.setSelectedIndex(0);
					setComponenteSelecionado("");
					if ((formComponente == null) || (formComponente.isClosed())) {
						formComponente = new FrmCadastroComponente(desk, getPermissao(), 0, "");
						desk.add(formComponente, new Integer(2));
						formComponente.show();
					}
				}
			});
		}
		return btnNovoComponente;
	}
	/**
	 * This method initializes txtDtEntrada	
	 * 	
	 * @return javax.swing.JFormattedTextField	
	 */
	private JFormattedTextField getTxtDtEntrada() {
		if (txtDtEntrada == null) {
			txtDtEntrada = new JFormattedTextField(setMascara("##/##/####"));
			txtDtEntrada.setBounds(new Rectangle(645, 370, 86, 22));
			txtDtEntrada.setToolTipText("Data de admissão na Instituição de ensino");
			txtDtEntrada.setEnabled(false);
		}
		return txtDtEntrada;
	}
	/**
	 * This method initializes txtOrgao	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTxtOrgao() {
		if (txtOrgao == null) {
			txtOrgao = new JTextField();
			txtOrgao.setBounds(new Rectangle(365, 44, 101, 20));
			txtOrgao.setBackground(Color.white);
		}
		return txtOrgao;
	}
}