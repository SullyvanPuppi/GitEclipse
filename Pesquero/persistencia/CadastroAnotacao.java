package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CadastroAnotacao extends ConexaoBD{

	private int registros = 0;
	
	private String txtRemetente = null;
	private String cmbDestinatario = null;
	private String txtDtEnvio = null;
	private String txtAssunto = null;
	private String txtAnotacao = null;
	private String login = null;
	
	DefaultTableModel tabelaMsgs = new DefaultTableModel();

	public DefaultTableModel getTabelaMsgs(){
		return this.tabelaMsgs;
	}
	
	public CadastroAnotacao() {
		super();
	}
	public void ajustaDados(String txtRemetente, String cmbDestinatario, String txtDtEnvio, String txtAssunto, String txtAnotacao, String login) {
		this.txtRemetente = txtRemetente;
		this.cmbDestinatario = cmbDestinatario;
		this.txtDtEnvio = txtDtEnvio;
		this.txtAssunto = txtAssunto;
		this.txtAnotacao = txtAnotacao;
		this.login = login;
	}
	public void cadastrar(){
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_Anotacoes (nm_Remetente, nm_Destinatario, nm_Assunto, dt_Envio, ds_Anotacao, nm_Login) VALUES (?,?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, getTxtRemetente());
			stmt.setString(2, getCmbDestinatario());
			stmt.setString(3, getTxtAssunto());
			stmt.setString(4, getTxtDtEnvio());
			stmt.setString(5, getTxtAssunto());
			stmt.setString(6, getLogin());
			
			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Mensagem enviada com sucesso","Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void marcarLido(String login, String nmAssunto){
		String nome = getFuncionarioAtual(login);		
		try{
			Class.forName(getDriverBd());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Prepara o código sql para cadastrar usuário
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_Anotacoes set sg_Lido=? WHERE nm_Destinatario='"+nome+"' AND nm_Assunto='"+nmAssunto+"';");

			//Preenche os campos
			stmt.setBoolean(1, true);
			
			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Leitura da mensagem confirmada com sucesso","Alterado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void remover(String login, String assunto){
		String nome = getFuncionarioAtual(login);
		try{
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());
			Statement st = con.createStatement();											

			String condicao ="DELETE FROM tb_Anotacoes WHERE nm_Destinatario='"+nome+"' AND nm_Assunto='"+assunto+"';";
			
			st.executeUpdate(condicao);//Remover funcionário da tabela anotacoes
			
			st.close();
			con.close();
			
			JOptionPane.showMessageDialog(null,"Mensagem excluída com sucesso!","Exclusão",1);
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}	
	}
	public void consultar(String login){
		String nome = getFuncionarioAtual(login);
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao = "SELECT nm_Assunto, nm_Remetente, dt_Envio, sg_Lido FROM tb_Anotacoes WHERE nm_Destinatario='"+nome+"';";
		
			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			tabelaMsgs.addColumn("Assunto");
			tabelaMsgs.addColumn("Remetente");
			tabelaMsgs.addColumn("Data de envio");
			tabelaMsgs.addColumn("Status");

			while(rs.next()){
				this.registros +=1;
				Object linha[] = new Object[4];
				linha[0] = rs.getString("nm_Assunto");
				linha[1] = rs.getString("nm_Remetente");
				linha[2] = rs.getString("dt_Envio");
				if(rs.getBoolean("sg_Lido")){
					linha[3] = "Confirmado";
				}else{
					linha[3] = "";
				}
				tabelaMsgs.addRow(linha);
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void consultarMsg(String login, String nmAssunto){
		String nome = getFuncionarioAtual(login);		
		try {
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			String condicao = "SELECT * FROM tb_Anotacoes WHERE nm_Destinatario='"+nome+"' AND nm_Assunto='"+nmAssunto+"';";;

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			rs.next();

			this.txtRemetente = rs.getString("nm_Remetente");
			this.cmbDestinatario = rs.getString("nm_Destinatario");
			this.txtDtEnvio = rs.getString("dt_Envio");
			this.txtAssunto = rs.getString("nm_Assunto");
			this.txtAnotacao = rs.getString("ds_Anotacao");
			
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
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
	
	//--Atributo e métodos para combobox usuarios
	private Vector usuarios = new Vector();//Armazena itens da cmbUsuarios

	//--Retorna os componentes cadastrados
	public Vector getUsuarios(){
		return this.usuarios;
	}
	@SuppressWarnings("unchecked")
	public void consultarCmbUsuarios(){
		try {						
			//Estabelece a conexão
			Class.forName(getDriverBd());
			Connection con = DriverManager.getConnection(getUrlBd(),getUsuarioBd(),getSenhaBd());

			//Condições de pesquisa
			String condicao= "SELECT nm_Usuario FROM tb_Usuarios;";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			while(rs.next()){
				usuarios.addElement(rs.getString("nm_Usuario"));
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}	
	//-----Retorno de dados
	public String getCmbDestinatario() {
		return cmbDestinatario;
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
