package negocios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.view.JasperViewer;

import persistencia.CadastroNotificacoes;
import persistencia.CadastroVisita;

public class Visita {

	private String idVisita = null;
	private String txtNmCliente = null;
	private String cmbSegmento = null;
	private String txtDtVisita = null;
	private Vector cmbRepresentadas = null;
	private String txtEndereco = null;
	private String txtEnderecoNumero = null;
	private String txtEnderecoComplemento = null;
	private String txtEnderecoBairro = null;
	private String txtEnderecoCidade = null;
	private String cmbEnderecoEstado = null;
	private String txtEnderecoCep = null;
	private String txtTelCliente = null;
	private String txtFaxCliente = null;
	private String txtEmail = null;
	private String txtSite = null;
	private String txtObs = null;
	private String cmbVendedores = null;
	private String txtRespUm = null;
	private String txtRespDois = null;
	private String txtRespTres = null;
	private String txtRespQuatro = null;
	private String txtRespCinco = null;
	private String txtRespSeis = null;
	private String txtRespSete = null;
	private String txtLembrar = null;
	private String txtTipo = null;
	private boolean lembrar = true;
	private int status = 0;
	private String msgStatus = "";
	private int registros = 0;

	private String nmVendedor = null;


	public Visita(){
		super();
	}	
	public void ajustaDados(String idVisita, String txtNmCliente, String cmbSegmento, String txtDtVisita, String txtEndereco, String txtEnderecoNumero, String txtEnderecoComplemento, String txtEnderecoBairro, String txtEnderecoCidade, String cmbEnderecoEstado, String txtEnderecoCep, String txtTelCliente, String txtFaxCliente, String txtEmail, String txtSite, String txtObs, String cmbVendedores, String txtRespUm, String txtRespDois, String txtRespTres, String txtRespQuatro, String txtRespCinco, String txtRespSeis, String txtRespSete, boolean lembrar, String txtLembrar, String nmVendedor, String txtTipo, String txtTipoOutros) {
		this.idVisita = idVisita;
		this.txtNmCliente = txtNmCliente.trim();
		this.cmbSegmento = cmbSegmento.trim();
		this.txtDtVisita = txtDtVisita.trim();
		this.txtEndereco = txtEndereco.trim();
		this.txtEnderecoNumero = txtEnderecoNumero.trim();
		this.txtEnderecoComplemento = txtEnderecoComplemento.trim();
		this.txtEnderecoBairro = txtEnderecoBairro.trim();
		this.txtEnderecoCidade = txtEnderecoCidade.trim();
		this.cmbEnderecoEstado = cmbEnderecoEstado.trim();
		this.txtEnderecoCep = txtEnderecoCep.trim();
		this.txtTelCliente = txtTelCliente.trim();
		this.txtFaxCliente = txtFaxCliente.trim();
		this.txtEmail = txtEmail.trim();
		this.txtSite = txtSite.trim();
		this.txtObs = txtObs.trim();
		this.cmbVendedores = cmbVendedores.trim();
		this.txtRespUm = txtRespUm.trim();
		this.txtRespDois = txtRespDois.trim();
		this.txtRespTres = txtRespTres.trim();
		this.txtRespQuatro = txtRespQuatro.trim();
		this.txtRespCinco = txtRespCinco.trim();
		this.txtRespSeis = txtRespSeis.trim();
		this.txtRespSete = txtRespSete.trim();
		this.lembrar = lembrar;
		this.txtLembrar = txtLembrar.trim();
		this.nmVendedor = nmVendedor;
		if(txtTipo.equals("Outros")){
			this.txtTipo = txtTipoOutros.trim();
		}else{
			this.txtTipo = txtTipo.trim();	
		}		
	}
	public int validaDados(){
		if(getTxtNmCliente().equals("")){
			this.status +=1;
			this.msgStatus +="\nNome da empresa cliente inválido";
		}
		if(getCmbSegmento().equals("--------------------------------")){
			this.status +=1;
			this.msgStatus +="\nSegmento da empresa cliente inválido";
		}
		if(txtTipo.equals("")){
			this.status +=1;
			this.msgStatus +="\nTipo de visita inválido";
		}
		if(getValidaOferecidas()==3){
			this.status +=1;
			this.msgStatus +="\nEmpresa oferecida inválida";
		}
		if(getTxtDtVisita().equals("/  /")){
			this.status +=1;
			this.msgStatus +="\nData da visita inválido";
		}else{
			if(validaData(getTxtDtVisita())>0){
				this.status +=1;
				this.msgStatus +="\nData da visita inválido";	
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
		/*if(getTxtEndereco().equals("")){
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
		}*/
		if(getCmbVendedores().equals("--------------------------------")){
			this.status +=1;
			this.msgStatus +="\nVendedor que realizou a visita inválido";
		}
		if(lembrar){
			if(getTxtDtVisita().equals("/  /") || getTxtLembrar().equals("/  /")){
				this.status +=1;
				this.msgStatus +="\nData para notificação inválida";
			}else{
				if(validaDataFuturo(getTxtDtVisita(), getTxtLembrar())>0){
					this.status +=1;
					this.msgStatus +="\nData para notificação inválida";	
				}	
			}			
		}
		return status;
	}
	public int novaVisita(){
		CadastroVisita cadastrar = new CadastroVisita();
		return  cadastrar.geraCodVisita();
	}
	public void ajustaOferecidas(Object[] lista){
		int contador = 0;
		while(contador<lista.length){
			if(!lista[contador].equals("")){
				this.lista[contador] = lista[contador];
			}else{
				this.validaOferecidas += 1;
				this.lista[contador] = "";
			}
			contador+=1;
		}
	}
	private Object[] lista = new Object[3];

	private int validaOferecidas = 0;

	public Object[] getOferecidas(){
		return lista;
	}
	public int getValidaOferecidas(){
		return validaOferecidas;
	}
	public String getNmVendedor(){
		return nmVendedor;
	}
	public int registros(){
		return registros;
	}
	public String getMsgStatus(){
		return msgStatus;
	}
	public int getStatus(){
		return status;
	}
	public String getIdVisita(){
		return idVisita;
	}
	public String getCmbEnderecoEstado() {
		return cmbEnderecoEstado;
	}
	public Vector getCmbRepresentadas() {
		return cmbRepresentadas;
	}
	public String getCmbSegmento() {
		return cmbSegmento;
	}
	public String getCmbVendedores() {
		return cmbVendedores;
	}
	public String getTxtDtVisita() {
		return txtDtVisita;
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
	public boolean getLembrar() {
		return lembrar;
	}
	public String getTxtLembrar() {
		return txtLembrar;
	}
	public String getTxtNmCliente() {
		return txtNmCliente;
	}
	public String getTxtObs() {
		return txtObs;
	}
	public String getTxtRespCinco() {
		return txtRespCinco;
	}
	public String getTxtRespDois() {
		return txtRespDois;
	}
	public String getTxtRespQuatro() {
		return txtRespQuatro;
	}
	public String getTxtRespSeis() {
		return txtRespSeis;
	}
	public String getTxtRespSete() {
		return txtRespSete;
	}
	public String getTxtRespTres() {
		return txtRespTres;
	}
	public String getTxtRespUm() {
		return txtRespUm;
	}
	public String getTxtSite() {
		return txtSite;
	}
	public String getTxtTelCliente() {
		return txtTelCliente;
	}
	public String getTxtTipo() {
		return txtTipo;
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
	public int validaFuturo(String data){
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
	public int validaDataCadastro(String dtHoje, String dtVisita){
		int x = 0;
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat sdf2 = new SimpleDateFormat("ddMMyyyy");

		Date dataHoje = null;
		Date dataVisita = null;

		try {
			dataHoje= sdf2.parse(dtHoje);
			dataVisita= sdf.parse(dtVisita);
		} catch (Exception e) {}

		Calendar date1 = new GregorianCalendar();
		Calendar date2 = new GregorianCalendar();

		date1.setTime(dataHoje);
		date2.setTime(dataVisita);

		if (date1.before(date2)) {
			x = 1;
		}
		return x;
	}
	public int validaDataFuturo(String dtVisita, String dtLembrar){
		int x = 0;
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Date dataVisita = null;
		Date dataLembrar = null;
		Date hoje = null;

		DateFormat sdf2 = new SimpleDateFormat("ddMMyyyy");

		try {
			dataVisita= sdf.parse(dtVisita);
			dataLembrar= sdf.parse(dtLembrar);
			hoje = sdf2.parse(getHoje());
		} catch (Exception e) {}

		Calendar date1 = new GregorianCalendar();
		Calendar date2 = new GregorianCalendar();
		Calendar date3 = new GregorianCalendar();

		date1.setTime(dataVisita);
		date2.setTime(dataLembrar);
		date3.setTime(hoje);

		if (date2.before(date1)) {
			x = 1;
		}
		if(date2.before(date3)) {
			x = 1;
		}			
		return x;
	}
	//----Consultar representadas para exibir
	public Vector consultarRepresentadas(){
		CadastroVisita consultar = new CadastroVisita();
		consultar.consultarCmbRepresentadas();
		return consultar.getRepresentadas();
	}
	//----Consultar representadas para exibir
	public Vector consultarVendedores(){
		CadastroVisita consultar = new CadastroVisita();
		consultar.consultarCmbVendedores();
		return consultar.getVendedores();
	}

	public int verificaExiste(){
		CadastroVisita cadastrar = new CadastroVisita();
		return cadastrar.verificaExiste(getTxtNmCliente() , getTxtDtVisita());
	}
	public void cadastrar(){
		CadastroVisita cadastrar = new CadastroVisita();
		cadastrar.ajustaDados(getIdVisita(), getTxtNmCliente(), getCmbSegmento(), getTxtDtVisita(), getCmbRepresentadas(), getTxtEndereco(), getTxtEnderecoNumero(), getTxtEnderecoComplemento(), getTxtEnderecoBairro(), getTxtEnderecoCidade(), getCmbEnderecoEstado(), getTxtEnderecoCep(), getTxtTelCliente(), getTxtFaxCliente(), getTxtEmail(), getTxtSite(), getTxtObs(), getCmbVendedores(), getTxtRespUm(), getTxtRespDois(), getTxtRespTres(), getTxtRespQuatro(), getTxtRespCinco(), getTxtRespSeis(), getTxtRespSete(), getTxtLembrar(), getTxtTipo(), getLembrar(), consultarVendedor(getNmVendedor()), getNmVendedor());
		cadastrar.ajustaOferecidas(lista);
		cadastrar.cadastrar();
		cadastrar.cadastraOferecidas();
		if(getLembrar()){
			CadastroNotificacoes evento = new CadastroNotificacoes();
			evento.cadastrarVisita(getIdVisita(), getCmbVendedores(), getTxtDtVisita(), getTxtLembrar(), "Visita", "Visita realizada no dia "+getTxtDtVisita()+" à empresa "+getTxtNmCliente()+"\nTipo de visita "+getTxtTipo()+".");			
		}		
	}
	public String consultarVendedor(String nmFunc){
		CadastroVisita consultar = new CadastroVisita();
		return consultar.consultarVendedor(nmFunc);
	}
	public void cadastrarQuestoes(String idVisita, String dtVisita, String nmEmpresa, String questao1, String questao2, String questao3, String questao4, String questao5, String questao6, String questao7){
		CadastroVisita cadastrar = new CadastroVisita();
		cadastrar.cadastraQuestoes(idVisita, dtVisita, nmEmpresa, questao1, questao2, questao3, questao4, questao5, questao6, questao7);
	}
	public void alterar(){
		CadastroVisita cadastrar = new CadastroVisita();
		cadastrar.ajustaDados(getIdVisita(), getTxtNmCliente(), getCmbSegmento(), getTxtDtVisita(), getCmbRepresentadas(), getTxtEndereco(), getTxtEnderecoNumero(), getTxtEnderecoComplemento(), getTxtEnderecoBairro(), getTxtEnderecoCidade(), getCmbEnderecoEstado(), getTxtEnderecoCep(), getTxtTelCliente(), getTxtFaxCliente(), getTxtEmail(), getTxtSite(), getTxtObs(), getCmbVendedores(), getTxtRespUm(), getTxtRespDois(), getTxtRespTres(), getTxtRespQuatro(), getTxtRespCinco(), getTxtRespSeis(), getTxtRespSete(), getTxtLembrar(), getTxtTipo(), getLembrar(), consultarVendedor(getNmVendedor()), getNmVendedor());
		cadastrar.alterar();
		cadastrar.ajustaOferecidas(lista);
		cadastrar.cadastraOferecidas();
		CadastroNotificacoes evento = new CadastroNotificacoes();
		evento.removerVisita(getIdVisita());
		if(getLembrar()){
			evento.cadastrarVisita(getIdVisita(), getCmbVendedores(), getTxtDtVisita(), getTxtLembrar(), "Visita", "Visita realizada no dia "+getTxtDtVisita()+" à empresa "+getTxtNmCliente()+"\nTipo de visita "+getTxtTipo()+".");			
		}		
	}
	public void alteraQuestoes(String idVisita, String dtVisita, String nmEmpresa, String questao1, String questao2, String questao3, String questao4, String questao5, String questao6, String questao7){
		CadastroVisita cadastrar = new CadastroVisita();
		cadastrar.alteraQuestoes(idVisita, dtVisita, nmEmpresa, questao1, questao2, questao3, questao4, questao5, questao6, questao7);
	}
	DefaultTableModel tabelaVisitas = new DefaultTableModel();

	public DefaultTableModel getTabelaVisitas(){
		return this.tabelaVisitas;
	}
	public void consultarVisitas(String nmEmpresaVisita, String nmRepresentada, String nmVendedor){
		CadastroVisita consultar = new CadastroVisita();
		consultar.consultar(nmEmpresaVisita, nmRepresentada, nmVendedor);
		this.registros = consultar.getRegistros();
		this.tabelaVisitas = consultar.getTabelaVisitas();
	}
	public void consultarVisita(String idVisita){
		CadastroVisita consultar = new CadastroVisita();
		consultar.consultar(idVisita);
		consultar.consultarQuestoes(idVisita);
		this.txtNmCliente = consultar.getTxtNmCliente();
		this.cmbSegmento = consultar.getCmbSegmento();
		this.txtDtVisita = consultar.getTxtDtVisita();
		this.txtEndereco = consultar.getTxtEndereco();
		this.txtEnderecoNumero = consultar.getTxtEnderecoNumero();
		this.txtEnderecoComplemento = consultar.getTxtEnderecoComplemento();
		this.txtEnderecoBairro = consultar.getTxtEnderecoBairro();
		this.txtEnderecoCidade = consultar.getTxtEnderecoCidade();
		this.cmbEnderecoEstado = consultar.getCmbEnderecoEstado();
		this.txtEnderecoCep = consultar.getTxtEnderecoCep();
		this.txtTelCliente = consultar.getTxtTelCliente();
		this.txtFaxCliente = consultar.getTxtFaxCliente();
		this.txtEmail = consultar.getTxtEmail();
		this.txtSite = consultar.getTxtSite();
		this.txtObs = consultar.getTxtObs();
		this.cmbVendedores = consultar.getCmbVendedores();
		this.txtRespUm = consultar.getTxtRespUm();
		this.txtRespDois = consultar.getTxtRespDois();
		this.txtRespTres = consultar.getTxtRespTres();
		this.txtRespQuatro = consultar.getTxtRespQuatro();
		this.txtRespCinco = consultar.getTxtRespCinco();
		this.txtRespSeis = consultar.getTxtRespSeis();
		this.txtRespSete = consultar.getTxtRespSete();
		this.lembrar = consultar.getLembrar();
		this.txtLembrar = consultar.getTxtLembrar();
		this.nmVendedor = consultar.getNmVendedor();
		this.txtTipo = consultar.getTxtTipo();
		this.lista = (Object[]) consultar.consultarOferecidas(idVisita);
	}
	public void remover(String idVisita, String nmEmpresa){
		CadastroVisita remover = new CadastroVisita();
		remover.remover(idVisita, nmEmpresa);				
	}
	public JasperViewer gerarRelatorio(String nmEmpresa, String nmVendedor){
		CadastroVisita gerar = new CadastroVisita();
		//exibe o resultado		
		JasperViewer viewer = new JasperViewer( gerar.gerarRelatorio(nmEmpresa, nmVendedor), false );
		viewer.setTitle("Visualizador de relatório - Pesquero Representações");
		return viewer;
	}
	public JasperViewer gerarRelatorioVisita(String nmEmpresa, String dtVisita, String nmVendedor){
		CadastroVisita gerar = new CadastroVisita();
		//exibe o resultado		
		JasperViewer viewer = new JasperViewer( gerar.gerarRelatorioVisita(nmEmpresa, dtVisita, nmVendedor), false );
		viewer.setTitle("Visualizador de relatório - Pesquero Representações");
		return viewer;
	}
	public JasperViewer gerarRelatorioResumido(){
		CadastroVisita gerar = new CadastroVisita();
		//exibe o resultado
		JasperViewer viewer = new JasperViewer( gerar.gerarRelatorioResumido() , false );
		viewer.setTitle("Visualizador de relatório - Pesquero Representações");
		return viewer;
	}
	public JasperViewer gerarRelatorioContatos(String nmEmpresa, String dtVisita){
		CadastroVisita gerar = new CadastroVisita();
		//exibe o resultado
		JasperViewer viewer = new JasperViewer( gerar.gerarRelatorioContatos(nmEmpresa, dtVisita) , false );
		viewer.setTitle("Visualizador de relatório - Pesquero Representações");
		return viewer;
	}
	public JasperViewer gerarRelatorioQuestoes(String nmEmpresaVisita, String nmRepresentada, String nmVendedor){
		CadastroVisita gerar = new CadastroVisita();
		//exibe o resultado
		JasperViewer viewer = new JasperViewer( gerar.gerarRelatorioQuestoes(nmEmpresaVisita, nmRepresentada, nmVendedor), false );
		viewer.setTitle("Visualizador de relatório - Pesquero Representações");
		return viewer;
	}
	public JasperViewer gerarRelatorioUteisVisita(){
		CadastroVisita gerar = new CadastroVisita();
		//exibe o resultado
		JasperViewer viewer = new JasperViewer( gerar.gerarRelatorioUteisVisitas() , false );
		viewer.setTitle("Visualizador de relatório - Pesquero Representações");
		return viewer;
	}
}
