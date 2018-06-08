package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CadastroNotificacoes extends ConexaoBD{

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

	public CadastroNotificacoes() {
		super();
	}
	public void ajustaDados(String txtRemetente, String txtDtEnvio, String txtLembrar, String txtAssunto, String txtAnotacao) {
		this.txtRemetente = txtRemetente;
		this.txtDtEnvio = txtDtEnvio;
		this.txtLembrar = txtLembrar;
		this.txtAssunto = txtAssunto;
		this.txtAnotacao = txtAnotacao;
	}
	public void cadastrar(){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_Eventos (nm_Cadastrante, dt_Cadastro, dt_Lembrar, nm_Assunto, ds_Assunto) VALUES (?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, getTxtRemetente());
			stmt.setString(2, getTxtDtEnvio());
			stmt.setString(3, getTxtLembrar());
			stmt.setString(4, getTxtAssunto());
			stmt.setString(5, getTxtAnotacao());

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Evento cadastrado com sucesso","Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void cadastrarVisita(String idVisita, String nmRemetente, String dtEnvio, String dtLembrar, String nmEmpresa, String detalhes){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_Eventos (id_Visita, nm_Cadastrante, dt_Cadastro, dt_Lembrar, nm_Assunto, ds_Assunto) VALUES (?,?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, idVisita);
			stmt.setString(2, nmRemetente);
			stmt.setString(3, dtEnvio);
			stmt.setString(4, dtLembrar);
			stmt.setString(5, nmEmpresa);
			stmt.setString(6, detalhes);

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void removerVisita(String id){
		try{
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());
			Statement st = con.createStatement();											
			String condicao ="DELETE FROM tb_Eventos WHERE id_Visita='"+id+"';";

			st.executeUpdate(condicao);				
			st.close();
			con.close();
			
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public int validaAssunto(String assunto){
		int x = 0;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao = "SELECT nm_Assunto FROM tb_Eventos WHERE nm_Assunto='"+assunto+"'";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			if(rs.next()){
				x = 1;
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
		return x;
	}
	public void consultar(){
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao = "SELECT id_Eventos, nm_Assunto, dt_Lembrar FROM tb_Eventos ORDER BY id_Eventos";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			tabela.addColumn("ID");
			tabela.addColumn("Assunto");
			tabela.addColumn("Data para lembrar");

			while(rs.next()){
				this.registros +=1;
				Object linha[] = new Object[3];
				linha[0] = rs.getInt("id_Eventos");
				linha[1] = rs.getString("nm_Assunto");
				linha[2] = rs.getString("dt_Lembrar");
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
	public void consultarEvento(String id){
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao = "SELECT * FROM tb_Eventos WHERE id_Eventos='"+id+"'";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			rs.next();

			this.txtRemetente = rs.getString("nm_Cadastrante");
			this.txtDtEnvio = rs.getString("dt_Cadastro");
			this.txtLembrar = rs.getString("dt_Lembrar");
			this.txtAssunto = rs.getString("nm_Assunto");
			this.txtAnotacao = rs.getString("ds_Assunto");			

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public int consultarEventosHoje(String dtHoje){
		int x = 0;
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao = "SELECT * FROM tb_Eventos WHERE dt_Lembrar='"+dtHoje+"'";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			while(rs.next()){
				this.ids[x] = ""+rs.getInt("id_Eventos");
				x+=1;
			}
			
			
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
		return x;
	}
	private String[] ids = new String[30];

	//--Retorna os componentes cadastrados
	public String[] getIds(){
		return this.ids;
	}
	public String getFuncionarioAtual(String login){
		String nome = "";
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao = "SELECT nm_Usuario FROM tb_Usuarios WHERE nm_Login='"+login+"';";;

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			if(rs.next()){
				nome = rs.getString("nm_Usuario");	
			}						

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
		return nome;
	}
	public void remover(String id, String assunto){
		try{
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());
			Statement st = con.createStatement();											
			String condicao ="DELETE FROM tb_Eventos WHERE id_Eventos='"+id+"';";

			if(!assunto.equals("Visita")){
				st.executeUpdate(condicao);				
				st.close();
				con.close();
				JOptionPane.showMessageDialog(null,"Evento excluído com sucesso!","Exclusão",1);
			}

		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}	
	}

	//-----Retorno de dados
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
}
