package apresentacao;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;

import negocios.Contato;
import negocios.Funcionario;

public class FrmCadastroFuncionarioCompleto extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6278739697760311645L;
	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	JDesktopPane desk = null;

	//Direitos de usuário determinado pelo grupo
	private int permissao;

	//--Checkbox
	private Boolean vlTransporte = false;
	private Boolean vlFamilia = false;
	private Boolean vlRefeicao = false;
	private String sexo = "";

	private JLabel lblFuncionario = null;
	private JFormattedTextField txtFuncionario = null;
	private JLabel lblNmFuncionario = null;
	private JFormattedTextField txtNmFuncionario = null;
	private JLabel lblFuncao = null;
	private JFormattedTextField txtFuncao = null;
	//private JLabel lblSalario = null;
	private JFormattedTextField txtSalario = null;
	private JLabel lblDoc = null;
	private JFormattedTextField txtDoc = null;
	private JLabel lblTelefone = null;
	private JFormattedTextField txtTelefone = null;
	private JLabel lblEmail = null;
	private JFormattedTextField txtEmail = null;
	private JLabel lblObs = null;
	@SuppressWarnings("unused")
	private JCheckBox chkValeTransporte = null;
	@SuppressWarnings("unused")
	private JCheckBox chkValeRefeicao = null;
	@SuppressWarnings("unused")
	private JLabel lblDependentes = null;
	private JFormattedTextField txtDependentes = null;
	@SuppressWarnings("unused")
	private JCheckBox chkValeFamilia = null;
	private JTextArea txtObs = null;
	private JRadioButton rdbSexoMasculino = null;
	private JRadioButton rdbSexoFeminino = null;
	private ButtonGroup radioSexo = new ButtonGroup();

	private JFormattedTextField txtCbo = null;
	private JFormattedTextField txtCtps = null;
	private JFormattedTextField txtDtNascimento = null;
	private JFormattedTextField txtCpf = null;
	private JFormattedTextField txtEndereco = null;
	private JFormattedTextField txtEnderecoNumero = null;
	private JFormattedTextField txtEnderecoComplemento = null;
	private JFormattedTextField txtEnderecoBairro = null;
	private JFormattedTextField txtEnderecoCidade = null;
	private JComboBox cmbEnderecoEstado = null;
	private JFormattedTextField txtEnderecoCep = null;
	private JFormattedTextField txtTelefoneContato = null;

	private JLabel lblDivisao = null;
	private JButton btnConfirmar = null;
	private JButton btnCancelar = null;
	private JButton btnAlterar = null;
	private JButton btnRelatorio = null;
	
	//Selecionar foto
	private String caminhoFoto = null;
	public String getCaminhoFoto(){
		return this.caminhoFoto;
	}
	public void setCaminhoFoto(String caminho){
		this.caminhoFoto = caminho;
	}
	private JTextField txtCaminho = null;
	private JButton btnSelecionaFoto = null;
	private String nmFoto = null;
	public void setNmFoto(String nmFoto){
		this.nmFoto = nmFoto;
	}
	public String getNmFoto(){
		return this.nmFoto;
	}

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
	private int op;

	public int getOp(){
		return op;
	}
	public void ajustaDados(String matricula, String tipo){
		Funcionario consultar = new Funcionario();
		consultar.consultarFuncionario(matricula, tipo);
		txtFuncionario.setText(consultar.getTxtFuncionario());
		txtNmFuncionario.setText(consultar.getTxtNmFuncionario());
		txtFuncao.setText(consultar.getTxtFuncao());
		//txtSalario.setText(consultar.getVlSalario());		
		txtDoc.setText(consultar.getTxtDoc());
		txtTelefone.setText(consultar.getTxtTelefone());
		txtEmail.setText(consultar.getTxtEmail());
		//chkValeTransporte.setSelected(consultar.getChkValeTransporte());
		//chkValeRefeicao.setSelected(consultar.getChkValeRefeicao());		
		//txtDependentes.setText(Integer.toString(consultar.getTxtDependentes()));
		//chkValeFamilia.setSelected(consultar.getChkValeFamilia());
		txtObs.setText(consultar.getTxtObs());
		txtCbo.setText(consultar.getTxtCbo());
		txtCtps.setText(consultar.getTxtCtps());
		txtDtNascimento.setText(consultar.getTxtDtNascimento());
		txtCpf.setText(consultar.getTxtCpf());
		txtEndereco.setText(consultar.getTxtEndereco());
		txtEnderecoNumero.setText(consultar.getTxtEnderecoNumero());
		txtEnderecoComplemento.setText(consultar.getTxtEnderecoComplemento());
		txtEnderecoBairro.setText(consultar.getTxtEnderecoBairro());
		txtEnderecoCidade.setText(consultar.getTxtEnderecoCidade());
		cmbEnderecoEstado.setSelectedItem((String) consultar.getCmbEnderecoEstado());
		txtEnderecoCep.setText(consultar.getTxtEnderecoCep());
		txtTelefoneContato.setText(consultar.getTxtTelefoneContato());
		if(consultar.getSexo().equals("Masculino")){
			rdbSexoMasculino.setSelected(true);
		}else if(consultar.getSexo().equals("Feminino")){
			rdbSexoFeminino.setSelected(true);
		}
	}
	public void desabilitaCampos(){
		txtFuncionario.setEditable(false);
		txtNmFuncionario.setEditable(false);
		txtFuncao.setEditable(false);
		txtDoc.setEditable(false);
		txtTelefone.setEditable(false);
		txtEmail.setEditable(false);
		txtObs.setEditable(false);
		txtCbo.setEditable(false);
		txtCtps.setEditable(false);
		txtDtNascimento.setEditable(false);
		txtCpf.setEditable(false);
		txtEndereco.setEditable(false);
		txtEnderecoNumero.setEditable(false);
		txtEnderecoComplemento.setEditable(false);
		txtEnderecoBairro.setEditable(false);
		txtEnderecoCidade.setEditable(false);
		cmbEnderecoEstado.setEnabled(false);
		txtEnderecoCep.setEditable(false);
		txtTelefoneContato.setEditable(false);
		rdbSexoMasculino.setEnabled(false);
		rdbSexoFeminino.setEnabled(false);
	}
	public void habilitaCampos(){
		txtFuncionario.setEditable(true);
		txtNmFuncionario.setEditable(true);
		txtFuncao.setEditable(true);
		txtDoc.setEditable(true);
		txtTelefone.setEditable(true);
		txtEmail.setEditable(true);
		txtObs.setEditable(true);
		txtCbo.setEditable(true);
		txtCtps.setEditable(true);
		txtDtNascimento.setEditable(true);
		txtCpf.setEditable(true);
		txtEndereco.setEditable(true);
		txtEnderecoNumero.setEditable(true);
		txtEnderecoComplemento.setEditable(true);
		txtEnderecoBairro.setEditable(true);
		txtEnderecoCidade.setEditable(true);
		cmbEnderecoEstado.setEnabled(true);
		txtEnderecoCep.setEditable(true);
		txtTelefoneContato.setEditable(true);
		rdbSexoMasculino.setEnabled(true);
		rdbSexoFeminino.setEnabled(true);
	}
	/**
	 * This is the xxx default constructor
	 */
	public FrmCadastroFuncionarioCompleto(JDesktopPane desktop, int permissao, int op, String matricula, String tipo) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		this.op = op;
		initialize();
		if(getOp()==0){//Novo cadastro
			Funcionario novo = new Funcionario();
			txtFuncionario.setText(novo.novoFuncionario());
			habilitaCampos();
			btnConfirmar.setEnabled(true);
			btnAlterar.setEnabled(false);
		}else{//Exibir dados
			ajustaDados(matricula, tipo);
			desabilitaCampos();
			btnConfirmar.setEnabled(false);
			btnAlterar.setEnabled(true);
		}
		txtFuncionario.setEnabled(false);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Cadastro de funcionário completo");
		//this.setModal(true);
		this.setResizable(false);
		
		txtCaminho = new JTextField();
		btnSelecionaFoto = new JButton("Selecionar");

		lblFuncionario = new JLabel("Matrícula");
		txtFuncionario = new JFormattedTextField(setMascara("**********"));
		lblNmFuncionario = new JLabel("Nome");
		txtNmFuncionario = new JFormattedTextField(setMascara("*********************************************"));
		JLabel lblSexo = new JLabel("Sexo");
		rdbSexoMasculino = new JRadioButton("Masculino");
		rdbSexoFeminino = new JRadioButton("Feminino");
		radioSexo.add(rdbSexoMasculino);
		radioSexo.add(rdbSexoFeminino);
		lblFuncao = new JLabel("Função");
		txtFuncao = new JFormattedTextField(setMascara("*********************************************"));
		//lblSalario = new JLabel("Salário");
		txtSalario = new JFormattedTextField(setMascara("R$####,##"));
		lblDoc = new JLabel("Documento");
		txtDoc = new JFormattedTextField(setMascara("************"));
		lblTelefone = new JLabel("Telefone");
		txtTelefone = new JFormattedTextField(setMascara("(##)####-####"));
		lblEmail = new JLabel("E-mail");
		txtEmail = new JFormattedTextField(setMascara("*********************************************"));
		//--Itens do cadastro completo
		JLabel lblCbo = new JLabel("CBO");
		txtCbo = new JFormattedTextField(setMascara("####/#-#"));
		JLabel lblCtps = new JLabel("CTPS");
		txtCtps = new JFormattedTextField(setMascara("************"));
		JLabel lblDtNascimento = new JLabel("Data de nascimento");
		txtDtNascimento = new JFormattedTextField(setMascara("##/##/####"));
		JLabel lblCpf = new JLabel("CPF");
		txtCpf = new JFormattedTextField(setMascara("###.###.###-##"));
		JLabel lblEndereco = new JLabel("Endereço");
		txtEndereco = new JFormattedTextField(setMascara("*********************************************"));
		JLabel lblEnderecoNumero = new JLabel("Número");
		txtEnderecoNumero = new JFormattedTextField(setMascara("#####"));
		JLabel lblEnderecoComplemento = new JLabel("Complemento");
		txtEnderecoComplemento = new JFormattedTextField(setMascara("************************"));
		JLabel lblEnderecoBairro = new JLabel("Bairro");
		txtEnderecoBairro = new JFormattedTextField(setMascara("*********************************************"));
		JLabel lblEnderecoCidade = new JLabel("Cidade");
		txtEnderecoCidade = new JFormattedTextField(setMascara("*********************************************"));
		JLabel lblEnderecoEstado = new JLabel("Estado");
		cmbEnderecoEstado = new JComboBox(getEstados());
		JLabel lblEnderecoCep = new JLabel("Cep");
		txtEnderecoCep = new JFormattedTextField(setMascara("##.###-###"));
		JLabel lblTelefone2 = new JLabel("Telefone contato");
		txtTelefoneContato = new JFormattedTextField(setMascara("(##)####-####"));

		chkValeTransporte = new JCheckBox("Vale transporte");
		lblDependentes = new JLabel("Dependentes");
		txtDependentes = new JFormattedTextField(setMascara("###"));
		chkValeFamilia = new JCheckBox("Salário família");
		chkValeRefeicao = new JCheckBox("Vale refeição");
		lblObs = new JLabel("Observações");
		txtObs = new JTextArea();
		JScrollPane jspTxtObs = new JScrollPane(txtObs);
		jspTxtObs.setPreferredSize(new Dimension(40, 100));

		lblDivisao = new JLabel("_______________________________________________________________________________________________");
		btnRelatorio = new JButton("Relatório");
		btnRelatorio.setToolTipText("Gerar relatório com parâmetros selecionados");
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
		Box linhaSeis = Box.createHorizontalBox();
		Box linhaSete = Box.createHorizontalBox();
		Box linhaOito = Box.createHorizontalBox();
		Box linhaNove = Box.createHorizontalBox();
		Box linhaDoze = Box.createHorizontalBox();
		Box linhaTreze = Box.createHorizontalBox();
		Box linhaA = Box.createHorizontalBox();
		Box linhaB = Box.createHorizontalBox();
		Box linhaC = Box.createHorizontalBox();
		Box linhaD = Box.createHorizontalBox();
		Box linhaE = Box.createHorizontalBox();
		Box linhaF = Box.createHorizontalBox();
		Box linhaFoto = Box.createHorizontalBox();

		linhaUm.add(lblFuncionario);
		linhaUm.add(txtFuncionario);
		linhaUm.add(Box.createHorizontalStrut(5));
		linhaUm.add(lblNmFuncionario);
		linhaUm.add(txtNmFuncionario);
		linhaTres.add(lblFuncao);
		linhaTres.add(txtFuncao);
		linhaTres.add(Box.createHorizontalStrut(5));
		//linhaTres.add(lblSalario);
		//linhaTres.add(txtSalario);
		//linhaTres.add(Box.createHorizontalStrut(5));
		linhaTres.add(lblCbo);
		linhaTres.add(txtCbo);
		linhaQuatro.add(lblTelefone);
		linhaQuatro.add(txtTelefone);
		linhaNove.add(lblEmail);
		linhaNove.add(txtEmail);
		//linhaCinco.add(chkValeTransporte);
		//linhaCinco.add(Box.createHorizontalStrut(5));
		//linhaCinco.add(chkValeFamilia);
		//linhaCinco.add(Box.createHorizontalStrut(5));
		//linhaCinco.add(chkValeRefeicao);
		//linhaCinco.add(Box.createHorizontalStrut(5));
		//linhaCinco.add(lblDependentes);
		//linhaCinco.add(txtDependentes);

		linhaSeis.add(lblSexo);
		linhaSeis.add(rdbSexoMasculino);
		linhaSeis.add(rdbSexoFeminino);
		linhaFoto.add(txtCaminho);
		linhaFoto.add(btnSelecionaFoto);

		linhaSete.add(lblObs);
		linhaOito.add(jspTxtObs);

		linhaA.add(lblTelefone2);
		linhaA.add(txtTelefoneContato);

		linhaB.add(lblCtps);
		linhaB.add(txtCtps);
		linhaC.add(Box.createHorizontalStrut(5));
		linhaC.add(lblDoc);
		linhaC.add(txtDoc);
		linhaC.add(Box.createHorizontalStrut(5));
		linhaC.add(lblCpf);
		linhaC.add(txtCpf);
		linhaC.add(Box.createHorizontalStrut(5));
		linhaC.add(lblDtNascimento);
		linhaC.add(txtDtNascimento);

		linhaD.add(lblEndereco);
		linhaD.add(txtEndereco);
		linhaE.add(lblEnderecoNumero);
		linhaE.add(txtEnderecoNumero);
		linhaE.add(Box.createHorizontalStrut(5));
		linhaE.add(lblEnderecoComplemento);
		linhaE.add(txtEnderecoComplemento);
		linhaE.add(Box.createHorizontalStrut(5));
		linhaE.add(lblEnderecoCep);
		linhaE.add(txtEnderecoCep);
		linhaF.add(lblEnderecoBairro);
		linhaF.add(txtEnderecoBairro);
		linhaF.add(Box.createHorizontalStrut(5));
		linhaF.add(lblEnderecoCidade);
		linhaF.add(txtEnderecoCidade);
		linhaF.add(Box.createHorizontalStrut(5));
		linhaF.add(lblEnderecoEstado);
		linhaF.add(cmbEnderecoEstado);		

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
		linhas.add(linhaB);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaC);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaQuatro);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaA);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaNove);	
		linhas.add(Box.createVerticalStrut(5));
		//linhas.add(linhaCinco);
		//linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaSeis);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaD);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaE);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaF);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaFoto);
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
				Funcionario cadastrar = new Funcionario();
				cadastrar.ajustaDados(txtFuncionario.getText(), txtNmFuncionario.getText(), txtFuncao.getText(), txtSalario.getText(), txtDoc.getText(), txtTelefone.getText(), txtEmail.getText(), vlTransporte, vlRefeicao, txtDependentes.getText().trim(), vlFamilia, sexo, txtObs.getText(), txtCbo.getText(), txtCtps.getText(), txtDtNascimento.getText(), txtCpf.getText(), txtEndereco.getText(), txtEnderecoNumero.getText(), txtEnderecoComplemento.getText(), txtEnderecoBairro.getText(), txtEnderecoCidade.getText(), (String) cmbEnderecoEstado.getSelectedItem(), txtEnderecoCep.getText(), txtTelefoneContato.getText());
				if(cadastrar.validaDadosCompleto()>0){
					JOptionPane.showMessageDialog(null, "Encontrada inconsistências nos dados informados:\n"+cadastrar.getMsgStatus(),"Erro",2);
				}else{
					if(cadastrar.verificaExiste()==0){
						cadastrar.cadastrarCompleto(getCaminhoFoto());
						Contato contato = new Contato();
						contato.ajustaDados("Pesquero representações", txtNmFuncionario.getText(), txtFuncao.getText(), txtTelefone.getText(), "", txtEmail.getText(), sexo, "");
						contato.cadastrar("Funcionário", "", "");
						desabilitaCampos();
						btnAlterar.setEnabled(true);
						btnConfirmar.setEnabled(false);
					}else{
						if(op==1){
							if (JOptionPane.showConfirmDialog(new JFrame(),
									"Deseja alterar as informações do funcionário?", "Confirmação",
									JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
								cadastrar.alterarCompleto();
								Contato contato = new Contato();
								contato.ajustaDados("Pesquero representações", txtNmFuncionario.getText(), txtFuncao.getText(), txtTelefone.getText(), "", txtEmail.getText(), sexo, "");
								contato.alterar("Funcionário", "Pesquero representações", "");
								desabilitaCampos();
								btnAlterar.setEnabled(true);
								btnConfirmar.setEnabled(false);
							}	
						}else{
							JOptionPane.showMessageDialog(null, "Funcionário ja está cadastrado","Erro",2);
						}						
					}
				}
			}});
		btnSelecionaFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("."));
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setFileFilter(new FileFilter() {
					public boolean accept(File f) {
						return f.getName().toLowerCase().endsWith(".jpg")
						|| f.isDirectory();
					}
					public String getDescription() {
						return "JPG Images";
					}
				});
				int r = chooser.showOpenDialog(new JFrame());
				if (r == JFileChooser.APPROVE_OPTION){
					setCaminhoFoto(chooser.getSelectedFile().getAbsolutePath());
					setNmFoto(txtFuncionario.getText());
				}
				txtCaminho.setText(getCaminhoFoto());
			}});
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				habilitaCampos();
				btnAlterar.setEnabled(false);
				btnConfirmar.setEnabled(true);
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
		txtDtNascimento.setInputVerifier(new InputVerifier() {
			public boolean verify(JComponent component) {
				if(((JTextComponent) component).getText().equals("  /  /    ")){
					txtDtNascimento.setText("");
					return ((JFormattedTextField) txtDtNascimento).isEditValid();
				}else{
					if (maiorIdade(component)){
						return ((JFormattedTextField) txtDtNascimento).isEditValid();
					}else{
						if (JOptionPane.showConfirmDialog(new JFrame(),
								"Funcionário menor de idade.\nContinuar assim mesmo?", "Confirmação",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
							return ((JFormattedTextField) txtDtNascimento).isEditValid();
						}else{
							txtDtNascimento.setText("");
							txtDtNascimento.grabFocus();
							return false; 
						}	
					}
				}				
			}
		});
		btnRelatorio.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Funcionario gerar = new Funcionario();
				gerar.gerarRelatorio(txtFuncionario.getText().trim(), txtNmFuncionario.getText().trim()).show();
			}});
	}
	public boolean maiorIdade(JComponent txtDtNascimento){
		Funcionario calcular = new Funcionario();
		if(calcular.calculaIdade(((JTextComponent) txtDtNascimento).getText())>0 && calcular.calculaIdade(((JTextComponent) txtDtNascimento).getText())<18){
			return false;
		}else{
			return true;
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
