/**
 * 
 * Autor: Sullyvan Puppi
 *  
 */
package negocios;

//Imports de classes internas necess�rias
import javax.swing.table.DefaultTableModel;

/* Importando classe da camada de persist�ncia respons�vel pela
 * comunica��o com o banco de dados.
 */
import persistencia.CadastroUsuario;

/**
 * @author Sullyvan Puppi
 *
 * Classe respons�vel pelo l�gica de Usu�rios
 */
public class Usuario {

	//Atributo que armazenar� os items em branco
	private String status = "";

	private String txtNomeUsuario;
	private String txtLogin;
	private String grupoUsuario;
	private String txtCargo;
	private String txtSenha;
	private String txtCorrigeSenha;

	/**--------Construtor-------------------------------------------------**/
	public Usuario() {
		super();
	}

	//------Ajuste de dados-----------------------------------------------//
	public void ajustaDados(String txtLogin, String txtSenha, String txtCorrigeSenha, String txtNomeUsuario, String txtCargo, String grupo){
		this.txtNomeUsuario = txtNomeUsuario.trim();
		this.txtLogin = txtLogin.trim();
		this.txtCargo = txtCargo.trim();
		this.txtSenha = txtSenha.trim();
		this.txtCorrigeSenha = txtCorrigeSenha.trim();	
		this.grupoUsuario = grupo.trim();
	}
	
	//--------------Busca de dados-------------------//
	public String getTxtCargo(){
		return txtCargo;
	}
	public String getGrupoUsuario() {
		return grupoUsuario;
	}
	public String getTxtCorrigeSenha() {
		return txtCorrigeSenha;
	}
	public String getTxtNomeUsuario() {
		return txtNomeUsuario;
	}
	public String getTxtSenha() {
		return txtSenha;
	}
	public String getTxtLogin() {
		return txtLogin;
	}
	public String getStatus(){
		return status;
	}
	//------Valida��o de dados do usu�rio----------------------------//
	public int validar(){
		int x =0;
		status="";
		if (getTxtNomeUsuario().equals("")){
			this.status="\nNome do usu�rio inv�lido.";
			x=1;
		}if (getTxtLogin().equals("")){
			this.status+="\nLogin para acesso inv�lido.";
			x+=1;
		}if (getTxtCargo().equals("")){
			this.status+="\nCargo do usu�rio inv�lido.";
			x+=1;
		}if (getGrupoUsuario().equals("Selecione um grupo de usu�rios")){
			this.status+="\nGrupo de usu�rios inv�lido.";
			x+=1;
		}if (getTxtSenha().equals("")){
			this.status+="\nSenha inv�lida.";
			x+=1;
		}if (getTxtCorrigeSenha().equals("")){
			this.status+="\nSenha de compara��o inv�lida.";
			x+=1;
		}if (getTxtCorrigeSenha().equals(getTxtSenha())){
		}else{
			this.status+="\nSenhas n�o conferem.";
			x+= 1;
		}
		return x ;
	}
	
	//------Comandos para a camada de persist�ncia--------------------------------//	
	//Atributo de controle para cadastro
	public int getExiste(){
		int x=0;
		CadastroUsuario cadastrar = new CadastroUsuario();
		cadastrar.verificaExiste(getTxtLogin());
		x=cadastrar.getExiste();
		return x;
	}
	//---Cadastrar usu�rio
	public void cadastrar(){
		CadastroUsuario cadastrar = new CadastroUsuario();
		cadastrar.cadastrar(getTxtLogin(), getTxtSenha(), getTxtNomeUsuario(), getTxtCargo(), getGrupoUsuario());
	}
	
	//----Remover usu�rio
	public void remover(String login){
		CadastroUsuario cadastrar = new CadastroUsuario();
		cadastrar.remover(login);
	}
	
	//----Atributos e m�todos de pesquisa
	DefaultTableModel tabela = new DefaultTableModel();
	
	public DefaultTableModel getTabela(){
		return this.tabela;
	}
	CadastroUsuario consultar = new CadastroUsuario();
		
	//----Consultar cadastro
	public void consultar(String nome, String grupo){
		consultar.consultar(nome, grupo);
		this.tabela = consultar.getTabela();
	}
	//----Consultar para exibir
	public void consultar(String login){
		consultar.consultar(login);
		this.txtNomeUsuario = consultar.getTxtNomeUsuario();
		this.txtLogin = consultar.getTxtLogin();
		this.txtCargo = consultar.getTxtCargo();
		this.grupoUsuario = consultar.getGrupoUsuario();
		this.txtSenha = consultar.getSenha();
	}
	//----Atributo para alterar senha
	private int confirma;
	
	public int getConfirma(){
		return this.confirma;
	}
	//----Consultar senha
	public void consultarSenha(String login, String senha){
		consultar.consultarSenha(login, senha);
		this.confirma = consultar.getConfirma();
	}
	//----Alterar cadastro
	public void alterar(String login){
		CadastroUsuario cadastrar = new CadastroUsuario();
		cadastrar.alterar(getTxtLogin(), getTxtSenha(), getTxtNomeUsuario(), getTxtCargo(), getGrupoUsuario());
	}
}
