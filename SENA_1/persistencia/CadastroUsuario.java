package persistencia;

//Imports de classes internas necessárias
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author Sullyvan Puppi
 *
 * Classe responsável para se comunicar ao banco de dados usuários do SENA 1.
 * Esta classe herda a classe Conexão BD com os dados relativos ao banco de dados.
 * Cadastros referentes a usuários do SENA 1.
 * 
 */
public class CadastroUsuario extends ConexaoBD{

	//------Atributos de usuário
	private String txtNomeUsuario;
	private String txtLogin;
	private String grupoUsuario;
	private String txtCargo;
	private String txtSenha;

	//-----Retorna conteúdo de atributos

	public String getGrupoUsuario() {
		return grupoUsuario;
	}

	public String getTxtCargo() {
		return txtCargo;
	}

	public String getTxtLogin() {
		return txtLogin;
	}

	public String getTxtNomeUsuario() {
		return txtNomeUsuario;
	}

	public String getTxtSenha() {
		return txtSenha;
	}

	//------Construtor-----------------------------------------//
	public CadastroUsuario() {
		super();
	}

	//----------Cadastrar dados--------------------------------------------------//

	public void cadastrar(String txtLogin, String txtSenha, String txtNomeUsuario, String txtCargo, String grupoUsuario){
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_usuarios_sena_um (nm_Login, cd_Senha, nm_Usuario, ds_Cargo, nm_Grupo) VALUES (?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, txtLogin);
			stmt.setString(2, txtSenha);
			stmt.setString(3, txtNomeUsuario);
			stmt.setString(4, txtCargo);
			stmt.setString(5, grupoUsuario);

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Usuário cadastrado com sucesso!\nUsuário:\n"+txtNomeUsuario+"\nLogin:\n"+txtLogin+"\nPossui direitos de:\n"+grupoUsuario+"","Cadastrado",1);
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
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao= "SELECT * FROM tb_usuarios_sena_um WHERE nm_Login='"+cd+"';";

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
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());
			Statement st = con.createStatement();																																																																																																																																																																																																											
			String condicao ="DELETE FROM tb_usuarios_sena_um WHERE nm_Login='"+login+"';";

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

	//-------Atributos e Métodos para pesquisa
	DefaultTableModel tabela = new DefaultTableModel();

	public DefaultTableModel getTabela(){
		return this.tabela;
	}
	Object linha[] = new Object[4];

	//----Colunas da tabela
	public void addColunas(){
		tabela.addColumn("Nome");
		tabela.addColumn("Login");
		tabela.addColumn("Cargo");
		tabela.addColumn("Grupo de usuário");
	}

	//-------Pesquisar cadastro---------------------------------------------//
	public void consultar(String nome, String grupo){
		addColunas();
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao;
			if(nome.equals("")){
				if(grupo.equals("Selecione um grupo de usuários")){
					condicao= "SELECT nm_Login, nm_Usuario, ds_Cargo, nm_Grupo FROM tb_usuarios_sena_um ORDER BY nm_Usuario;";
				}else{
					condicao= "SELECT nm_Login, nm_Usuario, ds_Cargo, nm_Grupo FROM tb_usuarios_sena_um WHERE nm_Grupo='"+grupo+"' ORDER BY nm_Usuario;";
				}
			}else{
				if(grupo.equals("Selecione um grupo de usuários")){
					condicao= "SELECT nm_Login, nm_Usuario, ds_Cargo, nm_Grupo FROM tb_usuarios_sena_um WHERE nm_Usuario='"+nome+"' ORDER BY nm_Usuario;";
				}else{
					condicao= "SELECT nm_Login, nm_Usuario, ds_Cargo, nm_Grupo FROM tb_usuarios_sena_um WHERE nm_Usuario='"+nome+"' AND nm_Grupo='"+grupo+"' ORDER BY nm_Usuario;";
				}					
			}

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			//Percorre a consulta
			while(rs.next()){
				Object linha[] = new Object[4];
				linha[0] = rs.getString("nm_Usuario");
				linha[1] = rs.getString("nm_Login");
				linha[2] = rs.getString("ds_Cargo");
				linha[3] = rs.getString("nm_Grupo");
				tabela.addRow(linha);
			}
			
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//----Consultar para exibir------------------------//
	public void consultar(String login){
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao= "SELECT * FROM tb_usuarios_sena_um WHERE nm_Login='"+login+"';";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);
			rs.next();

			this.txtNomeUsuario = rs.getString("nm_Usuario");
			this.txtLogin = rs.getString("nm_Login");
			this.txtCargo = rs.getString("ds_Cargo");
			this.grupoUsuario = rs.getString("nm_Grupo");
			this.txtSenha = rs.getString("cd_Senha");

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
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao= "SELECT cd_Senha FROM tb_usuarios_sena_um WHERE nm_Login='"+login+"' AND cd_Senha='"+senha+"';";

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
	public void alterar(String login, String txtSenha, String txtNomeUsuario, String txtCargo, String grupoUsuario){
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_usuarios_sena_um set cd_Senha=?, nm_Usuario=?, ds_Cargo=?, nm_Grupo=? WHERE nm_Login='"+login+"';");

			//Preenche os campos
			stmt.setString(1,login);
			stmt.setString(1, txtSenha);
			stmt.setString(2, txtNomeUsuario);
			stmt.setString(3, txtCargo);
			stmt.setString(4, grupoUsuario);

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Usuário alterado com sucesso!\nUsuário:\n"+txtNomeUsuario+"\nLogin:\n"+login+"\nPossui direitos de:\n"+grupoUsuario+"","Alterado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
}
