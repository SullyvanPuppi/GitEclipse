/**
 * 
 * Autor: Sullyvan Puppi
 *  
 */
package negocios;

//Imports de classes internas necessárias
import javax.swing.table.DefaultTableModel;

/* Importando classe da camada de persistência responsável pela
 * comunicação com o banco de dados.
 */
import persistencia.CadastroUsuario;

/**
 * @author Sullyvan Puppi
 *
 * Classe responsável pelo lógica de Usuários
 */
public class Usuario {

	//Atributo que armazenará os items em branco
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
	//------Validação de dados do usuário----------------------------//
	public int validar(){
		int x =0;
		status="";
		if (getTxtNomeUsuario().equals("")){
			this.status="\nNome do usuário inválido.";
			x=1;
		}if (getTxtLogin().equals("")){
			this.status+="\nLogin para acesso inválido.";
			x+=1;
		}if (getTxtCargo().equals("")){
			this.status+="\nCargo do usuário inválido.";
			x+=1;
		}if (getGrupoUsuario().equals("Selecione um grupo de usuários")){
			this.status+="\nGrupo de usuários inválido.";
			x+=1;
		}if (getTxtSenha().equals("")){
			this.status+="\nSenha inválida.";
			x+=1;
		}if (getTxtCorrigeSenha().equals("")){
			this.status+="\nSenha de comparação inválida.";
			x+=1;
		}if (getTxtCorrigeSenha().equals(getTxtSenha())){
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
		cadastrar.verificaExiste(getTxtLogin());
		x=cadastrar.getExiste();
		return x;
	}
	//---Cadastrar usuário
	public void cadastrar(){
		CadastroUsuario cadastrar = new CadastroUsuario();
		cadastrar.cadastrar(getTxtLogin(), getTxtSenha(), getTxtNomeUsuario(), getTxtCargo(), getGrupoUsuario());
	}
	
	//----Remover usuário
	public void remover(String login){
		CadastroUsuario cadastrar = new CadastroUsuario();
		cadastrar.remover(login);
	}
	
	//----Atributos e métodos de pesquisa
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
