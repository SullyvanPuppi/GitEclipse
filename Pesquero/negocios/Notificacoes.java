package negocios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;

import persistencia.CadastroNotificacoes;

public class Notificacoes {

	private int status = 0;
	private String msgStatus = "";
	private int registros = 0;

	private String txtRemetente = null;
	private String txtDtEnvio = null;
	private String txtLembrar = null;
	private String txtAssunto = null;
	private String txtAnotacao = null;

	DefaultTableModel tabela = new DefaultTableModel();

	public DefaultTableModel getTabela(){
		return this.tabela;
	}

	public Notificacoes() {
		super();
	}
	public void ajustaDados(String txtRemetente, String txtDtEnvio, String txtLembrar, String txtAssunto, String txtAnotacao) {
		this.txtRemetente = txtRemetente.trim();
		this.txtDtEnvio = txtDtEnvio.trim();
		this.txtLembrar = txtLembrar.trim();
		this.txtAssunto = txtAssunto.trim();
		this.txtAnotacao = txtAnotacao.trim();
	}
	public int validaDados(){
		if(getTxtLembrar().equals("/  /")){
			this.status +=1;
			this.msgStatus +="\nData para lembrar evento inválido";	
		}else{
			if(validaData(getTxtLembrar())>0){
				this.status +=1;
				this.msgStatus +="\nData para lembrar evento inválido";	
			}else{
				if(validaDataLembrar(getHoje(), getTxtLembrar())>0){
					this.status +=1;
					this.msgStatus +="\nData para lembrar evento inválido";
				}
			}
		}
		if(getTxtAssunto().equals("")){
			this.status +=1;
			this.msgStatus +="\nAssunto do evento inválido";	
		}else{
			if(getTxtAssunto().equals("Visita") || getTxtAssunto().equals("visita")){
				this.status +=1;
				this.msgStatus +="\nAssunto do evento inválido";	
			}else{
				CadastroNotificacoes consultar = new CadastroNotificacoes();
				if(consultar.validaAssunto(getTxtAssunto())>0){
					this.status +=1;
					this.msgStatus +="\nAssunto do evento ja existe";
				}	
			}
		}
		if(getTxtAnotacao().equals("")){
			this.status +=1;
			this.msgStatus +="\nConteúdo da mensagem inválido";	
		}
		return status;
	}
	public int verificaExiste(){
		CadastroNotificacoes consultar = new CadastroNotificacoes();
		return consultar.validaAssunto(getTxtAssunto()); 
	}
	public void cadastrar(){
		CadastroNotificacoes cadastrar = new CadastroNotificacoes();
		cadastrar.ajustaDados(getTxtRemetente(), getTxtDtEnvio(), getTxtLembrar(), getTxtAssunto(), getTxtAnotacao());
		cadastrar.cadastrar();
	}
	public void consultar(){
		CadastroNotificacoes consultar = new CadastroNotificacoes();
		consultar.consultar();
		this.tabela = consultar.getTabela();
	}
	public void consultarEvento(String assunto){
		CadastroNotificacoes consultar = new CadastroNotificacoes();
		consultar.consultarEvento(assunto);
		this.txtRemetente = consultar.getTxtRemetente();
		this.txtDtEnvio = consultar.getTxtDtEnvio();
		this.txtLembrar = consultar.getTxtLembrar();
		this.txtAssunto = consultar.getTxtAssunto();
		this.txtAnotacao = consultar.getTxtAnotacao();
	}
	public void remover(String id, String assunto){
		CadastroNotificacoes consultar = new CadastroNotificacoes();
		consultar.remover(id, assunto);	
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
	public int validaDataLembrar(String dtCompra, String dtEntrega){//Se retorna 1, data de entrega inválida
		int x = 0;
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat sdf2 = new SimpleDateFormat("ddMMyyyy");
		
		Date dataCompra = null;
		Date dataEntrega = null;

		try {
			dataCompra= sdf2.parse(dtCompra);
			dataEntrega= sdf.parse(dtEntrega);
		} catch (Exception e) {}

		Calendar date1 = new GregorianCalendar();
		Calendar date2 = new GregorianCalendar();

		date1.setTime(dataCompra);
		date2.setTime(dataEntrega);

		if (dataEntrega.before(dataCompra)) {
			x = 1;
		}		
		return x;
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
	public String nmAtual(String login){
		CadastroNotificacoes consultar = new CadastroNotificacoes();
		return consultar.getFuncionarioAtual(login);
	}
	public int getEventosHoje(){
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
		String d = data.substring(0, 2);
		String m = data.substring(2, 4);
		String a = data.substring(4, 8);
		String dt = ""+d+"/"+m+"/"+a;
		CadastroNotificacoes consultar = new CadastroNotificacoes();
		this.ids = consultar.getIds();
		return consultar.consultarEventosHoje(dt);
	}
	private String[] ids = new String[30];//Armazena itens

	//--Retorna os componentes cadastrados
	public String[] getIds(){
		return this.ids;
	}
	//-------Retorno de dados
	public String getTxtAnotacao() {
		return txtAnotacao;
	}
	public String getTxtAssunto() {
		return txtAssunto;
	}
	public String getTxtDtEnvio() {
		return txtDtEnvio;
	}
	public String getTxtLembrar() {
		return txtLembrar;
	}
	public String getTxtRemetente() {
		return txtRemetente;
	}
	public int getRegistros() {
		return registros;
	}	
	public String getMsgStatus(){
		return msgStatus;
	}

}
