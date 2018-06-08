package apresentacao;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Vector;

import javax.swing.Box;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;
import java.text.NumberFormat;

import negocios.Venda;

public class FrmCadastroVenda extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7516663306489588589L;

	//--Instanciação do formulário de contato
	FrmCadastroContato formCadastroContato;

	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	JDesktopPane desk = null;

	//--Dados da empresa representada
	private JFormattedTextField txtCnpjRepresentada = null;
	private JComboBox cmbNmRepresentada = null;
	private JTextField txtNmRepresentada = null;

	//--Dados da empresa cliente
	private JFormattedTextField txtCnpjCliente = null;
	private JComboBox cmbNmCliente = null;
	private JTextField txtNmCliente = null;

	//--Dados da empresa representada para guia comissao
	private JFormattedTextField txtCnpjRepresentada2 = null;
	private JComboBox cmbNmRepresentada2 = null;
	private JTextField txtNmRepresentada2 = null;

	//--Dados da empresa cliente para guia comissao
	private JFormattedTextField txtCnpjCliente2 = null;
	private JComboBox cmbNmCliente2 = null;
	private JTextField txtNmCliente2 = null;

	//--Dados da venda
	private JTextField txtNtFiscal = null;
	private JTextField txtNtPedido = null;
	private JFormattedTextField txtDtVenda = null;
	private JFormattedTextField txtNmProduto = null;
	private JTextField txtVlProduto;
	private JComboBox cmbMedida = null;
	private JFormattedTextField txtQtProduto = null;
	private JTextField txtVlAcrescido;
	private JTextField txtVlFinal = null;
	private JFormattedTextField txtDtEntrega = null;

	//--Dados da venda para a guia comissão
	private JFormattedTextField txtNtFiscal2 = null;
	private JFormattedTextField txtDtVenda2 = null;
	private JFormattedTextField txtVlFinal2 = null;
	private JFormattedTextField txtDtEntrega2 = null;

	//--Dados do vendedor
	private JTextField txtVendedor = null;
	private JComboBox cmbVendedores = null;
	private JTextField txtNmVendedor = null;

	private JCheckBox chkComissionado = null;

	//--Dados do vendedor para aguia comissão
	private JTextField txtVendedor2 = null;
	private JComboBox cmbVendedores2 = null;
	private JTextField txtNmVendedor2 = null;

	private JTextArea txtObs = null;

	//--Guia comissão
	private JTextField txtComissao;

	private JButton btnConfirmarComissao = null;

	private JTabbedPane abaPrincipal = null;
	private JPanel abaDados =  null;
	private JPanel abaComissao =  null;

	//Direitos de usuário determinado pelo grupo
	private int permissao;
	private JLabel lblDivisao = null;
	private JButton btnConfirmar = null;
	private JButton btnCancelar = null;
	private JButton btnRelatorio = null;

	//verificador de campos contábeis

	public int getPermissao(){
		return this.permissao;
	}
	//---Ajusta permissão no sistema
	public void ajustaPermissao(){
		if(getPermissao()==1){//Administrador		

		}else if(getPermissao()==2){//Secretaria

		}else if(getPermissao()==3){//Convidado

		}
	}
	public void ajustaDados(String notaFiscal){
		Venda consultar = new Venda();
		consultar.consultar(notaFiscal);
		NumberFormat nf = NumberFormat.getCurrencyInstance();

		double vlProduto = Double.parseDouble(consultar.getTxtVlProduto());
		double vlAcrescido = Double.parseDouble(consultar.getTxtVlAcrescido());
		double vlComissao = Double.parseDouble(consultar.getTxtComissao());
		txtVlProduto.setText(nf.format(vlProduto));
		txtVlAcrescido.setText(nf.format(vlAcrescido));
		txtComissao.setText(nf.format(vlComissao));

		txtVlFinal.setText(consultar.getTxtVlFinal());
		txtCnpjRepresentada.setText(consultar.getTxtCnpjRepresentada());
		txtNmRepresentada.setText(consultar.getCmbNmRepresentada());
		txtCnpjCliente.setText(consultar.getTxtCnpjCliente());
		txtNmCliente.setText(consultar.getCmbNmCliente());
		txtNtFiscal.setText(consultar.getTxtNtFiscal());
		txtNtPedido.setText(consultar.getTxtNtPedido());
		txtDtVenda.setText(consultar.getTxtDtVenda());
		txtNmProduto.setText(consultar.getTxtNmProduto());
		cmbMedida.setSelectedItem((String) consultar.getCmbMedida());
		txtQtProduto.setText(consultar.getTxtQtProduto());
		txtDtEntrega.setText(consultar.getTxtDtEntrega());
		txtVendedor.setText(consultar.getTxtVendedor());
		txtNmVendedor.setText(consultar.getCmbVendedores());
		chkComissionado.setSelected(consultar.getChkComissionado());
		txtObs.setText(consultar.getTxtObs());
		ajustaGuiaComissao();
	}
	private void desabilitaCampos(){
		txtCnpjRepresentada.setEditable(false);
		cmbNmRepresentada.setEnabled(false);
		txtNmRepresentada.setEditable(false);
		txtCnpjCliente.setEditable(false);
		cmbNmCliente.setEnabled(false);
		txtNmCliente.setEditable(false);
		txtNtFiscal.setEditable(false);
		txtNtPedido.setEditable(false);
		txtDtVenda.setEditable(false);
		txtNmProduto.setEditable(false);
		txtVlProduto.setEditable(false);
		cmbMedida.setEnabled(false);
		txtQtProduto.setEditable(false);
		txtVlAcrescido.setEditable(false);
		txtVlFinal.setEditable(false);
		txtDtEntrega.setEditable(false);
		txtVendedor.setEditable(false);
		cmbVendedores.setEnabled(false);
		txtNmVendedor.setEditable(false);
		chkComissionado.setEnabled(false);
		txtObs.setEditable(false);
		btnConfirmar.setEnabled(false);
		if(getPermissao()==1){
			if(getOp()==0){
				cmbNmCliente2.setEnabled(false);
				cmbNmRepresentada2.setEnabled(false);
				cmbVendedores2.setEnabled(false);	
			}			
		}
	}
	private void habilitaCampos(){
		txtCnpjRepresentada.setEditable(true);
		cmbNmRepresentada.setEnabled(true);
		txtNmRepresentada.setEditable(true);
		txtCnpjCliente.setEditable(true);
		cmbNmCliente.setEnabled(true);
		txtNmCliente.setEditable(true);
		txtNtFiscal.setEditable(true);
		txtNtPedido.setEditable(true);
		txtDtVenda.setEditable(true);
		txtNmProduto.setEditable(true);
		txtVlProduto.setEditable(true);
		cmbMedida.setEnabled(true);
		txtQtProduto.setEditable(true);
		txtVlAcrescido.setEditable(true);
		txtVlFinal.setEditable(true);
		txtDtEntrega.setEditable(true);
		txtVendedor.setEditable(true);
		cmbVendedores.setEnabled(true);
		txtNmVendedor.setEditable(true);
		chkComissionado.setEnabled(true);
		txtObs.setEditable(true);
		btnConfirmar.setEnabled(true);
		if(getPermissao()==1){
			if(getOp()==0){
				cmbNmCliente2.setEnabled(true);
				cmbNmRepresentada2.setEnabled(true);
				cmbVendedores2.setEnabled(true);	
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
	private int op;

	public int getOp(){
		return op;
	}
	/**
	 * This is the xxx default constructor
	 */
	public FrmCadastroVenda(JDesktopPane desktop, int permissao, int op, String nota) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		this.op = op;
		initialize();
		txtCnpjCliente.setEditable(false);
		txtCnpjRepresentada.setEditable(false);
		txtVendedor.setEditable(false);
		chkComissionado.setEnabled(false);
		txtVlFinal.setEditable(false);
		txtVlFinal2.setEditable(false);
		if(getOp()==0){//Novo cadastro

		}else{//Exibir dados
			if(getPermissao()==1){
				abaPrincipal.addTab("Comissão", null, abaComissao, null);	
				ajustaDados(nota);
				desabilitaCampos();
				if(txtComissao.getText().equals("R$ 0,00")){
					txtComissao.setEditable(true);	
				}else{
					txtComissao.setEditable(false);
				}							
			}else{
				ajustaDados(nota);
				desabilitaCampos();	
			}
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
			titulo = "Novo cadastro";
		}else{
			titulo = "Venda";
		}
		this.setTitle(titulo);
		this.setResizable(false);

		abaPrincipal = new JTabbedPane();
		abaDados = new JPanel();
		abaComissao = new JPanel();

		//--Dados da empresa representada
		JLabel lblRepresentada = new JLabel("Representada");
		JLabel lblCnpjRepresentada = new JLabel("CNPJ");
		txtCnpjRepresentada = new JFormattedTextField(setMascara("##.###.###/####-##"));
		JLabel lblNmRepresentada = new JLabel("Nome da empresa");
		cmbNmRepresentada = new JComboBox(getRepresentadas());
		txtNmRepresentada = new JTextField("",56);
		cmbNmRepresentada.setPreferredSize(new Dimension(450, 20));

		//--Dados da empresa cliente
		JLabel lblCliente = new JLabel("Comprador");
		JLabel lblCnpjCliente = new JLabel("CNPJ");
		txtCnpjCliente = new JFormattedTextField(setMascara("##.###.###/####-##"));
		JLabel lblNmCliente = new JLabel("Nome da empresa");
		cmbNmCliente = new JComboBox(getClientes());
		txtNmCliente = new JTextField("",40);
		cmbNmCliente.setPreferredSize(new Dimension(450, 20));

		//--Dados da empresa representada para guia comissao
		JLabel lblRepresentada2 = new JLabel("Representada");
		JLabel lblCnpjRepresentada2 = new JLabel("CNPJ");
		txtCnpjRepresentada2 = new JFormattedTextField(setMascara("##.###.###/####-##"));
		JLabel lblNmRepresentada2 = new JLabel("Nome da empresa");
		cmbNmRepresentada2 = new JComboBox(getRepresentadas());
		txtNmRepresentada2 = new JTextField("",56);
		cmbNmRepresentada2.setPreferredSize(new Dimension(450, 20));

		//--Dados da empresa cliente para guia comissao
		JLabel lblCliente2 = new JLabel("Comprador");
		JLabel lblCnpjCliente2 = new JLabel("CNPJ");
		txtCnpjCliente2 = new JFormattedTextField(setMascara("##.###.###/####-##"));
		JLabel lblNmCliente2 = new JLabel("Nome da empresa");
		cmbNmCliente2 = new JComboBox(getClientes());
		txtNmCliente2 = new JTextField("",40);
		cmbNmCliente2.setPreferredSize(new Dimension(450, 20));

		//--Dados da venda
		JLabel lblVenda = new JLabel("Venda realizada");
		JLabel lblNtFiscal = new JLabel("Nota fiscal");
		txtNtFiscal = new JTextField("",10);
		JLabel lblNtPedido = new JLabel("Pedido");
		txtNtPedido = new JTextField("",10);
		JLabel lblDtVenda = new JLabel("Data da venda");
		txtDtVenda = new JFormattedTextField(setMascara("##/##/####"));
		JLabel lblNmProduto = new JLabel("Produto");
		txtNmProduto = new JFormattedTextField(setMascara("*********************************************"));
		JLabel lblVlProduto = new JLabel("Valor do produto");
		//txtVlProduto = new JTextField(moneyFormat.format(DEFAULT_AMOUNT), 10);
		txtVlProduto = new JTextField();
		JLabel lblMedida = new JLabel("Unidade de medida");
		cmbMedida = new JComboBox(getUnidades());
		JLabel lblQtProduto = new JLabel("Quantidade");
		txtQtProduto = new JFormattedTextField(setMascara("######"));
		JLabel lblVlAcrescido = new JLabel("Valor à acrescentar");
		txtVlAcrescido = new JTextField();
		JLabel lblVlFinal = new JLabel("Valor final da venda");
		txtVlFinal = new JTextField("",10);
		JLabel lblDtEntrega = new JLabel("Data da entrega");
		txtDtEntrega = new JFormattedTextField(setMascara("##/##/####"));

		//--Dados da venda para a guia comissão
		JLabel lblVenda2 = new JLabel("Venda realizada");
		JLabel lblNtFiscal2 = new JLabel("Nota fiscal");
		txtNtFiscal2 = new JFormattedTextField(setMascara("********************"));
		JLabel lblDtVenda2 = new JLabel("Data da venda");
		txtDtVenda2 = new JFormattedTextField(setMascara("##/##/####"));
		JLabel lblVlFinal2 = new JLabel("Valor final da venda");
		txtVlFinal2 = new JFormattedTextField(setMascara("********************"));
		JLabel lblDtEntrega2 = new JLabel("Data da entrega");
		txtDtEntrega2 = new JFormattedTextField(setMascara("##/##/####"));

		//--Dados do vendedor
		JLabel lblVendedor = new JLabel("Vendedor");
		JLabel lblMatricula = new JLabel("Matrícula");
		txtVendedor = new JTextField("",10);
		JLabel lblNmVendedor = new JLabel("Nome do vendedor");
		cmbVendedores = new JComboBox(getVendedores());
		txtNmVendedor = new JTextField("",40);
		cmbVendedores.setPreferredSize(new Dimension(450, 20));
		chkComissionado = new JCheckBox("Comissionado");

		//--Dados do vendedor para aguia comissão
		JLabel lblVendedor2 = new JLabel("Vendedor");
		JLabel lblMatricula2 = new JLabel("Matrícula");
		txtVendedor2 = new JTextField("",10);
		JLabel lblNmVendedor2 = new JLabel("Nome do vendedor");
		cmbVendedores2 = new JComboBox(getVendedores());
		txtNmVendedor2 = new JTextField("",40);
		cmbVendedores2.setPreferredSize(new Dimension(450, 20));

		JLabel lblObs = new JLabel("Observações");
		txtObs = new JTextArea();
		JScrollPane jspTxtObs = new JScrollPane(txtObs);
		jspTxtObs.setPreferredSize(new Dimension(40, 100));

		//--Guia comissão
		JLabel lblComissao = new JLabel("Comissão");
		txtComissao = new JTextField();

		btnConfirmarComissao = new JButton("Confirmar");

		lblDivisao = new JLabel("___________________________________________________________________________________________________________________");
		btnRelatorio = new JButton("Relatório");
		btnConfirmar = new JButton("Confirmar");
		btnCancelar = new JButton("Cancelar");

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

		linhaUm.add(lblCliente);
		linhaDois.add(lblCnpjCliente);
		linhaDois.add(txtCnpjCliente);
		linhaDois.add(Box.createHorizontalStrut(5));
		linhaDois.add(lblNmCliente);

		linhaTres.add(lblRepresentada);
		linhaQuatro.add(lblCnpjRepresentada);
		linhaQuatro.add(txtCnpjRepresentada);
		linhaQuatro.add(Box.createHorizontalStrut(5));
		linhaQuatro.add(lblNmRepresentada);


		linhaCinco.add(lblVenda);
		linhaSeis.add(lblNtPedido);
		linhaSeis.add(txtNtPedido);
		linhaSeis.add(Box.createHorizontalStrut(5));
		linhaSeis.add(lblNtFiscal);
		linhaSeis.add(txtNtFiscal);
		linhaSeis.add(Box.createHorizontalStrut(5));		
		linhaSeis.add(lblDtVenda);
		linhaSeis.add(txtDtVenda);
		linhaSeis.add(Box.createHorizontalStrut(5));	
		linhaSeis.add(lblDtEntrega);
		linhaSeis.add(txtDtEntrega);	
		linhaSete.add(lblNmProduto);
		linhaSete.add(txtNmProduto);
		linhaOito.add(lblVlProduto);
		linhaOito.add(txtVlProduto);
		linhaOito.add(Box.createHorizontalStrut(5));	
		linhaOito.add(lblMedida);
		linhaOito.add(cmbMedida);
		linhaOito.add(Box.createHorizontalStrut(5));	
		linhaOito.add(lblQtProduto);
		linhaOito.add(txtQtProduto);
		linhaOito.add(Box.createHorizontalStrut(5));	
		linhaOito.add(lblVlAcrescido);
		linhaOito.add(txtVlAcrescido);
		linhaNove.add(lblVlFinal);
		linhaNove.add(txtVlFinal);
		linhaNove.add(Box.createHorizontalStrut(5));	
		linhaNove.add(chkComissionado);
		linhaDez.add(lblObs);
		linhaOnze.add(jspTxtObs);

		Box linhaA = Box.createHorizontalBox();
		Box linhaB = Box.createHorizontalBox();
		Box linhaC = Box.createHorizontalBox();
		Box linhaD = Box.createHorizontalBox();

		Box linhaG = Box.createHorizontalBox();
		Box linhaH = Box.createHorizontalBox();
		Box linhaI = Box.createHorizontalBox();
		Box linhaJ = Box.createHorizontalBox();
		Box linhaL = Box.createHorizontalBox();
		Box linhaM = Box.createHorizontalBox();
		Box linhaN = Box.createHorizontalBox();
		Box linhaO = Box.createHorizontalBox();
		Box linhaP = Box.createHorizontalBox();

		linhaA.add(lblVendedor);
		linhaB.add(lblMatricula);
		linhaB.add(txtVendedor);
		linhaB.add(Box.createHorizontalStrut(5));
		linhaB.add(lblNmVendedor);

		linhaL.add(lblVendedor2);
		linhaM.add(lblMatricula2);
		linhaM.add(txtVendedor2);
		linhaM.add(Box.createHorizontalStrut(5));
		linhaM.add(lblNmVendedor2);


		linhaN.add(lblNtFiscal2);
		linhaN.add(txtNtFiscal2);
		linhaN.add(Box.createHorizontalStrut(5));
		linhaN.add(lblDtVenda2);
		linhaN.add(txtDtVenda2);
		linhaN.add(Box.createHorizontalStrut(5));
		linhaN.add(lblDtEntrega2);
		linhaN.add(txtDtEntrega2);

		linhaO.add(lblVlFinal2);
		linhaO.add(txtVlFinal2);
		linhaO.add(Box.createHorizontalStrut(5));
		linhaO.add(lblComissao);
		linhaO.add(txtComissao);
		linhaO.add(Box.createHorizontalStrut(5));
		linhaO.add(btnConfirmarComissao);	

		linhaP.add(lblVenda2);

		linhaG.add(lblCliente2);
		linhaH.add(lblCnpjCliente2);
		linhaH.add(txtCnpjCliente2);
		linhaH.add(Box.createHorizontalStrut(5));
		linhaH.add(lblNmCliente2);

		linhaI.add(lblRepresentada2);
		linhaJ.add(lblCnpjRepresentada2);
		linhaJ.add(txtCnpjRepresentada2);
		linhaJ.add(Box.createHorizontalStrut(5));
		linhaJ.add(lblNmRepresentada2);

		Box comissao = Box.createVerticalBox();

		if(getOp()==0){
			linhaDois.add(cmbNmCliente);
			linhaQuatro.add(cmbNmRepresentada);
			linhaB.add(cmbVendedores);
			linhaM.add(cmbVendedores2);
			linhaH.add(cmbNmCliente2);
			linhaJ.add(cmbNmRepresentada2);
		}else{
			linhaDois.add(txtNmCliente);
			linhaQuatro.add(txtNmRepresentada);
			linhaB.add(txtNmVendedor);
			linhaM.add(txtNmVendedor2);
			linhaH.add(txtNmCliente2);
			linhaJ.add(txtNmRepresentada2);
		}

		comissao.add(linhaG);
		comissao.add(Box.createVerticalStrut(5));		
		comissao.add(linhaH);
		comissao.add(Box.createVerticalStrut(20));
		comissao.add(linhaI);
		comissao.add(Box.createVerticalStrut(5));
		comissao.add(linhaJ);
		comissao.add(Box.createVerticalStrut(20));
		comissao.add(linhaL);	
		comissao.add(Box.createVerticalStrut(5));
		comissao.add(linhaM);
		comissao.add(Box.createVerticalStrut(20));
		comissao.add(linhaP);
		comissao.add(Box.createVerticalStrut(5));
		comissao.add(linhaN);
		comissao.add(Box.createVerticalStrut(5));
		comissao.add(linhaO);


		linhaDoze.add(lblDivisao);
		linhaTreze.add(btnRelatorio);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnConfirmar);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnCancelar);

		Box linhas = Box.createVerticalBox();
		linhas.add(linhaUm);
		linhas.add(Box.createVerticalStrut(5));		
		linhas.add(linhaDois);
		linhas.add(Box.createVerticalStrut(20));
		linhas.add(linhaTres);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaQuatro);
		linhas.add(Box.createVerticalStrut(20));
		linhas.add(linhaA);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaB);
		linhas.add(linhaC);
		linhas.add(linhaD);
		linhas.add(Box.createVerticalStrut(20));
		linhas.add(linhaCinco);	
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaSeis);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaSete);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaOito);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaNove);		
		linhas.add(Box.createVerticalStrut(10));
		linhas.add(linhaDez);
		linhas.add(linhaOnze);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaOnze);

		Box principal = Box.createVerticalBox();						

		abaDados.add(linhas);
		abaComissao.add(comissao);

		abaPrincipal.addTab("Dados da venda", null, abaDados, null);


		principal.add(abaPrincipal);
		principal.add(linhaDoze);
		principal.add(Box.createVerticalStrut(10));
		principal.add(linhaTreze);

		//--Ações 
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		btnConfirmarComissao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}});
		jContentPane = new JPanel();
		jContentPane.add(principal);
		getContentPane().add(jContentPane);
		pack();

		//------------Ações
		txtVlProduto.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				try{
					double valor = Double.parseDouble(txtVlProduto.getText().replace(".",",").replace(",","."));
					NumberFormat nf = NumberFormat.getCurrencyInstance();
					String valorFormatado = nf.format(valor);
					txtVlProduto.setText(valorFormatado);
					if(txtVlProduto.getText().trim().equals("") || txtVlProduto.equals("R$ 0,00")){
						txtVlProduto.setText("");
						txtVlProduto.grabFocus();
					}
				}catch(Exception ex){
					txtVlProduto.setText("");
					txtVlProduto.grabFocus();					
				}}
		});

		btnConfirmarComissao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Venda cadastrar = new Venda();
				cadastrar.ajustaDados(txtCnpjRepresentada.getText(), txtNmRepresentada2.getText(), txtCnpjCliente.getText(), txtNmCliente2.getText(), txtNtFiscal.getText(), txtDtVenda.getText(), txtNmProduto.getText(), txtVlProduto.getText(), (String) cmbMedida.getSelectedItem(), txtQtProduto.getText(), txtVlAcrescido.getText(), txtVlFinal.getText(), txtDtEntrega.getText(), txtVendedor.getText(), txtNmVendedor2.getText(), txtObs.getText(), txtNtPedido.getText());
				if(cadastrar.validaDados()>0){
					JOptionPane.showMessageDialog(null, "Encontrada inconsistências nos dados informados:\n"+cadastrar.getMsgStatus(),"Erro",2);
				}else{
					if(op==1){
						if (JOptionPane.showConfirmDialog(new JFrame(),
								"Deseja alterar o valor da comissão desta venda?\n\nNota fiscal nº "+txtNtFiscal.getText(), "Confirmação",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
							cadastrar.ajustaComissao(txtComissao.getText().trim());
							cadastrar.alterarComissao();
							txtComissao.setEditable(false);
							chkComissionado.setSelected(true);							
						}	
					}else{
						cadastrar.ajustaComissao(txtComissao.getText().trim());
						cadastrar.cadastrarComissao();	
						txtComissao.setEditable(false);
						chkComissionado.setSelected(true);
					}				
				}
			}});
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Venda cadastrar = new Venda();
					cadastrar.ajustaDados(txtCnpjRepresentada.getText(), (String) cmbNmRepresentada.getSelectedItem(), txtCnpjCliente.getText(), (String) cmbNmCliente.getSelectedItem(), txtNtFiscal.getText(), txtDtVenda.getText(), txtNmProduto.getText(), txtVlProduto.getText(), (String) cmbMedida.getSelectedItem(), txtQtProduto.getText(), txtVlAcrescido.getText(), txtVlFinal.getText(), txtDtEntrega.getText(), txtVendedor.getText(), (String) cmbVendedores.getSelectedItem(), txtObs.getText(), txtNtPedido.getText());
					if(cadastrar.validaDados()>0){
						JOptionPane.showMessageDialog(null, "Encontrada inconsistências nos dados informados:\n"+cadastrar.getMsgStatus(),"Erro",2);
					}else{
						if(cadastrar.verificaExiste()==0){
							if (JOptionPane.showConfirmDialog(new JFrame(),
									"Deseja concluir o cadastro?\n\nOs dados cadastrados de venda não poderam mais serem alterados.", "Confirmação",
									JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
								if(getPermissao()==1){
									abaPrincipal.addTab("Comissão", null, abaComissao, null);	
									ajustaGuiaComissao();
									txtComissao.setEditable(true);
								}
								cadastrar.cadastrar();	
								desabilitaCampos();
							}												
						}else{
							JOptionPane.showMessageDialog(null, "Venda ja está cadastrada.\n\nNota fiscal nº "+txtNtFiscal.getText(),"Erro",2);				
						}
					}
				}catch(Exception er){
					JOptionPane.showMessageDialog(null, "Valor final da venda fora do limite suportado","Erro",2);
					habilitaCampos();
				}				
			}});
		btnRelatorio.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				Venda gerar = new Venda();
				gerar.gerarRelatorio(getPermissao(), (String) cmbNmCliente.getSelectedItem(), (String) cmbNmRepresentada.getSelectedItem(), (String) cmbVendedores.getSelectedItem(), "/  /", "/  /", txtNtFiscal.getText().trim()).show();
			}});
		txtDtVenda.setInputVerifier(new InputVerifier() {
			public boolean verify(JComponent component) {
				if(((JTextComponent) component).getText().trim().equals("/  /")){
					txtDtVenda.setText("");
					return false;
				}else{
					Venda consultar = new Venda();
					if(consultar.validaData(txtDtVenda.getText())>0){
						txtDtVenda.setText("");
						JOptionPane.showMessageDialog(null,"Data de venda inválida","Erro",2);
						return false;
					}else{
						return ((JFormattedTextField) txtDtVenda).isEditValid();
					}
				}				
			}
		});
		txtDtEntrega.setInputVerifier(new InputVerifier() {
			public boolean verify(JComponent component) {
				if(((JTextComponent) component).getText().trim().equals("/  /")){
					txtDtEntrega.setText("");
					JOptionPane.showMessageDialog(null,"Data de entrega inválida","Erro",2);
					return false;
				}else{
					Venda consultar = new Venda();
					if(consultar.validaData(txtDtEntrega.getText())>0){
						txtDtEntrega.setText("");
						JOptionPane.showMessageDialog(null,"Data de entrega inválida","Erro",2);
						return false;
					}else{
						if(consultar.validaDataEntrega(txtDtVenda.getText(), txtDtEntrega.getText())>0){
							txtDtEntrega.setText("");
							JOptionPane.showMessageDialog(null,"Data de entrega inválida","Erro",2);
							return false;	
						}else{
							return ((JFormattedTextField) txtDtVenda).isEditValid();
						}
					}
				}				
			}
		});
		txtQtProduto.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				try{
					if(txtQtProduto.getText().trim().equals("") || txtQtProduto.getText().trim().equals("000000")){
						txtQtProduto.grabFocus();
					}	
				}catch(Exception ex){
					txtQtProduto.setText("000001");
				}
			}
		});
		txtVlAcrescido.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				try{
					try{
						double valor = Double.parseDouble(txtVlAcrescido.getText().replace(".",",").replace(",","."));
						NumberFormat nf = NumberFormat.getCurrencyInstance();
						String valorFormatado = nf.format(valor);
						txtVlAcrescido.setText(valorFormatado);	
					}catch(Exception ex){
						txtVlAcrescido.setText("R$ 0,00");
					};
					double vl = Double.parseDouble(txtVlProduto.getText().replace(".","").replace(",",".").substring(3));
					//double vl = Double.parseDouble(txtVlProduto.getText().replace(".",",").replace(",",".").substring(3));
					double qt = Double.parseDouble(txtQtProduto.getText().trim());
					/*if(txtQtProduto.getText().trim().equals("")){
					txtQtProduto.setText("000000");
					}else{
						qt = Double.parseDouble(txtQtProduto.getText().trim());	
					}*/					
					double vlAcres = Double.parseDouble(txtVlAcrescido.getText().replace(".","").replace(",",".").substring(3));
					double total = (qt * vl)+vlAcres;
					BigDecimal bd = new BigDecimal (total);
					bd.add (new BigDecimal (total));
					DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(LOCAL));
					String s = df.format(bd);
					txtVlFinal.setText("R$ "+s);	
				}catch(Exception ex){
					//txtVlProduto.grabFocus();
				}}
		});
		txtComissao.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				try{
					double valor = Double.parseDouble(txtComissao.getText().replace(".",",").replace(",","."));
					NumberFormat nf = NumberFormat.getCurrencyInstance();
					String valorFormatado = nf.format(valor);
					txtComissao.setText(valorFormatado);	
				}catch(Exception ex){
					txtComissao.setText("");
					//txtComissao.grabFocus();
				}}
		});

		//-Combobox
		cmbNmCliente.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED){
					Venda consultar = new Venda();
					txtCnpjCliente.setText(consultar.cnpjCliente((String) cmbNmCliente.getSelectedItem()));
				}			
			}
		});
		cmbNmRepresentada.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED){
					Venda consultar = new Venda();
					txtCnpjRepresentada.setText(consultar.cnpjRepresentada((String) cmbNmRepresentada.getSelectedItem()));
				}				
			}
		});
		cmbVendedores.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED){
					Venda consultar = new Venda();
					txtVendedor.setText(consultar.codFuncionario((String) cmbVendedores.getSelectedItem()));
				}				
			}
		});
	}
	public void ajustaGuiaComissao(){
		txtCnpjRepresentada2.setText(txtCnpjRepresentada.getText());
		txtNmRepresentada2.setText(txtNmRepresentada.getText());
		txtCnpjCliente2.setText(txtCnpjCliente.getText());
		txtNmCliente2.setText(txtNmCliente.getText());
		txtNtFiscal2.setText(txtNtFiscal.getText());
		txtDtVenda2.setText(txtDtVenda.getText());
		txtVlFinal2.setText(txtVlFinal.getText());
		txtDtEntrega2.setText(txtDtEntrega.getText());
		txtVendedor2.setText(txtVendedor.getText());
		txtNmVendedor2.setText(txtNmVendedor.getText());
		cmbNmCliente2.setSelectedItem(cmbNmCliente.getSelectedItem());
		cmbNmRepresentada2.setSelectedItem(cmbNmRepresentada.getSelectedItem());
		cmbVendedores2.setSelectedItem(cmbVendedores.getSelectedItem());
		txtCnpjRepresentada2.setEditable(false);
		cmbNmCliente.setEditable(false);
		cmbNmCliente2.setEditable(false);
		txtNmRepresentada2.setEditable(false);
		cmbNmRepresentada2.setEditable(false);
		txtCnpjCliente2.setEditable(false);
		txtNmCliente2.setEditable(false);
		txtNtFiscal2.setEditable(false);
		txtDtVenda2.setEditable(false);
		txtVlFinal2.setEditable(false);
		txtDtEntrega2.setEditable(false);
		txtVendedor2.setEditable(false);
		txtNmVendedor2.setEditable(false);
		cmbVendedores.setEditable(false);
		cmbVendedores2.setEditable(false);
	}
	@SuppressWarnings("unchecked")
	public Vector getRepresentadas(){
		Venda consultar = new Venda();
		Vector representadas = new Vector();
		representadas.addElement("--------------------------------");
		representadas.addAll(consultar.consultarRepresentadas());
		return representadas;
	}
	@SuppressWarnings("unchecked")
	public Vector getVendedores(){
		Venda consultar = new Venda();
		Vector vendedores = new Vector();
		vendedores.addElement("--------------------------------");
		vendedores.addAll(consultar.consultarVendedores());
		return vendedores;
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
	@SuppressWarnings("unchecked")
	public Vector getClientes(){
		Venda consultar = new Venda();
		Vector clientes = new Vector();
		clientes.addElement("--------------------------------");
		clientes.addAll(consultar.consultarClientes());
		return clientes;
	}
	public DefaultComboBoxModel getUnidades(){
		DefaultComboBoxModel segmentos = new DefaultComboBoxModel();
		segmentos.addElement(new String("----------"));
		segmentos.addElement(new String("Metro"));
		segmentos.addElement(new String("Quilos"));
		segmentos.addElement(new String("Conjunto"));
		segmentos.addElement(new String("Peça"));
		return segmentos;
	}
	private static final Locale LOCAL = new Locale("pt","BR");
}  //  @jve:decl-index=0:visual-constraint="10,10"
