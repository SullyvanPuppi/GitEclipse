package negocios;

import javax.swing.table.DefaultTableModel;

import net.sf.jasperreports.view.JasperViewer;
import persistencia.CadastroContato;
import persistencia.CadastroVenda;

public class Contato {

	public Contato(){
		super();
	}	
	private int status = 0;
	private String msgStatus = "";
	private int registros = 0;

	private String nmEmpresa = null;
	private String txtNmContato = null;
	private String txtFuncao = null;
	private String txtTelefone = null;
	private String txtTelefone2 = null;
	private String txtEmail = null;
	private String sexo = null;
	private String cnpj = null;

	public String getCnpj() {
		return cnpj;
	}
	public String getSexo() {
		return sexo;
	}
	public String getNmEmpresa() {
		return nmEmpresa;
	}
	public String getTxtEmail() {
		return txtEmail;
	}
	public String getTxtFuncao() {
		return txtFuncao;
	}
	public String getTxtNmContato() {
		return txtNmContato;
	}
	public String getTxtTelefone() {
		return txtTelefone;
	}
	public String getTxtTelefone2() {
		return txtTelefone2;
	}
	public String getMsgStatus(){
		return msgStatus;
	}
	public int registros(){
		return registros;
	}
	public void ajustaDados(String nmEmpresa, String txtNmContato, String txtFuncao, String txtTelefone, String txtTelefone2, String txtEmail, String sexo, String cnpj) {
		this.nmEmpresa = nmEmpresa.trim();
		this.txtNmContato = txtNmContato.trim();
		this.txtFuncao = txtFuncao.trim();
		this.txtTelefone = txtTelefone.trim();
		this.txtTelefone2 = txtTelefone2.trim();
		this.txtEmail = txtEmail.trim();
		this.sexo = sexo.trim();
	}
	public int validaDados(){
		if(getTxtNmContato().equals("")){
			this.status +=1;
			this.msgStatus +="\nNome do contato inválido";
		}
		if(getTxtTelefone().equals("(  )    -")){
			this.status +=1;
			this.msgStatus +="\nTelefone do funcionário inválido";
		}	
		if(getSexo().equals("")){
			this.status +=1;
			this.msgStatus +="\nSexo do contato inválido";
		}
		return status;
	}
	public int verificaExiste(){
		CadastroContato contato = new CadastroContato();
		return contato.verificaExiste(getNmEmpresa(), getTxtNmContato());
	}	
	public void cadastrar(String categoria, String cnpj, String idVisita){
		CadastroContato cadastrar = new CadastroContato();
		cadastrar.ajustaDados(getNmEmpresa(), getTxtNmContato(), getTxtFuncao(), getTxtTelefone(), getTxtTelefone2(), getTxtEmail(), getSexo());
		if(categoria.equals("Representada")){
			cadastrar.cadastrarCttRepresentada();
		}else if(categoria.equals("Cliente")){
			cadastrar.cadastrarCttCliente(cnpj);
		}else if(categoria.equals("Visita")){
			cadastrar.cadastrarCttVisita(Integer.parseInt(idVisita), getNmEmpresa(), getTxtNmContato(), getTxtFuncao(), getTxtTelefone(), getTxtTelefone2(), getTxtEmail(), getSexo());
		}
		cadastrar.cadastrarContato(getNmEmpresa(), categoria);
	}
	public void alterar(String categoria, String nmEmpresa, String idVisita){
		CadastroContato cadastrar = new CadastroContato();
		cadastrar.ajustaDados(nmEmpresa, getTxtNmContato(), getTxtFuncao(), getTxtTelefone(), getTxtTelefone2(), getTxtEmail(), getSexo());
		CadastroVenda consultar = new CadastroVenda();
		if(categoria.equals("Representada")){
			String cnpj = consultar.cnpjRepresentada(nmEmpresa);
			cadastrar.alterarCttRepresentada(getTxtNmContato(), cnpj);
		}else if(categoria.equals("Cliente")){
			String cnpj = consultar.cnpjCliente(nmEmpresa);
			System.out.println(cnpj);
			cadastrar.alterarCttCliente(getTxtNmContato(), cnpj);
		}else if(categoria.equals("Visita")){
			cadastrar.alterarCttVisita(Integer.parseInt(idVisita), getNmEmpresa(), getTxtNmContato());
		}else if(categoria.equals("Funcionário")){
			cadastrar.alterarCttFuncionario(getTxtNmContato());
		}
		cadastrar.alterarContato(getTxtNmContato(), getNmEmpresa(), categoria);
	}
	DefaultTableModel tabelaContatos = new DefaultTableModel();

	public DefaultTableModel getTabelaContatos(){
		return this.tabelaContatos;
	}
	DefaultTableModel tabelaContatosEmpresa = new DefaultTableModel();

	public DefaultTableModel getTabelaContatosEmpresa(){
		return this.tabelaContatosEmpresa;
	}
	public void consultarContatos(String nmContato, String categoria){
		CadastroContato consultar = new CadastroContato();
		consultar.consultarContatos(nmContato, categoria);
		this.registros = consultar.getRegistros();
		this.tabelaContatos = consultar.getTabelaContatos();
	}
	public void consultarContatosEmpresa(String nmEmpresa){
		CadastroContato consultar = new CadastroContato();
		consultar.consultarContatosEmpresa(nmEmpresa);
		this.registros = consultar.getRegistros();
		this.tabelaContatosEmpresa = consultar.getTabelaContatosEmpresa();
	}	
	public void consultarContato(String nmEmpresa, String nmContato){
		CadastroContato consultar = new CadastroContato();
		consultar.consultarContato(nmEmpresa, nmContato);
		this.nmEmpresa = nmEmpresa.trim();
		this.txtNmContato = consultar.getTxtNmContato();
		this.txtFuncao = consultar.getTxtFuncao();
		this.txtTelefone = consultar.getTxtTelefone();
		this.txtTelefone2 = consultar.getTxtTelefone2();
		this.txtEmail = consultar.getTxtEmail();
		this.sexo = consultar.getSexo();
	}	
	public void remover(String nmContato, String nmEmpresa, String cnpjEmpresa, String categoria){
		CadastroContato cadastrar = new CadastroContato();
		cadastrar.remover(nmContato, nmEmpresa, cnpjEmpresa, categoria);
	}
	public JasperViewer gerarRelatorio(String nmContato, String categoria){
		CadastroContato gerar = new CadastroContato();
		//exibe o resultado
		JasperViewer viewer = new JasperViewer( gerar.gerarRelatorio(nmContato, categoria) , false );
		viewer.setTitle("Visualizador de relatório - Pesquero Representações");
		return viewer;
	}
}
