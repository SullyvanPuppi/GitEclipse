package apresentacao;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.text.MaskFormatter;

import negocios.Contato;
import negocios.Funcionario;

public class FrmCadastroFuncionario extends JInternalFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2646567386157691456L;
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
	private JCheckBox chkValeTransporte = null;
	private JCheckBox chkValeRefeicao = null;
	//private JLabel lblDependentes = null;
	private JFormattedTextField txtDependentes = null;
	private JCheckBox chkValeFamilia = null;	
	private JTextArea txtObs = null;

	private JRadioButton rdbSexoMasculino = null;
	private JRadioButton rdbSexoFeminino = null;
	private ButtonGroup radioSexo = new ButtonGroup();

	private JLabel lblDivisao = null;
	private JButton btnConfirmar = null;
	private JButton btnCancelar = null;
	private JButton btnAlterar = null;
	private JButton btnRelatorio = null;

	//Selecionar foto
	private String caminhoFoto = "";
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
		txtSalario.setText(consultar.getVlSalario());		
		txtDoc.setText(consultar.getTxtDoc());
		txtTelefone.setText(consultar.getTxtTelefone());
		txtEmail.setText(consultar.getTxtEmail());
		chkValeTransporte.setSelected(consultar.getChkValeTransporte());
		chkValeRefeicao.setSelected(consultar.getChkValeRefeicao());		
		txtDependentes.setText(consultar.getQtDependentes());
		txtObs.setText(consultar.getTxtObs());
		chkValeFamilia.setSelected(consultar.getChkValeFamilia());
		if(consultar.getSexo().equals("Masculino")){
			rdbSexoMasculino.setSelected(true);
		}else if(consultar.getSexo().equals("Feminino")){
			rdbSexoFeminino.setSelected(true);
		}
	}
	public void desabilitaCampos(){
		txtNmFuncionario.setEnabled(false);
		txtFuncao.setEnabled(false);
		txtSalario.setEnabled(false);
		txtDoc.setEnabled(false);
		txtTelefone.setEnabled(false);
		txtEmail.setEnabled(false);
		chkValeTransporte.setEnabled(false);
		chkValeRefeicao.setEnabled(false);
		txtDependentes.setEnabled(false);
		txtObs.setEnabled(false);
		chkValeFamilia.setEnabled(false);
		rdbSexoMasculino.setEnabled(false);
		rdbSexoFeminino.setEnabled(false);
	}
	public void habilitaCampos(){
		txtNmFuncionario.setEnabled(true);
		txtFuncao.setEnabled(true);
		txtSalario.setEnabled(true);
		txtDoc.setEnabled(true);
		txtTelefone.setEnabled(true);
		txtEmail.setEnabled(true);
		chkValeTransporte.setEnabled(true);
		chkValeRefeicao.setEnabled(true);
		txtDependentes.setEnabled(true);
		txtObs.setEnabled(true);
		chkValeFamilia.setEnabled(true);
		rdbSexoMasculino.setEnabled(true);
		rdbSexoFeminino.setEnabled(true);
	}

	/**
	 * This is the xxx default constructor
	 */
	public FrmCadastroFuncionario(JDesktopPane desktop, int permissao, int op, String matricula, String tipo) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		this.op = op;
		initialize();
		if(getOp()==0){//Novo cadastro
			Funcionario novo = new Funcionario();
			txtFuncionario.setText(novo.novoFuncionario());
			btnAlterar.setEnabled(false);
			btnConfirmar.setEnabled(true);
			habilitaCampos();
		}else{//Exibir dados
			ajustaDados(matricula, tipo);
			btnAlterar.setEnabled(true);
			btnConfirmar.setEnabled(false);
			desabilitaCampos();
		}
		txtFuncionario.setEnabled(false);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setTitle("Cadastro de funcionário simplificado");
		//this.setModal(true); 
		this.setResizable(false);
		
		txtCaminho = new JTextField("");
		btnSelecionaFoto = new JButton("Selecionar");
		
		lblFuncionario = new JLabel("Matrícula");
		txtFuncionario = new JFormattedTextField(setMascara("**********"));
		txtFuncionario.setSize(12,20);
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


		chkValeTransporte = new JCheckBox("Vale transporte");
		//lblDependentes = new JLabel("Dependentes");
		txtDependentes = new JFormattedTextField(setMascara("###"));
		chkValeFamilia = new JCheckBox("Salário família");
		chkValeRefeicao = new JCheckBox("Vale refeição");
		lblObs = new JLabel("Observações");
		txtObs = new JTextArea();
		JScrollPane jspTxtObs = new JScrollPane(txtObs);
		jspTxtObs.setPreferredSize(new Dimension(40, 100));

		lblDivisao = new JLabel("_________________________________________________________________________________________");
		btnRelatorio = new JButton("Relatório");
		btnRelatorio.setToolTipText("Gerar relatório com parâmetros selecionados");
		btnAlterar = new JButton("Alterar");
		btnAlterar.setToolTipText("Alterar dados cadastrados");
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setToolTipText("Confirmar dados para cadastro");
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Cancelar cadastro e fechar");

		Box linhaUm = Box.createHorizontalBox();
		Box linhaTres = Box.createHorizontalBox();
		Box linhaQuatro = Box.createHorizontalBox();
		Box linhaCinco = Box.createHorizontalBox();
		Box linhaSeis = Box.createHorizontalBox();
		Box linhaSete = Box.createHorizontalBox();
		Box linhaOito = Box.createHorizontalBox();
		Box linhaNove = Box.createHorizontalBox();
		Box linhaDoze = Box.createHorizontalBox();
		Box linhaTreze = Box.createHorizontalBox();
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
		linhaTres.add(lblDoc);
		linhaTres.add(txtDoc);
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
				cadastrar.ajustaDados(txtFuncionario.getText(), txtNmFuncionario.getText(), txtFuncao.getText(), txtSalario.getText(), txtDoc.getText(), txtTelefone.getText(), txtEmail.getText(), vlTransporte, vlRefeicao, txtDependentes.getText().trim(), vlFamilia, sexo, txtObs.getText());
				if(cadastrar.validaDadosSimples()>0){
					JOptionPane.showMessageDialog(null, "Encontrada inconsistências nos dados informados:\n"+cadastrar.getMsgStatus(),"Erro",2);
				}else{
					if(cadastrar.verificaExiste()==0){
						cadastrar.cadastrarSimples(getCaminhoFoto());
						Contato contato = new Contato();
						contato.ajustaDados("Pesquero Representações", txtNmFuncionario.getText(), txtFuncao.getText(), txtTelefone.getText(), "", txtEmail.getText(), sexo, "");
						contato.cadastrar("Funcionário", "", "");
						btnAlterar.setEnabled(true);
						btnConfirmar.setEnabled(false);
					}else{
						if(op==1){
							if (JOptionPane.showConfirmDialog(new JFrame(),
									"Deseja alterar as informações do funcionário?", "Confirmação",
									JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
								cadastrar.alterarSimples();
								Contato contato = new Contato();
								contato.ajustaDados("Pesquero representações", txtNmFuncionario.getText(), txtFuncao.getText(), txtTelefone.getText(), "", txtEmail.getText(), sexo, "");
								contato.alterar("Funcionário", "Pesquero representações", "");
								btnAlterar.setEnabled(true);
								btnConfirmar.setEnabled(false);
							}	
						}else{
							JOptionPane.showMessageDialog(null, "Funcionário ja está cadastrado","Erro",2);
						}						
					}

				}
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
		chkValeTransporte.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange()==ItemEvent.SELECTED){
					vlTransporte = true;//Sim
				}else{
					vlTransporte = false;//Não
				}
			}
		});
		chkValeFamilia.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange()==ItemEvent.SELECTED){
					vlFamilia = true;//Sim
				}else{
					vlFamilia = false;//Não
				}
			}
		});
		chkValeRefeicao.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange()==ItemEvent.SELECTED){
					vlRefeicao = true;//Sim
				}else{
					vlRefeicao = false;//Não
				}
			}
		});
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
		btnRelatorio.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Funcionario gerar = new Funcionario();
				gerar.gerarRelatorio(txtFuncionario.getText().trim(), txtNmFuncionario.getText().trim()).show();
			}});
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
