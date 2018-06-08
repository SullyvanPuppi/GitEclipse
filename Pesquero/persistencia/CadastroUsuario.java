package persistencia;

//Imports de classes internas necess�rias
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
 * Classe respons�vel para se comunicar ao banco de dados usu�rios.
 * Esta classe herda a classe Conex�o BD com os dados relativos ao banco de dados.
 * Cadastros referentes a usu�rios.
 * 
 */
public class CadastroUsuario extends ConexaoBD{

	//------Atributos de usu�rio
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
			//Estabelece a conex�o
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o c�digo sql para cadastrar usu�rio
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_Usuarios (nm_Usuario, nm_Cargo, nm_Login, cd_Senha, nm_Grupo) VALUES (?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, usuario);
			stmt.setString(2, cargo);
			stmt.setString(3, login);
			stmt.setString(4, senha);
			stmt.setString(5, grupo);

			//Executa a instru��o SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Usu�rio cadastrado com sucesso!\nUsu�rio:\n"+usuario+"\nLogin:\n"+login+"\nPossui direitos de:\n"+grupo+"","Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conex�o ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}

	//-----------------Verifica exist�ncia de usu�rios cadastrados-------------------//
	private int existe;

	public int getExiste(){
		return existe;
	}

	public void verificaExiste(String cd){
		try {
			//Estabelece a conex�o
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condi��es de pesquisa
			String condicao= "SELECT * FROM tb_Usuarios WHERE nm_Login='"+cd+"';";

			Statement st = con.createStatement();

			//Executa o c�digo sql para pesquisar usu�rio
			ResultSet rs = st.executeQuery(condicao);

			while (rs.next()){
				this.existe= 1;
			}			
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conex�o ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
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
			JOptionPane.showMessageDialog(null,"Usu�rio exclu�do com sucesso!","Exclus�o",1);
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conex�o ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//-------Editar o cadastro--------------------------------------------------------//
	//----Consultar para exibir------------------------//
	public void consultar(String login){
		try {
			//Estabelece a conex�o
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condi��es de pesquisa
			String condicao= "SELECT * FROM tb_Usuarios WHERE nm_Login='"+login+"';";

			Statement st = con.createStatement();

			//Executa o c�digo sql para pesquisar usu�rio
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
			JOptionPane.showMessageDialog(null, "Problemas na conex�o ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
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
			//Estabelece a conex�o
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condi��es de pesquisa
			String condicao= "SELECT cd_Senha FROM tb_Usuarios WHERE nm_Login='"+login+"' AND cd_Senha='"+senha+"';";
			
			Statement st = con.createStatement();

			//Executa o c�digo sql para pesquisar usu�rio
			ResultSet rs = st.executeQuery(condicao);

			if(rs.next()){
				JOptionPane.showMessageDialog(null, "Usu�rio e senha atual confirmados.","Altera��o de senha",1);
				this.confirma = 1;
			}else{
				JOptionPane.showMessageDialog(null, "Usu�rio e senha atual n�o confirmados.","Altera��o de senha",2);
			}
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conex�o ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//--------Alterar dados cadastrados---------------------------------//
	public void alterar(String usuario, String login, String cargo, String grupo, String senha){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conex�o
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o c�digo sql para cadastrar usu�rio
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_Usuarios set nm_Usuario=?, nm_Cargo=?, nm_Grupo=?, cd_Senha=? WHERE nm_Login='"+login+"';");

			//Preenche os campos
			stmt.setString(1, usuario);
			stmt.setString(2, cargo);
			stmt.setString(3, grupo);
			stmt.setString(4, senha);			

			//Executa a instru��o SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Usu�rio alterado com sucesso!\nUsu�rio:\n"+usuario+"\nLogin:\n"+login+"\nPossui direitos de:\n"+grupo+"","Alterado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conex�o ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
}
