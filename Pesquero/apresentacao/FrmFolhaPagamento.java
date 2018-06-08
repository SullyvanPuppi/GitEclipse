package apresentacao;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import negocios.FolhaPagamento;


public class FrmFolhaPagamento extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1185948339493910800L;
	private JPanel jContentPane = null;
	//Criação da área de trabalho do formulário
	JDesktopPane desk = null;

	//Direitos de usuário determinado pelo grupo
	private int permissao;

	private JLabel lblFuncionario = null;
	private JTextField txtFuncionario = null;
	private JLabel lblNmFuncionario = null;
	private JTextField txtNmFuncionario = null;
	private JLabel lblMes = null;
	private JFormattedTextField txtMes = null;
	private JLabel lblDtFechamento = null;
	private JFormattedTextField txtDtFechamento = null;
	private JLabel lblQtHorasExtras = null;
	private JFormattedTextField txtQtHorasExtras = null;
	private JTextField txtVlHorasExtras = null;
	private JLabel lblSalarioBase = null;
	private JFormattedTextField txtSalarioBase = null;
	private JLabel lblTransporte = null;
	private JFormattedTextField txtVlTransporte = null;
	private JLabel lblTransporteDes = null;
	private JTextField txtVlTransporteDes = null;
	private JLabel lblQtDependentes = null;
	private JFormattedTextField txtQtdependentes = null;
	private JLabel lblSalarioFamilia = null;
	private JTextField txtSalarioFamilia = null;
	private JLabel lblQtFaltas = null;
	private JFormattedTextField txtQtFaltas = null;
	private JLabel lblVlFaltas = null;
	private JTextField txtVlFaltas = null;
	private JLabel lblVlInss = null;
	private JTextField txtVlInss = null;
	private JLabel lblVlIr = null;
	private JTextField txtVlIr = null;
	private JLabel lblVlFgts = null;
	private JTextField txtVlFgts = null;
	private JLabel lblVlRefeicao = null;
	private JTextField txtVlRefeicao = null;
	private JLabel lblVlSalarioLiquido = null;
	private JTextField txtVlSalarioLiquido = null;
	private JLabel lblVlTotalVencimentos = null;
	private JTextField txtVlTotalVencimentos = null;
	private JLabel lblVlTotalDescontos = null;
	private JTextField txtVlTotalDescontos = null;
	
	
	private JCheckBox chkConsistido = null;

	//--Itens da guia comissões
	private JLabel lblNotaFiscal = null;
	private JTextField txtNotaFiscal = null;
	private JLabel lblCliente = null;
	private JTextField txtCliente = null;
	private JLabel lblRepresentada = null;
	private JTextField txtRepresentada = null;
	private JLabel lblProduto = null;
	private JTextField txtProduto = null;
	private JLabel lblValorVenda = null;
	private JTextField txtValorVenda = null;
	private JLabel lblDtVenda = null;
	private JTextField txtVenda = null;	
	

	private JTabbedPane abaPrincipal = null;
	private JPanel abaDados = null;
	private JPanel abaComissao = null;

	private JLabel lblDivisao = null;
	private JButton btnConfirmar = null;
	private JButton btnCancelar = null;
	private JButton btnAlterar = null;
	private JButton btnRelatorio = null;
	private JButton btnDemonstrativo = null;

	private JButton btnNavPri = null;
	private JButton btnNavAnt = null;
	private JButton btnNavProx = null;
	private JButton btnNavUlt = null;
	private JButton btnConsistir = null;

	private JTable tbVendas = null;
	private JScrollPane jspVendas = null;

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
	public void desabilitaCampos(String cdFuncionario){
		txtVlHorasExtras.setEditable(false);
		txtSalarioFamilia.setEditable(false);
		txtVlInss.setEditable(false);
		txtVlIr.setEditable(false);
		txtVlFgts.setEditable(false);
		txtVlRefeicao.setEditable(false);
		txtVlSalarioLiquido.setEditable(false);
		txtVlTransporteDes.setEditable(false);
		txtVlFaltas.setEditable(false);
		txtVlTotalDescontos.setEditable(false);
		txtVlTotalVencimentos.setEditable(false);
		FolhaPagamento consultar = new FolhaPagamento();
		if(consultar.direitoTransporte(cdFuncionario)>0){
			txtVlTransporte.setEditable(true);
		}else{
			txtVlTransporte.setEditable(false);	
			txtVlTransporte.setText("000,00");
			txtVlTransporteDes.setText("R$ 000,00");
		}
		txtVlRefeicao.setText(vlRefeicao(cdFuncionario));
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
	public FrmFolhaPagamento(JDesktopPane desktop, int permissao, String data) {
		super();
		this.desk = desktop;
		this.permissao = permissao;
		initialize(data);
		txtFuncionario.setText("func001");
		desabilitaCampos(txtFuncionario.getText().trim());
	}
	private int qtFuncionarios;
	public int getQtFuncionarios(){
		return qtFuncionarios;
	}
	private int consistidos = 0;
	private String msgStatus = "";
	public String getMsgStatus(){
		return msgStatus;
	}
	public int contConsistidos(){
		if(txtSalarioBase.getText().trim().equals("R$    ,")){
			this.msgStatus +="\nSalário base informado inválido";
			this.consistidos = 1;
		}
		if(txtQtHorasExtras.getText().trim().equals("")){
			this.msgStatus +="\nQuantidade de horas extras informado inválido";
			this.consistidos = 1;
		}
		if(txtQtdependentes.getText().trim().equals("")){
			this.msgStatus +="\nQuantidade de dependentes informado inválido";
			this.consistidos = 1;
		}
		if(txtQtFaltas.getText().trim().equals("")){
			this.msgStatus +="\nQuantidade de faltas informado inválido";
			this.consistidos = 1;
		}
		return consistidos;
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize(String data) {
		this.setTitle("Folha de pagamento: Referente à: "+data);
		this.setModal(true);
		this.setResizable(false);

		abaPrincipal = new JTabbedPane();
		abaDados = new JPanel();
		abaComissao = new JPanel();

		lblFuncionario = new JLabel("Matrícula");
		txtFuncionario = new JTextField("",10);
		lblNmFuncionario = new JLabel("Nome do funcionário");
		txtNmFuncionario = new JTextField("",45);
		lblMes = new JLabel("Mês de referência");
		txtMes = new JFormattedTextField(setMascara("##/####"));
		lblDtFechamento = new JLabel("Data de fechamento");
		txtDtFechamento = new JFormattedTextField(setMascara("##/##/####"));
		lblQtHorasExtras = new JLabel("Hora(s) extra(s)");
		txtQtHorasExtras = new JFormattedTextField(setMascara("##"));
		JLabel lblVlHorasExtras = new JLabel("Valor pago");
		txtVlHorasExtras = new JTextField("",7);
		lblSalarioBase = new JLabel("Salário base");
		txtSalarioBase = new JFormattedTextField(setMascara("R$####,##"));
		lblTransporte = new JLabel("Vale transporte");
		txtVlTransporte = new JFormattedTextField(setMascara("R$###,##"));
		lblTransporteDes = new JLabel("Descontado");
		txtVlTransporteDes = new JTextField("", 10);
		lblQtDependentes = new JLabel("Quantidade de filhos");
		txtQtdependentes = new JFormattedTextField(setMascara("##"));
		lblSalarioFamilia = new JLabel("Salário família");
		txtSalarioFamilia = new JTextField("",10);
		lblQtFaltas = new JLabel("Falta(s)");
		txtQtFaltas = new JFormattedTextField(setMascara("##"));
		lblVlFaltas = new JLabel("Descontado");
		txtVlFaltas = new JTextField("",10);
		lblVlInss = new JLabel("INSS");
		txtVlInss = new JTextField("",10);
		lblVlIr = new JLabel("IRRF");
		txtVlIr = new JTextField("",10);
		lblVlFgts = new JLabel("FGTS");
		txtVlFgts = new JTextField("",10);
		lblVlRefeicao = new JLabel("Vale refeição");
		txtVlRefeicao = new JTextField("",10);
		lblVlSalarioLiquido = new JLabel("Salário liquído");
		txtVlSalarioLiquido = new JTextField("",18);
		lblVlTotalDescontos = new JLabel("Total de descontos");
		txtVlTotalDescontos = new JTextField("",18);
		lblVlTotalVencimentos = new JLabel("Total de vencimentos");
		txtVlTotalVencimentos = new JTextField("",18);
		chkConsistido = new JCheckBox("Consistente");
		
		JLabel lblEmitido = new JLabel("Emitido");
		JCheckBox chkEmitido = new JCheckBox();
		
		btnNavPri = new JButton("|<");
		btnNavPri.setToolTipText("Primeiro registro");
		btnNavAnt = new JButton("<<");
		btnNavAnt.setToolTipText("Registro anterior");
		btnNavProx = new JButton(">>");
		btnNavProx.setToolTipText("Próximo registro");
		btnNavUlt = new JButton(">|");
		btnNavUlt.setToolTipText("Último registro");
		btnConsistir = new JButton("Consistir");
		btnDemonstrativo = new JButton("Demonstrativo");
		btnDemonstrativo.setToolTipText("Demonstrativo de pagamento deste funcionário");

		lblDivisao = new JLabel("__________________________________________________________________________________________________________________________");
		btnRelatorio = new JButton("Relatório");
		btnRelatorio.setToolTipText("Gerar relatório com parâmetros selecionados");
		btnAlterar = new JButton("Abrir");
		btnAlterar.setToolTipText("Exibir item selecionado");
		btnConfirmar = new JButton("Novo");
		btnConfirmar.setToolTipText("Iniciar novo cadastro");
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Cancelar registro e fechar");

		lblNotaFiscal = new JLabel("Nota fiscal");
		txtNotaFiscal = new JTextField("",18);
		lblCliente = new JLabel("Cliente");
		txtCliente = new JTextField("",45);
		lblRepresentada = new JLabel("Representada");
		txtRepresentada = new JTextField("",45);
		lblProduto = new JLabel("Produto vendido");
		txtProduto = new JTextField("",26);
		lblValorVenda = new JLabel("Valor da venda");
		txtValorVenda = new JTextField("",14);
		lblDtVenda = new JLabel("Data da venda");
		txtVenda = new JTextField("",10);

		tbVendas = new JTable();
		jspVendas = new JScrollPane(tbVendas);
		jspVendas.setPreferredSize(new Dimension(400, 200));

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

		Box linhaA = Box.createHorizontalBox();
		Box linhaB = Box.createHorizontalBox();
		Box linhaC = Box.createHorizontalBox();
		Box linhaD = Box.createHorizontalBox();
		Box linhaE = Box.createHorizontalBox();

		linhaUm.add(lblMes);
		linhaUm.add(txtMes);
		linhaUm.add(Box.createHorizontalStrut(20));
		linhaUm.add(lblDtFechamento);
		linhaUm.add(txtDtFechamento);
		linhaUm.add(Box.createHorizontalStrut(20));
		linhaUm.add(lblEmitido);
		linhaUm.add(chkEmitido);
		linhaDois.add(lblFuncionario);
		linhaDois.add(txtFuncionario);
		linhaDois.add(Box.createHorizontalStrut(5));
		linhaDois.add(lblNmFuncionario);
		linhaDois.add(txtNmFuncionario);
		linhaTres.add(lblSalarioBase);
		linhaTres.add(txtSalarioBase);
		linhaTres.add(Box.createHorizontalStrut(5));
		linhaTres.add(lblQtHorasExtras);
		linhaTres.add(txtQtHorasExtras);
		linhaTres.add(Box.createHorizontalStrut(5));
		linhaTres.add(lblVlHorasExtras);
		linhaTres.add(txtVlHorasExtras);
		linhaTres.add(Box.createHorizontalStrut(5));
		linhaTres.add(lblTransporte);
		linhaTres.add(txtVlTransporte);
		linhaTres.add(Box.createHorizontalStrut(5));
		linhaTres.add(lblTransporteDes);
		linhaTres.add(txtVlTransporteDes);
		linhaQuatro.add(lblQtDependentes);
		linhaQuatro.add(txtQtdependentes);
		linhaQuatro.add(Box.createHorizontalStrut(5));
		linhaQuatro.add(lblSalarioFamilia);
		linhaQuatro.add(txtSalarioFamilia);
		linhaQuatro.add(Box.createHorizontalStrut(5));
		linhaQuatro.add(lblQtFaltas);
		linhaQuatro.add(txtQtFaltas);
		linhaQuatro.add(Box.createHorizontalStrut(5));
		linhaQuatro.add(lblVlFaltas);
		linhaQuatro.add(txtVlFaltas);
		linhaQuatro.add(Box.createHorizontalStrut(5));		
		linhaCinco.add(lblVlInss);
		linhaCinco.add(txtVlInss);
		linhaCinco.add(Box.createHorizontalStrut(5));
		linhaCinco.add(lblVlIr);
		linhaCinco.add(txtVlIr);
		linhaCinco.add(Box.createHorizontalStrut(5));
		linhaCinco.add(lblVlFgts);
		linhaCinco.add(txtVlFgts);
		linhaCinco.add(Box.createHorizontalStrut(5));
		linhaCinco.add(lblVlRefeicao);
		linhaCinco.add(txtVlRefeicao);
		
		linhaOito.add(lblVlTotalDescontos);
		linhaOito.add(txtVlTotalDescontos);
		linhaOito.add(Box.createHorizontalStrut(5));
		linhaOito.add(lblVlTotalVencimentos);
		linhaOito.add(txtVlTotalVencimentos);
		linhaOito.add(Box.createHorizontalStrut(5));
		linhaOito.add(lblVlSalarioLiquido);
		linhaOito.add(txtVlSalarioLiquido);
		
		linhaNove.add(chkConsistido);

		linhaSeis.add(btnNavPri);
		linhaSeis.add(Box.createHorizontalStrut(5));
		linhaSeis.add(btnNavAnt);
		linhaSeis.add(Box.createHorizontalStrut(5));
		linhaSeis.add(btnDemonstrativo);
		linhaSeis.add(Box.createHorizontalStrut(5));
		linhaSeis.add(btnConsistir);
		linhaSeis.add(Box.createHorizontalStrut(5));
		linhaSeis.add(btnNavProx);
		linhaSeis.add(Box.createHorizontalStrut(5));
		linhaSeis.add(btnNavUlt);


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
		linhas.add(linhaOito);
		linhas.add(Box.createVerticalStrut(5));
		linhas.add(linhaNove);
		linhas.add(Box.createVerticalStrut(180));
		linhas.add(linhaSeis);

		Box linhasComissao = Box.createVerticalBox();
		linhasComissao.add(linhaUm);

		linhaA.add(lblNotaFiscal);
		linhaA.add(txtNotaFiscal);
		linhaB.add(lblCliente);
		linhaB.add(txtCliente);
		linhaC.add(lblRepresentada);
		linhaC.add(txtRepresentada);
		linhaD.add(lblProduto);
		linhaD.add(txtProduto);
		linhaD.add(Box.createHorizontalStrut(5));
		linhaD.add(lblValorVenda);
		linhaD.add(txtValorVenda);
		linhaD.add(Box.createHorizontalStrut(5));
		linhaD.add(lblDtVenda);
		linhaD.add(txtVenda);

		linhaE.add(jspVendas);

		Box linhaVdois = Box.createVerticalBox();
		linhaVdois.add(linhaA);
		linhaVdois.add(Box.createVerticalStrut(5));
		linhaVdois.add(linhaB);
		linhaVdois.add(Box.createVerticalStrut(5));
		linhaVdois.add(linhaC);
		linhaVdois.add(Box.createVerticalStrut(5));
		linhaVdois.add(linhaD);
		linhaVdois.add(Box.createVerticalStrut(5));
		linhaVdois.add(linhaE);

		Box linhaComissao = Box.createHorizontalBox();
		linhaComissao.add(linhaVdois);

		abaDados.add(linhas);
		abaComissao.add(linhaComissao);

		abaPrincipal.addTab("Dados do funcionário", null, abaDados, null);
		abaPrincipal.addTab("Comissões", null, abaComissao, null);
		linhaSete.add(abaPrincipal);

		Box navegacao = Box.createVerticalBox();
		linhaDoze.add(lblDivisao);
		linhaTreze.add(btnRelatorio);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnAlterar);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnConfirmar);
		linhaTreze.add(Box.createHorizontalStrut(5));
		linhaTreze.add(btnCancelar);

		navegacao.add(linhaDoze);
		navegacao.add(Box.createVerticalStrut(10));
		navegacao.add(linhaTreze);

		Box caixa = Box.createVerticalBox();
		caixa.add(linhaSete);
		caixa.add(Box.createVerticalStrut(5));
		caixa.add(navegacao);


		jContentPane = new JPanel();
		jContentPane.add(caixa);
		getContentPane().add(jContentPane);
		pack();	

		//-----Ações
		
		btnConsistir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(contConsistidos()>0){
					JOptionPane.showMessageDialog(null, "Encontrada inconsistências nos dados informados:\n"+getMsgStatus(),"Erro",2);
				}else{
					salBase = Double.parseDouble(txtSalarioBase.getText().trim().replace(".",",").replace(",",".").substring(2));
					txtVlHorasExtras.setText(calculaVlHoraExtra(txtSalarioBase.getText().trim(), txtQtHorasExtras.getText().trim()));
					txtSalarioFamilia.setText(calculaVlFamilia(txtFuncionario.getText().trim(), txtSalarioBase.getText().trim(), txtQtdependentes.getText().trim()));
					txtVlInss.setText(vlInss(txtSalarioBase.getText().trim()));
					txtVlFaltas.setText(calculaFaltas(txtSalarioBase.getText().trim(), txtQtFaltas.getText().trim()));
					System.out.println("VlIrrf btnConsistir "+vlIrrf(txtSalarioBase.getText().trim(), txtQtFaltas.getText().trim(), txtFuncionario.getText().trim()).replace(",", ".").substring(5));
					if(Double.parseDouble(vlIrrf(txtSalarioBase.getText().trim(), txtQtFaltas.getText().trim(), txtFuncionario.getText().trim()).replace(",", ".").substring(5))>=0){
						txtVlIr.setText("R$ "+vlIrrf(txtSalarioBase.getText().trim(), txtQtFaltas.getText().trim(), txtFuncionario.getText().trim()).replace(",", ".").substring(5));
					}else{
						txtVlIr.setText("R$ 0,00");
					}
					txtVlFgts.setText(vlFgts(txtSalarioBase.getText().trim()));
					if(Double.parseDouble(vlTransporte(txtFuncionario.getText().trim(), txtSalarioBase.getText().trim(), txtVlTransporte.getText().trim()).replace(".",",").replace(",",".").substring(3))>=0){
						txtVlTransporteDes.setText(vlTransporte(txtFuncionario.getText().trim(), txtSalarioBase.getText().trim(), txtVlTransporte.getText().trim()));
					}else{
						txtVlTransporte.setText("R$ 0,00");
						txtVlTransporteDes.setText("R$ 0,00");
					}					
					vlTrans = Double.parseDouble(txtVlTransporte.getText().trim().replace(".",",").replace(",",".").substring(2));
					txtVlTotalDescontos.setText(totDescontos());
					txtVlTotalVencimentos.setText(totVencimentos());
					txtVlSalarioLiquido.setText(totLiquido());
					JOptionPane.showMessageDialog(null,"Folha de pagamento do funcionário consistente","Consistente",1);
				}
			}});
	}
	private double salBase = 0;
	private double vlHorasExtras = 0;
	private double vlTransporte = 0;
	private double vlTrans = 0;
	private double salFamilia = 0;
	private double vlFaltas = 0;
	private double inss = 0;
	private double irrf = 0;
	private double vlRefeicao = 0;
		
	private String totDescontos(){
		double total = 0;
		total = getVlTransporte()+getVlFaltas()+getInss()+getIrrf();
		System.out.println(getVlTransporte());
		System.out.println(getVlFaltas());
		System.out.println(getInss());
		System.out.println(getIrrf());
		DecimalFormat decimal = new DecimalFormat( "0.00" );  
		decimal.format(total);
		return "R$ "+decimal.format(total);
	}
	private String totVencimentos(){
		double total = 0;
		total = getSalBase()+getVlHorasExtras()+getVlTrans()+getSalFamilia()+getVlRefeicao();
		DecimalFormat decimal = new DecimalFormat( "0.00" );  
		decimal.format(total);
		return "R$ "+decimal.format(total);
	}
	private String totLiquido(){
		double total = 0;
		double descontos = getVlTransporte()+getVlFaltas()+getInss()+getIrrf();		
		double vencimentos = getSalBase()+getVlHorasExtras()+getVlTrans()+getSalFamilia()+getVlRefeicao();
		if(descontos>vencimentos){
			total = descontos - vencimentos;
		}else{
			total = vencimentos - descontos;	
		}
		DecimalFormat decimal = new DecimalFormat( "0.00" );  
		decimal.format(total);
		return "R$ "+decimal.format(total);
	}
	private String vlRefeicao(String cdFuncionario){
		String voltar = "";
		FolhaPagamento consultar = new FolhaPagamento();
		if(consultar.direitoRefeicao(cdFuncionario)>0){
			String retorno = "R$ "+consultar.vlRefeicao();
			String o = retorno.substring(3, retorno.lastIndexOf("."));
			String u = retorno.substring(retorno.lastIndexOf("."));
			String e = u.substring(1);		
			if(Double.parseDouble(e)<10){
				e+=""+0;
			}else{
				e = e.substring(0, 2);
			}
			this.vlRefeicao = Double.parseDouble(o.replace(",",".").replace(".",","));	
			voltar = "R$ "+o+","+e;
		}else{
			this.vlRefeicao = 0.00;	
			voltar = "R$ 0,00";
		}		
		return voltar;
	}
	private String vlFgts(String base){
		FolhaPagamento consultar = new FolhaPagamento();
		String retorno = "R$ "+consultar.vlFgts(base);
		String o = retorno.substring(3, retorno.lastIndexOf("."));
		String u = retorno.substring(retorno.lastIndexOf("."));
		String e = u.substring(1);		
		if(Double.parseDouble(e)<10){
			e+=""+0;
		}else{
			e = e.substring(0, 2);
		}		
		return "R$ "+o+","+e;
	}
	private String vlIrrf(String base, String faltas, String cdFuncionario){
		FolhaPagamento consultar = new FolhaPagamento();
		String retorno = "R$ "+consultar.vlIr(base, Integer.parseInt(faltas), cdFuncionario);
		return "R$ "+retorno;
	}
	private String vlInss(String base){
		FolhaPagamento consultar = new FolhaPagamento();
		String retorno = "R$ "+consultar.vlInss(base);
		String o = retorno.substring(3, retorno.lastIndexOf("."));
		String u = retorno.substring(retorno.lastIndexOf("."));
		String e = u.substring(1);		
		if(Double.parseDouble(e)<10){
			e+=""+0;
		}else{
			e = e.substring(0, 2);
		}
		this.inss = Double.parseDouble(o.replace(",",".").replace(".",","));
		return "R$ "+o+","+e;
	}
	private String vlTransporte(String cdFuncionario, String base, String vlTrans){
		String voltar = "";
		FolhaPagamento consultar = new FolhaPagamento();
		if(consultar.direitoTransporte(cdFuncionario)>0){
			double vl = Double.parseDouble(vlTrans.replace(".",",").replace(",",".").substring(2));
			if(consultar.vlTransporte(cdFuncionario, base)>vl){
				this.vlTransporte = vl;
				voltar = vlTrans;
			}else{
				String retorno = "R$ "+consultar.vlTransporte(cdFuncionario, base);
				String o = retorno.substring(3, retorno.lastIndexOf("."));
				String u = retorno.substring(retorno.lastIndexOf("."));
				String e = u.substring(1);		
				if(Double.parseDouble(e)<10){
					e+=""+0;
				}else{
					e = e.substring(0, 2);
				}
				this.vlTransporte = Double.parseDouble(o.replace(",",".").replace(".",","));
				voltar = "R$ "+o+","+e;	
			}				
		}else{
			this.vlTransporte = 0;
			voltar = "R$ 0,00";
		}
		return voltar;
	}
	private String calculaVlHoraExtra(String base, String qtHoras){
		String x = base.substring(2,6);
		String y = base.substring(base.lastIndexOf(","));
		String a = y.substring(1);
		String z = x+"."+a;
		double baseDouble = Double.parseDouble(z); 
		int qtHrs = Integer.parseInt(qtHoras);
		double vlHora = baseDouble / 240;
		String retorno = "R$ "+qtHrs * vlHora;
		String o = retorno.substring(3, retorno.lastIndexOf("."));
		String u = retorno.substring(retorno.lastIndexOf("."));
		String e = u.substring(1);		
		if(Double.parseDouble(e)<10){
			e+=""+0;
		}else{
			e = e.substring(0, 2);
		}
		this.vlHorasExtras = Double.parseDouble(o.replace(",",".").replace(".",","));
		return "R$ "+o+","+e;
	}
	private String calculaVlFamilia(String cdFuncionario, String base, String qtDependentes){
		String voltar = "";
		FolhaPagamento consultar = new FolhaPagamento();
		if(consultar.direitoFamilia(cdFuncionario)>0){
			String retorno = "R$ "+Integer.parseInt(qtDependentes) * consultar.vlSalarioFamilia(base);	
			String o = retorno.substring(3, retorno.lastIndexOf("."));
			String u = retorno.substring(retorno.lastIndexOf("."));
			String e = u.substring(1);		
			if(Double.parseDouble(e)<10){
				e+=""+0;
			}else{
				e = e.substring(0, 2);
			}
			this.salFamilia = Double.parseDouble(o.replace(",",".").replace(".",","));
			voltar = "R$ "+o+","+e;
		}else{
			this.salFamilia = 0.00;	
			voltar = "R$ 0,00";
		}
		return voltar;
	}
	private String calculaFaltas(String base, String qtFaltas){
		String x = base.substring(2,6);
		String y = base.substring(base.lastIndexOf(","));
		String a = y.substring(1);
		String z = x+"."+a;
		double baseDouble = Double.parseDouble(z); 
		int qtF = Integer.parseInt(qtFaltas);
		double vlHora = baseDouble / 30;
		double total = qtF * vlHora;
		DecimalFormat decimal = new DecimalFormat( "0.00" );
		this.vlFaltas = total;
		return "R$ "+decimal.format(total);
	}
	public int getDependetes(String cdFuncionario) {
		FolhaPagamento consultar = new FolhaPagamento();
		return consultar.getDependentes(cdFuncionario);
	}
	public double getInss() {
		return inss;
	}
	public double getIrrf() {
		return irrf;
	}
	public double getSalBase() {
		return salBase;
	}
	public double getSalFamilia() {
		return salFamilia;
	}
	public double getVlFaltas() {
		return vlFaltas;
	}
	public double getVlHorasExtras() {
		return vlHorasExtras;
	}
	public double getVlRefeicao() {
		return vlRefeicao;
	}
	public double getVlTransporte() {
		return vlTransporte;
	}
	public double getVlTrans() {
		return vlTrans;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
