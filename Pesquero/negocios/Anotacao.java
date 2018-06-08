package negocios;

import java.util.Calendar;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import persistencia.CadastroAnotacao;

public class Anotacao {

	private int status = 0;
	private String msgStatus = "";
	private int registros = 0;
	
	private String txtRemetente = null;
	private String cmbDestinatario = null;
	private String txtDtEnvio = null;
	private String txtAssunto = null;
	private String txtAnotacao = null;
	private String login = null;
	
	public Anotacao() {
		super();
	}
	public void ajustaDados(String txtRemetente, String cmbDestinatario, String txtDtEnvio, String txtAssunto, String txtAnotacao, String login) {
		this.txtRemetente = txtRemetente.trim();
		this.cmbDestinatario = cmbDestinatario.trim();
		this.txtDtEnvio = txtDtEnvio.trim();
		this.txtAssunto = txtAssunto.trim();
		this.txtAnotacao = txtAnotacao.trim();
		this.login = login.trim();
	}
	public int validaDados(){
		if(getCmbDestinatario().equals("--------------------------------")){
			this.status +=1;
			this.msgStatus +="\nNome do destinatário da mensagem inválido";	
		}
		if(getTxtAssunto().equals("")){
			this.status +=1;
			this.msgStatus +="\nAssunto da mensagem inválido";
		}
		if(getTxtAnotacao().equals("")){
			this.status +=1;
			this.msgStatus +="\nContéudo da mensagem inválido";
		}
		
		return status;
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
	public void cadastrar(){
		CadastroAnotacao cadastrar = new CadastroAnotacao();
		cadastrar.ajustaDados(getTxtRemetente(), getCmbDestinatario(), getTxtDtEnvio(), getTxtAssunto(), getTxtAnotacao(), getLogin());
		cadastrar.cadastrar();
	}
	public void remover(String nmFuncionario, String assunto){
		CadastroAnotacao remover = new CadastroAnotacao();
		remover.remover(nmFuncionario, assunto);
	}
	public void consultarMsg(String login, String nmAssunto){
		CadastroAnotacao consultar = new CadastroAnotacao();
		consultar.consultarMsg(login, nmAssunto);
		this.txtRemetente = consultar.getTxtRemetente();
		this.cmbDestinatario = consultar.getCmbDestinatario();
		this.txtDtEnvio = consultar.getTxtDtEnvio();
		this.txtAssunto = consultar.getTxtAssunto();
		this.txtAnotacao = consultar.getTxtAnotacao();
	}
	DefaultTableModel tabelaMsgs = new DefaultTableModel();

	public DefaultTableModel getTabelaMsgs(){
		return this.tabelaMsgs;
	}
	public void consultarMsgs(String nmFuncionario){
		CadastroAnotacao consultar = new CadastroAnotacao();
		consultar.consultar(nmFuncionario);
		this.tabelaMsgs = consultar.getTabelaMsgs();
	}
	public String nmAtual(String login){
		CadastroAnotacao consultar = new CadastroAnotacao();
		return consultar.getFuncionarioAtual(login);
	}
	public void marcarLido(String login, String nmAssunto){
		CadastroAnotacao consultar = new CadastroAnotacao();
		consultar.marcarLido(login, nmAssunto);
	}
	public Vector consultarUsuarios(){
		CadastroAnotacao consultar = new CadastroAnotacao();
		consultar.consultarCmbUsuarios();
		return consultar.getUsuarios();
	}	
	//-----Retorno de dados
	public String getCmbDestinatario() {
		return cmbDestinatario;
	}
	public String getMsgStatus() {
		return msgStatus;
	}
	public int getRegistros() {
		return registros;
	}
	public String getTxtAnotacao() {
		return txtAnotacao;
	}
	public String getTxtAssunto() {
		return txtAssunto;
	}
	public String getTxtDtEnvio() {
		return txtDtEnvio;
	}
	public String getTxtRemetente() {
		return txtRemetente;
	}
	public String getLogin(){
		return login;
	}
	
	
}
