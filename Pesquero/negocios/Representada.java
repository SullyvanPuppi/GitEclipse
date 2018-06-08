package negocios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.view.JasperViewer;

import persistencia.CadastroRepresentada;

public class Representada {

	public Representada(){
		super();
	}

	private int status = 0;
	private String msgStatus = "";
	private int registros = 0;
	
	public String getMsgStatus(){
		return msgStatus;
	}
	
	//--Campos de cadastro simplificado
	private String txtNmRepresentada = null;
	private String txtCnpjRepresentada = null;
	private String cmbSegmento = null;
	private String txtDsAtividade = null;
	private String txtRepresentadaDesde = null;
	private String txtDtCadastro = null;
	private String txtTelRepresentada = null;
	private String txtFaxRepresentada = null;
	private String txtEmail = null;
	private String txtSite = null;
	private String txtObs = null;
	
	//--Campos de cadastro completo
	private String txtRazaoRepresentada = null;
	private String txtIeRepresentada = null;
	private String txtEndereco = null;
	private String txtEnderecoNumero = null;
	private String txtEnderecoComplemento = null;
	private String txtEnderecoBairro = null;
	private String txtEnderecoCidade = null;
	private String cmbEnderecoEstado = null;
	private String txtEnderecoCep = null;
	private String txtTelefone2 = null;
	
	//--Campos de cadastro completo
	public String getCmbEnderecoEstado() {
		return cmbEnderecoEstado;
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
	public String getTxtIeRepresentada() {
		return txtIeRepresentada;
	}
	public String getTxtRazaoRepresentada() {
		return txtRazaoRepresentada;
	}
	public String getTxtTelefone2() {
		return txtTelefone2;
	}
	//--Campos do cadastro simples
	public String getCmbSegmento() {
		return cmbSegmento;
	}
	public String getTxtCnpjRepresentada() {
		return txtCnpjRepresentada;
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
	public String getTxtFaxRepresentada() {
		return txtFaxRepresentada;
	}
	public String getTxtNmRepresentada() {
		return txtNmRepresentada;
	}
	public String getTxtObs() {
		return txtObs;
	}
	public String getTxtRepresentadaDesde() {
		return txtRepresentadaDesde;
	}
	public String getTxtSite() {
		return txtSite;
	}
	public String getTxtTelRepresentada() {
		return txtTelRepresentada;
	}
	public int registros(){
		return registros;
	}
	public void ajustaDadosSimples(String txtNmRepresentada, String txtCnpjRepresentada, String cmbSegmento, String txtDsAtividade, String txtRepresentadaDesde, String txtDtCadastro, String txtTelRepresentada, String txtFaxRepresentada, String txtEmail, String txtSite, String txtObs) {
		this.txtNmRepresentada = txtNmRepresentada.trim();
		this.txtCnpjRepresentada = txtCnpjRepresentada.trim();
		this.cmbSegmento = cmbSegmento.trim();
		this.txtDsAtividade = txtDsAtividade.trim();
		this.txtRepresentadaDesde = txtRepresentadaDesde.trim();
		this.txtDtCadastro = txtDtCadastro.trim();
		this.txtTelRepresentada = txtTelRepresentada.trim();
		this.txtFaxRepresentada = txtFaxRepresentada.trim();
		this.txtEmail = txtEmail.trim();
		this.txtSite = txtSite.trim();
		this.txtObs = txtObs.trim();
	}
	public int validarSimples(){
		if(getTxtNmRepresentada().equals("")){
			this.status +=1;
			this.msgStatus +="\nNome da empresa representada inválido";
		}
		if(getTxtCnpjRepresentada().equals("")){
			this.status +=1;
			this.msgStatus +="\nCNPJ da empresa representada inválido";
		}
		if(getTxtRepresentadaDesde().equals("/  /")){
			this.status +=1;
			this.msgStatus +="\nData de início de representação da empresa representada inválido";
		}else{
			if(validaData(getTxtRepresentadaDesde())>0){
				this.status +=1;
				this.msgStatus +="\nData de início de representação da empresa representada inválido";	
			}
		}
		if(getCmbSegmento().equals("--------------------------------")){
			this.status +=1;
			this.msgStatus +="\nSegmento da empresa representada inválido";
		}
		if(getTxtTelRepresentada().equals("(  )    -")){
			this.status +=1;
			this.msgStatus +="\nTelefone da empresa representada inválido";
		}
		return status;
	}
	public void ajustaDadosCompleto(String txtNmRepresentada, String txtCnpjRepresentada, String cmbSegmento, String txtDsAtividade, String txtRepresentadaDesde, String txtDtCadastro, String txtTelRepresentada, String txtFaxRepresentada, String txtEmail, String txtSite, String txtObs, String txtRazaoRepresentada, String txtIeRepresentada, String txtEndereco, String txtEnderecoNumero, String txtEnderecoComplemento, String txtEnderecoBairro, String txtEnderecoCidade, String cmbEnderecoEstado, String txtEnderecoCep, String txtTelefone2) {
		this.txtNmRepresentada = txtNmRepresentada.trim();
		this.txtCnpjRepresentada = txtCnpjRepresentada.trim();
		this.cmbSegmento = cmbSegmento.trim();
		this.txtDsAtividade = txtDsAtividade.trim();
		this.txtRepresentadaDesde = txtRepresentadaDesde.trim();
		this.txtDtCadastro = txtDtCadastro.trim();
		this.txtTelRepresentada = txtTelRepresentada.trim();
		this.txtFaxRepresentada = txtFaxRepresentada.trim();
		this.txtEmail = txtEmail.trim();
		this.txtSite = txtSite.trim();
		this.txtObs = txtObs.trim();
		this.txtRazaoRepresentada = txtRazaoRepresentada.trim();
		this.txtIeRepresentada = txtIeRepresentada.trim();
		this.txtEndereco = txtEndereco.trim();
		this.txtEnderecoNumero = txtEnderecoNumero.trim();
		this.txtEnderecoComplemento = txtEnderecoComplemento.trim();
		this.txtEnderecoBairro = txtEnderecoBairro.trim();
		this.txtEnderecoCidade = txtEnderecoCidade.trim();
		this.cmbEnderecoEstado = cmbEnderecoEstado.trim();
		this.txtEnderecoCep = txtEnderecoCep.trim();
		this.txtTelefone2 = txtTelefone2.trim();
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
	public int validarCompleto(){
		if(getTxtNmRepresentada().equals("")){
			this.status +=1;
			this.msgStatus +="\nNome da empresa representada inválido";
		}
		if(getTxtCnpjRepresentada().equals("")){
			this.status +=1;
			this.msgStatus +="\nCNPJ da empresa representada inválido";
		}
		if(getTxtRepresentadaDesde().equals("/  /")){
			this.status +=1;
			this.msgStatus +="\nData de início de representação da empresa representada inválido";
		}else{
			if(validaData(getTxtRepresentadaDesde())>0){
				this.status +=1;
				this.msgStatus +="\nData de início de representação da empresa representada inválido";	
			}
		}
		if(getCmbSegmento().equals("--------------------------------")){
			this.status +=1;
			this.msgStatus +="\nSegmento da empresa representada inválido";
		}
		if(getTxtTelRepresentada().equals("(  )    -")){
			this.status +=1;
			this.msgStatus +="\nTelefone da empresa representada inválido";
		}
		if(getTxtEndereco().equals("")){
			this.status +=1;
			this.msgStatus +="\nEndereço da empresa representada inválido";
		}
		if(getTxtEnderecoNumero().equals("")){
			this.status +=1;
			this.msgStatus +="\nNúmero do endereço da empresa representada inválido";
		}
		if(getTxtEnderecoBairro().equals("")){
			this.status +=1;
			this.msgStatus +="\nBairro do endereço da empresa representada inválido";
		}
		if(getTxtEnderecoCidade().equals("")){
			this.status +=1;
			this.msgStatus +="\nCidade do endereço da empresa representada inválido";
		}
		if(getCmbEnderecoEstado().equals("--")){
			this.status +=1;
			this.msgStatus +="\nEstado do endereço da empresa representada inválido";
		}
		if(getTxtEnderecoCep().equals("")){
			this.status +=1;
			this.msgStatus +="\nCEP do endereço da empresa representada inválido";
		}
		return status;
	}
	public int verificaExiste(){
		CadastroRepresentada cadastrar = new CadastroRepresentada();
		return cadastrar.verificaExiste(getTxtNmRepresentada(), getTxtCnpjRepresentada());
	}
	public void cadastrarSimples(){
		CadastroRepresentada cadastrar = new CadastroRepresentada();
		cadastrar.ajustaDados(getTxtNmRepresentada(), getTxtCnpjRepresentada(), getCmbSegmento(), getTxtDsAtividade(), getTxtRepresentadaDesde(), getTxtDtCadastro(), getTxtTelRepresentada(), getTxtFaxRepresentada(), getTxtEmail(), getTxtSite(), getTxtObs());
		cadastrar.cadastrarSimples();
	}
	public void alterarSimples(){
		CadastroRepresentada cadastrar = new CadastroRepresentada();
		cadastrar.ajustaDados(getTxtNmRepresentada(), getTxtCnpjRepresentada(), getCmbSegmento(), getTxtDsAtividade(), getTxtRepresentadaDesde(), getTxtDtCadastro(), getTxtTelRepresentada(), getTxtFaxRepresentada(), getTxtEmail(), getTxtSite(), getTxtObs());
		cadastrar.alteraSimples();
	}
	public void cadastrarCompleto(){
		CadastroRepresentada cadastrar = new CadastroRepresentada();
		cadastrar.ajustaDados(getTxtNmRepresentada(), getTxtCnpjRepresentada(), getCmbSegmento(), getTxtDsAtividade(), getTxtRepresentadaDesde(), getTxtDtCadastro(), getTxtTelRepresentada(), getTxtFaxRepresentada(), getTxtEmail(), getTxtSite(), getTxtObs(), getTxtRazaoRepresentada(), getTxtIeRepresentada(), getTxtEndereco(), getTxtEnderecoNumero(), getTxtEnderecoComplemento(), getTxtEnderecoBairro(), getTxtEnderecoCidade(), getCmbEnderecoEstado(), getTxtEnderecoCep(), getTxtTelefone2());
		cadastrar.cadastrarCompleto();
	}
	public void alterarCompleto(){
		CadastroRepresentada cadastrar = new CadastroRepresentada();
		cadastrar.ajustaDados(getTxtNmRepresentada(), getTxtCnpjRepresentada(), getCmbSegmento(), getTxtDsAtividade(), getTxtRepresentadaDesde(), getTxtDtCadastro(), getTxtTelRepresentada(), getTxtFaxRepresentada(), getTxtEmail(), getTxtSite(), getTxtObs(), getTxtRazaoRepresentada(), getTxtIeRepresentada(), getTxtEndereco(), getTxtEnderecoNumero(), getTxtEnderecoComplemento(), getTxtEnderecoBairro(), getTxtEnderecoCidade(), getCmbEnderecoEstado(), getTxtEnderecoCep(), getTxtTelefone2());
		cadastrar.alterarCompleto();
	}
	public void remover(String cnpj, String nmEmpresa){
		CadastroRepresentada remover = new CadastroRepresentada();
		remover.remover(cnpj, nmEmpresa);
	}
	DefaultTableModel tabelaRepresentadas = new DefaultTableModel();

	public DefaultTableModel getTabelaRepresentadas(){
		return this.tabelaRepresentadas;
	}
	public void consultarRepresentadas(String cnpj, String nome){
		CadastroRepresentada consultar = new CadastroRepresentada();
		consultar.consultar(cnpj, nome);
		this.registros = consultar.getRegistros();
		this.tabelaRepresentadas = consultar.getTabelaRepresentadas();
	}
	public void consultarRepresentada(String nmEmpresa, String tipo){
		CadastroRepresentada consultar = new CadastroRepresentada();
		consultar.consultarEmpresa(nmEmpresa);
		if(tipo.equals("Simplificado")){
			this.txtNmRepresentada = consultar.getTxtNmRepresentada();
			this.txtCnpjRepresentada = consultar.getTxtCnpjRepresentada();
			this.cmbSegmento = consultar.getCmbSegmento();
			this.txtDsAtividade = consultar.getTxtDsAtividade();
			this.txtRepresentadaDesde = consultar.getTxtRepresentadaDesde();
			this.txtDtCadastro = consultar.getTxtDtCadastro();
			this.txtTelRepresentada = consultar.getTxtTelRepresentada();
			this.txtFaxRepresentada = consultar.getTxtFaxRepresentada();
			this.txtEmail = consultar.getTxtEmail();
			this.txtSite = consultar.getTxtSite();
			this.txtObs = consultar.getTxtObs();
		}else if(tipo.equals("Completo")){
			this.txtNmRepresentada = consultar.getTxtNmRepresentada();
			this.txtCnpjRepresentada = consultar.getTxtCnpjRepresentada();
			this.cmbSegmento = consultar.getCmbSegmento();
			this.txtDsAtividade = consultar.getTxtDsAtividade();
			this.txtRepresentadaDesde = consultar.getTxtRepresentadaDesde();
			this.txtDtCadastro = consultar.getTxtDtCadastro();
			this.txtTelRepresentada = consultar.getTxtTelRepresentada();
			this.txtFaxRepresentada = consultar.getTxtFaxRepresentada();
			this.txtEmail = consultar.getTxtEmail();
			this.txtSite = consultar.getTxtSite();
			this.txtObs = consultar.getTxtObs();
			this.txtRazaoRepresentada = consultar.getTxtRazaoRepresentada();
			this.txtIeRepresentada = consultar.getTxtIeRepresentada();
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
		CadastroRepresentada gerar = new CadastroRepresentada();
		//exibe o resultado		
		JasperViewer viewer = new JasperViewer( gerar.gerarRelatorio(cnpj, nome) , false );
		viewer.setTitle("Visualizador de relatório - Pesquero Representações");
		return viewer;
	}
	public JasperViewer gerarRelatorioResumido(){
		CadastroRepresentada gerar = new CadastroRepresentada();
		//exibe o resultado
		JasperViewer viewer = new JasperViewer( gerar.gerarRelatorioResumido() , false );
		viewer.setTitle("Visualizador de relatório - Pesquero Representações");
		return viewer;
	}
	public JasperViewer gerarRelatorioContatos(String cnpj, String nmEmpresa){
		CadastroRepresentada gerar = new CadastroRepresentada();
		//exibe o resultado
		JasperViewer viewer = new JasperViewer( gerar.gerarRelatorioContatos(cnpj, nmEmpresa) , false );
		viewer.setTitle("Visualizador de relatório - Pesquero Representações");
		return viewer;
	}
}
