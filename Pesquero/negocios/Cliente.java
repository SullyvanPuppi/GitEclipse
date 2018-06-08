package negocios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.view.JasperViewer;
import persistencia.CadastroCliente;

public class Cliente {

	private int status = 0;
	private String msgStatus = "";
	private int registros = 0;

	//--Campos de cadastro simples
	private String txtNmCliente = null;
	private String txtCnpjCliente = null;
	private String cmbSegmento = null;
	private String txtDsAtividade = null;
	private String txtClienteDesde = null;
	private String txtDtCadastro = null;
	private String txtTelCliente = null;
	private String txtFaxCliente = null;
	private String txtEmail = null;
	private String txtSite = null;
	private String txtObs = null;

	//--Campos de cadastro completo
	private String txtRazaoCliente = null;
	private String txtIeCliente = null;
	private String txtEndereco = null;
	private String txtEnderecoNumero = null;
	private String txtEnderecoComplemento = null;
	private String txtEnderecoBairro = null;
	private String txtEnderecoCidade = null;
	private String cmbEnderecoEstado = null;
	private String txtEnderecoCep = null;
	private String txtTelefone2 = null;

	public int registros(){
		return registros;
	}
	public String getMsgStatus() {
		return msgStatus;
	}
	public int getStatus() {
		return status;
	}
	public String getCmbEnderecoEstado() {
		return cmbEnderecoEstado;
	}
	public String getCmbSegmento() {
		return cmbSegmento;
	}
	public String getTxtClienteDesde() {
		return txtClienteDesde;
	}
	public String getTxtCnpjCliente() {
		return txtCnpjCliente;
	}
	public String getTxtDsAtividade() {
		return txtDsAtividade;
	}
	public String getTxtDtCadastro() {
		return txtDtCadastro;
	}
	public String getTxtEmail() {
		return txtEmail;
	}
	public String getTxtEndereco() {
		return txtEndereco;
	}
	public String getTxtEnderecoBairro() {
		return txtEnderecoBairro;
	}
	public String getTxtEnderecoCep() {
		return txtEnderecoCep;
	}
	public String getTxtEnderecoCidade() {
		return txtEnderecoCidade;
	}
	public String getTxtEnderecoComplemento() {
		return txtEnderecoComplemento;
	}
	public String getTxtEnderecoNumero() {
		return txtEnderecoNumero;
	}
	public String getTxtFaxCliente() {
		return txtFaxCliente;
	}
	public String getTxtIeCliente() {
		return txtIeCliente;
	}
	public String getTxtNmCliente() {
		return txtNmCliente;
	}
	public String getTxtObs() {
		return txtObs;
	}
	public String getTxtRazaoCliente() {
		return txtRazaoCliente;
	}
	public String getTxtSite() {
		return txtSite;
	}
	public String getTxtTelCliente() {
		return txtTelCliente;
	}
	public String getTxtTelefone2() {
		return txtTelefone2;
	}
	public void ajustaDados(String txtNmCliente, String txtCnpjCliente, String cmbSegmento, String txtDsAtividade, String txtClienteDesde, String txtDtCadastro, String txtTelCliente, String txtFaxCliente, String txtEmail, String txtSite, String txtObs, String txtRazaoCliente, String txtIeCliente, String txtEndereco, String txtEnderecoNumero, String txtEnderecoComplemento, String txtEnderecoBairro, String txtEnderecoCidade, String cmbEnderecoEstado, String txtEnderecoCep, String txtTelefone2) {
		this.txtNmCliente = txtNmCliente.trim();
		this.txtCnpjCliente = txtCnpjCliente.trim();
		this.cmbSegmento = cmbSegmento.trim();
		this.txtDsAtividade = txtDsAtividade.trim();
		this.txtClienteDesde = txtClienteDesde.trim();
		this.txtDtCadastro = txtDtCadastro.trim();
		this.txtTelCliente = txtTelCliente.trim();
		this.txtFaxCliente = txtFaxCliente.trim();
		this.txtEmail = txtEmail.trim();
		this.txtSite = txtSite.trim();
		this.txtObs = txtObs.trim();
		this.txtRazaoCliente = txtRazaoCliente.trim();
		this.txtIeCliente = txtIeCliente.trim();
		this.txtEndereco = txtEndereco.trim();
		this.txtEnderecoNumero = txtEnderecoNumero.trim();
		this.txtEnderecoComplemento = txtEnderecoComplemento.trim();
		this.txtEnderecoBairro = txtEnderecoBairro.trim();
		this.txtEnderecoCidade = txtEnderecoCidade.trim();
		this.cmbEnderecoEstado = cmbEnderecoEstado.trim();
		this.txtEnderecoCep = txtEnderecoCep.trim();
		this.txtTelefone2 = txtTelefone2.trim();
	}
	public void ajustaDados(String txtNmCliente, String txtCnpjCliente, String cmbSegmento, String txtDsAtividade, String txtClienteDesde, String txtDtCadastro, String txtTelCliente, String txtFaxCliente, String txtEmail, String txtSite, String txtObs) {
		this.txtNmCliente = txtNmCliente.trim();
		this.txtCnpjCliente = txtCnpjCliente.trim();
		this.cmbSegmento = cmbSegmento.trim();
		this.txtDsAtividade = txtDsAtividade.trim();
		this.txtClienteDesde = txtClienteDesde.trim();
		this.txtDtCadastro = txtDtCadastro.trim();
		this.txtTelCliente = txtTelCliente.trim();
		this.txtFaxCliente = txtFaxCliente.trim();
		this.txtEmail = txtEmail.trim();
		this.txtSite = txtSite.trim();
		this.txtObs = txtObs.trim();
	}
	public int validaData(String data){
		int x = 0;		

		int dia = Integer.parseInt(data.substring(0, 2));

		int mes = Integer.parseInt(data.substring(3, 5));

		if(dia>31 || dia<1){
			x=1;
		}
		if(mes>12 || mes<1){
			x=1;
		}
		return x;
	}
	public String getHoje(){
		String data = "";
		//Cria um objeto calendar com a data atual
		Calendar today = Calendar.getInstance();

		int dia = today.get(Calendar.DAY_OF_MONTH);

		int mes = today.get(Calendar.MONTH)+1; 

		int ano = today.get(Calendar.YEAR);

		if(dia<10){
			if(mes<10){
				data = "0"+dia+"0"+mes+ano;
			}else{
				data = "0"+dia+mes+ano;
			}
		}else{
			if(mes<10){
				data = ""+dia+"0"+mes+ano;
			}else{
				data = ""+dia+mes+ano;
			}
		}

		return data;
	}
	public int validaDataCadastro(String dtEntrada, String dtAdmissao){
		int x = 0;
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Date dataEntrada = null;
		Date dataAdmissao = null;

		try {
			dataEntrada= sdf.parse(dtEntrada);
			dataAdmissao= sdf.parse(dtAdmissao);
		} catch (Exception e) {}

		Calendar date1 = new GregorianCalendar();
		Calendar date2 = new GregorianCalendar();

		date1.setTime(dataEntrada);
		date2.setTime(dataAdmissao);

		if (date2.before(date1)) {
			x = 1;

		}
		return x;
	}
	public int validarSimples(){
		if(getTxtNmCliente().equals("")){
			this.status +=1;
			this.msgStatus +="\nNome da empresa cliente inválido";
		}
		if(getTxtCnpjCliente().equals(".   .   /    -")){
			this.status +=1;
			this.msgStatus +="\nCNPJ da empresa cliente inválido";
		}
		if(getTxtClienteDesde().equals("/  /")){
			this.status +=1;
			this.msgStatus +="\nData de início do cliente inválido";
		}else{
			if(validaData(getTxtClienteDesde())>0){
				this.status +=1;
				this.msgStatus +="\nData de início do cliente inválido";	
			}
		}
		if(getCmbSegmento().equals("--------------------------------")){
			this.status +=1;
			this.msgStatus +="\nSegmento da empresa cliente inválido";
		}
		if(getTxtTelCliente().equals("(  )    -")){
			this.status +=1;
			this.msgStatus +="\nTelefone da empresa cliente inválido";
		}
		return status;
	}
	public int validarCompleto(){
		if(getTxtNmCliente().equals("")){
			this.status +=1;
			this.msgStatus +="\nNome da empresa cliente inválido";
		}
		if(getTxtCnpjCliente().equals("")){
			this.status +=1;
			this.msgStatus +="\nCNPJ da empresa cliente inválido";
		}
		if(getTxtClienteDesde().equals("/  /")){
			this.status +=1;
			this.msgStatus +="\nData de início do cliente inválido";
		}else{
			if(validaData(getTxtClienteDesde())>0){
				this.status +=1;
				this.msgStatus +="\nData de início do cliente inválido";	
			}
		}
		if(getCmbSegmento().equals("--------------------------------")){
			this.status +=1;
			this.msgStatus +="\nSegmento da empresa cliente inválido";
		}
		if(getTxtTelCliente().equals("(  )    -")){
			this.status +=1;
			this.msgStatus +="\nTelefone da empresa cliente inválido";
		}
		if(getTxtEndereco().equals("")){
			this.status +=1;
			this.msgStatus +="\nEndereço da empresa cliente inválido";
		}
		if(getTxtEnderecoNumero().equals("")){
			this.status +=1;
			this.msgStatus +="\nNúmero do endereço da empresa cliente inválido";
		}
		if(getTxtEnderecoBairro().equals("")){
			this.status +=1;
			this.msgStatus +="\nBairro do endereço da empresa cliente inválido";
		}
		if(getTxtEnderecoCidade().equals("")){
			this.status +=1;
			this.msgStatus +="\nCidade do endereço da empresa cliente inválido";
		}
		if(getCmbEnderecoEstado().equals("--")){
			this.status +=1;
			this.msgStatus +="\nEstado do endereço da empresa cliente inválido";
		}
		if(getTxtEnderecoCep().equals("")){
			this.status +=1;
			this.msgStatus +="\nCEP do endereço da empresa cliente inválido";
		}
		return status;
	}
	public int verificaExiste(){
		CadastroCliente cadastrar = new CadastroCliente();
		return cadastrar.verificaExiste(getTxtNmCliente(), getTxtCnpjCliente());
	}
	public void cadastrarSimples(){
		CadastroCliente cadastrar = new CadastroCliente();
		cadastrar.ajustaDados(getTxtNmCliente(), getTxtCnpjCliente(), getCmbSegmento(), getTxtDsAtividade(), getTxtClienteDesde(), getTxtDtCadastro(), getTxtTelCliente(), getTxtFaxCliente(), getTxtEmail(), getTxtSite(), getTxtObs());
		cadastrar.cadastrarSimples();
	}
	public void alterarSimples(){
		CadastroCliente cadastrar = new CadastroCliente();
		cadastrar.ajustaDados(getTxtNmCliente(), getTxtCnpjCliente(), getCmbSegmento(), getTxtDsAtividade(), getTxtClienteDesde(), getTxtDtCadastro(), getTxtTelCliente(), getTxtFaxCliente(), getTxtEmail(), getTxtSite(), getTxtObs());
		cadastrar.alteraSimples();
	}
	public void cadastrarCompleto(){
		CadastroCliente cadastrar = new CadastroCliente();
		cadastrar.ajustaDados(getTxtNmCliente(), getTxtCnpjCliente(), getCmbSegmento(), getTxtDsAtividade(), getTxtClienteDesde(), getTxtDtCadastro(), getTxtTelCliente(), getTxtFaxCliente(), getTxtEmail(), getTxtSite(), getTxtObs(), getTxtRazaoCliente(), getTxtIeCliente(), getTxtEndereco(), getTxtEnderecoNumero(), getTxtEnderecoComplemento(), getTxtEnderecoBairro(), getTxtEnderecoCidade(), getCmbEnderecoEstado(), getTxtEnderecoCep(), getTxtTelefone2());
		cadastrar.cadastrarCompleto();
	}
	public void alterarCompleto(){
		CadastroCliente cadastrar = new CadastroCliente();
		cadastrar.ajustaDados(getTxtNmCliente(), getTxtCnpjCliente(), getCmbSegmento(), getTxtDsAtividade(), getTxtClienteDesde(), getTxtDtCadastro(), getTxtTelCliente(), getTxtFaxCliente(), getTxtEmail(), getTxtSite(), getTxtObs(), getTxtRazaoCliente(), getTxtIeCliente(), getTxtEndereco(), getTxtEnderecoNumero(), getTxtEnderecoComplemento(), getTxtEnderecoBairro(), getTxtEnderecoCidade(), getCmbEnderecoEstado(), getTxtEnderecoCep(), getTxtTelefone2());
		cadastrar.alterarCompleto();
	}
	public void remover(String cnpj, String nmEmpresa){
		CadastroCliente remover = new CadastroCliente();
		remover.remover(cnpj, nmEmpresa);
	}
	DefaultTableModel tabelaClientes = new DefaultTableModel();

	public DefaultTableModel getTabelaClientes(){
		return this.tabelaClientes;
	}
	public void consultarClientes(String cnpj, String nome){
		CadastroCliente consultar = new CadastroCliente();
		consultar.consultar(cnpj, nome);
		this.registros = consultar.getRegistros();
		this.tabelaClientes = consultar.getTabelaClientes();
	}
	public void consultarCliente(String nmEmpresa, String tipo){
		CadastroCliente consultar = new CadastroCliente();
		consultar.consultarEmpresa(nmEmpresa);
		if(tipo.equals("Simplificado")){
			this.txtNmCliente = consultar.getTxtNmCliente();
			this.txtCnpjCliente = consultar.getTxtCnpjCliente();
			this.cmbSegmento = consultar.getCmbSegmento();
			this.txtDsAtividade = consultar.getTxtDsAtividade();
			this.txtClienteDesde = consultar.getTxtClienteDesde();
			this.txtDtCadastro = consultar.getTxtDtCadastro();
			this.txtTelCliente = consultar.getTxtTelCliente();
			this.txtFaxCliente = consultar.getTxtFaxCliente();
			this.txtEmail = consultar.getTxtEmail();
			this.txtSite = consultar.getTxtSite();
			this.txtObs = consultar.getTxtObs();
		}else if(tipo.equals("Completo")){
			this.txtNmCliente = consultar.getTxtNmCliente();
			this.txtCnpjCliente = consultar.getTxtCnpjCliente();
			this.cmbSegmento = consultar.getCmbSegmento();
			this.txtDsAtividade = consultar.getTxtDsAtividade();
			this.txtClienteDesde = consultar.getTxtClienteDesde();
			this.txtDtCadastro = consultar.getTxtDtCadastro();
			this.txtTelCliente = consultar.getTxtTelCliente();
			this.txtFaxCliente = consultar.getTxtFaxCliente();
			this.txtEmail = consultar.getTxtEmail();
			this.txtSite = consultar.getTxtSite();
			this.txtObs = consultar.getTxtObs();
			this.txtRazaoCliente = consultar.getTxtRazaoCliente();
			this.txtIeCliente = consultar.getTxtIeCliente();
			this.txtEndereco = consultar.getTxtEndereco();
			this.txtEnderecoNumero = consultar.getTxtEnderecoNumero();
			this.txtEnderecoComplemento = consultar.getTxtEnderecoComplemento();
			this.txtEnderecoBairro = consultar.getTxtEnderecoBairro();
			this.txtEnderecoCidade = consultar.getTxtEnderecoCidade();
			this.cmbEnderecoEstado = consultar.getCmbEnderecoEstado();
			this.txtEnderecoCep = consultar.getTxtEnderecoCep();
			this.txtTelefone2 = consultar.getTxtTelefone2();
		}
	}
	public JasperViewer gerarRelatorio(String cnpj, String nome){
		CadastroCliente gerar = new CadastroCliente();
		//exibe o resultado		
		JasperViewer viewer = new JasperViewer( gerar.gerarRelatorio(cnpj, nome) , false );
		viewer.setTitle("Visualizador de relatório - Pesquero Representações");
		return viewer;
	}
	public JasperViewer gerarRelatorioResumido(){
		CadastroCliente gerar = new CadastroCliente();
		//exibe o resultado
		JasperViewer viewer = new JasperViewer( gerar.gerarRelatorioResumido() , false );
		viewer.setTitle("Visualizador de relatório - Pesquero Representações");
		return viewer;
	}
	public JasperViewer gerarRelatorioContatos(String cnpj, String nmEmpresa){
		CadastroCliente gerar = new CadastroCliente();
		//exibe o resultado
		JasperViewer viewer = new JasperViewer( gerar.gerarRelatorioContatos(cnpj, nmEmpresa) , false );
		viewer.setTitle("Visualizador de relatório - Pesquero Representações");
		return viewer;
	}
}
