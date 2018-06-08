package negocios;

import persistencia.CadastroFolhaPagamento;

public class FolhaPagamento {

	private String txtFuncionario = null;
	private String txtNmFuncionario = null;
	private String txtMes = null;
	private String txtDtFechamento = null;
	private String txtQtHorasExtras = null;
	private String txtSalarioBase = null;
	private String txtVlTransporte = null;
	private String txtQtdependentes = null;
	private String txtSalarioFamilia = null;
	private String txtQtFaltas = null;
	private String txtVlInss = null;
	private String txtVlIr = null;
	private String txtVlFgts = null;
	private String txtVlRefeicao = null;
	private String txtVlSalarioLiquido = null;
	private String txtNotaFiscal = null;
	private String txtCliente = null;
	private String txtRepresentada = null;
	private String txtProduto = null;
	private String txtValorVenda = null;
	private String txtVenda = null;

	public FolhaPagamento() {
		super();
	}
	public void ajustaDados(String txtFuncionario, String txtNmFuncionario, String txtMes, String txtDtFechamento, String txtQtHorasExtras, String txtSalarioBase, String txtVlTransporte, String txtQtdependentes, String txtSalarioFamilia, String txtQtFaltas, String txtVlInss, String txtVlIr, String txtVlFgts, String txtVlRefeicao, String txtVlSalarioLiquido) {
		this.txtFuncionario = txtFuncionario;
		this.txtNmFuncionario = txtNmFuncionario;
		this.txtMes = txtMes;
		this.txtDtFechamento = txtDtFechamento;
		this.txtQtHorasExtras = txtQtHorasExtras;
		this.txtSalarioBase = txtSalarioBase;
		this.txtVlTransporte = txtVlTransporte;
		this.txtQtdependentes = txtQtdependentes;
		this.txtSalarioFamilia = txtSalarioFamilia;
		this.txtQtFaltas = txtQtFaltas;
		this.txtVlInss = txtVlInss;
		this.txtVlIr = txtVlIr;
		this.txtVlFgts = txtVlFgts;
		this.txtVlRefeicao = txtVlRefeicao;
		this.txtVlSalarioLiquido = txtVlSalarioLiquido;
	}
	public void avancaFuncionario(String codFunc){

	}
	public void recuaFuncionario(String codFunc){

	}
	public void primeiroFuncionario(String codFunc){

	}
	public void ultimoFuncionario(String codFunc){

	}

	public double vlTransporte(String cdFuncionario, String base){
		System.out.println(base);
		CadastroFolhaPagamento consultar = new CadastroFolhaPagamento();
		double baseDouble = Double.parseDouble(base.replace(".",",").replace(",",".").substring(2)); 
		return consultar.vlTransporte(baseDouble);
	}
	public int direitoTransporte(String cdFuncionario){
		CadastroFolhaPagamento consultar = new CadastroFolhaPagamento();
		return consultar.direitoTansporte(cdFuncionario);
	}
	public int direitoFamilia(String cdFuncionario){
		CadastroFolhaPagamento consultar = new CadastroFolhaPagamento();
		return consultar.direitoFamilia(cdFuncionario);
	}
	public int direitoRefeicao(String cdFuncionario){
		CadastroFolhaPagamento consultar = new CadastroFolhaPagamento();
		return consultar.direitoRefeicao(cdFuncionario);
	}
	public int getDependentes(String cdFuncionario){
		CadastroFolhaPagamento consultar = new CadastroFolhaPagamento();
		return consultar.getDependentes(cdFuncionario);
	}
	public double vlSalarioFamilia(String base){
		String x = base.substring(2,6);
		String y = base.substring(base.lastIndexOf(","));
		String a = y.substring(1);
		String z = x+"."+a;
		double baseDouble = Double.parseDouble(z); 
		CadastroFolhaPagamento consultar = new CadastroFolhaPagamento();
		return consultar.vlSalarioFamila(baseDouble);
	}
	public double vlInss(String base){
		String x = base.substring(2,6);
		String y = base.substring(base.lastIndexOf(","));
		String a = y.substring(1);
		String z = x+"."+a;
		double baseDouble = Double.parseDouble(z); 
		CadastroFolhaPagamento consultar = new CadastroFolhaPagamento();
		return consultar.vlInss(baseDouble);
	}
	public double vlIr(String base, int faltas, String cdFuncionario){
		double baseDouble = Double.parseDouble(base.replace(".",",").replace(",",".").substring(2));
		CadastroFolhaPagamento consultar = new CadastroFolhaPagamento();
		return consultar.vlIrrf(baseDouble, faltas, cdFuncionario);
	}
	public double vlFgts(String base){
		String x = base.substring(2,6);
		String y = base.substring(base.lastIndexOf(","));
		String a = y.substring(1);
		String z = x+"."+a;
		double baseDouble = Double.parseDouble(z);
		CadastroFolhaPagamento consultar = new CadastroFolhaPagamento();
		return consultar.vlFgts(baseDouble);
	}
	public double vlRefeicao(){
		CadastroFolhaPagamento consultar = new CadastroFolhaPagamento();
		return consultar.vlRefeicao();
	}
	public int qtFuncionarios(){
		CadastroFolhaPagamento consultar = new CadastroFolhaPagamento();
		return consultar.qtFuncionarios();
	}
	public int qtFolhaCadastrados(String mes){
		CadastroFolhaPagamento consultar = new CadastroFolhaPagamento();
		return consultar.folhaCadastrados(mes);
	}
	public double vlBase(){
		CadastroFolhaPagamento consultar = new CadastroFolhaPagamento();
		return consultar.getVlBase();
	}
	public double salarioBase(String valor){
		String x = valor.substring(2,6);
		String y = valor.substring(valor.lastIndexOf(","));
		String a = y.substring(1);
		String z = x+"."+a;
		return Double.parseDouble(z);
	}


	//----Retorno de dados
	public String getTxtCliente() {
		return txtCliente;
	}
	public String getTxtDtFechamento() {
		return txtDtFechamento;
	}
	public String getTxtFuncionario() {
		return txtFuncionario;
	}
	public String getTxtMes() {
		return txtMes;
	}
	public String getTxtNmFuncionario() {
		return txtNmFuncionario;
	}
	public String getTxtNotaFiscal() {
		return txtNotaFiscal;
	}
	public String getTxtProduto() {
		return txtProduto;
	}
	public String getTxtQtdependentes() {
		return txtQtdependentes;
	}
	public String getTxtQtFaltas() {
		return txtQtFaltas;
	}
	public String getTxtQtHorasExtras() {
		return txtQtHorasExtras;
	}
	public String getTxtRepresentada() {
		return txtRepresentada;
	}
	public String getTxtSalarioBase() {
		return txtSalarioBase;
	}
	public String getTxtSalarioFamilia() {
		return txtSalarioFamilia;
	}
	public String getTxtValorVenda() {
		return txtValorVenda;
	}
	public String getTxtVenda() {
		return txtVenda;
	}
	public String getTxtVlFgts() {
		return txtVlFgts;
	}
	public String getTxtVlInss() {
		return txtVlInss;
	}
	public String getTxtVlIr() {
		return txtVlIr;
	}
	public String getTxtVlRefeicao() {
		return txtVlRefeicao;
	}
	public String getTxtVlSalarioLiquido() {
		return txtVlSalarioLiquido;
	}
	public String getTxtVlTransporte() {
		return txtVlTransporte;
	}
}
