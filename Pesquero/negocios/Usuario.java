/**
 * 
 * Autor: Sullyvan Puppi
 *  
 */
package negocios;

import persistencia.CadastroUsuario;

/**
 * @author Sullyvan Puppi
 *
 * Classe responsável pelo lógica de Usuários
 */
public class Usuario {

	//Atributo que armazenará os items em branco
	private String status = "";

	private String usuario;
	private String login;
	private String cargo;
	private String grupo;
	private String senha;
	private String corrigeSenha;

	/**--------Construtor-------------------------------------------------**/
	public Usuario() {
		super();
	}

	public String getCargo() {
		return cargo;
	}

	public String getCorrigeSenha() {
		return corrigeSenha;
	}

	public String getGrupo() {
		return grupo;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getStatus(){
		return status;
	}
	
	public void ajustaDados(String usuario, String login, String cargo, String grupo, String senha, String corrigeSenha) {
		this.usuario = usuario;
		this.login = login;
		this.cargo = cargo;
		this.grupo = grupo;
		this.senha = senha;
		this.corrigeSenha = corrigeSenha;
	}

	//------Validação de dados do usuário----------------------------//
	public int validar(){
		int x =0;
		status="";
		if (getUsuario().equals("")){
			this.status="\nNome do usuário inválido.";
			x=1;
		}if (getLogin().equals("")){
			this.status+="\nLogin para acesso inválido.";
			x+=1;
		}if (getCargo().equals("")){
			this.status+="\nCargo do usuário inválido.";
			x+=1;
		}if (getGrupo().equals("Selecione um grupo de usuários")){
			this.status+="\nGrupo de usuários inválido.";
			x+=1;
		}if (getSenha().equals("")){
			this.status+="\nSenha inválida.";
			x+=1;
		}if (getCorrigeSenha().equals("")){
			this.status+="\nSenha de comparação inválida.";
			x+=1;
		}if (getSenha().equals(getCorrigeSenha())){
		}else{
			this.status+="\nSenhas não conferem.";
			x+= 1;
		}
		return x ;
	}
	
	//------Comandos para a camada de persistência--------------------------------//	
	//Atributo de controle para cadastro
	public int getExiste(){
		int x=0;
		CadastroUsuario cadastrar = new CadastroUsuario();
		cadastrar.verificaExiste(getLogin());
		x=cadastrar.getExiste();
		return x;
	}
	//---Cadastrar usuário
	public void cadastrar(){
		CadastroUsuario cadastrar = new CadastroUsuario();
		cadastrar.cadastrar(getUsuario(), getLogin(), getCargo(), getGrupo(), getSenha());
	}
	
	//----Remover usuário
	public void remover(String login){
		CadastroUsuario cadastrar = new CadastroUsuario();
		cadastrar.remover(login);
	}
	
	CadastroUsuario consultar = new CadastroUsuario();
		
	//----Consultar cadastro
	//----Consultar para exibir
	public void consultar(String login){
		consultar.consultar(login);
		this.usuario = consultar.getUsuario();
		this.login = consultar.getLogin();
		this.cargo = consultar.getCargo();
		this.grupo = consultar.getGrupo();
		this.senha = consultar.getSenha();
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
	public void alterar(){
		CadastroUsuario cadastrar = new CadastroUsuario();
		cadastrar.alterar(getUsuario(), getLogin(), getCargo(), getGrupo(), getSenha());
	}
}
