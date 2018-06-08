package negocios;

//Importando a camada responsável pelas funções com o Banco de dados
import persistencia.ConsultaLogin;

public class ValidaLogin {

	private String login;
	private String senha;
	private int permissao;

	public ValidaLogin(String login, String senha){
		this.login=login;
		this.senha=senha;
	}
	public String getLogin(){
		return this.login;
	}
	public String getSenha(){
		return this.senha;
	}
	public void Validar(){
		ConsultaLogin consultar = new ConsultaLogin(getLogin(), getSenha());
		if (consultar.getPermissao()>0 || consultar.getPermissao()<5){
			this.permissao = consultar.getPermissao();
			this.login= consultar.getLogin();
		}else{
			this.permissao = 0;
		}
	}
	public int getPermissao(){
		return permissao;
	}
}