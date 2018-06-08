package apresentacao;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;

import negocios.Contato;
import negocios.Visita;

public class FrmCadastroVisita extends JInternalFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9148410538999073356L;

	//--Instanciação do formulário de contato
	FrmCadastroContato formCadastroContato;

	private String representada = "--------------------------------";
	private Object[] lista = null;
	private String tipo = "";
	private boolean lembrar = true;
	String cdVendedor = "";

	public String getTipo(){
		return tipo;
	}

	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	private JDesktopPane desk = null;

	JTabbedPane abaPrincipal = null;
	JPanel abaDados = null;
	JPanel abaContatos = null;
	JPanel abaQuestoes = null;

	private Box contato = Box.createVerticalBox();
	private Box linhaContato = Box.createHorizontalBox();


	private JTextField txtCdVisita = null;
	private JFormattedTextField txtNmCliente = null;
	private JComboBox cmbSegmento = null;
	private JFormattedTextField txtDtVisita = null;
	private JComboBox cmbRepresentadas = null;
	private JFormattedTextField txtEndereco = null;
	private JFormattedTextField txtEnderecoNumero = null;
	private JFormattedTextField txtEnderecoComplemento = null;
	private JFormattedTextField txtEnderecoBairro = null;
	private JFormattedTextField txtEnderecoCidade = null;
	private JComboBox cmbEnderecoEstado = null;
	private JFormattedTextField txtEnderecoCep = null;
	private JFormattedTextField txtTelCliente = null;
	private JFormattedTextField txtFaxCliente = null;
	private JFormattedTextField txtEmail = null;
	private JFormattedTextField txtSite = null;
	private JTextArea txtObs = null;
	private JComboBox cmbVendedores = null;
	private JTextArea txtRespUm = null;
	private JTextArea txtRespDois = null;
	private JTextArea txtRespTres = null;
	private JTextArea txtRespQuatro = null;
	private JTextArea txtRespCinco = null;
	private JTextArea txtRespSeis = null;
	private JTextArea txtRespSete = null;
	private JFormattedTextField txtLembrar = null;
	private JTable tbContatos = null;
	private JScrollPane jspContatos = null;
	private JScrollPane jspListaRepresentadas = null;
	private JScrollPane jspTxtObs = null;
	private JFormattedTextField txtTipoOutros = null;
	private JList lstRepresentadas = null;
	private JCheckBox chkLembrar = null;

	//Direitos de usuário determinado pelo grupo
	private int permissao;
	private JLabel lblDivisao = null;
	private JButton btnConfirmar = null;
	private JButton btnCancelar = null;
	private JButton btnAlterar = null;
	private JButton btnRelatorio = null;
	private JButton btnAdicionarContato = new JButton("Novo");
	private JButton btnAlterarContato = new JButton("Abrir");
	private JButton btnRemoverContato = new JButton("Remover");
	private JButton btnRelContato = new JButton("Remover");
	private JButton btnAddRepresentada = new JButton("Adicionar");
	private JButton btnRemoverRepresentada = new JButton("Remover");
	private JRadioButton rdbComercial = new JRadioButton("Comercial");
	private JRadioButton rdbTecnica = new JRadioButton("Técnica");
	private JRadioButton rdbOutros = new JRadioButton("Outros");
	
	private JButton btnRelQuestoes = null;

	public int getPermissao(){
		return this.permissao;
	}
//	---Ajusta permissão no sistema
	public void ajustaPermissao(){
		if(getPermissao()==1){//Administrdor		

		}else if(getPermissao()==2){//Secretaria

		}else if(getPermissao()==3){//Convidado

		}
	}
	private String selecionado = "";
	private boolean status = false;

	//----Atributo para armazenar os itens da tabela(linhasXcolunas)	
	DefaultTableModel tabela;

	//----Retorna a tabela (linhasXcolunas)
	public DefaultTableModel getTabela(){
		return this.tabela;
	}
	//--Retornar seleção
	public String getSelecionado(){
		return this.selecionado;
	}
	public void setSelecionado(String selecao){
		this.selecionado = selecao;
	}
	//----Método para construir tabela de pesquisa
	public void construirTabela(){
		tbContatos = new JTable(getTabela());
		jspContatos = new JScrollPane( tbContatos );
		jspContatos.setPreferredSize(new Dimension(500, 500));
		contato.add(jspContatos);
		contato.add(linhaContato);
	}

	//----Consultar camada de negócios - Classe Funcionário
	public void consultarContatos(String nmEmpresa){
		Contato consultar = new Contato();
		consultar.consultarContatosEmpresa(nmEmpresa.trim());
		this.tabela = consultar.getTabelaContatosEmpresa();	
		if(tabela.getRowCount()<=0){
			Object linha[] = new Object[2];
			linha[0] = "";
			linha[1] = "";
			tabela.addRow(linha);
			construirTabela();			
		}else{
			construirTabela();			
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
	public void ajustaDados(String idVisita){
		Visita consultar = new Visita();
		consultar.consultarVisita(idVisita);
		txtCdVisita.setText(idVisita);
		txtNmCliente.setText(consultar.getTxtNmCliente());
		cmbSegmento.setSelectedItem((String) consultar.getCmbSegmento());
		txtDtVisita.setText(consultar.getTxtDtVisita());
		txtEndereco.setText(consultar.getTxtEndereco());
		txtEnderecoNumero.setText(consultar.getTxtEnderecoNumero());
		txtEnderecoComplemento.setText(consultar.getTxtEnderecoComplemento());
		txtEnderecoBairro.setText(consultar.getTxtEnderecoBairro());
		txtEnderecoCidade.setText(consultar.getTxtEnderecoCidade());
		cmbEnderecoEstado.setSelectedItem((String)consultar.getCmbEnderecoEstado());
		txtEnderecoCep.setText(consultar.getTxtEnderecoCep());
		txtTelCliente.setText(consultar.getTxtTelCliente());
		txtFaxCliente.setText(consultar.getTxtFaxCliente());
		txtEmail.setText(consultar.getTxtEmail());
		txtSite.setText(consultar.getTxtSite());
		txtObs.setText(consultar.getTxtObs());
		cmbVendedores.setSelectedItem((String) consultar.getNmVendedor());
		txtRespUm.setText(consultar.getTxtRespUm());
		txtRespDois.setText(consultar.getTxtRespDois());
		txtRespTres.setText(consultar.getTxtRespTres());
		txtRespQuatro.setText(consultar.getTxtRespQuatro());
		txtRespCinco.setText(consultar.getTxtRespCinco());
		txtRespSeis.setText(consultar.getTxtRespSeis());
		txtRespSete.setText(consultar.getTxtRespSete());
		chkLembrar.setSelected(consultar.getLembrar());
		txtLembrar.setText(consultar.getTxtLembrar());
		if(consultar.getTxtTipo().equals("Comercial")){
			rdbComercial.setSelected(true);
		}else if(consultar.getTxtTipo().equals("Técnica")){
			rdbTecnica.setSelected(true);	
		}else{
			rdbOutros.setSelected(true);
			txtTipoOutros.setText(consultar.getTxtTipo());
		}
		//ajustaOferecidas(consultar.getOferecidas());
		int contador = 0;
		while(contador<consultar.getOferecidas().length){
			if(consultar.getOferecidas()[contador].equals("")){
				lista[contador] = "";
			}else{				
				qtRepresentadas +=1;
				lista[contador] = (String) consultar.getOferecidas()[contador];
			}
			contador+=1;
		}
		lstRepresentadas.setListData(lista);
		//
	}
	private void desabilitaCampos(){
		cmbSegmento.setEnabled(false);
		cmbRepresentadas.setEnabled(false);
		txtDtVisita.setEditable(false);
		txtEndereco.setEditable(false);
		txtEnderecoNumero.setEditable(false);
		txtEnderecoComplemento.setEditable(false);
		txtEnderecoBairro.setEditable(false);
		txtEnderecoCidade.setEditable(false);
		cmbEnderecoEstado.setEnabled(false);
		txtEnderecoCep.setEditable(false);
		txtTelCliente.setEditable(false);
		txtFaxCliente.setEditable(false);
		txtEmail.setEditable(false);
		txtSite.setEditable(false);
		txtObs.setEditable(false);
		cmbVendedores.setEnabled(false);
		txtRespUm.setEditable(false);
		txtRespDois.setEditable(false);
		txtRespTres.setEditable(false);
		txtRespQuatro.setEditable(false);
		txtRespCinco.setEditable(false);
		txtRespSeis.setEditable(false);
		txtRespSete.setEditable(false);
		chkLembrar.setEnabled(false);
		txtLembrar.setEnabled(false);
		rdbComercial.setEnabled(false);
		rdbTecnica.setEnabled(false);
		rdbOutros.setEnabled(true);
		txtTipoOutros.setEnabled(false);
		lstRepresentadas.setEnabled(false);
	}
	private void habilitaCampos(){
		cmbSegmento.setEnabled(true);
		cmbRepresentadas.setEnabled(true);
		txtDtVisita.setEditable(true);
		txtEndereco.setEditable(true);
		txtEnderecoNumero.setEditable(true);
		txtEnderecoComplemento.setEditable(true);
		txtEnderecoBairro.setEditable(true);
		txtEnderecoCidade.setEditable(true);
		cmbEnderecoEstado.setEnabled(true);
		txtEnderecoCep.setEditable(true);
		txtTelCliente.setEditable(true);
		txtFaxCliente.setEditable(true);
		txtEmail.setEditable(true);
		txtSite.setEditable(true);
		txtObs.setEditable(true);
		cmbVendedores.setEnabled(true);
		txtRespUm.setEditable(true);
		txtRespDois.setEditable(true);
		txtRespTres.setEditable(true);
		txtRespQuatro.setEditable(true);
		txtRespCinco.setEditable(true);
		txtRespSeis.setEditable(true);
		txtRespSete.setEditable(true);
		chkLembrar.setEnabled(true);
		txtLembrar.setEnabled(true);
		rdbComercial.setEnabled(true);
		rdbTecnica.setEnabled(true);
		rdbOutros.setEnabled(true);
		txtTipoOutros.setEnabled(true);
		lstRepresentadas.setEnabled(true);
	}
	/**
	 * This is the xxx default constructor
	 */
	public FrmCadastroVisita(JDesktopPane desktop, int permissao, int op, String idVisita, String nmEmpresa) {
		super();	
		this.desk = desktop;
		this.permissao = permissao;
		this.op = op;
		consultarContatos(nmEmpresa);
		initialize();
		if(getOp()==0){//Novo cadastro
			Visita novo = new Visita();
			txtCdVisita.setText(""+novo.novaVisita());
			txtCdVisita.setEnabled(false);
			txtTipoOutros.setEnabled(false);
			btnAlterar.setEnabled(false);
			btnConfirmar.setEnabled(true);
			habilitaCampos();
		}else{//Exibir dados
			status = true;
			ajustaDados(idVisita);
			txtCdVisita.setEnabled(false);
			txtNmCliente.setEditable(false);
			btnAlterar.setEnabled(true);
			btnConfirmar.setEnabled(false);
			desabilitaCampos();
		}	
		txtLembrar.setEnabled(false);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		String titulo = "";
		lista = new Object[3];
		if(getOp()==0){
			titulo = "Nova visita";
			lista[0] = "";
			lista[1] = "";
			lista[2] = "";
			lembrar = false;
		}else{
			titulo = "Visita realizada";
		}
		this.setTitle(titulo);
		this.setResizable(false);

		abaPrincipal = new JTabbedPane();
		abaDados = new JPanel();
		abaContatos = new JPanel();
		abaQuestoes = new JPanel();

		JLabel lblNmCliente = new JLabel("Nome da empresa");
		txtNmCliente = new JFormattedTextField(setMascara("*********************************************"));
		JLabel lblCdVisita = new JLabel("Código de visita");
		txtCdVisita = new JTextField("",5);
		JLabel lblSegmento = new JLabel("Segmento");
		cmbSegmento = new JComboBox(getSegmentos());
		cmbSegmento.setPreferredSize(new Dimension(280, 20));
		JLabel lblDtVisita = new JLabel("Data da visita");
		txtDtVisita = new JFormattedTextField(setMascara("##/##/####"));
		JLabel lblRepresentadas = new JLabel("Empresas oferecidas");
		cmbRepresentadas = new JComboBox(getRepresentadas());
		cmbRepresentadas.setPreferredSize(new Dimension(280, 20));
		btnAddRepresentada = new JButton("Adicionar");
		btnAddRepresentada.setToolTipText("Adicionar empresa representada oferecida em visita");
		btnRemoverRepresentada = new JButton("Remover");
		btnRemoverRepresentada.setToolTipText("Remover empresa representada oferecida em visita");
		btnRemoverRepresentada.setEnabled(false);

		lstRepresentadas = new JList();
		jspListaRepresentadas = new JScrollPane(lstRepresentadas);
		jspListaRepresentadas.setPreferredSize(new Dimension(500, 50));

		JLabel lblEndereco = new JLabel("Endereço");
		txtEndereco = new JFormattedTextField(setMascara("*********************************************"));
		JLabel lblEnderecoNumero = new JLabel("Número");
		txtEnderecoNumero = new JFormattedTextField(setMascara("#####"));
		JLabel lblEnderecoComplemento = new JLabel("Complemento");
		txtEnderecoComplemento = new JFormattedTextField(setMascara("********************"));
		JLabel lblEnderecoBairro = new JLabel("Bairro");
		txtEnderecoBairro = new JFormattedTextField(setMascara("*********************************************"));
		JLabel lblEnderecoCidade = new JLabel("Cidade");
		txtEnderecoCidade = new JFormattedTextField(setMascara("*********************************************"));
		JLabel lblEnderecoEstado = new JLabel("Estado");
		cmbEnderecoEstado = new JComboBox(getEstados());
		JLabel lblEnderecoCep = new JLabel("Cep");
		txtEnderecoCep = new JFormattedTextField(setMascara("##.###-###"));
		JLabel lblTelCliente = new JLabel("Telefone");
		txtTelCliente = new JFormattedTextField(setMascara("(##)####-####"));
		JLabel lblFaxCliente = new JLabel("Fax");
		txtFaxCliente = new JFormattedTextField(setMascara("(##)####-####"));
		JLabel lblEmail = new JLabel("E-mail");
		txtEmail = new JFormattedTextField(setMascara("*********************************************"));
		JLabel lblSite = new JLabel("Site");
		txtSite = new JFormattedTextField(setMascara("*********************************************"));
		JLabel lblObs = new JLabel("Observações");
		txtObs = new JTextArea();
		jspTxtObs = new JScrollPane(txtObs);
		jspTxtObs.setPreferredSize(new Dimension(40, 100));

		JLabel lblTipo = new JLabel("Tipo de visita");
		ButtonGroup radioTipo = new ButtonGroup();
		rdbComercial = new JRadioButton("Comercial");
		rdbTecnica = new JRadioButton("Técnica");
		rdbOutros = new JRadioButton("Outros");
		radioTipo.add(rdbComercial);
		radioTipo.add(rdbTecnica);
		radioTipo.add(rdbOutros);
		txtTipoOutros = new JFormattedTextField(setMascara("********************"));
		JLabel lblVendedor = new JLabel("Vendedor");
		cmbVendedores = new JComboBox(getVendedores());
		cmbVendedores.setPreferredSize(new Dimension(450, 20));

		//--Itens da guia questões
		JLabel lblQuestaoUm = new JLabel("Objetivo?");
		txtRespUm = new JTextArea();
		JScrollPane jsptxtRespUm = new JScrollPane(txtRespUm);
		jsptxtRespUm.setPreferredSize(new Dimension(500, 50));
		JLabel lblQuestaoDois = new JLabel("Potencial e previsão de investimentos?");
		txtRespDois = new JTextArea();
		JScrollPane jsptxtRespDois = new JScrollPane(txtRespDois);
		jsptxtRespDois.setPreferredSize(new Dimension(500, 50));
		JLabel lblQuestaoTres = new JLabel("Ações?");
		txtRespTres = new JTextArea();
		JScrollPane jsptxtRespTres = new JScrollPane(txtRespTres);
		jsptxtRespTres.setPreferredSize(new Dimension(500, 50));
		JLabel lblQuestaoQuatro = new JLabel("De quem compra?");
		txtRespQuatro = new JTextArea();
		JScrollPane jsptxtRespQuatro = new JScrollPane(txtRespQuatro);
		jsptxtRespQuatro.setPreferredSize(new Dimension(500, 50));
		JLabel lblQuestaoCinco = new JLabel("Quem são nossos concorrentes?");
		txtRespCinco = new JTextArea();
		JScrollPane jsptxtRespCinco = new JScrollPane(txtRespCinco);
		jsptxtRespCinco.setPreferredSize(new Dimension(500, 50));
		JLabel lblQuestaoSeis = new JLabel("Quando há previsão para comprar?");
		txtRespSeis = new JTextArea();
		JScrollPane jsptxtRespSeis = new JScrollPane(txtRespSeis);
		jsptxtRespSeis.setPreferredSize(new Dimension(500, 50));
		JLabel lblQuestaoSete = new JLabel("Quanto paga para o concorrente sobre este produto?");
		txtRespSete = new JTextArea();
		JScrollPane jsptxtRespSete = new JScrollPane(txtRespSete);
		jsptxtRespSete.setPreferredSize(new Dimension(500, 50));	
		chkLembrar = new JCheckBox("Lembrar visita");		
		txtLembrar = new JFormattedTextField(setMascara("##/##/####"));

		btnAdicionarContato = new JButton("Novo");
		btnAdicionarContato.setToolTipText("Adicionar contato da empresa representada");
		btnAlterarContato = new JButton("Abrir");
		btnAlterarContato.setToolTipText("Exibir item selecionado");
		btnRemoverContato = new JButton("Remover");
		btnRemoverContato.setToolTipText("Remover item selecionado");
		btnRelContato = new JButton("Relatório");
		btnRelContato.setToolTipText("Gerar relatório de contatos desta empresa reprsentada");
		
		lblDivisao = new JLabel("___________________________________________________________________________________________");
		btnRelatorio = new JButton("Relatório");
		btnRelatorio.setToolTipText("Gerar relatório com parâmetros selecionados");
		btnAlterar = new JButton("Alterar");
		btnAlterar.setToolTipText("Alterar dados cadastrados");
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setToolTipText("Confirmar dados para cadastro");
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Cancelar cadastro e fechar");
		
		btnRelQuestoes = new JButton("Relatório");

		Box linhaUm = Box.createHorizontalBox();
		Box linhaDois = Box.createHorizontalBox();
		Box linhaQuatro = Box.createHorizontalBox();
		Box linhaCinco = Box.createHorizontalBox();
		Box linhaSeis = Box.createHorizontalBox();
		Box linhaSete = Box.createHorizontalBox();
		Box linhaOito = Box.createHorizontalBox();
		Box linhaNove = Box.createHorizontalBox();

		Box linhaD = Box.createHorizontalBox();
		Box linhaE = Box.createHorizontalBox();
		Box linhaF = Box.createHorizontalBox();

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

		Box linhaDoze = Box.createHorizontalBox();
		Box linhaTreze = Box.createHorizontalBox();

		linhaUm.add(lblNmCliente);
		linhaUm.add(txtNmCliente);
		linhaUm.add(Box.createHorizontalStrut(5));
		linhaUm.add(lblCdVisita);
		linhaUm.add(txtCdVisita);
		linhaDois.add(Box.createHorizontalStrut(5));
		linhaQuatro.add(lblSegmento);
		linhaQuatro.add(cmbSegmento);
		linhaQuatro.add(Box.createHorizontalStrut(5));
		linhaQuatro.add(lblDtVisita);
		linhaQuatro.add(txtDtVisita);
		linhaQuatro.add(Box.createHorizontalStrut(5));
		linhaCinco.add(lblTelCliente);
		linhaCinco.add(txtTelCliente);
		linhaCinco.add(Box.createHorizontalStrut(5));
		linhaCinco.add(lblFaxCliente);
		linhaCinco.add(txtFaxCliente);
		linhaCinco.add(Box.createHorizontalStrut(5));
		linhaSeis.add(lblEmail);
		linhaSeis.add(txtEmail);
		linhaSeis.add(Box.createHorizontalStrut(5));
		linhaSeis.add(lblSite);
		linhaSeis.add(txtSite);
		linhaSete.add(lblObs);
		linhaOito.add(lblObs);
		linhaNove.add(jspTxtObs);

		Box linhaTipo = Box.createHorizontalBox();

		linhaTipo.add(lblTipo);
		linhaTipo.add(Box.createHorizontalStrut(5));
		linhaTipo.add(rdbComercial);
		linhaTipo.add(Box.createHorizontalStrut(5));
		linhaTipo.add(rdbTecnica);
		linhaTipo.add(Box.createHorizontalStrut(5));
		linhaTipo.add(rdbOutros);
		linhaTipo.add(Box.createHorizontalStrut(5));
		linhaTipo.add(txtTipoOutros);

		Box linhaRepresentadas = Box.createHorizontalBox();

		linhaRepresentadas.add(lblRepresentadas);
		linhaRepresentadas.add(cmbRepresentadas);
		linhaRepresentadas.add(Box.createHorizontalStrut(5));
		linhaRepresentadas.add(btnAddRepresentada);	
		linhaRepresentadas.add(Box.createHorizontalStrut(5));
		linhaRepresentadas.add(btnRemoverRepresentada);	

		Box linhaListRepresentadas = Box.createHorizontalBox();

		linhaListRepresentadas.add(jspListaRepresentadas);

		Box linhaVendedor = Box.createHorizontalBox();
		linhaVendedor.add(lblVendedor);
		linhaVendedor.add(cmbVendedores);

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
		linhas.add(linhaQuatro);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaTipo);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaRepresentadas);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaListRepresentadas);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaD);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaE);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaF);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaCinco);	
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaSeis);
		linhas.add(Box.createVerticalStrut(15));
		linhas.add(linhaVendedor);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaSete);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaOito);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaNove);

		linhaContato.add(btnRelContato);
		linhaContato.add(btnAlterarContato);
		linhaContato.add(btnAdicionarContato);
		linhaContato.add(btnRemoverContato);

		Box principal = Box.createVerticalBox();

		Box linhaQuestoesA = Box.createHorizontalBox();
		Box linhaQuestoesB = Box.createHorizontalBox();
		Box linhaQuestoesC = Box.createHorizontalBox();
		Box linhaQuestoesD = Box.createHorizontalBox();
		Box linhaQuestoesE = Box.createHorizontalBox();
		Box linhaQuestoesF = Box.createHorizontalBox();
		Box linhaQuestoesG = Box.createHorizontalBox();
		Box linhaQuestoesH = Box.createHorizontalBox();
		Box linhaQuestoesI = Box.createHorizontalBox();
		Box linhaQuestoesJ = Box.createHorizontalBox();
		Box linhaQuestoesL = Box.createHorizontalBox();
		Box linhaQuestoesM = Box.createHorizontalBox();
		Box linhaQuestoesN = Box.createHorizontalBox();
		Box linhaQuestoesO = Box.createHorizontalBox();
		Box linhaQuestoesP = Box.createHorizontalBox();
		Box linhaQuestoesQ = Box.createHorizontalBox();

		linhaQuestoesA.add(lblQuestaoUm);
		linhaQuestoesB.add(jsptxtRespUm);
		linhaQuestoesC.add(lblQuestaoDois);
		linhaQuestoesD.add(jsptxtRespDois);
		linhaQuestoesE.add(lblQuestaoTres);
		linhaQuestoesF.add(jsptxtRespTres);
		linhaQuestoesG.add(lblQuestaoQuatro);
		linhaQuestoesH.add(jsptxtRespQuatro);
		linhaQuestoesI.add(lblQuestaoCinco);
		linhaQuestoesJ.add(jsptxtRespCinco);
		linhaQuestoesL.add(lblQuestaoSeis);
		linhaQuestoesM.add(jsptxtRespSeis);
		linhaQuestoesN.add(lblQuestaoSete);
		linhaQuestoesO.add(jsptxtRespSete);
		linhaQuestoesP.add(chkLembrar);
		linhaQuestoesP.add(txtLembrar);
		linhaQuestoesQ.add(btnRelQuestoes);

		Box Questoes = Box.createVerticalBox();
		Questoes.add(linhaQuestoesA);
		Questoes.add(linhaQuestoesB);
		Questoes.add(Box.createVerticalStrut(5));
		Questoes.add(linhaQuestoesC);
		Questoes.add(linhaQuestoesD);
		Questoes.add(Box.createVerticalStrut(5));
		Questoes.add(linhaQuestoesE);
		Questoes.add(linhaQuestoesF);
		Questoes.add(Box.createVerticalStrut(5));
		Questoes.add(linhaQuestoesG);
		Questoes.add(linhaQuestoesH);
		Questoes.add(Box.createVerticalStrut(5));
		Questoes.add(linhaQuestoesI);
		Questoes.add(linhaQuestoesJ);
		Questoes.add(Box.createVerticalStrut(5));
		Questoes.add(linhaQuestoesL);
		Questoes.add(linhaQuestoesM);
		Questoes.add(Box.createVerticalStrut(5));
		Questoes.add(linhaQuestoesN);
		Questoes.add(linhaQuestoesO);
		Questoes.add(Box.createVerticalStrut(5));
		Questoes.add(linhaQuestoesP);
		Questoes.add(Box.createVerticalStrut(5));
		Questoes.add(linhaQuestoesQ);

		abaPrincipal.addTab("Dados da empresa", null, abaDados, null);
		abaPrincipal.addTab("Questões", null, abaQuestoes, null);
		abaPrincipal.addTab("Contatos", null, abaContatos, null);

		abaDados.add(linhas);
		abaContatos.add(contato);
		abaQuestoes.add(Questoes);

		principal.add(abaPrincipal);
		principal.add(linhaDoze);
		principal.add(Box.createVerticalStrut(10));
		principal.add(linhaTreze);


		jContentPane = new JPanel();
		jContentPane.add(principal);
		getContentPane().add(jContentPane);
		pack();

		//----Ações
		tbContatos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) tbContatos.getValueAt(tbContatos.getSelectedRow(), 0));
			}
		});
		btnAdicionarContato.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(status){
					if ((formCadastroContato == null) || (!formCadastroContato.isVisible())) {
						formCadastroContato = new FrmCadastroContato(desk, getPermissao(), 0, "", txtNmCliente.getText(), "", "Visita", txtCdVisita.getText());
						formCadastroContato.setLocation(300,300);
						formCadastroContato.show();
						atualizaContatos();
					}	
				}else{
					JOptionPane.showMessageDialog(null,"Cadastre primeiro a empresa visitada antes de adicionar contatos à ela.","Notificação", 1);
				}
				
			}});
		btnAlterarContato.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(getSelecionado().equals("")){
					JOptionPane.showMessageDialog(null,"Selecione um contato para poder visualizar seus dados","Notificação", 1);
				}else{
					if ((formCadastroContato == null) || (!formCadastroContato.isVisible())) {
						formCadastroContato = new FrmCadastroContato(desk, getPermissao(), 1, getSelecionado(), txtNmCliente.getText(), "", "Visita", txtCdVisita.getText());
						formCadastroContato.setLocation(300,300);
						formCadastroContato.show();
						atualizaContatos();
					}	
				}				
			}});
		btnRemoverContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getSelecionado().equals("")){
					JOptionPane.showMessageDialog(null,"Selecione um contato para poder removê-lo","Notificação",1);
				}else{
					String nome = (String) tbContatos.getValueAt(tbContatos.getSelectedRow(), 0);
					Contato remover = new Contato();
					if (JOptionPane.showConfirmDialog(new JFrame(),
							"Tem certeza que deseja remover este contato?\n\nContato: "+nome, "Confirmação",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						remover.remover(getSelecionado(), txtNmCliente.getText().trim(), "", "Visita");
						atualizaContatos();
					}	
				}					
			}});
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Visita cadastrar = new Visita();
				cadastrar.ajustaDados(txtCdVisita.getText(), txtNmCliente.getText(), (String) cmbSegmento.getSelectedItem(), txtDtVisita.getText(), txtEndereco.getText(), txtEnderecoNumero.getText(), txtEnderecoComplemento.getText(), txtEnderecoBairro.getText(), txtEnderecoCidade.getText(), (String) cmbEnderecoEstado.getSelectedItem(), txtEnderecoCep.getText(), txtTelCliente.getText(), txtFaxCliente.getText(), txtEmail.getText(), txtSite.getText(), txtObs.getText(), (String) cmbVendedores.getSelectedItem(), txtRespUm.getText(), txtRespDois.getText(), txtRespTres.getText(), txtRespQuatro.getText(), txtRespCinco.getText(), txtRespSeis.getText(), txtRespSete.getText(), lembrar, txtLembrar.getText(), (String) cmbVendedores.getSelectedItem(), getTipo(), txtTipoOutros.getText());
				cadastrar.ajustaOferecidas(lista);
				if(cadastrar.validaDados()>0){
					JOptionPane.showMessageDialog(null, "Encontrada inconsistências nos dados informados:\n"+cadastrar.getMsgStatus(),"Erro",2);
				}else{
					if(cadastrar.verificaExiste()==0){
						cadastrar.cadastrar();
						cadastrar.cadastrarQuestoes(txtCdVisita.getText(), txtDtVisita.getText(), txtNmCliente.getText(), txtRespUm.getText(), txtRespDois.getText(), txtRespTres.getText(), txtRespQuatro.getText(), txtRespCinco.getText(), txtRespSeis.getText(), txtRespSete.getText());
						btnAlterar.setEnabled(true);
						btnConfirmar.setEnabled(false);
						desabilitaCampos();
						status = true;
					}else{
						if(op==1){
							if (JOptionPane.showConfirmDialog(new JFrame(),
									"Deseja alterar as informações da visita realizada?", "Confirmação",
									JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
								cadastrar.alterar();
								cadastrar.alteraQuestoes(txtCdVisita.getText(), txtDtVisita.getText(), txtNmCliente.getText(), txtRespUm.getText(), txtRespDois.getText(), txtRespTres.getText(), txtRespQuatro.getText(), txtRespCinco.getText(), txtRespSeis.getText(), txtRespSete.getText());
								btnAlterar.setEnabled(true);
								btnConfirmar.setEnabled(false);
								desabilitaCampos();
							}	
						}else{
							JOptionPane.showMessageDialog(null, "Empresa representada ja está cadastrada","Erro",2);
						}				
					}
				}
			}});
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAlterar.setEnabled(false);
				btnConfirmar.setEnabled(true);
				habilitaCampos();
			}});
		chkLembrar.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange()==ItemEvent.SELECTED){
					lembrar = true;//Sim
					txtLembrar.setEnabled(true);
				}else{
					lembrar = false;//Não
					txtLembrar.setEnabled(false);
				}
			}
		});		
		txtDtVisita.setInputVerifier(new InputVerifier() {
			public boolean verify(JComponent component) {
				if(((JTextComponent) component).getText().equals("  /  /    ")){
					txtDtVisita.setText("");
					return ((JFormattedTextField) txtDtVisita).isEditValid();
				}else{
					Visita consultar = new Visita();
					if(consultar.validaDataCadastro(consultar.getHoje(), txtDtVisita.getText())>0){
						txtDtVisita.setText("");
						txtDtVisita.grabFocus();
						return false;
					}else{
						return ((JFormattedTextField) txtDtVisita).isEditValid();
					}
				}				
			}
		});
		rdbTecnica.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange()==ItemEvent.SELECTED){
					tipo ="Técnica";
					txtTipoOutros.setEnabled(false);
				}
			}
		});
		rdbOutros.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange()==ItemEvent.SELECTED){
					tipo = "Outros";
					txtTipoOutros.setEnabled(true);
				}
			}
		});
		rdbComercial.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange()==ItemEvent.SELECTED){
					tipo ="Comercial";
					txtTipoOutros.setEnabled(false);
				}
			}
		});
		cmbRepresentadas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED){
					representada = (String) cmbRepresentadas.getSelectedItem();
				}				
			}
		});
		cmbVendedores.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED){
					Visita consultar = new Visita();
					cdVendedor = consultar.consultarVendedor((String) cmbVendedores.getSelectedItem());
				}				
			}
		});
		lstRepresentadas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try{
					@SuppressWarnings("unused")
					int c = 0;
					c  = lstRepresentadas.getSelectedIndex();
					String nome = "";
					nome = (String) lstRepresentadas.getSelectedValue();
					if(nome.trim().equals("")){
						
					}else{
						nmRepresentada = nome;
						btnRemoverRepresentada.setEnabled(true);
					}	
				}catch(Exception ex){
					
				}						
			}			
			//}
		});
		btnAddRepresentada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(representada.equals("--------------------------------")){
					JOptionPane.showMessageDialog(null,"Selecione uma empresa representada para poder adiciona-la a lista","Notificação",1);
				}else{
					int valida = 0;
					int contador = 0;
					while(contador<3){
						if(lista[contador].equals(representada)){
							valida =1;
						}
						contador+=1;
					}
					if(valida==1){
						JOptionPane.showMessageDialog(null,"Empresa representada já está na lista de empresas oferecidas","Notificação",1);
					}else{
						if(qtRepresentadas>2){
							JOptionPane.showMessageDialog(null,"Só é possível oferecer até 3 (Três) empresas representadas em cada visita","Notificação",1);
						}else{
							lista[qtRepresentadas] = (String) cmbRepresentadas.getSelectedItem();
							qtRepresentadas +=1;
							lstRepresentadas.setListData(lista);
						}														
					}			
				}
				cmbRepresentadas.setSelectedIndex(0);
			}});
		btnRemoverRepresentada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nmRepresentada.equals("")){
					JOptionPane.showMessageDialog(null,"Selecione uma empresa representada para poder removê-la a lista","Notificação",1);
				}else{
					if (JOptionPane.showConfirmDialog(new JFrame(),
							"Tem certeza que deseja remover esta empresa oferecida?\n\nEmpresa: "+nmRepresentada, "Confirmação",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
						int contador = 0;
						while(!nmRepresentada.equals(lista[contador])){
							contador+=1;
						}
						lista[contador] = "";
						qtRepresentadas -=1;
						lstRepresentadas.setListData(lista);
						cmbRepresentadas.setSelectedIndex(0);
						nmRepresentada = "";
					}	
				}				
			}});
		btnRelatorio.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Visita gerar = new Visita();
				gerar.gerarRelatorioVisita(txtNmCliente.getText().trim(), txtDtVisita.getText().trim() , (String) cmbVendedores.getSelectedItem()).show();
			}});
		btnRelContato.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Visita gerar = new Visita();
				gerar.gerarRelatorioContatos(txtNmCliente.getText().trim(), txtDtVisita.getText().trim()).show();
			}});
		btnRelQuestoes.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Visita gerar = new Visita();
				gerar.gerarRelatorioQuestoes(txtNmCliente.getText().trim(), txtDtVisita.getText().trim(), (String) cmbVendedores.getSelectedItem()).show();
			}});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();	
			}});
	}
	private void atualizaContatos(){
		jspContatos.remove(tbContatos);
		contato.remove(jspContatos);
		//contato.remove(linhaContato);
		//abaContatos.remove(contato);
		consultarContatos(txtNmCliente.getText().trim());
		abaContatos.add(contato);
		contato.doLayout();
		abaContatos.doLayout();
		//abaPrincipal.doLayout();
		//jContentPane.doLayout();
		tbContatos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setSelecionado((String) tbContatos.getValueAt(tbContatos.getSelectedRow(), 0));
			}
		});
	}
	private int qtRepresentadas = 0;
	public int getQtRepresentadas(){
		return qtRepresentadas;
	}
	private String nmRepresentada = "";
	public String getNmRepresentada(){
		return nmRepresentada;
	}
	public DefaultComboBoxModel getSegmentos(){
		DefaultComboBoxModel segmentos = new DefaultComboBoxModel();
		//Lista de segmentos
		segmentos.addElement(new String("--------------------------------"));
		segmentos.addElement(new String("00 - Não informado"));
		segmentos.addElement(new String("01 - Alimentos"));
		segmentos.addElement(new String("02 - Automobilística"));
		segmentos.addElement(new String("03 - Bebidas"));
		segmentos.addElement(new String("04 - Celulose"));
		segmentos.addElement(new String("05 - Cimentos"));
		segmentos.addElement(new String("06 - Siderurgia"));
		segmentos.addElement(new String("07 - Escadas rolantes"));
		segmentos.addElement(new String("08 - Fertilizantes"));
		segmentos.addElement(new String("09 - Fumageira"));
		segmentos.addElement(new String("10 - Fundição"));
		segmentos.addElement(new String("11 - Graneiro"));
		segmentos.addElement(new String("12 - Madeira"));
		segmentos.addElement(new String("13 - Mineração"));
		segmentos.addElement(new String("14 - Químico"));
		segmentos.addElement(new String("15 - Diversos"));
		segmentos.addElement(new String("16 - Revenda"));
		segmentos.addElement(new String("17 - Óleos"));
		segmentos.addElement(new String("18 - Rações"));
		segmentos.addElement(new String("19 - Gás"));
		segmentos.addElement(new String("20 - Usina"));
		return segmentos;
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

	@SuppressWarnings("unchecked")
	public Vector getRepresentadas(){
		Visita consultar = new Visita();
		Vector representadas = new Vector();
		representadas.addElement("--------------------------------");
		representadas.addAll(consultar.consultarRepresentadas());
		return representadas;
	}
	@SuppressWarnings("unchecked")
	public Vector getVendedores(){
		Visita consultar = new Visita();
		Vector vendedores = new Vector();
		vendedores.addElement("--------------------------------");
		vendedores.addAll(consultar.consultarVendedores());
		return vendedores;
	}
}