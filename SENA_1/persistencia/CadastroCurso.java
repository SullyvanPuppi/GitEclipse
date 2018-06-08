package persistencia;

//Imports de classes internas necessárias
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author Sullyvan Puppi
 *
 * Classe responsável para se comunicar ao banco de dados usuários do SENA 1.
 * Esta classe herda a classe Conexão BD com os dados relativos ao banco de dados.
 * Cadastros referentes a cursos.
 * 
 */
public class CadastroCurso extends ConexaoBD{

	//--Campos para a cadastro de curso
	private String nmCurso;

	private String nmTipo;

	private String nmArea;

	private int qtCiclos;

	private String qtDuracao;

	private String nmCoordenador;

	private String dsCurso;

	//--Campos para cadastro ciclos
	private int cdCiclo;

	private String nmCiclo;

	private String dsCiclo;

	//--Campos para cadastro CursoComponente
	private String nmComponente;

	private int cdCicloComponente;

	private String dsComponente;

	/**----------Construtor--------------**/
	public CadastroCurso(){
		super();
	}
	//--------------Retornam dados respectivos-------------------------------//
	public int getCdCiclo() {
		return cdCiclo;
	}
	public String getDsCiclo() {
		return dsCiclo;
	}
	public int getCdCicloComponente(){
		return cdCicloComponente;
	}
	public String getDsComponente() {
		return dsComponente;
	}
	public String getNmArea() {
		return nmArea;
	}
	public String getNmCiclo() {
		return nmCiclo;
	}
	public String getNmComponente() {
		return nmComponente;
	}
	public String getNmCurso() {
		return nmCurso;
	}
	public String getNmTipo() {
		return nmTipo;
	}
	public int getQtCiclos() {
		return qtCiclos;
	}
	public String getQtDuracao() {
		return qtDuracao;
	}
	public String getNmCoordenador(){
		return nmCoordenador;
	}
	public String getDsCurso(){
		return dsCurso;
	}
	//------------Comandos para o banco de dados-------------------------------------//
	private int existe = 0;//Armazena 1 se existir 0 se não

	//--Retorna 1 se existe 0 se não
	public int getExiste(){
		return this.existe;
	}
	//----------Verifica se ja existe o curso
	public void verificaExiste(String nome){
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao= "SELECT * FROM tb_Cursos WHERE nm_Curso='"+nome+"';";

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
	//----Verifica se existe ciclo
	private int existeCiclo;
	public int getExisteCiclo(){
		return this.existeCiclo;
	}
	public void verificaExisteCiclo(String nmCurso, int cd){
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao= "SELECT * FROM tb_Ciclos WHERE nm_Curso='"+nmCurso+"' AND cd_Ciclo="+cd+";";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar curso
			ResultSet rs = st.executeQuery(condicao);

			while (rs.next()){
				this.existeCiclo= 1;
			}			
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao bando de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}	
	//----Verifica se existe componente
	private int existeComponente;

	public int getExisteComponente(){
		return this.existeComponente;
	}
	public void verificaExisteComponente(String nmCurso, String nmComponente, int cdCiclo){
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao= "SELECT * FROM tb_CursoComponentes WHERE nm_Curso='"+nmCurso+"' AND nm_Componente='"+nmComponente+"' AND cd_Ciclo="+cdCiclo+";";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar curso
			ResultSet rs = st.executeQuery(condicao);

			while (rs.next()){
				this.existeComponente= 1;
			}			
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}	
	//----------Cadastrar curso
	public void cadastrarCurso(String nmCurso, String tipo, String area, int qtCiclo, String duracao, String nmCoordenador, String dsCurso){
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Prepara o código sql para cadastrar curso tb_Cursos
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_Cursos (nm_Curso, nm_Tipo, nm_Area, qt_Ciclos, qt_DuracaoCiclo, nm_Coordenador, ds_Curso) VALUES (?,?,?,?,?,?,?);");
			
			//Preenche os campos
			stmt.setString(1, nmCurso.trim());
			stmt.setString(2, tipo.trim());
			stmt.setString(3, area.trim());
			stmt.setInt(4, qtCiclo);
			stmt.setString(5, duracao.trim());
			stmt.setString(6, nmCoordenador.trim());
			stmt.setString(7, dsCurso.trim());
			
			stmt.execute();
			stmt.close();
			
			//Prepara o código sql para cadastrar curso tb_CursoDivulgacao
			PreparedStatement stmt2 = con.prepareStatement("INSERT INTO tb_CursoDivulgacao (nm_Curso, nm_Tipo, nm_Area, qt_Ciclos, qt_DuracaoCiclo, nm_Coordenador, ds_Curso) VALUES (?,?,?,?,?,?,?);");
			//Preenche os campos
			stmt2.setString(1, nmCurso.trim());
			stmt2.setString(2, tipo.trim());
			stmt2.setString(3, area.trim());
			stmt2.setInt(4, qtCiclo);
			stmt2.setString(5, duracao.trim());
			stmt2.setString(6, nmCoordenador.trim());
			stmt2.setString(7, dsCurso.trim());
			
			//Executa a instrução SQL
			stmt2.execute();
			stmt2.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Curso cadastrado com sucesso!\nCurso:\n"+nmCurso+"\nTipo:\n"+tipo+"\nÁrea de atuação:\n"+area+"","Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//---------Cadastro de ciclo do curso
	public void cadastrarCiclo(String nmCurso, int cdCiclo, String nmCiclo, String dsCiclo){
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Prepara o código sql para cadastrar ciclo tb_Ciclos
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_Ciclos (cd_Ciclo, nm_Curso, nm_Ciclo, ds_Ciclo) VALUES (?,?,?,?);");
			//Prepara o código sql para cadastrar ciclo tb_CursoDivulgcacao
			PreparedStatement stmt2 = con.prepareStatement("INSERT INTO tb_CursoDivulgacaoCiclos (cd_Ciclo, nm_Ciclo, nm_Curso, ds_Ciclo) VALUES (?,?,?,?);");
			
			//Preenche os campos
			stmt.setInt(1, cdCiclo);
			stmt.setString(2, nmCurso.trim());
			stmt.setString(3, nmCiclo.trim());
			stmt.setString(4, dsCiclo.trim());
			
			//Preenche os campos
			stmt2.setInt(1, cdCiclo);
			stmt2.setString(2, nmCiclo.trim());
			stmt2.setString(3, nmCurso.trim());
			stmt2.setString(4, dsCiclo.trim());

			//Executa a instrução SQL
			stmt.execute();
			stmt2.execute();
			stmt.close();
			stmt2.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Ciclo cadastrado com sucesso!\nCiclo:\n"+nmCiclo+"\nNo curso:\n"+nmCurso+"","Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//--------Cadastro de componente no curso
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
	public void cadastrarComponenteCurso(String nmComponente, int cdCiclo, String nmCurso, String dsComponente){
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Prepara o código sql para cadastrar componente
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_CursoComponentes (nm_Componente, nm_Curso, cd_Ciclo, nm_Ciclo, ds_Componente) VALUES (?,?,?,?,?);");
			PreparedStatement stmt2 = con.prepareStatement("INSERT INTO tb_CursoDivulgacaoComponentes (nm_Componente, cd_Ciclo, nm_Ciclo, nm_Curso, ds_Componente) VALUES (?,?,?,?,?);");
			
			//Preenche os campos
			stmt.setString(1, nmComponente.trim());
			stmt.setString(2, nmCurso.trim());
			stmt.setInt(3, cdCiclo);
			stmt.setString(4, getNmCicloComponente());
			stmt.setString(5, dsComponente);
			

			//Preenche os campos
			stmt2.setString(1, nmComponente.trim());
			stmt2.setInt(2, cdCiclo);
			stmt2.setString(3, getNmCicloComponente());
			stmt2.setString(4, dsComponente);
			stmt2.setString(5, nmCurso.trim());

			//Condições de pesquisa
			String condicao = "SELECT cd_Ciclo FROM tb_Ciclos WHERE nm_Curso='"+nmCurso+"' AND cd_Ciclo="+cdCiclo+";";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar se existe o ciclo cadastrado
			ResultSet rs = st.executeQuery(condicao);

			if(rs.next()){//Somente se existir ciclo selecionado cadastar
				//Executa a instrução SQL
				stmt.execute();
				stmt2.execute();				
				JOptionPane.showMessageDialog(null,"Componente curricular cadastrado com sucesso!\nCiclo:\n"+nmComponente+"\nNo curso:\n"+nmCurso+"","Cadastrado",1);
			}else{//Se não existir ciclo não cadastrar
				JOptionPane.showMessageDialog(null,"Ciclo não existe para componente curricular ser adicionado a ele!","Erro",2);
			}				
			st.close();
			stmt.close();
			stmt2.close();
			con.close();
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//--------Consultar componentes para exibir em combobox
	//---Atributo e métodos para combobox componentes
	private Vector componentes = new Vector();//Armazena itens da cmbComponentes

	//--Retorna os componentes cadastrados
	public Vector getComponentes(){
		return this.componentes;
	}
	@SuppressWarnings("unchecked")
	public void consultarCmbComponentes(){
		try {						
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao= "SELECT nm_Componente FROM tb_Componentes;";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);
			
			while(rs.next()){
				componentes.addElement(rs.getString("nm_Componente"));
			}
			
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//---Consultar para tabela
	//-------Atributos e Métodos para pesquisa

	//--Tabela cursos--//	
	DefaultTableModel tabela = new DefaultTableModel();

	public DefaultTableModel getTabela(){
		return this.tabela;
	}
	Object linha[] = new Object[3];

	//----Colunas da tabela cursos
	public void addColunas(){
		tabela.addColumn("Nome do curso");
		tabela.addColumn("Tipo");
		tabela.addColumn("Área");
	}
	//--Tabela ciclos no curso--//
	DefaultTableModel tabelaCiclos = new DefaultTableModel();

	public DefaultTableModel getTabelaCiclos(){
		return this.tabelaCiclos;
	}
	Object linhaCiclos[] = new Object[2];

	//----Colunas da tabela
	public void addColunasCiclos(){
		tabelaCiclos.addColumn("Ordenação");
		tabelaCiclos.addColumn("Título");
	}
	//--Tabela componentes no curso curso--//
	DefaultTableModel tabelaCicloComponentes = new DefaultTableModel();

	public DefaultTableModel getTabelaCicloComponentes(){
		return this.tabelaCicloComponentes;
	}
	Object linhaCicloComponente[] = new Object[1];
	public void addColunasCicloComponentes(){
		tabelaCicloComponentes.addColumn("Componente curricular");
	}

	//--Tabela componentes no curso CursoComponente
	DefaultTableModel tabelaCursoComponentes = new DefaultTableModel();

	public DefaultTableModel getTabelaCursoComponentes(){
		return this.tabelaCursoComponentes;
	}
	Object linhaCursoComponente[] = new Object[1];

	//----Colunas da tabela cursos
	public void addColunasCursoComponentes(){
		tabelaCursoComponentes.addColumn("Ciclo");
		tabelaCursoComponentes.addColumn("Componente curricular");
	}
	//-------Pesquisar cadastro---------------------------------------------//
	public void consultar(String nome, String tipo, String area){
		addColunas();
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao;
			if(nome.equals("")){
				if(tipo.equals("Todos")){
					if(area.equals("Todas")){
						condicao= "SELECT nm_Curso, nm_Tipo, nm_Area FROM tb_Cursos ORDER BY nm_Curso;";
					}else{
						condicao= "SELECT nm_Curso, nm_Tipo, nm_Area FROM tb_Cursos WHERE nm_Area='"+area+"'ORDER BY nm_Curso;";
					}
				}else{
					if(area.equals("Todas")){
						condicao= "SELECT nm_Curso, nm_Tipo, nm_Area FROM tb_Cursos WHERE nm_Tipo='"+tipo+"'ORDER BY nm_Curso;";
					}else{
						condicao= "SELECT nm_Curso, nm_Tipo, nm_Area FROM tb_Cursos WHERE nm_Tipo='"+tipo+"' AND nm_Area='"+area+"' ORDER BY nm_Curso;";
					}
				}
			}else{
				if(tipo.equals("Todos")){
					if(area.equals("Todas")){
						condicao= "SELECT nm_Curso, nm_Tipo, nm_Area FROM tb_Cursos WHERE nm_Curso='"+nome+"' ORDER BY nm_Curso;";
					}else{
						condicao= "SELECT nm_Curso, nm_Tipo, nm_Area FROM tb_Cursos WHERE nm_Curso='"+nome+"' AND nm_Area='"+area+"'ORDER BY nm_Curso;";
					}
				}else{
					if(area.equals("Todas")){
						condicao= "SELECT nm_Curso, nm_Tipo, nm_Area FROM tb_Cursos WHERE nm_Curso='"+nome+"' AND nm_Tipo='"+tipo+"'ORDER BY nm_Curso;";
					}else{
						condicao= "SELECT nm_Curso, nm_Tipo, nm_Area FROM tb_Cursos WHERE nm_Curso='"+nome+"' AND nm_Tipo='"+tipo+"' AND nm_Area='"+area+"' ORDER BY nm_Curso;";
					}
				}
			}
			Statement st = con.createStatement();
			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);


			//Percorre a consulta
			while(rs.next()){
				Object linha[] = new Object[3];
				linha[0] = rs.getString("nm_Curso");
				linha[1] = rs.getString("nm_Tipo");
				linha[2] = rs.getString("nm_Area");
				tabela.addRow(linha);
			}
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//------Tabela de ciclos cadastrados no curso
	public void consultarCiclos(String nmCurso){
		addColunasCiclos();
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao = "SELECT cd_Ciclo, nm_Ciclo FROM tb_Ciclos WHERE nm_Curso='"+nmCurso+"' ORDER BY cd_Ciclo;";;

			Statement st = con.createStatement();
			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			//Percorre a consulta
			while(rs.next()){
				Object linhaCiclos[] = new Object[2];
				linhaCiclos[0] = rs.getString("cd_Ciclo");
				linhaCiclos[1] = rs.getString("nm_Ciclo");
				tabelaCiclos.addRow(linhaCiclos);
			}
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//------Tabela de componentes cadastrados no curso
	public void consultarCicloComponentes(String nmCurso, String cdCiclo){
		addColunasCicloComponentes();
		try {
			String condicao;
			if(cdCiclo.equals("")){
				condicao= "SELECT nm_Componente FROM tb_CursoComponentes WHERE nm_Curso='"+nmCurso+"' ORDER BY nm_Componente;";
			}else{
				condicao = "SELECT nm_Componente FROM tb_CursoComponentes WHERE nm_Curso='"+nmCurso+"' AND cd_Ciclo='"+cdCiclo+"' ORDER BY nm_Componente;";
			}			

			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			Statement st = con.createStatement();
			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			//Percorre a consulta
			while(rs.next()){
				@SuppressWarnings("unused")
				Object linhaCicloComponentes[] = new Object[1];
				linhaCicloComponentes[0] = rs.getString("nm_Componente");
				tabelaCicloComponentes.addRow(linhaCicloComponentes);
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//---Tabela de componentes cadastrados no curso para abaComponentes
	public void consultarCursoComponentes(String nmCurso){
		addColunasCursoComponentes();
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao = "SELECT cd_Ciclo, nm_Componente FROM tb_CursoComponentes WHERE nm_Curso='"+nmCurso+"' ORDER BY cd_Ciclo;";;

			Statement st = con.createStatement();
			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			//Percorre a consulta
			while(rs.next()){
				@SuppressWarnings("unused")
				Object linhaCursoComponentes[] = new Object[2];
				linhaCursoComponentes[0] = rs.getString("cd_Ciclo");
				linhaCursoComponentes[1] = rs.getString("nm_Componente");
				tabelaCursoComponentes.addRow(linhaCursoComponentes);
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//--Consultar descrição do componente no curso
	public void consultarDsComponente(String nmCurso, String nmComponente, String cdCiclo){
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
				condicao = "SELECT ds_Componente FROM tb_CursoComponentes WHERE nm_Componente='"+nmComponente+"' AND nm_Curso='"+nmCurso+"' AND cd_Ciclo="+cdCiclo+";";
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
	public void consultarDsComponente(String nmComponente){
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao = "SELECT ds_Componente FROM tb_Componentes WHERE nm_Componente='"+nmComponente+"';";

			Statement st = con.createStatement();
			
			if(nmComponente.equals("----------")){
				
			}else{
				if(nmComponente.equals("")){
					JOptionPane.showMessageDialog(null, "Não existe componente curricular cadastrado.", "Informação", 1);
				}else{
					//Executa o código sql para pesquisar usuário
					ResultSet rs = st.executeQuery(condicao);

					rs.next();

					if(rs.getString("ds_Componente").equals("")){
						this.dsComponente = "Sem descrição cadastrada para este componente.";
					}else{
						this.dsComponente = rs.getString("ds_Componente");
					}	
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
	//--Consultar descrição do ciclo no curso
	public void consultarDsCiclo(String nmCurso, String cdCiclo){
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao = "SELECT ds_Ciclo FROM tb_Ciclos WHERE nm_Curso='"+nmCurso+"' AND cd_Ciclo="+cdCiclo+";";

			Statement st = con.createStatement();
			
			if(cdCiclo.equals("")){
				JOptionPane.showMessageDialog(null, "Não existe ciclo cadastrado.", "Informação", 1);
			}else{
				//Executa o código sql para pesquisar usuário
				ResultSet rs = st.executeQuery(condicao);

				rs.next();

				if(rs.getString("ds_Ciclo").equals("")){
					this.dsCiclo = "Sem descrição cadastrada para este ciclo.";
				}else{
					this.dsCiclo = rs.getString("ds_Ciclo");
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
	//----Consultar para exibir
	//--Curso
	public void consultar(String nome){
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao = "SELECT * FROM tb_Cursos WHERE nm_Curso='"+nome+"';";;

			Statement st = con.createStatement();
			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			rs.next();

			this.nmCurso = rs.getString("nm_Curso");
			this.nmTipo = rs.getString("nm_Tipo");
			this.nmArea = rs.getString("nm_Area");
			this.qtCiclos = rs.getInt("qt_Ciclos");
			this.qtDuracao = rs.getString("qt_DuracaoCiclo");
			this.nmCoordenador = rs.getString("nm_Coordenador");
			this.dsCurso = rs.getString("ds_Curso");

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//--Ciclos

	//------------Remover dados-------------------------------------------//
	//--Curso
	public void removerCurso(String nome){
		try{
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());
			Statement st = con.createStatement();											

			String condicao ="DELETE FROM tb_Cursos WHERE nm_Curso='"+nome+"';";
			String condicao2 = "DELETE FROM tb_Ciclos WHERE nm_Curso='"+nome+"';";
			String condicao3 = "DELETE FROM tb_CursoComponentes WHERE nm_Curso='"+nome+"';";
			String condicao4 = "DELETE FROM tb_CursoDivulgacao WHERE nm_Curso='"+nome+"';";
			String condicao5 = "DELETE FROM tb_CursoDivulgacaoCiclos WHERE nm_Curso='"+nome+"';";
			String condicao6 = "DELETE FROM tb_CursoDivulgacaoComponentes WHERE nm_Curso='"+nome+"';";
			String condicao7 = "DELETE FROM tb_CursoDivulgacaoProfessores WHERE nm_Curso='"+nome+"';";
			String condicao8 = "DELETE FROM tb_CursoDivulgacaoAtividades WHERE nm_Curso='"+nome+"';";
			String condicao9 = "DELETE FROM tb_CursoDivulgacaoTurmas WHERE nm_Curso='"+nome+"';";
			String condicao10 = "DELETE FROM tb_CursoDivulgacaoLinks WHERE nm_Curso='"+nome+"';";
			String condicao11 = "DELETE FROM tb_CursoDivulgacaoDownloads WHERE nm_Curso='"+nome+"';";
			String condicao12 = "DELETE FROM tb_CursoDivulgacaoFotos WHERE nm_Curso='"+nome+"';";

			st.executeUpdate(condicao);//Remover curso da tabela cursos
			st.executeUpdate(condicao2);//Remover ciclos do curso da tabela ciclos
			st.executeUpdate(condicao3);//Remover componentes do curso da tabela cursoComponentes
			st.executeUpdate(condicao4);//Remover curso do curso da tabela cursoDivulgacao
			st.executeUpdate(condicao5);//Remover ciclos do curso da tabela cursoDivulgacaoCiclos
			st.executeUpdate(condicao6);//Remover componentes do curso da tabela cursoDivulgacaoComponentes
			st.executeUpdate(condicao7);//Remover dados do curso da tabela cursoDivulgacaoProfessores
			st.executeUpdate(condicao8);//Remover dados do curso da tabela cursoDivulgacaoAtividades
			st.executeUpdate(condicao9);//Remover dados do curso da tabela cursoDivulgacaoTurmas
			st.executeUpdate(condicao10);//Remover dados do curso da tabela cursoDivulgacaoLinks
			st.executeUpdate(condicao11);//Remover dados do curso da tabela cursoDivulgacaoDownloads
			st.executeUpdate(condicao12);//Remover dados do curso da tabela cursoDivulgacaoFotos

			st.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Curso excluído com sucesso!","Exclusão",1);
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//--Ciclo
	public void removerCiclo(String nmCurso, String cdCiclo){
		try{
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());
			Statement st = con.createStatement();											

			String condicao = "DELETE FROM tb_Ciclos WHERE nm_Curso='"+nmCurso+"' AND cd_Ciclo="+cdCiclo+";";
			String condicao2 = "DELETE FROM tb_CursoComponentes WHERE nm_Curso='"+nmCurso+"' AND cd_Ciclo="+cdCiclo+";";
			String condicao3= "DELETE FROM tb_CursoDivulgacaoCiclos WHERE nm_Curso='"+nmCurso+"' AND cd_Ciclo="+cdCiclo+";";
			String condicao4 = "DELETE FROM tb_CursoDivulgacaoComponentes WHERE nm_Curso='"+nmCurso+"' AND cd_Ciclo="+cdCiclo+";";
			
			
			st.executeUpdate(condicao);//Remover ciclo da tabela ciclos
			st.executeUpdate(condicao2);//Remover ciclo da tabela cursoComponentes
			st.executeUpdate(condicao3);//Remover ciclos do curso da tabela cursoDivulgacaoCiclos
			st.executeUpdate(condicao4);//Remover coomponentes do curso da tabela cursoDivulgacaoComponentes
			
			st.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Ciclo excluído com sucesso!","Exclusão",1);
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//--Componente
	public void removerComponente(String nmCurso, String nmComponente, String cdCiclo){
		try{
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());
			Statement st = con.createStatement();											

			String condicao = "DELETE FROM tb_CursoComponentes WHERE nm_Componente='"+nmComponente+"' AND cd_Ciclo="+cdCiclo+" AND nm_Curso='"+nmCurso+"';";
			String condicao2 = "DELETE FROM tb_CursoDivulgacaoComponentes WHERE nm_Componente='"+nmComponente+"' AND cd_Ciclo="+cdCiclo+" AND nm_Curso='"+nmCurso+"';";

			st.executeUpdate(condicao);//Remover ciclo da tabela cursoComponentes
			st.executeUpdate(condicao2);//Remover coomponentes do curso da tabela cursoDivulgacaoComponentes
			
			st.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Componente curricular excluído do curso com sucesso!","Exclusão",1);
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//-------------Alterar dados------------------------------------------------------------//
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
	public void alterarComponente(String nmComponente, int cdCiclo, String nmCurso, String dsComponente){
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Prepara o código sql para alterar curso
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_CursoComponentes set nm_Componente=?, tb_Cursos_nm_Curso=?, cd_Ciclo=?, nm_Ciclo=?, nm_Curso=?, ds_Componente=? WHERE nm_Curso='"+nmCurso+"' AND nm_Componente='"+nmComponente+"' AND cd_Ciclo='"+cdCiclo+"';");
			PreparedStatement stmt2 = con.prepareStatement("UPDATE tb_CursoDivulgacaoComponentes set nm_Componente=?, cd_Ciclo=?, nm_Ciclo=?, nm_Curso=?, ds_Componente=? WHERE nm_Curso='"+nmCurso+"' AND nm_Componente='"+nmComponente+"' AND cd_Ciclo='"+cdCiclo+"';");				
			
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
			stmt.setString(5, nmCurso.trim());
			stmt.setString(6, dsComponente.trim());
			
			//Preenche os campos
			stmt2.setString(1, nmComponente.trim());
			stmt2.setInt(2, cdCiclo);
			stmt2.setString(3, getNmCicloComponente());
			stmt2.setString(4, nmCurso.trim());
			stmt2.setString(5, dsComponente.trim());
			
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
