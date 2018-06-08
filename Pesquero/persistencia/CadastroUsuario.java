package persistencia;

//Imports de classes internas necessárias
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

/**
 * @author Sullyvan Puppi
 *
 * Classe responsável para se comunicar ao banco de dados usuários.
 * Esta classe herda a classe Conexão BD com os dados relativos ao banco de dados.
 * Cadastros referentes a usuários.
 * 
 */
public class CadastroUsuario extends ConexaoBD{

	//------Atributos de usuário
	private String usuario;
	private String login;
	private String cargo;
	private String grupo;
	private String senha;
	private String corrigeSenha;

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

	//------Construtor-----------------------------------------//
	public CadastroUsuario() {
		super();
	}

	//----------Cadastrar dados--------------------------------------------------//
	public void cadastrar(String usuario, String login, String cargo, String grupo, String senha){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_Usuarios (nm_Usuario, nm_Cargo, nm_Login, cd_Senha, nm_Grupo) VALUES (?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, usuario);
			stmt.setString(2, cargo);
			stmt.setString(3, login);
			stmt.setString(4, senha);
			stmt.setString(5, grupo);

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Usuário cadastrado com sucesso!\nUsuário:\n"+usuario+"\nLogin:\n"+login+"\nPossui direitos de:\n"+grupo+"","Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}

	//-----------------Verifica existência de usuários cadastrados-------------------//
	private int existe;

	public int getExiste(){
		return existe;
	}

	public void verificaExiste(String cd){
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT * FROM tb_Usuarios WHERE nm_Login='"+cd+"';";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			while (rs.next()){
				this.existe= 1;
			}			
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//-------Remover dados-------------------------------------------------------------//
	public void remover(String login){
		try{
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());
			Statement st = con.createStatement();																																																																																																																																																																																																											
			String condicao ="DELETE FROM tb_Usuarios WHERE nm_Login='"+login+"';";

			st.executeUpdate(condicao);
			st.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Usuário excluído com sucesso!","Exclusão",1);
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//-------Editar o cadastro--------------------------------------------------------//
	//----Consultar para exibir------------------------//
	public void consultar(String login){
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT * FROM tb_Usuarios WHERE nm_Login='"+login+"';";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);
			rs.next();

			this.usuario = rs.getString("nm_Usuario");
			this.login = rs.getString("nm_Login");
			this.cargo = rs.getString("nm_Cargo");
			this.grupo = rs.getString("nm_Grupo");
			this.senha = rs.getString("cd_Senha");

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//--------Atributo para alterar senha
	private int confirma = 0;

	public int getConfirma(){
		return this.confirma;
	}
	//--------Consultar senha-------------------------------------------//
	public void consultarSenha(String login, String senha){
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT cd_Senha FROM tb_Usuarios WHERE nm_Login='"+login+"' AND cd_Senha='"+senha+"';";
			
			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			if(rs.next()){
				JOptionPane.showMessageDialog(null, "Usuário e senha atual confirmados.","Alteração de senha",1);
				this.confirma = 1;
			}else{
				JOptionPane.showMessageDialog(null, "Usuário e senha atual não confirmados.","Alteração de senha",2);
			}
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//--------Alterar dados cadastrados---------------------------------//
	public void alterar(String usuario, String login, String cargo, String grupo, String senha){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_Usuarios set nm_Usuario=?, nm_Cargo=?, nm_Grupo=?, cd_Senha=? WHERE nm_Login='"+login+"';");

			//Preenche os campos
			stmt.setString(1, usuario);
			stmt.setString(2, cargo);
			stmt.setString(3, grupo);
			stmt.setString(4, senha);			

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Usuário alterado com sucesso!\nUsuário:\n"+usuario+"\nLogin:\n"+login+"\nPossui direitos de:\n"+grupo+"","Alterado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
}
