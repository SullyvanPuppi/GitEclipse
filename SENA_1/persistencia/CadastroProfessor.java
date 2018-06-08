package persistencia;

//--Imports de classes internas do java
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
 * Cadastros referentes a professores.
 * 
 */
public class CadastroProfessor extends ConexaoBD{

//	-----Tabela professores

	private String cdMatricula;

	private String nmProfessor;

	private String cdCpf;

	private String cdRg;
	
	private String rgOrgao;

	private String sexo;

	private String dtNascimento;

	private String naturalidade;
	
	private String naturalidadeEstado;

	private String nacionalidade;

	private String endereco;

	private String numero;

	private String complemento;

	private String bairro;

	private String cidade;

	private String estado;

	private String cep;

	private String telefone;

	private String email;

	private String nmPai;

	private String nmMae;

	private String dsCaminhoFoto;
	
	private String dtAdmissaoIe;

	//---Tabela ProfessorComponente

	private String nmComponente;

	private String dsComponente;

	private String dtAdmissao;

	//---Tabela usuários do sena dois

	private String nmLogin;

	private String cdSenha;

	//-------------Novo cadastro de professor
	//----Ajusta matricula do professor
	private int matriculaNova;
	public int getMatriculaNova(){
		return this.matriculaNova;
	}
	public void ajustaMatricula(){
		try {						
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao= "SELECT cd_MatriculaProfessor FROM tb_Professores ORDER BY cd_MatriculaProfessor DESC;";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			if(rs.next()){
				this.matriculaNova = rs.getInt("cd_MatriculaProfessor")+1;
			}else{
				this.matriculaNova = 1;
			}
			
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//-----Verifica se ja existe professor
	private int existeProfessor = 0;

	public int getExisteProfessor(){
		return this.existeProfessor;
	}
	public void verificaExisteProfessor(String matricula){
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao= "SELECT * FROM tb_Professores WHERE cd_MatriculaProfessor="+matricula+";";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar curso
			ResultSet rs = st.executeQuery(condicao);

			while (rs.next()){
				this.existeProfessor= 1;
			}
			
			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//--Verifica se professor ja ministra componente curricular
	private int existeComponente = 0;
	public int getExisteComponente(){
		return existeComponente;
	}
	public void verificaComponente(String matricula, String nmComponente){
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao= "SELECT nm_Componente FROM tb_ProfessorComponentes WHERE cd_MatriculaProfessor="+matricula+" AND nm_Componente='"+nmComponente+"';";

			Statement st = con.createStatement();

			//Executa o código sql para pesquisar curso
			ResultSet rs = st.executeQuery(condicao);

			if(rs.next()){
				this.existeComponente = 1;
			}else{
				this.existeComponente = 0;
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}

	//---------------------Cadastrar--------------------------------------------------------------------------------------//
	public void cadastrarProfessor(String cdMatricula, String nmProfessor, String cdCpf, String cdRg, String RgOrgao, String sexo, String dtNascimento, String naturalidade, String naturalidadeEstado, String nacionalidade, String endereco, String numero, String complemento, String bairro, String cidade, String estado, String cep, String telefone, String email, String nmPai, String nmMae, String dsCaminhoFoto, String dtAdmissao, String dsCaminhoFotoSenaQuatro){
		/* cadastrar professor nas tabelas:
		 * tb_Professores
		 * tb_DivulgacaoProfessor
		 */
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Prepara o código sql para cadastrar curso tb_Cursos
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_Professores (cd_MatriculaProfessor, nm_Professor, cd_Cpf, cd_Rg, sg_RgOrgao, nm_Sexo, dt_Nascimento, nm_Naturalidade, sg_EstadoNatural, nm_Nacionalidade, nm_Endereco, cd_Numero, ds_Complemento, nm_Bairro, nm_Cidade, sg_Estado, cd_Cep, cd_Telefone, nm_Email, nm_Pai, nm_Mae, ds_CaminhoFoto, dt_Admissao, ds_CaminhoFotoSenaQuatro) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

			//Preenche os campos
			stmt.setString(1, cdMatricula.trim());
			stmt.setString(2, nmProfessor.trim());
			stmt.setString(3, cdCpf.trim());
			stmt.setString(4, cdRg.trim());
			stmt.setString(5, RgOrgao.trim());
			stmt.setString(6, sexo.trim());
			stmt.setString(7, dtNascimento.trim());
			stmt.setString(8, naturalidade.trim());
			stmt.setString(9, naturalidadeEstado.trim());
			stmt.setString(10, nacionalidade.trim());
			stmt.setString(11, endereco.trim());
			stmt.setString(12, numero);
			stmt.setString(13, complemento.trim());
			stmt.setString(14, bairro.trim());
			stmt.setString(15, cidade.trim());
			stmt.setString(16, estado.trim());
			stmt.setString(17, cep.trim());
			stmt.setString(18, telefone.trim());
			stmt.setString(19, email.trim());
			stmt.setString(20, nmPai.trim());
			stmt.setString(21, nmMae.trim());
			stmt.setString(22, dsCaminhoFoto);
			stmt.setString(23, dtAdmissao.trim());
			stmt.setString(24, dsCaminhoFotoSenaQuatro);

			stmt.execute();
			stmt.close();

			//Prepara o código sql para cadastrar curso tb_ProfessorDivulgacao
			PreparedStatement stmt2 = con.prepareStatement("INSERT INTO tb_ProfessorDivulgacao (cd_MatriculaProfessor, nm_Professor, ds_CaminhoFoto, dt_Admissao, sg_Exibir) VALUES (?,?,?,?,?);");
			//Preenche os campos
			stmt2.setString(1, cdMatricula);
			stmt2.setString(2, nmProfessor.trim());
			stmt2.setString(3, dsCaminhoFotoSenaQuatro);
			stmt2.setString(4, dtAdmissao.trim());
			stmt2.setInt(5, 1);//Opção inicial de exibir perfil
			
			//Executa a instrução SQL
			stmt2.execute();
			stmt2.close();

			//Prepara o código sql para cadastrar curso tb_Usuarios_Sena_Dois
			PreparedStatement stmt3 = con.prepareStatement("INSERT INTO tb_Usuarios_Sena_Dois (cd_MatriculaProfessor, nm_Professor, nm_Login, cd_Senha) VALUES (?,?,?,?);");
			//Preenche os campos
			stmt3.setString(1, cdMatricula.trim());
			stmt3.setString(2, nmProfessor.trim());
			stmt3.setString(3, cdMatricula.trim());
			stmt3.setString(4, cdMatricula.trim());
			//Executa a instrução SQL
			stmt3.execute();
			stmt3.close();
			
			//Prepara o código sql para cadastrar curso tb_ProfessoresSemComponentes
			PreparedStatement stmt4 = con.prepareStatement("INSERT INTO tb_ProfessoresSemComponentes (cd_MatriculaProfessor, nm_Professor) VALUES (?,?);");
			//Preenche os campos
			stmt4.setString(1, cdMatricula.trim());
			stmt4.setString(2, nmProfessor.trim());
			
			//Executa a instrução SQL
			stmt4.execute();
			stmt4.close();
			
			con.close();
			JOptionPane.showMessageDialog(null,"Professor cadastrado com sucesso!\nProfessor:\n"+nmProfessor+"","Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}

	}
	//---Cadastrar componente curricular para o professor
	public void cadastrarComponente(String cdMatricula, String nmProfessor, String componente, String dtAdmissao){
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());
			
			//Prepara o código sql para cadastrar curso tb_Cursos
			PreparedStatement stmt = con.prepareStatement("INSERT INTO tb_ProfessorComponentes (cd_MatriculaProfessor, nm_Professor, nm_Componente, dt_Admissao) VALUES (?,?,?,?);");

			stmt.setString(1, cdMatricula);
			stmt.setString(2, nmProfessor);
			stmt.setString(3, componente);
			stmt.setString(4, dtAdmissao);

			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			
			//Condições de pesquisa
			String condicao2 = "SELECT * FROM tb_ProfessoresSemComponentes WHERE cd_MatriculaProfessor="+cdMatricula+";";

			Statement st2 = con.createStatement();

			//Executa o código sql para pesquisar curso
			ResultSet rs = st2.executeQuery(condicao2);

			if(rs.next()){
				String condicao = "DELETE FROM tb_ProfessoresSemComponentes WHERE cd_MatriculaProfessor="+cdMatricula+";";
				st2.executeUpdate(condicao);//Remover professor da tabela de professores sem componentes
				st2.close();
			}
			con.close();
			JOptionPane.showMessageDialog(null,"Componente curricular associado ao professor com sucesso!\nComponente curricular:\n"+componente+"\nProfessor: \n"+nmProfessor+"","Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//---Cadastrar senha
	public void NovaSenha(String cdMatricula, String nmProfessor){
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			///Prepara o código sql para alterar curso tb_Usuarios_Sena_Dois
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_Usuarios_Sena_Dois set nm_Login=?, cd_Senha=? WHERE cd_MatriculaProfessor="+cdMatricula+";");
			//Preenche os campos
			stmt.setString(1, cdMatricula.trim());
			stmt.setString(2, cdMatricula.trim());
		
			//Executa a instrução SQL
			stmt.execute();
			stmt.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Gerado nova senha para o professor:\n\n"+nmProfessor+"\n\n","Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//---------------------Alterar--------------------------------------------------------------------------------------//
	public void alterarProfessor(String cdMatricula, String nmProfessor, String cdCpf, String cdRg, String RgOrgao, String sexo, String dtNascimento, String naturalidade, String nacionalidade, String endereco, String numero, String complemento, String bairro, String cidade, String estado, String cep, String telefone, String email, String nmPai, String nmMae, String dsCaminhoFoto){
		try{
			Class.forName(getDriver());
			//Estabelece a conexão
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Prepara o código sql para cadastrar curso tb_Cursos
			PreparedStatement stmt = con.prepareStatement("UPDATE tb_Professores SET nm_Professor=?, cd_Cpf=?, cd_Rg=?, sg_RgOrgao=?, nm_Sexo=?, dt_Nascimento=?, nm_Naturalidade=?, nm_Nacionalidade=?, nm_Endereco=?, cd_Numero=?, ds_Complemento=?, nm_Bairro=?, nm_Cidade=?, sg_Estado=?, cd_Cep=?, cd_Telefone=?, nm_Email=?, nm_Pai=?, nm_Mae=?, ds_CaminhoFoto=? WHERE cd_MatriculaProfessor="+cdMatricula+";");

			//Preenche os campos
			stmt.setString(1, nmProfessor.trim());
			stmt.setString(2, cdCpf.trim());
			stmt.setString(3, cdRg.trim());
			stmt.setString(4, RgOrgao.trim());
			stmt.setString(5, sexo.trim());
			stmt.setString(6, dtNascimento.trim());
			stmt.setString(7, naturalidade.trim());
			stmt.setString(8, nacionalidade.trim());
			stmt.setString(9, endereco.trim());
			stmt.setString(10, numero);
			stmt.setString(11, complemento.trim());
			stmt.setString(12, bairro.trim());
			stmt.setString(13, cidade.trim());
			stmt.setString(14, estado.trim());
			stmt.setString(15, cep.trim());
			stmt.setString(16, telefone.trim());
			stmt.setString(17, email.trim());
			stmt.setString(18, nmPai.trim());
			stmt.setString(19, nmMae.trim());
			stmt.setString(20, dsCaminhoFoto);

			stmt.execute();
			stmt.close();

			//Prepara o código sql para cadastrar curso tb_ProfessorDivulgacao
			PreparedStatement stmt2 = con.prepareStatement("UPDATE tb_ProfessorDivulgacao SET nm_Professor=?, ds_CaminhoFoto=? WHERE cd_MatriculaProfessor="+cdMatricula+";");

			//Preenche os campos
			stmt2.setString(1, nmProfessor.trim());
			stmt2.setString(2, dsCaminhoFoto);

			//Executa a instrução SQL
			stmt2.execute();
			stmt2.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Professor alterado com sucesso!\nProfessor:\n"+nmProfessor+"","Cadastrado",1);
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//---------------------Remover dados---------------------------------------------------------------------------//
	public void removerProfessor(String cdMatricula){
		try{
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());
			Statement st = con.createStatement();											

			String condicao ="DELETE FROM tb_Professores WHERE cd_MatriculaProfessor='"+cdMatricula+"';";
			String condicao2 = "DELETE FROM tb_ProfessorComponentes WHERE cd_MatriculaProfessor='"+cdMatricula+"';";
			String condicao3 = "DELETE FROM tb_Usuarios_Sena_Dois WHERE cd_MatriculaProfessor='"+cdMatricula+"';";
			String condicao4 = "DELETE FROM tb_ProfessorDivulgacao WHERE cd_MatriculaProfessor='"+cdMatricula+"';";
			String condicao5 = "DELETE FROM tb_ProfessorDIvulgacaoCursos WHERE cd_MatriculaProfessor='"+cdMatricula+"';";
			String condicao6 = "DELETE FROM tb_ProfessorDivulgacaoDownloads WHERE cd_MatriculaProfessor='"+cdMatricula+"';";
			String condicao7 = "DELETE FROM tb_ProfessorDivulgacaoLinks WHERE cd_MatriculaProfessor='"+cdMatricula+"';";
			String condicao8 = "DELETE FROM tb_ProfessoresSemComponentes WHERE cd_MatriculaProfessor='"+cdMatricula+"';";

			st.executeUpdate(condicao);//Remover professor da tabela de professores
			st.executeUpdate(condicao2);//Remover dados do professor da tabela professorComponentes
			st.executeUpdate(condicao3);//Remover dados do professor da tabela de usuários do sena 2
			st.executeUpdate(condicao4);//Remover dados do professor da tabela de divulgacao de professores
			st.executeUpdate(condicao5);//Remover dados do professor da tabela de divulgação de cursos do professor
			st.executeUpdate(condicao6);//Remover dados do professor da tabela de divulgação de downloads dos professores
			st.executeUpdate(condicao7);//Remover dados do professor da tabela de divulgação de links dos professores
			st.executeUpdate(condicao8);//Remover dados do professor da tabela de professores sem componentes

			st.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Professor excluído com sucesso!","Exclusão",1);
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}	
	}
	//----Remover componente do professor
	public void removerComponente(String cdMatricula, String nmProfessor, String componente){
		try{
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());
			Statement st = con.createStatement();											

			String condicao = "DELETE FROM tb_ProfessorComponentes WHERE cd_MatriculaProfessor="+cdMatricula+" AND nm_Componente='"+componente+"';";
			
			st.executeUpdate(condicao);//Remover componente do professor

			st.close();
			
			//Condições de pesquisa
			String condicao2 = "SELECT nm_Componente FROM tb_ProfessorComponentes WHERE cd_MatriculaProfessor="+cdMatricula+";";

			Statement st2 = con.createStatement();

			//Executa o código sql para pesquisar curso
			ResultSet rs = st2.executeQuery(condicao2);
			
			int x = 0;
			if(rs.next()){
				x = 1;
			}else{
				x = 0;
			}
			if(x==1){
				
			}else{
				//Prepara o código sql para cadastrar curso tb_ProfessoresSemComponentes
				PreparedStatement stmt4 = con.prepareStatement("INSERT INTO tb_ProfessoresSemComponentes (cd_MatriculaProfessor, nm_Professor) VALUES (?,?);");
				//Preenche os campos
				stmt4.setString(1, cdMatricula.trim());
				stmt4.setString(2, nmProfessor.trim());				
				//Executa a instrução SQL
				stmt4.execute();
				stmt4.close();				
			}
			st2.close();
			con.close();
			JOptionPane.showMessageDialog(null,"Componente curricular removido com sucesso!","Exclusão",1);
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}	
	}
	//---------------------Consultar--------------------------------------------------------------------------------------//
	//-------Pesquisar cadastro para tabela do formulário professores---------------------------------------------//
	//--Tabela professores--//	
	DefaultTableModel tabela = new DefaultTableModel();

	public DefaultTableModel getTabela(){
		return this.tabela;
	}
	Object linha[] = new Object[3];

	//----Colunas da tabela cursos
	public void addColunasA(){
		tabela.addColumn("Matrícula do professor");
		tabela.addColumn("Nome do professor");
	}
	public void addColunasB(){
		tabela.addColumn("Matrícula do professor");
		tabela.addColumn("Nome do professor");
		tabela.addColumn("Componente curricular");
	}
	public void consultar(String nmProfessor, String nmComponente){
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao;
			int tb = 0;
			if(nmProfessor.equals("")){
				if(nmComponente.equals("Sem componente curricular associado")){
					tb=1;
					addColunasB();
					condicao= "SELECT cd_MatriculaProfessor, nm_Professor FROM tb_ProfessoresSemComponentes ORDER BY cd_MatriculaProfessor;";
				}else if(nmComponente.equals("Todos")){
					tb=0;
					addColunasA();
					condicao= "SELECT cd_MatriculaProfessor, nm_Professor FROM tb_Professores ORDER BY cd_MatriculaProfessor;";
				}else{
					tb = 1;
					addColunasB();
					condicao= "SELECT cd_MatriculaProfessor, nm_Professor, nm_Componente FROM tb_ProfessorComponentes WHERE nm_Componente='"+nmComponente+"' ORDER BY cd_MatriculaProfessor;";
				}
			}else{
				if(nmComponente.equals("Sem componente curricular associado")){
					tb=1;
					addColunasB();
					condicao= "SELECT cd_MatriculaProfessor, nm_Professor FROM tb_ProfessoresSemComponentes WHERE nm_Professor='"+nmProfessor+"' ORDER BY cd_MatriculaProfessor;";
				}else if(nmComponente.equals("Todos")){
					tb=0;
					addColunasA();
					condicao= "SELECT cd_MatriculaProfessor, nm_Professor FROM tb_Professores WHERE nm_Professor='"+nmProfessor+"' ORDER BY cd_MatriculaProfessor;";
				}else{
					tb = 1;
					addColunasB();
					condicao= "SELECT cd_MatriculaProfessor, nm_Professor, nm_Componente FROM tb_ProfessorComponentes WHERE nm_Professor='"+nmProfessor+"' nm_Componente='"+nmComponente+"' ORDER BY cd_MatriculaProfessor;";
				}					
			}

			Statement st = con.createStatement();
			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			//Percorre a consulta

			if(tb==0){
				while(rs.next()){
					Object linha[] = new Object[2];
					linha[0] = rs.getInt("cd_MatriculaProfessor");
					linha[1] = rs.getString("nm_Professor");
					tabela.addRow(linha);
				}
			}else if(tb==1){
				if(nmComponente.equals("Sem componente curricular associado")){
					while(rs.next()){
						Object linha[] = new Object[3];
						linha[0] = rs.getInt("cd_MatriculaProfessor");
						linha[1] = rs.getString("nm_Professor");
						linha[2] = "";
						tabela.addRow(linha);
					}	
				}else{
					while(rs.next()){
						Object linha[] = new Object[3];
						linha[0] = rs.getInt("cd_MatriculaProfessor");
						linha[1] = rs.getString("nm_Professor");
						linha[2] = rs.getString("nm_Componente");
						tabela.addRow(linha);
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
	//--Tabela componentes no curso CursoComponente
	DefaultTableModel tabelaComponentes = new DefaultTableModel();

	public DefaultTableModel getTabelaComponentes(){
		return this.tabelaComponentes;
	}
	Object linhaComponente[] = new Object[1];

	//----Colunas da tabela cursos
	public void addColunasComponentes(){
		tabelaComponentes.addColumn("Componente curricular");
	}
	//---Tabela de componentes cadastrados no curso para especialização
	public void consultarComponentes(String matricula){
		addColunasComponentes();
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao = "SELECT nm_Componente FROM tb_ProfessorComponentes WHERE cd_MatriculaProfessor="+matricula+" ORDER BY dt_Admissao;";;

			Statement st = con.createStatement();
			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			//Percorre a consulta
			while(rs.next()){
				@SuppressWarnings("unused")
				Object linhaComponentes[] = new Object[1];
				linhaComponentes[0] = rs.getString("nm_Componente");
				tabelaComponentes.addRow(linhaComponentes);
			}

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
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
	//--Consultar descrição do componente no curso
	public void consultarDsComponente(String nmComponente){
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			String condicao = "";
			
			if(!nmComponente.equals("----------") && !nmComponente.equals("")){
				condicao = "SELECT ds_Componente FROM tb_Componentes WHERE nm_Componente='"+nmComponente+"';";
				Statement st = con.createStatement();
				//Executa o código sql para pesquisar usuário				
				ResultSet rs = st.executeQuery(condicao);
				rs.next();
				if(rs.getString("ds_Componente").equals("")){
					this.dsComponente = "";
				}else{
					this.dsComponente = rs.getString("ds_Componente");
				}st.close();
			}
			
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	//--Professor
	public void consultar(String matricula){
		try {
			//Estabelece a conexão
			Class.forName(getDriver());
			Connection con = DriverManager.getConnection(getUrl(),getUsuario(),getSenha());

			//Condições de pesquisa
			String condicao = "SELECT * FROM tb_Professores WHERE cd_MatriculaProfessor="+matricula+";";
			String condicao2 = "SELECT * FROM tb_Usuarios_Sena_Dois WHERE cd_MatriculaProfessor="+matricula+";";
			
			Statement st = con.createStatement();
			//Executa o código sql para pesquisar usuário
			ResultSet rs = st.executeQuery(condicao);

			rs.next();
			this.cdMatricula = rs.getString("cd_MatriculaProfessor");
			this.nmProfessor = rs.getString("nm_Professor");
			this.cdCpf = rs.getString("cd_Cpf");
			this.cdRg = rs.getString("cd_Rg");
			this.rgOrgao = rs.getString("sg_RgOrgao");
			this.sexo = rs.getString("nm_Sexo");
			this.dtNascimento = rs.getString("dt_Nascimento");
			this.naturalidade = rs.getString("nm_Naturalidade");
			this.naturalidadeEstado = rs.getString("sg_EstadoNatural");
			this.nacionalidade = rs.getString("nm_Nacionalidade");
			this.endereco = rs.getString("nm_Endereco");
			this.numero = rs.getString("cd_Numero");
			this.complemento = rs.getString("ds_Complemento");
			this.bairro = rs.getString("nm_Bairro");
			this.cidade = rs.getString("nm_Cidade");
			this.estado = rs.getString("sg_Estado");
			this.cep = rs.getString("cd_Cep");
			this.telefone = rs.getString("cd_Telefone");
			this.email = rs.getString("nm_Email");
			this.nmPai = rs.getString("nm_Pai");
			this.nmMae = rs.getString("nm_Mae");
			this.dsCaminhoFoto = rs.getString("ds_CaminhoFoto");
			this.dtAdmissaoIe = rs.getString("dt_Admissao");
			
			ResultSet rs2 = st.executeQuery(condicao2);
			rs2.next();
			this.nmLogin = rs2.getString("nm_Login");
			this.cdSenha = rs2.getString("cd_Senha");

			st.close();
			con.close();
		}catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "Problemas na conexão ao banco de dados.\nVerifique acesso a rede.", "Erro", 2);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Problema interno do sistema.\nReinicie o sistema.","Erro",2);
		}
	}
	

	//-----Retorno de dados para exibição
	public String getBairro() {
		return bairro;
	}
	public String getCdCpf() {
		return cdCpf;
	}
	public String getCdMatricula() {
		return cdMatricula;
	}
	public String getCdRg() {
		return cdRg;
	}
	public String getRgOrgao(){
		return rgOrgao;
	}
	public String getCdSenha() {
		return cdSenha;
	}
	public String getCep() {
		return cep;
	}
	public String getCidade() {
		return cidade;
	}
	public String getComplemento() {
		return complemento;
	}
	public String getDsCaminhoFoto() {
		return dsCaminhoFoto;
	}
	public String getDtAdmissao() {
		return dtAdmissao;
	}
	public String getDtNascimento() {
		return dtNascimento;
	}
	public String getEmail() {
		return email;
	}
	public String getEndereco() {
		return endereco;
	}
	public String getEstado() {
		return estado;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public String getNaturalidade() {
		return naturalidade;
	}
	public String getNaturalidadeEstado(){
		return naturalidadeEstado;
	}
	public String getNmComponente() {
		return nmComponente;
	}
	public String getNmLogin() {
		return nmLogin;
	}
	public String getNmMae() {
		return nmMae;
	}
	public String getNmPai() {
		return nmPai;
	}
	public String getNmProfessor() {
		return nmProfessor;
	}
	public String getNumero() {
		return numero;
	}
	public String getSexo() {
		return sexo;
	}
	public String getDtAdmissaoIe(){
		return dtAdmissaoIe;
	}
	public String getTelefone() {
		return telefone;
	}
	public String getDsComponente(){
		return dsComponente;
	}
}
