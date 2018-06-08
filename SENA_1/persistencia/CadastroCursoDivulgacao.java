package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class CadastroCursoDivulgacao extends ConexaoBD{

	
//	-------------Alterar dados------------------------------------------------------------//
	//--Curso
	public void alterarCurso(String nmCurso, String tipo, String area, int qtCiclo, String duracao, String nmCoordenador, String dsCurso){
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Prepara o código sql para alterar curso
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_Cursos set nm_Curso=?, nm_Tipo=?, nm_Area=?, qt_Ciclos=?, qt_DuracaoCiclo=?, nm_Coordenador=?, ds_Curso=? WHERE nm_Curso='"+nmCurso+"';");
			PreparedStatement stmt2 = con.prepareStatement("UPDATE tb_CursoDivulgacao set nm_Curso=?, nm_Tipo=?, nm_Area=?, qt_Ciclos=?, qt_DuracaoCiclo=?, nm_Coordenador=?, ds_Curso=? WHERE nm_Curso='"+nmCurso+"';");
			
			//Preenche os campos
			stmt.setString(1, nmCurso.trim());
			stmt.setString(2, tipo.trim());
			stmt.setString(3, area.trim());
			stmt.setInt(4, qtCiclo);
			stmt.setString(5, duracao.trim());
			stmt.setString(6, nmCoordenador.trim());
			stmt.setString(7, dsCurso.trim());
			
			//Preenche os campos
			stmt2.setString(1, nmCurso.trim());
			stmt2.setString(2, tipo.trim());
			stmt2.setString(3, area.trim());
			stmt2.setInt(4, qtCiclo);
			stmt2.setString(5, duracao.trim());
			stmt2.setString(6, nmCoordenador.trim());
			stmt2.setString(7, dsCurso.trim());
			
			//Executa a instrução SQL
			stmt.execute();
			stmt2.execute();
			stmt.close();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Curso alterado com sucesso!\nCurso:\n"+nmCurso+"\nTipo:\n"+tipo+"\nÁrea de atuação:\n"+area+"","Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//--Ciclo
	public void alterarCiclo(String nmCurso, int cdCiclo, String nmCiclo, String dsCiclo){
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Prepara o código sql para alterar curso
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_Ciclos set tb_Cursos_nm_Curso=?, nm_Ciclo=?, nm_Curso=?, ds_Ciclo=? WHERE nm_Curso='"+nmCurso+"' AND cd_Ciclo='"+cdCiclo+"';");
			PreparedStatement stmt2 = con.prepareStatement("UPDATE tb_CursoDivulgacaoCiclos set nm_Ciclo=?, nm_Curso=?, ds_Ciclo=? WHERE nm_Curso='"+nmCurso+"' AND cd_Ciclo='"+cdCiclo+"';");			
			
			//Preenche os campos
			stmt.setString(1, nmCurso.trim());
			stmt.setString(2, nmCiclo.trim());
			stmt.setString(3, nmCurso.trim());
			stmt.setString(4, dsCiclo.trim());
						
			//Preenche os campos
			stmt2.setString(1, nmCiclo.trim());
			stmt2.setString(2, nmCurso.trim());
			stmt2.setString(3, dsCiclo.trim());
			
			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			stmt2.execute();
			stmt2.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Ciclo alterado com sucesso!\nCiclo:\n"+nmCiclo+"\nNo curso:\n"+nmCurso+"","Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}	
	//--Componente curricular
	private String nmCicloComponente;
	
	public String getNmCicloComponente(){
		return this.nmCicloComponente;
	}
	public void ajustaNmCiclo(String nmCurso, int cdCiclo){
		try {
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Pesquisar nome do ciclo
			String condicao= "SELECT nm_Ciclo FROM tb_Ciclos WHERE nm_Curso='"+nmCurso+"' AND cd_Ciclo="+cdCiclo+";";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar ciclo
			ResultSet rs = st.executeQuery(condicao);

			while(rs.next()){
				this.nmCicloComponente = rs.getString("nm_Ciclo");	
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	public void alterarComponente(String nmComponente, int cdCiclo, String dsComponente, String nmCurso){
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Prepara o código sql para alterar curso
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_CursoComponentes set nm_Componente=?, tb_Cursos_nm_Curso=?, cd_Ciclo=?, nm_Ciclo=?, ds_Componente=?, nm_Curso=? WHERE nm_Curso='"+nmCurso+"' AND nm_Componente='"+nmComponente+"' AND cd_Ciclo='"+cdCiclo+"';");
			PreparedStatement stmt2 = con.prepareStatement("UPDATE tb_CursoDivulgacaoComponentes set nm_Componente=?, cd_Ciclo=?, nm_Ciclo=?, ds_Componente=?, nm_Curso=? WHERE nm_Curso='"+nmCurso+"' AND nm_Componente='"+nmComponente+"' AND cd_Ciclo='"+cdCiclo+"';");				
			
			//Condições de pesquisa
			String condicao = "SELECT cd_Ciclo FROM tb_Ciclos WHERE nm_Curso='"+nmCurso+"' AND cd_Ciclo="+cdCiclo+";";

			Statement st = con.createStatement();
			
			//Executa o código sql para pesquisar se existe o ciclo cadastrado
			ResultSet rs = st.executeQuery(condicao);

			//Preenche os campos
			stmt.setString(1, nmComponente.trim());
			stmt.setString(2, nmCurso.trim());
			stmt.setInt(3, cdCiclo);
			stmt.setString(4, getNmCicloComponente());
			stmt.setString(5, dsComponente.trim());
			stmt.setString(6, nmCurso.trim());
			
			//Preenche os campos
			stmt2.setString(1, nmComponente.trim());
			stmt2.setInt(2, cdCiclo);
			stmt2.setString(3, getNmCicloComponente());
			stmt2.setString(4, dsComponente.trim());
			stmt2.setString(5, nmCurso.trim());
						
			if(rs.next()){//Somente se existir ciclo selecionado cadastar
				//Executa a instrução SQL
				stmt.execute();
				stmt2.execute();
				JOptionPane.showMessageDialog(null,"Componente curricular alterado com sucesso!\nCiclo:\n"+nmComponente+"\nNo curso:\n"+nmCurso+"","Cadastrado",1);
			}else{//Se não existir ciclo não cadastrar
				JOptionPane.showMessageDialog(null,"Ciclo não existe para componente curricular ser adicionado a ele!","Erro",2);
			}
			stmt.close();
			stmt.close();
			con.close();
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
}
