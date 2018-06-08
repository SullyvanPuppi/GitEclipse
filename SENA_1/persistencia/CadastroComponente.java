package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CadastroComponente extends ConexaoBD{
	
	private String nmComponente;
	
	private String dsComponente;

	public String getDsComponente() {
		return dsComponente;
	}

	public String getNmComponente() {
		return nmComponente;
	}

	public void ajustaComponente(String nmComponente, String dsComponente) {
		this.nmComponente = nmComponente;
		this.dsComponente = dsComponente;
	}
	public CadastroComponente(){
		super();
	}//------------Comandos para o banco de dados-------------------------------------//
	private int existe = 0;//Armazena 1 se existir 0 se não

	//--Retorna 1 se existe 0 se não
	public int getExiste(){
		return this.existe;
	}
	//----------Verifica se ja existe o curso
	public void verificaExiste(String nmComponente){
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao= "SELECT nm_Componente FROM tb_Componentes WHERE nm_Componente='"+nmComponente+"';";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar curso
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
	//--Tabela cursos componentes
	DefaultTableModel tabelaComponentes = new DefaultTableModel();

	public DefaultTableModel getTabelaComponenteCurso(){
		return this.tabelaComponentes;
	}
	//----Colunas da tabela cursos
	public void addColunas(){
		tabelaComponentes.addColumn("Curso");
	}
	//--Consultar descrição do componente no curso
	public void consultarDsComponente(String nmComponente){
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			String condicao = "";
			Statement st = con.createStatement();
			
			//Condições de pesquisa
			if(nmComponente.equals("")){
				JOptionPane.showMessageDialog(null, "Não existe componente curricular cadastrado.", "Informação", 1);
			}else{
				condicao = "SELECT ds_Componente FROM tb_Componentes WHERE nm_Componente='"+nmComponente+"';";
				//Executa o código sql para pesquisar usuário
				ResultSet rs = st.executeQuery(condicao);
				rs.next();
				if(rs.getString("ds_Componente").equals("")){
					this.dsComponente = "Sem descrição cadastrada para este componente.";
				}else{
					this.dsComponente = rs.getString("ds_Componente");
				}
			}		
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void consultarComponenteCurso(String nmComponente){
		addColunas();
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao = "SELECT nm_Curso FROM tb_CursoComponentes WHERE nm_Componente='"+nmComponente+"' ORDER BY nm_Curso;";;

			Statement st = con.createStatement();
			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			//Percorre a consulta
			while(rs.next()){
				@SuppressWarnings("unused")
				Object linha[] = new Object[1];
				linha[0] = rs.getString("nm_Curso");
				tabelaComponentes.addRow(linha);
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	
	
	public void cadastrarComponente(String nmComponente, String dsComponente){
			try{
				Class.forName(getDriver());
				//Estabelece a conexão
				Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

				//Prepara o código sql para cadastrar componente
				PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_Componentes (nm_Componente, ds_Componente) VALUES (?,?);");
				
				//Preenche os campos
				stmt.setString(1, nmComponente.trim());
				stmt.setString(2, dsComponente.trim());

				stmt.execute();
				
				JOptionPane.showMessageDialog(null,"Componente curricular cadastrado com sucesso!\nCiclo:\n"+nmComponente+"","Cadastrado",1);
				
				stmt.close();
				con.close();
			}
			catch(SQLException ex){
				JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
			}
	}
	public void removerComponente(String nmComponente){
		try{
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());
			Statement st = con.createStatement();											

			String condicao = "DELETE FROM tb_Componentes WHERE nm_Componente='"+nmComponente+"';";
			String condicao2 = "DELETE FROM tb_CursoComponentes WHERE nm_Componente='"+nmComponente+"';";
			String condicao3= "DELETE FROM tb_ProfessorComponentes nm_Componente='"+nmComponente+"';";
			String condicao4 = "DELETE FROM tb_CursoDivulgacaoComponentes nm_Componente='"+nmComponente+"';";
			String condicao5 = "DELETE FROM tb_CursoDivulgacaoProfessores nm_Componente='"+nmComponente+"';";
			
			
			st.executeUpdate(condicao);//Remover componente da tabela Componentes
			st.executeUpdate(condicao2);//Remover componente da tabela cursoComponentes
			st.executeUpdate(condicao3);//Remover componente da tabela ProfessorComponentes 
			st.executeUpdate(condicao4);//Remover componente da tabela CursoDivulgacaoComponentes 
			st.executeUpdate(condicao5);//Remover componente da tabela CursoDivulgacaoProfessores 
			
			st.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Componente curricular excluído com sucesso!","Exclusão",1);
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//--Componente curricular
	public void alterarComponente(String nmComponente, String dsComponente){
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Prepara o código sql para alterar curso
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_Componentes set nm_Componente, ds_Componente=? WHERE nm_Componente="+nmComponente+";");
			PreparedStatement stmt2 = con.prepareStatement("UPDATE tb_CursoComponentes set ds_Componente=? WHERE nm_Componente="+nmComponente+";");
			PreparedStatement stmt3 = con.prepareStatement("UPDATE tb_CursoDivulgacaoComponentes set ds_Componente=? WHERE WHERE nm_Componente="+nmComponente+";");				
			
			//Preenche os campos
			stmt.setString(1, dsComponente.trim());
			stmt.execute();
			stmt.close();
			
			//Preenche os campos
			stmt2.setString(1, dsComponente.trim());
			stmt2.execute();
			stmt2.close();
			
			stmt3.setString(1, dsComponente.trim());
			stmt3.execute();
			stmt3.close();
			
			con.close();
			JOptionPane.showMessageDialog(null,"Componente curricular alterado com sucesso!","Alteração",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
}